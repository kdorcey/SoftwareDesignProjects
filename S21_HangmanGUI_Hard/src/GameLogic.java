import java.util.ArrayList;

/***
 * Class responsible for handling all logic behind the game (IE. checking if a letter has been guessed, if
 * the user solved the puzzle, etc.)
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class GameLogic {
    /**String containing the word the user will be trying to guess**/
    private String secretWord;
    /**String that will dynamically update as the user guesses elements of the secret word correctly. Each time replacing a "_" with the correct guess**/
    private String secretWordToPrint;
    /**String used to hold all incorrectGuesses**/
    private String incorrectGuesses;
    /**String used to hold every guess**/
    private String allGuesses;
    /**Integer which specifies the maximum amount of incorrect guesses a user can have before they lose**/
    private static int maxIncorrectGuessCount = 6;
    /**Static integer that keeps track of how many incorrect guesses a user has**/
    private static int currentIncorrectGuessCount;
    /**char array containing every correct guess**/
    private char[] correctGuessArray;
    /**boolean that is true when the user wins**/
    private boolean userWins;
    /**boolean that is true when the user loses**/
    private boolean userLoses;
    /**boolean that returns true when neither userWins or userLoses is true**/
    private boolean keepPlaying;

    /***
     * Class constructor, sets the Secret word (as all uppercase letters regardless of user input), calls the static
     * method "createDashedWord()" to set the "secretWordToPrint" variable, allGuesses, currentIncorrectGuessCount to 0,
     * defines the correctGuessArray, sets userWins to false, userLoses to false, and keepPlaying to True.
     * @param secretWord    Word that the user will try to guess
     */
    public GameLogic(String secretWord){
        this.secretWord = secretWord.toUpperCase();
        this.secretWordToPrint = createDashedWord(secretWord);
        this.allGuesses="";

        this.currentIncorrectGuessCount = 0;
        this.incorrectGuesses = "";
        this.correctGuessArray = new char[secretWord.length()];
        this.userWins = false;
        this.userLoses = false;
        this.keepPlaying = true;

    }

    /***
     * @return returns String incorrectGuesses
     */
    public String getIncorrectGuesses(){
        return incorrectGuesses;
    }

    /***
     * @return returns boolean userLoses
     */
    public boolean getUserLoses(){
        return userLoses;
    }
    /***
     * @return returns boolean getUserWins
     */
    public boolean getUserWins(){
        return userWins;
    }
    /***
     * @return returns boolean keepPlaying
     */
    public boolean getKeepPlaying(){
        return keepPlaying;
    }
    /***
     * @return returns string getSecretWordToPrint
     */
    public String getSecretWordToPrint(){
        return secretWordToPrint;
    }

    /***
     * @return returns int getCurrentIncorrectGuessCount
     */
    public static int getCurrentIncorrectGuessCount(){
        return currentIncorrectGuessCount;
    }

    /***
     * @return returns String containing the secretWord
     */
    public String getSecretWord(){
        return secretWord;
    }


    /***
     * Checks whether a user's guess is correct or not.
     * @param guess String containing the user's guess
     * @return  boolean correctGuess, returns true when guess is correct
     */
    public boolean userGuess (String guess){
        boolean correctGuess = false;
        guess = guess.toUpperCase();

        //determines if this letter has been used before.
        if(!allGuesses.contains(guess) && guess.length()<=1) {
            if (secretWord.contains(guess)) {
                correctGuess = true;
                allGuesses += guess;

                ArrayList<Integer> letterLocation = placeInArray(secretWord,guess);
                for(int intHolder:letterLocation){
                    correctGuessArray[intHolder] = guess.charAt(0);
                }
                secretWordToPrint = replaceDashesWithLetter(secretWord, secretWordToPrint, guess);

            }
            else if (!secretWord.contains(guess)) {
                allGuesses += guess;
                incorrectGuesses += guess +" ";
                currentIncorrectGuessCount++;
            }
        }

        updateGameStatus();
        return correctGuess;
    }

    /***
     * Called after the userGuess() method is called. Determines whether the user has won or lost.
     */
    private void updateGameStatus(){
        boolean wordIsCorrect = false;
        int numberOfCorrectGuess=0;

        char[] secretWordArray = secretWord.toCharArray();
        for(int i=0; i<secretWord.length(); i++){
           if(secretWordArray[i] == correctGuessArray[i]){
                numberOfCorrectGuess++;
           }
        }
        if(numberOfCorrectGuess == secretWord.length()) //condition that user wins
        {
            userWins = true;
            keepPlaying = false;
        }
        else if (currentIncorrectGuessCount >= maxIncorrectGuessCount) //condition that user loses
        {
            userLoses = true;
            keepPlaying = false;
        }
    }

    /***
     * Determines whether a user is guessing a letter they have already used
     * @param guess String containing the user's guess
     * @return  boolean that returns false if the user HAS used a letter already
     */
    public boolean repeatCheck (String guess){
        if(!allGuesses.contains(guess.toUpperCase())){
            return true;
        }
        else
        {
            return false;
        }

    }

    /***
     * Returns an ArrayList of Integers to determine the indexes of the secret word that contain a specific letter
     * (ie if the secret word is "shennanigans" the arrayList would contain (3,4,10).
     * @param secretWord String containing the secret word
     * @param guess String containing the user's guess
     * @return  ArrayList containing all index's which the user's guess appears in
     */
    private static ArrayList<Integer> placeInArray (String secretWord, String guess){
        ArrayList<Integer> placeInArray = new ArrayList<>();

        char[] guessToChar = guess.toCharArray();

        for(int i =0; i<secretWord.length(); i++){
            if(secretWord.charAt(i) == guessToChar[0]){
                placeInArray.add(i);
            }
        }

        return placeInArray;
    }

    /***
     * Static method which creates the version of the "SecretWord" that will be displayed. That is
     * for every letter in the secret word it prints "_" followed by a white-space
     * @param secretWord String containing the secret word
     * @return  String containing the "_" version of the word
     */
    private static String createDashedWord(String secretWord){
        String dashes = "";
        int arrayLength = secretWord.length()+(secretWord.length()-1);
        char secretWordDashes[] = new char[arrayLength];

        for(int i=0; i<arrayLength; i++){
            if(i==0 || i%2==0){
                secretWordDashes[i]='_';
            }
            else{
                secretWordDashes[i]=' ';
            }
        }
        for(int i =0; i<arrayLength;i++){
            dashes = dashes+secretWordDashes[i];
        }
        return dashes;
    }

    /***
     * Static method used to dynamically update dashedSecretWord so that it replaces the "_" with a correct
     * letter guess
     * @param secretWord String containing the secret word to guess
     * @param correctGuessWithDash  The current string containing a mixture of "_" and correct guesses
     * @param correctLetter The correctly guessed letter which will replace a (or multiple) "_"
     * @return  returns a String with updated "_" and correct letters which will be displayed to the user
     *          to show progress
     */
    private static String replaceDashesWithLetter(String secretWord, String correctGuessWithDash, String correctLetter) {
        char dashesAsArray[] = correctGuessWithDash.toCharArray();
        correctLetter = correctLetter.toUpperCase();
        char letter = correctLetter.charAt(0);
        ArrayList<Integer> index = new ArrayList<>();

        int indexOfLetter = secretWord.indexOf(letter);
        index.add(indexOfLetter);
        while (indexOfLetter >= 0) {
            indexOfLetter = secretWord.indexOf(correctLetter, indexOfLetter + 1);
            if (indexOfLetter >= 0) {
                index.add(indexOfLetter);
            }
        }

        for (int temp : index) //converts each index of a letter to it's even counterpart. This is so the "_" can be printed with a white space between each
        {
            temp = temp * 2;
            dashesAsArray[temp] = correctLetter.charAt(0);
        }

        String stringToReturn = "";
        for (int i = 0; i < correctGuessWithDash.length(); i++) {
            stringToReturn += dashesAsArray[i];
        }

        return stringToReturn;
    }

    }
