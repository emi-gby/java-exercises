public class Book {

    private int bookId;
    private String title;
    private String author;
    private boolean availability;
    private String borrowBy;

    Book(int bookId, String title, String author){
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        availability = true;
        borrowBy = null;
    }

    public int getBookId(){
        return this.bookId;
    }
    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return this.author;
    }
    public boolean getAvailability(){
        return this.availability;
    }
    public void setAvailability(boolean availability){
        this.availability = availability;
    }
    public String getBorrowBy(){
        return this.borrowBy;
    }
    public void setBorrowBy(int userId, String name){
        this.borrowBy = String.format("(id-%d)-> Username: %s", userId, name);
    }
    public void setBorrowReturn(){
        this.borrowBy = null;
    }
}
