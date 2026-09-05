// ============================================================
// Approach-1 (Brute Force)
// T.C : O(n^2)
// S.C : O(1)
// ============================================================

class Solution
{
public:
  int firstStableIndex(vector<int> &nums, int k)
  {
    int n = nums.size();

    for (int i = 0; i < n; i++)
    {

      int maxLeft = nums[0];

      for (int j = 1; j <= i; j++)
      {
        maxLeft = max(maxLeft, nums[j]);
      }

      int minRight = nums[i];

      for (int j = i + 1; j < n; j++)
      {
        minRight = min(minRight, nums[j]);
      }

      if (maxLeft - minRight <= k)
      {
        return i;
      }
    }

    return -1;
  }
};

// ============================================================
// Approach-2 (Suffix Minimum + Running Prefix Maximum)
// T.C : O(n)
// S.C : O(n)
// ============================================================

class Solution2
{
public:
  int firstStableIndex(vector<int> &nums, int k)
  {
    int n = nums.size();

    vector<int> suffixMin(n);

    suffixMin[n - 1] = nums[n - 1];

    for (int i = n - 2; i >= 0; i--)
    {
      suffixMin[i] = min(nums[i], suffixMin[i + 1]);
    }

    int prefixMax = INT_MIN;

    for (int i = 0; i < n; i++)
    {
      prefixMax = max(prefixMax, nums[i]);

      int instability = prefixMax - suffixMin[i];

      if (instability <= k)
      {
        return i;
      }
    }

    return -1;
  }
};