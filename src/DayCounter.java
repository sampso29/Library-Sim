import java.util.Scanner;

public class DayCounter {
    int[] DaysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31}; //365 days tot assuming no leap year
    int Day;
    int Month;
    int Year;
    int DaysIntoYear; //the num of days into a year, given the date

    public void setDate() {
        int days = 0;
        Scanner UserIn = new Scanner(System.in);
        System.out.println("What is the current year? Format: yyyy");
        Year = UserIn.nextInt();
        System.out.println("What is the current month? Format: mm");
        System.out.println("No number higher than 12 or less than 1!");
        Month = UserIn.nextInt();
        System.out.println("What day of the month is today? Format: dd (for double digit) or d (for single digits)");
        Day = UserIn.nextInt();
        System.out.println("\n");
        for (int i=0; i < Month-1;i++) {
            days += DaysInMonth[i];
        }
        days += Day;
        DaysIntoYear =days;
    }

    public void setDate(int day, int month, int year) {
        Day = day;
        Month = month;
        Year = year;
        int days = 0;
        for (int i=0; i < Month-1;i++) {
            days += DaysInMonth[i];
        }
        days += Day;
        DaysIntoYear =days;
    }

    public DayCounter(){
        this.setDate();
    }

    //The number of days that has passed between day book was taken out (DaysIntoYear) and the the day it was returned (Parameters)
    // --> assumming given dates is after the date set
    public int numDays(int GivenDay, int GivenMonth, int GivenYear) {
        int num;
        if (GivenYear == Year) {
            int days = 0;
            for (int i=0; i < GivenMonth-1;i++) {
                days += DaysInMonth[i];
            }
            days += GivenDay;
            num = days - DaysIntoYear;
        }else {
            int days = 365 - DaysIntoYear;
            int year = Year+1;
            while (year != GivenYear){
                days +=365;
                year +=1;
            }
            for (int i=0; i < GivenMonth-1;i++) {
                days += DaysInMonth[i];
            }
            days += GivenDay;
            num = days;
        }
        return num;
    }

    public int getDay() {return Day;}
    public int getMonth() {return Month;}
    public int getYear() {return Year;}
}
