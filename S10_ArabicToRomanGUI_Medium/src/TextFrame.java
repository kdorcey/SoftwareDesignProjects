import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/***
 * The class TextFrame extends JFrame and has a primary function of creating and setting all
 * components within the JFrame.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class TextFrame extends JFrame{
    /**Label displaying prompt telling the user what to do**/
    private final JLabel userPrompt;
    /**JTextField where the user enters roman numerals to convert**/
    private final JTextField romanNumeralTextField;
    /**JTextField that displays the arabic conversion of Roman numerals**/
    private final JTextField arabicTextField;

    /***
     * Constructor of the TextFrame JFrame. This constructor sets the layout for the components and also adds
     * action listeners to the JTextFields.
     */
    public TextFrame(){
        super("Roman Numeral To Arabic Converter");
        setLayout(new FlowLayout());
        UserInputHandler handler = new UserInputHandler();

        userPrompt = new JLabel("Please enter valid Roman Numeral to convert: ");
        add(userPrompt);

        romanNumeralTextField = new JTextField("Roman Numerals", 10);
        romanNumeralTextField.addActionListener(handler);
        add(romanNumeralTextField);

        arabicTextField = new JTextField(10);
        add(arabicTextField);

    }

    /***
     * UserInputHandler is the handler class for he romanNumeralTextField JTextField.
     * The event detects when the user hits the ENTER key. When this is done it calls the convertRomantoArabic
     * method from RomanToArabicLogic and then outputs the result to the arabicTextField.
     */
    private class UserInputHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){

            String romanNums = (event.getActionCommand()).toUpperCase();
            int output = RomanToArabicLogic.convertRomantoArabic(romanNums);
            arabicTextField.setText("Arabic: "+output);

        }

    }




}

