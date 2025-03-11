package src.java.main.dp;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        //time complexity: O(n^2)
        //space complexity: O(n)
        if (nums.length == 1)
            return 1;
        int[] result = new int[nums.length];
        result[0] = 1;
        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            int tempResult = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    tempResult = Math.max(tempResult, result[j] + 1);
                }
            }
            result[i] = tempResult;
            maxLength = Math.max(maxLength, tempResult);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] nums = {4, 2, 3, 6, 10, 1, 12};
        System.out.println(lis.lengthOfLIS(nums));
        nums = new int[]{-4, 10, 3, 7, 15};
        System.out.println(lis.lengthOfLIS(nums));
    }
}
