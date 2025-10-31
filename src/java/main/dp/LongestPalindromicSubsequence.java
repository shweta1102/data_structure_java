package src.java.main.dp;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 * <p>
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 */
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        return longestPalindromeSubseqDp(s);
    }

    private int longestPalindromeSubseqDp(String s) {
        int maxLen = 1;
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int len = 2; len <= s.length(); len++) {
            for (int start = 0; start <= s.length() - len; start++) {
                int end = start + len - 1;
                if (s.charAt(start) == s.charAt(end)) {
                    dp[start][end] = 2 + dp[start + 1][end - 1];
                } else {
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
                }
                maxLen = Math.max(maxLen, dp[start][end]);
            }
        }
        return maxLen;
    }

    private int longestPalindromeSubseqMemo(String s, int start, int end, int[][] memo, boolean[][] visited) {
        if (visited[start][end])
            return memo[start][end];
        visited[start][end] = true;
        if (start == end) {
            memo[start][end] = 1;
            return 1;
        }
        if (start > end)
            return 0;
        if (s.charAt(start) == s.charAt(end)) {
            int length = 2 + longestPalindromeSubseqMemo(s, start + 1, end - 1, memo, visited);
            memo[start][end] = length;
            return memo[start][end];
        }
        int length = Math.max(longestPalindromeSubseqMemo(s, start + 1, end, memo, visited),
                longestPalindromeSubseqMemo(s, start, end - 1, memo, visited));
        memo[start][end] = length;
        return memo[start][end];
    }

    private int longestPalindromeSubseqRecurssion(String s, int start, int end) {
        if (start == end)
            return 1;
        if (start > end)
            return 0;
        if (s.charAt(start) == s.charAt(end)) {
            return 2 + longestPalindromeSubseqRecurssion(s, start + 1, end - 1);
        }
        return Math.max(longestPalindromeSubseqRecurssion(s, start + 1, end),
                longestPalindromeSubseqRecurssion(s, start, end - 1));
    }
}
