package daystolive;

// imports ---------------------------------------------
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.Scanner;
// imports ---------------------------------------------

public class DaysToLive {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter year of birth: ");
        int BirthYear = input.nextInt();
        System.out.println();
        System.out.print("Enter Month of birth (01 for january, 04 for april, etc...): ");
        int BirthMonth = input.nextInt();
        System.out.println();
        System.out.print("Enter day of birth: ");
        int BirthDay = input.nextInt();
        System.out.println();
        System.out.println("Enter Time of birth (in the form 0800 for 8:00am and 1350 for 1:50pm) "
                + " if not known input '0'");
        int BirthTime = input.nextInt();
        System.out.println();

        //setting currrent year as an int and outputting the age
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter FormatYear = DateTimeFormatter.ofPattern("yyyy");
        String OnlyYear = myDateObj.format(FormatYear);
        int CurrentYear = Integer.parseInt(OnlyYear);
        int age = CurrentYear - BirthYear;
        System.out.print("you are: " + age + " year(s), ");

        //setting days to integer and working out how many days you are old 
        DateTimeFormatter FormatDay = DateTimeFormatter.ofPattern("dd");
        String OnlyDay = myDateObj.format(FormatDay);
        int CurrentDay = Integer.parseInt(OnlyDay);

        //setting current month into an integer and outputting how many months you are old 
        DateTimeFormatter FormatMonth = DateTimeFormatter.ofPattern("MM");
        String OnlyMonth = myDateObj.format(FormatMonth);
        int CurrentMonth = Integer.parseInt(OnlyMonth);

        //calculating how many months and days you are old ---
        int ageMonth = 0;
        int ageDay = 0;
        if (BirthDay <= CurrentDay) {
            ageMonth = CurrentMonth - BirthMonth;
            ageDay = CurrentDay - BirthDay;
        } else if (BirthDay > CurrentDay) {
            ageMonth = CurrentMonth - BirthMonth - 1;
            ageDay = CurrentDay + (30 - BirthDay);
        }

        //setting the current time in hours and minutes to workable integers 
        DateTimeFormatter FormatHour = DateTimeFormatter.ofPattern("HH");
        String OnlyHour = myDateObj.format(FormatHour);
        int CurrentHour = Integer.parseInt(OnlyHour);
        DateTimeFormatter FormatMinute = DateTimeFormatter.ofPattern("mm");
        String OnlyMinute = myDateObj.format(FormatMinute);
        int CurrentMinute = Integer.parseInt(OnlyMinute);

        int BirthHour = BirthTime / 100;
        int BirthMinute = BirthTime - (BirthHour * 100);

        //if the user actually knows their time and has inputted a variable 
        if (BirthTime != 0) {
            int ageHour = 0;
            int ageMinute = 0;
            //calculating the amount of time they have lived in hours
            if (CurrentHour > BirthHour) {
                ageHour = CurrentHour - BirthHour;
            } else if (CurrentHour < BirthHour) {
                ageDay--;
                ageHour = 24 - (BirthHour - CurrentHour);
            }

            //calculating the amount of time they have lives in minutes
            if (CurrentMinute < BirthMinute) {
                ageHour--;
                ageMinute = 60 - (BirthMinute - CurrentMinute);
            } else if (CurrentMinute > BirthMinute) {
                ageMinute = CurrentMinute - BirthMinute;
            }

            if (ageHour < 0) {
                ageDay--;
                ageHour = 24 + ageHour;
            }

            System.out.print(ageMonth + " month(s) " + ageDay + " day(s) " + ageHour + " Hour(s) and " + ageMinute + " minute(s) old");
            System.out.println("");

        } else {
            //outputting said calculating concatinated onto the previous String
            System.out.print(ageMonth + " month(s) and " + ageDay + " day(s) ");
            System.out.println("");
        }

        System.out.println();
        System.out.print("what is the life expectancy in your country: ");
        int LifeExpectancy = input.nextInt();
        System.out.println();
        // Average life expectancy for the uk = 80.96 (rounded up for simplicity sake)

        int a = LifeExpectancy * 365;
        a = (((a - ageDay - (ageMonth * 30 + (ageMonth / 2)) - (age * 365)) * 24) * 60) * 60;

        int leftDays = a / 86400;
        int remainder1 = a % 86400;
        int leftHours = remainder1 / 3600;
        int remainder2 = remainder1 % 3600;
        int leftMinute = remainder2 / 60;
        int leftSeconds = remainder2 % 60;

        System.out.print("which means you have about... " + a + "seconds left to live");

        System.out.println();
        System.out.println("which is about " + leftDays + " days " + leftHours + " Hours, " + leftMinute + " minutes and " + leftSeconds + " seconds left.");
    }

}
