package src.java.main.prefixsum;

import java.util.HashMap;

/**
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class SubsumEqualsK {
    /**
     * Time complexity: O(n^2)
     * Space Complexity: O(n)
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumWithTwoPass(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        int result = 0;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (prefixSum[i] - prefixSum[j] == k)
                    result++;
            }
        }
        return result;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumWithHashMap(int[] nums, int k) {
        int result = 0;
        HashMap<Integer, Integer> prefixSumFreq = new HashMap<Integer, Integer>();
        prefixSumFreq.put(0, 1);
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            //calcultae prefix sum
            sum = sum + nums[i - 1];
            //check freq for sum-k for the subarrays before ith index
            int freq = prefixSumFreq.getOrDefault(sum - k, 0);
            //if exist add that to result count
            result += freq;
            //update freq for current sum
            prefixSumFreq.put(sum, prefixSumFreq.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}
