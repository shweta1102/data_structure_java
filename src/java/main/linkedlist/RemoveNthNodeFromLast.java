package src.java.main.linkedlist;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * <p>
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [1,2], n = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class RemoveNthNodeFromLast {
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode nNode = null;
        ListNode temp = head;
        ListNode prev = null;
        int position = 1;
        while (temp != null) {
            if (position == n) {
                nNode = head;
            }
            temp = temp.next;
            prev = position > n ? nNode : prev;
            nNode = position > n ? nNode.next : nNode;
            position++;
        }
        if (prev == null) {
            head = nNode.next;
        } else {
            prev.next = nNode.next;
        }
        return head;
    }
}
