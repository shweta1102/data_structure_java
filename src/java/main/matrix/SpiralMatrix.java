package src.java.main.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int upper = 0;
        int lower = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        List<Integer> result = new ArrayList<Integer>();

        while (upper <= lower && left <= right) {
            //first row
            for (int i = left; i <= right && upper <= lower; i++) {
                result.add(matrix[upper][i]);
            }
            upper++;
            //last column
            for (int i = upper; i <= lower && left <= right; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            //last row
            for (int i = right; i >= left && upper <= lower; i--) {
                result.add(matrix[lower][i]);
            }
            lower--;
            for (int i = lower; i >= upper && left <= right; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }
        return result;
    }
}
