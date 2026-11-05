// Approach-1 (Brute Force: Check Every Pair)
// T.C: O(n * m)
// S.C: O(1)

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int answer = 0;

        for (int x : arr1) {
            boolean valid = true;

            for (int y : arr2) {
                if (Math.abs((long) x - y) <= d) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                answer++;
            }
        }

        return answer;
    }
}

// Approach-2 (Sorting + Binary Search)
// T.C: O(m log m + n log m)
// S.C: O(m)

import java.util.Arrays;

class Solution2 {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int[] sorted = arr2.clone();
        Arrays.sort(sorted);

        int answer = 0;

        for (int x : arr1) {
            long lowerLimit = (long) x - d;
            long upperLimit = (long) x + d;

            int low = 0;
            int high = sorted.length;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (sorted[mid] < lowerLimit) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            if (low == sorted.length || sorted[low] > upperLimit) {
                answer++;
            }
        }

        return answer;
    }
}

// Approach-3 (Sorting + Two Pointers)
// T.C: O(n log n + m log m)
// S.C: O(n + m)

import java.util.Arrays;

class Solution3 {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int[] a = arr1.clone();
        int[] b = arr2.clone();

        Arrays.sort(a);
        Arrays.sort(b);

        int answer = 0;
        int j = 0;

        for (int x : a) {
            long lowerLimit = (long) x - d;
            long upperLimit = (long) x + d;

            while (j < b.length && b[j] < lowerLimit) {
                j++;
            }

            if (j == b.length || b[j] > upperLimit) {
                answer++;
            }
        }

        return answer;
    }
}