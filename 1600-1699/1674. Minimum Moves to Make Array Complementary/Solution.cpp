class Solution
{
public:
  int minMoves(vector<int> &nums, int limit)
  {

    int len = limit + limit + 2;

    vector<int> lower(len, 0);
    vector<int> upper(len, 0);
    vector<int> target(len, 0);

    int n = nums.size();

    for (int i = 0; i < n / 2; i++)
    {

      int sum = nums[i] + nums[n - 1 - i];

      int mn = min(nums[i], nums[n - 1 - i]) + 1;

      int mx = max(nums[i], nums[n - 1 - i]) + limit;

      if (sum < len)
      {
        target[sum] += 1;
      }

      if (mn < len)
      {
        lower[mn] += 1;
      }

      if (mx < len)
      {
        upper[mx] += 1;
      }
    }

    int minCost = n;
    int cost = n;

    for (int i = 2; i < len; i++)
    {

      cost = cost - lower[i] - target[i] + target[i - 1] + upper[i - 1];

      minCost = min(minCost, cost);
    }

    return minCost;
  }
};