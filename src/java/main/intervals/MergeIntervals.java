package src.java.main.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class MergeIntervals {
    /**
     * Time Complexity: O(nlogn)
     * Space Complexity:  O(n)
     */
    public int[][] mergeBruteForce(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int start = 0;
        int end = 1;
        ArrayList<List<Integer>> list = new ArrayList<List<Integer>>();
        int index = 0;
        int rangeEnd = intervals[start][1];
        while (end <= intervals.length) {
            if (end == intervals.length) {
                list.add(Arrays.asList(intervals[start][0], Math.max(rangeEnd, intervals[end - 1][1])));
                break;
            }
            if (intervals[end][0] > rangeEnd) {
                list.add(Arrays.asList(intervals[start][0], Math.max(intervals[end - 1][1], rangeEnd)));
                index++;
                start = end;
                rangeEnd = intervals[start][1];
            }
            rangeEnd = Math.max(rangeEnd, intervals[end][1]);
            end++;
        }
        int[][] result = new int[list.size()][2];
        int i = 0;
        for (List<Integer> pair : list) {
            result[i][0] = pair.get(0);
            result[i][1] = pair.get(1);
            i++;
        }
        return result;
    }

    public int[][] mergeMoreReadable(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                merged.add(prev);
                prev = interval;
            }
        }

        merged.add(prev);

        return merged.toArray(new int[merged.size()][]);
    }
}
