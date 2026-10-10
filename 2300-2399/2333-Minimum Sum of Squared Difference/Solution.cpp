//Approach-1 (Greedy Sorting and Leveling)
//T.C : O(n log n)
//S.C : O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution {

public:
    long long minSumSquareDiff(vector<int>& nums1, vector<int>& nums2,
                               int k1, int k2) {
        int n = nums1.size();
        vector<int> diff(n);
        long long operations = (long long) k1 + k2;
        long long total = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = abs(nums1[i] - nums2[i]);
            total += diff[i];
        }

        if (operations >= total) {
            return 0LL;
        }

        sort(diff.begin(), diff.end());

        for (int i = n - 1; i >= 0; i--) {
            int nextLevel = (i == 0) ? 0 : diff[i - 1];
            long long count = n - i;
            long long cost = (long long)(diff[i] - nextLevel) * count;

            if (operations >= cost) {
                operations -= cost;
            } else {
                long long q = operations / count;
                long long r = operations % count;
                long long level = diff[i] - q;
                long long answer = 0;

                for (int j = 0; j < i; j++) {
                    answer += 1LL * diff[j] * diff[j];
                }

                answer += (count - r) * level * level;
                answer += r * (level - 1) * (level - 1);

                return answer;
            }
        }

        return 0LL;
    }
};

//Approach-2 (Binary Search on Final Difference Cap)
//T.C : O(n log M)
//S.C : O(n)

class Solution2 {

    long long requiredOperations(const vector<int>& diff, int cap) {
        long long required = 0;

        for (int d : diff) {
            if (d > cap) {
                required += d - cap;
            }
        }

        return required;
    }

public:
    long long minSumSquareDiff(vector<int>& nums1, vector<int>& nums2,
                               int k1, int k2) {
        int n = nums1.size();
        vector<int> diff(n);
        long long operations = (long long) k1 + k2;
        long long total = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = abs(nums1[i] - nums2[i]);
            total += diff[i];
            maxDiff = max(maxDiff, diff[i]);
        }

        if (operations >= total) {
            return 0LL;
        }

        int low = 0;
        int high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (requiredOperations(diff, mid) <= operations) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int cap = low;
        long long remaining = operations;

        for (int i = 0; i < n; i++) {
            if (diff[i] > cap) {
                remaining -= diff[i] - cap;
                diff[i] = cap;
            }
        }

        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] == cap) {
                diff[i]--;
                remaining--;
            }
        }

        long long answer = 0;

        for (int d : diff) {
            answer += 1LL * d * d;
        }

        return answer;
    }
};