import java.util.Calendar;

public class Test {

    public static void main(String args[]) {

        Collection UTorLib = new Collection(14, 10.00);
        System.out.println(UTorLib.getLatePenalty());
        System.out.println(UTorLib.getReturnPeriod());
        UTorLib.addBook("Life of Pi", "utsc");
        UTorLib.addBook("Life of Pi", "utm");
        UTorLib.SeeBooks();
        //UTorLib.SearchBook("Life of Pi").getBook().getCheckout().setDaysRemaining(UTorLib);



        Member student = new Member("sampso29");
        System.out.println(student.getUser());
        // charge the user a late penalty
        student.ChargeFee(UTorLib);
        System.out.println(student.getFeeDue());
        // student pays fee
        student.PayFee();
        System.out.println(student.getFeeDue());


        Member student0 = new Member("jath232");
        Member student1 = new Member("steve145");
        Member student2 = new Member("ian1");
        System.out.println("sampso29");
        UTorLib.SearchBook("Life of Pi").GetBookFromLib(student);
        student.BooksInPossession();
        System.out.println("jath232");
        UTorLib.SearchBook("Life of Pi").GetBookFromLib(student0);
        student0.BooksInPossession();
        System.out.println("steve145");
        UTorLib.SearchBook("Life of Pi").GetBookFromLib(student1);
        student1.BooksInPossession();
        System.out.println("ian1");
        UTorLib.SearchBook("Life of Pi").GetBookFromLib(student2);
        student2.BooksInPossession();



        System.out.println("sampso29");
        student.returnBook("Life of Pi",UTorLib);
        System.out.println("ian1");
        UTorLib.SearchBook("Life of Pi").GetBookFromLib(student2);
        student2.BooksInPossession();
        System.out.println("steve145");
        UTorLib.SearchBook("Life of Pi").GetBookFromLib(student1);
        student1.BooksInPossession();

        DayCounter Calendar = new DayCounter();
        System.out.println(Calendar.numDays(31,12,1997));
        System.out.println(Calendar.numDays(8,04,1998));








    }

}
