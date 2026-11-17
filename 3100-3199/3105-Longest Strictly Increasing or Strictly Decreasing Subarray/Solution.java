
//Approach-1 (Single Pass with Two Counters)
//T.C: O(n)
//S.C: O(1)

class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int inc = 1;
        int dec = 1;
        int answer = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                inc++;
                dec = 1;
            } 
            else if (nums[i] < nums[i - 1]) {
                dec++;
                inc = 1;
            } 
            else {
                inc = 1;
                dec = 1;
            }

            answer = Math.max(answer, Math.max(inc, dec));
        }

        return answer;
    }
}


//Approach-2 (Two Independent Passes)
//T.C: O(n)
//S.C: O(1)

class Solution2 {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;

        int increasing = 1;
        int maxIncreasing = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                increasing++;
            } else {
                increasing = 1;
            }

            maxIncreasing = Math.max(maxIncreasing, increasing);
        }

        int decreasing = 1;
        int maxDecreasing = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                decreasing++;
            } else {
                decreasing = 1;
            }

            maxDecreasing = Math.max(maxDecreasing, decreasing);
        }

        return Math.max(maxIncreasing, maxDecreasing);
    }
}
