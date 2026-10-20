//Approach-1 (Direct Block Matching with KMP)
//T.C : O(n log n + n * D), where D is the number of divisors of n
//S.C : O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    bool isRotation(const vector<int>& nums,
                    const vector<int>& sortedNums,
                    int start, int k) {
        vector<int> lps(k, 0);
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

    bool isSortableForK(const vector<int>& nums,
                        const vector<int>& sortedNums, int k) {
        int n = static_cast<int>(nums.size());

        for (int start = 0; start < n; start += k) {
            if (!isRotation(nums, sortedNums, start, k)) {
                return false;
            }
        }

        return true;
    }

public:
    int sortableIntegers(vector<int>& nums) {
        int n = static_cast<int>(nums.size());
        vector<int> sortedNums = nums;

        sort(sortedNums.begin(), sortedNums.end());

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
};


//Approach-2 (Circular Drops and Boundary Arrays)
//T.C : O(n + sum(n / k) for all divisors k of n)
//S.C : O(n)

class Solution2 {
private:
    bool isSortableForK(const vector<int>& nums, int k,
                        const vector<int>& prefMax,
                        const vector<int>& suffMin,
                        const vector<int>& prefDrop) {
        int n = static_cast<int>(nums.size());

        for (int start = 0; start < n; start += k) {
            int end = start + k - 1;
            int drops = prefDrop[end] - prefDrop[start];

            if (nums[end] > nums[start]) {
                drops++;
            }

            if (drops > 1 || prefMax[start] > suffMin[start]) {
                return false;
            }
        }

        return true;
    }

public:
    int sortableIntegers(vector<int>& nums) {
        int n = static_cast<int>(nums.size());
        vector<int> prefMax(n + 1, 0);

        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = max(prefMax[i], nums[i]);
        }

        vector<int> suffMin(n + 1, INT_MAX);

        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = min(nums[i], suffMin[i + 1]);
        }

        vector<int> prefDrop(n, 0);

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

            int other = n / d;

            if (other != d
                    && isSortableForK(nums, other, prefMax, suffMin, prefDrop)) {
                answer += other;
            }
        }

        return answer;
    }
};