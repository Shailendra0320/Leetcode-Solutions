import java.util.*;

//Approach-1 (Depth Counter / Balance)
//T.C : O(n)
//S.C : O(1)

class Solution {
    public int maxDepth(String s) {
        int depth = 0;
        int maxDepth = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            }
            else if (ch == ')') {
                depth--;
            }
        }

        return maxDepth;
    }
}


//Approach-2 (Stack-Based Parentheses Tracking)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
    public int maxDepth(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int maxDepth = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
                maxDepth = Math.max(maxDepth, stack.size());
            }
            else if (ch == ')') {
                stack.pop();
            }
        }

        return maxDepth;
    }
}