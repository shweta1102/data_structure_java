package src.java.main.twopointers;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class TrappingWater {
    public int trap(int[] height) {
        int prevMinHeight = 0;
        int left = 0;
        int right = height.length - 1;
        int trappedWater = 0;
        while (left < right) {
            int min = Math.min(height[left], height[right]);
            //total additional water that can be trapped between left and right assuming bar width is zero
            trappedWater += ((min - prevMinHeight) * (right - left));
            prevMinHeight = min;
            if (height[left] == min) {
                //if the bar height is less than the current min then the water quanity is already calculated in current trappedWater
                while (height[left] <= min && left < right) {
                    //as bar width is 1 we will subtract that from the total water calculated
                    trappedWater -= height[left];
                    left++;
                }
            } else {
                //if the bar height is less than the current min then the water quanity is already calculated in current trappedWater
                while (height[right] <= min && left < right) {
                    //as bar width is 1 we will subtract that from the total water calculated
                    trappedWater -= height[right];
                    right--;
                }

            }
        }
        return trappedWater;
    }

    private int trapWithTwoPointer(int[] height) {
        if (height.length == 1)
            return 0;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int left = 0;
        int right = height.length - 1;
        int water = 0;
        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                if (height[left] < leftMax) {
                    water += leftMax - height[left];
                } else
                    leftMax = height[left];
            } else {
                right--;
                if (height[right] < rightMax) {
                    water += rightMax - height[right];
                } else
                    rightMax = height[right];
            }
        }
        return water;
    }

}
