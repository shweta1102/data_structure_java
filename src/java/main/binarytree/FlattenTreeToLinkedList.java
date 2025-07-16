package src.java.main.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 * <p>
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 * <p>
 * <p>
 * Example 1:
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 * <p>
 * Example 2:
 * Input: root = []
 * Output: []
 * <p>
 * Example 3:
 * Input: root = [0]
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 * <p>
 * <p>
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */
public class FlattenTreeToLinkedList {
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
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param root
     */
    public void flattenWithMorrisTraversal(TreeNode root) {
        TreeNode current = root;
        TreeNode runner = null;
        while (current != null) {
            if (current.left != null) {
                runner = current.left;
                while (runner.right != null) {
                    runner = runner.right;
                }
                runner.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }

    TreeNode prev = null;

    /**
     * Time Complexity:O(n) as we store previous reverse pre order node.
     * Space Complexity: O(n) for recursion stack --> equal to height of the tree can be maximum equal to number of nodes
     *
     * @param root
     */
    public void flattenWithRecursionAndOn(TreeNode root) {
        if (root == null)
            return;
        flattenWithRecursionAndOn(root.right);
        flattenWithRecursionAndOn(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    /**
     * TimeComplexity: O(n^2) --> at each node we need to traverse the list till end to set the right pointer.
     * Space Complexity:  O(n) for recursion stack --> equal to height of the tree can be maximum equal to number of nodes
     * <p>
     * Worst case is when we have all the nodes in left.
     *
     * @param root
     */
    public void flattenWithRecursion(TreeNode root) {
        if (root == null)
            return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flattenWithRecursion(left);
        flattenWithRecursion(right);
        root.right = left;
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        current.right = right;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param root
     */
    public void flattenWithQueue(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        constructQueue(queue, root);
        TreeNode pre = queue.remove();
        TreeNode current = null;
        while (!queue.isEmpty()) {
            current = queue.remove();
            pre.right = current;
            pre.left = null;
            pre = current;
        }
    }

    private void constructQueue(Queue<TreeNode> queue, TreeNode root) {
        if (root == null)
            return;
        queue.add(root);
        if (root.left != null)
            constructQueue(queue, root.left);
        if (root.right != null)
            constructQueue(queue, root.right);
    }
}
