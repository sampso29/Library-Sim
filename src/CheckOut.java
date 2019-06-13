public class CheckOut {
    Boolean avail;
    Member currUser;
    int daysRemaining;
    int numExtensions;
    DayCounter dayCounter;

    public CheckOut() {
        avail = true;
        numExtensions = 0;
    }

    public void setDaysRemaining(Collection collection) {
        daysRemaining = collection.getReturnPeriod();
    }

    public void checkoutBook(Member member, BookGrouper bookGrp, int index){
        //what happens when waitlist is not empty
        if (bookGrp.getWaitlist().size() > 0) {
            //check if Member is on waitlist
            boolean MemOnList = false;
            for (int i =0; i < bookGrp.getWaitlist().size(); i++){
                if (bookGrp.getWaitlist().get(i) == member) {
                    MemOnList = true;
                }
            }
            //if they are first, check to see if book is available
            if (MemOnList == true){
                if (bookGrp.getWaitlist().get(0) == member){
                    if (avail == true) {
                        currUser = member;
                        avail = false;
                        bookGrp.removeFromWait();
                        member.addBook(bookGrp.getBook(index));
                        dayCounter = new DayCounter();
                        System.out.println("You have now checked out this book.");
                    }
                }
            }else {bookGrp.addMemToWait(member);}//if they are not on the waitlist add them
         // if there is no waitlist
        }else {
            currUser = member;
            avail = false;
            member.addBook(bookGrp.getBook(index));
            dayCounter = new DayCounter();
            System.out.println("You have now checked out this book.");
        }
    }

    public void ReturnBook(Collection collection) {
        avail = true;
        currUser = null;
        daysRemaining = collection.getReturnPeriod();
        numExtensions = 0;
        System.out.println("Book has been returned.");
    }

    public void ExtendBook(Collection collection, BookGrouper bookgrp, Member member) {
        if ( avail == false && currUser == member){
            if (bookgrp.getWaitlist().size() == 0) {
                if (numExtensions < 1) {
                    daysRemaining = collection.getReturnPeriod();
                    numExtensions = 1;
                }else{
                    System.out.println("Please return book once Return Period is done. Then you may check it out again.");
                }
            }
        }
    }

    public boolean isLate() {
        boolean late;
        if (daysRemaining < 0) {late = true;}
        else{late = false;}
        return late;
    }

    public boolean isAvail() {return avail;}
    public DayCounter getDays() {return dayCounter;}










}
