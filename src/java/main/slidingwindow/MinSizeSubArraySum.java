package src.java.main.slidingwindow;

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 * <p>
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 * <p>
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * <p>
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */
public class MinSizeSubArraySum {
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 1) {
            return nums[0] >= target ? 1 : 0;
        }
        int left = 0;
        int right = 0;
        int sum = nums[left];
        int windowSize = Integer.MAX_VALUE;
        while (right < nums.length && left <= right) {
            if (sum >= target) {
                windowSize = Math.min(windowSize, right - left + 1);
                sum -= nums[left];
                left++;
            } else {
                right++;
                if (right < nums.length)
                    sum += nums[right];
            }
        }
        return windowSize == Integer.MAX_VALUE ? 0 : windowSize;
    }
}
