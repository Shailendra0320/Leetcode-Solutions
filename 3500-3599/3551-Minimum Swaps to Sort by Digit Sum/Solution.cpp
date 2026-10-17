//Approach-1 (Permutation Cycle Decomposition)
//T.C : O(n log n)
//S.C : O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

public:
    int minSwaps(vector<int>& nums) {
        int n = nums.size();
        vector<int> target = nums;

        sort(target.begin(), target.end(), [this](int a, int b) {
            int sumA = digitSum(a);
            int sumB = digitSum(b);

            if (sumA != sumB) {
                return sumA < sumB;
            }

            return a < b;
        });

        unordered_map<int, int> targetIndex;
        targetIndex.reserve(n * 2);

        for (int i = 0; i < n; i++) {
            targetIndex[target[i]] = i;
        }

        vector<bool> visited(n, false);
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int j = i;
            int cycleSize = 0;

            while (!visited[j]) {
                visited[j] = true;
                cycleSize++;
                j = targetIndex[nums[j]];
            }

            swaps += cycleSize - 1;
        }

        return swaps;
    }
};

//Approach-2 (Greedy Correct-Position Swapping)
//T.C : O(n log n)
//S.C : O(n)

class Solution2 {
private:
    int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

public:
    int minSwaps(vector<int>& nums) {
        int n = nums.size();
        vector<int> target = nums;

        sort(target.begin(), target.end(), [this](int a, int b) {
            int sumA = digitSum(a);
            int sumB = digitSum(b);

            if (sumA != sumB) {
                return sumA < sumB;
            }

            return a < b;
        });

        vector<int> current = nums;
        unordered_map<int, int> position;
        position.reserve(n * 2);

        for (int i = 0; i < n; i++) {
            position[current[i]] = i;
        }

        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (current[i] == target[i]) {
                continue;
            }

            int correctValue = target[i];
            int j = position[correctValue];
            int displacedValue = current[i];

            swap(current[i], current[j]);

            position[correctValue] = i;
            position[displacedValue] = j;

            swaps++;
        }

        return swaps;
    }
};