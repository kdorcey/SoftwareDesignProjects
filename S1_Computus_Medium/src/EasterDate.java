import java.util.ArrayList;

/***
 * The EasterDate class is the base class for creating EasterDate objects. Each EasterDate object
 * contains the month and day of a year's Easter. The object can also contain the year depending on which constructor is called.
 * @author Kyle Dorcey
 * @since 1.8
 */
public class EasterDate  {

    /** Month that Easter occurs given a year **/
    private int month;
    /** Day that Easter occurs given a year **/
    private int day;
    /** Year that Easter occurs given a year **/
    private int year;
    /** String array containing the names of every month**/
    final private static String [] monthNames ={"January", "February" , "March", "April", "May", "June", "July", "August", "September", "October", "November","December"};

    /***
     * Constructs the EasterDate object given the month, day, and year. These values are calculated from the
     * EasterCalculator's easterFinder method.
     * @param month     The month that Easter occurs given a year.
     * @param day       The day that Easter occurs given a year.
     * @param year      The year that Easter occurs.
     * @see EasterCalculator
     */
    public EasterDate(int month, int day, int year){
        if(month>=1 || month<=12 ) {
            this.month = month;
        }
        if (day>=1 || day <=31) {
            this.day = day;
        }
        this.year=year;
    }

    /***
     * Overload constructor for when an Easter's year is not required. Parameters are computed from EasterCalculator's
     * easterFiner method.
     * @param month     Month that Easter occurs given a year.
     * @param day       Day that Easter occurs given a year.
     * @see EasterCalculator
     */
    public EasterDate(int month, int day){
        this.month=month;
        this.day=day;
    }

    /***
     * @return      Returns year of a specific EasterDate object.
     */
    public int getYear(){return this.year; }

    /***
     * @return      Returns the month of a specific EasterDate object.
     */
    public int getMonth(){
        return this.month;
    }

    /***
     * @return      Returns the day of a specific EasterDate object.
     */
    public int getDay (){
        return this.day;
    }

    /***
     * Returns the name of a month based on the months integer value.
     * @param monthNum      Integer representing a month (1 represents January, 2 February, etc.).
     * @return              Returns a String containing a months name
     */
    public static String getMonthFromNum (int monthNum){ return monthNames[monthNum]; }

    /***
     * Prints an EasterDate object's  month, day, and year in the format "[month] [day], [year]".
     */
    public void printDateWithYear(){
        System.out.println(monthNames[this.month-1] + " " +this.day+", "+this.year);
    }

}