import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/***
 * The Encryptor class is the class entirely responsible for encrypting the text within a text document.
 * It handles this by first converting the text documents contents to a String, then by extending the Alphabet class,
 * converts every letter within that string to it's corresponding integer value (0-26, representing A and Z respectively).
 * These values (referred to as the letter's "index") are then added to values from the encryption-codexKey. After they're
 * reconverted to the new index's corresponding letter.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Encryptor extends Alphabet {
    /**User selected value that dictates the encryption codexKey start position (starting from 0) **/
    private int codexKey;
    /**Reference to the codexObject object from the KeyGenerator class containing the encryption codexKey **/
    private KeyGenerator codexObject;
    /**String containing the unencrypted text from the file to be encrypted **/
    private String originalPhrase;
    /**User specified filepath for the file to encrypt **/
    private String filePath;
    /**Reference to the originalPhraseObject in the Alphabet class **/
    private Alphabet originalPhraseObject;
    /**ArrayList of Integers containing encryption values from the encryption-codexKey file **/
    private ArrayList<Integer> codex;
    /**ArrayList of Integers containing the originalPhrase's letter index after being added to the encryption-codexKey's values **/
    private ArrayList<Integer> shiftedIndex;

    /***
     * Constructor based on the user selected "codexKey", file path, and KeyGenerator object.
     * @param codexKey           User selected value starting at 0.
     * @param filePath      User specified filepath of the text file to encode.
     * @param codexObject   KeyGenerator object containing the encryption codexKey.
     */
    public Encryptor(int codexKey, String filePath, KeyGenerator codexObject){
        this.codexKey = codexKey;
        this.filePath = filePath;

        try {
            this.originalPhrase = getOriginalPhraseFromDoc();
        }
        catch (java.io.IOException noFile){
            System.out.println("File does not exist "+noFile);
        }

        this.originalPhraseObject = new Alphabet(originalPhrase);
        this.codex = codexObject.getCodex();
        this.codexObject =codexObject;
        this.shiftedIndex = new ArrayList<>();
        this.codexObject = codexObject;
    }

    /***
     * Reads in the phrase to encrypt from a text file at a user specified location (filePath)
     * @return  Returns the String version of the text read in from the file-to-be-encrypted.
     * @throws IOException  Throw IOException when the method cannot find the user specified filepath
     */
    public String getOriginalPhraseFromDoc() throws IOException{
        String originalPhrase="";
        File textDoc = new File(this.filePath);
        Scanner reader = new Scanner(textDoc);

        while (reader.hasNextLine())
        {
            originalPhrase +=  reader.nextLine();
        }


        return originalPhrase.toUpperCase();
    }

    /***
     * Converts the originalPhrase to it's corresponding letter index. After this the method reads in
     * integer values from the "codex" (encryption-codexKey) starting at the "codexKey"'s value. Each letter index is added
     * to the encryption-codexKey's value and then shifted within the range 0-26 using Alphabet's indexValidity checker.
     * The resulting letter index is saved to the instance variable shiftedIndex.
     */
    public void shiftIndex() {
        ArrayList<Integer> cypheredTextLetterIndex = new ArrayList<>();
        int codexKey = this.codexKey;

        for (int i = 0; i < this.originalPhraseObject.getOriginalTextLetterIndex().size(); i++) {

            int indexToChange = this.originalPhraseObject.getOriginalTextLetterIndex().get(i);

            if (indexToChange != Alphabet.getSpaceHolder()) {
                int newIndex = indexToChange + this.codex.get(codexKey);
                cypheredTextLetterIndex.add(Alphabet.indexValidityChecker(newIndex));

                //loops codex values back to start if it reaches the end of the codex
                if(codexKey == codex.size()){
                    codexKey=0;
                }
                else {
                    codexKey++;
                }
            }
            else {
                cypheredTextLetterIndex.add(Alphabet.getSpaceHolder());
            }

        }
        this.shiftedIndex = cypheredTextLetterIndex;
    }

    /***
     * Converts the integers in the shiftedIndex ArrayList to a string. Then the method prints this string to overwrites
     * the original file with both the codexKey and encrypted text.
     * @return                  returns String containing the now encrypted phrase
     * @throws IOException      throws IOException if it cannot find the user specified file
     */
    public String printEncryptedPhrase () throws IOException{

        String changedWord = originalPhraseObject.convertIndexToText(this.shiftedIndex);

        PrintWriter writer = new PrintWriter(filePath, "US-ASCII");
        writer.println(codexKey);
        writer.println(changedWord);
        writer.close();

        return changedWord;
    }

}
