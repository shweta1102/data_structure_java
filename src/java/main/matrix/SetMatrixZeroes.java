package src.java.main.matrix;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * <p>
 * You must do it in place.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 * <p>
 * <p>
 * Follow up:
 * <p>
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        boolean firstRow = false;
        boolean firstCol = false;
        //use first row and column of the matrix as a space to mark rows and columns with 0
        //also maintain if the first row or column has 0 itself
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 0) {
                    if (row == 0)
                        firstRow = true;
                    if (col == 0)
                        firstCol = true;
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }
        //mark all column as 0 for the rows wich are marked as 0
        for (int col = 1; col < matrix[0].length; col++) {
            if (matrix[0][col] == 0) {
                for (int row = 1; row < matrix.length; row++) {
                    matrix[row][col] = 0;
                }
            }
        }
        //mark all rows as 0 for the columns those are marked as 0 in the first column
        for (int row = 1; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                for (int col = 1; col < matrix[0].length; col++) {
                    matrix[row][col] = 0;
                }
            }
        }
        //if first row is marked then replace all cells with 0
        if (firstRow) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[0][col] = 0;
            }
        }
        //if first column is marked then replace all cells in first column with 0
        if (firstCol) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][0] = 0;
            }
        }
    }
}
