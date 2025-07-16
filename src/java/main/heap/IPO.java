package src.java.main.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 * <p>
 * You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
 * <p>
 * Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 * <p>
 * Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
 * <p>
 * The answer is guaranteed to fit in a 32-bit signed integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
 * Output: 4
 * Explanation: Since your initial capital is 0, you can only start the project indexed 0.
 * After finishing it you will obtain profit 1 and your capital becomes 1.
 * With capital 1, you can either start the project indexed 1 or the project indexed 2.
 * Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
 * Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= 105
 * 0 <= w <= 109
 * n == profits.length
 * n == capital.length
 * 1 <= n <= 105
 * 0 <= profits[i] <= 104
 * 0 <= capital[i] <= 109
 */
public class IPO {
    class Project {
        int profit;
        int capital;

        Project(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Project> maxHeap = new PriorityQueue<Project>((a, b) -> (b.profit - a.profit));
        PriorityQueue<Project> minHeap = new PriorityQueue<Project>((a, b) -> (a.capital - b.capital));

        //create min heap with the capital values
        for (int i = 0; i < capital.length; i++) {
            minHeap.add(new Project(profits[i], capital[i]));
        }

        while (k > 0) {
            //move the projects with valid capital to max heap with profits
            while (!minHeap.isEmpty() && minHeap.peek().capital <= w) {
                maxHeap.add(minHeap.remove());
            }
            if (maxHeap.isEmpty()) {
                break;
            }
            //take project with maximum profit and add it's capital w
            w += maxHeap.remove().profit;
            k--;
        }
        return w;
    }

    /**
     * This has better performance
     *
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     */
    public int findMaximizedCapitalWithOneList(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Project> maxHeap = new PriorityQueue<Project>((a, b) -> (b.profit - a.profit));
        List<Project> minHeap = new ArrayList<Project>();

        //create min heap with the capital values O(n)
        for (int i = 0; i < capital.length; i++) {
            minHeap.add(new Project(profits[i], capital[i]));
        }

        //O(nlog n)
        Collections.sort(minHeap, (a, b) -> a.capital - b.capital);

        int i = 0;
        //k removal and at max n addition to heap O(nlog n)
        while (k > 0) {
            //move the projects with valid capital to max heap with profits
            while (i < capital.length && minHeap.get(i).capital <= w) {
                maxHeap.add(minHeap.get(i));
                i++;
            }
            if (maxHeap.isEmpty()) {
                break;
            }
            //take project with maximum profit and add it's capital w
            w += maxHeap.remove().profit;
            k--;
        }
        return w;
    }
}
