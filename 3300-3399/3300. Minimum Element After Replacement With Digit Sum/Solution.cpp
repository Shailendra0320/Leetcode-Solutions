//Approach-1 (Digit Sum Array + Sorting)
//T.C : O(n * d + n log n)
//S.C : O(n)

class Solution {
public:

    int minElement(vector<int>& nums) {

        vector<int> newArr(nums.size());

        for (int i = 0; i < nums.size(); i++) {

            int sum = 0;
            int digit = nums[i];

            while (digit > 0) {

                sum += digit % 10;

                digit /= 10;
            }

            newArr[i] = sum;
        }

        sort(newArr.begin(), newArr.end());

        return newArr[0];
    }
};


/*
//Approach-2 (Optimal)
//T.C : O(n * d)
//S.C : O(1)

class Solution {
public:

    int minElement(vector<int>& nums) {

        int mn = INT_MAX;

        for (int num : nums) {

            int sum = 0;

            while (num > 0) {

                sum += num % 10;

                num /= 10;
            }

            mn = min(mn, sum);
        }

        return mn;
    }
};
*/