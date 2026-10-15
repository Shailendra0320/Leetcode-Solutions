//Approach-1 (Unordered Set Enumeration)
//T.C : O(n^2) expected
//S.C : O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int sumCounts(vector<int>& nums) {
        int n = nums.size();
        int answer = 0;

        for (int i = 0; i < n; i++) {
            unordered_set<int> seen;

            for (int j = i; j < n; j++) {
                seen.insert(nums[j]);

                int distinct = seen.size();
                answer += distinct * distinct;
            }
        }

        return answer;
    }
};

//Approach-2 (Fixed Boolean Array)
//T.C : O(n^2)
//S.C : O(1)

class Solution2 {
public:
    int sumCounts(vector<int>& nums) {
        int n = nums.size();
        int answer = 0;

        for (int i = 0; i < n; i++) {
            bool seen[101] = {};
            int distinct = 0;

            for (int j = i; j < n; j++) {
                int value = nums[j];

                if (!seen[value]) {
                    seen[value] = true;
                    distinct++;
                }

                answer += distinct * distinct;
            }
        }

        return answer;
    }
};