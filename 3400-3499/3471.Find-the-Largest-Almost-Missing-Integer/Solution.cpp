#include <bits/stdc++.h>
using namespace std;

class Solution {

public:

    //Approach-1 (Frequency + Boundary Observation)
    //T.C : O(n)
    //S.C : O(n)

    int largestInteger(vector<int>& nums, int k) {

        int n = nums.size();

        unordered_map<int, int> frequency;

        for (int num : nums) {
            frequency[num]++;
        }

        int answer = -1;

        if (k == 1) {

            for (auto& [value, count] : frequency) {

                if (count == 1) {
                    answer = max(answer, value);
                }
            }

            return answer;
        }

        if (k == n) {

            for (int num : nums) {
                answer = max(answer, num);
            }

            return answer;
        }

        if (frequency[nums[0]] == 1) {
            answer = max(answer, nums[0]);
        }

        if (frequency[nums[n - 1]] == 1) {
            answer = max(answer, nums[n - 1]);
        }

        return answer;
    }


    //Approach-2 (Brute Force)
    //T.C : O(d * (n-k+1) * k), worst case O(n^3)
    //S.C : O(n)

    int largestIntegerBruteForce(vector<int>& nums, int k) {

        int arrayLength = nums.size();
        int answer = -1;

        unordered_set<int> uniqueValues;

        for (int value : nums) {
            uniqueValues.insert(value);
        }

        for (int candidate : uniqueValues) {

            int subarrayCount = 0;

            for (int startIndex = 0;
                 startIndex <= arrayLength - k;
                 startIndex++) {

                bool foundInSubarray = false;

                for (int position = startIndex;
                     position < startIndex + k;
                     position++) {

                    if (nums[position] == candidate) {
                        foundInSubarray = true;
                        break;
                    }
                }

                if (foundInSubarray) {
                    subarrayCount++;
                }
            }

            if (subarrayCount == 1) {
                answer = max(answer, candidate);
            }
        }

        return answer;
    }
};