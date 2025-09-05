package src.java.main.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;

/**
 * You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
 * <p>
 * The length of the subarray is k, and
 * All the elements of the subarray are distinct.
 * Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
 * <p>
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,5,4,2,9,9,9], k = 3
 * Output: 15
 * Explanation: The subarrays of nums with length 3 are:
 * - [1,5,4] which meets the requirements and has a sum of 10.
 * - [5,4,2] which meets the requirements and has a sum of 11.
 * - [4,2,9] which meets the requirements and has a sum of 15.
 * - [2,9,9] which does not meet the requirements because the element 9 is repeated.
 * - [9,9,9] which does not meet the requirements because the element 9 is repeated.
 * We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
 * Example 2:
 * <p>
 * Input: nums = [4,4,4], k = 3
 * Output: 0
 * Explanation: The subarrays of nums with length 3 are:
 * - [4,4,4] which does not meet the requirements because the element 4 is repeated.
 * We return 0 because no subarrays meet the conditions.
 * <p>
 * Input: [3,2,3,1], k=3
 * Output: 6
 * <p>
 * Input: [1,1,1,1,1,1], k=1
 * Output: 1
 * <p>
 * Input: [9,9,9,1,2,3], k=3
 * Output: 12
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class MaximumSumOfDistinctSubArraysWithLengthK {
    public long maximumSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> state = new HashMap<Integer, Integer>();
        int start = 0;
        long maxSum = 0;
        long currentSum = 0;

        for (int end = 0; end < nums.length; end++) {
            currentSum += nums[end];
            state.put(nums[end], state.getOrDefault(nums[end], 0) + 1);

            if (end - start + 1 == k) {
                if (state.size() == k) {
                    maxSum = Math.max(maxSum, currentSum);
                }
                state.put(nums[start], state.get(nums[start]) - 1);
                currentSum -= nums[start];
                if (state.get(nums[start]) == 0)
                    state.remove(nums[start]);
                start++;
            }
        }
        return maxSum;
    }

    public long maximumSubarraySumWithHashSet(int[] nums, int k) {
        //hash set of size k to maintain unique elements
        long maxSum = 0;
        int start = 0;
        HashSet<Integer> uniqueSet = new HashSet<Integer>();
        long currentSum = 0;
        for (int end = 0; end < nums.length; end++) {
            currentSum += nums[end];
            if (uniqueSet.contains(nums[end])) {
                while (nums[start] != nums[end]) {
                    currentSum -= nums[start];
                    uniqueSet.remove(nums[start]);
                    start++;
                }
                currentSum -= nums[start];
                start++;
            }
            uniqueSet.add(nums[end]);
            if (end - start + 1 == k) {
                maxSum = Math.max(maxSum, currentSum);
                currentSum -= nums[start];
                uniqueSet.remove(nums[start]);
                start++;
            }
        }
        return maxSum;
    }
}
