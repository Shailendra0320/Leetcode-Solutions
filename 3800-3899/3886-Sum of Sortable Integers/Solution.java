//Approach-1 (Direct Block Matching with KMP)
//T.C : O(n log n + n * D), where D is the number of divisors of n
//S.C : O(n)

import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        int answer = 0;

        for (int d = 1; d <= n / d; d++) {
            if (n % d != 0) {
                continue;
            }

            if (isSortableForK(nums, sortedNums, d)) {
                answer += d;
            }

            int other = n / d;

            if (other != d && isSortableForK(nums, sortedNums, other)) {
                answer += other;
            }
        }

        return answer;
    }

    private boolean isSortableForK(int[] nums, int[] sortedNums, int k) {
        int n = nums.length;

        for (int start = 0; start < n; start += k) {
            if (!isRotation(nums, sortedNums, start, k)) {
                return false;
            }
        }

        return true;
    }

    private boolean isRotation(int[] nums, int[] sortedNums,
                               int start, int k) {
        int[] lps = new int[k];
        int length = 0;
        int i = 1;

        while (i < k) {
            if (sortedNums[start + i] == sortedNums[start + length]) {
                length++;
                lps[i] = length;
                i++;
            } else if (length > 0) {
                length = lps[length - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        int textIndex = 0;
        int patternIndex = 0;
        int textLength = 2 * k - 1;

        while (textIndex < textLength) {
            int value = nums[start + textIndex % k];

            if (value == sortedNums[start + patternIndex]) {
                textIndex++;
                patternIndex++;

                if (patternIndex == k) {
                    return true;
                }
            } else if (patternIndex > 0) {
                patternIndex = lps[patternIndex - 1];
            } else {
                textIndex++;
            }
        }

        return false;
    }
}

//Approach-2 (Circular Drops and Boundary Arrays)
//T.C : O(n + sum(n / k) for all divisors k of n)
//S.C : O(n)

class Solution2 {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefMax = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        int[] suffMin = new int[n + 1];
        suffMin[n] = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }

        int[] prefDrop = new int[n];

        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1]
                + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        int answer = 0;

        for (int d = 1; d <= n / d; d++) {
            if (n % d != 0) {
                continue;
            }

            if (isSortableForK(nums, d, prefMax, suffMin, prefDrop)) {
                answer += d;
            }

            int