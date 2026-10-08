/*
Approach-1 (Depth Tracking)
T.C : O(n)
S.C : O(n)
*/

class Solution {
  public String removeOuterParentheses(String s) {
    StringBuilder answer = new StringBuilder();
    int depth = 0;

    for (char ch : s.toCharArray()) {
      if (ch == '(') {
        if (depth > 0) {
          answer.append(ch);
        }
        depth++;
      } else {
        depth--;
        if (depth > 0) {
          answer.append(ch);
        }
      }
    }

    return answer.toString();
  }
}

/*
 * Approach-2 (Primitive Boundary Tracking)
 * T.C : O(n)
 * S.C : O(n)
 */

class Solution2 {
  public String removeOuterParentheses(String s) {
    StringBuilder answer = new StringBuilder();
    int balance = 0;
    int start = 0;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        balance++;
      } else {
        balance--;
      }

      if (balance == 0) {
        answer.append(s, start + 1, i);
        start = i + 1;
      }
    }

    return answer.toString();
  }
}