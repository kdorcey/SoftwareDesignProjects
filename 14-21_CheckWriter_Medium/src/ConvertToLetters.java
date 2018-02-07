/***
 * Class responsible for converting text to letters. In short it isolates each digit within the
 * inputted number and prints the digits corresponding text based on a series of if-statements.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class ConvertToLetters {
    /**Final and static String array containing all written values between 0-9**/
    private final static String[] ones ={"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    /**Final static String array containing all written values between 0-19 **/
    private final static String[] teens={"ten", "eleven", "twelve", "thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
    /**Final static String array containing all written values between 10-90. The null is to make all values correspond with their 10's place digit **/
    private final static String[] tens ={null,"ten","twenty", "thirty","fourty","fifty","sixty","seventy","eighty","ninety"};
    /**String containing 100 written as a word**/
    private final static String hundred = "hundred";
    /**Int that will contain only the whole numbers from the users input (ie: when the input is 245.32 "dollars=245") **/
    private int dollars;
    /**Int that will conatin only the decimals from the users input (ie: when the input is 245.32 "cents = 32")**/
    private int cents;
    /**String where the final string value is saved**/
    private String writtenCheck;

    /***
     * Constructor for the ConverToLetters class. Reads in the user input when called
     * then converts the user input double into two separate ints, dollars and cents, with
     * the use of two static methods getWholeNumber(); and getCents();
     * @param numToChange   Users input
     */
    public ConvertToLetters(double numToChange){
        this.dollars = getWholeNumber(numToChange);
        this.cents = getCents(numToChange);
        this.writtenCheck ="";
    }

    /***
     * Used to convert the users input into its written form. It is called from the driver
     * class InputClass. The method will call either getOnes(), getTeens(), getTens(), or getHundred()
     * depending on the size of "dollars"
     * @return String writtenCheck containing the final converted string.
     */
    public String convertToWords(){
        writtenCheck="";

        if(dollars<10){
            writtenCheck += getOnes(dollars);
        }
        else if (dollars>=10 && dollars<20){
            writtenCheck += getTeens(dollars);
        }
        else if(dollars>20 && dollars<100 ){
            writtenCheck +=getTens(dollars);

        }
        else if(dollars>=100){
            writtenCheck+=getHundred(dollars);

            int teenCheck = dollars - ((dollars/100)*100);
            int tempOnes = teenCheck -((teenCheck/10)*10);
            int tempTens = (teenCheck - tempOnes);

            if(teenCheck>10 && teenCheck<20 && teenCheck !=0){
                writtenCheck +=" "+getTeens(teenCheck);
            }
            else if (tempTens !=0 && tempOnes !=0) {
                writtenCheck += " " + getTens(tempTens);
                if(tempOnes !=0) {
                    writtenCheck += "-"+getOnes(tempOnes);
                }
            }

        }

        getDecimalString();
        return writtenCheck;
    }

    /***
     * When cents is not 0 it will add "and [cent value]/100" to the final string
     */
    private void getDecimalString(){
        if(cents !=0){
            writtenCheck += " and "+cents+"/100";
        }
    }

    /***
     * Static method which returns the one value as a string
     * @param onesPlace if dollar amount is less than 10, then input is just the dollar amount. However,
     *                  when dollars is greater than 10, the input is the derived 1's place.
     * @return  String one which contains the written version of the 1's place.
     */
    private static String getOnes(int onesPlace){
        String one="";
        one += ones[onesPlace];
        return one;
    }
    /***
     * Static method which returns any "teen" value (10-19)
     * @param valueToConvert value from the user input which represent 10-19.
     * @return  String teen with "teens" value written as a string.
     */
    private static String getTeens(int valueToConvert){
        String teen="";

        int teensPlaceHolder = valueToConvert/10;

        teensPlaceHolder = teensPlaceHolder*10;

        int teensPlace = valueToConvert - teensPlaceHolder;
        teen += teens[teensPlace];

        return teen;
    }

    /***
     * Static method which takes in the 10's place value and converts it to a string. This method also
     * calls the getOnes() method to add the ones place to the string it returns.
     * @param valueToConvert    10's place value to convert to string
     * @return  String tenString which contains the string version of the inputted int.
     */
    private static String getTens(int valueToConvert){
        String tenString="";

        int tensPlace = (valueToConvert/10);
        int onesPlace = valueToConvert - tensPlace*10;

        if (onesPlace !=0) {
            tenString += tens[tensPlace] + "-" + getOnes(onesPlace);
        }
        else{
            tenString += tens[tensPlace];
        }

        return tenString;
    }

    /***
     * Finds the written 100's place value. It does this by taking the 100's place value
     * and inserting it into the getOnes() method. Then it adds " hundred" to the resulting string.
     * @param valueToConvert    Hundreds-place value to convert to a string
     * @return String hundred which contains the hundreds-place value converted to a string
     */
    private static String getHundred(int valueToConvert){
        String hundred="";
        int temp = valueToConvert/100;
        hundred += getOnes(temp)+" hundred";

        return hundred;
    }

    /***
     * Using java's Math.round() method it removes the decimal place from the users Input
     * @param numToChange   double containing the number to remove the decimal place from
     * @return  returns the inputted double as an int without the decimal place
     */
    public static int getWholeNumber(double numToChange){
        int wholeNumber=12444;
        double temp = Math.round(numToChange);

        if(temp>numToChange){
            wholeNumber = (int)temp-1;
        }
        else if(temp<=numToChange){
            wholeNumber = (int)temp;
        }

        return wholeNumber;
    }

    /***
     * As with the getWholeNumber() method, this method uses java's Math.round() method to extract
     * the decimal place. However this time it returns just the decimal places rather than the whole number
     * @param numToChange   double containing the users desired number to isolate the decimal place from
     * @return              returns an int containing just the decimal place.
     */
    public static int getCents (double numToChange){
        int cents =1231;
        double roundedNumToChange = Math.round(numToChange);

        if (roundedNumToChange>numToChange){
            roundedNumToChange = roundedNumToChange-1;
        }

        double temp =numToChange - roundedNumToChange;


        temp = temp*100;

        cents = (int) Math.round(temp);

        return cents;
    }
}
