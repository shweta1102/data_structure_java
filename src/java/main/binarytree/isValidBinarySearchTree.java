package src.java.main.binarytree;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * A valid BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [2,1,3]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 */
public class isValidBinarySearchTree {
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

    TreeNode prev = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        boolean leftResult = isValidBST(root.left);
        if (prev != null && root.val <= prev.val)
            return false;
        prev = root;
        boolean rightResult = isValidBST(root.right);
        return leftResult && rightResult;
    }

    /**
     * Time Complexity: O(n)
     * Space COmplexity: O(n) where N is the number of nodes in the binary tree, for the space that it takes to allocate each recursive call frame on the call stack.
     * @param root
     * @return
     */
    public boolean isValidBSTWithHelper(TreeNode root) {
        if (root.left == null && root.right == null)
            return true;
        return isValidBSTDfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBSTDfs(TreeNode root, int min, int max) {
        if (root == null)
            return true;
        if ((root.val == Integer.MIN_VALUE && root.left != null)
                || (root.val == Integer.MAX_VALUE && root.right != null) || root.val < min || root.val > max)
            return false;
        return isValidBSTDfs(root.left, min, root.val - 1) && isValidBSTDfs(root.right, root.val + 1, max);
    }
}
