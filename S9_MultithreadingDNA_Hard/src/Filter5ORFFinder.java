import java.lang.reflect.Array;
import java.util.ArrayList;

/***
 * Class responsible for finding only "relevant ORFs" (in other words, it ignores any amino acid chain
 * that is less than 21 characters long)
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Filter5ORFFinder extends Filter implements Runnable {
    /**Array List containing the relevant ORFs for the original input**/
    private static ArrayList<String> listOfORF;
    /**Array List used to hold the relevant ORFs for the input without the first base**/
    private static ArrayList<String> noFirstListOfORF;
    /**Array List used to hold the relevant ORFs for the input without the first and second base**/
    private static ArrayList<String> noFirstandSecondListofORF;

    /***
     * Class constructor, responsible for calling this class's parent class's constructor as well as
     * defining listOfORF, noFirstListOfORF, and noFirstandSecondListofORF.
     * @param inputBufferLocation   object reference to the buffer that handles inputs
     * @param outputBufferLocation  object reference to the buffer that handles outputs
     */
    public Filter5ORFFinder(Buffer inputBufferLocation, Buffer outputBufferLocation){
        super(inputBufferLocation, outputBufferLocation);
        listOfORF = new ArrayList<>();
        noFirstListOfORF = new ArrayList<>();
        noFirstandSecondListofORF = new ArrayList<>();
    }

    /***
     * Method responsible for pulling all values from the buffer until it reaches the stopping value. As it
     * pulls each string it will be passed to the "sortInputs()" method which determines whether an inputted
     * string belongs to the original DNA sequence, the DNA sequence without the first base,
     * or the sequence without the first or second base.
     */
    public void run(){
        while(true){
            ArrayList<String> relevantORFHolder = new ArrayList<>();
            String tempAminoChain = getFromBuffer();

            if(tempAminoChain.equals(getStopValue())){
                break;
            }
            sortInputs(tempAminoChain);
        }
    }

    /***
     * Method which determines whether a string belongs to the original DNA sequence, the DNA sequence
     * without the first base, or the sequence without the first or second base. After determining the origin
     * of the DNA sequence it will call individualString() which returns only the relevant amino acid chain. Finally
     * it will pass this chain to findORF.
     * @param inputToSort String that will be sorted.
     */
    public void sortInputs(String inputToSort){
        for (int i = 0; i < inputToSort.length(); i++)//when the following string belongs to the original input
        {
            String potentialORF = "";
            char checkForPrefix = inputToSort.charAt(i);

            if (checkForPrefix == getUnchangedInputPrefix().charAt(0))
            {
                potentialORF = individualString(i,inputToSort);

                for(String individualStringFromORFArray: findORF(potentialORF)){
                    listOfORF.add(individualStringFromORFArray);
                }

            }
            else if (checkForPrefix == getNoFirstInputPrefix().charAt(0)) //when the following string belongs to the input without its first base
            {
                potentialORF = individualString(i,inputToSort);

                for(String individualStringFromORFArray2: findORF(potentialORF)){
                    noFirstListOfORF.add(individualStringFromORFArray2);
                }
            }
            else if (checkForPrefix == getNoFirstandSecondPrefix().charAt(0)) //when the following string belongs to the input without its first and second base
            {
                potentialORF = individualString(i,inputToSort);

                for(String individualStringFromORFArray3: findORF(potentialORF)) {
                    noFirstandSecondListofORF.add(individualStringFromORFArray3);
                }
            }
        }
    }

    /***
     * Takes in a string containing a "prefix" and "partialStop" and returns only the DNA sequence
     * @param currentLocation integer showing where to start reading the input String from (so that it does not
     *                        read the same sequence more than once)
     * @param inputToSort     String it will extract the amino acid sequence from
     * @return  String containing only the relevant amino acid sequence
     */
    public String individualString(int currentLocation, String inputToSort){
        int partialStopSearch =currentLocation + 1; //+1 accounts for the arbitrary symbol representing each input
        String rnaToTranslate="";
        while(true){
            if(inputToSort.charAt(partialStopSearch)==getPartialStop().charAt(0)){
                break;
            }
            else {
                rnaToTranslate += inputToSort.charAt(partialStopSearch);
                partialStopSearch++;
            }
        }
        return rnaToTranslate;
    }

    /***
     * Determines which Strings of amino acid chains are "relevant" (or greater than 21 characters long)
     * @param aminoAcidChain    Amino acid chain to check
     * @return  String containing all relevant amino acid chains
     */
    public ArrayList<String> findORF(String aminoAcidChain){
        String relevantORF = "";
        ArrayList<String> temporaryHolder = new ArrayList<>();

        int k=0;
        for(int i=0; i<aminoAcidChain.length();i++){
            if(aminoAcidChain.charAt(i)=='*'){
                if(k>21) {
                    temporaryHolder.add(relevantORF);
                }
                relevantORF="";
                k=0;
            }
            else{
                relevantORF += aminoAcidChain.charAt(i);
            }
            k++;
        }
        if(k>21){
            temporaryHolder.add(relevantORF);
        }

        return temporaryHolder;
    }

    /***
     * After all threads have executed, prints the relevant ORF's to the console
     */
    public static void printRelevantORF(){
        System.out.println();
        System.out.print("Relevant ORF: ");
        for(String test1: listOfORF){
            System.out.print(test1+", ");
        }
        System.out.println();
        System.out.print("Relevant ORF (first RNA removed): ");
        for(String test2:  noFirstListOfORF){
            System.out.print(test2+", ");
        }
        System.out.println();
        System.out.print("Relevant ORF (first and second RNA removed): ");
        for(String test3: noFirstandSecondListofORF){
            System.out.print(test3+", ");
        }
        System.out.println();
    }
}
