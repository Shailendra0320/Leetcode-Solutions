//Approach-1 (Sliding Window + Monotonic Deque: Value-Count Pairs)
//T.C: O(n)
//S.C: O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long countNonDecreasingSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        deque<pair<int, int>> dq;

        long long answer = 0;
        long long cost = 0;
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            int num = nums[left];
            int count = 1;

            while (!dq.empty() && dq.back().first < num) {
                auto [nextNum, nextCount] = dq.back();
                dq.pop_back();

                count += nextCount;
                cost += 1LL * (num - nextNum) * nextCount;
            }

            dq.push_back({num, count});

            while (cost > k) {
                auto [rightmostNum, rightmostCount] = dq.front();
                dq.pop_front();

                cost -= 1LL * rightmostNum - nums[right];
                right--;

                if (rightmostCount > 1) {
                    dq.push_front({
                        rightmostNum,
                        rightmostCount - 1
                    });
                }
            }

            answer += right - left + 1;
        }

        return answer;
    }
};


//Approach-2 (Sliding Window + Monotonic Deque: Indices)
//T.C: O(n)
//S.C: O(n)

class Solution2 {
public:
    long long countNonDecreasingSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        deque<int> dq;

        long long answer = 0;
        long long cost = 0;
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            int num = nums[left];

            while (!dq.empty() && nums[dq.back()] < num) {
                int l = dq.back();
                dq.pop_back();

                int r = dq.empty() ? right + 1 : dq.back();

                cost += 1LL * (r - l) * (num - nums[l]);
            }

            dq.push_back(left);

            while (cost > k) {
                cost -= 1LL * nums[dq.front()] - nums[right];

                if (dq.front() == right) {
                    dq.pop_front();
                }

                right--;
            }

            answer += right - left + 1;
        }

        return answer;
    }
};