import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends User{

    private String password;

    Admin(String name, int userId, String password){
        super(name,userId);
        this.password = password;
    }


    public String getPassword(){
        return this.password;
    }



    public void addBook(Scanner scanner,
                        HashMap<Integer, Book> books,
                        int bookId){
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        if(!title.isEmpty()){
            System.out.print("Enter book author: ");
            String author = scanner.nextLine();
            if(!author.isEmpty()){

                Book book = new Book(bookId, title, author);
                books.put(bookId, book);

                System.out.println("Book added successfully.");

            }
            else{
                System.out.println("Author can't be empty.");
            }
        }
        else {
            System.out.println("Title can't be empty.");
        }
    }



    public void removeBook(Scanner scanner,
                           HashMap<Integer, Book> books){

        try{
            System.out.print("Enter book id: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();

            Book book = books.get(bookId);

            if(book != null){
                if(book.getAvailability()){
                    books.remove(bookId);
                    System.out.printf("\nBook %s deleted successfully\n", book.getTitle());
                }
                else{
                    System.out.println("Deletion not Available. Book Borrowed.");
                }

            }
            else{
                System.out.println("Book not Found");
            }

        }
        catch(InputMismatchException e){
            scanner.nextLine();
            System.out.println("\nEnter only integer numbers please.");
        }


    }

}
