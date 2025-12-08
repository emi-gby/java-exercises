import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        char playAgain = 'y';

        String[] slotSymbols = { "🍒", "🍋", "🍉", "🍇", "♥️", "💎","🍒", "🍋", "🍉",
                "🍒", "🍋", "🍉", "🍇","🍋", "🍉","♥️"};

        System.out.println("--------------------------------------");
        System.out.println("-----------JAVA SLOT MACHINE----------");
        System.out.println("--------------------------------------");
        System.out.print("What's your current balance: ");
        double balance = scanner.nextDouble();

        do{

            System.out.printf("Current balance: $%.2f \n", balance);
            System.out.print("Place the amount you want to bet: ");
            double betAmount = scanner.nextDouble();


            if(betAmount > balance){
                System.out.println("You can't bet an amount greater than your balance!");
                continue;
            }
            else if(betAmount <= 0){
                System.out.println("Bet must be greater that 0!!!");
                continue;
            }
            balance -= betAmount;

            System.out.println("Spinning.....");
            balance += spinSlots(slotSymbols, betAmount);


            if(balance == 0){
                System.out.println("GAME OVER! You lost everything. :)");
                break;
            }

            System.out.print("Do you want to play again (Y/N): ");
            playAgain = scanner.next().toLowerCase().charAt(0);

        }while(playAgain != 'n');

        System.out.printf("\nYour final balance is: $%.2f",balance);
        scanner.close();
    }

    public static double spinSlots(String[] slotSymbols, double betAmount){
        Random random = new Random();

        String[] slotBet = new String[3];   //empty array for the slots
        int totalScore = 0;

        System.out.println("------------------------");
        for(int i = 0; i < 3; i++){
            String slotChoice = slotSymbols[random.nextInt(0,slotSymbols.length)];  //random choice in the symbol array
            slotBet[i] = slotChoice;
            System.out.print("|" + slotChoice + "|");
        }
        System.out.println();
        System.out.println("------------------------");


        for(String slot : slotBet){
            if (slot.equals("♥️")){
                totalScore += 3;   //points
            }
            else if(slot.equals("💎")){
                totalScore += 7;
            }
        }
        double amountIncrement = totalScore*betAmount;
        if(totalScore == 0){
            System.out.println("You lost this round. :(");
        }
        else{
            System.out.printf("\nYou won $%.2f\n",amountIncrement);
        }
        return amountIncrement;
    }
}
