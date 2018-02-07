/***
 * Class Rhombus extends from Parallelogram as it shares multiple traits with the class. The class does require
 * an Override method however as its area is found in a significantly different fashion to other parallelograms.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Rhombus extends Parallelogram {
    /**area of the rhombus**/
    private double area;
    /**length of the diagonal of the rhombus**/
    private double diagonal;
    /**length of the second diagonal of the rhombus**/
    private double diagonal2;

    /***
     * Class constructor for Rhombus
     * @param shapeName     String containing the user defined name of the shape
     * @param base          length of the base
     * @param base2         Length of the side opposite to base
     * @param side          Length of the side
     * @param side2         Length of the side opposite of "side"
     * @param diagonal      Length of one of the rhombus's diagonals
     * @param diagnola2     Length of the other diagnola
     */
    public Rhombus (String shapeName, double base, double base2, double side, double side2, double diagonal, double diagnola2){
        super(shapeName, base,side, base2, side2);
        this.diagonal = diagonal;
        this.diagonal2 = diagnola2;
        this.area = setArea(diagonal, diagonal2);
    }

    /***
     * Finds area of the rhombus. The method is static as it is called during construction
     * @param diagonal      Length of one of the rhombus's diagonals
     * @param diagonal2     Length of the other diagonal
     * @return              Area of the rhombus
     */
    private static double setArea(double diagonal, double diagonal2){
        double area = .5*diagonal*diagonal2;
        return area;
    }

    /***
     * @return the area of the rhombus. Overriden so as to not accidentally call Parallelograms method
     */
    @Override
    public double getArea() {
        return this.area;
    }

    /***
     * @return returns height as a double
     */
    @Override
    public double getHeight(){
        return getHeight();
    }

    /***
     * @return returns height2 as a double
     */
    @Override
    public double getHeight2(){
        return getHeight2();
    }

    /***
     * @return returns diagonal as a double
     */
    public double getDiagonal() {
        return diagonal;
    }

    /***
     * @return returns diagonal2 as a double
     */
    public double getDiagonal2() {
        return diagonal2;
    }





}
