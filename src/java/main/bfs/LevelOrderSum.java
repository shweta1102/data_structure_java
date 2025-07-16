package src.java.main.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the sum of the nodes at each level. The output should be a list containing the sum of the nodes at each level.
 * Example 1:
 * Input:
 * [1, 3, 4, null, 2, 7, null, 8]
 * Output: [1, 7, 9, 8]
 * Example 2:
 * Input:
 * [1, 2, 5, 3, null, null, 4]
 * Output: [1, 7, 3, 4]
 */
public class LevelOrderSum {
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

    /**
     * Time Complexity: O(N) where N is the number of nodes in the tree. We visit each node exactly once, and each node, we perform a constant amount of work, so the time complexity is O(N).
     * Space Complexity: O(N) In the worst case, each node is on its own level, so the output list will contain N elements.
     *
     * @param root
     * @return
     */
    public int[] levelOrderSum(TreeNode root) {
        // Your code goes here
        if (root == null)
            return new int[0];
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> sum = new ArrayList<Integer>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 1; i <= size; i++) {
                TreeNode currentNode = queue.remove();
                levelSum += currentNode.val;
                if (currentNode.left != null)
                    queue.add(currentNode.left);
                if (currentNode.right != null)
                    queue.add(currentNode.right);
            }
            sum.add(levelSum);
        }
        int[] result = new int[sum.size()];
        for (int i = 0; i < sum.size(); i++) {
            result[i] = sum.get(i);
        }
        return result;
    }

}
