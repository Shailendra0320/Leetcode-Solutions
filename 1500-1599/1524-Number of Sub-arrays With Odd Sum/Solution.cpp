//Approach-1 (Prefix Sum Parity + Frequency Counting)
//T.C: O(n)
//S.C: O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int numOfSubarrays(vector<int>& arr) {
        const int MOD = 1e9 + 7;

        int even = 1;
        int odd = 0;
        int prefixParity = 0;

        long long answer = 0;

        for (int num : arr) {
            prefixParity ^= (num & 1);

            if (prefixParity == 0) {
                answer = (answer + odd) % MOD;
                even++;
            } else {
                answer = (answer + even) % MOD;
                odd++;
            }
        }

        return (int)answer;
    }
};


//Approach-2 (Dynamic Programming: Odd and Even Subarrays)
//T.C: O(n)
//S.C: O(1)

class Solution2 {
public:
    int numOfSubarrays(vector<int>& arr) {
        const int MOD = 1e9 + 7;

        long long odd = 0;
        long long even = 0;
        long long answer = 0;

        for (int num : arr) {
            if ((num & 1) == 0) {
                even = (even + 1) % MOD;
            } else {
                long long temp = odd;

                odd = (even + 1) % MOD;
                even = temp;
            }

            answer = (answer + odd) % MOD;
        }

        return (int)answer;
    }
};