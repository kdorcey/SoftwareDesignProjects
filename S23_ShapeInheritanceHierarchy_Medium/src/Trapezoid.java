/***
 * Class constructor for Trapezoid. Extends the Quadrilateral class as it shares various methods and variables with it
 * as well as Quadrilateral's parent class.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Trapezoid extends Quadrilaterals {
    /**area of the trapezoid**/
    double area;
    /**height of the trapezoid**/
    double height;

    /***
     * Class constructor for the Trapezoid. Passes shapeName, base, leftside length, base2, and rightside length to
     * the parent class.
     * @param shapeName     String containing the user defined name of the shape
     * @param base          Length of the trapezoid's base
     * @param base2         Length of the side opposite of the base
     * @param leftSide      Length of the left side of the trapezoid
     * @param rightSide     Length of the right side of the trapezoid
     * @param height        Height of the trapezoid
     */
    public Trapezoid(String shapeName, double base, double base2, double leftSide, double rightSide, double height){
        super(shapeName, base, leftSide , base2, rightSide);
        this.area = setArea(base, base2, height);
        this.height =height;
    }


    /***
     * Method used to find the area of a trapezoid. Static as it is called during class construction
     * @param base      Length of the base of the trapezoid
     * @param base2     Length of the side opposite to base
     * @param height    Height of the trapezoid
     * @return          returns the area of the trapezoid as a double
     */
    private static double setArea(double base, double base2, double height){
        double area = ((base + base2)/2) * height;
        return area;
    }

    /***
     * @return returns the leftSideLength as a double
     */
    public double getLeftSideLength(){ return getHeight();}
    /***
     * @return returns the rightSideLength as a double
     */
    public double getRightSideLength(){ return getHeight2();}
    /***
     * @return returns the area as a double
     */
    public double getArea(){
        return area;
    }
    /***
     * @return returns the height as a double
     */
    public double getHeight() {
        return height;
    }
}
