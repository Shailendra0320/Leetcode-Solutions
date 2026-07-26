//Approach-1 (Sorting + Compare Two Candidate Products)
//T.C : O(n log n)
//S.C : O(1)   //Ignoring sorting space

class Solution {
public:

    int maximumProduct(
        vector<int>& nums
    ) {

        sort(
            nums.begin(),
            nums.end()
        );

        int n =
            nums.size();

        int case1 =
            nums[n - 1] *
            nums[n - 2] *
            nums[n - 3];

        int case2 =
            nums[0] *
            nums[1] *
            nums[n - 1];

        return
            max(
                case1,
                case2
            );
    }
};


/*
//Approach-2 (Single Pass - Without Sorting)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:

    int maximumProduct(
        vector<int>& nums
    ) {

        int max1 = INT_MIN;
        int max2 = INT_MIN;
        int max3 = INT_MIN;

        int min1 = INT_MAX;
        int min2 = INT_MAX;

        for (
            int num : nums
        ) {

            if (
                num > max1
            ) {

                max3 = max2;
                max2 = max1;
                max1 = num;

            } else if (
                num > max2
            ) {

                max3 = max2;
                max2 = num;

            } else if (
                num > max3
            ) {

                max3 = num;
            }

            if (
                num < min1
            ) {

                min2 = min1;
                min1 = num;

            } else if (
                num < min2
            ) {

                min2 = num;
            }
        }

        return max(
            max1 * max2 * max3,
            min1 * min2 * max1
        );
    }
};
*/