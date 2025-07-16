package src.java.main.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: [[1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
public class Permutations {
    /**
     * Time Complexity: O(n!) --> number of permutations
     * Space Complexity: O(n) --> Recursion stack + visited array
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        boolean[] visited = new boolean[nums.length];
        List<Integer> current = new ArrayList<Integer>();
        populatePermutation(nums, result, visited, current);
        return result;
    }

    private void populatePermutation(int[] nums, List<List<Integer>> result, boolean[] visited, List<Integer> current) {
        if (current.size() == nums.length) {
            result.add(new ArrayList(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.add(nums[i]);
                populatePermutation(nums, result, visited, current);
                current.remove(current.size() - 1);
                visited[i] = false;
            }
        }
    }
}
