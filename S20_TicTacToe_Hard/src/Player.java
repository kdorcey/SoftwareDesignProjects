/***
 * Abstract player class which both ComputerPlayer and HumanPlayer implement. The only
 * method they are required to use is the "makeMove()" method.
 * @author Kyle Dorcey
 * @version 1.8
 */
public abstract class Player  {
    /**
     * Abstract method that all classes implementing Player must use. The method handles either a computer
     * or human making moves in the game
     */
    public abstract void makeMove();
}
