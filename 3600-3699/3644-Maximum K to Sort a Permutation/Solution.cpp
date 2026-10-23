//Approach-1 (Bitwise AND of Misplaced Values)
//T.C : O(n)
//S.C : O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int sortPermutation(vector<int>& nums) {
        int ans = -1;

        for (int i = 0; i < static_cast<int>(nums.size()); i++) {
            if (nums[i] != i) {
                ans &= nums[i];
            }
        }

        return max(ans, 0);
    }
};

//Approach-2 (Build the Answer Bit by Bit)
//T.C : O(31 * n) = O(n)
//S.C : O(1)

class Solution2 {
public:
    int sortPermutation(vector<int>& nums) {
        bool misplaced = false;

        for (int i = 0; i < static_cast<int>(nums.size()); i++) {
            if (nums[i] != i) {
                misplaced = true;
                break;
            }
        }

        if (!misplaced) {
            return 0;
        }

        int ans = 0;

        for (int bit = 30; bit >= 0; bit--) {
            int mask = 1 << bit;
            bool common = true;

            for (int i = 0; i < static_cast<int>(nums.size()); i++) {
                if (nums[i] != i && (nums[i] & mask) == 0) {
                    common = false;
                    break;
                }
            }

            if (common) {
                ans |= mask;
            }
        }

        return ans;
    }
};