import java.awt.*;

/***
 * Class Square, the class extends directly from Rectangle as it shares every method and variable with Rectangle.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Square extends Rectangle {
    /***
     * Class constructor for Square. Only requires one side length as a square is composed of 4 sides of equal lengths.
     * Passes shapeName and base to it's parent class.
     * @param shapeName     String containing the user selected name of the shape
     * @param base          length of one side of the triangle
     */
    public Square (String shapeName, double base){
        super(shapeName,base,base);
    }
}
