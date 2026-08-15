// Approach 1 — XOR Observation + Boolean Check

class Solution
{
public:
  int longestSubsequence(vector<int> &nums)
  {
    int totalXor = 0;
    bool hasNonZero = false;

    for (int val : nums)
    {
      totalXor ^= val;

      if (val != 0)
      {
        hasNonZero = true;
      }
    }

    if (!hasNonZero)
    {
      return 0;
    }

    if (totalXor != 0)
    {
      return nums.size();
    }

    return nums.size() - 1;
  }

  // Time Complexity: O(n)
  // Space Complexity: O(1)
};

// Approach 2 — XOR + Non-Zero Count

class Solution
{
public:
  int longestSubsequence(vector<int> &nums)
  {
    int xorValue = 0;
    int nonZeroCount = 0;

    for (int num : nums)
    {
      xorValue ^= num;

      if (num != 0)
      {
        nonZeroCount++;
      }
    }

    if (xorValue != 0)
    {
      return nums.size();
    }

    if (nonZeroCount == 0)
    {
      return 0;
    }

    return nums.size() - 1;
  }

  // Time Complexity: O(n)
  // Space Complexity: O(1)
};