//Approach-1 (Sorting + Median)
//T.C : O(n log n)
//S.C : O(log n) for sorting stack

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minMoves2(vector<int>& nums) {
        sort(nums.begin(), nums.end());

        int n = nums.size();
        int median = nums[n / 2];

        long long moves = 0;

        for (int num : nums) {
            moves += abs(1LL * num - median);
        }

        return static_cast<int>(moves);
    }
};


//Approach-2 (Randomized Quickselect + Median)
//T.C : O(n) expected, O(n^2) worst case
//S.C : O(1) auxiliary space

class Solution2 {
private:
    void swapValues(vector<int>& nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    int quickSelect(vector<int>& nums, int target) {
        int left = 0;
        int right = static_cast<int>(nums.size()) - 1;

        mt19937 rng(
            static_cast<unsigned int>(
                chrono::steady_clock::now()
                    .time_since_epoch().count()
            )
        );

        while (left <= right) {
            uniform_int_distribution<int> dist(left, right);
            int pivot = nums[dist(rng)];

            int low = left;
            int mid = left;
            int high = right;

            while (mid <= high) {
                if (nums[mid] < pivot) {
                    swapValues(nums, low, mid);
                    low++;
                    mid++;
                }
                else if (nums[mid] > pivot) {
                    swapValues(nums, mid, high);
                    high--;
                }
                else {
                    mid++;
                }
            }

            if (target < low) {
                right = low - 1;
            }
            else if (target > high) {
                left = high + 1;
            }
            else {
                return nums[target];
            }
        }

        return nums[target];
    }

public:
    int minMoves2(vector<int>& nums) {
        int median = quickSelect(nums, nums.size() / 2);

        long long moves = 0;

        for (int num : nums) {
            moves += abs(1LL * num - median);
        }

        return static_cast<int>(moves);
    }
};