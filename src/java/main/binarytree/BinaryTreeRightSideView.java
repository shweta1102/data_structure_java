package src.java.main.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,null,5,null,4]
 * <p>
 * Output: [1,3,4]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1,2,3,4,null,null,null,5]
 * <p>
 * Output: [1,3,4,5]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: root = [1,null,3]
 * <p>
 * Output: [1,3]
 * <p>
 * Example 4:
 * <p>
 * Input: root = []
 * <p>
 * Output: []
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class BinaryTreeRightSideView {
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
     * Time Complexity: O(N) each node is visted once and we perform constant operation at each node
     * Space Complexity: O(N) In the worst case, each node in the tree is on its own level, so the output list will contain N elements.
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideViewWithBFS(TreeNode root) {
        if (root == null)
            return new ArrayList<Integer>();
        List<Integer> list = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            TreeNode next = queue.peek();

            if (current == null) {
                if (!queue.isEmpty())
                    queue.add(null);
            } else {
                if (next == null) {
                    list.add(current.val);
                }
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
        }
        return list;
    }

    /**
     * At each level the first node from the right is inserted to the list.
     * Identify that based on the list size.
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideViewWithOneNodeAtEachLevel(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        rightSideViewHelper(root, list, 0);
        return list;
    }

    private void rightSideViewHelper(TreeNode root, List<Integer> result, int level) {
        if (root == null)
            return;
        if (result.size() == level) {
            result.add(root.val);
        }
        rightSideViewHelper(root.right, result, level + 1);
        rightSideViewHelper(root.left, result, level + 1);
    }
}
