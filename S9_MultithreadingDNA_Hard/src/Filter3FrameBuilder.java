import java.lang.reflect.Array;
import java.util.ArrayList;

public class Filter3FrameBuilder extends Filter implements Runnable {
    /**Array list used to hold the DNA sequence but with the first base removed**/
    private static ArrayList<String> noFirstBaseHolder;
    /**Array list used to hold the DNA sequence but with the first and second base removed**/
    private static ArrayList<String> noSecondBaseHolder;

    /***
     * Class constructor, responsible for calling this class's parent class's constructor as well as
     * defining noFirstBaseHolder and noSecondBaseHolder.
     * @param inputBufferLocation   object reference to the buffer that handles inputs
     * @param outputBufferLocation  object reference to the buffer that handles outputs
     */
    public Filter3FrameBuilder(Buffer inputBufferLocation, Buffer outputBufferLocation){
        super(inputBufferLocation, outputBufferLocation);

        noFirstBaseHolder = new ArrayList<>();
        noSecondBaseHolder = new ArrayList<>();
    }

    /***
     * For each DNA sequence unbroken by the character "N", this method will remove the first and second base.
     * After doing this the method will add the Strings corresponding prefix and stopping value to denote when a
     * DNA sequence with a base-pair removed starts or ends. It is also added to the ArrayLists that will print
     * at the end of the program. Finally it re-inserts the stoppingvalue into the buffer.
     */
    public void run(){
        String stringToChange = new String();
        while(true){
            stringToChange = getFromBuffer();
            if(stringToChange.equals(getStopValue())){
                break;
            }
            String noFirstBase = removeFirstBase(stringToChange);
            String noFirstandSecondBase = removeFirstBase(noFirstBase);

            putInBuffer(getUnchangedInputPrefix()+stringToChange+getPartialStop()); //This is new
            putInBuffer(getNoFirstInputPrefix()+noFirstBase+getPartialStop());
            putInBuffer(getNoFirstandSecondPrefix()+noFirstandSecondBase+getPartialStop()); //This is also new
            noFirstBaseHolder.add(noFirstBase);
            noSecondBaseHolder.add(noFirstandSecondBase);
        }
        putInBuffer(getStopValue());
    }

    /***
     * Removes the first base from a DNA sequence. This method is called again to remove the second base as well
     * @param ogBase DNA sequence to remove the first base from
     * @return the original DNA sequence but without its first base
     */
    public String removeFirstBase(String ogBase) {
        String noFirstBase = "";
        for (int i = 1; i < ogBase.length(); i++) {
                noFirstBase += ogBase.charAt(i);
        }
        return noFirstBase;
    }

    /***
     * Called after all threads are executed, prints all DNA sequences with their first base removed. Then it
     * prints all DNA sequences with the second base removed as well.
     */
    public static void print(){
        System.out.print("No First base: ");
        for(String noFirst: noFirstBaseHolder){
            System.out.print(noFirst+", ");
        }
        System.out.println();
        System.out.print("No second base: ");
        for(String noSecond: noSecondBaseHolder){
            System.out.print(noSecond+", ");
        }
        System.out.println();
    }
}
