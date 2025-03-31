package src.java.main.hashmap;

import java.util.HashSet;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 * Example 3:
 * <p>
 * Input: nums = [1,0,1,2]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class LongestCommonSequence {
    // longest possible sequence can be maximum of length n
    public int longestConsecutive(int[] nums) {
        int length = 0;
        HashSet<Integer> set = new HashSet<Integer>(nums.length);
        for (int num : nums) {
            set.add(num);
        }
        // we only need to check all unique numbers, so can iterate set instead of nums
        // to reduce time complexity
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentLength = 1;
                while (set.contains(num + currentLength)) {
                    currentLength++;
                }
                length = Math.max(length, currentLength);
            }
        }
        return length;
    }
}
