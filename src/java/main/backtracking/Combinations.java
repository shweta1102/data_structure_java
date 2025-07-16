package src.java.main.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combinations {
    /**
     * Time Complexity : O(nCk) --> (n!/(k!(n-k)!)) choose 2 from n
     * Space Complexity:
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        fillCombinations(list, k, n, 1, new ArrayList<Integer>(), 0);
        return list;
    }

    private void fillCombinations(List<List<Integer>> list, int k, int n, int start, List<Integer> current, int index) {
        if (k == 0) {
            list.add(new ArrayList(current));
            return;
        }
        for (int i = start; i <= n; i++) {
            current.add(i);
            fillCombinations(list, k - 1, n, i + 1, current, index + 1);
            current.remove(index);
        }
    }
}
