package src.java.main.twopointers;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * <p>
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * <p>
 * You must solve this problem without using the library's sort function.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 * <p>
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 * <p>
 * <p>
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int colourIndex = 0;
        //move 0
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = nums[colourIndex];
                nums[colourIndex] = 0;
                colourIndex++;
            }
        }
        //move 1
        for (int i = colourIndex; i < nums.length; i++) {
            if (nums[i] == 1) {
                nums[i] = nums[colourIndex];
                nums[colourIndex] = 1;
                colourIndex++;
            }
        }
    }

    public void sortColorsOnePass(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                nums[i] = nums[left];
                nums[left] = 0;
                left++;
                i++;
            } else if (nums[i] == 2) {
                nums[i] = nums[right];
                nums[right] = 2;
                right--;
            } else {
                i++;
            }
        }
    }
}
