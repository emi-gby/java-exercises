import java.util.HashMap;

public class Library {

    private String name;
    private int year;
    private HashMap<Integer, Book> books;

    Library(String name, int year, HashMap<Integer, Book> books){
        this.name = name;
        this.year = year;
        this.books = books;
    }

    //add usage
    public String getName(){
        return this.name;
    }
    public int getYear(){
        return this.year;
    }
    public HashMap<Integer ,Book> getBooks(){
        return this.books;
    }
}
