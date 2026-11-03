//Approach-1 (Maximum Value + Total Sum)
//T.C : O(n)
//S.C : O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minMoves(vector<int>& nums) {
        int maxValue = 0;
        int sum = 0;

        for (int num : nums) {
            maxValue = max(maxValue, num);
            sum += num;
        }

        return maxValue * static_cast<int>(nums.size()) - sum;
    }
};


//Approach-2 (Maximum Value + Element-Wise Differences)
//T.C : O(n)
//S.C : O(1)

class Solution2 {
public:
    int minMoves(vector<int>& nums) {
        int maxValue = *max_element(nums.begin(), nums.end());

        int moves = 0;

        for (int num : nums) {
            moves += maxValue - num;
        }

        return moves;
    }//Approach-1 (Maximum Value + Total Sum)
//T.C : O(n)
//S.C : O(1)

class Solution {
    public int minMoves(int[] nums) {
        int maxValue = 0;
        int sum = 0;

        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
            sum += num;
        }

        return maxValue * nums.length - sum;
    }
}

//Approach-2 (Maximum Value + Element-Wise Differences)
//T.C : O(n)
//S.C : O(1)

class Solution2 {
    public int minMoves(int[] nums) {
        int maxValue = 0;

        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }

        int moves = 0;

        for (int num : nums) {
            moves += maxValue - num;
        }

        return moves;
    }
}
};