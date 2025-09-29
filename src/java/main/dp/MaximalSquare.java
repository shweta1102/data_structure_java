package src.java.main.dp;

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * Example 3:
 * <p>
 * Input: matrix = [["0"]]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */
public class MaximalSquare {
    /**
     * Time Complexity: O(row*col)
     * Space Complexity: O(row*col)
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        //if the number of area becomes more than 10 char will not be able to hold digits like 10,11. So create different array to  store area
        int max_area = 0;
        int[][] area = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int currentArea = matrix[i][j] - '0';
                if (i > 0 && j > 0 && matrix[i][j] != '0' && matrix[i - 1][j - 1] != '0' && matrix[i - 1][j] != '0'
                        && matrix[i][j - 1] != '0') {
                    currentArea = Math.min(area[i - 1][j - 1], Math.min(area[i - 1][j], area[i][j - 1])) + 1;
                }
                area[i][j] = currentArea;
                max_area = Math.max(currentArea, max_area);
            }
        }
        return max_area * max_area;
    }
}
