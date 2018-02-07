import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;

/***
 * The KeyGenerator class is responsible for generating an encryption key (or "codex" as it is referred to within the program)
 * text file containing a series of randomly generated numbers that meet parameters (such as complexity of randomly
 * generated number or amount of randomly generated numbers) which are specified by the user. All user defined variables
 * are from the RunOneTimePadHardUI class
 *
 * @author Kyle Dorcey
 * @version 1.8
 * @see RunOneTimePadHardUI
 */
public class KeyGenerator {

    /** ArrayList of Integers containing all randomly generated values read in from the encryption key file.**/
    private ArrayList<Integer> codex;
    /** User specified integer which dictates how many randomly generated numbers the key file should contain.**/
    private int codexLength;
    /** User specified integer which sets the largest number the random number generator can create **/
    private int codexComplexity;
    /** User specified String giving the file path to the key file. If a text file does not already exist it will create one. Default file location is within kdorcey_swd folder**/
    private String filePath;

    /**
     * Constructor used when generating a new encryption key. It is used to establish the length
     * of the encryption key, complexity of th key (maximum randomly generated number
     * and file path to the encryption key text file.
     * @param codexLength       User specified integer to determine amount of random numbers to generate in the encyption key.
     * @param codexComplexity   User specified integer used to determine the largest random variable that the class can generate.
     * @param filepath          User specified String used to set the filepath to where the encryption key text file will be generated.
     */
    public KeyGenerator(int codexLength, int codexComplexity, String filepath){
        this.codexLength = codexLength;
        this.codexComplexity = codexComplexity;
        this.codex = new ArrayList<>();
        this.filePath = filepath;
    }

    /***
     * Constructor used when a new encryption key does not need to be generated.
     * @param filePath      User specified String used to find the already generated encryption key.
     */
    public KeyGenerator(String filePath){
        this.filePath = filePath;
        this.codex = new ArrayList<>();
    }

    /***
     * No argument constructor
     */
    public KeyGenerator(){
        this.codex = new ArrayList<>();
    }

    /***
     * @return returns all randomly generated integers from the encryption key file in the form of an ArrayList of Integers.
     */
    public ArrayList<Integer> getCodex(){
        return this.codex;
    }

    /***
     * @return returns String containing the filePath of the encryption key
     */
    public String getFilePath(){return this.filePath;}

    /***
     * Generates and prints random integers (within the users predefined parameters) to the text document
     * at a user specified file path. Only used if a new encryption key text file is generated
     * @throws IOException      throws IOException when the user's filepath is unreachable.
     */
    public void printToTxtDoc() throws IOException {

        Random randomInt = new Random();
        FileWriter writer = new FileWriter(this.filePath);

        for(int i=0; i<this.codexLength;i++){
            int temp =randomInt.nextInt(this.codexComplexity);
            writer.write(temp + "\n");
        }
        writer.close();
    }

    /***
     * Reads in all integers from the encryption key text file and places them in an ArrayList of Integers.
     * Used when generating a new encryption key and when using a preexisting one.
     * @throws FileNotFoundException        Throws FileNotFoundException if encryption key file is not found
     */
    public void retrieveCodexDoc() throws FileNotFoundException{
        File textDoc = new File (this.filePath);
        Scanner reader = new Scanner(textDoc);
        while (reader.hasNextLine())
        {
            int temp =Integer.parseInt(reader.nextLine());
            this.codex.add(temp);
        }

    }

    /***
     * Prints all values from the encryption key text file to the console.
     */
    public void printCodex(){
        for(int numToPrint:this.codex){
            System.out.print(numToPrint+" ");
        }
        System.out.println();
    }


}
