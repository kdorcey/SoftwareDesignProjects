import java.lang.Math;
import javax.swing.*;
import java.awt.*;

/***
 * Parent class to entire module. Class must be constructed individually or through the
 * "super" notation before using child classes.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Shapes {
    /**Color of the shape**/
    private Color shapeColor;
    /**Boolean which returns true if an object is a shape**/
    private boolean isShape= false;
    /**String containing the name of a shape**/
    private String shapeName;
    /**constant value of pi**/
    private static final double pi = 3.14;

    /***
     * Constructor of the Shapes class. if called, sets isShape = true;
     * @param shapeName         String containing the user chosen name of a shape
     * @param selectedColor     Color of shape
     */
    public Shapes(String shapeName, Color selectedColor){
        this.shapeName =shapeName;
        this.shapeColor =selectedColor;
        isShape = true;
    }
    /****
     * Constructor for the Shapes class when no specific color is chosen.
     * The default color is black.
     * @param shapeName     String containing user selected name of shape
     */
    public Shapes(String shapeName){
        this(shapeName,Color.BLACK);
    }

    /***
     * Method used to change the color of a shape object
     * @param newColor  Color to change shape to
     */
    public void changeColor(Color newColor){
        this.shapeColor = newColor;
    }

    /***
     * @return returns color of shape as a String
     */
    public String getShapeColor() {return shapeColor.toString();}

    /***
     * @return returns the user chosen name of a shape as a string
     */
    public String name (){
        return shapeName;
    }

    /***
     * @return returns the boolean isShape
     */
    public boolean isShapeCheck(){return isShape;}

    /***
     * @return returns the pi constant as a double
     */
    public static double getPi(){
        return pi;
    }

}
