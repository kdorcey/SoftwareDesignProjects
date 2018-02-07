import javafx.fxml.FXML;

/***
 * The Maze class is responsible for creating the maze as well as changing it based
 * on inputs from the MazeGuy class
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class Maze {
    /**2D array of strings containing the maze**/
    private String[][] drawnMaze;
    /**2d array of strings that keeps track of where X's are placed**/
    private String[][] xMarkers;
    /**x location of the entrance**/
    private int entranceXLocation = 0;
    /**y location of the entrance**/
    private int entranceYLocation = 2;
    /**x location of the maze's exit**/
    private final int exitXLocation = 24;
    /**y location of the maze's exit**/
    private final int exitYLocation = 4;
    /**number of rows**/
    private static final int rows = 12;
    /**number of columns, every other column is a white space in order to make the maze easier to read**/
    private static final int columns =24;
    /**returns true when maze is complete**/
    private boolean mazeComplete;

    /***
     *Constructor for the Maze class, calls the static method createMaze() and defineXMarkers() which defines and creates
     *the maze and xMarkers arrays. It also sets the mazeComplete boolean to false.
     */
    public Maze(){
        xMarkers = defineXMarkers();
        drawnMaze = createMaze();
        mazeComplete = false;
    }

    /***
     * Returns x-location of the maze's entrance
     * @return entranceXLocation
     */
    public int getInitialX() {
        return entranceXLocation;
    }

    /***
     * returns y-location of the maze's entrance
     * @return entranceYLocation
     */
    public int getInitialY(){
        return entranceYLocation;
    }

    /***
     * returns the 2D String array containing the maze
     * @return drawnMaze
     */
    public String[][] getMazeMap(){
        return drawnMaze;
    }

    /***
     * returns the x-location of the maze's exit
     * @return exitXLocation
     */
    public int getExitXLocation(){
        return exitXLocation;
    }

    /***
     * returns the y-location of the maze's exit
     * @return exitYLocation
     */
    public int getEntranceYLocation(){
        return exitYLocation;
    }

    /***
     * returns the amount of columns in the maze
     * @return columns
     */
    public int getColumns(){
        return columns;
    }

    /***
     * returns the amount of rows in the maze
     * @return rows
     */
    public int getRows(){
        return rows;
    }


    /***
     * Called by the MazeGuy class, updates the mazeGuy's position in the maze
     * @param xCoord current x-position in the maze
     * @param yCoord current y-position in the maze
     * @param currentLocationMarker marker used to represent the current position in the maze
     */
    public void updateManLocation(int xCoord, int yCoord, String currentLocationMarker){
        drawnMaze[xCoord][yCoord] = currentLocationMarker;
        printMaze();
    }

    /***
     * Checks if the maze has been completed
     * @param nextXCoord the next x-position that the mazeGuy will move to
     * @param nextYCoord the next y-position that the MazeGuy will move to
     * @return returns true if next move will result in the maze being complete
     */
    public boolean checkMazeComplete(int nextXCoord, int nextYCoord){
        if(nextXCoord == exitXLocation && nextYCoord == exitYLocation){
            mazeComplete = true;
        }
        return mazeComplete;
    }

    /***
     * Marks a trail of "bread crumbs" ("x"s) to represent the path that the MazeGuy has taken through the maze
     * if a cell is marked more than once it will switch to an "O", and if an "O" is marked again back to an "x".
     * @param xCoord x-position to mark
     * @param yCoord y-position to mark
     * @param breadCrumb String representation of what the breadCrumbs look like
     */
    public void breadCrumbs(int xCoord, int yCoord, String breadCrumb){
        if(xMarkers[xCoord][yCoord].equals("x")){
            drawnMaze[xCoord][yCoord] = "o";
            xMarkers[xCoord][yCoord] = "o";
        }
        else {
            drawnMaze[xCoord][yCoord] = breadCrumb;
            xMarkers[xCoord][yCoord] = breadCrumb;
        }
    }

    /***
     * Verifies that the move MazeGuy is trying to make is within the bounds of the maze, and not into a maze border
     * @param xDirectionToCheck x-position to check
     * @param yDirectionToCheck y-position to check
     * @return validMove true if move is valid, otherwise returns false.
     */
    public boolean validMoveCheck(int xDirectionToCheck, int yDirectionToCheck){
        boolean validMove = false;
        if(xDirectionToCheck>=2 && xDirectionToCheck<columns && yDirectionToCheck>=1 && yDirectionToCheck<rows ){
            if(!drawnMaze[xDirectionToCheck][yDirectionToCheck].equals("#")){
                validMove =true;
            }
        }
        return validMove;
    }

    /***
     * When the MazeGuy's method retraceSteps() is being called, it uses this method to determine
     * whether it should continue to follow only "x" (breadCrumb) marked spots or not. When this method
     * returns true, that method will exit.
     * @param currentXLocation  current x-location
     * @param currentYLocation  current y-location
     * @return  boolean exitCheck. Returns true if there is a spot immediately next to the current x-y location
     *          that is a "."
     */
    public boolean retraceExitCheck(int currentXLocation, int currentYLocation){
        boolean exitCheck =false;

        if(drawnMaze[currentXLocation-2][currentYLocation].equals(".") || drawnMaze[currentXLocation+2][currentYLocation].equals(".") || drawnMaze[currentXLocation][currentYLocation-1].equals(".")|| drawnMaze[currentXLocation][currentYLocation+1].equals(".")){
            exitCheck=true;
        }

        return exitCheck;
    }

    /***
     * Prints the maze to the console each turn
     */
    public void printMaze(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int currentRow = 0; currentRow<rows; currentRow++){
            for(int currentColumn = 0; currentColumn<columns; currentColumn++){
                System.out.print(drawnMaze[currentColumn][currentRow]);
            }
            System.out.println();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /***
     * prints the maze to the console with only the correct path
     */
    public void printFinalMaze(){
        System.out.println("~~~~~~~~~Final Maze~~~~~~~~~~~~~~~~");
        for(int currentRow = 0; currentRow<rows; currentRow++){
            for(int currentColumn = 0; currentColumn<columns; currentColumn++){
                if(drawnMaze[currentColumn][currentRow].equals("o")){
                    drawnMaze[currentColumn][currentRow] =".";
                }
                System.out.print(drawnMaze[currentColumn][currentRow]);
            }
            System.out.println();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /***
     * static method which defines the xMarkers 2D array when called by the constructor
     * @return xMarkers[][]
     */
    public static String[][] defineXMarkers(){
        String[][] xMarkers = new String[columns][rows];
        for (int i=0; i<columns; i++){
            for (int k=0; k<rows; k++){
                xMarkers[i][k]="";
            }
        }
        return xMarkers;
    }

    /***
     * Static method which defines and creates the maze that will be solved.
     * @return createdMaze[][]
     */
    public static String[][] createMaze(){
        String[][] createdMaze;
        createdMaze = new String[columns][rows];

        for(int currentRow = 0; currentRow<rows; currentRow++){
            for(int currentColumn = 0; currentColumn<columns; currentColumn++){
                if(currentRow==0){
                    if(currentColumn==0 || currentColumn%2==0) {
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                }
                if(currentRow==1){
                    if(currentColumn==0 || currentColumn ==8 || currentColumn ==22){
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else if(currentColumn%2 !=0){
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = ".";
                    }
                }
                if(currentRow==2){
                    if(currentColumn==4 || currentColumn ==8 || currentColumn ==12 || currentColumn==16 ||currentColumn==18||currentColumn==22){
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else if(currentColumn%2 !=0){
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = ".";
                    }
                }
                if(currentRow==3){
                    if(currentColumn==0 || currentColumn ==2 || currentColumn ==4 || currentColumn == 8 || currentColumn ==20 || currentColumn ==22){
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else if(currentColumn%2 !=0){
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = ".";
                    }
                }
                if(currentRow==4){
                    if(currentColumn==0 || currentColumn ==12 || currentColumn == 14 || currentColumn ==16){
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else if(currentColumn%2 !=0){
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = ".";
                    }
                }
                if(currentRow==5){
                    if(currentColumn ==0 || currentColumn== 2 || currentColumn == 4 || currentColumn == 6 || currentColumn == 8 || currentColumn == 10 || currentColumn ==14 || currentColumn ==18 || currentColumn ==22){
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else if(currentColumn%2 !=0){
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = ".";
                    }
                }
                if(currentRow==6){
                    if(currentColumn==0 || currentColumn ==6 || currentColumn ==14 || currentColumn == 18 || currentColumn ==20 || currentColumn ==22){
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else if(currentColumn%2 !=0){
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = ".";
                    }
                }
                if(currentRow==7){
                    if(currentColumn==0 || currentColumn ==2 || currentColumn ==6 || currentColumn == 10 || currentColumn==14 || currentColumn ==18 || currentColumn ==22){
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else if(currentColumn%2 !=0){
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = ".";
                    }
                }
                if(currentRow==8){
                    if(currentColumn==0 || currentColumn ==18 || currentColumn ==22){
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else if(currentColumn%2 !=0){
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = ".";
                    }
                }
                //this row has its IF and ELSE flipped from all the others!!
                if(currentRow==9){
                    if(currentColumn %2==0 && currentColumn !=12 && currentColumn !=20) {
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else if(currentColumn == 12|| currentColumn ==20){
                        createdMaze[currentColumn][currentRow] = ".";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                }
                if(currentRow==10){
                    if(currentColumn==0 || currentColumn ==14 || currentColumn ==22){
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else if(currentColumn%2 !=0){
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = ".";
                    }
                }
                if(currentRow==11){
                    if(currentColumn %2==0) {
                        createdMaze[currentColumn][currentRow] = "#";
                    }
                    else{
                        createdMaze[currentColumn][currentRow] = " ";
                    }
                }
            }
        }
        return createdMaze;
    }


}
