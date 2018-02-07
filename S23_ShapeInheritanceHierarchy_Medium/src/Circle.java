/***
 * Class Circle extends Elipse as it shares the same area and perimeter methods as an Elipse.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Circle extends Elipse {
    /**radius of a circle**/
    private double radius;

    /***
     * Constructor for Circle, passes the shapeName and radius to it's parent class Elipse
     * @param shapeName     String containing the user selected name of the shape
     * @param radius        double representation of the radius
     */
    public Circle(String shapeName, double radius){
        super(shapeName, radius);
        this.radius =radius;
    }

    /***
     * @return returns the radius value as a double.
     */
    public double getRadius(){
        return radius;
    }


}
