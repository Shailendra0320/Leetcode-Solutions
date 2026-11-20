
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:

    //Approach-1 (Sorting + HashMap DP)
    //T.C: O(n log n)
    //S.C: O(n)

    int maxSelectedElements(vector<int>& nums) {
        sort(nums.begin(), nums.end());

        unordered_map<int, int> dp;
        int ans = 0;

        for (int x : nums) {
            dp[x + 1] = dp[x] + 1;
            dp[x] = dp[x - 1] + 1;

            ans = max(ans, max(dp[x], dp[x + 1]));
        }

        return ans;
    }
};


class Solution2 {
public:

    //Approach-2 (Sorting + Ordered Map DP)
    //T.C: O(n log n)
    //S.C: O(n)

    int maxSelectedElements(vector<int>& nums) {
        sort(nums.begin(), nums.end());

        map<int, int> dp;
        int ans = 0;

        for (int x : nums) {
            int extend = dp[x] + 1;
            int start = dp[x - 1] + 1;

            dp[x + 1] = extend;
            dp[x] = start;

            ans = max(ans, max(extend, start));
        }

        return ans;
    }
};
