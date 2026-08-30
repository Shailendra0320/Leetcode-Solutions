#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
  int minimumDeletions(vector<int> &nums)
  {
    int n = nums.size();

    int minIndex = 0;
    int maxIndex = 0;

    for (int i = 1; i < n; i++)
    {
      if (nums[i] < nums[minIndex])
      {
        minIndex = i;
      }

      if (nums[i] > nums[maxIndex])
      {
        maxIndex = i;
      }
    }

    int left = min(minIndex, maxIndex);
    int right = max(minIndex, maxIndex);

    // Remove both from the front.
    int fromFront = right + 1;

    // Remove both from the back.
    int fromBack = n - left;

    // Remove one from each side.
    int fromBoth = (left + 1) + (n - right);

    return min({fromFront,
                fromBack,
                fromBoth});
  }
};