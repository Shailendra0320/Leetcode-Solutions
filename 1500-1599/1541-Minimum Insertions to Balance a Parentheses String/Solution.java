//Approach-1 (Greedy Required-Closing Counter)
//T.C : O(n)
//S.C : O(1)

import java.util.*;

class Solution {

  public int minInsertions(String s) {
    int answer = 0;
    int need = 0;

    for (char ch : s.toCharArray()) {

      if (ch == '(') {
        need += 2;

        if (need % 2 == 1) {
          answer++;
          need--;
        }

      } else {
        need--;

        if (need < 0) {
          answer++;
          need = 1;
        }
      }
    }

    return answer + need;
  }
}

// Approach-2 (Stack of Remaining Closing Requirements)
// T.C : O(n)
// S.C : O(n)

class Solution2 {

  public int minInsertions(String s) {
    Stack<Integer> stack = new Stack<>();
    int answer = 0;

    for (char ch : s.toCharArray()) {

      if (ch == '(') {

        if (!stack.isEmpty() && stack.peek() == 1) {
          answer++;
          stack.pop();
        }

        stack.push(2);

      } else {

        if (stack.isEmpty()) {
          answer++;
          stack.push(1);
        } else {
          int remaining = stack.pop() - 1;

          if (remaining > 0) {
            stack.push(remaining);
          }
        }
      }
    }

    while (!stack.isEmpty()) {
      answer += stack.pop();
    }

    return answer;
  }
}