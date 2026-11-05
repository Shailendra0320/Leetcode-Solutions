// Approach-1 (Brute Force: Check Every Pair)
// T.C: O(n * m)
// S.C: O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        int answer = 0;

        for (int x : arr1) {
            bool valid = true;

            for (int y : arr2) {
                if (abs(1LL * x - y) <= d) {
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
};

// Approach-2 (Sorting + Binary Search)
// T.C: O(m log m + n log m)
// S.C: O(m)

class Solution2 {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        vector<int> sorted = arr2;
        sort(sorted.begin(), sorted.end());

        int answer = 0;

        for (int x : arr1) {
            long long lowerLimit = 1LL * x - d;
            long long upperLimit = 1LL * x + d;

            int low = 0;
            int high = sorted.size();

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (sorted[mid] < lowerLimit) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            if (low == sorted.size() || sorted[low] > upperLimit) {
                answer++;
            }
        }

        return answer;
    }
};

// Approach-3 (Sorting + Two Pointers)
// T.C: O(n log n + m log m)
// S.C: O(n + m)

class Solution3 {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        vector<int> a = arr1;
        vector<int> b = arr2;

        sort(a.begin(), a.end());
        sort(b.begin(), b.end());

        int answer = 0;
        int j = 0;

        for (int x : a) {
            long long lowerLimit = 1LL * x - d;
            long long upperLimit = 1LL * x + d;

            while (j < b.size() && b[j] < lowerLimit) {
                j++;
            }

            if (j == b.size() || b[j] > upperLimit) {
                answer++;
            }
        }

        return answer;
    }
};