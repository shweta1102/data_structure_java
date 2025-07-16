package src.java.main.stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= val <= 231 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */

import java.util.Stack;

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
public class MinStack {
    Stack<StackElement> customStack;

    public MinStack() {
        customStack = new Stack<StackElement>();
    }

    public void push(int val) {
        //if stack is not empty then peek the last node and compare the min value with the current value and push accprdingly
        if (!customStack.isEmpty()) {
            StackElement topElement = customStack.peek();
            if (topElement.getMinimum() < val) {
                customStack.push(new StackElement(topElement.getMinimum(), val));
                return;
            }
        }
        customStack.push(new StackElement(val, val));
        return;
    }

    public void pop() {
        customStack.pop();
    }

    public int top() {
        StackElement topElement = customStack.peek();
        return topElement.getVal();
    }

    public int getMin() {
        StackElement topElement = customStack.peek();
        return topElement.getMinimum();
    }

    private class StackElement {
        private int minimum;
        private int val;

        StackElement(int minimum, int val) {
            this.minimum = minimum;
            this.val = val;
        }

        public int getMinimum() {
            return this.minimum;
        }

        public int getVal() {
            return this.val;
        }
    }
}
