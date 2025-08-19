package src.java.main.intervals;

import java.util.Arrays;

/**
 * Write a function to check if a person can attend all the meetings scheduled without any time conflicts. Given an array intervals, where each element [s1, e1] represents a meeting starting at time s1 and ending at time e1, determine if there are any overlapping meetings. If there is no overlap between any meetings, return true; otherwise, return false.
 * Note that meetings ending and starting at the same time, such as (0,5) and (5,10), do not conflict.
 * Input:
 * intervals = [(1,5),(3,9),(6,8)]
 * Output:
 * false
 * Explanation: The meetings (1,5) and (3,9) overlap.
 * Input:
 * intervals = [(10,12),(6,9),(13,15)]
 * Output:
 * true
 * Explanation: There are no overlapping meetings, so the person can attend all.
 */
public class CanAttendMeetings {

    /**
     * A person can attend all meetings if and only if none of the meetings overlap.
     * By sorting the intervals by start time, we can easily check if any two consecutive intervals overlap.
     * We iterate over each interval, beginning with the second interval in the sorted list.
     * We compare the start time of the current interval with the end time of the previous interval.
     * If the start time of the current interval is less than the end time of the previous interval, then the two intervals overlap and the person cannot attend both meetings, so we return false.
     * Otherwise, the person can attend both meetings, and we continue to the next interval.
     * If we reach the end of the list without finding any overlapping intervals, then the person can attend all meetings, and we return true.
     * Time Complexity: O(n logn)
     * Space Complexity: O(1)
     *
     * @param intervals
     * @return
     */
    public Boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length <= 1)
            return true;

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1])
                return false;
        }
        return true;
    }
}
