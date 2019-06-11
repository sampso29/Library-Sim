import java.util.ArrayList;

public class Member {

    private String Username;

    Double FeeDue = 0.00;
    ArrayList<Book> BookList = new ArrayList<>();


    // this section deals with the User login
    public Member(String username) {
        if (username != "none") {
            Username = username;
        } else {
            System.out.println("Please pick another Username");
        }

    }

    public String getUser() { return Username;}

    // this section deals with Late Penalties
    public void PayFee() {
        FeeDue = 0.00;
    }

    public Double getFeeDue() {
        return FeeDue;
    }

    public void ChargeFee(Collection library) { FeeDue += library.getLatePenalty();}

    public void addBook(Book book) {BookList.add(book);}

    public void returnBook(String title, Collection collection) {
        int index = -1;
        for (int i = 0; i < BookList.size(); i++){
            if (BookList.get(i).getTitle() == title) {index = i;}
        }
        if (index != -1){
            Book book = BookList.get(index);
            book.getCheckout().ReturnBook(collection);
            BookList.remove(book);
        }
    }

    public void BooksInPossession(){
        if (BookList.size() == 0) {
            System.out.println("You currently have no books.");
        }else
            for (int i=0; i < BookList.size(); i++){
                System.out.println(BookList.get(i).getTitle());
            }
    }




}
