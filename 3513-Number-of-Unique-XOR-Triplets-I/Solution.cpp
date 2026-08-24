// Approach 1: Bit Manipulation
// Time Complexity: O(1)
// Space Complexity: O(1)

class Solution
{
public:
  int uniqueXorTriplets(vector<int> &nums)
  {
    int n = nums.size();

    if (n <= 2)
    {
      return n;
    }

    int bits = 32 - __builtin_clz(n);

    return 1 << bits;
  }
};

// Approach 2: Next Power of Two
// Time Complexity: O(log n)
// Space Complexity: O(1)

class Solution
{
public:
  int uniqueXorTriplets(vector<int> &nums)
  {
    int n = nums.size();

    if (n <= 2)
    {
      return n;
    }

    int ans = 1;

    while (ans <= n)
    {
      ans <<= 1;
    }

    return ans;
  }
};