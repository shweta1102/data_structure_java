package src.java.main.array;

import java.util.HashMap;

/**
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 */
public class MajorityElement {
    public int majorityElementHashMap(int[] nums) {
        HashMap<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        int result = 0;
        int maxFrequency = 0;
        for (int i = 0; i < nums.length; i++) {
            frequency.put(nums[i], 1 + frequency.getOrDefault(nums[i], 0));
            if (frequency.get(nums[i]) > maxFrequency) {
                result = nums[i];
                maxFrequency = frequency.get(nums[i]);
            }
        }
        return result;
    }

    public int majorityElementO1Space(int[] nums) {
        int result = nums[0];
        int maxFrequency = 1;
        for (int i = 1; i < nums.length; i++) {
            if (result == nums[i]) {
                maxFrequency++;
            } else {
                maxFrequency--;
                if (maxFrequency == 0) {
                    result = nums[i];
                    maxFrequency = 1;
                }

            }
        }
        return result;
    }
}
