//Approach-1 (Linear Case Analysis)
//T.C : O(n)
//S.C : O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    bool check(const vector<int>& nums, int zero, int step) {
        int n = static_cast<int>(nums.size());

        for (int i = 1; i < n; i++) {
            int prev = (zero + (i - 1) * step + n) % n;
            int curr = (zero + i * step + n) % n;

            if (nums[prev] > nums[curr]) {
                return false;
            }
        }

        return true;
    }

public:
    int minOperations(vector<int>& nums) {
        int n = static_cast<int>(nums.size());
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        const vector<int>& dranofelik = nums;

        bool forward = check(dranofelik, zero, 1);
        bool backward = check(dranofelik, zero, -1);

        int answer = INT_MAX;

        if (forward) {
            answer = min(answer, zero);
            answer = min(answer, n - zero + 2);
        }

        if (backward) {
            answer = min(answer, zero + 2);
            answer = min(answer, n - zero);
        }

        return answer == INT_MAX ? -1 : answer;
    }
};


//Approach-2 (BFS on Compressed States)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
private:
    bool check(const vector<int>& nums, int zero, int step) {
        int n = static_cast<int>(nums.size());

        for (int i = 1; i < n; i++) {
            int prev = (zero + (i - 1) * step + n) % n;
            int curr = (zero + i * step + n) % n;

            if (nums[prev] > nums[curr]) {
                return false;
            }
        }

        return true;
    }

public:
    int minOperations(vector<int>& nums) {
        int n = static_cast<int>(nums.size());
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        bool forward = check(nums, zero, 1);
        bool backward = check(nums, zero, -1);

        if (!forward && !backward) {
            return -1;
        }

        vector<vector<int>> dist(2, vector<int>(n, -1));
        queue<pair<int, int>> q;

        q.push({0, 0});
        dist[0][0] = 0;

        while (!q.empty()) {
            auto [type, shift] = q.front();
            q.pop();

            int nextDist = dist[type][shift] + 1;

            int rotateShift = (shift + 1) % n;

            if (dist[type][rotateShift] == -1) {
                dist[type][rotateShift] = nextDist;
                q.push({type, rotateShift});
            }

            int reverseType = 1 - type;
            int reverseShift = (n - shift) % n;

            if (dist[reverseType][reverseShift] == -1) {
                dist[reverseType][reverseShift] = nextDist;
                q.push({reverseType, reverseShift});
            }
        }

        int answer = INT_MAX;

        if (forward) {
            answer = min(answer, dist[0][zero]);
        }

        if (backward) {
            int reverseBaseZero = n - 1 - zero;
            answer = min(answer, dist[1][reverseBaseZero]);
        }

        return answer == INT_MAX ? -1 : answer;
    }
};