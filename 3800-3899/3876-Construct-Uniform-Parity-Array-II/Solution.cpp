// Approach-1 (Minimum Element + Parity Check)
// T.C : O(n)
// S.C : O(1)

class Solution
{
public:
  bool uniformArray(vector<int> &nums1)
  {

    int minValue = INT_MAX;

    for (int x : nums1)
    {

      minValue = min(minValue, x);
    }

    if (minValue % 2 == 1)
    {
      return true;
    }

    for (int x : nums1)
    {

      if (x % 2 == 1)
      {
        return false;
      }
    }

    return true;
  }
};

// Approach-2 (Single Pass: Minimum + Odd Flag)
// T.C : O(n)
// S.C : O(1)

class Solution
{
public:
  bool uniformArray(vector<int> &nums1)
  {

    int minValue = INT_MAX;
    bool hasOdd = false;

    for (int x : nums1)
    {

      minValue = min(minValue, x);

      if (x % 2 == 1)
      {
        hasOdd = true;
      }
    }

    if (minValue % 2 == 1)
    {
      return true;
    }

    return !hasOdd;
  }
};