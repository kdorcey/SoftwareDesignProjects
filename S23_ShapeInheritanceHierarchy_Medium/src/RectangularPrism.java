/***
 * Class for the RectangularPrism shape. It extends from class Prism as it shares multples variables and
 * methods with it.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class RectangularPrism extends Prisms {
    /***
     * Construtor for RectangularPrism class. Passes shapeName, width, length, height, and baseArea to it's parent class.
     * @param shapeName     String containing the user selected name of the shape
     * @param width         width of the rectangular prism
     * @param length        length of the rectangular prism
     * @param height        height of the rectangular prism
     */
    public RectangularPrism (String shapeName, double width, double length, double height){
        super(shapeName,width,length,height, getBaseArea(width,height));
    }

    /***
     * Calculates the base area. Method is static as it is called during class construction
     * @param width     width of the rectangular prism
     * @param height    height of the rectangular prism
     * @return          returns the area of the base of the rectangular prism
     */
    private static double getBaseArea(double width, double height){
        double baseArea = width*height;
        return baseArea;
    }

}
