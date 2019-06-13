import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    ArrayList<Member> Membership = new ArrayList<>();
    Member CurrUser;
    DayCounter Today = new DayCounter(); // was inside main method

    public static void main(String args[]){
        System.out.println("Welcome to the library.");
        Menu menu = new Menu();
        Collection library = new Collection(14,0.5);

        menu.UserLogin(library);
    }





    public DayCounter getToday() {
        return Today;
    }

    // create a new student account
    public void MemberSetUp(){
        System.out.println("What username would you like?");
        Scanner keyboard = new Scanner(System.in);
        String username = keyboard.nextLine().trim();
        boolean used = false;
        for (int i=0; i < Membership.size(); i++) {
            if (Membership.get(i).getUser() == username) {
                used = true;
            }
        }
        if (used == false){
            Member NewMem = new Member(username);
            Membership.add(NewMem);
            CurrUser = NewMem;
        }else {
            System.out.println("Username is already in use. Please pick a new username.");
            MemberSetUp();
        }
    }

    // is a student able to login
    public boolean StudentLogin(){
        boolean result = false; // track if you are logged in or not
        System.out.println("Do you have Membership? Format: YES or NO");
        Scanner keyboard = new Scanner(System.in);
        String res0 = keyboard.nextLine();
        if (res0.trim().equalsIgnoreCase("NO")){
            this.MemberSetUp();
            result = true;
        } else {
            boolean UserInList = false;
            int index = -1;
            System.out.println("Please type in your username:");
            String res1 = keyboard.nextLine();
            for (int i=0; i<Membership.size();i++) {
                if (Membership.get(i).getUser() == res1.trim()){
                    UserInList = true;
                    index = i;
                }
            }
            if (UserInList == true){
                result = true;
                CurrUser = Membership.get(index);
            }else{
                System.out.println("You are not a user, please signup for membership.");
                System.out.println("Do you wish to continue? YES or NO");
                String res2 = keyboard.nextLine().trim();
                if (res2.equalsIgnoreCase("YES")){this.StudentLogin();}
            }
        }
        return result;
    }

    // handles all logins --> main startup screen
    public void UserLogin(Collection collection){
        System.out.println("Are you a STAFF or STUDENT?");
        Scanner keyboard = new Scanner(System.in);
        String UserType = keyboard.nextLine().trim();
        if (UserType.equalsIgnoreCase("STAFF")){
            this.StaffMenu(collection);
        }else{
            if (this.StudentLogin()) {
                this.StudentMenu(collection,CurrUser); // student menu? -----------------------------
            }else{
                System.out.println("\n");
                this.UserLogin(collection);
            }
        }
    }

    // all the operations a staff can do
    public void StaffMenu(Collection collection){
        System.out.println("\n");
        System.out.println("What operations would you like to do today?");
        System.out.println("Options: ADD A BOOK, UPDATE A BOOK or PAY PENALTIES FOR STUDENT");
        Scanner UserResponse = new Scanner(System.in);
        String response = UserResponse.nextLine().trim();
        if (response.equalsIgnoreCase("ADD A BOOK")) {
            System.out.println("\n");
            System.out.println("What is the book's title?");
            String Title = UserResponse.nextLine().trim();
            System.out.println("What will be the books location?");
            String Location = UserResponse.nextLine().trim();
            collection.addBook(Title,Location);
            System.out.println("\n");
            StaffMenu(collection);


        }else if (response.equalsIgnoreCase("UPDATE A BOOK")){
            System.out.println("\n");
            System.out.println("What is the Title of the book?");
            String title = UserResponse.nextLine();
            if (title == "EXIT") {this.UserLogin(collection);} //-----------------------------
            else if (collection.SearchBook(title) == null){
                System.out.println("Library doesn't have this book in catalogue.");
                System.out.println("\n");
                this.StaffMenu(collection);
            }else{
                BookGrouper bookgrp = collection.SearchBook(title);
                System.out.println("What do you want to update?");
                System.out.println("Options: TITLE, DESCRIPTION, LOCATION");
                String Field = UserResponse.nextLine().trim();
                if (Field.equalsIgnoreCase("Title")) {
                    bookgrp.updateTitle(title);
                    System.out.println("\n");
                    this.StaffMenu(collection);
                }else if (Field.equalsIgnoreCase("DESCRIPTION")){
                    bookgrp.updateDescription();
                    System.out.println("\n");
                    this.StaffMenu(collection);
                }else if (Field.equalsIgnoreCase("LOCATION")){
                    System.out.println("Which location is the book assigned to?");
                    String newLoc = UserResponse.nextLine();
                    System.out.println("How many copies are you updating?");
                    int copies = UserResponse.nextInt();
                    bookgrp.updateLocation(newLoc,copies);
                    System.out.println("\n");
                    this.StaffMenu(collection);
                }
            }


        }else if (response.equalsIgnoreCase("PAY PENALTIES FOR STUDENT")) {
            System.out.println("What is the student's username?");
            String username = UserResponse.nextLine();
            int index = -1;
            for (int i=0; i<Membership.size();i++){
                if (Membership.get(i).getUser() == username) {index = i;}
            }
            if (index != -1){
                Membership.get(index).PayFee();
                System.out.println("Fees have been paid.");
                System.out.println("\n");
                this.StaffMenu(collection);
            } else{
                System.out.println("User is not a member.");
                System.out.println("\n");
                this.StaffMenu(collection);
            }
        }else if (response.equalsIgnoreCase("EXIT")); {
            System.out.println("\n");
            this.UserLogin(collection); //---------------------------------------------
        }
    }


    public void StudentMenu(Collection collection,Member student){
        student.BookDueMessage(this,collection);
        Scanner keyboard = new Scanner(System.in);
        System.out.println("what would you like to do today?");
        System.out.println("Options: CHECKOUT A BOOK, RETURN A BOOK, EXTEND RETURN DATE");
        String operation = keyboard.nextLine().trim();
        if (operation.equalsIgnoreCase("EXIT")) {
            CurrUser = null;
            System.out.println("\n");
            this.UserLogin(collection);
        }else if (operation.equalsIgnoreCase("CHECKOUT A BOOK")){
            System.out.println("What is the Title of the book you want?");
            String title = keyboard.nextLine();
            BookGrouper Bookgrp = collection.SearchBook(title);
            if (Bookgrp == null) {
                System.out.println("This book does not exist.");
                System.out.println("\n");
                this.StudentMenu(collection,student);
            }else {
                collection.SearchBook(title).GetBookFromLib(student);
                System.out.println("\n");
                this.StudentMenu(collection,student);
            }


        }else if (operation.equalsIgnoreCase("RETURN A BOOK")){
            System.out.println("Here are a list of books that can be returned:");
            CurrUser.BooksInPossession();
            System.out.println("\n");
            System.out.println("Which book do you want to return? If no books in your possession write anything");
            String Title = keyboard.nextLine().trim();
            if (CurrUser.searchBook(Title) != null){
                int day = Today.getDay();
                int month = Today.getMonth();
                int year = Today.getYear();
                int TakeoutPeriod = CurrUser.searchBook(Title).getCheckout().getDays().numDays(day,month,year);
                if (TakeoutPeriod > collection.getReturnPeriod()) {
                    int DaysLate = TakeoutPeriod - collection.getReturnPeriod();
                    for (int i=0;i<DaysLate;i++){student.ChargeFee(collection);}
                }
                CurrUser.returnBook(Title,collection);
                this.StudentMenu(collection,student);
            }else if (Title.equalsIgnoreCase("EXIT")){
                System.out.println("\n");
                CurrUser = null;
                this.UserLogin(collection);
            }
            else {
                System.out.println("Please try again");
                this.StudentMenu(collection,student);
            }

        }else if (operation.equalsIgnoreCase("EXTEND RETURN DATE")){
            System.out.println("Here are a list of books in your possession:");
            CurrUser.BooksInPossession();
            System.out.println("\n");
            System.out.println("Which book do you want to try to extend?");
            String Title = keyboard.nextLine();
            if (CurrUser.searchBook(Title) != null) {
                Book book = CurrUser.searchBook(Title);
                BookGrouper bookgrp0 = collection.SearchBook(Title);
                if (CurrUser.searchBook(Title).getCheckout().ExtendBook(bookgrp0,student)){
                    int day = this.getToday().getDay();
                    int month = this.getToday().getMonth();
                    int year = this.getToday().getYear();
                    book.checkout.getDays().setDate(day,month,year);
                    System.out.println("Book has been extended");
                    System.out.println("\n");
                    this.StudentMenu(collection,student);
                }else {
                    System.out.println("\n");
                    this.StudentMenu(collection,student);
                }
            } else {
                System.out.println("Please try again");
                System.out.println("\n");
                this.StudentMenu(collection,student);
            }
        }

    }













}
