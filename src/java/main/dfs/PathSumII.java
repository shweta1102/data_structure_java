package src.java.main.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 * <p>
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * Example 3:
 * <p>
 * Input: root = [1,2], targetSum = 0
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class PathSumII {

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

    List<List<Integer>> result;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        result = new ArrayList<>();
        pathSumHelper(root, targetSum, new ArrayList<Integer>());
        return result;
    }

    /**
     * Time Complexity: O(N),where N is the number of nodes in the binary tree. We visit each node via each recursive call to dfs exactly once. Each recursive call does a constant amount of work.
     * Space Complexity: O(N), where N is the number of nodes in the binary tree, for the space that it takes to allocate each recursive call frame on the call stack. The space taken up by the result list is in the worst case equal to the number of leaf nodes in the binary tree, which is also O(N).
     *
     * @param root
     * @param targetSum
     * @param current
     */
    private void pathSumHelper(TreeNode root, int targetSum, ArrayList<Integer> current) {
        if (root == null)
            return;
        current.add(root.val);
        if (root.left == null && root.right == null && targetSum == root.val) {
            result.add(new ArrayList(current));
            current.removeLast();
            return;
        }
        pathSumHelper(root.left, targetSum - root.val, current);
        pathSumHelper(root.right, targetSum - root.val, current);
        current.removeLast();
    }
}
