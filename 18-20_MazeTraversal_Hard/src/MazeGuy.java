/***
 * The MazeGuy class is responsible for handling all logic for traversing the maze. With the exception of
 * the variables "breadCrumb" and "currentLocationMarker" the class will not directly interact with the
 * maze created by the Maze class. Instead it calls methods from the Maze class with parameters to tell
 * it what to do
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class MazeGuy {
    /**maze object**/
    Maze mazeToSolve;
    /**Marker used to show the current position of the Maze Guy in the maze (It's a smiley face because linux didn't support the cowboy emoji :/**/
    private String currentLocationMarker = "\uD83D\uDE00";
    /**String used to represent the "breadcrumbs"**/
    private String breadCrumb = "x";
    /**2D array containing the original maze**/
    private String[][] mazeMap;

    /***
     * Constructor for the MazeGuy class, defines the mazeMap and mazeToSolve variables
     * @param mazeToSolve reference to the Maze object the maze guy will solve
     */
    public MazeGuy(Maze mazeToSolve) {
        this.mazeToSolve = mazeToSolve;
        this.mazeMap = mazeToSolve.getMazeMap();
        String lastMove ="";
    }

    /***
     * Recursive method used to solve traverse the maze. Each "turn" the method will check for a
     * valid direction to move. If there is one, it will call the breadCrumb method, marking the position the
     * mazeGuy will be moving from, and recalls mazeTraversal. In the event that method detects it has reached
     * a dead end, the retraceSteps() method will be called. If it detects that the mazeGuy has reach the final
     * position it will call Maze's printFinalMaze() method.The method also contains a Thread.sleep() to
     * make the maze easier to view
     * @param currentXLocation current x-position
     * @param currentYLocation current y-position
     * @param lastMove String containing the last move. The only point where the mazeGuy will need to
     *                 reverse the direction it just came from is when it gets stuck in at a dead end, and
     *                 if this happens the retraceStep() method will handle it.
     */
    public void mazeTraversal(int currentXLocation, int currentYLocation, String lastMove){
        mazeToSolve.updateManLocation(currentXLocation,currentYLocation,currentLocationMarker);

        try{ Thread.sleep(700); } catch(InterruptedException threadSleepError){
            System.out.println("Thread Sleep Error: "+threadSleepError);
        }

        if (moveUp(currentXLocation, currentYLocation) && !lastMove.equals("DOWN")) {
            mazeToSolve.breadCrumbs(currentXLocation, currentYLocation, breadCrumb);
            mazeTraversal(currentXLocation, currentYLocation - 1, "UP");
        } else if (moveRight(currentXLocation, currentYLocation) && !lastMove.equals("LEFT")) {
            mazeToSolve.breadCrumbs(currentXLocation, currentYLocation, breadCrumb);
            mazeTraversal(currentXLocation + 2, currentYLocation, "RIGHT");
        } else if (moveDown(currentXLocation, currentYLocation) && !lastMove.equals("UP")) {
            mazeToSolve.breadCrumbs(currentXLocation, currentYLocation, breadCrumb);
            mazeTraversal(currentXLocation, currentYLocation + 1, "DOWN");
        } else if (moveLeft(currentXLocation, currentYLocation) && !lastMove.equals("RIGHT")) {
            mazeToSolve.breadCrumbs(currentXLocation, currentYLocation, breadCrumb);
            mazeTraversal(currentXLocation - 2, currentYLocation, "LEFT");
        }
        else if(mazeToSolve.checkMazeComplete(currentXLocation,currentYLocation)){
            mazeToSolve.printFinalMaze();
        }
        else {
            mazeToSolve.breadCrumbs(currentXLocation, currentYLocation, breadCrumb);
            retraceSteps(currentXLocation, currentYLocation, "none");
        }
    }

    /***
     * This method is called whenever the maze guy reaches a dead end. It functions almost identically to the
     * mazeTraversal() method, except it only follows paths made of "x"s and is constantly checking for a new
     * path made of "."s. Because the path it's taking is already covered in "x"'s (or breadCrumbs). The breadCrumb() method
     * will replace the "x"s with "o"s as it runs. The method also contains a Thread.sleep() to make the maze easier to view
     * @param currentXLocation current x-location.
     * @param currentYLocation current y-location.
     * @param lastMove String containing the last move
     */
    private void retraceSteps(int currentXLocation, int currentYLocation, String lastMove){
        mazeToSolve.updateManLocation(currentXLocation,currentYLocation,currentLocationMarker);

        try{ Thread.sleep(700); } catch(InterruptedException e){ }

        if(!mazeToSolve.retraceExitCheck(currentXLocation,currentYLocation)){
            if (moveUp(currentXLocation, currentYLocation) && !lastMove.equals("DOWN") && mazeMap[currentXLocation][currentYLocation - 1].equals("x")) {
                mazeToSolve.breadCrumbs(currentXLocation, currentYLocation, breadCrumb);
                retraceSteps(currentXLocation, currentYLocation - 1, "UP");
            } else if (moveRight(currentXLocation, currentYLocation) && !lastMove.equals("LEFT") && mazeMap[currentXLocation + 2][currentYLocation].equals("x")) {
                mazeToSolve.breadCrumbs(currentXLocation, currentYLocation, breadCrumb);
                retraceSteps(currentXLocation + 2, currentYLocation, "RIGHT");
            } else if (moveDown(currentXLocation, currentYLocation) && !lastMove.equals("UP") && mazeMap[currentXLocation][currentYLocation + 1].equals("x")) {
                mazeToSolve.breadCrumbs(currentXLocation, currentYLocation, breadCrumb);
                retraceSteps(currentXLocation, currentYLocation + 1, "DOWN");
            } else if (moveLeft(currentXLocation, currentYLocation) && !lastMove.equals("RIGHT") && mazeMap[currentXLocation - 2][currentYLocation].equals("x")) {
                mazeToSolve.breadCrumbs(currentXLocation, currentYLocation, breadCrumb);
                retraceSteps(currentXLocation - 2, currentYLocation, "LEFT");
            }
        }
        else{
            mazeToSolve.breadCrumbs(currentXLocation,currentYLocation,breadCrumb);
            mazeTraversal(currentXLocation,currentYLocation,lastMove);
        }
    }

    /**
     * Checks if moving the MazeGuy "up" is possible
     * @param currentX current x-position
     * @param currentY current y-position
     * @return boolean canMove. True if move is possible
     */
    private boolean moveUp(int currentX, int currentY){
        boolean canMove =false;
        int nextX = currentX;
        int nextY = currentY-1;

        if(!mazeToSolve.checkMazeComplete(nextX,nextY)) {
            if (mazeToSolve.validMoveCheck(nextX, nextY)) {
                canMove = true;
            }
        }
        else{
            canMove =false;
        }
        return canMove;
    }
    /**
     * Checks if moving the MazeGuy "down" is possible
     * @param currentX current x-position
     * @param currentY current y-position
     * @return boolean canMove. True if move is possible
     */
    private boolean moveDown(int currentX, int currentY){
        boolean canMove =false;
        int nextX = currentX;
        int nextY = currentY+1;

        if(!mazeToSolve.checkMazeComplete(nextX,nextY)) {
            if (mazeToSolve.validMoveCheck(nextX, nextY)) {
                canMove = true;
            }
        }
        else{
            canMove =false;
        }
        return canMove;
    }
    /**
     * Checks if moving the MazeGuy "left" is possible
     * @param currentX current x-position
     * @param currentY current y-position
     * @return boolean canMove. True if move is possible
     */
    private boolean moveLeft(int currentX, int currentY){
        boolean canMove =false;
        int nextX = currentX-2;
        int nextY = currentY;

        if(!mazeToSolve.checkMazeComplete(nextX,nextY)) {
            if (mazeToSolve.validMoveCheck(nextX, nextY)) {
                canMove = true;
            }
        }
        else{
            canMove = false;
        }
        return canMove;
    }
    /**
     * Checks if moving the MazeGuy "right" is possible
     * @param currentX current x-position
     * @param currentY current y-position
     * @return boolean canMove. True if move is possible
     */
    private boolean moveRight(int currentX, int currentY){
        boolean canMove =false;
        int nextX = currentX+2;
        int nextY = currentY;

        if(!mazeToSolve.checkMazeComplete(nextX,nextY)) {
            if (mazeToSolve.validMoveCheck(nextX, nextY)) {
                canMove = true;
            }
        }
        else{
            canMove = false;
        }
        return canMove;
    }
}