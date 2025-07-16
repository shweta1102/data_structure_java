package src.java.main.linkedlist;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * <p>
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Return the head of the merged linked list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 * <p>
 * Input: list1 = [], list2 = []
 * Output: []
 * Example 3:
 * <p>
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeSortedLinkedLists {

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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode temp = null;
        ListNode prev = null;
        if (list1 == null || list2 == null) {
            head = list1 == null ? list2 : list1;
        }
        //adjust pointers till one of the list reaches the end
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp = list1;
                //move pointer till we reach the point where list1 val > list2 val
                while (temp != null && temp.val <= list2.val) {
                    prev = temp;
                    temp = temp.next;
                }
                //set head node
                if (head == null) {
                    head = list1;
                }
                list1 = temp;
                prev.next = list2;
            } else {
                temp = list2;
                //move pointer till we reach the point where list1 val > list1 val
                while (temp != null && temp.val <= list1.val) {
                    prev = temp;
                    temp = temp.next;
                }
                //set head node
                if (head == null) {
                    head = list2;
                }
                list2 = temp;
                prev.next = list1;
            }
        }
        return head;
    }
}
