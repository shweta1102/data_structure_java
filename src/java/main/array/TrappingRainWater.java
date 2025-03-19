package src.java.main.array;

import java.util.Stack;

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
public class TrappingRainWater {
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

    private int trapWithArrayForMaxHeight(int[] height) {
        if (height.length == 1)
            return 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int water = 0;
        // for each index calculate maximum height on the left side
        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);

        }
        // for each index calculate maximum height on the right side
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        // for each index water stored will be min of left and right height - height at
        // the index
        for (int i = 0; i < height.length; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return water;
    }

    private int trapWithStack(int[] height) {
        if (height.length == 1)
            return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int water = 0;
        int left = 0;
        int mid = 0;
        int width = 0;
        for (int right = 0; right < height.length; right++) {
            while (!stack.isEmpty() && height[right] > height[stack.peek()]) {
                mid = stack.pop();
                if (stack.isEmpty())
                    break;
                left = stack.peek();
                width = right - left - 1;
                water += Math.min(height[right] - height[mid], height[left] - height[mid]) * width;
            }
            stack.push(right);
        }
        return water;
    }
}
