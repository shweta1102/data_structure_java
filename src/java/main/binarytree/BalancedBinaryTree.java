package src.java.main.binarytree;

/**
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * Example 3:
 * <p>
 * Input: root = []
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 5000].
 * -104 <= Node.val <= 104
 */
public class BalancedBinaryTree {
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

    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root) >= 0;
    }

    public int isBalancedHelper(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        int leftHeight = isBalancedHelper(root.left);
        int rightHeight = isBalancedHelper(root.right);
        if (leftHeight < 0 || rightHeight < 0)
            return -1;
        int diff = Math.abs(leftHeight - rightHeight);
        return diff > 1 ? -1 : Math.max(leftHeight, rightHeight) + 1;
    }
}
