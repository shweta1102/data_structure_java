package src.java.main.binarytree;

import java.util.Stack;

/**
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 104].
 * 0 <= Node.val <= 105
 */
public class MinimumAbsoluteDifferenceBetweenNodesForBST {
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

    public int getMinimumDifferenceIterative(TreeNode root) {
        if (root == null)
            return 0;
        int minDistance = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode temp = root.left;
        //push left subtree
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        TreeNode current = null;
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            current = stack.pop();
            if (prev != null) {
                minDistance = Math.min(minDistance, (current.val - prev.val));
            }
            prev = current;
            if (current.right != null) {
                stack.push(current.right);
                temp = current.right.left;
                //push left subtree
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
        }
        return minDistance;
    }

    int prev = Integer.MAX_VALUE;

    public int getMinimumDifferenceRecursive(TreeNode root) {
        if (root == null)
            return Integer.MAX_VALUE;
        int leftMin = getMinimumDifferenceRecursive(root.left);
        int absoluteDiff = Math.abs(root.val - prev);
        prev = root.val;
        int rightMin = getMinimumDifferenceRecursive(root.right);
        return Math.min(leftMin, Math.min(absoluteDiff, rightMin));
    }
}
