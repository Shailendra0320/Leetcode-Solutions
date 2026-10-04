//Approach-1 (Greedy Balance Range)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                low++;
                high++;
            } else if (ch == ')') {
                low--;
                high--;
            } else {
                low--;
                high++;
            }

            low = Math.max(low, 0);

            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
}

//Approach-2 (Two Stacks with Index Matching)
//T.C : O(n)
//S.C : O(n)

class Solution2 {

    public boolean checkValidString(String s) {
        Stack<Integer> openStack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                openStack.push(i);
            } else if (s.charAt(i) == '*') {
                starStack.push(i);
            } else {

                if (!openStack.isEmpty()) {
                    openStack.pop();
                } else if (!starStack.isEmpty()) {
                    starStack.pop();
                } else {
                    return false;
                }
            }
        }

        while (!openStack.isEmpty() && !starStack.isEmpty()) {

            if (openStack.pop() > starStack.pop()) {
                return false;
            }
        }

        return openStack.isEmpty();
    }
}