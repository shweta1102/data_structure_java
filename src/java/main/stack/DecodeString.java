package src.java.main.stack;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 * <p>
 * The test cases are generated so that the length of the output will never exceed 105.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 * <p>
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 * <p>
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */
public class DecodeString {
    /**
     * Time COmplexity: O(n)
     * Space Complexity: O(n)
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<String> stringStack = new Stack<String>();
        Stack<Integer> intStack = new Stack<Integer>();
        int currentNum = 0;
        String currentString = "";
        for (char ch : s.toCharArray()) {
            if (ch == '[') {
                intStack.push(currentNum);
                stringStack.push(currentString);
                currentString = "";
                currentNum = 0;
            } else if (ch == ']') {
                int num = intStack.pop();
                String prevString = stringStack.pop();
                currentString = prevString + currentString.repeat(num);
            } else if (Character.isDigit(ch)) {
                currentNum = currentNum * 10 + (ch - '0');
            } else {
                currentString = currentString + ch;
            }
        }
        return currentString;
    }
}
