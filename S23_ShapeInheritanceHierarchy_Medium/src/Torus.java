/***
 * Class for the Torus 3D shape. The class extends from ThreeDCurves as it shares multple variables and methods with the
 * class.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Torus extends ThreeDCurves {

    /**radius from the center of a torus to the outer ring**/
    private double majorRadius;
    /**radius from the center of a torus to the inner ring**/
    private double minorRadius;
    /**volume of the torus**/
    private double volume;
    /**surface area of the torus**/
    private double surfaceArea;

    /***
     * Torus class constructor. Passes shapeName and minorRadius to it's parent class.
     * The volume and surface area are found from the setVolume() and setSurfaceArea() methods respectively.
     * @param shapeName     String containing user defined shape name
     * @param minorRadius   double representation of the radius to the Torus's inner ring
     * @param majorRadius   double representation of the radius to the Torus's outter ring
     */
    public Torus(String shapeName, double minorRadius, double majorRadius){
        super (shapeName, minorRadius);
        this.majorRadius = majorRadius;
        this.volume = setVolume(majorRadius)*areaOfCircleBase();
        this.surfaceArea = setSurfaceArea(minorRadius,majorRadius);
        this.minorRadius = getRadius();
    }

    /***
     * Finds the Torus's surface area. The method is static so it may be called during class construction
     * @param minorRadius   radius to Torus's inner ring
     * @param majorRadius   radius to Torus's outer ring
     * @return              returns the surface area as a double
     */
    private static double setSurfaceArea(double minorRadius, double majorRadius){
        double surfaceArea = 2*getPi()*majorRadius*2*getPi()*minorRadius;
        return surfaceArea;
    }

    /***
     * Finds the volume of the Torus. The method is static so it may be called during class construction
     * @param majorRadius   radius to the Torus's outer ring
     * @return              returns volue as a double
     */
    private static double setVolume(double majorRadius){
        double volume = 2*getPi()*majorRadius;
        return volume;
    }

    /***
     * @return returns MajorRadius as a double
     */
    public double getMajorRadius() {
        return majorRadius;
    }

    /***
     * @return returns minorRadius as a double
     */
    public double getMinorRadius() {
        return minorRadius;
    }

    /***
     * @return returns the volume as a double
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
