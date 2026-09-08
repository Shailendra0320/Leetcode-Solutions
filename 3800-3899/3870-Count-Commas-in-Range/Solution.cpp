// Approach-1 (Direct Mathematical Formula)
// T.C : O(1)
// S.C : O(1)

class Solution
{
public:
  int countCommas(int n)
  {
    return max(0, n - 999);
  }
};

// Approach-2 (Threshold-Based Counting)
// T.C : O(log_1000(n))
// S.C : O(1)

class Solution2
{
public:
  long long countCommas(long long n)
  {
    long long answer = 0;

    for (long long x = 1000; x <= n; x *= 1000)
    {
      answer += n - x + 1;
    }

    return answer;
  }
};