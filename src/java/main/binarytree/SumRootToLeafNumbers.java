package src.java.main.binarytree;

/**
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * <p>
 * Each root-to-leaf path in the tree represents a number.
 * <p>
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
 * <p>
 * A leaf node is a node with no children.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3]
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [4,9,0,5,1]
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 9
 * The depth of the tree will not exceed 10.
 */
public class SumRootToLeafNumbers {
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

    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        return sumNumbersHelper(root, 0);
    }

    public int sumNumbersHelper(TreeNode root, int number) {
        //if leaf node then return the number
        if (root.left == null && root.right == null)
            return number * 10 + root.val;

        int left = root.left != null ? sumNumbersHelper(root.left, number * 10 + root.val) : 0;
        int right = root.right != null ? sumNumbersHelper(root.right, number * 10 + root.val) : 0;
        return left + right;
    }
}
