package src.java.main.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the maximum width of the given tree.
 * <p>
 * The maximum width of a tree is the maximum width among all levels.
 * <p>
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
 * <p>
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,3,2,5,3,null,9]
 * Output: 4
 * Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,3,2,5,null,null,9,6,null,7]
 * Output: 7
 * Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
 * Example 3:
 * <p>
 * <p>
 * Input: root = [1,3,2,5]
 * Output: 2
 * Explanation: The maximum width exists in the second level with length 2 (3,2).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 3000].
 * -100 <= Node.val <= 100
 */
public class MaximumWidthOfBinaryTree {
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

    private class QueueData {
        TreeNode node;
        int index;

        public QueueData(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int maxWidth = 0;
        Queue<QueueData> queue = new LinkedList<QueueData>();
        queue.add(new QueueData(root, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            int startIndex = 0;
            for (int i = 0; i < size; i++) {
                QueueData current = queue.remove();
                if (current.node.left != null)
                    queue.add(new QueueData(current.node.left, 2 * current.index));
                if (current.node.right != null)
                    queue.add(new QueueData(current.node.right, (2 * current.index) + 1));
                if (i == 0) {
                    startIndex = current.index;
                }
                if (i == size - 1) {
                    maxWidth = Math.max(maxWidth, current.index - startIndex + 1);
                }
            }

        }
        return maxWidth;
    }
}
