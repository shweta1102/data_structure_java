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
        List<Integer> order = new ArrayList<Integer>();
        //l1 --> left boundary
        int l1 = 0;
        //l2 --> right boundary
        int l2 = matrix[0].length - 1;
        //h1 --> upper boundary
        int h1 = 0;
        //h2 --> bottom boundary
        int h2 = matrix.length - 1;
        int iteration = 1;
        //spiral till we reach from left to right and upper to lower
        while (l1 <= l2 && h1 <= h2) {
            switch (iteration) {
                //left to right
                case 1: {
                    for (int i = l1; i <= l2; i++) {
                        order.add(matrix[h1][i]);
                    }
                    //move upper boundary one down
                    h1++;
                    break;
                }
                //upper to lower
                case 2: {
                    for (int i = h1; i <= h2; i++) {
                        order.add(matrix[i][l2]);
                    }
                    //move right boundary one left
                    l2--;
                    break;
                }
                //right to left
                case 3: {
                    for (int i = l2; i >= l1; i--) {
                        order.add(matrix[h2][i]);
                    }
                    //move lower boundary one up
                    h2--;
                    break;
                }
                //lower to upper
                case 4: {
                    for (int i = h2; i >= h1; i--) {
                        order.add(matrix[i][l1]);
                    }
                    //move left boundary to right by one
                    l1++;
                    break;
                }
            }
            iteration++;
            if (iteration == 5)
                iteration = 1;
        }
        return order;
    }
}
