package src.java.main.binarytree;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 * <p>
 * The path sum of a path is the sum of the node's values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 */
public class BinaryTreeMaximumPathSum {
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

    private int maxPathSum = -1001;

    public int maxPathSum(TreeNode root) {
        int maxPathSumFromTree = maxPathSumHelper(root);
        return Math.max(maxPathSumFromTree, maxPathSum);
    }

    public int maxPathSumHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = maxPathSumHelper(root.left);
        int rightSum = maxPathSumHelper(root.right);

        //set maxPathSum
        if (root.left == null && root.right == null) {
            maxPathSum = Math.max(maxPathSum, root.val);
        } else if (root.left == null) {
            maxPathSum = Math.max(maxPathSum, Math.max(root.val + rightSum, Math.max(root.val, rightSum)));
        } else if (root.right == null) {
            maxPathSum = Math.max(maxPathSum, Math.max(root.val + leftSum, Math.max(root.val, leftSum)));
        } else {
            maxPathSum = Math.max(maxPathSum,
                    Math.max(root.val + leftSum + rightSum, Math.max(leftSum, Math.max(root.val, rightSum))));
        }

        //return maximum value so far for the subtree
        return Math.max(root.val + leftSum, Math.max(root.val + rightSum, root.val));
    }

    public int maxPathSumHelperSimplified(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //if we get -ve value from the left or right subtree then don't consider them. Use 0 for calculation of maxPathSum. So now the values will be positive always
        int leftSum = Math.max(maxPathSumHelper(root.left), 0);
        int rightSum = Math.max(maxPathSumHelper(root.right), 0);

        //set maxPathSum
        maxPathSum = Math.max(maxPathSum, root.val + leftSum + rightSum);

        //return maximum value so far for the subtree
        return Math.max(root.val + leftSum, Math.max(root.val + rightSum, root.val));
    }
}
