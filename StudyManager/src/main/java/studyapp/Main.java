package studyapp;

import studyapp.model.Topic;
import studyapp.repository.TopicRepository;
import studyapp.service.TopicService;
import studyapp.util.Input;
import studyapp.enums.CompleteTopicResult;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        TopicRepository repository = new TopicRepository();
        TopicService service = new TopicService(repository);

        systemStart(service);
    }

    public static void systemStart(TopicService service) throws IOException {

        int option;

        do {
            option = displayMenu();

            switch (option) {
                case 1 -> addTopic(service);
                case 2 -> listTopics(service);
                case 3 -> searchTopicsByName(service);
                case 4 -> concludeTopic(service);
                case 5 -> editTopic(service);
                case 6 -> deleteTopic(service);
                case 0 -> System.out.println("Exiting the system.");
                default -> System.out.println("This is not an option.");
            }
        } while(option != 0);
    }

    public static int displayMenu() {

        System.out.print("""
                ************************
                     STUDY MANAGER
                ************************
                1. Add Topic;
                2. List Topics;
                3. Search Topics
                4. Conclude Topic
                5. Edit Topic
                6. Delete Topic
                0. Quit
                ************************
                """);

        return Input.readInt("Enter your choice: ");
    }

    public static void displayTopic(Topic topic) {
        if (topic == null) {
            System.out.println("Topic not found.");
            return;
        }
        System.out.printf("Topic Name: %s\nTopic Id: %d\nConcluded: %b\n",
                topic.getTopicName(), topic.getId(), topic.getIsCompleted());
    }

    public static void addTopic(TopicService service)  {
        String topicName = Input.readString("Enter topic name: ").toUpperCase();
        service.addTopic(topicName);
        System.out.println("Topic " + topicName + " Added successfully");
    }

    public static void listTopics(TopicService service) {
        if (service.getAllTopics().isEmpty()) {
            System.out.println("No topics saved yet.");
            return;
        }
        for (Topic topic : service.getAllTopics()) {
            String statusBox = topic.getIsCompleted() ? "[✔]" : "[ ]";
            System.out.printf("id: %d | %s %s\n", topic.getId(), statusBox, topic.getTopicName());
        }
    }

    public static void searchTopicsByName(TopicService service) {
        String name = Input.readString("Enter topic name: ").toUpperCase();

        Topic topic = service.findByName(name);
        displayTopic(topic);
    }

    public static void concludeTopic(TopicService service) {
        int id = Input.readInt("Enter the topic id: ");
        CompleteTopicResult result = service.completeTopic(id);

        switch (result) {
            case CompleteTopicResult.NOT_FOUND -> System.out.println("Topic not found.");
            case CompleteTopicResult.ALREADY_COMPLETED -> System.out.println("Topic already completed.");
            case CompleteTopicResult.COMPLETED -> {
                Topic topic = service.getTopicById(id);
                System.out.printf("[✔] %s\nTopic completed.\n", topic.getTopicName());
            }
        }
    }

    public static void editTopic(TopicService service)  {
        int id = Input.readInt("Enter the topic id: ");
        Topic topic = service.getTopicById(id);

        if (topic == null) {
            System.out.println("Topic not found.");
            return;
        }
        displayTopic(topic);

        if (topic.getIsCompleted()) {
            int choice = Input.readInt("1. Change name\n2. Change to not completed\nEnter your choice: ");
            if (choice == 2) {
                service.updateTopicStatus(id, false);
                System.out.println("Topic Status was updated successfully.");
                return;
            } else if (choice != 1) {
                System.out.println("Not a valid option.");
                return;
            }
        }
        String newName = Input.readString("Enter the new name for the topic: ").toUpperCase();
        service.updateTopicName(id, newName);
        System.out.println("Topic name was updated to " + newName + " successfully.");
    }

    public static void deleteTopic(TopicService service)  {
        int id = Input.readInt("Enter the id of the topic you want to delete: ");
        Topic topic = service.getTopicById(id);

        if (topic == null) {
            System.out.println("Topic not found.");
        } else {
            String name = topic.getTopicName();
            service.removeTopic(id);
            System.out.printf("Topic %s was deleted successfully.\n", name);
        }
    }
}
