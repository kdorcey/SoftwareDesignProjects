/***
 * Class Rectangle Extends from Prallelogram as it shares multiple methods and instance variables with it's parent class.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Rectangle extends Parallelogram {
    /***
     * Constructor for the Rectangle class. Passes shapeName, base, and height to it's parent class.
     * The constructor only requiers two side lengths as rectangles only have two different side lengths.
     * @param shapeName     String containing the user selected name of the shape.
     * @param base          double representing the length of the base of the rectangle
     * @param height        double representing the height of the rectangle
     */
    public Rectangle (String shapeName, double base, double height){
        super(shapeName,base,base,height,height);
    }
}
