package studyapp.model;

import studyapp.enums.CompleteTopicResult;

import java.util.Collection;
import java.util.TreeMap;

public class TopicsMap {

    private final TreeMap<Integer, Topic> topics;

    public TopicsMap(TreeMap<Integer, Topic> topics) {
        this.topics = topics;
    }

    private int getNewId() {
        return topics.isEmpty() ? 1 : topics.lastKey() + 1;
    }

    public TreeMap<Integer, Topic> getTreeMap() { return topics; }

    public Topic getTopic(int id) {
        return topics.get(id);
    }

    public Collection<Topic> getTopics() {
        return topics.values();
    }

    public void addTopic(String topicName) {
        int id = getNewId();
        Topic topic = new Topic(id, topicName);
        topics.put(id, topic);
    }

    public Topic findByName(String topicName) {
        for (Topic topic : topics.values()) {
            if (topic.getTopicName().equals(topicName)) {
                return topic;
            }
        }
        return null;
    }

    public CompleteTopicResult completeTopic (int id) {
        Topic topic = topics.get(id);

        if (topic == null) {
            return CompleteTopicResult.NOT_FOUND;
        }

        if (topic.getIsCompleted()) {
            return CompleteTopicResult.ALREADY_COMPLETED;
        }

        topic.setIsCompleted(true);
        return CompleteTopicResult.COMPLETED;
    }

    public void removeTopic(int id) {
        topics.remove(id);
    }

}
