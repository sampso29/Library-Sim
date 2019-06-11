public class Book {
    String Title;
    String Descript;
    String Location;
    CheckOut checkout = new CheckOut();

    public Book(String title, String descript, String location) {
        Title = title;
        Descript = descript;
        Location = location;
        // initialize Checkout later
    }

    //interact with the title
    public String getTitle() {
        return Title;
    }
    public void updateTitle(String title) {Title = title;}

    //interact with the description
    public String getDescript() {
        return Descript;
    }
    public void updateDescript(String descript) {Descript = descript;}

    //interact with location
    public String getLocation() {
        return Location;
    }
    public void updateLocation(String location) {Location = location;}

    public CheckOut getCheckout() {return checkout;}


}
