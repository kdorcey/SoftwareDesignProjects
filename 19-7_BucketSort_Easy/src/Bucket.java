import java.math.*;

/***
 * Class responsible for sorting the input array via the bucket-sort method
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Bucket {
    /**inputted integer array to sort**/
    private int[] arrayToSort;
    /**2D integer array used to do the bucket-sort method**/
    private int[][] bucket;
    /**legnth of the array to sort**/
    private int arrayLength;
    /**Maximum 10's place in array to sort (ie: if you're sorting 20, 134, and 2432 "1000" will be the max10Multiple*/
    private int max10Multiple;
    /**Counts how many times the for loop within distributionPass() has ran**/
    private int run;

    /***
     * Constructor for the Bucket class. Sets arrayToSort, arrayLength, run, and bucket.
     * @param arrayToSort array that will be sorted. Inputted from BucketMain
     */
    public Bucket(int[] arrayToSort){
        this.arrayToSort = arrayToSort;
        this.arrayLength = arrayToSort.length;
        this.bucket = new int[10][arrayLength-1];
        run=0;
    }

    /***
     * Implements the bucket sort algorithm to sort "arrayToSort"
     */
    public void distributionPass(){
            for(int runTime = max10Multiple; runTime>1; runTime = runTime/10) {
                for (int arrayValue = 0; arrayValue < arrayLength; arrayValue++) {
                    int valueToSort = arrayToSort[arrayValue];
                    int column = 0; //places into the 0th column by default, changes if space is already occupied
                    int columnNum = valueToSort / (int) Math.pow(10, run); //used to decide which row to place valueToSort in. It isolates
                    int i = 0;
                    while (columnNum >= 10)  //runs until the columNum value is between 0 and 9
                    {
                        columnNum = columnNum % (max10Multiple / (int) Math.pow(10, i));
                        i++;
                    }
                    if (bucket[columnNum][column] != 0) {
                        column++;
                    }
                    bucket[columnNum][column] = valueToSort;
                }
                updateArrayList();
                run++;
            }
    }

    /***
     * Used to change the order of the arrayToSort after each for-loop in distributionPass()
     */
    public void updateArrayList(){
        int placeInArray=0;
        for(int row=0; row<9; row++){
            for (int col =0 ; col<arrayLength-1; col++){
                int toReplace = bucket[row][col];
                if(toReplace !=0){
                    arrayToSort[placeInArray] = toReplace;
                    placeInArray++;
                }
            }
        }
        emptyBucket();
    }

    /***
     * Resets the values within the bucket[][] array to 0
     */
    public void emptyBucket(){
        for(int x=0; x<9; x++){
            for(int y=0; y<arrayLength-1; y++){
                bucket[x][y]=0;
            }
        }
    }

    /***
     * Finds the maximum 10's power from the arrayToSort (ie: if the numbers to sort are 2341, 222, 41, and 1422 the maxTensPlace will be 1000)
     */
    public void findTensPlace(){
        int recounts = 0;
        int maxRecount = 0;
        for(int i =0; i<arrayLength; i++){
            int valueHolder = arrayToSort[i];

            while(valueHolder>0){
                valueHolder = valueHolder/10;
                recounts++;
            }
            if(recounts>=maxRecount){
                maxRecount= recounts;
                recounts=0;
            }
            else{
                recounts=0;
            }

        }
        int maxTensPlace = (int)Math.pow(10,maxRecount);
        max10Multiple = maxTensPlace;
    }

    /***
     * Prints the sorted ArrayList to the console
     */
    public void printSortedList(){
        System.out.println("Please work: ");
        for(int i=0; i<arrayLength; i++){
            System.out.print(arrayToSort[i]+", ");
        }
    }
}
