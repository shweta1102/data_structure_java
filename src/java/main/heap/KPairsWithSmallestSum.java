package src.java.main.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
 * <p>
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * <p>
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 * <p>
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length, nums2.length <= 105
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1 and nums2 both are sorted in non-decreasing order.
 * 1 <= k <= 104
 * k <= nums1.length * nums2.length
 */
public class KPairsWithSmallestSum {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> list = new PriorityQueue<int[]>((a, b) -> (a[0] - b[0]));
        for (int i = 0; i < nums1.length && i < k; i++) {
            list.add(new int[]{nums1[i] + nums2[0], 0});
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < k; i++) {
            int[] minPair = list.remove();
            int nums2Index = minPair[1];
            int pairSum = minPair[0];
            int nums1Element = pairSum - nums2[nums2Index];
            result.add(new ArrayList<Integer>(Arrays.asList(nums1Element, nums2[nums2Index])));
            if (nums2Index < nums2.length - 1) {
                list.add(new int[]{nums1Element + nums2[nums2Index + 1], nums2Index + 1});
            }
        }
        return result;
    }
}
