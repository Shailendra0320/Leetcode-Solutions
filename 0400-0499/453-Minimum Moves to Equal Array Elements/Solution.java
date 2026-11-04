// Approach-1 (One Pass: Track Sum and Minimum)
// T.C: O(n)
// S.C: O(1)

class Solution {
    public int minMoves(int[] nums) {
        long totalSum = 0;
        int minimum = nums[0];

        for (int num : nums) {
            totalSum += num;
            minimum = Math.min(minimum, num);
        }

        long answer = totalSum - (long) minimum * nums.length;
        return (int) answer;
    }
}

// Approach-2 (Two Passes: Add Every Difference)
// T.C: O(n)
// S.C: O(1)

class Solution2 {
    public int minMoves(int[] nums) {
        int minimum = nums[0];

        for (int num : nums) {
            minimum = Math.min(minimum, num);
        }

        long answer = 0;

        for (int num : nums) {
            answer += (long) num - minimum;
        }

        return (int) answer;
    }
}