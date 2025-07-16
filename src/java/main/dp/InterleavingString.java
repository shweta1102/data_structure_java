package src.java.main.dp;

import java.util.HashSet;
import java.util.Stack;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * <p>
 * An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Explanation: One way to obtain s3 is:
 * Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
 * Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
 * Since s3 can be obtained by interleaving s1 and s2, we return true.
 * Example 2:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
 * Example 3:
 * <p>
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lowercase English letters.
 * <p>
 * <p>
 * Follow up: Could you solve it using only O(s2.length) additional memory space?
 */
public class InterleavingString {

    /**
     * This is DFS approach.
     * We store the pointers to s1,s2 and s3 when s1 and s1 character both match with s3 character in stack.
     * We get back to the point when we selected s1 when both s1 and s2 matched and explore the other path.
     * Time Complexity: O(2^s3.length)
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleaveBruteForce(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length())
            return false;
        Stack<StackNode> stack = new Stack<>();
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        while (p3 < s3.length()) {
            if (p1 < s1.length() && p2 < s2.length() && s1.charAt(p1) == s3.charAt(p3)
                    && s2.charAt(p2) == s3.charAt(p3)) {
                stack.push(new StackNode(p1, p2, p3));
                p1++;
            } else if (p1 < s1.length() && s1.charAt(p1) == s3.charAt(p3)) {
                p1++;
            } else if (p2 < s2.length() && s2.charAt(p2) == s3.charAt(p3)) {
                p2++;
            } else {
                if (stack.isEmpty())
                    return false;
                StackNode stackNode = stack.pop();
                p3 = stackNode.p3;
                p2 = stackNode.p2 + 1;
                p1 = stackNode.p1;
            }
            p3++;
        }
        return true;
    }

    /**
     * Time Complexity: O(s2.length * s1.length)
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleaveBruteForceWithMemo(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length())
            return false;
        Stack<StackNode> stack = new Stack<>();
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        HashSet<String> set = new HashSet<String>();

        while (p3 < s3.length()) {
            while (set.contains(p1 + "_" + p2)) {
                if (stack.isEmpty()) {
                    return false;
                }
                StackNode stackNode = stack.pop();
                p3 = stackNode.p3 + 1;
                p2 = stackNode.p2 + 1;
                p1 = stackNode.p1;
            }
            set.add(p1 + "_" + p2);
            if (p1 < s1.length() && p2 < s2.length() && c1[p1] == c3[p3]
                    && c2[p2] == c3[p3]) {
                stack.push(new StackNode(p1, p2, p3));
                p1++;
            } else if (p1 < s1.length() && c1[p1] == c3[p3]) {
                p1++;
            } else if (p2 < s2.length() && c2[p2] == c3[p3]) {
                p2++;
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                StackNode stackNode = stack.pop();
                p3 = stackNode.p3;
                p2 = stackNode.p2 + 1;
                p1 = stackNode.p1;
            }
            p3++;
        }
        return true;
    }

    class StackNode {
        int p3;
        int p1;
        int p2;

        public StackNode(int p1, int p2, int p3) {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }
    }

    /**
     * Time Complexity:
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleaveDFS(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length())
            return false;
        boolean[][] invalidFlow = new boolean[s1.length() + 1][s2.length() + 1];
        return isInterleaveHelper(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0, invalidFlow);
    }

    public boolean isInterleaveHelper(char[] s1, char[] s2, char[] s3, int p1, int p2, int p3,
                                      boolean[][] invalidFlow) {
        if (invalidFlow[p1][p2])
            return false;
        if (p3 == s3.length && p1 == s1.length && p2 == s2.length)
            return true;
        //if we revisit the same p1,p2 again it will be invalid combination for interleaving
        invalidFlow[p1][p2] = true;
        boolean r1 = p1 < s1.length && s1[p1] == s3[p3]
                && isInterleaveHelper(s1, s2, s3, p1 + 1, p2, p3 + 1, invalidFlow);
        boolean r2 = p2 < s2.length && s2[p2] == s3[p3]
                && isInterleaveHelper(s1, s2, s3, p1, p2 + 1, p3 + 1, invalidFlow);
        return (r1 || r2);
    }

    /**
     * Time Complexity: O(s1.length * s2.length)
     * Space Complexity: O(s1.length * s2.length)
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave2DDP(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length())
            return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j != 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                }
                if (j == 0 && i > 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                }
                if (i > 0 && j > 0) {
                    dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                            || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
