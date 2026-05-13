class Solution {

    public int minMoves(int[] nums, int limit) {

        int len = limit + limit + 2;

        int[] lower = new int[len];
        int[] upper = new int[len];
        int[] target = new int[len];

        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {

            int sum = nums[i] + nums[n - 1 - i];

            int min = Math.min(nums[i], nums[n - 1 - i]) + 1;

            int max = Math.max(nums[i], nums[n - 1 - i]) + limit;

            if (sum < len) {
                target[sum] += 1;
            }

            if (min < len) {
                lower[min] += 1;
            }

            if (max < len) {
                upper[max] += 1;
            }
        }

        int minCost = n;
        int cost = n;

        for (int i = 2; i < len; i++) {

            cost = cost
                 - lower[i]
                 - target[i]
                 + target[i - 1]
                 + upper[i - 1];

            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }
}