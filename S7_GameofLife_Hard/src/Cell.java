import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/***
 * The Cell class is an extension of JPanel as every Cell is it's own JPanel.
 * Alive cells are yellow in color and dead cells are a light gray. Each cell has a flag counter that starts 0 and
 * increases every time the flag. Each Cell also contains MouseHandler used to kill or bring cells to life by clicking
 * on them.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Cell extends JPanel {

    /**x-Coordinant of selected Cell **/
    int xCord;
    /**y-Coordinant of selected Cell **/
    int yCord;
    /**integer value used to count the amount of flags a cell has and kill them/bring them to life based on it **/
    int flag; //used to count how many active cells are touching it
    /**boolean returns true if the cell is "alive" (yellow) or false if the cell is "dead" (light gray)**/
    boolean cellAlive;

    /**
     * Constructor sets each cell at their default state (dead) with a flag count of 0. It also
     * adds a mouse listeners to each JPanel to detect when a cell is clicked.
     * @param xCord     x-coordinate of the selected cell
     * @param yCord     y-coordinate of the selected cell
     */
    public Cell(int xCord, int yCord){
        setBackground(Color.LIGHT_GRAY); //all cells are initially "dead" or light gray in color
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.xCord = xCord;
        this.yCord = yCord;
        this.cellAlive = false;
        this.flag =0;

        this.addMouseListener(new MouseHandler());
    }

    /***
     * @return returns the x-coordinate in the form of an integer
     */
    public int getxCord() {
        return xCord;
    }

    /***
     * @return returns the y-coordinate in the form of an integer
     */
    public int getyCord() {
        return yCord;
    }

    /***
     * @return returns the cellAlive boolean (true if cell is alive, false otherwise)
     */
    public boolean isCellAlive() {
        return cellAlive;
    }
    /***
     * Increases the flag integer value by one
     */
    public void flagThisCell(){
        this.flag++;
    }

    /***
     * Sets the flag back to 0
     */
    public void resetFlags(){
        this.flag=0;
    }

    /***
     * Converts a cells flag value to either kill or bring this cell to life.
     */
    public void convertFlagsToLife(){
        if(cellAlive) {
            if(flag<2){
                killCell();
            }
            else if(flag>3){
                killCell();
            }
            else if(flag ==2 || flag ==3){
                aliveCell();
            }
        }
        else if (!cellAlive) {
            if(flag==3){
                aliveCell();
            }
        }
    }

    /***
     * Changes this cell object to it's alive state
     */
    public void aliveCell(){
        cellAlive = true;
        setBackground(Color.YELLOW);
    }

    /***
     * Changes this cell object to it's dead state
     */
    public void killCell(){
        cellAlive = false;
        setBackground(Color.lightGray);
    }

    /***
     * MouseHandler which extends mouse adapter. It's purpose is to detect mouse clicks on Cell
     * a mousePressed MouseEvent on a dead cell brings the cell to life and vice-versa.
     */
    private class MouseHandler extends MouseAdapter {
        @Override
        public synchronized void mousePressed(MouseEvent event) {
            checkForTriggerEvent();
        }

        public void checkForTriggerEvent() {
            if (cellAlive == true) {
                killCell();
            }
            else if (cellAlive == false) {
                aliveCell();
            }
        }
    }

}
