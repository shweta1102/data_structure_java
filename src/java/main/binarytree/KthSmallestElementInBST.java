package src.java.main.binarytree;

/**
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * Example 2:
 * <p>
 * <p>
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 * <p>
 * <p>
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
 */
public class KthSmallestElementInBST {
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

    int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null)
            return -1;
        int leftResult = kthSmallest(root.left, k);
        if (leftResult != -1)
            return leftResult;
        count = count + 1;
        if (count == k)
            return root.val;
        int rightResult = kthSmallest(root.right, k);
        if (rightResult != -1)
            return rightResult;
        return -1;
    }
}
