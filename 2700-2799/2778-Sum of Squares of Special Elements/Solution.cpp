// Approach-1 (Divisibility Scan)
// T.C : O(n)
// S.C : O(1)

class Solution
{
public:
  int sumOfSquares(vector<int> &nums)
  {
    int n = nums.size();
    int answer = 0;

    for (int i = 1; i <= n; i++)
    {
      if (n % i == 0)
      {
        answer += nums[i - 1] * nums[i - 1];
      }
    }

    return answer;
  }
};

// Approach-2 (Enumerate Divisors in Pairs)
// T.C : O(sqrt(n))
// S.C : O(1)

class Solution2
{
public:
  int sumOfSquares(vector<int> &nums)
  {
    int n = nums.size();
    int answer = 0;

    for (int d = 1; d * d <= n; d++)
    {
      if (n % d == 0)
      {
        answer += nums[d - 1] * nums[d - 1];

        int other = n / d;

        if (other != d)
        {
          answer += nums[other - 1] * nums[other - 1];
        }
      }
    }

    return answer;
  }
};