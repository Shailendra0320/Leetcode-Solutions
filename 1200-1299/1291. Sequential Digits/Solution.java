//Approach-1 (String Enumeration)
//T.C : O(1)
//S.C : O(1)

import java.util.*;

class Solution {

  public List<Integer> sequentialDigits(
      int low,
      int high) {

    List<Integer> answer = new ArrayList<>();

    String digits = "123456789";

    int n = digits.length();

    for (int length = 1; length <= n; length++) {

      for (int start = 0; start + length <= n; start++) {

        String current = digits.substring(
            start,
            start + length);

        int number = Integer.parseInt(
            current);

        if (number >= low &&
            number <= high) {

          answer.add(
              number);
        }
      }
    }

    Collections.sort(
        answer);

    return answer;
  }
}

/*
 * //Approach-2 (Breadth-First Search)
 * //T.C : O(1)
 * //S.C : O(1)
 * 
 * import java.util.*;
 * 
 * class Solution {
 * 
 * public List<Integer> sequentialDigits(
 * int low,
 * int high
 * ) {
 * 
 * Queue<Integer> queue =
 * new LinkedList<>();
 * 
 * List<Integer> answer =
 * new ArrayList<>();
 * 
 * for (
 * int digit = 1;
 * digit <= 9;
 * digit++
 * ) {
 * 
 * queue.offer(
 * digit
 * );
 * }
 * 
 * while (
 * !queue.isEmpty()
 * ) {
 * 
 * int current =
 * queue.poll();
 * 
 * if (
 * current >= low &&
 * current <= high
 * ) {
 * 
 * answer.add(
 * current
 * );
 * }
 * 
 * int lastDigit =
 * current % 10;
 * 
 * if (lastDigit < 9) {
 * 
 * int next =
 * current * 10
 * + lastDigit + 1;
 * 
 * if (next <= high) {
 * 
 * queue.offer(
 * next
 * );
 * }
 * }
 * }
 * 
 * Collections.sort(
 * answer
 * );
 * 
 * return answer;
 * }
 * }
 */