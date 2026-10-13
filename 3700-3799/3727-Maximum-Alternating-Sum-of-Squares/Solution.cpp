
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:

    // Approach 1: Use a separate array
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)

    long long maxAlternatingSum(vector<int>& nums) {
        int n = nums.size();
        vector<long long> squares(n);

        for (int i = 0; i < n; i++) {
            squares[i] = 1LL * nums[i] * nums[i];
        }

        sort(squares.begin(), squares.end());

        long long ans = 0;
        int mid = n / 2;

        for (int i = 0; i < mid; i++) {
            ans -= squares[i];
        }

        for (int i = mid; i < n; i++) {
            ans += squares[i];
        }

        return ans;
    }

    // Approach 2: Square and sort in place
    // Time Complexity: O(n log n)
    // Auxiliary Space Complexity: O(log n) typically for sort stack
    // Note: This approach modifies nums.

    long long maxAlternatingSumApproach2(vector<int>& nums) {
        for (int& x : nums) {
            x = x * x;
        }

        sort(nums.begin(), nums.end());

        long long ans = 0;
        int mid = nums.size() / 2;

        for (int i = 0; i < mid; i++) {
            ans -= nums[i];
        }

        for (int i = mid; i < nums.size(); i++) {
            ans += nums[i];
        }

        return ans;
    }
};
