package studyapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import studyapp.model.Topic;
import studyapp.model.TopicsList;
import studyapp.util.Input;
import studyapp.enums.CompleteTopicResult;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        ObjectMapper mapper = new ObjectMapper();

        File file = new File("src/main/resources/topics.json");

        List<Topic> jsonTopics = mapper.readValue(file,
                new TypeReference<List<Topic>>() {});
        TreeMap<Integer, Topic> topics = new TreeMap<>(
                jsonTopics.stream()
                        .collect(Collectors.toMap(
                                Topic::getId,
                                topic -> topic
                        ))
        );
        TopicsList topicsList = new TopicsList(topics);

        systemStart(mapper, file, topics, topicsList);

           scanner.close();
    }

    public static void updateJson(ObjectMapper mapper, File file, TreeMap<Integer, Topic> topics) throws IOException {
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(file, topics.values());
    }

    public static void systemStart(ObjectMapper mapper, File file, TreeMap<Integer,
            Topic> topics,TopicsList topicsList) throws IOException {

        int option;

        do {
            option = displayMenu();

            switch (option) {
                case 1 -> addTopic(mapper, file, topics, topicsList);
                case 2 -> listTopics(topicsList);
                case 3 -> searchTopicsByName(topicsList);
                case 4 -> concludeTopic(mapper, file, topics, topicsList);
                case 5 -> editTopic(mapper, file, topics, topicsList);
                case 6 -> deleteTopic(mapper, file, topics, topicsList);
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

    public static void addTopic(ObjectMapper mapper, File file, TreeMap<Integer,
            Topic> topics,TopicsList topicsList) throws IOException {
        String topicName = Input.readString("Enter topic name: ").toUpperCase();

        topicsList.addTopic(topicName);
        updateJson(mapper, file, topics);
        System.out.println("Topic " + topicName + " Added successfully");
    }

    public static void listTopics(TopicsList topicsList) {
        for (Topic topic : topicsList.getTopics()) {
            if (topic.getIsCompleted()) {
                System.out.println("id: "+ topic.getId() + "| [✔] " + topic.getTopicName());
            } else {
                System.out.println("id: "+ topic.getId() + "| [ ] " + topic.getTopicName());
            }
        }
    }

    public static void searchTopicsByName(TopicsList topicsList) {
        String name = Input.readString("Enter topic name: ").toUpperCase();

        Topic topic = topicsList.findByName(name);
        if (topic == null) {
            System.out.println("Topic not found.");
            return;
        }
        displayTopic(topic);
    }

    public static void concludeTopic(ObjectMapper mapper, File file, TreeMap<Integer,
            Topic> topics,TopicsList topicsList) throws IOException {
        int id = Input.readInt("Enter the topic id: ");

        CompleteTopicResult result = topicsList.completeTopic(id);

        switch (result) {
            case CompleteTopicResult.NOT_FOUND -> System.out.println("Topic not found.");
            case CompleteTopicResult.ALREADY_COMPLETED -> System.out.println("Topic already completed.");
            case CompleteTopicResult.COMPLETED -> {
                Topic topic = topicsList.getTopic(id);
                updateJson(mapper, file, topics);
                System.out.printf("[✔] %s\nTopic completed.\n", topic.getTopicName());
            }
        }
    }

    public static void editTopic(ObjectMapper mapper, File file, TreeMap<Integer,
            Topic> topics,TopicsList topicsList) throws IOException {
        int id = Input.readInt("Enter the topic id: ");

        Topic topic = topicsList.getTopic(id);

        if (topic == null) {
            System.out.println("Topic not found.");
            return;
        }
        displayTopic(topic);

        if (topic.getIsCompleted()) {
            int choice = Input.readInt("1. Change name\n2. Change to not completed\nEnter your choice: ");
            if (choice == 2) {
                topic.setIsCompleted(false);
                updateJson(mapper, file, topics);
                System.out.println("Topic Status was updated successfully.");
                return;
            }
        }
        String newName = Input.readString("Enter the new name for the topic: ").toUpperCase();
        topic.setSubjectName(newName);
        System.out.println("Topic " + newName + " was updated successfully.");
    }

    public static void deleteTopic(ObjectMapper mapper, File file, TreeMap<Integer,
            Topic> topics,TopicsList topicsList) throws IOException {
        int id = Input.readInt("Enter the id of the topic you want to delete: ");
        Topic topic = topicsList.getTopic(id);

        if (topic == null) {
            System.out.println("Topic not found.");
        } else {
            topicsList.removeTopic(id);
            System.out.printf("Topic %s was deleted successfully.\n", topic.getTopicName());
            updateJson(mapper, file, topics);
        }

    }
}
