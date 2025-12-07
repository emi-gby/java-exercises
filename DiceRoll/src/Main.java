import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int totalResult = 0;

        System.out.print("How many times do you want to row: ");
        int timesToRoll = scanner.nextInt();

        if(timesToRoll < 0){
            System.out.println("Number must be greater than 0.");
        }
        else{
            for(int i = 0; i < timesToRoll; i++){
                int numberRolled = random.nextInt(1, 7);
                System.out.printf("You rolled: %d\n", numberRolled);
                printDice(numberRolled);
                totalResult += numberRolled;
            }
            System.out.printf("\nYou rolled a total of: %d / %d",totalResult, timesToRoll * 6);
        }
        scanner.close();
    }

    public static void printDice(int numberRolled) {
        String dice1 = """
                +-------+
                |       |
                |   o   |
                |       |
                +-------+
                """;

        String dice2 = """
                +-------+
                | o     |
                |       |
                |     o |
                +-------+
                """;

        String dice3 = """
                +-------+
                | o     |
                |   o   |
                |     o |
                +-------+
                """;

        String dice4 = """
                +-------+
                | o   o |
                |       |
                | o   o |
                +-------+
                """;

        String dice5 = """
                +-------+
                | o   o |
                |   o   |
                | o   o |
                +-------+
                """;

        String dice6 = """
                +-------+
                | o   o |
                | o   o |
                | o   o |
                +-------+
                """;
        switch (numberRolled) {
            case 1 -> System.out.println(dice1);
            case 2 -> System.out.println(dice2);
            case 3 -> System.out.println(dice3);
            case 4 -> System.out.println(dice4);
            case 5 -> System.out.println(dice5);
            case 6 -> System.out.println(dice6);
        }
    }
}