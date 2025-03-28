package src.java.main.hashmap;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is happy.
 * <p>
 * A happy number is a number defined by the following process:
 * <p>
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 231 - 1
 */
public class HappyNumber {
    public boolean isHappyWithSet(int n) {
        Set<Integer> uniqueNumbers = new HashSet<Integer>();
        while (n != 1 && !uniqueNumbers.contains(n)) {
            uniqueNumbers.add(n);
            int temp = n;
            int sum = 0;
            while (temp > 0) {
                sum += Math.pow((temp % 10), 2);
                temp = temp / 10;
            }
            n = sum;
        }
        return n == 1;
    }

    public boolean isHappyWithCycleDetection(int n) {
        int slow = n;
        int fast = n;
        do {
            // move slow to one next number
            slow = getNextNum(slow);

            // move fast to 2 next number
            fast = getNextNum(fast);
            fast = getNextNum(fast);
        } while (slow != fast && fast != 1);
        return fast == 1;
    }

    private int getNextNum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += Math.pow((n % 10), 2);
            n = n / 10;
        }
        return sum;
    }
}
