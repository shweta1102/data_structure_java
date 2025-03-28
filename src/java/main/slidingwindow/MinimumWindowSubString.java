package src.java.main.slidingwindow;

import java.util.HashMap;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 * <p>
 * <p>
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class MinimumWindowSubString {
    /**
     * Time Complexity O(n^2)
     * Space Compexity O(1) --> we will need constant space for 2 hash maps
     */
    public String minWindowBruteForce(String s, String t) {
        HashMap<Character, Integer> tCount = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            tCount.put(t.charAt(i), tCount.getOrDefault(t.charAt(i), 0) + 1);
        }
        HashMap<Character, Integer> sCount = null;
        new HashMap<Character, Integer>();
        int left = 0;
        int right = 0;
        String result = null;
        while (right < s.length()) {
            // move left till we get the character that is present in t
            while (left < s.length() && !tCount.containsKey(s.charAt(left))) {
                left++;
            }
            right = left;
            sCount = new HashMap<Character, Integer>(tCount);
            while (!sCount.isEmpty() && right < s.length()) {
                if (sCount.containsKey(s.charAt(right))) {
                    sCount.put(s.charAt(right), sCount.get(s.charAt(right)) - 1);
                    if (sCount.get(s.charAt(right)) == 0)
                        sCount.remove(s.charAt(right));
                }
                right++;
            }
            if (sCount.isEmpty()) {
                String temp = s.substring(left, right);
                //System.out.println(temp);
                if (result == null || result.length() > temp.length()) {
                    result = temp;
                }
                left++;
                right = left;
            }
        }
        return result == null ? "" : result;
    }

    /**
     * Time Complexity: O(t.length + s.length)
     * Space Complexity: O(1) --> as the number of characters will be limited
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length())
            return "";
        HashMap<Character, Integer> sCount = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            sCount.put(t.charAt(i), sCount.getOrDefault(t.charAt(i), 0) + 1);
        }
        int left = 0;
        int right = 0;
        String result = null;
        int tCount = t.length();
        while (right < s.length()) {
            Character currentCh = s.charAt(right);
            if (sCount.containsKey(currentCh) && sCount.getOrDefault(currentCh, 0) > 0) {
                tCount--;
            }
            sCount.put(currentCh, sCount.getOrDefault(currentCh, 0) - 1);
            if (tCount == 0) {
                // find the valid start character
                Character leftCh = s.charAt(left);
                while (true) {
                    leftCh = s.charAt(left);
                    if (sCount.get(leftCh) == 0) {
                        break;
                    }
                    sCount.put(leftCh, sCount.getOrDefault(leftCh, 0) + 1);
                    left++;
                }
                // get the valid substring and compare with the earlier one
                String temp = s.substring(left, right + 1);
                if (result == null || result.length() > temp.length()) {
                    result = temp;
                }
                // move left
                sCount.put(leftCh, sCount.get(leftCh) + 1);
                left++;
                tCount++;
            }
            right++;
        }
        return result == null ? "" : result;
    }
}
