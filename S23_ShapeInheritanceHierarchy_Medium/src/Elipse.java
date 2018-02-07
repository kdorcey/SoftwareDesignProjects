/***
 * The class Elipse contains all required variables and methods to compute information about itself
 * as well as it's child-classes. It extends TwoDimensionalShapes as it shares traits (such as having no volume or
 * surface area) as other TwoDimensionalShapes.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Elipse extends TwoDimensionalShape {
    /**Larger radius of an elipse**/
    private double bigRadius;
    /**Smaller radius of an elipse**/
    private double smallRadius;
    /**perimeter of an elipse**/
    private double perimeter;
    /**area of an elipse**/
    private double area;
    /**Degrees in an elipse**/
    private final int angleInDegrees =360;

    /***
     * Class constructor for Elipse. Passes shapeName to it's parent class. Checks first and second radius to
     * determine which is larger and should be saved as bigRadius or smallRadius.
     * @param shapeName     String containing the user selected shape name.
     * @param firstRadius   One radius of the elipse.
     * @param secondRadius  Second radius of the elipse.
     */
    public Elipse (String shapeName, double firstRadius, double secondRadius){
        super(shapeName);

        if(firstRadius >secondRadius){
            bigRadius = firstRadius;
            smallRadius =secondRadius;
        }
        else if (secondRadius > firstRadius){
            bigRadius = secondRadius;
            smallRadius = firstRadius;
        }
        //sets both equal to one another in the event that the user accidentally enters a circle as an elipse
        else{
            bigRadius = firstRadius;
            smallRadius = firstRadius;
        }
        this.perimeter = findPerimeter( bigRadius, smallRadius);
        this.area = findArea(bigRadius,smallRadius);

    }

    /***
     * Overload version of the Elipse constructor used primarily for when an elipse is actually a circle.
     * @param shapeName     String containing the user selected shape name.
     * @param firstRadius   radius of the elipse/circle.
     */
    public Elipse (String shapeName, double firstRadius){
        this(shapeName, firstRadius, firstRadius);
    }

    /***
     * Finds the circumference of the elipse. Method is static so it can be called during class construction.
     * Due to the nature of elipses the perimeter can only be accurate within 5% using this method.
     * @param bigRadius     Double with the size of the largest radius of the elipse
     * @param smallRadius   Double with the smaller radius of the elipse
     * @return              Returns the circumference of the elipse as a double.
     */
    private static double findPerimeter(double bigRadius, double smallRadius){
        double perimeter = 2*getPi()*(Math.sqrt(((Math.pow(bigRadius,2)+Math.pow(smallRadius,2)))/2)); //accurate within 5%
        return perimeter;
    }

    /***
     * Finds the area of an elipse. Method is static so it can be called during class construction.
     * @param bigRadius     Double with the size of the largest radius of the elipse
     * @param smallRadius   Double with the smaller radius of the elipse
     * @return              returns the area of a circle as a double.
     */
    private static double findArea(double bigRadius, double smallRadius){
        double area;
        area = getPi()*bigRadius*smallRadius;
        return area;
    }


    /***
     * @return returns perimeter as a double
     */
    public double getPerimeter(){return perimeter;}
    /***
     * @return returns larger radius as a double
     */
    public double getBigRadius(){
        return bigRadius;
    }
    /***
     * @return returns area as a double
     */
    public double getArea(){return area;}
    /***
     * @return returns the smaller radius as a double
     */
    public double getSmallRadius(){
        return smallRadius;
    }
    /***
     * @return returns degrees in an elipse as a double
     */
    public double getDegrees(){return angleInDegrees;}

}
