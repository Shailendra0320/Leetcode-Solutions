//Approach-1 (Sort and Scan)
//T.C : O(n log n)
//S.C : O(n) upper bound

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> targetIndices(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());

        vector<int> ans;

        for (int i = 0; i < static_cast<int>(nums.size()); i++) {
            if (nums[i] == target) {
                ans.push_back(i);
            }
        }

        return ans;
    }
};

//Approach-2 (Frequency Counting Without Sorting)
//T.C : O(n)
//S.C : O(1) auxiliary space, excluding output

class Solution2 {
public:
    vector<int> targetIndices(vector<int>& nums, int target) {
        int smaller = 0;
        int equal = 0;

        for (int num : nums) {
            if (num < target) {
                smaller++;
            } else if (num == target) {
                equal++;
            }
        }

        vector<int> ans;

        for (int i = smaller; i < smaller + equal; i++) {
            ans.push_back(i);
        }

        return ans;
    }
};