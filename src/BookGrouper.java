import java.util.ArrayList;
import java.util.Scanner;

public class BookGrouper {
    // holds books of the same Title
    ArrayList<Book> ListOfBooks = new ArrayList<Book>();
    String Title;
    ArrayList<Member> MemberList = new ArrayList<>(); // this is a waitlist for the book


    public BookGrouper(Book book) {
        Title = book.getTitle();
        ListOfBooks.add(book);
    }

    public String getTitle() {return Title;}

    public void updateTitle(String title){
        System.out.println("What would you like to change the Title to?");
        Scanner keyboard = new Scanner(System.in);
        String newTitle = keyboard.nextLine();
        for (int i=0;i<ListOfBooks.size();i++){
            ListOfBooks.get(i).updateTitle(newTitle);
        }
        Title = newTitle;
        System.out.println("Title has been changed");
    }

    public void updateDescription(){
        System.out.println("What would you like to change the Description to?");
        Scanner keyboard = new Scanner(System.in);
        String newDes = keyboard.nextLine();
        for (int i=0;i<ListOfBooks.size();i++){
            ListOfBooks.get(i).updateDescript(newDes);
        }
        System.out.println("Description has been changed");
    }

    public void updateLocation(String location,int copies){
        int copiesChanged = 0;
        int copiesAvail = 0; // copies available to change
        for (int i=0;i<ListOfBooks.size();i++){
            if (ListOfBooks.get(i).getLocation()== location){copiesAvail +=1;}
        }
        if (copiesAvail > 0){
            System.out.println("What location would you like to place these books?");
            Scanner keyboard = new Scanner(System.in);
            String newLoc = keyboard.nextLine();
            while (copiesChanged != copies){
                for (int i=0;i<ListOfBooks.size();i++) {
                    if (ListOfBooks.get(i).getLocation()== location){
                        ListOfBooks.get(i).updateLocation(newLoc);
                        copiesChanged +=1;
                    }
                }
            }
        }else {
            System.out.println("There are not enough copies in the library");
        }
    }

    public void addCopy(String location){
        if (ListOfBooks.size() > 0){
            String descript = ListOfBooks.get(0).getDescript();
            ListOfBooks.add(new Book(Title,descript, location));
        }
    }
    // untested beyond this point
    public Book getBook(int index) {return  ListOfBooks.get(index);}

    public int getNumCopies() {return ListOfBooks.size();}

    //this section of code is for my waitlist
    public void addMemToWait(Member member) {
        if (MemberList.size() > 0) {
            String usr = member.getUser();
            Boolean UsrInList = false;
            for (int i = 0; i < MemberList.size(); i++) {
                if (MemberList.get(i).getUser() == usr){UsrInList = true;}
            }
            if (UsrInList == true) {
                System.out.println("You are already on the waitlist.");
            }else {
                MemberList.add(member);
                System.out.println("You have been added to the waitlist!");
            }
        }else {
            MemberList.add(member);
            System.out.println("You have been added to the waitlist!");
        }
    }

    public void removeFromWait() {MemberList.remove(0);}

    public ArrayList<Member> getWaitlist() {return MemberList;}

    // processes checkouts
    public void GetBookFromLib(Member member) {
        // if the book is available get it from the library
        // else go on the waitlist
        int index = -1;
        for (int i =0; i < ListOfBooks.size(); i++) {
            if (ListOfBooks.get(i).getCheckout().isAvail()) {index = i;}
        }
        if (index != -1) {
            ListOfBooks.get(index).getCheckout().checkoutBook(member,this,index);
        } else {
            boolean MemOnList = false;
            for (int i = 0; i < MemberList.size(); i++) {
                if (MemberList.get(i) == member) {
                    MemOnList = true;
                }
            }
            if (MemOnList == false) {
                MemberList.add(member);
                System.out.println("You have been added to the waitlist!");
            }

        }
    }







}
