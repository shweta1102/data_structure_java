package src.java.main.binarysearch;

/**
 * A peak element is an element that is strictly greater than its neighbors.
 * <p>
 * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 * <p>
 * You must write an algorithm that runs in O(log n) time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * nums[i] != nums[i + 1] for all valid i.
 */
public class PeakElement {
    public int findPeakElement(int[] nums) {
        //check if the ends of the array are peaks or array has only one element
        if (nums.length == 1 || nums[0] > nums[1])
            return 0;
        int n = nums.length;
        if (nums[n - 1] > nums[n - 2])
            return n - 1;
        int left = 1;
        int right = n - 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
                return mid;
            else if (nums[mid] < nums[mid - 1])
                right = mid - 1;
            else if (nums[mid] > nums[mid - 1])
                left = mid + 1;
        }
        return -1;
    }
}
