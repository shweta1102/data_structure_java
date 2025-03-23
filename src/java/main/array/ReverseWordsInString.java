package src.java.main.array;

/**
 * Given an input string s, reverse the order of the words.
 * <p>
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * <p>
 * Return a string of the words in reverse order concatenated by a single space.
 * <p>
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 * <p>
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 * <p>
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 * <p>
 * <p>
 * Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 */
public class ReverseWordsInString {

    // detect the word and append
    public String reverseWords(String s) {
        String trimString = s.trim();
        StringBuilder reverse = new StringBuilder();
        int wordStart = 0;
        int wordEnd = trimString.length();
        int i = trimString.length() - 1;
        while (i >= 0) {
            while (i >= 0 && trimString.charAt(i) == ' ') {
                i--;
            }
            wordEnd = i + 1;
            // identify each word and append it to new string
            while (i >= 0 && trimString.charAt(i) != ' ') {
                i--;
            }
            wordStart = i + 1;
            reverse.append(trimString, wordStart, wordEnd);
            if (i > 0)
                reverse.append(" ");
        }
        return reverse.toString();
    }
}
