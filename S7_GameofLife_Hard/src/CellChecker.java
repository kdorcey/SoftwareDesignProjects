/***
 * The CellChecker class holds various static methods used in detecting cells that should be changed during for the
 * next generation and sequentially applying those changes to the Cell objects. Due to this only one instance
 * variable is required at a time so every method is static.
 *
 * @author Kyle Dorcey
 * @version 1.8
 * @see Cell
 */
public class CellChecker  {
    /***
     * The method checkNeighbors runs through every Cell object and flags any cells "touching" an alive cell.
     * The method also checks for invalid cells[][] parameters and ignores any that would cause an error.
     * @param cellCount     integer value representing the amount of cells in a row or column.
     * @param cells         2D array of Cell objects that the method will check.
     */
    public static void checkNeighbors(int cellCount, Cell cells[][]) {
        for(int xCord =0; xCord<cellCount; xCord++) {
            for (int yCord=0; yCord<cellCount; yCord++) {
                if(cells[xCord][yCord].isCellAlive()) {
                    if (xCord > 0) {
                        cells[xCord - 1][yCord].flagThisCell();
                    }
                    if (yCord > 0) {
                        cells[xCord][yCord - 1].flagThisCell();
                    }
                    if (xCord > 0 && yCord > 0) {
                        cells[xCord - 1][yCord - 1].flagThisCell();
                    }
                    if (xCord < cellCount - 1) {
                        cells[xCord + 1][yCord].flagThisCell();
                    }
                    if (yCord < cellCount - 1) {
                        cells[xCord][yCord + 1].flagThisCell();
                    }
                    if (xCord < cellCount - 1 && yCord < cellCount - 1) {
                        cells[xCord + 1][yCord + 1].flagThisCell();
                    }
                    if (xCord < cellCount - 1 && yCord > 0) {
                        cells[xCord + 1][yCord - 1].flagThisCell();
                    }
                    if (xCord > 0 && yCord < cellCount - 1) {
                        cells[xCord - 1][yCord + 1].flagThisCell();
                    }
                }
            }
        }
    }

    /***
     * Runs through every Cell object and call the convertFlagsToLife() method from the Cell class.
     * This method also resets every cells flag after calling the convertFlagsToLife() method is called in order
     * to prepare the cells for the next generation.
     * @param cellCount     integer value representing the amount of cells in a row or column.
     * @param cells         2D array of Cell objects that the method will check.
     */
    public static void flagConverter (int cellCount, Cell cells[][]){
        for(int xCord=0; xCord<cellCount; xCord++) {
            for(int yCord=0; yCord<cellCount; yCord++) {
                cells[xCord][yCord].convertFlagsToLife();
                cells[xCord][yCord].resetFlags();
            }
        }

    }

    /***
     * The clearBoard method is called when the stopSim button is fired. It runs through every cell killing them
     * and clearing flags to ready the board for a new user seed.
     * @param cellCount     integer value representing the amount of cells in a row or column.
     * @param cells         2D array of Cell objects that the method will check.
     */
    public static void clearBoard(int cellCount, Cell cells[][]){
        for(int xCord=0; xCord<cellCount; xCord++){
            for(int yCord=0; yCord<cellCount;yCord++){
                cells[xCord][yCord].killCell();
            }
        }
    }

    /***
     * calls the aliveCell() method for the necessary Cell objects in order to create a Gosper's Glider Gun seed.
     * The method also calls the static clearBoard() before constructing the seed in order to avoid any logic
     * errors created by unintentional flagging.
     * @param cellCount     integer value representing the amount of cells in a row or column.
     * @param cells         2D array of Cell objects that the method will check.
     */
    public static void createGliderGun(int cellCount, Cell cells[][]){
        clearBoard(cellCount,cells);
        int startX = cellCount/2;
        int startY= cellCount/2;

        int coords[] =  {-14, -13 ,-4,0,2,3,8,10};
        int coordsOne[] = {-14,-13,-4,2,10};
        int coordsTwo[] = {-3,1};
        int coordsThree[] = {-2,-1};
        int coordsNegOne[] = {-4,2,6,7,20,21};
        int coordsNegTwo[] = {-3,1,6,7,20,21};
        int coordsNegThree[] ={-2,-1,6,7};
        int coordsNegFour[] = {8,10};
        int coordsNegFive[] = {10};

        for(int a:coords){
            cells[startX][startY+a].aliveCell();
        }
        for(int a:coordsOne){
            cells[startX+1][startY+a].aliveCell();
        }
        for(int a:coordsTwo){
            cells[startX+2][startY+a].aliveCell();
        }
        for(int a: coordsThree){
            cells[startX+3][startY+a].aliveCell();
        }
        for(int a: coordsNegOne){
            cells[startX-1][startY+a].aliveCell();
        }
        for(int a: coordsNegTwo){
            cells[startX-2][startY+a].aliveCell();
        }
        for(int a: coordsNegThree){
            cells[startX-3][startY+a].aliveCell();
        }
        for(int a: coordsNegFour){
            cells[startX-4][startY+a].aliveCell();
        }
        for(int a: coordsNegFive){
            cells[startX-5][startY+a].aliveCell();
        }
    }

}
