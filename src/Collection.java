import java.util.ArrayList;
import java.util.Scanner;

public class Collection {
    ArrayList<BookGrouper> ListOfBookGrper = new ArrayList<BookGrouper>();
    int ReturnPeriod;
    Double LatePenalty;



    // what happens when instances are made
    public Collection(int returnPeriod, double latePenalty) {
        ReturnPeriod = returnPeriod;
        LatePenalty = latePenalty;
    }

    //interact with Penalty variable
    public Double getLatePenalty() {return LatePenalty;}
    public void setLatePenalty(Double latePenalty) {
        LatePenalty = latePenalty;
    }

    // interact with returns variable
    public int getReturnPeriod() {return ReturnPeriod;}
    public void setReturnPeriod(int days) {ReturnPeriod = days;}

    // to create a new book, if title not in collection
    // add a copy if book is in the collection
    public void addBook(String title, String location) {
        Boolean BookInList = false;
        for (int i = 0; i < ListOfBookGrper.size(); i++) {
            if (ListOfBookGrper.get(i).getTitle() == title) {
                BookInList = true;
            }
        }
        if (BookInList == false) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please give this book a short description:");
            String description = keyboard.nextLine();
            ListOfBookGrper.add(new BookGrouper(new Book(title, description, location)));
        } else {
            for (int i = 0; i < ListOfBookGrper.size(); i++) {
                if (ListOfBookGrper.get(i).getTitle() == title) {
                    ListOfBookGrper.get(i).addCopy(location);
                }
            }
        }
    }


    public BookGrouper SearchBook(String title) {
        BookGrouper book = null;
        for (int i =0; i < ListOfBookGrper.size(); i++) {
            if (ListOfBookGrper.get(i).getTitle() == title) {
                book = ListOfBookGrper.get(i);
            }
        }
        return book;
    }



    //-----------------------------------used for testing
    public void SeeBooks() {
        for (int i = 0; i < ListOfBookGrper.size(); i++) {
            System.out.println(ListOfBookGrper.get(i).getTitle());
        }
    }


}
