package src.java.main.twopointers;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Note that you must do this in-place without making a copy of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * <p>
 * Follow up: Could you minimize the total number of operations done?
 */
public class MoveZeros {
    /**
     * Time Complexity: O(n)
     *
     * @param nums
     */
    public void moveZeroesBruteForce(int[] nums) {
        int left = 0;
        int right = 0;
        int temp = 0;
        while (right < nums.length && left < nums.length) {
            while (left < nums.length && nums[left] != 0) {
                left++;
            }
            right = left + 1;
            while (right < nums.length && nums[right] == 0) {
                right++;
            }
            if (right < nums.length && left < nums.length) {
                temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
            left++;
        }
    }

    /**
     * Time Complexity: O(n)
     *
     * @param nums
     */
    public void moveZeroesCleaner(int[] nums) {
        int nonZeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex] = nums[i];
                nums[i] = nonZeroIndex != i ? 0 : nums[i];
                nonZeroIndex++;
            }
        }
    }
}
