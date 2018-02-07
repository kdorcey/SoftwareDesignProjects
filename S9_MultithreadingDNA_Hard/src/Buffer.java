/***
 * Creates the interface for the programs "buffer".
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public interface Buffer {
    /***
     * Puts the input into the ArrayBuffer
     * @param dna   String containing the DNA sequence to add to the buffer
     * @throws InterruptedException Throws in the event that adding a String to the buffer is interrupted by
     *                              another thread.
     */
    public void blockingPut (String dna) throws InterruptedException;

    /***
     * Method responsible for pulling Strings of dna out from the bufferArray
     * @return  Returns String containing a DNA sequence
     * @throws InterruptedException Throws in the event that pulling a String from the buffer is interrupted by
     *                              another thread.
     */
    public String blockingGet () throws InterruptedException;
}
