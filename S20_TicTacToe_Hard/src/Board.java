/***
 * Class responsible for creating, and keeping track of all X's and O's on the playing board.
 * The class is also responsible for checking for valid moves.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Board {
    /**2D string array which holds X's and O's**/
    private String currentBoard[][];
    /**String representation of an "X" placed whenever player one makes a move**/
    private final String X = "X";
    /**String representation of an "O" Placed in the currentBoard array whenever player 2 makes a move**/
    private final String O = "O";
    /**String that will contain the name of the winner (either "player 1" or "player 2")**/
    private String winnersName;

    /***
     * Class constructor. Responsible for defining the currentBoard as a 3x3 array
     * and setting each square with it's default value "-"
     */
    public Board(){
        currentBoard = new String[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                currentBoard[j][i] = "-";
            }
        }
    }

    /***
     * Called whenever player 1 makes a move. Sets the selected x and y coordinate with an X. Checks for a winner
     * whenever called
     * @param xCord x-coordinate on the playing board to set to an X
     * @param yCord y-coordinate on the playing board to set to a Y
     */
    public void setX(int xCord, int yCord){
        if(checkIfValid(xCord,yCord)){
            currentBoard[xCord][yCord] = X;
            checkForWinner();
        }

    }

    /***
     * Called whenever player 2 makes a move. Sets the selected x and y coordinate with an O. Checks for a winner
     * whenever called.
     * @param xCord x-coordinate on the playing board to set to an O
     * @param yCord y-coordinate on the playing board to set to a O
     */
    public void setO(int xCord, int yCord){
        if(checkIfValid(xCord,yCord)) {
            currentBoard[xCord][yCord] = O;
            checkForWinner();
        }
    }

    /***
     * Determines off of the x-coordinate and y-coordinate whether a move is valid (an invalid move is a move that has
     * either already been made, or that isn't within the playing board).
     * @param xCord x-coordinate to check
     * @param yCord y-coordinate to check
     * @return boolean valid as true when the move is valid
     */
    public boolean checkIfValid(int xCord, int yCord){
        boolean valid = false;
        if(xCord>=0 && xCord<=2 && yCord>=0 && yCord<=2) {
            if (currentBoard[xCord][yCord].equals("-")) {
                valid = true;
            }
        }
        return valid;
    }

    /***
     * Prints the current board, with both X's and O's
     */
    public void printBoard(){
        System.out.println("~~~~~~~~~~~~~~");
        for(int i=0; i<3; i++){
            if(i!=0) {
                System.out.println();
            }
            for(int j=0; j<3; j++){
                System.out.print(currentBoard[j][i]);
            }
        }
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~");
    }

    /***
     * Checks if any win conditions are met. If any are, that player (player 1 for "x"s, player 2 for "o"s is
     * the winner
     * @return boolean winner, returns true when a winner is found.
     */
    public boolean checkForWinner(){
        boolean winner=false;

        if(currentBoard[0][0].equals("X") && currentBoard[1][1].equals("X") && currentBoard[2][2].equals("X")){

            winnersName = "X";
            winner=true;
        }
        else if(currentBoard[0][2].equals("X") && currentBoard[1][1].equals("X") && currentBoard[2][0].equals("X")){
            winnersName = "X";
            winner=true;

        }
        else if(currentBoard[0][0].equals("X") && currentBoard[1][0].equals("X") && currentBoard[2][0].equals("X")){
            winnersName = "X";
            winner=true;
        }
        else if(currentBoard[0][1].equals("X") && currentBoard[1][1].equals("X") && currentBoard[2][1].equals("X")){
            winnersName = "X";
            winner=true;
        }
        else if(currentBoard[0][2].equals("X") && currentBoard[1][2].equals("X") && currentBoard[2][2].equals("X")){
            winnersName = "X";
            winner=true;
        }
        else if(currentBoard[0][0].equals("X") && currentBoard[0][1].equals("X") && currentBoard[0][2].equals("X")){
            winnersName = "X";
            winner=true;
        }
        else if(currentBoard[1][0].equals("X") && currentBoard[1][1].equals("X") && currentBoard[1][2].equals("X")){
            winnersName = "X";
            winner=true;
        }
        else if(currentBoard[2][0].equals("X") && currentBoard[2][1].equals("X") && currentBoard[2][2].equals("X")){
            winnersName = "X";
            winner=true;
        }
        else if(currentBoard[0][0].equals("O") && currentBoard[1][1].equals("O") && currentBoard[2][2].equals("O")){
            winnersName = "O";
            winner=true;
        }
        else if(currentBoard[0][2].equals("O") && currentBoard[1][1].equals("O") && currentBoard[2][0].equals("O")){
            winnersName = "O";
            winner=true;

        }
        else if(currentBoard[0][0].equals("O") && currentBoard[1][0].equals("O") && currentBoard[2][0].equals("O")){
            winnersName = "O";
            winner=true;
        }
        else if(currentBoard[0][1].equals("O") && currentBoard[1][1].equals("O") && currentBoard[2][1].equals("O")){
            winnersName = "O";
            winner=true;
        }
        else if(currentBoard[0][2].equals("O") && currentBoard[1][2].equals("O") && currentBoard[2][2].equals("O")){
            winnersName = "O";
            winner=true;
        }
        else if(currentBoard[0][0].equals("O") && currentBoard[0][1].equals("O") && currentBoard[0][2].equals("O")){
            winnersName = "O";
            winner=true;
        }
        else if(currentBoard[1][0].equals("O") && currentBoard[1][1].equals("O") && currentBoard[1][2].equals("O")){
            winnersName = "O";
            winner=true;
        }
        else if(currentBoard[2][0].equals("O") && currentBoard[2][1].equals("O") && currentBoard[2][2].equals("O")){
            winnersName = "O";
            winner=true;
        }

        if(winner==true){
            System.out.println("GAME OVER, "+winnersName+" wins!");

        }
        return winner;
    }
}
