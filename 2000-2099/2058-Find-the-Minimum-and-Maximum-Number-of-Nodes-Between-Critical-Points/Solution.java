/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */

//Approach-1 (One Pass + Constant Space)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public int[] nodesBetweenCriticalPoints(
        ListNode head
    ) {

        int[] answer =
            {-1, -1};

        if (
            head == null ||
            head.next == null ||
            head.next.next == null
        ) {
            return answer;
        }

        ListNode prev =
            head;

        ListNode curr =
            head.next;

        int index = 1;

        int firstCritical =
            -1;

        int previousCritical =
            -1;

        int lastCritical =
            -1;

        int minDistance =
            Integer.MAX_VALUE;

        while (
            curr.next != null
        ) {

            ListNode next =
                curr.next;

            boolean isCritical =
                (
                    curr.val > prev.val &&
                    curr.val > next.val
                ) ||
                (
                    curr.val < prev.val &&
                    curr.val < next.val
                );

            if (isCritical) {

                if (
                    firstCritical == -1
                ) {

                    firstCritical =
                        index;

                } else {

                    minDistance =
                        Math.min(
                            minDistance,
                            index -
                            previousCritical
                        );
                }

                previousCritical =
                    index;

                lastCritical =
                    index;
            }

            prev =
                curr;

            curr =
                next;

            index++;
        }

        if (
            firstCritical == lastCritical
        ) {
            return answer;
        }

        answer[0] =
            minDistance;

        answer[1] =
            lastCritical -
            firstCritical;

        return answer;
    }
}

// Approach-2 (Store Critical Positions)
// T.C : O(n)
// S.C : O(n)

import java.util.*;

class Solution {

  public int[] nodesBetweenCriticalPoints(
      ListNode head) {

    List<Integer> critical = new ArrayList<>();

    ListNode prev = head;

    ListNode curr = head.next;

    int index = 1;

    while (curr.next != null) {

      if ((curr.val > prev.val &&
          curr.val > curr.next.val) ||
          (curr.val < prev.val &&
              curr.val < curr.next.val)) {

        critical.add(
            index);
      }

      prev = curr;

      curr = curr.next;

      index++;
    }

    if (critical.size() < 2) {

      return new int[] {
          -1,
          -1
      };
    }

    int minDistance = Integer.MAX_VALUE;

    for (int i = 1; i < critical.size(); i++) {

      minDistance = Math.min(
          minDistance,
          critical.get(i) -
              critical.get(i - 1));
    }

    int maxDistance = critical.get(
        critical.size() - 1) -
        critical.get(0);

    return new int[] {
        minDistance,
        maxDistance
    };
  }
}