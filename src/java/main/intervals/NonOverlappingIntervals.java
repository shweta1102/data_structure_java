package src.java.main.intervals;

import java.util.Arrays;

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * <p>
 * Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 * <p>
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * <p>
 * <p>
 * Input: [[1,100],[11,22],[1,11],[2,12]]
 * Output: 2
 * <p>
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 */
public class NonOverlappingIntervals {
    /**
     * Time Complexity: O(n * log n) for sorting the array
     * Space Complexity: O(1)
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        //we need to remove the minimum possible intervals from the list
        //sort the list in ascending order of the end times because if the interval ends early we can include more intervals int the final list
        Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));
        //count the non-over alpping intervals --> the ones that starts after the earlier one ends
        //as first interval is already considered to be non-overlapping
        int nonOverLapping = 1;
        int endTime = intervals[0][1];
        //count the total overlapping intervals
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= endTime) {
                nonOverLapping++;
                endTime = intervals[i][1];
            }

        }
        return intervals.length - nonOverLapping;
    }
}
