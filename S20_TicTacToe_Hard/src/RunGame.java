import java.util.Scanner;

/***
 * Driver class for the program, responsible for handling all user inputs and outputs
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class RunGame {
    public static void main(String[]args){
        boolean playAgain=true;

        while (playAgain)//playAgain is false in the event that the user wins/loses and selects "do not play again"
        {
            Scanner reader = new Scanner(System.in);
            Board board = new Board();

            System.out.println("Player Select");
            System.out.println("-------------");
            System.out.println("Select Player One (X): \n1 - Human\n2 - Computer");

            int userChoice = Integer.parseInt(reader.nextLine());

            Player player1;
            if(userChoice ==1){
                 player1 = new HumanPlayer(1, board);
            }
            else {
                 player1 = new ComputerPlayer(1, board);
            }

            System.out.println("-------------");
            System.out.println("Select Player Two (O): \n1 - Human\n2 - Computer");

            userChoice = Integer.parseInt(reader.nextLine());

            Player player2;
            if (userChoice == 1) {
                player2 = new HumanPlayer(2, board);
            } else {
                player2 = new ComputerPlayer(2, board);
            }

            while (true) //Loops until either playre 1 or player 2 wins
            {
                player1.makeMove();
                if (board.checkForWinner()) {
                    break;
                }

                try //creates a pause between moves when game is played between two computers
                {
                    Thread.sleep(700);
                }
                catch (InterruptedException e) {
                    System.out.println("Robots game got interrupted "+e);
                }

                player2.makeMove();
                if (board.checkForWinner()) {
                    break;
                }
            }
            System.out.println("Play again? \n1 - Yes\n2 - No");
            int replayValue = Integer.parseInt(reader.nextLine());

            if(replayValue ==1){
                playAgain = true;
            }
            else if(replayValue ==2){
                playAgain = false;
            }
        }



    }
}
