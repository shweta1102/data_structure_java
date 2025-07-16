package src.java.main.stack;

import java.util.Stack;

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * <p>
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * <p>
 * Note that:
 * <p>
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 * <p>
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 * <p>
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= tokens.length <= 104
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> result = new Stack<Integer>();
        for (String token : tokens) {
            //if it is operator pop 2 elements from the stack and peform operation
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int second = result.pop();
                int first = result.pop();
                //push the result to the stack
                switch (token) {
                    case "*":
                        result.push(first * second);
                        break;
                    case "+":
                        result.push(first + second);
                        break;
                    case "-":
                        result.push(first - second);
                        break;
                    case "/":
                        result.push(first / second);
                        break;
                    default:
                        break;
                }
                continue;
            }
            //else if it is integer push to stack
            result.push(Integer.valueOf(token));
        }
        return result.pop();
    }
}
