package src.java.main.linkedlist;

/**
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 * <p>
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 * <p>
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * <p>
 * Return the head of the copied linked list.
 * <p>
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * <p>
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 * <p>
 * Example 1:
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * Example 2:
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * <p>
 * Example 3:
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random is null or is pointing to some node in the linked list.
 */
public class CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        //prepare copy list
        Node copyHead = null;
        Node original = head;
        Node copy = null;
        Node temp = null;
        while (original != null) {
            //create new node
            Node copyNode = new Node(original.val);
            if (copy != null) {
                copy.random = copyNode;
            }
            copyNode.next = original.next;
            if (copyHead == null) {
                copyHead = copyNode;
            }
            temp = original.next;
            original.next = copyNode;
            original = temp;
            copy = copyNode;
        }
        if (copy != null) {
            copy.random = null;
        }
        //adjust random pointer
        original = head;
        while (original != null) {
            original.next.random = original.random != null ? original.random.next : null;
            // System.out.println(original.next.random!=null?original.next.random.val:"null");
            original = original.next.next;
        }
        //adjust next pointer
        original = head;
        copy = copyHead;
        while (original != null) {
            original.next = original.next.next;
            copy.next = copy.next == null ? null : copy.next.next;
            original = original.next;
            copy = copy.next;
        }
        return copyHead;
    }
}
