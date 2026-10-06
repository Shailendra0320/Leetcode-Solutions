//Approach-1 (Greedy Balance Counting)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public int minAddToMakeValid(String s) {
        int open = 0;
        int answer = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    answer++;
                }
            }
        }

        return answer + open;
    }
}

//Approach-2 (Stack-Based Matching)
//T.C : O(n)
//S.C : O(n)

import java.util.*;

class Solution2 {

    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(ch);
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
        }

        return stack.size();
    }
}