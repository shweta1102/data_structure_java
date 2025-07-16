package src.java.main.binarytree;

/**
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 * <p>
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * <p>
 * Design an algorithm that runs in less than O(n) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * <p>
 * Example 2:
 * Input: root = []
 * Output: 0
 * <p>
 * Example 3:
 * Input: root = [1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 5 * 104].
 * 0 <= Node.val <= 5 * 104
 * The tree is guaranteed to be complete.
 */
public class CountNodesInCompleteTree {
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

    public int countNodes(TreeNode root) {
        int h = getHeight(root);
        TreeNode current = root;
        int nodes = 0;
        while (current != null) {
            int rightHeight = getHeight(current.right);
            if (rightHeight == h - 1) {
                //this will calculate 2^h
                nodes += 1 << h;
                current = current.right;
            } else {
                //if right node height is lass than (parent node -1) --> the tree is full till h-1
                nodes += 1 << (h - 1);
                current = current.left;
            }
            h--;
        }
        return nodes;
    }

    private int getHeight(TreeNode root) {
        return root != null ? 1 + getHeight(root.left) : -1;
    }
}
