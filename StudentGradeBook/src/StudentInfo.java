import java.util.List;

public class StudentInfo {

    private String name;
    private List<Double> grades;

    StudentInfo(String name, List<Double> grades){
        this.name = name;
        this.grades = grades;
    }

    public String getName(){
        return this.name;
    }
    public List<Double> getGrades(){
        return this.grades;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setGrades(List<Double> grades){
        this.grades = grades;
    }
}
