package studyapp.model;

public class StudyTopic {

    private final int id;
    private String subjectName;
    private boolean completed;

    StudyTopic(int id, String subjectName) {
        this.id = id;
        this.subjectName = subjectName;
        completed = false;
    }

    public String getTopicName() {
        return this.subjectName;
    }

    public int getId() {
        return this.id;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setSubjectName(String newName) {
        this.subjectName = newName;
    }

    public void setCompleted(boolean newStatus) {
        this.completed = newStatus;
    }

}
