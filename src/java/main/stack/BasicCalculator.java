package src.java.main.stack;

import java.util.Stack;

/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * <p>
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 * <p>
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 * <p>
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 * Every number and running calculation will fit in a signed 32-bit integer.
 */
public class BasicCalculator {
    public int calculate(String s) {
        int result = 0;
        int sign = 1;
        int number = 0;
        Stack<Integer> intStack = new Stack<Integer>();
        for (char c : s.toCharArray()) {
            //if it is a digit, it is part of the number
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                //if it is + then we need to add the current number to the result
                result += (sign * number);
                //System.out.println(result);
                number = 0;
                sign = 1;
            } else if (c == '-') {
                //add current number to the result and make sign as -1
                result += (sign * number);
                number = 0;
                sign = -1;
            } else if (c == '(') {
                //push current result and the sign to the stack
                intStack.push(result);
                intStack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                //calculate result for current number and add it with the result before opening bracket
                result += (sign * number);
                result = (intStack.pop() * result) + intStack.pop();
                number = 0;
                sign = 1;
            }
        }
        result += (sign * number);
        return result;
    }
}
