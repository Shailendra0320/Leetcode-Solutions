// Approach-1 (Prefix Sum + HashMap)
// T.C : O(n)
// S.C : O(n)

class Solution
{
public:
  long long countMajoritySubarrays(
      vector<int> &nums,
      int target)
  {

    unordered_map<int, int> frequency;

    int prefixSum = 0;

    frequency[0] = 1;

    long long validPrefixes = 0;

    long long answer = 0;

    for (int value : nums)
    {

      if (value == target)
      {

        validPrefixes +=
            frequency[prefixSum];

        prefixSum++;
      }
      else
      {

        prefixSum--;

        validPrefixes -=
            frequency[prefixSum];
      }

      frequency[prefixSum]++;

      answer += validPrefixes;
    }

    return answer;
  }
};