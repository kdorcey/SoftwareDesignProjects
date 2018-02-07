import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.reflect.Array;
import java.util.ArrayList;

/***
 * Class responsible for converting The DNA sequences from Frame Builder into their corresponding amino acids
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Filter4Translator extends Filter implements Runnable {
    /***
     * enum containing all possible amino acids. The enum names are the RNA sequences that are used to
     * find the proper amino acid. Each enum contains the full name of the amino acid and its one-letter
     * representation
     *
     * @author Prof. Johnson/Casavant, Software Design, 2017
     */
    public enum Codon {
        UUU("Phe", 'F'), UCU("Ser",'S'), UAU("Tyr", 'Y'), UGU("Cys", 'C'),
        UUC("Phe", 'F'), UCC("Ser",'S'), UAC("Tyr",'Y'), UGC("Cys", 'C'),
        UUA("Leu", 'L'), UCA("Ser",'S'), UAA("Stop", '*'), UGA("Stop", '*'),
        UUG("Leu", 'L'), UCG("Ser",'S'), UAG("Stop", '*'), UGG("Trp", 'W'),

        CUU("Leu",'L'), CCU("Pro",'P'), CAU("His", 'H'), CGU("Arg", 'R'),
        CUC("Leu",'L'), CCC("Pro",'P'), CAC("His", 'H'), CGC("Arg", 'R'),
        CUA("Leu",'L'), CCA("Pro",'P'), CAA("Gln", 'Q'), CGA("Arg", 'R'),
        CUG("Leu",'L'), CCG("Pro",'P'), CAG("Gln", 'Q'), CGG("Arg", 'R'),

        AUU("Ile",'I'), ACU("Thr", 'T'), AAU("Asn", 'N'), AGU("Ser", 'S'),
        AUC("Ile",'I'), ACC("Thr", 'T'), AAC("Asn", 'N'), AGC("Ser", 'S'),
        AUA("Ile",'I'), ACA("Thr", 'T'), AAA("Lys", 'K'), AGA("Arg", 'R'),
        AUG("Ile",'I'), ACG("Thr", 'T'), AAG("Lys", 'K'), AGG("Arg", 'R'),

        GUU("Val",'V'), GCU("Ala", 'A'), GAU("Asp", 'D'), GGU("Gly", 'G'),
        GUC("Val",'V'), GCC("Ala", 'A'), GAC("Asp", 'D'), GGC("Gly", 'G'),
        GUA("Val",'V'), GCA("Ala", 'A'), GAA("Glu", 'E'), GGA("Gly", 'G'),
        GUG("Val",'V'), GCG("Ala", 'A'), GAG("Glu", 'E'), GGG("Gly", 'G'),
        ZZZ("ERROR", '~');


        private String threeLetterCode;
        private char oneLetterCode;

        /***
         * Constructor for each Codon
         * @param threeLetter   String containing the full amino acid name
         * @param oneLetter     Char containing the 1-letter amino acid representation
         */
        private Codon (String threeLetter, char oneLetter){
            threeLetterCode = threeLetter;
            oneLetterCode = oneLetter;
        }


        /***
         * @return returns the codons 3-letter representation
         */
        public String getThreeLetterCode(){
            return threeLetterCode;
        }

        /***
         * @return returns each codons 1-letter representation
         */
        public char getOneLetterCode(){
            return oneLetterCode;
        }

        /***
         * Static method which returns a specific codon based on the base-pairs in the input
         * @param codon Input that determines which codon to return
         * @return  A reference to a specific codon
         */
        public static Codon getCodon(String codon){
            codon = codon.toUpperCase();
            codon = codon.replace('T','U');
            return valueOf(codon);
        }
    }

    /**Array list that will hold all aminoAcids that come from the original user input**/
    private static ArrayList<String> aminoAcids;
    /**Array list that will hold all aminoAcids that come from the original input without the first base pair**/
    private static ArrayList<String> noFirstAminoAcids;
    /**Array list that will hold all amino aicds that come from the original input without the first or second base pair**/
    private static ArrayList<String> noSecondAminoAcids;

    /***
     * Class constructor, responsible for calling this class's parent class's constructor as well as
     * defining aminoAcids, noFirstAminoAcids, and noSecondAminoAcids.
     * @param inputBufferLocation   object reference to the buffer that handles inputs
     * @param outputBufferLocation  object reference to the buffer that handles outputs
     */
    public Filter4Translator(Buffer inputBufferLocation, Buffer outputBufferLocation){
        super(inputBufferLocation,outputBufferLocation);
        aminoAcids = new ArrayList<>();
        noFirstAminoAcids = new ArrayList<>();
        noSecondAminoAcids = new ArrayList<>();
    }

    /***
     * This method is responsible for pulling all information from the Buffer. It stops doing this when it reaches
     * the stopping value (defined in the Filter class). Each time it pulls a String from the buffer it passes it to
     * the sortInputs() method so that it can sort whether an input belongs to the original DNA sequence, the DNA sequence
     * without the first base, or the sequence without the first or second base.
     */
    public void run(){
        String tempRnaHolder ="";

        while(true){
            tempRnaHolder = getFromBuffer();
            if(tempRnaHolder.equals(getStopValue())){
                break;
            }
            sortInputs(tempRnaHolder);
        }
        putInBuffer(getStopValue());

    }

    /***
     * Runs through each character from each string pulled from the buffer. Based on each strings "prefix" (or first
     * value) it will determine whether it  belongs to the original DNA sequence, the DNA sequence
     * without the first base, or the sequence without the first or second base. From here it will pass the String to
     * the individualString() method. Finally it will pass the new String value, containing only the DNA sequence, to the
     * findAminoAcids() method. It then puts the string of aminoAcids into the buffer with its corresponding prefix and
     * partialStop.
     * @param inputToSort String that will be sorted and converted from the buffer
     */
    public void sortInputs(String inputToSort) {

        for (int i = 0; i < inputToSort.length(); i++) {
            String rnaToTranslate = "";
            String acids ="";
            char checkForPrefix = inputToSort.charAt(i);

            if (checkForPrefix == getUnchangedInputPrefix().charAt(0)) //when the following string belongs to the original input
            {
                rnaToTranslate = individualString(i,inputToSort);
                acids = findAminoAcids(rnaToTranslate);

                aminoAcids.add(acids);
                putInBuffer(getUnchangedInputPrefix()+acids+getPartialStop());
            }
            else if (checkForPrefix == getNoFirstInputPrefix().charAt(0)) //when the following string belongs to the input without the first base
            {
                rnaToTranslate = individualString(i,inputToSort);
                acids = findAminoAcids(rnaToTranslate);

                noFirstAminoAcids.add(acids);
                putInBuffer(getNoFirstInputPrefix()+acids+getPartialStop());

            }
            else if (checkForPrefix == getNoFirstandSecondPrefix().charAt(0)) //when the following string belongs to the input without the first and second base
            {
                rnaToTranslate = individualString(i,inputToSort);
                acids = findAminoAcids(rnaToTranslate);

                noSecondAminoAcids.add(acids);
                putInBuffer(getNoFirstandSecondPrefix()+acids+getPartialStop());
            }

        }

    }

    /***
     * Takes in a string containing a "prefix" and "partialStop" and returns only the DNA sequence
     * @param currentLocation integer showing where to start reading the input String from (so that it does not
     *                        read the same sequence more than once)
     * @param inputToSort     String it will extract the DNA sequence from
     * @return  String containing only the relevant DNA sequence
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
     * Used to find the amino acids from each set of 3 RNA sequences
     * @param rnaToAmino    RNA sequence from amino acid
     * @return  String containing the amino acids derived from the RNA sequence
     */
    public String findAminoAcids(String rnaToAmino){
        String aminoAcidChain ="";
        String chainToReturn ="";
        if(rnaToAmino.length()>=3){
            for (int i = 0; i<rnaToAmino.length(); i++){
                aminoAcidChain += rnaToAmino.charAt(i);

                if((i+1)%3==0){
                    Codon singleCodon = Codon.getCodon(aminoAcidChain);
                    chainToReturn += singleCodon.getOneLetterCode();
                    aminoAcidChain ="";
                }
            }
        }
        return chainToReturn;
    }


    /***
     * After all threads are executed method is called to print each inputs corresponding amino acids
     */
    public static void printAminoAcids(){
        System.out.print("Amino Acids: ");
        for(String acid: aminoAcids){
            System.out.print(acid+", ");
        }
        System.out.println();
        System.out.print("Amino Acids (Dropped first): ");
        for(String acid: noFirstAminoAcids){
            System.out.print(acid+", ");
        }
        System.out.println();
        System.out.print("Amino Acids (Dropped first and second): ");
        for(String acid: noSecondAminoAcids){
            System.out.print(acid+", ");
        }
        System.out.println();
    }
}
