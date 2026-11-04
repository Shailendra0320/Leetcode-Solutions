// Approach-1 (One Pass: Track Sum and Minimum)
// T.C: O(n)
// S.C: O(1)

class Solution {
public:
    int minMoves(vector<int>& nums) {
        long long totalSum = 0;
        int minimum = nums[0];

        for (int num : nums) {
            totalSum += num;
            minimum = min(minimum, num);
        }

        long long answer = totalSum - 1LL * minimum * nums.size();
        return (int) answer;
    }
};

// Approach-2 (Two Passes: Add Every Difference)
// T.C: O(n)
// S.C: O(1)

class Solution2 {
public:
    int minMoves(vector<int>& nums) {
        int minimum = *min_element(nums.begin(), nums.end());

        long long answer = 0;

        for (int num : nums) {
            answer += (long long) num - minimum;
        }

        return (int) answer;
    }
};