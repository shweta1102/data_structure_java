package src.java.main.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a sorted unique integer array nums.
 * <p>
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * <p>
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * Example 2:
 * <p>
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 20
 * -231 <= nums[i] <= 231 - 1
 * All the values of nums are unique.
 * nums is sorted in ascending order.
 */
public class SummaryRanges {
    public List<String> summaryRangesBruteForce(int[] nums) {
        if (nums.length == 0)
            return new ArrayList<>();

        ArrayList<String> result = new ArrayList<String>();
        StringBuilder range = new StringBuilder();
        range.append(nums[0]);
        if (nums.length == 1) {
            result.add(range.toString());
            return result;
        }

        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                if (i == nums.length - 1) {
                    range.append("->");
                    range.append(nums[i]);
                    result.add(range.toString());
                }
            } else {
                if (len > 1) {
                    range.append("->");
                    range.append(nums[i - 1]);
                }
                result.add(range.toString());
                range = new StringBuilder();
                len = 0;
                range.append(nums[i]);
                if (i == nums.length - 1)
                    result.add(range.toString());
            }
            len++;
        }
        return result;
    }

    public List<String> summaryRangesTwoPointer(int[] nums) {
        if (nums.length == 0)
            return new ArrayList<>();

        ArrayList<String> result = new ArrayList<String>();
        int start = 0;
        int end = start + 1;
        StringBuilder builder = new StringBuilder();
        while (end <= nums.length) {
            if (end == nums.length) {
                builder.append(nums[start]);
                if (end - start > 1) {
                    builder.append("->");
                    builder.append(nums[end - 1]);
                }
                result.add(builder.toString());
                return result;
            }
            if (nums[end] != nums[end - 1] + 1) {
                builder.append(nums[start]);
                if (end - start > 1) {
                    builder.append("->");
                    builder.append(nums[end - 1]);
                }
                result.add(builder.toString());
                builder = new StringBuilder();
                start = end;
            }
            end++;
        }
        return result;
    }
}
