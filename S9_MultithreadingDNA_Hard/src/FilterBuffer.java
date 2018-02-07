import java.util.concurrent.ArrayBlockingQueue;
/***
 * Class responsible for adding functionality to the Buffer interface
 * @author Kyle Dorcey
 * @version 1.8
 */
public class FilterBuffer implements Buffer {
    /**ArrayList that stores all values added to the buffer**/
    private final ArrayBlockingQueue<String> buffer;

    /***
     * Constructor for the FilterBuffer. Dictates that it has a maximum capacity of 10000 Strings at once
     */
    public FilterBuffer(){
        buffer = new ArrayBlockingQueue<String>(10000);
    }

    /***
     * Puts values into the buffer, while blocking other threads from doing the same while being used
     * @param dna   String containing the DNA sequence to add to the buffer
     * @throws InterruptedException Throws an exception in the event that blocking fails
     */
    public void blockingPut(String dna) throws InterruptedException{
        buffer.put(dna);
    }

    /***
     * Pulls values from the buffer and blocks other threads from doing so while in use.
     * @return String returnDNA, returns string holding DNA sequence added to the buffer
     * @throws InterruptedException Throws an exception in the event thatt blocking fails.
     */
    public String blockingGet() throws InterruptedException{
        String returnDNA = buffer.take();
        return returnDNA;
    }

}
