import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/***
 * Class that handles and controls the console output and user inputs.
 * The UI is contained within a while loop which breaks after the user choses to exit
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class RunOneTimePadHardUI {
    public static void main(String [] args) throws IOException{
        Scanner reader = new Scanner(System.in);
        KeyGenerator codexObject = new KeyGenerator();


        while (true){
            System.out.println("Would you like to generate a new key? (yes/no)");
            String codexGenCheck = reader.nextLine();

            //if the user wants to create a new codex
            if (codexGenCheck.equalsIgnoreCase("yes")) {
                System.out.println("Please enter length of encryption key to generate: ");
                int codexLength = Integer.parseInt(reader.nextLine());
                System.out.println("Please enter desired complexity: (maximum value to shift letters by)");
                int codexComplexity = Integer.parseInt(reader.nextLine());
                System.out.println("Please enter file location: ");
                String fileLocation = reader.nextLine();

                codexObject = new KeyGenerator(codexLength, codexComplexity, fileLocation);
                codexObject.printToTxtDoc(); //prints a new set of numbers to use as the codex for encryption to a text document
                System.out.println("Generated codex: ");
                codexObject.retrieveCodexDoc(); //retrieves all codex numbers in the form of an ArrayList from the text document
                //codex = codexObject.getCodex(); //returns all codex numbers in the form of an arrayList
                break;
            }

            //if the user does not want to generate a new codex and wants to read from a premade one.
            else if (codexGenCheck.equalsIgnoreCase("no")) {
                System.out.println("Please enter location of key file: ");
                String keyFileLocation = reader.nextLine();
                codexObject = new KeyGenerator(keyFileLocation);
                System.out.println("Generated codex: ");
                codexObject.retrieveCodexDoc();
                //codex = codexObject.getCodex();
                break;
            }
            else {
                System.out.println("Invalid argument");
            }
        }
        codexObject.printCodex();


        //User interface Loop
        while (true){
            System.out.println("Would you like to Encrypt, Decrypt, or exit?");
            String userOption = reader.nextLine();

            //if user wants to encrypt a message
            if (userOption.equalsIgnoreCase("Encrypt")) {
                System.out.println("Directory of the file you wish to encode?");
                String filePathForEncryption = reader.nextLine();
                System.out.println("Value to start encryption at (starts at 0): ");
                int key = Integer.parseInt(reader.nextLine());

                Encryptor phraseToEncrypt = new Encryptor(key, filePathForEncryption,codexObject);

                phraseToEncrypt.shiftIndex();
                String output = phraseToEncrypt.printEncryptedPhrase();
                System.out.println(output);
            }
            //if the user wants to decrypt a text file
            else if (userOption.equalsIgnoreCase("Decrypt")) {
                System.out.println("Location of file to decrypt: ");
                String fileForDecryptionPath = reader.nextLine();
                Decryptor phraseToDecryptObject = new Decryptor(codexObject, fileForDecryptionPath);
                phraseToDecryptObject.shiftIndexForDecryption();
                System.out.println(phraseToDecryptObject.printDecryptedPhrase());
            }
            //if the user just wanted to create a codex
            else if (userOption.equalsIgnoreCase("exit")) {
                System.out.println("Thank you!");
                break;
            }
            //if the user does not pick a valid option
            else{
                System.out.println("Invalid argument");
            }
        }

    }
}
