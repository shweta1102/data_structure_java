package src.java.main.linkedlist;

/**
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * Example 2:
 * <p>
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class PartitionList {
    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null)
            return head;
        //maintain refernce for right side nodes
        ListNode right = null;
        //maintain refernce for left side nodes
        ListNode left = null;
        ListNode temp = head;
        //need head of right sode nodes to point left end to right start
        ListNode rightHead = null;
        while (temp != null) {
            //if value of current node is less than x move it to left list
            if (temp.val < x) {
                if (left == null) {
                    left = temp;
                    head = left;
                } else {
                    left.next = temp;
                    left = temp;
                }
            } else {
                //if value of current node is >=  x move it to right list
                if (right == null) {
                    right = temp;
                    rightHead = right;
                } else {
                    right.next = temp;
                    right = temp;
                }
            }
            temp = temp.next;
        }
        if (right != null)
            right.next = null;
        if (left != null)
            left.next = rightHead;
        return head;
    }
}
