import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> tasks = new ArrayList<>();

        String userInput = "";


        while(!userInput.equals("Q")){
            System.out.print("""
                    -------Task Manager-------
                    1. Add task;
                    2. Show tasks;
                    3. Complete task;
                    Q. Quit;
                    -------------------------
                    Chose an option :""");
            userInput = scanner.nextLine().toUpperCase();

            switch (userInput){
                case "1" -> addTask(scanner,tasks);
                case "2" -> showTask(tasks);
                case "3" -> completeTask(scanner,tasks);
                case "Q" -> System.out.println("System finalization completed.");
                default -> System.out.println("Not a valid option!");
            }
        }

        scanner.close();

    }
    static void addTask(Scanner scanner, ArrayList<String> tasks){
        System.out.print("Enter the task: ");
        String task = scanner.nextLine().toLowerCase();

        tasks.add(task);
        System.out.println("Task added successfully.");


    }
    static void showTask(ArrayList<String> tasks){

        if(tasks.isEmpty()){
            System.out.println("All tasks completed!");
        }
        else{
            System.out.println("Tasks: ");

            for(String task: tasks){
                System.out.println(task);
            }
        }

    }
    static void completeTask(Scanner scanner, ArrayList<String> tasks){
        System.out.print("Which task do you wanna complete: ");
        String taskToComplete = scanner.nextLine().toLowerCase();


        if(tasks.remove(taskToComplete)){
            System.out.println("Task completed successfully.");
        }
        else{
            System.out.println("Task not founded!");
        }

    }
}
