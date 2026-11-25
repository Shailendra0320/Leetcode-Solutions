
import java.util.*;

class Solution {

    // Approach-1 (Binary Search + Double Rolling Hash)
    // T.C: O(n log n) expected
    // S.C: O(n)

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long mod1 = 1_000_000_007L;
        long mod2 = 1_000_000_009L;
        long base1 = 911382323L;
        long base2 = 972663749L;

        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];
        long[] p1 = new long[n + 1];
        long[] p2 = new long[n + 1];

        p1[0] = p2[0] = 1;

        for (int i = 0; i < n; i++) {
            p1[i + 1] = p1[i] * base1 % mod1;
            p2[i + 1] = p2[i] * base2 % mod2;

            h1[i + 1] = (h1[i] * base1 + nums[i]) % mod1;
            h2[i + 1] = (h2[i] * base2 + nums[i]) % mod2;
        }

        int low = 1, high = n, ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (valid(mid, n, h1, h2, p1, p2, mod1, mod2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean valid(
        int len, int n,
        long[] h1, long[] h2,
        long[] p1, long[] p2,
        long mod1, long mod2
    ) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {
            long a = (h1[i + len] - h1[i] * p1[len] % mod1 + mod1) % mod1;
            long b = (h2[i + len] - h2[i] * p2[len] % mod2 + mod2) % mod2;

            long key = (a << 32) | b;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) return true;
        }

        return false;
    }
}


// Approach-2 (Suffix Array + LCP Array)
// T.C: O(n log^2 n)
// S.C: O(n)

class Solution2 {

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        if (n == 1) return 1;

        int[] sa = buildSuffixArray(nums);
        int[] lcp = buildLCP(nums, sa);

        int ans = n;

        for (int r = 0; r < n; r++) {
            int maxLcp = lcp[r];

            if (r + 1 < n) {
                maxLcp = Math.max(maxLcp, lcp[r + 1]);
            }

            int len = maxLcp + 1;

            if (len <= n - sa[r]) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;

        Integer[] order = new Integer[n];
        int[] rank = new int[n];
        int[] nextRank = new int[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
            rank[i] = nums[i];
        }

        for (int k = 1; k < n; k <<= 1) {
            final int step = k;
            final int[] currentRank = rank;

            Arrays.sort(order, (a, b) -> {
                if (currentRank[a] != currentRank[b]) {
                    return Integer.compare(currentRank[a], currentRank[b]);
                }

                int ra = a + step < n ? currentRank[a + step] : -1;
                int rb = b + step < n ? currentRank[b + step] : -1;

                return Integer.compare(ra, rb);
            });

            nextRank[order[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = order[i - 1];
                int b = order[i];

                boolean different =
                    rank[a] != rank[b] ||
                    (a + k < n ? rank[a + k] : -1) !=
                    (b + k < n ? rank[b + k] : -1);

                nextRank[b] = nextRank[a] + (different ? 1 : 0);
            }

            int[] temp = rank;
            rank = nextRank;
            nextRank = temp;

            if (rank[order[n - 1]] == n - 1) break;
        }

        int[] sa = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = order[i];
        }

        return sa;
    }

    private int[] buildLCP(int[] nums, int[] sa) {
        int n = nums.length;

        int[] rank = new int[n];
        int[] lcp = new int[n];

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
}
