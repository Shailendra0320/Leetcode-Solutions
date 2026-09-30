import java.util.*;

//Approach-1 (Nesting Depth Parity)
//T.C : O(n)
//S.C : O(1) auxiliary

class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];

        int depth = 0;

        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                depth++;
                answer[i] = depth % 2;
            } 
            else {
                answer[i] = depth % 2;
                depth--;
            }
        }

        return answer;
    }
}


//Approach-2 (Stack-Based Matching Pair Assignment)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                int group = (stack.size() + 1) % 2;

                answer[i] = group;
                stack.push(group);
            } 
            else {
                answer[i] = stack.pop();
            }
        }

        return answer;
    }
}