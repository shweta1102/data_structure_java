package src.java.main.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        sunsetsHelper(result, nums, 0, new ArrayList<Integer>());
        return result;
    }

    /**
     * Time Complexity:
     * Space Complexity:
     * @param result
     * @param nums
     * @param currentIndex
     * @param currentList
     */
    private void sunsetsHelper(List<List<Integer>> result, int[] nums, int currentIndex, List<Integer> currentList) {
        if (currentIndex == nums.length) {
            result.add(new ArrayList(currentList));
            return;
        }
        //include current Index
        currentList.add(nums[currentIndex]);
        sunsetsHelper(result, nums, currentIndex + 1, currentList);
        currentList.remove(currentList.size() - 1);
        //exclude current Index
        sunsetsHelper(result, nums, currentIndex + 1, currentList);
    }
}
