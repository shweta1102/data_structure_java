package src.java.main.stack;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 * <p>
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 * <p>
 * Input: s = ""
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 */
public class LongestValidParantheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> indexStack = new Stack<Integer>();
        int index = 0;
        int len = 0;
        indexStack.push(-1);
        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                indexStack.pop();
                if (indexStack.isEmpty()) {
                    indexStack.push(index);
                } else {
                    len = Math.max(len, index - indexStack.peek());
                }
            } else {
                indexStack.push(index);
            }
            index++;
        }
        return len;
    }
}
