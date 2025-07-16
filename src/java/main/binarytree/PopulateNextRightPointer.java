package src.java.main.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * <p>
 * Example 2:
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 6000].
 * -100 <= Node.val <= 100
 * Follow-up:
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class PopulateNextRightPointer {
    // Definition for a Node.
    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null)
            return root;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            Node nextNode = queue.peek();
            if (node == null) {
                if (!queue.isEmpty())
                    queue.add(null);
            } else {
                node.next = nextNode;
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return root;
    }
}
