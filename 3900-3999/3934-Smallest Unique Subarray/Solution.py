
#include <bits/stdc++.h>
using namespace std;

class Solution {

    // Approach-1 (Binary Search + Double Rolling Hash)
    // T.C: O(n log n) expected
    // S.C: O(n)

    using ll = long long;

    static constexpr ll MOD1 = 1'000'000'007LL;
    static constexpr ll MOD2 = 1'000'000'009LL;
    static constexpr ll BASE1 = 911382323LL;
    static constexpr ll BASE2 = 972663749LL;

    bool valid(
        int len, int n,
        const vector<ll>& h1,
        const vector<ll>& h2,
        const vector<ll>& p1,
        const vector<ll>& p2
    ) {
        unordered_map<ll, int> freq;
        freq.reserve(2 * n);

        for (int i = 0; i + len <= n; i++) {
            ll a = (
                h1[i + len] - h1[i] * p1[len] % MOD1 + MOD1
            ) % MOD1;

            ll b = (
                h2[i + len] - h2[i] * p2[len] % MOD2 + MOD2
            ) % MOD2;

            ll key = (a << 32) | b;
            freq[key]++;
        }

        for (auto& [key, count] : freq) {
            if (count == 1) return true;
        }

        return false;
    }

public:
    int smallestUniqueSubarray(vector<int>& nums) {
        int n = nums.size();

        vector<ll> h1(n + 1, 0), h2(n + 1, 0);
        vector<ll> p1(n + 1, 1), p2(n + 1, 1);

        for (int i = 0; i < n; i++) {
            p1[i + 1] = p1[i] * BASE1 % MOD1;
            p2[i + 1] = p2[i] * BASE2 % MOD2;

            h1[i + 1] = (h1[i] * BASE1 + nums[i]) % MOD1;
            h2[i + 1] = (h2[i] * BASE2 + nums[i]) % MOD2;
        }

        int low = 1, high = n, ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (valid(mid, n, h1, h2, p1, p2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
};


// Approach-2 (Suffix Array + LCP Array)
// T.C: O(n log^2 n)
// S.C: O(n)

class Solution2 {
private:
    vector<int> buildSuffixArray(const vector<int>& nums) {
        int n = nums.size();

        vector<int> sa(n), rank = nums, nextRank(n);

        iota(sa.begin(), sa.end(), 0);

        for (int k = 1; k < n; k <<= 1) {
            auto cmp = [&](int a, int b) {
                if (rank[a] != rank[b]) {
                    return rank[a] < rank[b];
                }

                int ra = a + k < n ? rank[a + k] : -1;
                int rb = b + k < n ? rank[b + k] : -1;

                return ra < rb;
            };

            sort(sa.begin(), sa.end(), cmp);

            nextRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                nextRank[sa[i]] = nextRank[sa[i - 1]] +
                                  (cmp(sa[i - 1], sa[i]) ? 1 : 0);
            }

            rank.swap(nextRank);

            if (rank[sa[n - 1]] == n - 1) break;
        }

        return sa;
    }

    vector<int> buildLCP(
        const vector<int>& nums,
        const vector<int>& sa
    ) {
        int n = nums.size();

        vector<int> rank(n), lcp(n, 0);

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int common = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == 0) {
                common = 0;
                continue;
            }

            int j = sa[r - 1];

            while (i + common < n &&
                   j + common < n &&
                   nums[i + common] == nums[j + common]) {
                common++;
            }

            lcp[r] = common;

            if (common > 0) common--;
        }

        return lcp;
    }

public:
    int smallestUniqueSubarray(vector<int>& nums) {
        int n = nums.size();

        if (n == 1) return 1;

        vector<int> sa = buildSuffixArray(nums);
        vector<int> lcp = buildLCP(nums, sa);

        int ans = n;

        for (int r = 0; r < n; r++) {
            int maxLcp = lcp[r];

            if (r + 1 < n) {
                maxLcp = max(maxLcp, lcp[r + 1]);
            }

            int len = maxLcp + 1;

            if (len <= n - sa[r]) {
                ans = min(ans, len);
            }
        }

        return ans;
    }
};
