public class Collection {
    String[] ListOfBooks;
    private int ReturnPeriod;
    private Double LatePenalty;

    // what happens when instances are made
    public Collection(int returnPeriod, double latePenalty) {
        ReturnPeriod = returnPeriod;
        LatePenalty = latePenalty;
    }

    //interact with Penalties
    public Double getLatePenalty() {return LatePenalty;}
    public void setLatePenalty(Double latePenalty) {
        LatePenalty = latePenalty;
    }

    // interact with returns
    public int getReturnPeriod() {return ReturnPeriod;}
    public void setReturnPeriod(int days) {ReturnPeriod = days;}
}
