package src.java.main.hashmap;

import java.util.HashMap;

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 * <p>
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 */
public class ContainsDuplicate {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //only last seen index is enough to remember
        HashMap<Integer, Integer> numToIndex = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (numToIndex.containsKey(nums[i])) {
                if (Math.abs(i - numToIndex.get(nums[i])) <= k)
                    return true;
            }
            numToIndex.put(nums[i], i);
        }
        return false;
    }
}
