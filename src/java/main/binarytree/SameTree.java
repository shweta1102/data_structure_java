package src.java.main.binarytree;

/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * <p>
 * Example 2:
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * <p>
 * Example 3:
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in both trees is in the range [0, 100].
 * -104 <= Node.val <= 104
 */
public class SameTree {
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if ((p == null && q != null) || (p != null && q == null) || (p.val != q.val))
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
