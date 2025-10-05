package src.java.main.prefixsum;

/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 * <p>
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 * <p>
 * <p>
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        int[] ans = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] * nums[i];
        }
        suffix[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1)
                ans[i] = prefix[i - 1];
            else if (i == 0)
                ans[i] = suffix[i + 1];
            else
                ans[i] = prefix[i - 1] * suffix[i + 1];
        }
        return ans;
    }

    public int[] productExceptSelfOneArray(int[] nums) {
        int[] ans = new int[nums.length];
        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            ans[i] = left;
            left = left * nums[i];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans[i] = ans[i] * right;
            right = right * nums[i];
        }
        return ans;
    }
}
