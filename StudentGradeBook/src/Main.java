import java.util.*;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        HashMap<Integer, StudentInfo> gradeBook = new HashMap<>();
        
        int studentId = 1;

        String userInput = "";

        while(!userInput.equals("q")){

            System.out.print("""
                    -------GradeBook-------
                    1. Add student;
                    2. List students;
                    3. View student info;
                    4. Delete student info;
                    5. Update student info;
                    Q. Quit;
                    -------------------------
                    Chose an option :""");
            userInput = scanner.nextLine();

            switch (userInput){
                case "1" -> addStudent(scanner,gradeBook, studentId);
                case "2" -> listStudents(gradeBook);
                case "3" -> viewStudentInfo(scanner, gradeBook);
                case "4" -> deleteStudentInfo(scanner, gradeBook);
                case "5" -> updateStudentInfo(scanner, gradeBook);
                case "Q" -> System.out.println("System finalization completed.");
                default -> System.out.println("Not a valid option!");
            }
            studentId++;
        }
        scanner.close();
    }
    static void addStudent(Scanner scanner, 
                           HashMap<Integer, StudentInfo> gradeBook,
                           int studentId){

        List<Double> grades = new ArrayList<>();
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        if(!name.isEmpty()){
            try{
                for(int i=1; i<=3; i++){

                    System.out.printf("Enter student grade #%d: ", i);
                    double grade = scanner.nextDouble();
                    scanner.nextLine();

                    while(grade<0 || grade>10){
                        System.out.print("Grade must be between 0 and 10.\n");
                        System.out.printf("Enter student grade #%d: ", i);
                        grade = scanner.nextDouble();
                        scanner.nextLine();
                    }

                    grades.add(grade);
                }

                StudentInfo studentInfo = new StudentInfo(name, grades);

                gradeBook.put(studentId, studentInfo);

                System.out.println("Student added successfully.");

            }
            catch(InputMismatchException e){
                scanner.nextLine();
                System.out.println("Enter only numbers please.");
            }
        }
        else{
            System.out.println("Name can't be empty.");
        }

    }
    static void listStudents(HashMap<Integer, StudentInfo> gradeBook){

        for(Map.Entry<Integer, StudentInfo> entry : gradeBook.entrySet()){
            System.out.println("----------------------------------");

            System.out.println("Id : " + entry.getKey());

            String name = entry.getValue().getName();
            List<Double> grades = entry.getValue().getGrades();

            System.out.println("Name: "+ name);
            System.out.println("Grades: "+ grades);

        }
    }
    static int searchStudent(Scanner scanner,
                                     HashMap<Integer, StudentInfo> gradeBook){

        try{
            System.out.print("Enter student id: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("----------------------------------");

            StudentInfo student = gradeBook.get(studentId);

            if(student != null){
                return studentId;
            }
            else{
                System.out.println("Student Not Found.");
            }


        }
        catch(InputMismatchException e){
            scanner.nextLine();
            System.out.println("Enter only integer numbers please.");
        }

        return -1;
    }



    static void viewStudentInfo(Scanner scanner,
                                 HashMap<Integer, StudentInfo> gradeBook){

        int studentId = searchStudent(scanner, gradeBook);

        if(studentId != -1) {
            StudentInfo student = gradeBook.get(studentId);

            String name = student.getName();
            List<Double> grades = student.getGrades();
            double average = 0;
            int gradeNum = 0;

            System.out.println("Name: " + name);
            for (double grade : grades) {
                gradeNum++;
                System.out.printf("Grade #%d: %.1f\n", gradeNum, grade);
                average += grade;
            }

            average = average / gradeNum;

            System.out.printf("Average: %.1f\n", average);

            if (average >= 7) {
                System.out.println("Status : Approved");
            } else {
                System.out.println("Status : Reproved");
            }
        }

    }
    static void deleteStudentInfo(Scanner scanner,
                                  HashMap<Integer, StudentInfo> gradeBook){

        int studentId = searchStudent(scanner, gradeBook);

        if(studentId != -1) {
            gradeBook.remove(studentId);
            System.out.println("Student deleted successfully.");
        }

    }
    static void updateStudentInfo(Scanner scanner,
                                  HashMap<Integer, StudentInfo> gradeBook){

        int studentId = searchStudent(scanner, gradeBook);

        if(studentId != -1){
            StudentInfo student = gradeBook.get(studentId);

            System.out.print("""
                    1. Update name;
                    2. Update grades;
                    Q. Quit;
                    -------------------------
                    Chose an option :""");

            String userInput = scanner.nextLine();

            switch (userInput){
                case "1" -> {
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    if(!newName.isEmpty()){
                        student.setName(newName);
                        System.out.println("Name updated successfully.");
                    }
                    else{
                        System.out.println("Name can't be empty.");
                    }
                }
                case "2" -> {
                    try{
                        List<Double> grades = new ArrayList<>();
                        for(int i=1; i<=3; i++){

                            System.out.printf("Enter student grade #%d: ", i);
                            double grade = scanner.nextDouble();
                            scanner.nextLine();

                            while(grade<0 || grade>10){
                                System.out.print("Grade must be between 0 and 10.\n");
                                System.out.printf("Enter student grade #%d: ", i);
                                grade = scanner.nextDouble();
                                scanner.nextLine();
                            }

                            grades.add(grade);
                        }
                        student.setGrades(grades);
                        System.out.println("Grades updated successfully.");
                    }
                    catch(InputMismatchException e){
                        scanner.nextLine();
                        System.out.println("Enter only numbers please.");
                    }

                }
                case "Q" -> System.out.println("Returning...");
                default -> System.out.println("Not a valid option!");
            }
        }

    }
}
