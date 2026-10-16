//Approach-1 (Lazy Segment Tree: Sum and Sum of Squares)
//T.C : O(n log n)
//S.C : O(n)

import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;

    private static final class SegmentTree {
        private final int n;
        private final long[] sum;
        private final long[] squareSum;
        private final int[] lazy;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            squareSum = new long[4 * n];
            lazy = new int[4 * n];
        }

        private void apply(int node, int left, int right, int delta) {
            long length = right - left + 1L;
            long oldSum = sum[node];

            squareSum[node] = (
                squareSum[node]
                + 2L * delta * oldSum
                + length * delta * delta
            ) % MOD;

            sum[node] = (oldSum + length * delta) % MOD;
            lazy[node] += delta;
        }

        private void push(int node, int left, int right) {
            if (lazy[node] == 0 || left == right) {
                return;
            }

            int mid = left + (right - left) / 2;
            int pending = lazy[node];

            apply(node * 2, left, mid, pending);
            apply(node * 2 + 1, mid + 1, right, pending);

            lazy[node] = 0;
        }

        private void update(int node, int left, int right,
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

        long getSquareSum() {
            return squareSum[1];
        }
    }

    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, Integer> lastSeen = new HashMap<>();
        SegmentTree tree = new SegmentTree(n);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int value = nums.get(i);
            int previous = lastSeen.getOrDefault(value, -1);

            tree.addOne(previous + 1, i);

            answer = (answer + tree.getSquareSum()) % MOD;

            lastSeen.put(value, i);
        }

        return (int) answer;
    }
}

//Approach-2 (Range Sum Tree + Running Square Total)
//T.C : O(n log n)
//S.C : O(n)

class Solution2 {
    private static final long MOD = 1_000_000_007L;

    private static final class SegmentTree {
        private final int n;
        private final long[] sum;
        private final int[] lazy;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            lazy = new int[4 * n];
        }

        private void apply(int node, int left, int right, int delta) {
            long length = right - left + 1L;

            sum[node] = (sum[node] + length * delta) % MOD;
            lazy[node] += delta;
        }

        private void push(int node, int left, int right) {
            if (lazy[node] == 0 || left == right) {
                return;
            }

            int mid = left + (right - left) / 2;
            int pending = lazy[node];

            apply(node * 2, left, mid, pending);
            apply(node * 2 + 1, mid + 1, right, pending);

            lazy[node] = 0;
        }

        private void update(int node, int left, int right,
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

        private long query(int node, int left, int right,
                           int ql, int qr) {
            if (ql <= left && right <= qr) {
                return sum[node];
            }

            push(node, left, right);

            int mid = left + (right - left) / 2;
            long result = 0;

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

        long rangeSum(int left, int right) {
            return query(1, 0, n - 1, left, right);
        }
    }

    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, Integer> lastSeen = new HashMap<>();
        SegmentTree tree = new SegmentTree(n);

        long squareEnding = 0;
        long answer = 0;

        for (int i = 0; i < n; i++) {
            int value = nums.get(i);
            int previous = lastSeen.getOrDefault(value, -1);
            int left = previous + 1;

            long oldRangeSum = tree.rangeSum(left, i);
            long length = i - left + 1L;

            squareEnding = (
                squareEnding + 2L * oldRangeSum + length
            ) % MOD;

            tree.addOne(left, i);

            answer = (answer + squareEnding) % MOD;

            lastSeen.put(value, i);
        }

        return (int) answer;
    }
}