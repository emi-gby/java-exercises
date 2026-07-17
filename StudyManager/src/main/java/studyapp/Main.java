package studyapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import studyapp.model.Topic;
import studyapp.model.TopicsMap;
import studyapp.util.Input;
import studyapp.enums.CompleteTopicResult;
import studyapp.util.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Topic> jsonTopics = JsonUtils.fromJsonToList(new TypeReference<List<Topic>>() {});
        TreeMap<Integer, Topic> topics = JsonUtils.fromListToMap(jsonTopics);
        TopicsMap topicsMap = new TopicsMap(topics);

        systemStart(topicsMap);

    }

    public static void systemStart(TopicsMap topicsMap) throws IOException {

        int option;

        do {
            option = displayMenu();

            switch (option) {
                case 1 -> addTopic(topicsMap);
                case 2 -> listTopics(topicsMap);
                case 3 -> searchTopicsByName(topicsMap);
                case 4 -> concludeTopic(topicsMap);
                case 5 -> editTopic(topicsMap);
                case 6 -> deleteTopic(topicsMap);
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

    public static void addTopic(TopicsMap topicsMap) throws IOException {
        String topicName = Input.readString("Enter topic name: ").toUpperCase();

        topicsMap.addTopic(topicName);
        JsonUtils.updateJson(topicsMap.getTreeMap());
        System.out.println("Topic " + topicName + " Added successfully");
    }

    public static void listTopics(TopicsMap topicsMap) {
        for (Topic topic : topicsMap.getTopics()) {
            if (topic.getIsCompleted()) {
                System.out.println("id: "+ topic.getId() + "| [✔] " + topic.getTopicName());
            } else {
                System.out.println("id: "+ topic.getId() + "| [ ] " + topic.getTopicName());
            }
        }
    }

    public static void searchTopicsByName(TopicsMap topicsMap) {
        String name = Input.readString("Enter topic name: ").toUpperCase();

        Topic topic = topicsMap.findByName(name);
        if (topic == null) {
            System.out.println("Topic not found.");
            return;
        }
        displayTopic(topic);
    }

    public static void concludeTopic(TopicsMap topicsMap) throws IOException {
        int id = Input.readInt("Enter the topic id: ");

        CompleteTopicResult result = topicsMap.completeTopic(id);

        switch (result) {
            case CompleteTopicResult.NOT_FOUND -> System.out.println("Topic not found.");
            case CompleteTopicResult.ALREADY_COMPLETED -> System.out.println("Topic already completed.");
            case CompleteTopicResult.COMPLETED -> {
                Topic topic = topicsMap.getTopic(id);
                JsonUtils.updateJson(topicsMap.getTreeMap());
                System.out.printf("[✔] %s\nTopic completed.\n", topic.getTopicName());
            }
        }
    }

    public static void editTopic(TopicsMap topicsMap) throws IOException {
        int id = Input.readInt("Enter the topic id: ");

        Topic topic = topicsMap.getTopic(id);

        if (topic == null) {
            System.out.println("Topic not found.");
            return;
        }
        displayTopic(topic);

        if (topic.getIsCompleted()) {
            int choice = Input.readInt("1. Change name\n2. Change to not completed\nEnter your choice: ");
            if (choice == 2) {
                topic.setIsCompleted(false);
                JsonUtils.updateJson(topicsMap.getTreeMap());
                System.out.println("Topic Status was updated successfully.");
                return;
            } else if (choice != 1) {
                System.out.println("Not a valid option.");
                return;
            }
        }
        String newName = Input.readString("Enter the new name for the topic: ").toUpperCase();
        topic.setSubjectName(newName);
        JsonUtils.updateJson(topicsMap.getTreeMap());
        System.out.println("Topic " + newName + " was updated successfully.");
    }

    public static void deleteTopic(TopicsMap topicsMap) throws IOException {
        int id = Input.readInt("Enter the id of the topic you want to delete: ");
        Topic topic = topicsMap.getTopic(id);

        if (topic == null) {
            System.out.println("Topic not found.");
        } else {
            topicsMap.removeTopic(id);
            System.out.printf("Topic %s was deleted successfully.\n", topic.getTopicName());
            JsonUtils.updateJson(topicsMap.getTreeMap());
        }

    }
}
