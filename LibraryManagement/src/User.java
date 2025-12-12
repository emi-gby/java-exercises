import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User {

    private String name;
    private int userId;   // is this necessary//

    User(String name, int userId){
        this.name = name;
        this.userId = userId;
    }

    public String getName(){
        return this.name;
    }

    public int getUserId(){
        return this.userId;
    }

    public void listBook(HashMap<Integer, Book> books){

        System.out.println("------Books List------");
        for(Book book : books.values()){
            System.out.printf("""
                    \nBook id: %d
                    Title: %s
                    Author: %s
                    Availability: %b
                    \n""", book.getBookId(), book.getTitle(), book.getAuthor(), book.getAvailability());

            System.out.println("----------------------");
        }

    }

    public void searchBook(String user,Scanner scanner, HashMap<Integer, Book> books){

        try {
            System.out.print("Enter book id: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();

            Book book = books.get(bookId);

            if (book != null) {
                System.out.println("----------------------");
                System.out.printf("""
                        \nBook id: %d
                        Title: %s
                        Author: %s
                        Availability: %b
                        """, book.getBookId(), book.getTitle(), book.getAuthor(), book.getAvailability());

                if(user.equals("admin")){
                    System.out.println("Borrow By: " + book.getBorrowBy());
                }

            }
            else{
                System.out.println("Book Not Found.");
            }
        }

        catch(InputMismatchException e){
                scanner.nextLine();
                System.out.println("\nEnter only integer numbers please.");

        }
    }
}


