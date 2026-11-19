#include <bits/stdc++.h>
using namespace std;

//Approach-1 (Sorting + Three Possible Cases)
//T.C: O(n log n)
//S.C: O(1) auxiliary, excluding sorting internals

class Solution {
public:
    int minDifference(vector<int>& nums) {
        int n = nums.size();

        sort(nums.begin(), nums.end());

        int case1 = nums[n - 1] - nums[2];
        int case2 = nums[n - 2] - nums[1];
        int case3 = nums[n - 3] - nums[0];

        return min(case1, min(case2, case3));
    }
};


//Approach-2 (Sorting + Sliding Window of Size n - 2)
//T.C: O(n log n)
//S.C: O(1) auxiliary, excluding sorting internals

class Solution2 {
public:
    int minDifference(vector<int>& nums) {
        sort(nums.begin(), nums.end());

        int n = nums.size();
        int windowSize = n - 2;
        int answer = INT_MAX;

        for (int start = 0; start <= 2; start++) {
            int end = start + windowSize - 1;
            int currentScore = nums[end] - nums[start];

            answer = min(answer, currentScore);
        }

        return answer;
    }
};