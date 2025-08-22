package src.java.main.twopointers;

/**
 * Given a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
 * <p>
 * Substrings that occur multiple times are counted the number of times they occur.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 * Example 2:
 * <p>
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
 * <p>
 * <p>
 * Input: s = "00100"
 * Output: 2
 * <p>
 * 1 <= s.length <= 105
 * s[i] is either '0' or '1'.
 */
public class CountBinarySubStrings {
    public int countBinarySubstrings(String s) {
        int current = 1;
        int prev = 0;
        int count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1))
                current++;
            else {
                count += Math.min(prev, current);
                prev = current;
                current = 1;
            }
        }
        return count + Math.min(prev, current);
    }
}
