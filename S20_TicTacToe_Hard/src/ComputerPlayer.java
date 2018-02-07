import java.util.Random;

/***
 * Class which is used in the event that a player is a computer
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class ComputerPlayer extends Player {
    /**integer representing whether the player is player1 or player2**/
    int playerNumber;
    /**Reference to the current Board object**/
    Board currentPlayingBoard;

    /***
     * Class constructor which sets playerNumber and currentPlayingBoard
     * @param playerNumber integer representing whether the player is player1 or player2
     * @param currentPlayingBoard Reference to the current Board object
     */
    public ComputerPlayer(int playerNumber, Board currentPlayingBoard){
        this.playerNumber = playerNumber;
        this.currentPlayingBoard = currentPlayingBoard;
    }

    /***
     * Implemented method from the abstract Player class. Uses a random number generator to decide
     * where it should make its next move. If the random number generator produces a valid move, the AI
     * makes the move. Otherwise it runs the random number generator until such a condition is met. This is
     * not a particularity smart opponent.
     */
    public void makeMove(){
        Random rand = new Random();
        int xCord = 1123;
        int yCord = 1234;

        while(!currentPlayingBoard.checkIfValid(xCord,yCord)){
            xCord = rand.nextInt(3);
            yCord = rand.nextInt(3);

        }
        if(playerNumber==1){
            currentPlayingBoard.setX(xCord,yCord);
        }
        else if(playerNumber==2){
            currentPlayingBoard.setO(xCord,yCord);
        }
        currentPlayingBoard.printBoard();
    }
}
