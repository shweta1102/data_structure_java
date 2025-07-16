package src.java.main.binarysearch;

/**
 * Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
 * <p>
 * Return the minimized largest sum of the split.
 * <p>
 * A subarray is a contiguous part of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 9
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= k <= min(50, nums.length)
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        //find the range for the binary search
        int low = 0;
        int high = 0;
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }
        int result = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isFeasible(k, nums, mid)) {
                result = Math.min(result, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    private boolean isFeasible(int k, int[] nums, int sum) {
        int currentSum = 0;
        int partition = 1;
        for (int num : nums) {
            currentSum += num;
            if (currentSum > sum) {
                partition++;
                currentSum = num;
                if (partition > k)
                    return false;
            }
        }
        return true;
    }
}
