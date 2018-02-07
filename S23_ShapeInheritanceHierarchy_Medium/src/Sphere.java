/***
 * Sphere class, the class extends ThreeDCurves as it shares multple variables and methods with the class
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Sphere extends ThreeDCurves {
    /**volume of the sphere**/
    private double volume;
    /**surface are of the sphere**/
    private double surfaceArea;

    /***
     * Constructor for the Shape class. Passes shapeName and radius to the parent class.
     * Calls methods setVolume and setSurfaceArea to get values for volume and surfaceArea
     * @param shapeName     String containing the user defined name of the shape
     * @param radius        Radius of the sphere
     */
    public Sphere (String shapeName, double radius){
        super(shapeName, radius);
        volume = setVolume(radius);
        surfaceArea = setSurfaceArea(radius);
    }

    /***
     * Static method used to find the volume of a sphere. Static as it is called during the class construction
     * @param radius    radius of the sphere
     * @return          returns the volume as a double
     */
    private static double setVolume(double radius){
        double volume = ((4/3)*getPi()*Math.pow(radius,3));
        return volume;
    }

    /***
     * Static method to find surface area of the sphere. Static as it is called during class construction
     * @param radius    radius of the sphere
     * @return          returns surface area as a double
     */
    private static double setSurfaceArea(double radius){
        double surfaceArea = 4*getPi()*Math.pow(radius,2);
        return surfaceArea;
    }

    /***
     * @return returns volume as a double
     */
    public double getVolume(){
        return volume;
    }

    /***
     * @return returns surface area as a double
     */
    public double getSurfaceArea(){
        return surfaceArea;
    }
}
