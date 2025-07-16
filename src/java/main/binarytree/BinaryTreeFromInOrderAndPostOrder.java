package src.java.main.binarytree;

import java.util.HashMap;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * Example 1:
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * Example 2:
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 */
public class BinaryTreeFromInOrderAndPostOrder {
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

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(inorder, postorder, postorder.length - 1, 0, inorder.length - 1, map);
    }

    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int postIndex, int start, int end,
                                     HashMap<Integer, Integer> map) {
        if (postIndex < 0 || end < start)
            return null;
        TreeNode root = new TreeNode(postorder[postIndex]);
        int index = map.get(postorder[postIndex]);
        root.right = buildTreeHelper(inorder, postorder, postIndex - 1, index + 1, end, map);
        root.left = root.right == null ? buildTreeHelper(inorder, postorder, postIndex - 1, start, index - 1, map)
                : buildTreeHelper(inorder, postorder, postIndex - (end - index + 1), start, index - 1, map);
        return root;
    }
}
