/***
 * Class Cone is an extension ThreeDCurves as it shares methods and features with other Three dimensional curves
 * such as radius, height, and a circle base.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Cone extends ThreeDCurves{
    /**Voluem of the cone**/
    private double volume;
    /**surface area of the cone**/
    private double surfaceArea;

    /***
     * Constructor of the Cone class. It passes the shapeName, radius, and height parameters to its parent class
     * ThreeDCurves
     * @param shapeName     String containing the user selected shape name.
     * @param radius        The radius of the cones base held in a double.
     * @param height        The height of the cone held in a double.
     */
    public Cone (String shapeName, double radius, double height) {
        super(shapeName, radius, height);
        volume = volumeForConeAndCylinder()/3;
        surfaceArea = setSurfaceArea(radius, height);

    }

    /***
     * Finds the surface area of the cone. The method is static as it is purely computational and is
     * called during the class construction.
     * @param radius        Radius of the cone's base.
     * @param height        Height of the cone.
     * @return              Returns the cones surface area as a double.
     */
    private static double setSurfaceArea(double radius, double height){
        double surfaceArea = (getPi()*radius) * (radius + Math.sqrt(Math.pow(height,2)+Math.pow(radius,2)));
        return surfaceArea;
    }

    /***
     * @return returns volume as a double
     */
    public double getVolume() {
        return volume;
    }

    /***
     * @return returns the surface area as a double
     */
    public double getSurfaceArea() {
        return surfaceArea;
    }
}
