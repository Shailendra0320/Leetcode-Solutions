// Approach-1 (Bit Manipulation + Mathematical Observation)
// T.C : O(1)
// S.C : O(1)

class Solution
{
public:
  int uniqueXorTriplets(
      vector<int> &nums)
  {

    int n =
        nums.size();

    if (
        n < 3)
    {

      return n;
    }

    int bits =
        32 -
        __builtin_clz(
            n);

    return 1 << bits;
  }
};

/*
//Approach-2 (Brute Force)
//T.C : O(n³)
//S.C : O(n³)

class Solution {
public:

    int uniqueXorTriplets(
        vector<int>& nums
    ) {

        unordered_set<int> values;

        int n =
            nums.size();

        for (
            int i = 0;
            i < n;
            i++
        ) {

            for (
                int j = i;
                j < n;
                j++
            ) {

                for (
                    int k = j;
                    k < n;
                    k++
                ) {

                    values.insert(
                        nums[i] ^
                        nums[j] ^
                        nums[k]
                    );
                }
            }
        }

        return values.size();
    }
};
*/