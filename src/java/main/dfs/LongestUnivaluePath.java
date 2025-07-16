package src.java.main.dfs;

/**
 * Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.
 * <p>
 * The length of the path between two nodes is represented by the number of edges between them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,4,5,1,1,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 5).
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,4,5,4,4,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 4).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 * The depth of the tree will not exceed 1000.
 */
public class LongestUnivaluePath {
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

    int longestPath;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return 0;
        longestPath = 0;
        longestUnivaluePathHelper(root, root.val);
        return longestPath;
    }

    /**
     * Time Complexity: O(N) where N is the number of nodes in the tree. We visit each node exactly once, and at each node, we perform constant time work.
     * Space Complexity: O(N)  where N is the number of nodes. A total of N call frames have to be allocated, one for each node in the tree in the worst case (in the case of a skewed tree, where each node only has one child).
     *
     * @param root
     * @param parentValue
     * @return
     */
    private int longestUnivaluePathHelper(TreeNode root, int parentValue) {
        if (root == null)
            return 0;
        int leftPath = longestUnivaluePathHelper(root.left, root.val);
        int rightPath = longestUnivaluePathHelper(root.right, root.val);
        longestPath = Math.max(longestPath, leftPath + rightPath);
        if (root.val == parentValue) {
            return 1 + Math.max(leftPath, rightPath);
        }
        return 0;
    }
}
