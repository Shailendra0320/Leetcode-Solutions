//Approach-1 (Lazy Segment Tree: Sum and Sum of Squares)
//T.C : O(n log n)
//S.C : O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
    static constexpr long long MOD = 1'000'000'007LL;

    struct SegmentTree {
        int n;
        vector<long long> sum, squareSum;
        vector<int> lazy;

        explicit SegmentTree(int size)
            : n(size),
              sum(4 * size, 0),
              squareSum(4 * size, 0),
              lazy(4 * size, 0) {}

        void apply(int node, int left, int right, int delta) {
            long long length = right - left + 1LL;
            long long oldSum = sum[node];

            squareSum[node] = (
                squareSum[node]
                + 2LL * delta * oldSum
                + length * delta * delta
            ) % MOD;

            sum[node] = (oldSum + length * delta) % MOD;
            lazy[node] += delta;
        }

        void push(int node, int left, int right) {
            if (lazy[node] == 0 || left == right) {
                return;
            }

            int mid = left + (right - left) / 2;
            int pending = lazy[node];

            apply(node * 2, left, mid, pending);
            apply(node * 2 + 1, mid + 1, right, pending);

            lazy[node] = 0;
        }

        void update(int node, int left, int right,
                    int ql, int qr) {
            if (ql <= left && right <= qr) {
                apply(node, left, right, 1);
                return;
            }

            push(node, left, right);

            int mid = left + (right - left) / 2;

            if (ql <= mid) {
                update(node * 2, left, mid, ql, qr);
            }

            if (qr > mid) {
                update(node * 2 + 1, mid + 1, right, ql, qr);
            }

            sum[node] = (
                sum[node * 2] + sum[node * 2 + 1]
            ) % MOD;

            squareSum[node] = (
                squareSum[node * 2] + squareSum[node * 2 + 1]
            ) % MOD;
        }

        void addOne(int left, int right) {
            if (left <= right) {
                update(1, 0, n - 1, left, right);
            }
        }

        long long getSquareSum() const {
            return squareSum[1];
        }
    };

public:
    int sumCounts(vector<int>& nums) {
        int n = static_cast<int>(nums.size());

        unordered_map<int, int> lastSeen;
        lastSeen.reserve(n * 2);

        SegmentTree tree(n);
        long long answer = 0;

        for (int i = 0; i < n; i++) {
            int value = nums[i];

            auto it = lastSeen.find(value);
            int previous = (
                it == lastSeen.end() ? -1 : it->second
            );

            tree.addOne(previous + 1, i);

            answer = (answer + tree.getSquareSum()) % MOD;

            lastSeen[value] = i;
        }

        return static_cast<int>(answer);
    }
};


//Approach-2 (Range Sum Tree + Running Square Total)
//T.C : O(n log n)
//S.C : O(n)

class Solution2 {
    static constexpr long long MOD = 1'000'000'007LL;

    struct SegmentTree {
        int n;
        vector<long long> sum;
        vector<int> lazy;

        explicit SegmentTree(int size)
            : n(size), sum(4 * size, 0), lazy(4 * size, 0) {}

        void apply(int node, int left, int right, int delta) {
            long long length = right - left + 1LL;

            sum[node] = (sum[node] + length * delta) % MOD;
            lazy[node] += delta;
        }

        void push(int node, int left, int right) {
            if (lazy[node] == 0 || left == right) {
                return;
            }

            int mid = left + (right - left) / 2;
            int pending = lazy[node];

            apply(node * 2, left, mid, pending);
            apply(node * 2 + 1, mid + 1, right, pending);

            lazy[node] = 0;
        }

        void update(int node, int left, int right,
                    int ql, int qr) {
            if (ql <= left && right <= qr) {
                apply(node, left, right, 1);
                return;
            }

            push(node, left, right);

            int mid = left + (right - left) / 2;

            if (ql <= mid) {
                update(node * 2, left, mid, ql, qr);
            }

            if (qr > mid) {
                update(node * 2 + 1, mid + 1, right, ql, qr);
            }

            sum[node] = (
                sum[node * 2] + sum[node * 2 + 1]
            ) % MOD;
        }

        long long query(int node, int left, int right,
                        int ql, int qr) {
            if (ql <= left && right <= qr) {
                return sum[node];
            }

            push(node, left, right);

            int mid = left + (right - left) / 2;
            long long result = 0;

            if (ql <= mid) {
                result += query(node * 2, left, mid, ql, qr);
            }

            if (qr > mid) {
                result += query(node * 2 + 1, mid + 1, right, ql, qr);
            }

            return result % MOD;
        }

        void addOne(int left, int right) {
            update(1, 0, n - 1, left, right);
        }

        long long rangeSum(int left, int right) {
            return query(1, 0, n - 1, left, right);
        }
    };

public:
    int sumCounts(vector<int>& nums) {
        int n = static_cast<int>(nums.size());

        unordered_map<int, int> lastSeen;
        lastSeen.reserve(n * 2);

        SegmentTree tree(n);

        long long squareEnding = 0;
        long long answer = 0;

        for (int i = 0; i < n; i++) {
            int value = nums[i];

            auto it = lastSeen.find(value);
            int previous = (
                it == lastSeen.end() ? -1 : it->second
            );

            int left = previous + 1;

            long long oldRangeSum = tree.rangeSum(left, i);
            long long length = i - left + 1LL;

            squareEnding = (
                squareEnding + 2LL * oldRangeSum + length
            ) % MOD;

            tree.addOne(left, i);

            answer = (answer + squareEnding) % MOD;

            lastSeen[value] = i;
        }

        return static_cast<int>(answer);
    }
};