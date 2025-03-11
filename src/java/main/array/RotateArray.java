package src.java.main.array;

/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 * <p>
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        if (nums.length == 1 || k == 0)
            return;
        // k can be greater than nums.length
        if (k > nums.length) {
            k = k % nums.length;
        }
        // every element is shifted exactly once
        int count = 0;
        int previous = 0;
        int nextIndex = 0;
        int temp = 0;
        for (int i = 0; count < nums.length; i++) {
            previous = nums[i];
            nextIndex = i;
            do {
                nextIndex = (nextIndex + k) % nums.length;
                System.out.println("i " + i + " nextIndex" + nextIndex + " count" + count);
                temp = nums[nextIndex];
                nums[nextIndex] = previous;
                previous = temp;
                count++;
            } while (nextIndex != i);
        }
    }
}
