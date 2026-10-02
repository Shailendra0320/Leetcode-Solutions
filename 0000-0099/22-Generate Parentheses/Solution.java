import java.util.*;

//Approach-1 (Backtracking / DFS)
//T.C : O(Cn * n)
//S.C : O(n) auxiliary, excluding output

class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backtrack(int n, int open, int close,
                           StringBuilder current, List<String> ans) {

        if (open == n && close == n) {
            ans.add(current.toString());
            return;
        }

        if (open < n) {
            current.append('(');
            backtrack(n, open + 1, close, current, ans);
            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {
            current.append(')');
            backtrack(n, open, close + 1, current, ans);
            current.deleteCharAt(current.length() - 1);
        }
    }
}

//Approach-2 (Dynamic Programming / Catalan Decomposition)
//T.C : O(Cn * n)
//S.C : O(Cn * n)

class Solution2 {

    public List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            dp.add(new ArrayList<>());
        }

        dp.get(0).add("");

        for (int pairs = 1; pairs <= n; pairs++) {
            for (int leftPairs = 0; leftPairs < pairs; leftPairs++) {
                int rightPairs = pairs - 1 - leftPairs;

                for (String left : dp.get(leftPairs)) {
                    for (String right : dp.get(rightPairs)) {
                        dp.get(pairs).add("(" + left + ")" + right);
                    }
                }
            }
        }

        return dp.get(n);
    }
}