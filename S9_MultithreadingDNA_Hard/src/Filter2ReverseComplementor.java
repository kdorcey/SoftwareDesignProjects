import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;

/***
 * Filter responsible for finding the inputs reverse compliment
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Filter2ReverseComplementor extends Filter implements Runnable {
    /**Array list holding the original string inputted by the user. All threads are executed**/
    private static ArrayList<String> unchanedStringsHolder;
    /**Array list holding the reverse complimented version of the users input**/
    private static ArrayList<String> reverseComplimentHolder;

    /***
     * Class constructor, responsible for calling this class's parent class's constructor as well as
     * defining unchangedStringHolder and reverseComplimentHolder.
     * @param inputBufferLocation   location of the buffer that handles inputs
     * @param outputBufferLocation  location of the buffer that handles outputs
     */
    public Filter2ReverseComplementor(Buffer inputBufferLocation, Buffer outputBufferLocation){
        super(inputBufferLocation, outputBufferLocation);
        this.unchanedStringsHolder = new ArrayList<>();
        this.reverseComplimentHolder = new ArrayList<>();

    }

    /***
     * Contains the primary logic for this filter. It gets Strings from the buffer and loops through each
     *  until it finds the stopping value (previously defined in the Filter class).
     *  For each String it flips all "A"s to "T"s and "G"s to "C"s.
     */
    @Override
    public void run() {
            String toReverse = "";

            while(true) {
                toReverse = getFromBuffer();
                if(toReverse.equals(getStopValue()))
                {
                    break;
                }
                unchanedStringsHolder.add(toReverse);


                String reversed = "";
                for (int i = toReverse.length() - 1; i >= 0; i--) {
                    reversed += toReverse.charAt(i);
                }

                String reverseCompliment = "";


                ///Compliment reversed String////
                for (int i = 0; i < reversed.length(); i++) {
                    char letterToChange = reversed.charAt(i);

                    if (letterToChange == 'T') {
                        reverseCompliment += "A";
                    } else if (letterToChange == 'A') {
                        reverseCompliment += "T";
                    } else if (letterToChange == 'C') {
                        reverseCompliment += "G";
                    } else if (letterToChange == 'G') {
                        reverseCompliment += "C";
                    }
                }
                putInBuffer(reverseCompliment);
                reverseComplimentHolder.add(reverseCompliment);
            }
            putInBuffer(getStopValue());
        }

    /***
     * Called after all threads have been executed. Responsible for printing the users input and
     * reverse compliment to the console
     */
    public static void printDNA(){

            System.out.print("Input: ");
            for(String unchanged: unchanedStringsHolder){
                System.out.print(unchanged+", ");
            }
            System.out.println();
            System.out.print("Reverse Compliment: ");
            for(String reverseComp: reverseComplimentHolder){
                System.out.print(reverseComp+", ");
            }
            System.out.println();
        }
    }






