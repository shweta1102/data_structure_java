package src.java.main.dp;

/**
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class LongestPalindromicSubstring {

    private String palinString;

    public String longestPalindrome(String s) {
        palinString = null;
        boolean[][] memo = new boolean[s.length()][s.length()];
        boolean[][] visited = new boolean[s.length()][s.length()];
        islongestPalindrome(s, 0, s.length() - 1, memo, visited);
        return palinString;
    }

    private boolean islongestPalindrome(String s, int left, int right, boolean[][] memo, boolean[][] visited) {
        if (visited[left][right]) {
            return memo[left][right];
        }
        visited[left][right] = true;
        if (left > right)
            return false;
        if (s.charAt(left) == s.charAt(right)
                && (right == left || right == left + 1 || islongestPalindrome(s, left + 1, right - 1, memo, visited))) {
            String temp = s.substring(left, right + 1);
            palinString = palinString == null || (palinString.length() < temp.length()) ? temp : palinString;
            memo[left][right] = true;
            return true;
        }
        islongestPalindrome(s, left, right - 1, memo, visited);
        islongestPalindrome(s, left + 1, right, memo, visited);
        return false;
    }

    /**
     * Select one character in the string and then keep exanding to left and right till it is valid palindrome.
     * If the palindrome is greater than the previous palindromes, store it as longest palindrome substring.
     * Time Complexity: O(n^2) as for each character in the string we might expand till whole string length.
     * Space Complexity: O(1)
     */
    public String longestPalindromeExpandFromCorner(String s) {
        int maxLength = 0;
        int start = 0;
        int end = 0;

        //for each character, consider it as center of the palindrome string and check the maximum possible palindrome string with it as center.
        // We can have one or 2 characters in center for odd and even palindrome string.
        for (int i = 0; i < s.length(); i++) {
            //case when the substring have one character in the center
            int odd = expandFromCorner(s, i, i);
            //case when the substring have 2 characters int he center
            int even = expandFromCorner(s, i, i + 1);
            maxLength = Math.max(odd, even);
            //if we find the palindrome with length greater than the existing one then recalculate start and end index for the palindrome string
            if (maxLength > (end - start + 1)) {
                start = i - (maxLength - 1) / 2;
                end = i + maxLength / 2;
                //System.out.println(i + " " + start + " " + end);
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandFromCorner(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param s
     * @return longest substring
     */
    private String longestPalindromeWithManachersAlgo(String s) {
        StringBuilder newString = new StringBuilder();
        newString.append("^");
        for (char c : s.toCharArray()) {
            newString.append("#");
            newString.append(c);
        }
        newString.append("#$");
        String newS = newString.toString();
        int c = 0;
        int r = 0;
        int[] p = new int[newS.length()];
        int maxLength = 0;
        int center = 0;
        for (int i = 1; i < newS.length() - 1; i++) {
            int mirror = 2 * c - i;
            p[i] = i < r ? Math.min(p[mirror], r - i) : 0;
            //expand around the current index
            while (newS.charAt(i + p[i] + 1) == newS.charAt(i - p[i] - 1))
                p[i]++;
            if (i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
            if (p[i] > maxLength) {
                maxLength = p[i];
                center = i;
            }
        }
        return s.substring((center - maxLength) / 2, (center + maxLength) / 2);
    }
}
