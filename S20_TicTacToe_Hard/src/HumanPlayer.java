import java.util.Scanner;

/***
 * Class used to handle a Player object in the event that it's a human
 * @author Kyle Dorcey
 * @version 1.8
 */
public class HumanPlayer extends Player{
    /**Integer which represents whether the player is player1 or player2**/
    private int playerNumber;
    /**Board object which contains a reference to the state of the current playing board**/
    private Board currentPlayingBoard;
    /**Scanner object used to read in console inputs for moves**/
    private Scanner reader = new Scanner(System.in);

    /***
     * Class constructor. Sets the playerNumber and CurrentPlayingBoard variables
     * @param playerNumber Integer which represents whether the player is player1 or player2
     * @param currentPlayingBoard Board object which contains a reference to the state of the current playing board
     */
    public HumanPlayer(int playerNumber, Board currentPlayingBoard){
        this.playerNumber = playerNumber;
        this.currentPlayingBoard = currentPlayingBoard;
    }

    /***
     * Method used to allow the human player to make a move. The method prompts the user for a x-coordinate
     * and y-coordinate which corresponds with spaces on the tic-tac-toe board. (top left is (1,1), middle is
     * (2,2), bottom right is (3,3), etc.) If the move is valid the turn is over, if it is not valid the user is
     * asked for a different move
     */
    public void makeMove(){
        int xCord=1234;
        int yCord=1234;
        while(!currentPlayingBoard.checkIfValid(xCord,yCord)) {
            System.out.println("Enter move location (the top left position is X=1, Y=1)");
            System.out.print("X: ");
            xCord = Integer.parseInt(reader.nextLine())-1;
            System.out.print("Y: ");
            yCord = Integer.parseInt(reader.nextLine())-1;
            if(currentPlayingBoard.checkIfValid(xCord,yCord)==false){
                System.out.println("Invalid Move");
            }
        }
        if (playerNumber == 1) {
            currentPlayingBoard.setX(xCord, yCord);
        } else if (playerNumber == 2) {
            currentPlayingBoard.setO(xCord, yCord);
        }
        currentPlayingBoard.printBoard();
    }
}
