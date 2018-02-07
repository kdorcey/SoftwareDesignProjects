import java.util.concurrent.*;
import java.util.Scanner;
import java.util.ArrayList;

/***
 * Parent Class of all other filters. It's primary function is to hold the stopping values as well as
 * handle putting and getting inputs from the buffer.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Filter {
    /**Object reference to the buffer from which input is received from**/
    private final Buffer inputBufferLocation;
    /**Object refrerence to the buffer from which the filter outputs (puts) information in**/
    private final Buffer outputBufferLocation;
    /**Arbitrary String which represents when the different filters should stop trying to receive new input from the buffer**/
    private final String stopValue = "?";
    /**Arbitrary String which is used to show when the unchanged DNA sequences begin**/
    private final String unchangedInputPrefix = "~";
    /**Arbitrary String which is used to tell other filters when it is reading a DNA sequence with its first base pairs removed**/
    private final String noFirstInputPrefix = "!";
    /**Arbitrary String which is used to tell other filters when it is reading a DNA sequence with its first two base pairs removed**/
    private final String noFirstandSecondPrefix ="@";
    /**Arbitrary String that is placed at either an edited or unedited DNA sequence to denote when it ends and another begins**/
    private final String partialStop = ";";

    /**
     * Class constructor for Filter. Called by all other filters via the "super()" syntax.
     * @param inputBufferLocation   Object reference to the buffer from which input is received from
     * @param outputBufferLocation  Object reference to the buffer that has items placed into it.
     */
    public Filter(Buffer inputBufferLocation, Buffer outputBufferLocation){
        this.inputBufferLocation = inputBufferLocation;
        this.outputBufferLocation = outputBufferLocation;
    }

    /***
     * Method used to place new DNA sequences into the buffer
     * @param stringToInsert String containing the DNA sequence that will be placed in the buffer
     */
    public void putInBuffer(String stringToInsert){
        try{
            Thread.sleep(100);
            outputBufferLocation.blockingPut(stringToInsert);
        }
        catch(InterruptedException error){
            System.out.println("Error putting in Filter Class");
        }
    }

    /***
     * Method used to pull DNA sequences from the buffer
     * @return  String sequenceToReturn, string pulled from buffer which contains a previously inputted DNA sequence
     */
    public String getFromBuffer(){
        String sequenceToRetun = new String();
        try{
            Thread.sleep(100);
            sequenceToRetun = inputBufferLocation.blockingGet();
        }
        catch (InterruptedException erro2){
            System.out.println("Error getting from Filter Class");
        }
        return sequenceToRetun;
    }

    /***
     * @return String stopValue
     */
    public String getStopValue(){
        return stopValue;
    }

    /***
     * @return String unchangedInputPrefix;
     */
    public String getUnchangedInputPrefix() {return unchangedInputPrefix;}
    /***
     * @return String noFirstInputPrefix;
     */
    public String getNoFirstInputPrefix(){ return noFirstInputPrefix;}
    /***
     * @return String noFirstandSecondPrefix;
     */
    public String getNoFirstandSecondPrefix() { return noFirstandSecondPrefix;}
    /***
     * @return String partialStop;
     */
    public String getPartialStop(){ return partialStop;}
}
