package src.java.main.linkedlist;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbers {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        int sum = 0;
        ListNode l3 = null;
        ListNode temp = null;
        while (l1 != null || l2 != null) {
            //check if l1 is not equal to null and add it's value for sum
            if (l1 != null) {
                sum += l1.val;
            }
            //check if l2 is not equal to null and add it's value for sum
            if (l2 != null) {
                sum += l2.val;
            }
            //add carry from previous decimal place to sum
            sum += carry;
            //find if we have carry for this decimal place
            carry = sum / 10;
            //for the first node set l3 pointer
            if (l3 == null) {
                temp = new ListNode(sum % 10, null);
                l3 = temp;
            } else {
                //for all other nodes keep adding the new nodes
                temp.next = new ListNode(sum % 10, null);
                temp = temp.next;
            }
            //reset sum for the next decimal place
            sum = 0;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        //if we have carry remaining add last node for it
        if (carry > 0) {
            temp.next = new ListNode(carry, null);
        }
        return l3;
    }
}
