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
    public int longestValidParenthesesWithStack(String s) {
        //stack used to maintain the index of the last unmatched opening bracket for the closing bracket. current index of the closing bracket - the last unmatched index is equal to length of the valid substring
        Stack<Integer> indexStack = new Stack<Integer>();
        int len = 0;
        //push -1 to ease the calculation when we have unmatched first )
        indexStack.push(-1);
        int index = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                //for opening bracket push the index
                indexStack.push(index);
            } else {
                //for closing bracket pop the corresponding potential opening bracket index
                indexStack.pop();
                if (indexStack.isEmpty()) {
                    //if the stack is empty after pop, it means it is in valid closing. push current index to mark the unmatched index for the new substring
                    indexStack.push(index);
                } else {
                    //if the stack is not empty them calculate the len of the current valid substring based on the top of the stack index
                    len = Math.max(len, index - indexStack.peek());
                }
            }
            index++;
        }
        return len;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param s
     * @return
     */
    public int longestValidParenthesesWithTwoPass(String s) {
        int left = 0, right = 0;
        int maxLen = 0;
        //from left to right calculate valid substrings and update maxLen
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                left++;
            else
                right++;

            if (left == right)
                maxLen = Math.max(maxLen, left * 2);
            else if (right > left)
                left = right = 0;
        }

        left = right = 0;
        //from right to left calculate valid substrings and update maxLen
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(')
                left++;
            else
                right++;

            if (left == right)
                maxLen = Math.max(maxLen, left * 2);
            else if (left > right)
                left = right = 0;
        }

        return maxLen;

    }
}
