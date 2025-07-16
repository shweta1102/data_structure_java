package src.java.main.dividenconquer;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -105 <= Node.val <= 105
 * <p>
 * <p>
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class SortLinkedList {
    class ListNode {
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

    /**
     * Time Complexity O(n^2)
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        ListNode temp = head;
        ListNode newHead = null;
        ListNode next = null;
        while (temp != null) {
            next = temp.next;
            temp.next = null;
            newHead = insertInSortedList(temp, newHead);
            temp = next;
        }
        return newHead;
    }

    private ListNode insertInSortedList(ListNode node, ListNode head) {
        if (head == null)
            return node;
        ListNode temp = head;
        ListNode prev = null;
        while (temp != null && temp.val < node.val) {
            prev = temp;
            temp = temp.next;
        }
        if (prev != null) {
            prev.next = node;
        } else {
            head = node;
        }
        node.next = temp;

        return head;
    }

    /**
     * Time Complexity: O(nlogn)
     * @param head
     * @return
     */
    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;
        //find middle node and call merge sort recurssively
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        ListNode leftHead = head;
        ListNode rightHead = slow;
        prev.next = null;
        leftHead = mergeSort(leftHead);
        rightHead = mergeSort(rightHead);
        //after sorting two parts merge them and return the sorted merged list
        head = merge(leftHead, rightHead);
        return head;
    }

    private ListNode merge(ListNode left, ListNode right) {
        if (left == null && right == null)
            return null;
        if (left == null || right == null)
            return left == null ? right : left;
        ListNode head = null;
        ListNode temp = null;
        ListNode prev = null;
        ListNode small = left.val <= right.val ? left : right;
        ListNode large = left.val > right.val ? left : right;
        while (large != null && small != null) {
            if (head == null) {
                head = small;
            }
            temp = small;
            prev = small;
            while (temp != null && temp.val <= large.val) {
                prev = temp;
                temp = temp.next;
            }
            prev.next = large;
            small = large;
            large = temp;
        }
        if (large != null)
            prev.next = large;
        return head;
    }
}
