package src.java.main.matrix;

/**
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state of the board is determined by applying the above rules simultaneously to every cell in the current state of the m x n grid board. In this process, births and deaths occur simultaneously.
 * <p>
 * Given the current state of the board, update the board to reflect its next state.
 * <p>
 * Note that you do not need to return anything.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * Example 2:
 * <p>
 * <p>
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?
 */
public class GameOfLife {

    public void gameOfLife(int[][] board) {
        //mark cell as 3 if it is turned from dead to live
        //mark it as 2 if it is turned from live to dead
        //while calculating live neighbours consider 2 as live (0) and 3 as dead(1)
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                int liveNeighbours = countLiveNeighbours(row, col, board);
                if ((liveNeighbours < 2 || liveNeighbours > 3) && board[row][col] == 1)
                    board[row][col] = 2;
                if (liveNeighbours == 3 && board[row][col] == 0)
                    board[row][col] = 3;
            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 2)
                    board[row][col] = 0;
                if (board[row][col] == 3)
                    board[row][col] = 1;
            }
        }
    }

    private int countLiveNeighbours(int row, int col, int[][] board) {
        int neighbours = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                //if the cells are out of boundary or the cell is the current cell then skip calculating neighbours
                if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || (i == row && j == col))
                    continue;
                if (board[i][j] == 1 || board[i][j] == 2)
                    neighbours += 1;
            }
        }
        return neighbours;
    }
}
