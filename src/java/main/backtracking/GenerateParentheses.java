package src.java.main.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: ["()"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 8
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        generateParenthesis(n, result, 0, 0, "");
        return result;
    }

    private void generateParenthesis(int n, List<String> result, int open, int close, String current) {
        if (open + close == n * 2) {
            result.add(new String(current));
            return;
        }
        if (open < n) {
            generateParenthesis(n, result, open + 1, close, current + "(");
        }
        if (close < open) {
            generateParenthesis(n, result, open, close + 1, current + ")");
        }
    }
}
