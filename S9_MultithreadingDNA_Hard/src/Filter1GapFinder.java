import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.*;
import java.util.Scanner;
import java.util.ArrayList;

/***
 * Class used to read in a DNA sequence to edit from the console. After reading it in it adds each sequence to the buffer
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Filter1GapFinder extends Filter implements Runnable {
    /**Scanner object used to read DNA sequences in from the console **/
    private Scanner reader;

    /***
     * Class constructor, responsible for constructing Filter1GapFinder's parent class
     * @param inputBufferLocation   Object reference to the buffer that handles inputs
     * @param outputBufferLocation  Object reference to the buffer that handles outputs
     */
    public Filter1GapFinder(Buffer inputBufferLocation, Buffer outputBufferLocation) {
        super(inputBufferLocation, outputBufferLocation);
        reader = new Scanner(System.in);

    }

    /***
     * Primary logic behind this filter. Loops through every character of the users input. If a character is "A",
     * "T","G", or "C" it will be added to a temporary string. If the character is an  "N" the temporary string
     * is added to the buffer and the temporary string is reset to "".
     */
    @Override
    public void run() {
        System.out.println("Enter genome: ");
        String uneditedGenome = "";
        uneditedGenome = reader.nextLine();
        String temp = "";
        for (int i = 0; i < uneditedGenome.length(); i++) {
            char charHolder = uneditedGenome.charAt(i);

            if (charHolder == 'A' || charHolder == 'T' || charHolder == 'C' || charHolder == 'G') {
                temp = temp + charHolder;
                if (i == uneditedGenome.length() - 1) {
                    putInBuffer(temp);

                }
            }
            if (charHolder == 'N') {
                putInBuffer(temp);
                temp = "";
            }
        }
        putInBuffer(getStopValue());
    }
}



