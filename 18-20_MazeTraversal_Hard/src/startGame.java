/***
 * Driver class for the maze game. Calls the Maze class's constructor, as well as the first printMaze() method call.
 * The class is also responsible for creating the first instance of the MazeGuy class and calling the first
 * mazeTraversal().
 */
public class startGame {
    public static void main(String [] args){
        Maze mainMaze = new Maze();
        mainMaze.printMaze();
        MazeGuy cowBoyJoe = new MazeGuy(mainMaze);
        cowBoyJoe.mazeTraversal(mainMaze.getInitialX(), mainMaze.getInitialY(),"none");
    }
}
