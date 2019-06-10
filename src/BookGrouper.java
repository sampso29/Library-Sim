import java.util.ArrayList;
import java.util.Scanner;

public class BookGrouper {
    // holds books of the same Title
    ArrayList<Book> ListOfBooks = new ArrayList<Book>();
    String Title;

    public BookGrouper(Book book) {
        Title = book.getTitle();
        ListOfBooks.add(book);
    }

    public String getTitle() {return Title;}

    public void addCopy(String location){
        if (ListOfBooks.size() > 0){
            String descript = ListOfBooks.get(0).getDescript();
            ListOfBooks.add(new Book(Title,descript, location));
        }
    }
    // untested beyond this point
    public Book getBook(int numCopies) {return  ListOfBooks.get(numCopies-1);}

    public int getNumCopies() {return ListOfBooks.size();}




}
