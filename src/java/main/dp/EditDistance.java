package src.java.main.dp;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following three operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 * <p>
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 */
public class EditDistance {
    public int minDistanceWithMemo(String word1, String word2) {
        if (word1.isEmpty()) {
            return word2.length();
        }
        if (word2.isEmpty()) {
            return word1.length();
        }
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        return minDistanceRecursionAndMemo(word1, word2, 0, 0, memo);
    }

    private int minDistanceRecursionAndMemo(String word1, String word2, int i1, int i2, int[][] memo) {
        if (memo[i1][i2] > 0)
            return memo[i1][i2];
        if (i2 == word2.length()) {
            int result = word1.length() - i1;
            memo[i1][i2] = result;
            return memo[i1][i2];
        }
        if (i1 == word1.length()) {
            int result = word2.length() - i2;
            memo[i1][i2] = result;
            return memo[i1][i2];
        }
        //if the characters at the index are same move ahead
        if (word1.charAt(i1) == word2.charAt(i2))
            return minDistanceRecursionAndMemo(word1, word2, i1 + 1, i2 + 1, memo);
        //insert
        int d1 = minDistanceRecursionAndMemo(word1, word2, i1, i2 + 1, memo);
        //remove
        int d2 = minDistanceRecursionAndMemo(word1, word2, i1 + 1, i2, memo);
        //replace
        int d3 = minDistanceRecursionAndMemo(word1, word2, i1 + 1, i2 + 1, memo);
        int result = 1 + Math.min(d1, Math.min(d2, d3));
        memo[i1][i2] = result;
        return memo[i1][i2];
    }


    /**
     * Time Complexity: O(3^(max(word1 length, word2 length)))
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistanceWithRecursion(String word1, String word2) {
        if (word1.isEmpty()) {
            return word2.length();
        }
        if (word2.isEmpty()) {
            return word1.length();
        }
        return minDistanceRecursion(word1, word2, 0, 0);
    }

    private int minDistanceRecursion(String word1, String word2, int i1, int i2) {
        if (i2 == word2.length()) {
            return word1.length() - i1;
        }
        if (i1 == word1.length()) {
            return word2.length() - i2;
        }
        //if the characters at the index are same move ahead
        if (word1.charAt(i1) == word2.charAt(i2))
            return minDistanceRecursion(word1, word2, i1 + 1, i2 + 1);
        //insert
        int d1 = minDistanceRecursion(word1, word2, i1, i2 + 1);
        //remove
        int d2 = minDistanceRecursion(word1, word2, i1 + 1, i2);
        //replace
        int d3 = minDistanceRecursion(word1, word2, i1 + 1, i2 + 1);
        return 1 + Math.min(d1, Math.min(d2, d3));
    }


    public int minDistanceDP(String word1, String word2) {
        int w1 = word1.length();
        int w2 = word2.length();
        if (w1 == 0) {
            return w2;
        }
        if (w2 == 0) {
            return w1;
        }
        int[][] dp = new int[w1 + 1][w2 + 1];
        //to convert empty string to empty string we need 0 operations
        dp[0][0] = 0;
        for (int i = 0; i <= w1; i++) {
            for (int j = 0; j <= w2; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1]
                            : 1 + Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
                } else if (j == 0 && i != 0) {
                    dp[i][j] = i;
                } else if (i == 0 && j != 0) {
                    dp[i][j] = j;
                }
            }
        }
        return dp[w1][w2];
    }
}

