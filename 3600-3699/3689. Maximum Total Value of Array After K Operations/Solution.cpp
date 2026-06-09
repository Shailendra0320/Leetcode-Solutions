class Solution
{
public:
  long long maxTotalValue(vector<int> &nums, int k)
  {

    int minimumValue = nums[0];
    int maximumValue = nums[0];

    for (int value : nums)
    {

      minimumValue = min(minimumValue, value);
      maximumValue = max(maximumValue, value);
    }

    return 1LL * (maximumValue - minimumValue) * k;
  }
};