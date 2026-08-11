// Approach-1 (HashSet + Sequential Prefix)
// T.C : O(n) average
// S.C : O(n)

class Solution
{
public:
  int missingInteger(
      vector<int> &nums)
  {

    unordered_set<int> seen;

    for (
        int val : nums)
    {

      seen.insert(val);
    }

    int prefixSum =
        nums[0];

    for (
        int i = 1;
        i < nums.size();
        i++)
    {

      if (
          nums[i] ==
          nums[i - 1] + 1)
      {

        prefixSum +=
            nums[i];
      }
      else
      {

        break;
      }
    }

    while (
        seen.count(
            prefixSum))
    {

      prefixSum++;
    }

    return prefixSum;
  }
};