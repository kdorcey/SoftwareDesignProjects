import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/***
 * Class Polygons extends TwoDimensionalShape as it shares multiple methods or variables with its parent class.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Polygons extends TwoDimensionalShape {
    /**Perimeter of the polygon**/
    private double perimeter;
    /**As polygons can have infinite sides an arraylist holds all of them so it can find perimeter**/
    private ArrayList <Double> sides;

    /***
     * Constructor for the polygon class. Pass shapeName to it's parent class.
     * The constructor adds all sides to an ArrayList so it can be passed to the method that calculates perimeter
     * of a polygon regardless of number of sides
     * @param shapeName     String containing name of shape.
     * @param base          Length of the base of the shape.
     * @param base2         Length of the side opposite of base.
     * @param side          Length of the side of the shape.
     * @param side2         Length of the side opposite of "side"
     */
    public Polygons (String shapeName, double base, double base2, double side, double side2){
        super(shapeName);
        sides = new ArrayList<>();
        Collections.addAll(sides, base,base2, side, side2);

        perimeter = getPerimeter(sides);
    }


    /***
     * Constructor for polygons with more than 4 sides.
     * @param shapeName     String containing name of the shape.
     * @param sideLegnths   ArrayList of doubles holding the side lengths of the polygon
     */
    public Polygons (String shapeName, ArrayList<Double> sideLegnths){
        super(shapeName);
        this.sides = sideLegnths;
    }

    /***
     * Calculates the perimeter of the polygon. Static as it is called during class construction.
     * @param sides     ArrayList containing double values representing every side length.
     * @return          returns the perimeter as a double
     */
    private static double getPerimeter(ArrayList<Double> sides){
        double perimeter=0;
        for(double individualSides:sides){
            perimeter +=individualSides;
        }
        return perimeter;
    }

    /***
     * @return returns the perimeter as a double
     */
    public double getPerimeter() {
        return perimeter;
    }
}
