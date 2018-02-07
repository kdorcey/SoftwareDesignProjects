import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.lang.reflect.Array;
import java.util.ArrayList;

/***
 * The Alphabet class is used to convert letters to their numeric value (referred to as a letter's "index") and viceversa.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Alphabet  {
    /** Static final string containing all letters used in encryption. This is where the index values derive from (0-A, 25-Z) **/
    private static final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    /**Static and final integer giving the size of the letters array **/
    private static final int lettersArrayLength =25;
    /**Static and final arbitrary integer value used as a place holder for spaces when converting letter's to their corresponding numeric index**/
    private static final int spaceHolder =1234;
    /**String that holds the text before it is converted to an index **/
    private String originalText;
    /**ArrayList of Integers used to hold the integer values of every letter from originalText**/
    private ArrayList<Integer> originalTextLetterIndex;

    /***
     * No argument constructor
     */
    public Alphabet(){}

    /***
     * Constructor based on the originalText String which is pulled from a text file in the Encryptor class
     * @param originalText      String containing text to be modified
     */
    public Alphabet(String originalText) {
        this.originalText = originalText;
        this.originalTextLetterIndex = convertLetterToIndex(originalText);
    }

    /***
     * @return returns String array of each letter used for encryption
     */
    public static String[] getLetters() {
        return letters;
    }

    /***
     * @return returns length of the letters array as an integer
     */
    public static int getLettersArrayLength() {
        return lettersArrayLength;
    }

    /***
     * @return returns the arbitrary spaceHolder value used to represent white-spaces in letter indexes
     */
    public static int getSpaceHolder() {
        return spaceHolder;
    }

    /***
     * @return returns the ArrayList of Integers containing the original words letter index
     */
    public ArrayList<Integer> getOriginalTextLetterIndex() {
        return originalTextLetterIndex;
    }


    /***
     * Converts every letter in a string to it's corresponding numeric value (0-A, 25-Z, etc.) and places them
     * in an ArrayList of integers. White-spaces are held with the arbitrary integer spaceHolder.
     * @param textToChange      String containing the text to convert to an index
     * @return                  Returns a ArrayList of Integers where each integer represents a letter
     */
    public static ArrayList<Integer> convertLetterToIndex(String textToChange) {
        ArrayList<Integer> indexNumber = new ArrayList<>();
        for (int i = 0; i < textToChange.length(); i++) {

            char individualLetter = textToChange.charAt(i);

            //inserts the arbitrary value spaceHolder to hold the place of white-spaces
            if (individualLetter == ' ') {
                indexNumber.add(Alphabet.getSpaceHolder());
            }
            else
            {
                for (int w = 0; w < Alphabet.getLettersArrayLength()+1; w++) {
                    if (Alphabet.getLetters()[w].charAt(0) == individualLetter) {
                        indexNumber.add(w);
                    }
                }
            }
        }
        return indexNumber;
    }

    /***
     * Checks to make sure an index number is within the range of (0-25). This method is used after
     * shifting the originalLetterIndex by values from the encryption key. If a number is larger than 25,
     * 25 is subtracted from it to simulate the numbers wrapping from 25 to 0.
     * @param newIndexToCheck       individual numeric value to check
     * @return                      returns either the original numeric value or its corrected counterpart.
     */
    public static int indexValidityChecker (int newIndexToCheck){
        int newIndex=newIndexToCheck;
        boolean indexCheck = true;
        while (indexCheck) {
            if (newIndex <= lettersArrayLength) {
                indexCheck = false;
            } else {
                newIndex = newIndex - (lettersArrayLength + 1);
            }

        }
        return newIndex;
    }

    /***
     * Functions identically to indexValidtyChecker except it is used when decrypting instead of encrypitng. If an
     * index value is below 0 it will add that value to 25 to simulate the values wrapping from 0 to 25.
     * @param numToCheck    numeric value to check for validity
     * @return              returns either the original numeric value or its corrected counterpart.
     */
    public static int subtractionIndexValidityChecker(int numToCheck){
        int newIndex=numToCheck;
        boolean indexCheck = true;
        while (indexCheck) {
            if (newIndex >=0) {
                indexCheck = false;
            } else {
                newIndex = newIndex + (lettersArrayLength + 1);
            }

        }
        return newIndex;
    }

    /***
     * Converts integer values to their alphabetical counterpart. Used for both encryption and decryption
     * @param indexToConvert    ArrayList of integers to convert to text
     * @return                  returns the string of converted integers.
     */
    public String convertIndexToText(ArrayList<Integer> indexToConvert){
        String cypheredString = "";

        for(int temp:indexToConvert){
            if(temp != this.spaceHolder) {
                cypheredString = cypheredString + this.letters[temp];
            }
            else {
                cypheredString = cypheredString + " ";
            }
        }
        return cypheredString;
    }
}
