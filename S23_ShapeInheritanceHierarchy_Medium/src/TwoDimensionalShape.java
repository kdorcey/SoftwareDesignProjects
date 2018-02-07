/***
 * Class TwoDimensionalShape extends from Shapes as it shares the basic variables (such as color and shapeName).
 * This class serves primarily as a parent class for others to extend from.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class TwoDimensionalShape extends Shapes {
    /**Dimensions of the 2D shapes*/
    private final double dimensions=2;
    /**Constant property for all 2D shapes. They have no volume**/
    private final double volume =0;
    /**Constant property for all 2D shapes. They have no surface area**/
    private final double surfaceArea=0;

    /***
     * Class constructor for TwoDimensionalShapes. Passes shapeName to parent class.
     * @param ShapeName     String containing user defined name of shape.
     */
    public TwoDimensionalShape(String ShapeName){
        super(ShapeName);
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
    public double getSurfaceArea() {return surfaceArea;}


}
