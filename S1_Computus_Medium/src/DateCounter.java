import java.util.ArrayList;
import java.util.Collections;
/*****
 * The date counter class is responsible for calling the various methods used in counting the amount of recurring dates.
 * As this class exists only to perform computations all of its methods are static
 *
 * @author Kyle Dorcey
 * @version %I%, %G%
 * @since 1.8
 */
public class DateCounter {

    /***
     * In order for the program to count the amount of times that each date reoccurs it must first put them in order. This method will
     * only sorts the dates of the month specified by the parameter monthToSort. Along with aiding in the counting process sortByDay will
     * also be used later to print every date in order. The method will return the dates of the specified month in numerical order in the form
     * of an ArrayList.
     * @param dateContainer     ArrayList holding the date of every easter for the next 5,700,000 years. This list includes repeating dates.
     * @param monthToSort       Integer representation of which month the method should sort the dates of (IE: monthToSort =4 will sort all Easters which occur in April).
     *
     * @return                  The method will return an ArrayList of Integers called sortedDateContainer which contains the
     *                          day of every Easter occurrence in numeric order for the specified month
     */
    public static ArrayList<Integer> sortByDay (ArrayList<EasterDate> dateContainer, int monthToSort){

        //rearranges a months easter dates so that the ArrayList holds them in order
        ArrayList<Integer> sortedDateContainer = new ArrayList<>();
        for(EasterDate temp:dateContainer){
            if(temp.getMonth() == monthToSort){
                sortedDateContainer.add(temp.getDay());
            }
        }
        Collections.sort(sortedDateContainer);
        return sortedDateContainer;
    }

    /***
     * This method counts the amount of times that a certain date (specified by variable referenceDay) occurs. It returns the result as
     * an integer value.
     * @param dateContainer     ArrayList of Integers holding every date of Easter for a specific month.
     * @param referenceDay      Date that the method checks for recurrences of.
     * @return                  Returns an integer representing the amount of times that the specified date occured.
     */
    public static int specificDayCount (ArrayList<Integer> dateContainer, int referenceDay)
    {
        //counts how many times Easter occurs on a specific day
        int count =0;
        for(int temp:dateContainer){
            if(temp == referenceDay){
                count++;
            }
        }
        return count;
    }

    /***
     * Method used to print every date of Easter in a specified month. The method will print each date once followed by
     * an integer representing how many times that specific date occurred. This integer is found through calling the specificDayCount method.
     * @param dates     ArrayList containing all reoccurring dates in a specified month sorted numerically.
     * @param month     Integer representation of the month which the dates are printed for.
     */
    public static void printDatesAndCounts(ArrayList<Integer> dates, int month)
    {
        //prints the dates easter occurs on as well as the number of times that date occurs
        ArrayList<Integer> usedDates = new ArrayList<>();
        for(int i=0; i<dates.size(); i++){
            if(!usedDates.contains(dates.get(i)) ) {
                System.out.println(EasterDate.getMonthFromNum(month-1)+ " "+ dates.get(i) +" : "+ DateCounter.specificDayCount(dates, dates.get(i)));
                usedDates.add(dates.get(i)); //keeps track of which dates have already been counted/printed
            }

        }
    }

}
