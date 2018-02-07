/***
 * Class Quadrilateral used mostly as a parent class shared between Rectangle and Rhombus. It extends from Polygon
 * as it shares multiple methods with it.
 */
public class Quadrilaterals extends Polygons {
    /**length of the base of the quadrilateral**/
    private double base;
    /**height of the quadrilateral**/
    private double height;
    /**Length of the base opposite of "base" of the quadrilateral**/
    private double base2;
    /**height opposite of "height" of the quadrilateral**/
    private double height2;

    /***
     * Class constructor for Quadrilaterals. It passes the shapeName, base, height, base2, and height2 to it's parent
     * class.
     * @param shapeName     String containing the name of the shape.
     * @param base          Length of the base of the shape.
     * @param height        Height of the shape.
     * @param base2         Length of the side opposite of "base".
     * @param height2       Length of the side opposite of "height"
     */
    public Quadrilaterals (String shapeName, double base, double height, double base2, double height2){
        super(shapeName, base, height, base2, height2);
        this.base = base;
        this.height = height;
        this.base2 = base2;
        this.height2 =height2;

    }

    /***
     * @return returns base as a double
     */
    public double getBase() {
        return base;
    }

    /***
     * @return returns base2 as a double
     */
    public double getBase2() {
        return base2;
    }

    /***
     * @return returns height as a double
     */
    public double getHeight() {
        return height;
    }

    /***
     * @return returns height2 as a double
     */
    public double getHeight2() {
        return height2;
    }




}
