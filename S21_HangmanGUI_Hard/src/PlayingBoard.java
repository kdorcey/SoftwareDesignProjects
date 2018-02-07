import javax.swing.*;
import java.awt.*;

/***
 * This class is an extension of Swing's JFrame component. It calls and creates the hangManPanel and userInputPanel
 * adding both on top of itself.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class PlayingBoard extends JFrame {

    /**panel that contains the hangman drawing**/
    private final JPanel hangmanPanel;
    /**panel that contains all components required to handle user input**/
    private final JPanel userInputPanel;

    /***
     * Constructor for the playingBoard JFRame. Constructs both the HangingManPanel and UserInputPanel JPanels
     */
    public PlayingBoard(){
        super("Hangman!");
        setLayout(new BorderLayout());
        hangmanPanel = new HangingManPanel();
        userInputPanel = new UserInputPanel();
        setBoard();
    }

    /***
     * Adds the JPanels defined in the constructor to the JFrame with their respective border alignment
     */
    private void setBoard(){
        add(userInputPanel, BorderLayout.WEST);
        add(hangmanPanel, BorderLayout.EAST);

    }



}
