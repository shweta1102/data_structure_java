package src.java.main.slidingwindow;

import java.util.HashSet;

/**
 * Given a string s, find the length of the longest substring without duplicate characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubStringWithoutRepetition {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s.length() == 1)
            return s.length();
        HashSet<Character> characters = new HashSet<Character>();
        int left = 0;
        int right = 0;
        int maxLen = 0;
        while (right < s.length()) {
            if (!characters.contains(s.charAt(right))) {
                characters.add(s.charAt(right));
                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            } else {
                // if repetitive character
                // calculate length till now and get the max one
                maxLen = Math.max(maxLen, right - left);
                // move left till we find the current character at right
                while (s.charAt(left) != s.charAt(right)) {
                    characters.remove(s.charAt(left));
                    left++;
                }
                characters.remove(s.charAt(left));
                left++;
                characters.add(s.charAt(right));
                right++;
            }
        }
        return maxLen;
    }
}
