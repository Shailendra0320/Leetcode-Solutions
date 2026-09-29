#include <bits/stdc++.h>
using namespace std;

// Approach-1 (3D Dynamic Programming)
// T.C : O(m * n * (m + n))
// S.C : O(m * n * (m + n))

class Solution
{
public:
  bool hasValidPath(vector<vector<char>> &grid)
  {
    int m = grid.size();
    int n = grid[0].size();
    int len = m + n - 1;

    if (len % 2 == 1)
    {
      return false;
    }

    if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(')
    {
      return false;
    }

    vector<vector<vector<bool>>> dp(
        m,
        vector<vector<bool>>(
            n,
            vector<bool>(len + 1, false)));

    dp[0][0][1] = true;

    for (int i = 0; i < m; i++)
    {
      for (int j = 0; j < n; j++)
      {
        if (i == 0 && j == 0)
        {
          continue;
        }

        int delta = grid[i][j] == '(' ? 1 : -1;
        int remaining = len - (i + j + 1);

        for (int balance = 0; balance <= len; balance++)
        {
          int prevBalance = balance - delta;

          if (prevBalance < 0 || prevBalance > len)
          {
            continue;
          }

          if ((i > 0 && dp[i - 1][j][prevBalance]) ||
              (j > 0 && dp[i][j - 1][prevBalance]))
          {

            if (balance <= remaining)
            {
              dp[i][j][balance] = true;
            }
          }
        }
      }
    }

    return dp[m - 1][n - 1][0];
  }
};

// Approach-2 (Rolling Row DP / Space Optimized)
// T.C : O(m * n * (m + n))
// S.C : O(n * (m + n))

class Solution2
{
public:
  bool hasValidPath(vector<vector<char>> &grid)
  {
    int m = grid.size();
    int n = grid[0].size();
    int len = m + n - 1;

    if (len % 2 == 1)
    {
      return false;
    }

    if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(')
    {
      return false;
    }

    vector<vector<bool>> prev(
        n,
        vector<bool>(len + 1, false));

    prev[0][1] = true;

    for (int i = 0; i < m; i++)
    {
      vector<vector<bool>> cur(
          n,
          vector<bool>(len + 1, false));

      for (int j = 0; j < n; j++)
      {
        if (i == 0 && j == 0)
        {
          cur[0][1] = true;
          continue;
        }

        int delta = grid[i][j] == '(' ? 1 : -1;
        int remaining = len - (i + j + 1);

        for (int prevBalance = 0; prevBalance <= len; prevBalance++)
        {
          bool reachable = false;

          if (i > 0 && prev[j][prevBalance])
          {
            reachable = true;
          }

          if (j > 0 && cur[j - 1][prevBalance])
          {
            reachable = true;
          }

          if (!reachable)
          {
            continue;
          }

          int balance = prevBalance + delta;

          if (balance >= 0 && balance <= remaining)
          {
            cur[j][balance] = true;
          }
        }
      }

      prev = move(cur);
    }

    return prev[n - 1][0];
  }
};