#include <bits/stdc++.h>
using namespace std;

// Approach-1 (Arithmetic Digit Sum)
// T.C : O(n * D)
// S.C : O(1)

class Solution
{
public:
  int smallestIndex(vector<int> &nums)
  {
    for (int i = 0; i < nums.size(); i++)
    {
      int num = nums[i];
      int sum = 0;

      while (num > 0)
      {
        sum += num % 10;
        num /= 10;
      }

      if (sum == i)
      {
        return i;
      }
    }

    return -1;
  }
};

// Approach-2 (String Digit Sum)
// T.C : O(n * D)
// S.C : O(D)

class Solution2
{
public:
  int smallestIndex(vector<int> &nums)
  {
    for (int i = 0; i < nums.size(); i++)
    {
      string s = to_string(nums[i]);
      int sum = 0;

      for (char ch : s)
      {
        sum += ch - '0';
      }

      if (sum == i)
      {
        return i;
      }
    }

    return -1;
  }
};