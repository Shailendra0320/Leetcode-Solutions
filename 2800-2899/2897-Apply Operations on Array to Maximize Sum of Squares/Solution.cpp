//Approach-1 (Greedy Bit Counting with Decrement)
//T.C : O(31 * (n + k))
//S.C : O(31)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxSum(vector<int>& nums, int k) {
        const long long MOD = 1'000'000'007;
        vector<int> count(31, 0);

        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if (num & (1 << bit)) {
                    count[bit]++;
                }
            }
        }

        long long answer = 0;

        for (int i = 0; i < k; i++) {
            long long x = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (count[bit] > 0) {
                    x |= (1LL << bit);
                    count[bit]--;
                }
            }

            answer = (answer + x * x) % MOD;
        }

        return static_cast<int>(answer);
    }
};

//Approach-2 (Greedy Bit-Frequency Ranking)
//T.C : O(31 * (n + k))
//S.C : O(31)

class Solution2 {
public:
    int maxSum(vector<int>& nums, int k) {
        const long long MOD = 1'000'000'007;
        vector<int> count(31, 0);

        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if (num & (1 << bit)) {
                    count[bit]++;
                }
            }
        }

        long long answer = 0;

        for (int i = 0; i < k; i++) {
            long long x = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (count[bit] > i) {
                    x |= (1LL << bit);
                }
            }

            answer = (answer + x * x) % MOD;
        }

        return static_cast<int>(answer);
    }
};