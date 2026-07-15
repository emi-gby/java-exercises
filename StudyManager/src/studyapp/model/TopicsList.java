package studyapp.model;

import studyapp.enums.CompleteTopicResult;

import java.util.Collection;
import java.util.TreeMap;

public class TopicsList {

    private final TreeMap<Integer, StudyTopic> topics;

    public TopicsList(TreeMap<Integer, StudyTopic> topics) {
        this.topics = topics;
    }

    private int getNewId() {
        return topics.isEmpty() ? 1 : topics.lastKey() + 1;
    }

    public StudyTopic getTopic(int id) {
        return topics.get(id);
    }

    public Collection<StudyTopic> getTopics() {
        return topics.values();
    }

    public void addTopic(String topicName) {
        int id = getNewId();
        StudyTopic topic = new StudyTopic(id, topicName);
        topics.put(id, topic);
    }

    public StudyTopic findByName(String topicName) {
        for (StudyTopic topic : topics.values()) {
            if (topic.getTopicName().equals(topicName)) {
                return topic;
            }
        }
        return null;
    }

    public CompleteTopicResult completeTopic (int id) {
        StudyTopic topic = topics.get(id);

        if (topic == null) {
            return CompleteTopicResult.NOT_FOUND;
        }

        if (topic.isCompleted()) {
            return CompleteTopicResult.ALREADY_COMPLETED;
        }

        topic.setCompleted(true);
        return CompleteTopicResult.COMPLETED;
    }

    public void removeTopic(int id) {
        topics.remove(id);
    }

}
