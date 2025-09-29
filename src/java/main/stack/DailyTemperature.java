package src.java.main.stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperature {
    /**
     * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: temperatures = [73,74,75,71,69,72,76,73]
     * Output: [1,1,4,2,1,1,0,0]
     * Example 2:
     * <p>
     * Input: temperatures = [30,40,50,60]
     * Output: [1,1,1,0]
     * Example 3:
     * <p>
     * Input: temperatures = [30,60,90]
     * Output: [1,1,0]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= temperatures.length <= 105
     * 30 <= temperatures[i] <= 100
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperaturesWithMonotonicStack(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Arrays.fill(answer, 0);
        Stack<Integer> decreasingStack = new Stack<Integer>();
        for (int i = 0; i < temperatures.length; i++) {
            while (i > 0 && !decreasingStack.isEmpty() && temperatures[i] > temperatures[decreasingStack.peek()]) {
                int index = decreasingStack.pop();
                answer[index] = i - index;
            }
            decreasingStack.push(i);
        }
        return answer;
    }
}
