leetcode 2574 class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int left = 0;

        for (int i = 0; i < n; i++) {
            total -= nums[i];

            int diff = left - total;
            ans[i] = diff < 0 ? -diff : diff;

            left += nums[i];
        }

        return ans;
    }
}