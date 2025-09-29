package src.java.main.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 * <p>
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 * <p>
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 * Example 3:
 * <p>
 * <p>
 * <p>
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 104
 * 1 <= startTime[i] < endTime[i] <= 109
 * 1 <= profit[i] <= 104
 */
public class MaximumProfitInJobScheduling {
    class Job {
        int startTime;
        int endTime;
        int profit;

        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobList = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            jobList.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        jobList.sort((a, b) -> (a.startTime - b.startTime));
        int[] dp = new int[jobList.size()];
        dp[0] = jobList.get(0).profit;
        //dp i indicates maximum profit with ith job included
        int overAllMax = dp[0];
        for (int i = 1; i < jobList.size(); i++) {
            Job job = jobList.get(i);
            int maxProfit = job.profit;
            for (int j = i - 1; j >= 0; j--) {
                if (jobList.get(j).endTime <= job.startTime) {
                    maxProfit = Math.max(maxProfit, job.profit + dp[j]);
                }
            }
            dp[i] = maxProfit;
            overAllMax = Math.max(overAllMax, maxProfit);
        }
        return overAllMax;
    }
}
