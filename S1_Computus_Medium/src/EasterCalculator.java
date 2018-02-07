import java.util.ArrayList;
import java.util.Collections;

/**
 * The EasterCalculator class exists purely for computing parameters to create or edit the EasterDate class object.
 * Because of this all methods within the EasterCalculator class only require one instance and so are static.
 *
 * This class contains two methods. The method easterFinder implements the Meeus/Jones/Butcher algorithm for computing
 * the date of easter given the year. The easterDateIgnoreYear method simply removes the year from the DateCounter object
 * for later computational purposes.
 *
 * @author Kyle Dorcey
 * @version %I%, %G%
 * @since 1.8
 */
public class EasterCalculator {

    /***
     * This method implements the Meeus/Jones/Butcher algorithm in order to compute the date and month of Easter when
     * given the year. After determining the month and date of a year's Easter the method will create a EasterDate class-
     * object for the specified month, day, and year.
     * @param year      Integer representing the year which the algorithm computes.
     * @return          This method returns an EasterDate class-object for the computer date of Easter.
     *                  The class-object's constructor is called earlier in the method.
     */
    public static EasterDate easterFinder(int year)
    {
        //implementation of the Meeus/Jones/Butcher Gregorian algorithm
        int a= year%19;
        int b= year/100;
        int c= year%100;
        int d= b/4;
        int e= b%4;
        int f= (b+8)/25;
        int g= (b-f+1)/3;
        int h= ((19*a)+b-d-g+15)%30;
        int i= c/4;
        int k= c%4;
        int l= (32+(2*e)+(2*i)-h-k)%7;
        int m= (a+(11*h)+(22*l))/451;

        int month= (h+l-(7*m)+114)/31;
        int day= ((h+l-(7*m)+114)%31)+1;

        EasterDate easterFound= new EasterDate(month,day, year);

        return easterFound;
    }

    /***
     * The method easterDateIgnoreYear calls the easterFinder method but returns an object only containing
     * an Easter's day and month. This is used for computational purposes later on.
     * @param year      Integer representation of the year which is passed to easterFinder to compute.
     * @return          returns a EasterDate class-object representing the date of Easter in a given year, but with the
     *                  year removed.
     */
    public static EasterDate easterDateIgnoreYear(int year)
    {
        //Creates new EasterDate objects that do not contain a year
        int month=easterFinder(year).getMonth();
        int day=easterFinder(year).getDay();
        EasterDate modifiedDate = new EasterDate(month,day);

        return modifiedDate;
    }

}
