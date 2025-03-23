package src.java.main.twopointers;

/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * <p>
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 100
 * 0 <= t.length <= 104
 * s and t consist only of lowercase English letters.
 */
public class IsSubSequence {

    public boolean isSubsequence(String s, String t) {
        int slength = s.length();
        int tlength = t.length();
        if (slength == 0)
            return true;
        if (tlength == 0 && slength > 0)
            return false;

        int sIndex = 0;
        for (int i = 0; i < tlength; i++) {
            if (t.charAt(i) == s.charAt(sIndex))
                sIndex++;

            if (sIndex == slength)
                break;
        }
        return sIndex == slength;
    }
}
