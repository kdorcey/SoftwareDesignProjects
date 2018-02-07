/***
 * Class for Parallelogram. It extends from Quadrilaterals as parallelogram's share multiple methods with quadrilaterals.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Parallelogram extends Quadrilaterals {
    /**area of the parallelogram**/
    private double area;


    /****
     * Class constructor for parallelogram. Passes shapeName, base, height, base2, and height2 to the parent class.
     * @param shapeName     String containing the name of the shape.
     * @param base          Double representing the length of the base.
     * @param height        Double representing the height of the parallelogram
     * @param base2         Double representing the length of the side opposite of base
     * @param height2       Double representing the length of the side opposite of "height"
     */
    public Parallelogram (String shapeName, double base, double height, double base2, double height2 ){
        super(shapeName,base,height,base2,height2);
        this.area = setArea(base,height);
    }

    /***
     * Finds the area of a parallelogram. The method is static as it is called during class construction.
     * @param base          Length of the base
     * @param height        Length of the height
     * @return              Returns the area as a double
     */
    private static double setArea(double base, double height){
        double area = base*height;
        return area;
    }

    /***
     * @return returns the area as a double
     */
    public double getArea(){
        return area;
    }

}
