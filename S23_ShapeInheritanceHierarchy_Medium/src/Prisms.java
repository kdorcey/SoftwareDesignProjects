/***
 * Class Prisms extends ThreeDimensionalShapes as it shares multiple traits with it's parent class.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Prisms extends ThreeDimensionalShapes {
    /**Width of the prism**/
    private double width;
    /**height of the prism**/
    private double height;
    /**length of the prism**/
    private double length;
    /**area of the base of the prism**/
    private double areaOfBase;
    /**volume of the prism**/
    private double volume;
    /**surface area of the prism**/
    private double surfaceArea;

    /****
     * Constructor for the Prism class. Passes shapeName to its parent class
     * @param shapeName     String containing the shape's name
     * @param width         width of the prism
     * @param length        length of the prism
     * @param height        height of the prism
     * @param areaOfBase    area of the base of the prism
     */
    public Prisms(String shapeName, double width, double length, double height, double areaOfBase){
        super(shapeName);
        this.width = width;
        this.height = height;
        this.length = length;
        this.areaOfBase = areaOfBase;

        this.volume = setVolume(areaOfBase,length);
        this.surfaceArea = setSurfaceArea(width,length,height);

    }

    /***
     * Finds surface area of the prism. Static as it is called during construction of the class.
     * @param width     width of the prism
     * @param length    length of the prism
     * @param height    height of the prism
     * @return          returns the surfaceArea of the prism as a double
     */
    private static double setSurfaceArea(double width, double length, double height){
        double surfaceArea;
        surfaceArea = 2*((width*length)+(width*height)+(height*length));
        return surfaceArea;
    }

    /***
     * Finds the volume of the prism. The method is static as it
     * @param areaOfBase    Area of the base of the prism.
     * @param length        Length of the prism.
     * @return              Returns the volume of the prism as a double
     */
    private static double setVolume(double areaOfBase, double length){
        double volume = areaOfBase*length;
        return volume;
    }

    /***
     * @return returns width of the prism as a double
     */
    public double getWidth() {
        return width;
    }

    /***
     * @return returns the height of the prism as a double
     */
    public double getHeight() {
        return height;
    }

    /***
     * @return returns the length of the prism as a double
     */
    public double getLength() {
        return length;
    }

    /***
     * @return returns the area of the base of the prism as a double
     */
    public double getAreaOfBase() {
        return areaOfBase;
    }

    /***
     * @return returns the volume of the prism as a double
     */
    public double getVolume() {
        return volume;
    }

    /***
     * @return returns the surface area of the prism as a double
     */
    public double getSurfaceArea() {
        return surfaceArea;
    }





}
