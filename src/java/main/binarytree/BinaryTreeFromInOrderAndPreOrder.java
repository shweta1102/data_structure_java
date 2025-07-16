package src.java.main.binarytree;

import java.util.HashMap;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */
public class BinaryTreeFromInOrderAndPreOrder {
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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, inorder, 0, 0, inorder.length - 1, map);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preIndex, int start, int end,
                                     HashMap<Integer, Integer> map) {
        if (preIndex >= preorder.length || end < start)
            return null;
        TreeNode root = new TreeNode(preorder[preIndex]);
        int index = map.get(preorder[preIndex]);
        root.left = buildTreeHelper(preorder, inorder, preIndex + 1, start, index - 1, map);
        root.right = root.left == null ? buildTreeHelper(preorder, inorder, preIndex + 1, index + 1, end, map)
                : buildTreeHelper(preorder, inorder, preIndex + (index - start + 1), index + 1, end, map);
        return root;
    }

    private int getIndex(int[] inorder, int val) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val)
                return i;
        }
        return -1;
    }
}
