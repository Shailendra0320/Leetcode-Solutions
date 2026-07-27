// Approach-1 (Sorting)
// T.C : O(n log n)
// S.C : O(1)   //Ignoring sorting space

class Solution
{
public:
  int maxProduct(
      vector<int> &nums)
  {

    sort(
        nums.begin(),
        nums.end());

    int n =
        nums.size();

    return (nums[n - 1] - 1) *
           (nums[n - 2] - 1);
  }
};

/*
//Approach-2 (Single Pass)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:

    int maxProduct(
        vector<int>& nums
    ) {

        int max1 =
            INT_MIN;

        int max2 =
            INT_MIN;

        for (
            int num : nums
        ) {

            if (
                num > max1
            ) {

                max2 =
                    max1;

                max1 =
                    num;

            } else if (
                num > max2
            ) {

                max2 =
                    num;
            }
        }

        return
            (max1 - 1) *
            (max2 - 1);
    }
};
*/