import java.util.Scanner;
import java.util.ArrayList;

/***
 * RunComputus is the driver class for S1_Computus_Medium. This class contains all parts necissary for user interaction
 * such as a Scanner class, and console outputs.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class RunComputus {
    public static void main (String[] args){

        Scanner reader = new Scanner(System.in);
        System.out.println("Please input year: ");
        int inputYear = Integer.parseInt(reader.nextLine());
        EasterCalculator.easterFinder(inputYear).printDateWithYear(); //prints answer to part 1

        System.out.println("Year to start computation for the next 5,700,000 Easters?");
        int futureCalculatorStartDate = Integer.parseInt(reader.nextLine());

        System.out.println("Please wait while dates for next 5,700,000 Easters are calculated...");
        ArrayList<EasterDate> dateContainer = new ArrayList<>(); //holds all dates after year is removed
        for(int i=futureCalculatorStartDate; i<(futureCalculatorStartDate+5700000); i++) //finds the date of every Easter for 5,700,000 years but excludes the year
        {
            dateContainer.add(EasterCalculator.easterDateIgnoreYear(i));
        }

        ArrayList<Integer> marchDates = DateCounter.sortByDay(dateContainer, 3);
        ArrayList<Integer> aprilDates = DateCounter.sortByDay(dateContainer,4);

        DateCounter.printDatesAndCounts(marchDates,3);
        DateCounter.printDatesAndCounts(aprilDates, 4);

    }



}
