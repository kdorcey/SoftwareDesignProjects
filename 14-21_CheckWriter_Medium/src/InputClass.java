import java.util.Scanner;

/***
 * Driver class for 14-21_CheckWriter_Medium. Reads in amount to convert to plain text and then call the
 * constructor for the ConvertToLetters class.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class InputClass {
    public static void main(String [] args){
        Scanner reader = new Scanner(System.in);
        System.out.println("Check amount (under $1000): ");
        double numToConvert = Double.parseDouble(reader.nextLine());

        ConvertToLetters convertedWord = new ConvertToLetters(numToConvert);
        System.out.println(convertedWord.convertToWords());
    }
}
