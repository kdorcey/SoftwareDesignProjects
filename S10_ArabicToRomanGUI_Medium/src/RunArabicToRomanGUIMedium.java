import javax.swing.JFrame;

/***
 * Driver class for the S10_ArabicToRomanGUI_Medium module. Calls the TextFrame constructor
 * as well as sets the parameters for the JFrame that holds the class.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class RunArabicToRomanGUIMedium {
    public static void main (String args[]){

        TextFrame labelFrame = new TextFrame();
        labelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        labelFrame.setSize(350,150);
        labelFrame.setVisible(true);


    }
}
