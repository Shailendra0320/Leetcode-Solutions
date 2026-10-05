//Approach-1 (Stack Evaluation)
//T.C : O(n)
//S.C : O(n)

import java.util.*;

class Solution {

    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(0);
            } else {
                int inner = stack.pop();
                int score = inner == 0 ? 1 : 2 * inner;

                stack.push(stack.pop() + score);
            }
        }

        return stack.peek();
    }
}

//Approach-2 (Depth-Based Scoring)
//T.C : O(n)
//S.C : O(1)

class Solution2 {

    public int scoreOfParentheses(String s) {
        int depth = 0;
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                depth++;
            } else {
                if (s.charAt(i - 1) == '(') {
                    answer += 1 << (depth - 1);
                }

                depth--;
            }
        }

        return answer;
    }
}