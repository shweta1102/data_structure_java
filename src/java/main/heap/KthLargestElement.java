package src.java.main.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Can you solve it without sorting?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class KthLargestElement {
    /**
     * Time Complexity: O(nlog k)
     * Space Complexity: O(k) heap if size k
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestWithHeap(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        //n inserts and (n-k) removal
        for (int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);
            if (heap.size() > k) {
                heap.remove();
            }
        }
        return heap.peek();
    }

    /**
     * Time Complexity: 3O(n)+O(r) , find min, find max,get count and find element based on the count, r is range of the value as we will traverse the count array
     * Space Complexity: O(maxPossible Value-minPossible Value) for storing count
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestWIthArray(int[] nums, int k) {
        //find min and max in the nums
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        //create and array to hold the count of the numbers in the array of size (max-min+1)
        int[] count = new int[max - min + 1];
        //traverse the nums array and increament count for each number
        for (int i = 0; i < nums.length; i++) {
            count[nums[i] - min]++;
        }
        //traverse the new count array from backwark and keep reducing k.when k==0 we found the element
        for (int i = count.length - 1; i >= 0; i--) {
            k -= count[i];
            if (k <= 0)
                return i + min;
        }
        return -1;
    }
}
