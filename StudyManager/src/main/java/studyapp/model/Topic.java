package studyapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Topic {

    @JsonProperty("id")
    private int id;
    @JsonProperty("topic_name")
    private String topicName;
    @JsonProperty("is_completed")
    private boolean isCompleted;

    Topic() {}

    public Topic(int id, String topicName) {
        this.id = id;
        this.topicName = topicName;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public int getId() {
        return this.id;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public void setSubjectName(String newName) {
        this.topicName = newName;
    }

    public void setIsCompleted(boolean newStatus) {
        this.isCompleted = newStatus;
    }

}
