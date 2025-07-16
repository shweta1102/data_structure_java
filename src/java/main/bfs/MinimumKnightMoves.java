package src.java.main.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a chessboard of infinite size where the coordinates of each cell are defined by integer pairs (x, y). The knight piece moves in an L-shape, either two squares horizontally and one square vertically, or two squares vertically and one square horizontally.
 * Write a function to determine the minimum number of moves required for the knight to move from the starting position (0, 0) to the target position (x, y). Assume that it is always possible to reach the target position, and that x and y are both integers in the range [-200, 200]
 * Example 1:
 * Input:
 * x = 1
 * y = 2
 * Output: 1
 * Explanation: The knight can move from (0, 0) to (1, 2) in one move.
 * Example 2:
 * x = 4
 * y = 4
 * Output: 4
 * Explanation: The knight can move from (0, 0) to (4, 4) in four moves ( [0, 0] -> [2, 1] -> [4, 2] -> [6, 3] -> [4, 4] )
 */
public class MinimumKnightMoves {
    int[][] directions = new int[][]{{-2, 1}, {2, 1}, {-2, -1}, {2, -1}, {1, 2}, {-1, 2}, {-1, -2}, {1, -2}};

    class Cell {
        int x;
        int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Time Complexity: O(x*y) --> worst case visit all cells which is equal to x*y cells
     * Space Complexity: O(x*y)
     *
     * @param x
     * @param y
     * @return
     */
    public Integer minimum_knight_moves(Integer x, Integer y) {
        // Your code goes here
        int minMoves = 0;
        Queue<Cell> queue = new LinkedList<Cell>();
        queue.add(new Cell(0, 0));
        HashSet<String> visited = new HashSet<String>();
        visited.add(0 + "_" + 0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Cell current = queue.remove();
                if (current.x == x && current.y == y) {
                    return minMoves;
                }
                for (int[] direction : directions) {
                    int newX = current.x + direction[0];
                    int newY = current.y + direction[1];
                    if (!visited.contains(newX + "_" + newY)) {
                        visited.add(newX + "_" + newY);
                        queue.add(new Cell(newX, newY));
                    }
                }
            }
            minMoves++;
        }
        return minMoves;
    }
}
