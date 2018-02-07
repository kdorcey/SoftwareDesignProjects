/**
 * Driver class for 19-7_BucketSort_Easy. Responsible for defining the int[] that will be sorted
 * and then passing it to the Bucket class as well as calling methods necessary to sort the numbers.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class BucketMain {
    public static void main (String args[]){
        System.out.println("sorting 97, 3, 100");
        int arrayToSort[] ={97,3,100};
        Bucket bucket = new Bucket(arrayToSort);
        bucket.findTensPlace(); //Finds the maximum 10's place necessary to loop through the 2D array the right amount of times
        bucket.distributionPass(); //method responsible for actually sorting the array
        bucket.printSortedList(); //prints the array


    }
}
