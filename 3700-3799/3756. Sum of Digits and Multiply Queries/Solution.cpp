// Approach-1 (Prefix Sum + Rolling Hash)
// T.C : O(n + q)
// S.C : O(n)

class Solution
{
public:
  static constexpr int MOD = 1000000007;

  vector<int> sumAndMultiply(
      string s,
      vector<vector<int>> &queries)
  {

    int n = s.size();

    vector<long long> power10(
        n + 1);

    power10[0] = 1;

    for (int i = 1; i <= n; i++)
    {

      power10[i] =
          (power10[i - 1] * 10) % MOD;
    }

    vector<long long> prefixHash(
        n + 1);

    vector<int> prefixCount(
        n + 1);

    vector<int> prefixSum(
        n + 1);

    for (int i = 0; i < n; i++)
    {

      int digit =
          s[i] - '0';

      prefixCount[i + 1] =
          prefixCount[i];

      prefixSum[i + 1] =
          prefixSum[i];

      prefixHash[i + 1] =
          prefixHash[i];

      if (digit != 0)
      {

        prefixCount[i + 1]++;

        prefixSum[i + 1] += digit;

        prefixHash[i + 1] =
            (prefixHash[i] * 10 + digit) % MOD;
      }
    }

    vector<int> answer(
        queries.size());

    for (
        int i = 0;
        i < queries.size();
        i++)
    {

      int left =
          queries[i][0];

      int right =
          queries[i][1];

      int digitSum =
          prefixSum[right + 1] -
          prefixSum[left];

      int digits =
          prefixCount[right + 1] -
          prefixCount[left];

      long long number =
          (prefixHash[right + 1] -
           (prefixHash[left] *
            power10[digits]) %
               MOD +
           MOD) %
          MOD;

      answer[i] =
          (number *
           digitSum) %
          MOD;
    }

    return answer;
  }
};