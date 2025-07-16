package src.java.main.twopointers;

import java.util.Arrays;

/**
 * Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,3,4]
 * Output: 3
 * Explanation: Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Example 2:
 *
 * Input: nums = [4,2,3,4]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class ValidTriangleNumber {
    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int fixed = nums.length - 1;
        int result = 0;
        while (fixed > 0) {
            int left = 0;
            int right = fixed - 1;
            while (right > left) {
                int sum = nums[left] + nums[right];
                if (sum > nums[fixed]) {
                    result += right - left;
                    right--;
                } else {
                    left++;
                }
            }
            fixed--;
        }
        return result;
    }
}
