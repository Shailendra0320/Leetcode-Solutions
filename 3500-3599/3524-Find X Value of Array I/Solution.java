//Approach-1 (Remainder DP with New State Array)
//T.C : O(n * k)
//S.C : O(k)

class Solution {
  public long[] resultArray(int[] nums, int k) {

    long[] answer = new long[k];
    long[] dp = new long[k];

    for (int num : nums) {

      int mod = num % k;
      long[] next = new long[k];

      // Start a new subarray with nums[i]
      next[mod] = 1;

      // Extend all subarrays ending at previous index
      for (int rem = 0; rem < k; rem++) {

        int newRem = (int) ((long) rem * mod % k);

        next[newRem] += dp[rem];
      }

      // All subarrays in next end at the current index
      for (int rem = 0; rem < k; rem++) {
        answer[rem] += next[rem];
      }

      dp = next;
    }

    return answer;
  }
}

// Approach-2 (Remainder DP with Rolling State)
// T.C : O(n * k)
// S.C : O(k)

class Solution2 {
  public long[] resultArray(int[] nums, int k) {

    long[] answer = new long[k];
    long[] dp = new long[k];
    long[] temp = new long[k];

    for (int num : nums) {

      int mod = num % k;

      // Clear temp for current position
      java.util.Arrays.fill(temp, 0);

      // Single-element subarray
      temp[mod] = 1;

      // Extend previous subarrays
      for (int rem = 0; rem < k; rem++) {

        int newRem = (int) ((long) rem * mod % k);

        temp[newRem] += dp[rem];
      }

      // Add current-ending subarrays to final answer
      for (int rem = 0; rem < k; rem++) {
        answer[rem] += temp[rem];
      }

      // Swap states
      long[] swap = dp;
      dp = temp;
      temp = swap;
    }

    return answer;
  }
}