//Approach-1 (Brute Force: Running Sum from Every Starting Index)
//T.C: O(n * r) - O(n^2) in the worst case
//S.C: O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minimumSumSubarray(vector<int>& nums, int l, int r) {
        int n = nums.size();
        int answer = INT_MAX;

        for (int start = 0; start < n; start++) {
            int sum = 0;
            int limit = min(n, start + r);

            for (int end = start; end < limit; end++) {
                sum += nums[end];
                int length = end - start + 1;

                if (length >= l && sum > 0) {
                    answer = min(answer, sum);
                }
            }
        }

        return answer == INT_MAX ? -1 : answer;
    }
};


//Approach-2 (Fixed-Size Sliding Window)
//T.C: O(n * (r - l + 1)) - O(n^2) in the worst case
//S.C: O(1)

class Solution2 {
public:
    int minimumSumSubarray(vector<int>& nums, int l, int r) {
        int n = nums.size();
        int answer = INT_MAX;

        for (int size = l; size <= r; size++) {
            int sum = 0;

            for (int i = 0; i < size; i++) {
                sum += nums[i];
            }

            if (sum > 0) {
                answer = min(answer, sum);
            }

            for (int right = size; right < n; right++) {
                sum += nums[right] - nums[right - size];

                if (sum > 0) {
                    answer = min(answer, sum);
                }
            }
        }

        return answer == INT_MAX ? -1 : answer;
    }
};