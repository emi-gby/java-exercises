import java.util.*;

public class Client extends User{

    private List<String> booksBorrowed = new ArrayList<>();

    Client(String name, int userId){
        super(name, userId);
    }

    public List<String> getBooksBorrowed(){
        return booksBorrowed;
    }


    // join these two methods
    public void borrowBook(Scanner scanner, HashMap<Integer, Book> books){

        try{
            System.out.print("Enter book id: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();

            Book book = books.get(bookId);

            if(book != null){
                if(book.getAvailability()){
                    booksBorrowed.add(book.getTitle());
                    book.setBorrowBy(this.getUserId(), this.getName());
                    book.setAvailability(false);

                    System.out.println(book.getTitle()+" borrowed successfully.");
                }
                else{
                    System.out.println("Book Not Available.");
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
    public void returnBook(Scanner scanner, HashMap<Integer, Book> books){
        try{
            System.out.print("Enter book id: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();

            Book book = books.get(bookId);

            if(book != null){
                booksBorrowed.remove(book.getTitle());
                book.setBorrowReturn();
                book.setAvailability(true);


                System.out.println("Book returned successfully.");
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
