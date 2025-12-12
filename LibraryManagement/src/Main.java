import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        HashMap<Integer, Book> books = new HashMap<>();

        Library library = new Library("MyLibrary",2019, books);

        Admin admin = new Admin("admin",1,"admin123");

        HashMap<Integer, Client> clientsList = new HashMap<>();

        Client client;

        int clientId = 2;

        int bookId = 0;

        String userInput = "";

        while(!userInput.equals("q")){

            System.out.print("""
                    -------Library Management-------
                    1. Enter as admin;
                    2. Enter as client;
                    Q. Quit;
                    -------------------------
                    Chose an option :""");
            userInput = scanner.nextLine().toLowerCase();

            switch (userInput){
                case "1" -> {
                    System.out.print("Enter admin password: ");
                    String password = scanner.nextLine();
                    if(password.equals(admin.getPassword())){
                        bookId = loginAdmin(scanner, admin, books, bookId);
                    }
                    else{
                        System.out.println("Incorrect password!");
                    }
                }



                case "2" -> {
                    System.out.print("""
                            -------------------
                            \n1. Login;
                            2. Create account;
                            -------------------
                            Chose an option: """);
                    String clientInput = scanner.nextLine();
                    switch (clientInput){
                        case "1" -> loginClient(scanner,books,clientsList);
                        case "2" -> {
                            createClient(scanner, clientId, clientsList);
                            clientId++;
                        }
                        default -> System.out.println("Not a valid option!");
                    }

                }
                case "q" -> System.out.println("System finalization completed.");
                default -> System.out.println("Not a valid option!");
            }

        }


        scanner.close();
    }
    static void loginClient(Scanner scanner, HashMap<Integer, Book> books,
                            HashMap<Integer, Client> clientsList){
        try{
            System.out.print("Enter your client id: ");
            int clientId = scanner.nextInt();
            scanner.nextLine();

            Client client = clientsList.get(clientId);

            if(client != null){
                System.out.println("Username: "+ client.getName());
                System.out.println("Borrowed Books: "+ client.getBooksBorrowed());
                clientServices(scanner, client, books);
            }
            else{
                System.out.println("\nClient Not Found.");
            }

        }
        catch(InputMismatchException e){
            scanner.nextLine();
            System.out.println("\nEnter only integer numbers please.");
        }

    }
    static void createClient(Scanner scanner, int clientId,
                             HashMap<Integer, Client> clientsList){

        System.out.print("Enter a username: ");
        String name = scanner.nextLine();

        if(!name.isEmpty()){
            Client newClient = new Client(name, clientId);
            clientsList.put(clientId, newClient);
            System.out.println("Client "+ name+"(id-"+clientId+") added successfully");
        }
        else{
            System.out.println("Name can't be empty.");
        }


    }
    static int loginAdmin(Scanner scanner, Admin admin,
                           HashMap<Integer, Book> books,
                           int bookId){

        String adminInput = "";

        while(!adminInput.equals("r")){
            System.out.print("""
                    -------------------------
                    1. Add book;
                    2. Remove book;
                    3. List books;
                    4. Search book;
                    R. Return;
                    -------------------------
                    Chose an option :""");
            adminInput = scanner.nextLine().toLowerCase();
            switch (adminInput){
                case "1" -> {
                    bookId++;
                    admin.addBook(scanner, books, bookId);
                }
                case "2" -> admin.removeBook(scanner, books);
                case "3" -> admin.listBook( books);
                case "4" -> admin.searchBook("admin",scanner, books);
                case "r" -> System.out.println("Returning...");
                default -> System.out.println("Not a valid option!");
            }
        }
        return bookId;

    }
    static void clientServices(Scanner scanner, Client client,
                               HashMap<Integer, Book> books){

        String clientInput = "";

        while(!clientInput.equals("r")){
            System.out.print("""
                    -------------------------
                    1. Borrow book;
                    2. Return book;
                    3. List books;
                    4. Search book;
                    R. Return;
                    -------------------------
                    Chose an option :""");
            clientInput = scanner.nextLine().toLowerCase();
            switch (clientInput){
                case "1" -> client.borrowBook(scanner,books);
                case "2" -> client.returnBook(scanner,books);
                case "3" -> client.listBook(books);
                case "4" -> client.searchBook("client",scanner,books);
                case "r" -> System.out.println("Returning...");
                default -> System.out.println("Not a valid option!");
            }
        }
    }
}
