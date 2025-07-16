package src.java.main.linkedlist;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 * <p>
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 * <p>
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * <p>
 * Follow up: Could you do it in one pass?
 */
public class ReverseNodesBetweenRange {
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

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right)
            return head;
        ListNode origin = null;
        ListNode leftNode = null;
        ListNode rightNode = null;
        ListNode temp = head;
        ListNode previous = null;
        int position = 1;
        //find the left node
        while (temp != null && position <= left) {
            if (position == left) {
                leftNode = temp;
                origin = previous;
                break;
            }
            previous = temp;
            temp = temp.next;
            position++;
        }

        previous = origin;
        ListNode revertedHead = leftNode;
        temp = null;
        //reverse the nodes between left to right
        while (position <= right) {
            temp = leftNode.next;
            leftNode.next = previous;
            previous = leftNode;
            leftNode = temp;
            position++;
        }
        //adjust the pointers for nodes before and after the left-right nodes
        revertedHead.next = leftNode;
        //set head if the left position is pointing to the first node
        if (origin != null) {
            origin.next = previous;
        } else {
            head = previous;
        }
        return head;
    }
}
