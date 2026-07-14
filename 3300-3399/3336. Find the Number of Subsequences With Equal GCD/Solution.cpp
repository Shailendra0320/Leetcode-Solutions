// Approach-1 (Dynamic Programming + Memoization)
// T.C : O(n × G²)
// S.C : O(n × G²)

class Solution
{
public:
  static const int MOD =
      1000000007;

  vector<vector<vector<int>>> dp;

  int subsequencePairCount(
      vector<int> &nums)
  {

    int n = nums.size();

    dp.assign(
        n,
        vector<vector<int>>(
            201,
            vector<int>(
                201,
                -1)));

    return solve(
        nums,
        0,
        0,
        0);
  }

  int solve(
      vector<int> &nums,
      int index,
      int gcd1,
      int gcd2)
  {

    if (index == nums.size())
    {

      return (
                 gcd1 > 0 &&
                 gcd2 > 0 &&
                 gcd1 == gcd2)
                 ? 1
                 : 0;
    }

    if (
        dp[index][gcd1][gcd2] != -1)
    {

      return dp[index][gcd1][gcd2];
    }

    int current =
        nums[index];

    long long answer = 0;

    answer += solve(
        nums,
        index + 1,
        gcd(gcd1, current),
        gcd2);

    answer += solve(
        nums,
        index + 1,
        gcd1,
        gcd(gcd2, current));

    answer += solve(
        nums,
        index + 1,
        gcd1,
        gcd2);

    return dp[index][gcd1][gcd2] =
               answer % MOD;
  }

  int gcd(
      int a,
      int b)
  {

    return b == 0
               ? a
               : gcd(
                     b,
                     a % b);
  }
};

/*
//Approach-2 (Brute Force Recursion)
//T.C : O(3^n)
//S.C : O(n)

class Solution {
public:

    static const int MOD =
        1000000007;

    int subsequencePairCount(
        vector<int>& nums
    ) {

        return solve(
            nums,
            0,
            0,
            0
        );
    }

    int solve(
        vector<int>& nums,
        int index,
        int gcd1,
        int gcd2
    ) {

        if (index == nums.size()) {

            return (
                gcd1 > 0 &&
                gcd2 > 0 &&
                gcd1 == gcd2
            ) ? 1 : 0;
        }

        int current =
            nums[index];

        long long answer = 0;

        answer += solve(
            nums,
            index + 1,
            gcd(gcd1, current),
            gcd2
        );

        answer += solve(
            nums,
            index + 1,
            gcd1,
            gcd(gcd2, current)
        );

        answer += solve(
            nums,
            index + 1,
            gcd1,
            gcd2
        );

        return answer % MOD;
    }

    int gcd(
        int a,
        int b
    ) {

        return b == 0
            ? a
            : gcd(
                b,
                a % b
            );
    }
};
*/