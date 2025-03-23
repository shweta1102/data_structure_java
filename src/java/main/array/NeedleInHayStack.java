package src.java.main.array;

/**
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * Example 2:
 * <p>
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= haystack.length, needle.length <= 104
 * haystack and needle consist of only lowercase English characters.
 */
public class NeedleInHayStack {
    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length())
            return -1;
        int needleIndex = -1;
        for (int i = 0; i < haystack.length(); i++) {
            needleIndex = -1;
            if (haystack.charAt(i) == needle.charAt(0)) {
                int j = 0;
                int hayIndex = i;
                needleIndex = i;
                while (j < needle.length()) {
                    if (hayIndex >= haystack.length() || haystack.charAt(hayIndex) != needle.charAt(j)) {
                        needleIndex = -1;
                        break;
                    }
                    hayIndex++;
                    j++;
                }
                if (j == needle.length())
                    return needleIndex;
            }
        }
        return needleIndex;
    }

    public int strStrSubStringMatch(String haystack, String needle) {
        if (needle.length() > haystack.length())
            return -1;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (haystack.startsWith(needle, i))
                    return i;
            }
        }
        return -1;
    }
}
