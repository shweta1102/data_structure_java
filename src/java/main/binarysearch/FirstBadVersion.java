package src.java.main.binarysearch;

import java.util.Random;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 * Example 2:
 * <p>
 * Input: n = 1, bad = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= bad <= n <= 231 - 1
 */
public class FirstBadVersion {
    Random random = new Random();

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + ((right - left) / 2);
            if (isBadVersion(mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    private boolean isBadVersion(int x) {
        return random.nextBoolean();
    }
}
