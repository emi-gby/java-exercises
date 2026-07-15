package studyapp;

import studyapp.model.StudyTopic;
import studyapp.model.TopicsList;
import studyapp.util.Input;
import studyapp.enums.CompleteTopicResult;

import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<Integer, StudyTopic> listTopics = new TreeMap<>();
        TopicsList topicsList = new TopicsList(listTopics);

        systemStart(topicsList);

        scanner.close();
    }

    public static void systemStart(TopicsList topicsList) {
        int option;

        do {
            option = displayMenu();

            switch (option) {
                case 1 -> addTopic(topicsList);
                case 2 -> listTopics(topicsList);
                case 3 -> searchTopicsByName(topicsList);
                case 4 -> concludeTopic(topicsList);
                case 5 -> editTopic(topicsList);
                case 6 -> deleteTopic(topicsList);
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

    public static void displayTopic(StudyTopic topic) {
        if (topic == null) {
            System.out.println("Topic not found.");
            return;
        }

        System.out.printf("Topic Name: %s\nTopic Id: %d\nConcluded: %b\n",
                topic.getTopicName(), topic.getId(), topic.isCompleted());
    }

    public static void addTopic(TopicsList topicsList) {
        String topicName = Input.readString("Enter topic name: ").toUpperCase();

        topicsList.addTopic(topicName);
        System.out.println("Topic " + topicName + " Added successfully");
    }

    public static void listTopics(TopicsList topicsList) {
        for (StudyTopic topic : topicsList.getTopics()) {
            if (topic.isCompleted()) {
                System.out.println("id: "+ topic.getId() + "| [✔] " + topic.getTopicName());
            } else {
                System.out.println("id: "+ topic.getId() + "| [ ] " + topic.getTopicName());
            }
        }
    }

    public static void searchTopicsByName(TopicsList topicsList) {
        String name = Input.readString("Enter topic name: ").toUpperCase();

        StudyTopic topic = topicsList.findByName(name);
        if (topic == null) {
            System.out.println("Topic not found.");
            return;
        }
        displayTopic(topic);
    }

    public static void concludeTopic(TopicsList topicsList) {
        int id = Input.readInt("Enter the topic id: ");

        CompleteTopicResult result = topicsList.completeTopic(id);

        switch (result) {
            case CompleteTopicResult.NOT_FOUND -> System.out.println("Topic not found.");
            case CompleteTopicResult.ALREADY_COMPLETED -> System.out.println("Topic already completed.");
            case CompleteTopicResult.COMPLETED -> {
                StudyTopic topic = topicsList.getTopic(id);
                System.out.printf("[✔] %s\nTopic completed.\n", topic.getTopicName());
            }
        }
    }

    public static void editTopic(TopicsList topicsList) {
        int id = Input.readInt("Enter the topic id: ");

        StudyTopic topic = topicsList.getTopic(id);

        if (topic == null) {
            System.out.println("Topic not found.");
            return;
        }
        displayTopic(topic);

        if (topic.isCompleted()) {
            int choice = Input.readInt("1. Change name\n2. Change to not completed\nEnter your choice: ");
            if (choice == 2) {
                topic.setCompleted(false);
                System.out.println("Topic Status was updated successfully.");
                return;
            }
        }
        String newName = Input.readString("Enter the new name for the topic: ").toUpperCase();
        topic.setSubjectName(newName);
        System.out.println("Topic " + newName + " was updated successfully.");
    }

    public static void deleteTopic(TopicsList topicsList) {
        int id = Input.readInt("Enter the id of the topic you want to delete: ");
        StudyTopic topic = topicsList.getTopic(id);

        if (topic == null) {
            System.out.println("Topic not found.");
        } else {
            System.out.printf("Topic %s was deleted successfully.\n", topic.getTopicName());
            topicsList.removeTopic(id);
        }

    }
}