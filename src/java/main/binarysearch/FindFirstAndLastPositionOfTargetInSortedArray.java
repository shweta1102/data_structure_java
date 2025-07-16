package src.java.main.binarysearch;

/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 * <p>
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */
public class FindFirstAndLastPositionOfTargetInSortedArray {

    public int[] searchRangeBruteForce(int[] nums, int target) {
        if (nums.length == 0 || (nums.length == 1 && target != nums[0]))
            return new int[]{-1, -1};
        if (nums.length == 1 && target == nums[0])
            return new int[]{0, 0};
        int low = searchLowerIndex(nums, target);
        int high = searchHigherIndex(nums, target);
        return new int[]{low, high};
    }

    private int searchLowerIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                if (mid == 0 || nums[mid] > nums[mid - 1])
                    return mid;
                else
                    right = mid - 1;
            } else if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }

    private int searchHigherIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                if (mid == nums.length - 1 || nums[mid] < nums[mid + 1])
                    return mid;
                else
                    left = mid + 1;
            } else if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }

    public int[] searchRangeBetterReadability(int[] nums, int target) {
        int low = findIndex(nums, target, true);
        int high = findIndex(nums, target, false);
        return new int[]{low, high};
    }

    private int findIndex(int[] nums, int target, boolean isLower) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else {
                index = mid;
                //if we need lowerIndex
                //keep moving left side and noting the index when mid = target till we left > right
                if (isLower)
                    right = mid - 1;
                    // when we need higher index
                    //keep moving right side and noting the index when mid = target till we left > right
                else
                    left = mid + 1;
            }
        }
        return index;
    }
}
