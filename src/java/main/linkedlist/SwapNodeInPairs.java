package src.java.main.linkedlist;

/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4]
 * <p>
 * Output: [2,1,4,3]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: head = []
 * <p>
 * Output: []
 * <p>
 * Example 3:
 * <p>
 * Input: head = [1]
 * <p>
 * Output: [1]
 * <p>
 * Example 4:
 * <p>
 * Input: head = [1,2,3]
 * <p>
 * Output: [2,1,3]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 */
public class SwapNodeInPairs {
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

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode temp = head;
        ListNode currentHead = null;
        ListNode newHead = null;
        ListNode prev = null;
        int k = 1;
        ListNode previousTail = null;
        while (temp != null) {
            if (k == 1) {
                currentHead = temp;
            }
            if (k == 3) {
                prev.next = null;
                ListNode reverseHead = reverse(currentHead);
                if (previousTail != null)
                    previousTail.next = reverseHead;
                previousTail = currentHead;
                if (newHead == null)
                    newHead = reverseHead;
                currentHead.next = temp;
                currentHead = temp;
                k = 1;
            }
            prev = temp;
            temp = temp.next;
            k++;
        }
        if (k == 3) {
            ListNode reverseHead = reverse(currentHead);
            if (newHead == null)
                newHead = reverseHead;
            if (previousTail != null)
                previousTail.next = reverseHead;
            currentHead.next = null;
        }
        return newHead;
    }

    private ListNode reverse(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public ListNode swapPairsWithDummyNode(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode first = head;
        while (first != null && first.next != null) {
            ListNode second = first.next;

            //swap nodes
            first.next = second.next;
            second.next = first;
            prev.next = second;

            prev = first;
            first = first.next;
        }

        return dummy.next;
    }
}
