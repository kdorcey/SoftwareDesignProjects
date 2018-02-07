import javax.swing.*;
import java.awt.*;

/***
 * PlayGameOfLife is the driver cass for the S7_GameofLife_Hard project. It is responsible for calling the
 * PlayingBoard object constructor as well as setting the parameters for the JFrame which contains the game.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class PlayGameOfLife {
    public static void main (String args[]){
        System.setProperty("sun.java2d.opengl","true"); //Video acceleration is off by default on java. Adding this line enables it and stops the simulation from running jaggedly

        PlayingBoard playingBoard = new PlayingBoard();
        playingBoard.setSize(1000,1000);
        playingBoard.setVisible(true);
        playingBoard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
