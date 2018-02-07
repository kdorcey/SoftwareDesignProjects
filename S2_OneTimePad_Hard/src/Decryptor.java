import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/***
 * The Decryptor class is used to print the decrypted version of an encrypted file to the console.
 * It does this by converting the file-to-decrypt to a string, then, through an extension of the Alphabet class,
 * converting the String to it's letter's integer values called the letter's "index". The class reads in the "key"
 * from the file-to-decrypt and, in a similar fashion to the Encryptor class, reads in individual values from the encryption
 * key and subtracts their value from the letter index before finally converting the index back to a string.
 *
 * @author Kyle Dorcey
 */
public class Decryptor extends Alphabet {
    /**String version of the encrypted phrase from the file-to-decrypt **/
    private String encryptedPhrase;
    /**User specified filepath to the file-to-decrypt **/
    private String filePath;
    /**ArrayList of integers containing all values from the Encryption Key **/
    private ArrayList<Integer> codex;
    /**ArrayList containing integer representation of every letter from the file-to-decrypt **/
    private ArrayList<Integer> encryptedPhraseIndex;
    /**ArrayList containing integer representation of every letter after the text is shifted back to it's original index values **/
    private ArrayList<Integer> decypheredPhraseIndex;
    /**Integer value read in from file-to-decrypt that determines starting position in the encryption key when shifting letter indexs back to their original value**/
    private int codexKey;

    /***
     * Constructor
     * @param codexObject   KeyGenerator object refrence which contains the encryption key
     * @param filePath      String containing filepath to file-to-decrypt
     */
    public Decryptor(KeyGenerator codexObject, String filePath){
        try {
            this.encryptedPhrase = getEncryptedPhraseFromTxt(filePath);
        }
        catch(java.io.IOException e){
            System.out.println("oops " +e);
        }
        this.codex = codexObject.getCodex();
        this.codexKey = cleanPhraseAndGetKey();
        this.filePath = filePath;
        this.encryptedPhraseIndex = convertLetterToIndex(encryptedPhrase);
    }

    /***
     * Reads in phrase-to-decrypt from the text file as well as the codexKey from the top of the file.
     * @param filePath      User specified file path to file-to-decrypt
     * @return              Returns the phrase from the file-to-decrypt in the form of a string
     * @throws IOException  throws IOException if method cannot reach file-to-decrypt
     */
    public static String getEncryptedPhraseFromTxt (String filePath) throws IOException{
        String phrase = "";

        try {
            File filePaths = new File(filePath);
            Scanner reader = new Scanner(filePaths);


            while (reader.hasNextLine()) {
                phrase += reader.nextLine();
            }



        }
        catch(java.io.IOException exception) {
            System.out.println("oops " +exception);
        }
        return phrase;
    }

    /***
     * Isolates the encryption key and encrypted phrase from each other
     * @return  returns the integer value of the codexKey
     */
    public int cleanPhraseAndGetKey (){
        int key;

        key = Integer.parseInt(encryptedPhrase.replaceAll("[\\D]",""));
        this.encryptedPhrase = encryptedPhrase.replaceAll("\\d", "");

        return key;
    }

    /***
     * Converts the originalPhrase to it's corresponding letter index. After this the method reads in
     * integer values from the "codex" (encryption-codexKey) starting at the "codexKey"'s value. Each letter index is subtracted
     * from the encryption-key's value and then shifted within the range 0-26 using Alphabet's indexValidity checker.
     * The resulting letter index is saved to the instance variable shiftedIndex.
     *
     * @return      returns an ArrayList of Integers containing the shifted index
     */
    public ArrayList<Integer> shiftIndexForDecryption() {
        ArrayList<Integer> decypheredPhraseIndex = new ArrayList<>();
        int indexToChange = 124312;
        int codexStartPoint = this.codexKey;


        for (int i = 0; i < this.encryptedPhraseIndex.size(); i++) {
            indexToChange = this.encryptedPhraseIndex.get(i);

            if (indexToChange != Alphabet.getSpaceHolder()) {
                int numToSubtract = indexToChange - this.codex.get(codexStartPoint);
                numToSubtract = Alphabet.subtractionIndexValidityChecker(numToSubtract);
                decypheredPhraseIndex.add(numToSubtract);
                codexStartPoint++;
            }
            else {
                decypheredPhraseIndex.add(Alphabet.getSpaceHolder());
            }


        }
      return this.decypheredPhraseIndex = decypheredPhraseIndex;
    }

    /***
     * Calls the convertIndexToText from the Alphabet class and returns the resulting string
     * @return      String of decrypted letters which will later be printed to the console
     */
    public String printDecryptedPhrase (){
        String decryptedPhrase ="ERROR";

        decryptedPhrase = convertIndexToText(this.decypheredPhraseIndex);

        return decryptedPhrase;
    }


}
