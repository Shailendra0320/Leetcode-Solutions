import java.util.Arrays;

//Approach-1 (Sorting + Three Possible Cases)
//T.C: O(n log n)
//S.C: O(1) auxiliary, excluding sorting internals

class Solution {
    public int minDifference(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        int case1 = nums[n - 1] - nums[2];
        int case2 = nums[n - 2] - nums[1];
        int case3 = nums[n - 3] - nums[0];

        return Math.min(case1, Math.min(case2, case3));
    }
}


//Approach-2 (Sorting + Sliding Window of Size n - 2)
//T.C: O(n log n)
//S.C: O(1) auxiliary, excluding sorting internals

class Solution2 {
    public int minDifference(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int windowSize = n - 2;
        int answer = Integer.MAX_VALUE;

        for (int start = 0; start <= 2; start++) {
            int end = start + windowSize - 1;
            int currentScore = nums[end] - nums[start];

            answer = Math.min(answer, currentScore);
        }

        return answer;
    }
}