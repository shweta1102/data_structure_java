package src.java.main.linkedlist;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * <p>
 * Example 2:
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class RotateList {
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

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null)
            return head;
        //find the length of the list and find effective k value --> k % length
        ListNode temp = head;
        int length = 0;
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        int rotation = k % length;
        if (rotation == 0)
            return head;
        //remove last k nodes from the list and join them to front
        ListNode prev = null;
        ListNode kNode = null;
        ListNode lastNode = null;
        int position = 0;
        temp = head;
        while (temp != null) {
            if (position == rotation) {
                kNode = head;
            }
            lastNode = temp.next == null ? temp : null;
            temp = temp.next;
            position++;
            prev = kNode;
            kNode = kNode != null ? kNode.next : null;
        }
        prev.next = null;
        lastNode.next = head;
        head = kNode;
        return head;
    }
}
