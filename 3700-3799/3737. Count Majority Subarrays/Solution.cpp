// Approach-1 (Brute Force)
// T.C : O(n²)
// S.C : O(1)

class Solution
{
public:
  int countMajoritySubarrays(
      vector<int> &nums,
      int target)
  {

    int n = nums.size();

    int answer = 0;

    for (int start = 0; start < n; start++)
    {

      int targetCount = 0;

      for (int end = start; end < n; end++)
      {

        if (nums[end] == target)
        {
          targetCount++;
        }

        int length = end - start + 1;

        if (targetCount > length / 2)
        {
          answer++;
        }
      }
    }

    return answer;
  }
};

/*
//Approach-2 (Prefix Sum)
//T.C : O(n²)
//S.C : O(n)

class Solution {
public:

    int countMajoritySubarrays(
        vector<int>& nums,
        int target
    ) {

        int n = nums.size();

        vector<int> prefix(n + 1, 0);

        for (int i = 0; i < n; i++) {

            if (nums[i] == target)
                prefix[i + 1] = prefix[i] + 1;
            else
                prefix[i + 1] = prefix[i] - 1;
        }

        int answer = 0;

        for (int left = 0; left < n; left++) {

            for (int right = left + 1; right <= n; right++) {

                if (prefix[right] - prefix[left] > 0)
                    answer++;
            }
        }

        return answer;
    }
};
*/

/*

*/