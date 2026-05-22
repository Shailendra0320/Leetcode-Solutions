class Solution {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    ListNode dummy = new ListNode(0);

    ListNode temp = dummy;

    int carry = 0;

    while (l1 != null || l2 != null || carry != 0) {

      int sum = carry;

      if (l1 != null) {

        sum += l1.val;

        l1 = l1.next;
      }

      if (l2 != null) {

        sum += l2.val;

        l2 = l2.next;
      }

      carry = sum / 10;

      temp.next = new ListNode(sum % 10);

      temp = temp.next;
    }

    return dummy.next;
  }
}

/*
 * class Solution {
 * 
 * public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
 * 
 * return solve(l1, l2, 0);
 * }
 * 
 * private ListNode solve(ListNode l1, ListNode l2, int carry) {
 * 
 * if (l1 == null && l2 == null && carry == 0) {
 * 
 * return null;
 * }
 * 
 * int sum = carry;
 * 
 * if (l1 != null) {
 * 
 * sum += l1.val;
 * }
 * 
 * if (l2 != null) {
 * 
 * sum += l2.val;
 * }
 * 
 * ListNode node = new ListNode(sum % 10);
 * 
 * node.next = solve(
 * l1 != null ? l1.next : null,
 * l2 != null ? l2.next : null,
 * sum / 10
 * );
 * 
 * return node;
 * }
 * }
 */