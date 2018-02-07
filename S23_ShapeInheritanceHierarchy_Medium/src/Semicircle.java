/***
 * Semicircle extends circle as it shares almost every method and variable as Circle. Just modified slightly
 */
public class Semicircle extends Circle {
    /**area of the semicircle**/
    private double area;
    /**perimeter of the semicircle**/
    private double perimeter;
    /**degrees in a semicircle**/
    private double degrees;

    /***
     * Constructs the Semicircle object. Passes shapeName and radius to it's parent class.
     * @param shapeName     String representation of the user selected name for the shape.
     * @param radius        radius of the semicircle
     */
    public Semicircle(String shapeName, double radius){

        super(shapeName, radius);
    }

    /***
     * @return returns the area of the semicircle as a double after using it's parent class's getArea() method and dividing by two
     */
    @Override
    public double getArea(){
        area = super.getArea();
        return area/2;
    }

    /***
     * @return returns the perimeter of the semicircle as a double after using it's parent classes method getPerimeter()/4
     */
    @Override
    public double getPerimeter(){
        perimeter = super.getPerimeter()/4;
        return perimeter;
    }

    /***
     * @return returns the degrees in a semicircle as a double after calling its parent class's getDegrees()/2
     */
    @Override
    public double getDegrees(){
        degrees = super.getDegrees()/2;
        return degrees;
    }


}
