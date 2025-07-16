package src.java.main.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid where each cell can have one of three values:
 * <p>
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 * <p>
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 * <p>
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 */
public class RottenOranges {
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    class Cell {
        int row;
        int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Time Complexity: O(row*col) The worst-case scenario is that all the cells in the grid are fresh oranges, and we have to visit all of them to rot them.
     * Space Complexity: O(row*col) The space complexity is dominated by the queue used for the BFS traversal, which can contain at most row * col elements in the worst case.
     *
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        Queue<Cell> queue = new LinkedList<Cell>();
        int freshOrange = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Cell(i, j));
                }
                if (grid[i][j] == 1)
                    freshOrange++;
            }
        }
        if (freshOrange == 0)
            return 0;
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isUpdated = false;
            for (int i = 0; i < size; i++) {
                Cell current = queue.remove();
                for (int[] direction : directions) {
                    int newRow = current.row + direction[0];
                    int newCol = current.col + direction[1];
                    if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length
                            && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        freshOrange--;
                        isUpdated = true;
                        queue.add(new Cell(newRow, newCol));
                    }
                }
            }
            if (isUpdated)
                time++;
        }
        return freshOrange == 0 ? time : -1;
    }
}
