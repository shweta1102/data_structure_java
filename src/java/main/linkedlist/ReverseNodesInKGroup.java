package src.java.main.linkedlist;

/**
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * <p>
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 * <p>
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * <p>
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 * <p>
 * <p>
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 */
public class ReverseNodesInKGroup {
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

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode prev = null;
        ListNode nextnode = null;
        ListNode left = null;
        ListNode right = null;
        ListNode temp = head;
        int position = 1;
        while (temp != null) {
            if (position == 1) {
                left = temp;
            }
            if (position == k) {
                right = temp;
                nextnode = right.next;
                reverse(left, right);
                if (prev == null) {
                    head = right;
                } else {
                    prev.next = right;
                }
                prev = left;
                left.next = nextnode;
                temp = prev;
                position = 0;
            }
            temp = temp.next;
            position++;
        }
        return head;
    }

    private void reverse(ListNode left, ListNode right) {
        ListNode prev = null;
        ListNode temp = left;
        ListNode current = left;
        ListNode rightNext = right.next;
        while (current != rightNext) {
            temp = temp.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
    }
}
