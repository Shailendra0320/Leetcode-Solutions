//Approach-1 (Check Every Index from Scratch)
//T.C : O(n^2)
//S.C : O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int firstStableIndex(vector<int>& nums, int k) {
        int n = static_cast<int>(nums.size());

        for (int i = 0; i < n; i++) {
            int prefixMax = nums[0];

            for (int j = 1; j <= i; j++) {
                prefixMax = max(prefixMax, nums[j]);
            }

            int suffixMin = nums[i];

            for (int j = i + 1; j < n; j++) {
                suffixMin = min(suffixMin, nums[j]);
            }

            if (prefixMax - suffixMin <= k) {
                return i;
            }
        }

        return -1;
    }
};


//Approach-2 (Suffix Minimum + Running Prefix Maximum)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
public:
    int firstStableIndex(vector<int>& nums, int k) {
        int n = static_cast<int>(nums.size());
        vector<int> rightMin(n);

        rightMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = min(nums[i], rightMin[i + 1]);
        }

        int leftMax = nums[0];

        for (int i = 0; i < n; i++) {
            leftMax = max(leftMax, nums[i]);

            if (leftMax - rightMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
};