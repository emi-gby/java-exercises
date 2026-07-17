package studyapp.service;

import studyapp.enums.CompleteTopicResult;
import studyapp.model.Topic;
import studyapp.repository.TopicRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class TopicService {
    private final TopicRepository repository;
    private final Map<Integer, Topic> topics;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
        this.topics = repository.loadTopics();
    }

    private int generateNewId() {
        if (topics.isEmpty()) return 1;
        // Since it's a Sorted Map (TreeMap), we can find the last key safely
        return ((java.util.TreeMap<Integer, Topic>) topics).lastKey() + 1;
    }

    public Collection<Topic> getAllTopics() {
        // Return an unmodifiable view to ensure safety (encapsulation)
        return Collections.unmodifiableCollection(topics.values());
    }

    public Topic getTopicById(int id) {
        return topics.get(id);
    }

    public void addTopic(String topicName) {
        int id = generateNewId();
        Topic topic = new Topic(id, topicName);
        topics.put(id, topic);
        repository.saveTopics(topics); // Auto-save on mutation
    }

    public Topic findByName(String topicName) {
        return topics.values().stream()
                .filter(t -> t.getTopicName().equalsIgnoreCase(topicName))
                .findFirst()
                .orElse(null);
    }

    public CompleteTopicResult completeTopic(int id) {
        Topic topic = topics.get(id);
        if (topic == null) return CompleteTopicResult.NOT_FOUND;
        if (topic.getIsCompleted()) return CompleteTopicResult.ALREADY_COMPLETED;

        topic.setIsCompleted(true);
        repository.saveTopics(topics);
        return CompleteTopicResult.COMPLETED;
    }

    public void updateTopicName(int id, String newName) {
        Topic topic = topics.get(id);
        if (topic != null) {
            topic.setTopicName(newName);
            repository.saveTopics(topics);
        }
    }

    public void updateTopicStatus(int id, boolean isCompleted) {
        Topic topic = topics.get(id);
        if (topic != null) {
            topic.setIsCompleted(isCompleted);
            repository.saveTopics(topics);
        }
    }

    public void removeTopic(int id) {
        if (topics.containsKey(id)) {
            topics.remove(id);
            repository.saveTopics(topics);
        }
    }
}