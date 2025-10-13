package src.java.main.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 */
public class SerializeDeserializeBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        StringBuilder sb = new StringBuilder();
        sb.append(root.val + ",");
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left == null)
                    sb.append("*,");
                else {
                    sb.append(node.left.val + ",");
                    queue.add(node.left);
                }
                if (node.right == null)
                    sb.append("*,");
                else {
                    sb.append(node.right.val + ",");
                    queue.add(node.right);
                }
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //if data is nullit is empty tree
        if (data == null)
            return null;
        String[] nodeVal = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(nodeVal[0]));
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        for (int i = 1; i < nodeVal.length; i++) {
            TreeNode qnode = queue.remove();
            //add leftNode if it is there
            if (!nodeVal[i].equals("*")) {
                qnode.left = new TreeNode(Integer.valueOf(nodeVal[i]));
                queue.add(qnode.left);
            }
            //add right if it is there
            if (!nodeVal[++i].equals("*")) {
                qnode.right = new TreeNode(Integer.valueOf(nodeVal[i]));
                queue.add(qnode.right);
            }
        }

        return root;
    }
}
