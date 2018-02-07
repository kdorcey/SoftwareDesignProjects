/***
 * Class Kite extends Quadrilaterals as it shares multiple methods and variables from it's parent class.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Kite extends Quadrilaterals {
    /**area of the kite**/
    private double area;
    /**length of the kite**/
    private double length;
    /**width of the kite**/
    private double width;

    /***
     * Class constructor for the Kite. It passes shapeName, longersideLength, and shorterSideLength twice to it's
     * parent class in order to calculate it's perimeter.
     * @param shapeName             String containing the shape's name.
     * @param longerSidesLength     Length of the kite's longer side.
     * @param shorterSideLength     Length of the kite's shorter side
     * @param length                Length of the kite.
     * @param width                 Width of the kite.
     */
    public Kite(String shapeName, double longerSidesLength, double shorterSideLength, double length, double width){
        super(shapeName,longerSidesLength,longerSidesLength,shorterSideLength,shorterSideLength);
        this.length =length;
        this.width = width;
        this.area= setArea(length,width);
    }

    /***
     * Finds the area of a kite. Method is static as it is called during class construction.
     * @param length                Length of the Kite
     * @param width                 Width of the kite
     * @return                      returns the area of the kite as a double
     */
    private static double setArea(double length, double width){
        double area = length*width;
        return area;
    }

    /***
     * @return returns the length of the longer side as a double.
     */
    public double getLongerSideLength(){
        return getBase();
    }
    /***
     * @return returns the length of the shorter side as a double.
     */
    public double getShorterSideLength(){
        return getBase2();
    }

    /***
     * @return returns the length of the kite as a double.
     */
    public double getLenght(){
        return length;
    }
    /***
     * @return returns the width of the kite as a double.
     */
    public double getWidth(){
        return width;
    }

    /***
     * @return returns the area as a double
     */
    public double getArea(){
        return area;
    }
}
