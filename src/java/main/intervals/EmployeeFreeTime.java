package src.java.main.intervals;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Write a function to find the common free time for all employees from a list called schedule. Each employee's schedule is represented by a list of non-overlapping intervals sorted by start times. The function should return a list of finite, non-zero length intervals where all employees are free, also sorted in order.
 * Input:
 * schedule = [[[2,4],[7,10]],[[1,5]],[[6,9]]]
 * Output:
 * [(5,6)]
 * Explanation: The three employees collectively have only one common free time interval, which is from 5 to 6.
 */
public class EmployeeFreeTime {
    public int[][] employeeFreeTime(int[][][] schedule) {
        // Your code goes here
        ArrayList<int[]> result = new ArrayList<int[]>();
        ArrayList<int[]> mergedList = new ArrayList<>();
        // merge all the intervals
        for (int[][] intervals : schedule) {
            for (int[] interval : intervals) {
                mergedList.add(interval);
            }
        }
        //sort them by start time
        Collections.sort(mergedList, (a, b) -> (a[0] - b[0]));
        //merge the overlapping intervals

        //check the non-over lapping intervals
        for (int i = 1; i < mergedList.size(); i++) {
            if (mergedList.get(i - 1)[1] <= mergedList.get(i)[0]) {
                result.add(new int[]{mergedList.get(i - 1)[1], mergedList.get(i)[0]});
            }
        }
        return result.toArray(new int[result.size()][2]);
    }
}
