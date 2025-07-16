package src.java.main.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * Example 2:
 * <p>
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * Example 3:
 * <p>
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class ThreeSum {
    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2) to store result
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int fixed = 0;
        while (fixed < nums.length - 2) {
            if (fixed != 0 && nums[fixed] == nums[fixed - 1]) {
                fixed++;
                continue;
            }
            int left = fixed + 1;
            int right = nums.length - 1;
            int target = -nums[fixed];
            while (right > left) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    result.add(Arrays.asList(nums[left], nums[right], nums[fixed]));
                    //to avoid duplicate move left till it is same value
                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                        sum = nums[left] + nums[right];
                    }
                    //move right till it is same value
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                        sum = nums[left] + nums[right];
                    }
                }
                if (sum >= target) {
                    right--;
                } else {
                    left++;
                }
            }
            fixed++;
        }
        return result;
    }
}
