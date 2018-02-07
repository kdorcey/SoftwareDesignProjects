import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/***
 * The PlayingBoard class is an extension of JFrame and holds and creates all GUI components of the game.
 * It also creates Cell objects for every part of th grid
 *
 * @author Kyle Dorcey
 * @version 1.8
 * @see CellChecker
 */
public class PlayingBoard extends JFrame {
    /** Button used to start simulation **/
    private final JButton startSim; //Button used to start simulation
    /** Button used to pause simulation**/
    private final JButton pauseSim; //Button used to pause the simulation
    /** Button used to clear the board and reset all cellflags **/
    private final JButton clearBoard; //disables all cells to create a clear board
    /** Button used to call static CellChecker method to create a Gosper Glider Gun starting seed**/
    private final JButton gunConfig; //button to create the Gosper Glider Gun configuration
    /** Timer object used to repeat startSim button presses allowing the simulation to run continuously**/
    private final Timer timer;
    /** Final integer value in milliseconds used to set the delay between startSim JButton fires **/
    private final int timerDelay= 200;
    /** Value used to set the amount of cells on the board. Here cellCount=50 will create a board 50x50 cells**/
    final int  cellCount = 50; //used to determine size of the board (in this case it will be 75x75).
    /** 2D array of Cell objects **/
    private Cell[][] cells;

    /***
     * Constructor used to generate the playing board "pieces". The constructor first defines the size of the Cell 2D array
     * then it defines each JButton, adds action listeners to each JButton, and it also initializes the Timer.
     * After this it calls the createBoard() method to create the visible GUI.
     */
    public PlayingBoard() {
        super("Game of Life");

        cells = new Cell[cellCount][cellCount]; //creates a cell object for ever board piece.

        startSim = new JButton("Start Sim", null);
        pauseSim = new JButton("Pause Sim", null);
        clearBoard = new JButton("Clear Board", null);
        gunConfig = new JButton("Gosper's Glider Gun ",null);

        startSim.addActionListener(new OnButtonHandler()); //startSim button does not have an anonymous inner class as it's handler is used by timer as well
        timer = new Timer(timerDelay, new OnButtonHandler());

        pauseSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        clearBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                CellChecker.clearBoard(cellCount,cells);
            }
        });
        gunConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                CellChecker.createGliderGun(cellCount, cells);
            }
        });

        createBoard();
    }

    /***
     * This method creates three JPanels gameGrid, buttonPanel, and gamePanel. The gameGrid JPanel holds all
     * Cell objects, the buttonPanel holds all JButtons, and the gamePanel JPanel holds the previous two panels.
     * As the JPanels are used exclusively to hold other containers they are not initialized as instance variables.
     */
    private void createBoard() {

        JPanel gameGrid = new JPanel(new GridLayout(cellCount, cellCount)); //dimensions of the JPanels that hold all of the cells. Must be the same as the cellCount for square cells
        JPanel buttonPanel = new JPanel(new FlowLayout());  //Panel at bottom of JFrame that holds JButtons for the user
        JPanel gamePanel = new JPanel(new BorderLayout()); //Holds both buttonPanel and gameGrid

        buttonPanel.add(startSim);
        buttonPanel.add(pauseSim);
        buttonPanel.add(clearBoard);
        buttonPanel.add(gunConfig);

        //creates all cell Objects
        for (int i = 0; i < cellCount; i++) {
            for (int j = 0; j < cellCount; j++) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].setVisible(true);
                gameGrid.add(cells[i][j]);
            }
        }

        gamePanel.add(gameGrid, BorderLayout.CENTER);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(gamePanel); //adds the gamePanel to the JFrame
    }

    /***
     * Toggle cells calls the static CellChecker methods of checkNeighbors and flagConverter in order to flag
     * cells and convert those flags to alive or dead cells.
     */
    public void toggleCells(){
        CellChecker.checkNeighbors(cellCount, cells);
        CellChecker.flagConverter(cellCount, cells);
    }

    /***
     * OnButtonHandler acts as the handler class for the startSim JButton as well as the timer
     */
    private class OnButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            timer.setDelay(250);
            timer.start();
            toggleCells();

        }
    }
}


