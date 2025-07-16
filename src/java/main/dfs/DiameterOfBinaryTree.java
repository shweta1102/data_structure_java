package src.java.main.dfs;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * <p>
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * <p>
 * The length of a path between two nodes is represented by the number of edges between them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 * <p>
 * Input: root = [1,2]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -100 <= Node.val <= 100
 */
public class DiameterOfBinaryTree {

    public class TreeNode {
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

    private int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        diameterOfBinaryTreeHelper(root);
        return diameter;
    }

    /**
     * Time Complexity: O(N) where N is the number of nodes in the binary tree. We visit each node exactly once, so the time complexity is O(N).
     * Space Complexity: O(N)  where N is the number of nodes in the binary tree. A total of N recursive calls are made, one for each node in the tree. Each recursive call requires a constant amount of space for the call frame, so the space complexity is O(N).
     * @param root
     * @return
     */
    private int diameterOfBinaryTreeHelper(TreeNode root) {
        if (root == null)
            return 0;
        int leftLength = diameterOfBinaryTreeHelper(root.left);
        int rightLength = diameterOfBinaryTreeHelper(root.right);
        diameter = Math.max(diameter, leftLength + rightLength);
        return Math.max(1 + leftLength, 1 + rightLength);
    }
}
