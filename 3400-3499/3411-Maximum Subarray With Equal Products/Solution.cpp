
// Approach-1 (Brute Force + Big Integer)
// T.C: O(n^2) subarray extensions, excluding big integer arithmetic cost
// S.C: O(1) auxiliary space, excluding big integer storage

#include <bits/stdc++.h>
#include <boost/multiprecision/cpp_int.hpp>
using namespace std;
using boost::multiprecision::cpp_int;

class Solution {
public:
    int maxLength(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            cpp_int product = 1;
            int gcdVal = 0;
            int lcmVal = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];

                product *= x;

                gcdVal = gcd(gcdVal, x);
                lcmVal = lcm(lcmVal, x);

                cpp_int required = (long long) gcdVal * lcmVal;

                if (product == required) {
                    ans = max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }
};

// Approach-2 (Optimized Brute Force + Product Bound)
// T.C: O(n^2)
// S.C: O(1)

class Solution2 {
public:
    int maxLength(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;

        int globalLcm = 1;
        int maxValue = 0;

        for (int x : nums) {
            globalLcm = lcm(globalLcm, x);
            maxValue = max(maxValue, x);
        }

        long long limit = 1LL * globalLcm * maxValue;

        for (int i = 0; i < n; i++) {
            long long product = 1;
            int gcdVal = 0;
            int lcmVal = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];

                product *= x;

                // Further extensions cannot produce a valid subarray.
                if (product > limit) {
                    break;
                }

                gcdVal = gcd(gcdVal, x);
                lcmVal = lcm(lcmVal, x);

                if (product == 1LL * gcdVal * lcmVal) {
                    ans = max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }
};