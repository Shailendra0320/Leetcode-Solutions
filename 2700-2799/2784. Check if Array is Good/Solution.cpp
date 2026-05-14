class Solution
{
public:
  bool isGood(vector<int> &nums)
  {
    int n = nums.size() - 1;
    vector<int> freq(n + 2, 0);
    for (int num : nums)
    {
      if (num > n)
        return false;
      freq[num]++;
    }
    for (int i = 1; i < n; i++)
    {
      if (freq[i] != 1)
        return false;
    }
    return freq[n] == 2;
  }
};

/*
class Solution {
public:
    bool isGood(vector<int>& nums) {
        if (nums.size() <= 1) return false;
        sort(nums.begin(), nums.end());
        int n = nums.size();
        if (nums[n-1] != n-1 || nums[n-2] != n-1) return false;
        for (int i = 1; i < n-1; i++) {
            if (nums[i-1] != i) return false;
        }
        return true;
    }
};
*/