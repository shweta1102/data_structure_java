package src.java.main.graph;

/**
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
 * <p>
 * Connect: A cell is connected to adjacent cells horizontally or vertically.
 * Region: To form a region connect every 'O' cell.
 * Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
 * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * <p>
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.
 * <p>
 * Example 2:
 * <p>
 * Input: board = [["X"]]
 * <p>
 * Output: [["X"]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegion {
    int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void solve(char[][] board) {
        if (board == null)
            return;
        int row = board.length;
        int col = board[0].length;
        //mark the regions starting from boundary and having O with N using dfsboundary--> these regions are not to be captured
        //mark all other regions as captured by making O-> X. Simultaneously convert N-->O
        boolean[][] visited = new boolean[row][col];
        //firstrow
        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O')
                dfsAtBoundary(board, 0, i, visited);
        }
        //first col
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O')
                dfsAtBoundary(board, i, 0, visited);
        }
        //last row
        for (int i = 0; i < col; i++) {
            if (board[row - 1][i] == 'O')
                dfsAtBoundary(board, row - 1, i, visited);
        }
        //last col
        for (int i = 0; i < row; i++) {
            if (board[i][col - 1] == 'O')
                dfsAtBoundary(board, i, col - 1, visited);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == 'N')
                    board[i][j] = 'O';
            }
        }

    }

    private void dfsAtBoundary(char[][] board, int row, int col, boolean[][] visited) {
        if (visited[row][col])
            return;
        visited[row][col] = true;
        if (board[row][col] == 'O')
            board[row][col] = 'N';
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow >= 0 && newCol >= 0 && newRow < board.length && newCol < board[0].length
                    && board[newRow][newCol] == 'O')
                dfsAtBoundary(board, newRow, newCol, visited);
        }
    }
}
