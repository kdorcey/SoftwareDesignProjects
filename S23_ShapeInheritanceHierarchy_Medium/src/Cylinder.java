/***
 * The cylinder class extends ThreeDCurves as it shares multiple methods and variables with the parent class.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Cylinder extends ThreeDCurves {
    /**Surface area of a cylinder**/
    private double surfaceArea;
    /**volume of the clyinder**/
    private double volume;

    /***
     * Constructor for the cylinder class, passes shapeName, radius, and height to the parent class.
     * @param shapeName     String containing the user selected shape name.
     * @param radius        Radius of the cylinder's base.
     * @param height        Height of the cylinder
     */
    public Cylinder (String shapeName, double radius, double height){
        super(shapeName, radius, height);

        surfaceArea = setSurfaceArea(radius, height);
        volume = volumeForConeAndCylinder();
    }

    /***
     * Finds the surface area of a cylinder. Static because it is called during class construction.
     * @param radius        Radius of the cylinder's base in the form of a double.
     * @param height        Height of the cylinder's base in the form of a double.
     * @return              Returns the surface area as a double.
     */
    private static double setSurfaceArea(double radius, double height){
        double surfaceArea = (2*getPi()*radius*height)+(2*getPi()*Math.pow(radius,2));
        return surfaceArea;
    }

    /***
     * @return returns the surface area as a double
     */
    public double getSurfaceArea() {
        return surfaceArea;
    }

    /***
     * @return returns the volume as a double.
     */
    public double getVolume() {
        return volume;
    }

}
