// Approach-1 (DP by Ending Character)
// T.C : O(26 * n) = O(n)
// S.C : O(26) = O(1)

class Solution
{
public:
  int distinctSubseqII(string s)
  {
    const long long MOD = 1'000'000'007;

    vector<long long> dp(26, 0);

    for (char ch : s)
    {
      int index = ch - 'a';

      long long total = 0;

      for (long long count : dp)
      {
        total = (total + count) % MOD;
      }

      dp[index] = (total + 1) % MOD;
    }

    long long answer = 0;

    for (long long count : dp)
    {
      answer = (answer + count) % MOD;
    }

    return (int)answer;
  }
};

// Approach-2 (Total Count + Last Occurrence)
// T.C : O(n)
// S.C : O(1)

class Solution2
{
public:
  int distinctSubseqII(string s)
  {
    const long long MOD = 1'000'000'007;

    long long total = 1;
    vector<long long> last(26, 0);

    for (char ch : s)
    {
      int index = ch - 'a';

      long long previousTotal = total;

      total = (2 * total - last[index] + MOD) % MOD;

      last[index] = previousTotal;
    }

    return (int)((total - 1 + MOD) % MOD);
  }
};