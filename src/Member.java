public class Member {

    private String Username;

    Double FeeDue = 0.00;
    String[] BookList;

    // this section deals with the User login
    public Member(String username) { Username = username;}

    public String getUser() { return Username;}

    // this section deals with Late Penalties
    public void PayFee() {
        FeeDue = 0.00;
    }

    public Double getFeeDue() {
        return FeeDue;
    }

    public void ChargeFee(Collection library) {
        FeeDue += library.getLatePenalty();
    }




}
