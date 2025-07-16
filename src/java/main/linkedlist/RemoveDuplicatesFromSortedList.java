package src.java.main.linkedlist;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * <p>
 * Example 2:
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 */
public class RemoveDuplicatesFromSortedList {

    public class ListNode {
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

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return head;
        ListNode temp = head;
        ListNode nextNode = head.next;
        ListNode prev = null;
        while (temp != null) {
            if (nextNode != null && temp.val == nextNode.val) {
                while (nextNode != null && temp.val == nextNode.val) {
                    nextNode = nextNode.next;
                }
                if (prev == null) {
                    head = nextNode;
                    temp = head;
                    nextNode = temp != null ? temp.next : null;
                    continue;
                } else {
                    temp = prev;
                    prev.next = nextNode;
                }
            }
            prev = temp;
            temp = temp != null ? temp.next : null;
            nextNode = temp != null ? temp.next : null;
        }
        return head;
    }
}
