//Approach-1 (Greedy Sorting and Leveling)
//T.C : O(n log n)
//S.C : O(n)

import java.util.*;

class Solution {

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long operations = (long) k1 + k2;
        long total = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
        }

        if (operations >= total) {
            return 0L;
        }

        Arrays.sort(diff);

        for (int i = n - 1; i >= 0; i--) {
            int nextLevel = (i == 0) ? 0 : diff[i - 1];
            long count = n - i;
            long cost = (long) (diff[i] - nextLevel) * count;

            if (operations >= cost) {
                operations -= cost;
            } else {
                long q = operations / count;
                long r = operations % count;
                long level = diff[i] - q;
                long answer = 0L;

                for (int j = 0; j < i; j++) {
                    answer += (long) diff[j] * diff[j];
                }

                answer += (count - r) * level * level;
                answer += r * (level - 1) * (level - 1);

                return answer;
            }
        }

        return 0L;
    }
}

//Approach-2 (Binary Search on Final Difference Cap)
//T.C : O(n log M)
//S.C : O(n)

class Solution2 {

    private long requiredOperations(int[] diff, int cap) {
        long required = 0;

        for (int d : diff) {
            if (d > cap) {
                required += d - cap;
            }
        }

        return required;
    }

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long operations = (long) k1 + k2;
        long total = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        if (operations >= total) {
            return 0L;
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
        long remaining = operations;

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

        long answer = 0L;

        for (int d : diff) {
            answer += (long) d * d;
        }

        return answer;
    }
}