package src.java.main.dp;

/**
 * You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
 * <p>
 * "1" -> 'A'
 * <p>
 * "2" -> 'B'
 * <p>
 * ...
 * <p>
 * "25" -> 'Y'
 * <p>
 * "26" -> 'Z'
 * <p>
 * However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").
 * <p>
 * For example, "11106" can be decoded into:
 * <p>
 * "AAJF" with the grouping (1, 1, 10, 6)
 * "KJF" with the grouping (11, 10, 6)
 * The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
 * Note: there may be strings that are impossible to decode.
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "12"
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * "12" could be decoded as "AB" (1 2) or "L" (12).
 * <p>
 * Example 2:
 * <p>
 * Input: s = "226"
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * <p>
 * Example 3:
 * <p>
 * Input: s = "06"
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 */
public class DecodeWays {
    public int numDecodings(String s) {
        return numDecodingsWithRecurssionWithMemo(s.toCharArray(), 0, new int[s.length() + 1]);
    }

    /**
     * Time Complexity: O(2^n) as at each node we have two possibility, single digit and double digit
     * Space Complexity: O(n) recursive call stack at max till length of the string
     *
     * @param s
     * @param index
     * @return
     */
    private int numDecodingsWithRecurssion(char[] s, int index) {
        if (index > s.length || (index == 0 && s[index] == '0'))
            return 0;
        if (index == s.length)
            return 1;

        //single
        int count = 0;
        if (s[index] != '0') {
            count = numDecodingsWithRecurssion(s, index + 1);
        }

        //double
        if ((index + 1) < s.length) {
            int number = 10 * (s[index] - '0') + (s[index + 1] - '0');
            count = (number >= 10 && number <= 26) ? count + numDecodingsWithRecurssion(s, index + 2) : count;
        }
        return count;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n) memo + O(n) call stack
     *
     * @param s
     * @param index
     * @param memo
     * @return
     */
    private int numDecodingsWithRecurssionWithMemo(char[] s, int index, int[] memo) {
        if (memo[index] != 0)
            return memo[index];
        if (index > s.length || (index == 0 && s[index] == '0'))
            return 0;
        if (index == s.length) {
            memo[index] = 1;
            return memo[index];
        }

        //single
        int count = 0;
        if (s[index] != '0') {
            count = numDecodingsWithRecurssionWithMemo(s, index + 1, memo);
        }

        //double
        if ((index + 1) < s.length) {
            int number = 10 * (s[index] - '0') + (s[index + 1] - '0');
            count = (number >= 10 && number <= 26) ? count + numDecodingsWithRecurssionWithMemo(s, index + 2, memo)
                    : count;
        }
        memo[index] = count;
        return memo[index];
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param s
     * @return
     */
    private int numDecodingsWithRecurssionWithDP(char[] s) {
        if (s[0] == '0')
            return 0;
        int[] dp = new int[s.length + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= s.length; i++) {
            //single
            if (s[i - 1] != '0')
                dp[i] += dp[i - 1];
            //double
            int number = 10 * (s[i - 2] - '0') + (s[i - 1] - '0');
            dp[i] += (number >= 10 && number <= 26) ? dp[i - 2] : 0;
        }
        return dp[s.length];
    }
}
