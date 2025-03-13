package src.java.main.array;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        if (nums.length == 1)
            return true;
        int maxIndex = nums[0];
        int i = 1;
        while (i < nums.length && i <= maxIndex) {
            maxIndex = Math.max(i + nums[i], maxIndex);
            if (maxIndex == (nums.length - 1))
                return true;
            i++;
        }
        return maxIndex >= (nums.length - 1);
    }
}
