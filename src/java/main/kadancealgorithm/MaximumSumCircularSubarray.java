package src.java.main.kadancealgorithm;

/**
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 * <p>
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 * <p>
 * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3.
 * Example 2:
 * <p>
 * Input: nums = [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
 * Example 3:
 * <p>
 * Input: nums = [-3,-2,-3]
 * Output: -2
 * Explanation: Subarray [-2] has maximum sum -2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104
 */
public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] nums) {
        int left = 0;
        int right = 0;
        int currentSum = nums[0];
        int maxSum = nums[0];
        int minSum = nums[0];
        int totalSum = nums[0];
        while (right < nums.length - 1) {
            right++;
            totalSum += nums[right];
            if (nums[right] + currentSum < nums[right]) {
                currentSum = nums[right];
                left = right;
            } else {
                currentSum += nums[right];
            }
            maxSum = Math.max(currentSum, maxSum);
        }
        right = 0;
        currentSum = nums[0];
        while (right < nums.length - 1) {
            right++;
            if (nums[right] + currentSum > nums[right]) {
                currentSum = nums[right];
                left = right;
            } else {
                currentSum += nums[right];
            }
            minSum = Math.min(currentSum, minSum);
        }
        return (totalSum - minSum) == 0 ? maxSum : Math.max(maxSum, (totalSum - minSum));
    }
}
