package src.java.main.binarysearch;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 */
public class MedianOfTwoSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if (n == 0 && m == 0) {
            return 0;
        } else if (n == 0) {
            return m % 2 == 0 ? (double) (nums2[m / 2] + nums2[(m / 2) - 1]) / 2 : nums2[m / 2];
        } else if (m == 0) {
            return n % 2 == 0 ? (double) (nums1[n / 2] + nums1[(n / 2) - 1]) / 2 : nums1[n / 2];
        }
        //we will do binary search on nums 1. So make sure it is done on shorter array
        if (n > m) {
            return findMedianSortedArrays(nums2, nums1);
        }
        //binary search on the nums1 to find index such nums1[index]>nums2[index]
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            //select mid1 for nums1
            int mid1 = left + (right - left) / 2;
            //select mid2 for nums2 as (half of total elements) - (mid1) --> because we want median of both arrays --> which is middle element of total elements
            int mid2 = ((m + n + 1) / 2) - (mid1) - 1;
            if (nums1[mid1] < nums2[mid2])
                left = mid1 + 1;
            else
                right = mid1 - 1;
        }
        int n1Index = left;
        int n2Index = ((m + n + 1) / 2) - (left);
        //left should be pointing to nums1 index
        int left1 = n1Index == 0 ? Integer.MIN_VALUE : nums1[n1Index - 1];
        int right1 = n1Index == n ? Integer.MAX_VALUE : nums1[n1Index];
        int right2 = n2Index == m ? Integer.MAX_VALUE : nums2[n2Index];
        int left2 = n2Index == 0 ? Integer.MIN_VALUE : nums2[n2Index - 1];

        return (m + n) % 2 == 0 ? (double) (Math.max(left1, left2) + Math.min(right1, right2)) / 2
                : Math.max(left1, left2);
    }
}
