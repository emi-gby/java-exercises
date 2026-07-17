package studyapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Topic {

    @JsonProperty("id")
    private int id;
    @JsonProperty("topic_name")
    private String topicName;
    @JsonProperty("is_completed")
    private boolean isCompleted;

    //Jackson needs this
    Topic() {}

    public Topic(int id, String topicName) {
        this.id = id;
        this.topicName = topicName;
        this.isCompleted = false;
    }

    public String getTopicName() { return this.topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }

    public int getId() { return this.id; }

    public boolean getIsCompleted() { return this.isCompleted; }
    public void setIsCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }

}
