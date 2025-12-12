import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;
        int timesPlayed = 0;

        String[] gameChoices = {"rock","paper","scissors"};


        while(true){
            System.out.println("----Rock Paper Scissor----");
            String computerChoice = gameChoices[random.nextInt(0,3)];
            System.out.print("Enter your choice(rock,paper,scissors) or 'Q' to quit: ");
            String userChoice = scanner.nextLine().toLowerCase();

            if(userChoice.equals("q")){
                System.out.println("Exiting the game.");
                break;
            }

            timesPlayed++;
            if(declareWinner(computerChoice,userChoice)) {
                totalScore++;
            }
        }
        System.out.printf("Your final score is %d/%d.",totalScore,timesPlayed);

        scanner.close();
    }
    public static boolean declareWinner(String computerChoice, String userChoice){
        System.out.printf("\nComputer chose: %s\nYou chose: %s\n",computerChoice,userChoice);

        boolean returnScore = false;
        System.out.println("--------------------------");
        if(userChoice.equals(computerChoice)){
            System.out.println("It's a Tie.");
            System.out.println("--------------------------");
            return false;
        }

        switch (userChoice){
            case "paper" -> {
                if(computerChoice.equals("rock")){
                    System.out.println("You Won!");
                    returnScore = true;
                }
                else{
                    System.out.println("Computer Won!");
                }
            }
            case "rock" -> {
                if(computerChoice.equals("paper")){
                    System.out.println("Computer Won!");
                }
                else{
                    System.out.println("You Won!");
                    returnScore = true;
                }
            }
            case "scissors" -> {
                if(computerChoice.equals("paper")){
                    System.out.println("You Won!");
                    returnScore = true;
                }
                else{
                    System.out.println("Computer Won!");
                }
            }
            default -> System.out.println("Invalid choice.");
        }
        System.out.println("--------------------------");
        return returnScore;
    }
}
