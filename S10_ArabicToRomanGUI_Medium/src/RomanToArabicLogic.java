import java.util.EnumSet;
import java.util.ArrayList;

/***
 * RomanToArabicLogic contains everything necessary for converting roman numerals to their subsequent
 * arabic counterparts. The class contains an enum containing all possible roman numerals and their subsequent
 * symbol and value.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class RomanToArabicLogic  {

    /***
     * The romanNumerals enum is used to define each Roman numeral and give each their own value
     */
    public enum romanNumerals {
        I("I", 1),
        V("V", 5),
        X("X", 10),
        L("L", 50),
        C("C",100),
        D("D", 500),
        M("M", 1000);

        private final String numeralName;
        private final int numeralValue;

        romanNumerals(String numeralName, int numeralValue){
            this.numeralName = numeralName;
            this.numeralValue = numeralValue;
        }

        public String getNumeralName(){
            return this.numeralName;
        }
        public int getNumeralValue(){
            return this.numeralValue;
        }

    }

    /***
     * The method convertRomantoArabic takes in a string of Roman numerals, using the enum romanNumerals converts them
     * to their corresponding value. The method then tests to make sure that no Roman numeral of a smaller value is
     * before a numeral of a larger value. If this is detected it subtracts the larger number from the smaller number
     * instead of adding them. Finally the value of the roman numeral is returned as an integer.
     * @param romanNums         String containing Roman numerals to be converted
     * @return                  returns value of Roman numerals as a value.
     */
    public static int convertRomantoArabic (String romanNums){
        int arabicConversion=0;
        int valueToAdd =12341234;

        ArrayList<String> individualRomanNums = new ArrayList<>();

        //converts string of roman numerals to each value
        for (int i=0; i<romanNums.length(); i++) {
            individualRomanNums.add(String.valueOf(romanNums.charAt(i)));
        }


        for (int i=0; i<individualRomanNums.size(); i++){
            String temp = individualRomanNums.get(i);
            int tempValue = romanNumerals.valueOf(temp).getNumeralValue();

            //Tests to determine if a roman numeral value should be subtracted from the next value rather than added. (See: IV =5-4, or IX =10-1)
            if(i+1 < individualRomanNums.size()) {
                if (romanNumerals.valueOf(temp).getNumeralValue() < romanNumerals.valueOf(individualRomanNums.get(i + 1)).getNumeralValue())
                {
                    valueToAdd = romanNumerals.valueOf(individualRomanNums.get(i + 1)).getNumeralValue() - tempValue;
                    i++;
                }
                else
                {
                    valueToAdd = tempValue;
                }

            }
            else
            {
                valueToAdd = tempValue;
            }

            arabicConversion += valueToAdd;
        }

        return arabicConversion;
    }


}
