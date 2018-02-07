import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * This class is an extension of JPanel and is responsible for handling all user interactions with the program
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class UserInputPanel extends JPanel {

    /**JPasswordField object reference which is where the user will enter the secret word**/
    private final JPasswordField secretWord;
    /**JTextField used for the user to guess the word**/
    private final JTextField guess;
    /**JLabel used to prompt the user where to enter the secret word **/
    private final JLabel passwordLabel;
    /**Jlabel used to show the user where to enter their guesses **/
    private final JLabel guessLabel;
    /**JLabel used to show which letters the user has already guessed**/
    private final JLabel usedLetters;
    /**JLabel used to show the amount of remaining guesses**/
    private final JLabel remainingGuesses;
    /**JLabel used to show how many letters the secret word. This updates every time the user makes a correct guess**/
    private final JLabel secretWordWithDashes;
    /**Object reference to the GameLogic class**/
    private GameLogic newHangman;

    /***
     * Class constructor, used to define, passwordLabel, guessLabel, guess, usedLetters, secretWordWithDashes,
     * remainingGuesses, and secretWord. The Constructor also adds anonymous inner class action listeners to
     * secretWord in order to read in the user's "secret word", and one for "guess" for the same purpose.
     */
    public UserInputPanel(){
        this.setLayout(new BorderLayout(10,0));

        passwordLabel = new JLabel("Enter Secret Word: ");
        guessLabel = new JLabel("Guess: ");
        guess = new JTextField(5);
        usedLetters = new JLabel("Used Letters: ");
        secretWordWithDashes = new JLabel("______");
        remainingGuesses = new JLabel("Remaining Guesses: 6");

        secretWord = new JPasswordField(20);

        secretWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                newHangman = new GameLogic(event.getActionCommand());
                secretWordWithDashes.setText(newHangman.getSecretWordToPrint());
                secretWord.setEditable(false);}
            }
        );

        guess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                if (event.getActionCommand().length() == 1 && newHangman.repeatCheck(event.getActionCommand())) {
                    boolean check = newHangman.userGuess(event.getActionCommand());

                    if (!check && event.getActionCommand().length() <= 1) {
                        usedLetters.setText("Used Letters: " + newHangman.getIncorrectGuesses());

                        UserInputPanel.super.setSize(800, 500); //forces the program to redraw the different JPanels and update the hangman

                    }
                    guess.setText("");

                    secretWordWithDashes.setText(newHangman.getSecretWordToPrint());

                    remainingGuesses.setText("Remaining Guesses: "+(6-GameLogic.getCurrentIncorrectGuessCount()));

                    checkGame();


                }
            }
        });

        putTogether();
    }

    /***
     * Places all components onto the JPanel
     */
    public void putTogether (){
        JPanel secretWordPanel = new JPanel(new FlowLayout());
        JPanel guessWordPanel = new JPanel(new FlowLayout());
        JPanel wordWithDashes = new JPanel(new FlowLayout());

        secretWordPanel.add(passwordLabel);
        secretWordPanel.add(secretWord);

        guessWordPanel.add(guessLabel);
        guessWordPanel.add(guess);
        guessWordPanel.add(usedLetters);

        wordWithDashes.add(secretWordWithDashes);
        wordWithDashes.add(remainingGuesses);

        this.add(secretWordPanel, BorderLayout.NORTH);
        this.add(guessWordPanel);
        this.add(wordWithDashes, BorderLayout.SOUTH);


    }

    /***
     * Checks whether the user has won or not
     */
    private void checkGame(){
        if(!newHangman.getKeepPlaying()){
            if(newHangman.getUserWins()){
                JOptionPane.showMessageDialog(this, "You Win!");
            }
            else if (newHangman.getUserLoses()){
                JOptionPane.showMessageDialog(this, "You Lose! The correct word was: "+newHangman.getSecretWord());
            }
        }
    }




}
