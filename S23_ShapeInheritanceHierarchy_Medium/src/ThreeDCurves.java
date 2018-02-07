/***
 * Class ThreeDCurves extends from ThreeDimensionalShapes as it shares the common features such as
 * not having either area or perimeter. It exsists primarily as a parent class to other shape classes.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class ThreeDCurves  extends ThreeDimensionalShapes{
    /**radius of the ThreeDCurve**/
    private double radius;
    /**height of the ThreeDCurve**/
    private double height;

    /***
     * Class constructor for ThreeDCurves that do not have a height value.
     * @param shapeName     String containing user defined shape name
     * @param radius        double with the value of the radius of the ThreeDCurve
     */
    public ThreeDCurves (String shapeName, double radius){
        this(shapeName,radius,0);
    }

    /***
     * Class constructor for ThreeDCurves that have a height value
     * @param shapeName     String containing user defined shape name
     * @param radius        double representing the radius of the ThreeDCurve
     * @param height        double representing the height of the curve
     */
    public ThreeDCurves (String shapeName, double radius, double height){
        super(shapeName);
        this.radius =radius;
        this.height = height;
    }

    /***
     * Method used to find the area of a circular base
     * @return  returns the area of a circular base as a double
     */
    public double areaOfCircleBase(){
        double areaOfCircleBase = getPi()*Math.pow(radius,2);
        return areaOfCircleBase;
    }

    /***
     * Method to find the volume for a cone or cylinder
     * @return  returns the volume of a cone or cylinder as a double
     */
    public double volumeForConeAndCylinder(){
        double volume = getPi()*Math.pow(radius,2)*height;
        return volume;
    }

    /***
     * @return returns the radius as a double
     */
    public double getRadius(){
        return radius;
    }

    /***
     * @return returns the height as a double
     */
    public double getHeight(){
        return height;
    }

}
