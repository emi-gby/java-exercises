import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        /*
         * add handling errors in methods
         * improve code
         * scanner in createAccount*/

        Scanner scanner = new Scanner(System.in);

        int idAccount = 1;
        HashMap<Integer, AccountDetails> accounts = new HashMap<>();


        String userInput = "";

        while(!userInput.equals("Q")){
            System.out.print("""
                    \n-------Bank Account Manager-------
                    1. Add account;
                    2. Deposit money;
                    3. Withdraw money;
                    4. View account details;
                    Q. Quit;
                    -----------------------------------
                    Chose an option :""");
            userInput = scanner.nextLine().toUpperCase();

            switch (userInput){
                case "1" -> idAccount = createAccount(idAccount, accounts, scanner);
                case "2" -> searchIdAccount(accounts, scanner,"deposit");
                case "3" -> searchIdAccount(accounts, scanner,"withdraw");
                case "4" -> searchIdAccount(accounts, scanner,"details");
                case "Q" -> System.out.println("System finalization completed.");
                default -> System.out.println("Not a valid option!");
            }
        }

        scanner.close();
    }


    static int createAccount(int idAccount,
                             HashMap<Integer, AccountDetails> accounts,
                             Scanner scanner){

        try{
            System.out.print("Enter account name: ");
            String name = scanner.nextLine();

            System.out.print("Enter account balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            AccountDetails accountDetails = new AccountDetails(name,balance);

            accounts.put(idAccount, accountDetails);

            System.out.print("id - "+ idAccount+
                    "\nAccount added successfully.");

            idAccount++;

        }
        catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println("Not a valid type.");
        }

        return idAccount;

    }
    static void searchIdAccount(HashMap<Integer, AccountDetails> accounts,
                                Scanner scanner, String type){
        try{
            System.out.print("Enter account id: ");
            int idAccount = scanner.nextInt();
            scanner.nextLine();

            AccountDetails accountDetails = accounts.get(idAccount);

            if(accountDetails == null){
                System.out.println("Account id not found.");
            }
            else{
                String name = accountDetails.getName();
                double balance = accountDetails.getBalance();


                if(type.equals("deposit")) {
                    depositMoney(accountDetails,balance,scanner);
                }
                else if(type.equals("withdraw")){
                    withdrawMoney(accountDetails,balance,scanner);
                }
                else{
                    accountDetails(idAccount,name, balance);
                }

            }

        }
        catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println("Not a valid type.");
        }

    }

    static void depositMoney(AccountDetails accountDetails,
                             double balance,
                             Scanner scanner){
        try{

            System.out.println("Actual balance: "+ balance);
            System.out.print("How much do you wanna deposit: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            balance += amount;

            accountDetails.setBalance(balance);

            System.out.println("Deposit of $"+amount+" made successfully.");

        }
        catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println("Not a valid type.");
        }


    }
    static void withdrawMoney(AccountDetails accountDetails,
                              double balance,
                              Scanner scanner){
        try{

            System.out.println("Actual balance: "+ balance);
            System.out.print("How much do you wanna withdraw: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            if(amount<=balance){
                balance -= amount;

                accountDetails.setBalance(balance);

                System.out.println("Withdraw of $"+amount+" made successfully.");
            }
            else{
                System.out.println("You can't withdraw more than the balance.");
            }


        }
        catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println("Not a valid type.");
        }
    }
    static void accountDetails(int idAccount, String name, double balance){
        System.out.print("idAccount: "+idAccount+
                "\nname: "+name+
                "\nbalance: "+balance);

    }
}
