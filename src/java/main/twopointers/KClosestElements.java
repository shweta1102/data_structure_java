package src.java.main.twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 * <p>
 * An integer a is closer to x than an integer b if:
 * <p>
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * <p>
 * Output: [1,2,3,4]
 * <p>
 * Example 2:
 * <p>
 * Input: arr = [1,1,2,3,4,5], k = 4, x = -1
 * <p>
 * Output: [1,1,2,3]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr is sorted in ascending order.
 * -104 <= arr[i], x <= 104
 */
public class KClosestElements {
    /**
     * Time Complexity: O(n)
     *
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElementsWithTwoPointer(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;
        while ((right - left + 1) > k) {
            int ldis = distance(arr[left], x);
            int rdis = distance(arr[right], x);
            if (ldis > rdis) {
                left++;
            } else {
                right--;
            }
        }
        List<Integer> result = new ArrayList<Integer>();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    private int distance(int num, int x) {
        return Math.abs(num - x);
    }

    public List<Integer> findClosestElementsWithBinarySearch(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;
        //use binary search to find the left which can be starting point for the sub-array of length k
        while (left < right) {
            //stop when we get left==right
            int mid = (left + right) / 2;
            //we compare mid distance of mid value and mid+k value from x. In case when both of them are same, them we check mid-point of both value to decide if we want to move left or right pointer.
            //target should be between mid and mid+k
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        List<Integer> result = new ArrayList<Integer>();
        for (int i = left; i <= left + k - 1; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
