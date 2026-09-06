
// Approach-1 (2D Dynamic Programming)
// T.C : O(n * m)
// S.C : O(n * m)

class Solution
{
public:
  int numDistinct(string s, string t)
  {
    int n = s.length();
    int m = t.length();

    vector<vector<long long>> dp(
        n + 1,
        vector<long long>(m + 1, 0));

    for (int i = 0; i <= n; i++)
    {
      dp[i][0] = 1;
    }

    for (int i = 1; i <= n; i++)
    {
      for (int j = 1; j <= m; j++)
      {

        if (s[i - 1] == t[j - 1])
        {
          dp[i][j] =
              dp[i - 1][j - 1] +
              dp[i - 1][j];
        }
        else
        {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return (int)dp[n][m];
  }
};

// Approach-2 (1D Dynamic Programming)
// T.C : O(n * m)
// S.C : O(m)

class Solution2
{
public:
  int numDistinct(string s, string t)
  {
    int n = s.length();
    int m = t.length();

    vector<long long> dp(m + 1, 0);

    dp[0] = 1;

    for (int i = 0; i < n; i++)
    {

      for (int j = m; j >= 1; j--)
      {

        if (s[i] == t[j - 1])
        {
          dp[j] += dp[j - 1];
        }
      }
    }

    return (int)dp[m];
  }
};