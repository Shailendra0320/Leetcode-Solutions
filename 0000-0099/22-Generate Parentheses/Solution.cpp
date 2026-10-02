#include <bits/stdc++.h>
using namespace std;

// Approach-1 (Backtracking / DFS)
// T.C : O(Cn * n)
// S.C : O(n) auxiliary, excluding output

class Solution
{

public:
  void backtrack(int n, int open, int close,
                 string &current, vector<string> &ans)
  {

    if (open == n && close == n)
    {
      ans.push_back(current);
      return;
    }

    if (open < n)
    {
      current.push_back('(');
      backtrack(n, open + 1, close, current, ans);
      current.pop_back();
    }

    if (close < open)
    {
      current.push_back(')');
      backtrack(n, open, close + 1, current, ans);
      current.pop_back();
    }
  }

  vector<string> generateParenthesis(int n)
  {
    vector<string> ans;
    string current;

    backtrack(n, 0, 0, current, ans);

    return ans;
  }
};

// Approach-2 (Dynamic Programming / Catalan Decomposition)
// T.C : O(Cn * n)
// S.C : O(Cn * n)

class Solution2
{

public:
  vector<string> generateParenthesis(int n)
  {
    vector<vector<string>> dp(n + 1);

    dp[0].push_back("");

    for (int pairs = 1; pairs <= n; pairs++)
    {
      for (int leftPairs = 0; leftPairs < pairs; leftPairs++)
      {
        int rightPairs = pairs - 1 - leftPairs;

        for (const string &left : dp[leftPairs])
        {
          for (const string &right : dp[rightPairs])
          {
            dp[pairs].push_back("(" + left + ")" + right);
          }
        }
      }
    }

    return dp[n];
  }
};