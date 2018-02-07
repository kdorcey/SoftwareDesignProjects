/***
 * The Cube class extends RectangularPrism as all methods (getVolume, getSurfaceArea, getSideLength, etc.) are
 * shared between the two.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Cube extends RectangularPrism {
    /**
     * Constructor for the cube class. It only has one sideLength as every side of a cube is the same length.
     * Because the class extends RectangularPrism it passes the string shapeName and sideLength to the it's parent class.
     * @param shapeName     String containing the user selected shape name.
     * @param sideLength    Length of the squares side.
     */
    public Cube(String shapeName, double sideLength){
        super(shapeName, sideLength, sideLength, sideLength);
    }

}
