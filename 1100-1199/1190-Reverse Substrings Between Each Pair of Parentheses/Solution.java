import java.util.*;

//Approach-1 (Matching Parentheses + Direction Reversal)
//T.C : O(n)
//S.C : O(n)

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();

        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } 
            else if (s.charAt(i) == ')') {
                int open = stack.pop();

                pair[open] = i;
                pair[i] = open;
            }
        }

        StringBuilder ans = new StringBuilder();

        int i = 0;
        int direction = 1;

        while (i >= 0 && i < n) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == ')') {
                i = pair[i];
                direction = -direction;
            } 
            else {
                ans.append(ch);
            }

            i += direction;
        }

        return ans.toString();
    }
}


//Approach-2 (Stack of Partial Strings)
//T.C : O(n^2) worst-case
//S.C : O(n)

class Solution2 {
    public String reverseParentheses(String s) {
        Deque<StringBuilder> stack = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(current);
                current = new StringBuilder();
            } 
            else if (ch == ')') {
                current.reverse();

                StringBuilder outer = stack.pop();
                outer.append(current);

                current = outer;
            } 
            else {
                current.append(ch);
            }
        }

        return current.toString();
    }
}