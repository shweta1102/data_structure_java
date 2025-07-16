package src.java.main.dp;

import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 * <p>
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * Example 2:
 * <p>
 * Input: triangle = [[-10]]
 * Output: -10
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 * <p>
 * <p>
 * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> prev = triangle.get(0);
        List<Integer> current = null;
        int minSum = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            current = triangle.get(i);
            int minCurrent = Integer.MAX_VALUE;
            for (int j = 0; j < current.size(); j++) {
                Integer currentElement = current.get(j);
                int value = j - 1 < 0 ? currentElement + prev.get(j)
                        : j >= prev.size() ? currentElement + prev.get(j - 1)
                        : Math.min(currentElement + prev.get(j - 1), currentElement + prev.get(j));
                current.set(j, value);
                minCurrent = Math.min(minCurrent, value);
            }
            minSum = minCurrent;
            prev = current;
        }
        return minSum;
    }
}
