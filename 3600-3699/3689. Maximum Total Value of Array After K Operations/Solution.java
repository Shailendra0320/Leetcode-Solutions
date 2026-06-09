class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int lo = nums[0], hi = nums[0];
        for (int x : nums) {
            if (x < lo) lo = x;
            if (x > hi) hi = x;
        }
        return (long)(hi - lo) * k;
    }
}