/***
 * TriangularPrism class extends from Prisms as it shares multple methods and variables with Prisms as well as Prisms's
 * parent class.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class TriangularPrism extends Prisms {
    /**baseWidth of the triangular prism **/
    private double baseWidth;
    /**base side length of the triangular prism**/
    private double baseSideLength;
    /**base side length opposite of "base side length" of the triangular prism**/
    private double baseSideLength2;
    /**height of the triangular prism**/
    private double baseHeight;
    /**surface area of the triangular prism**/
    private double surfaceArea;

    /***
     * TriangularPrism class constructor. Passes shapeName, baseWidth, length, baseHeight, and baseArea to the parent Class
     * @param shapeName         String containing shape name
     * @param baseWidth         width of the triangular prism's base
     * @param baseSideLength    sidelength of the triangular prism
     * @param baseSideLength2   other sidelenght of the triangular prism
     * @param baseHeight        height of triangular prism
     * @param length            length of the triangular prism
     */
    public TriangularPrism(String shapeName, double baseWidth, double baseSideLength, double baseSideLength2, double baseHeight, double length){
        super(shapeName, baseWidth, length, baseHeight,setBaseArea(baseWidth,baseHeight));

        this.baseWidth=baseWidth;
        this.baseSideLength = baseSideLength;
        this.baseSideLength2 = baseSideLength2;
        this.baseHeight = baseHeight;

        surfaceArea = setSurfaceArea(baseWidth, baseSideLength, baseSideLength2, length, setBaseArea(baseWidth, baseHeight));
    }

    /**
     * Finds base area
     * @param baseWidth     width of the triangular prism's base
     * @param baseHeight    height of the triangular prism
     * @return              the triangular prism's base's area
     */
    private static double setBaseArea(double baseWidth, double baseHeight){
        double baseArea = .5*baseHeight*baseWidth;
        return baseArea;
    }

    /***
     * Finds the Triangular prisms surface area
     * @param baseWidth         width of the triangular prism's base
     * @param baseSideLength    base side length of the triangular prism
     * @param baseSideLength2   the other base side length of the triangular prism
     * @param length            length of the triangular prism
     * @param baseArea          area of the triangular prism base
     * @return                  returns surface area as a double
     */
    private static double setSurfaceArea(double baseWidth, double baseSideLength, double baseSideLength2, double length, double baseArea){
        double surfaceArea = (2*baseArea)+(baseSideLength*length)+(baseSideLength2*length)+(baseWidth*length);
        return surfaceArea;
    }

    /***
     * @return returns the base width as a double
     */
    public double getBaseWidth() {
        return baseWidth;
    }

    /***
     * @return returns the base side length as a double
     */
    public double getBaseSideLength() {
        return baseSideLength;
    }

    /***
     * @return returns the other base length as a double
     */
    public double getBaseSideLength2() {
        return baseSideLength2;
    }

    /***
     * @return returns the base height as a double
     */
    public double getBaseHeight() {
        return baseHeight;
    }

    /***
     * @return returns the surface area as a double
     */
    public double getSurfaceArea() {
        return surfaceArea;
    }


}
