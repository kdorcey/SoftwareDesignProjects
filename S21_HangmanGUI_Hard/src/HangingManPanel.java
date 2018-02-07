import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

/***
 * This class is an extension of Swing's JPanel. It represents a JPanel which will display the hangman
 * and draw a new body part every time the user guesses incorrectly
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class HangingManPanel extends JPanel {
    /**Object reference to the GameLogic class. Used primarily to find how many incorrect guesses the user has made**/
    private GameLogic currentGame;
    /**Background color the JPanel**/
    private final Color backgroundColor = Color.LIGHT_GRAY;
    /**Height of the JPanel**/
    private final int height = 300;
    /**Width of the JPanel**/
    private final int width = 400;

    /***
     * Class constructor. Sets the JPanels background color, the border color, and the JPanels dimensions
     */
    public HangingManPanel(){
        setBackground(backgroundColor);
        setBorder(BorderFactory.createLineBorder(Color.black));
        Dimension panelDimensions = new Dimension(width,height);

        setPreferredSize(panelDimensions);
    }

    /***
     * Method used to paint the hangman Directly onto the JPanel. It will draw a new part of the hangman each time
     * the user guesses incorrectly
     * @param g graphics object reference
     */
    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        g.drawLine(200, 50, 350, 50); //horizontal top

        g.drawLine(200, 50, 200, 75); //head holder

        g.drawLine(325,50,350,75); //cross bar

        g.drawLine(350, 50,350, 400); //vertical bar

        g.drawLine(300,400,400,400); //stand

        if(GameLogic.getCurrentIncorrectGuessCount() >=1) {
            g.drawOval(150, 75, 100, 100); //head
        }
        if(GameLogic.getCurrentIncorrectGuessCount() >=2) {

            g.drawLine(200, 175, 200, 250); //body
        }
        if(GameLogic.getCurrentIncorrectGuessCount() >=3) {
            g.drawLine(200, 190, 230, 200);//right arm
        }
        if(GameLogic.getCurrentIncorrectGuessCount() >=4) {
            g.drawLine(200, 190, 170, 200);//left arm
        }
        if(GameLogic.getCurrentIncorrectGuessCount()>=5) {
            g.drawLine(200, 250, 220, 270);//right leg
        }
        if(GameLogic.getCurrentIncorrectGuessCount()>=6) {
            g.drawLine(200, 250, 180, 270);//left leg
        }


    }
}
