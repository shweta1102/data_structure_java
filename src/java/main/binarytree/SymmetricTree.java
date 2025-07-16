package src.java.main.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * <p>
 * Example 2:
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 */
public class SymmetricTree {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetricHelp(root.left, root.right);
    }

    private boolean isSymmetricHelp(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null || left.val != right.val)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }

    private boolean isSymmetricWithStack(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(left);
        stack.push(right);
        while (!stack.isEmpty()) {
            TreeNode rightNode = stack.pop();
            TreeNode leftNode = stack.pop();
            if (rightNode == null && leftNode == null)
                continue;
            if (rightNode == null || leftNode == null || rightNode.val != leftNode.val)
                return false;
            stack.push(leftNode.left);
            stack.push(rightNode.right);
            stack.push(leftNode.right);
            stack.push(rightNode.left);
        }
        return true;
    }

    private boolean isSymmetricWithQueue(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(left);
        queue.add(right);
        while (!queue.isEmpty()) {
            TreeNode leftNode = queue.remove();
            TreeNode rightNode = queue.remove();
            if (rightNode == null && leftNode == null)
                continue;
            if (rightNode == null || leftNode == null || rightNode.val != leftNode.val)
                return false;
            queue.add(leftNode.left);
            queue.add(rightNode.right);
            queue.add(leftNode.right);
            queue.add(rightNode.left);
        }
        return true;
    }
}
