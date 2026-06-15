//Approach-1 (Fast & Slow Pointer)
//T.C : O(n)
//S.C : O(1)

class Solution {

  public ListNode deleteMiddle(ListNode head) {

    if (head == null || head.next == null) {
      return null;
    }

    ListNode fast = head.next.next;
    ListNode slow = head;

    while (fast != null && fast.next != null) {

      fast = fast.next.next;
      slow = slow.next;
    }

    slow.next = slow.next.next;

    return head;
  }
}

/*
 * //Approach-2 (Count Length)
 * //T.C : O(n)
 * //S.C : O(1)
 * 
 * class Solution {
 * 
 * public ListNode deleteMiddle(ListNode head) {
 * 
 * if (head == null || head.next == null) {
 * return null;
 * }
 * 
 * int size = 0;
 * 
 * ListNode temp = head;
 * 
 * while (temp != null) {
 * size++;
 * temp = temp.next;
 * }
 * 
 * int middle = size / 2;
 * 
 * temp = head;
 * 
 * for (int i = 1; i < middle; i++) {
 * temp = temp.next;
 * }
 * 
 * temp.next = temp.next.next;
 * 
 * return head;
 * }
 * }
 */