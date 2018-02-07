import javax.swing.*;

/***
 * Driver class for this program. Calls the constructor for PlayingBoard as well as sets the JFrame's
 * close parameters and size.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class runHangmanGUI {
    public static void main (String args[]) {
        PlayingBoard newGame = new PlayingBoard();
        newGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newGame.setSize(800,500);
        newGame.setVisible(true);

    }

}
