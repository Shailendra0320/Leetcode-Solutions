// Approach-1 (Remainder DP with New State Array)
// T.C : O(n * k)
// S.C : O(k)

class Solution
{
public:
  vector<long long> resultArray(vector<int> &nums, int k)
  {

    vector<long long> answer(k, 0);
    vector<long long> dp(k, 0);

    for (int num : nums)
    {

      int mod = num % k;
      vector<long long> next(k, 0);

      // Start a new subarray
      next[mod] = 1;

      // Extend previous subarrays
      for (int rem = 0; rem < k; rem++)
      {

        int newRem = (int)(1LL * rem * mod % k);

        next[newRem] += dp[rem];
      }

      // Add all current-ending subarrays
      for (int rem = 0; rem < k; rem++)
      {
        answer[rem] += next[rem];
      }

      dp = next;
    }

    return answer;
  }
};

// Approach-2 (Remainder DP with Rolling State)
// T.C : O(n * k)
// S.C : O(k)

class Solution2
{
public:
  vector<long long> resultArray(vector<int> &nums, int k)
  {

    vector<long long> answer(k, 0);
    vector<long long> dp(k, 0);
    vector<long long> temp(k, 0);

    for (int num : nums)
    {

      int mod = num % k;

      fill(temp.begin(), temp.end(), 0);

      // Single-element subarray
      temp[mod] = 1;

      // Extend previous subarrays
      for (int rem = 0; rem < k; rem++)
      {

        int newRem = (int)(1LL * rem * mod % k);

        temp[newRem] += dp[rem];
      }

      // Add current-ending subarrays
      for (int rem = 0; rem < k; rem++)
      {
        answer[rem] += temp[rem];
      }

      // Swap states
      dp.swap(temp);
    }

    return answer;
  }
};