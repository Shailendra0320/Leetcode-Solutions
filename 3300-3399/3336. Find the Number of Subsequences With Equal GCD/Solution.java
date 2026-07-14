//Approach-1 (Dynamic Programming + Memoization)
//T.C : O(n × G²)
//S.C : O(n × G²)

import java.util.Arrays;

class Solution {

  static final int MOD = 1_000_000_007;

  int[][][] dp;

  public int subsequencePairCount(
      int[] nums) {

    int n = nums.length;

    dp = new int[n][201][201];

    for (int[][] first : dp) {

      for (int[] second : first) {

        Arrays.fill(
            second,
            -1);
      }
    }

    return solve(
        nums,
        0,
        0,
        0);
  }

  private int solve(
      int[] nums,
      int index,
      int gcd1,
      int gcd2) {

    if (index == nums.length) {

      return (gcd1 > 0 &&
          gcd2 > 0 &&
          gcd1 == gcd2) ? 1 : 0;
    }

    if (dp[index][gcd1][gcd2] != -1) {

      return dp[index][gcd1][gcd2];
    }

    int current = nums[index];

    long answer = 0;

    answer += solve(
        nums,
        index + 1,
        gcd(gcd1, current),
        gcd2);

    answer += solve(
        nums,
        index + 1,
        gcd1,
        gcd(gcd2, current));

    answer += solve(
        nums,
        index + 1,
        gcd1,
        gcd2);

    dp[index][gcd1][gcd2] = (int) (answer % MOD);

    return dp[index][gcd1][gcd2];
  }

  private int gcd(
      int a,
      int b) {

    return b == 0
        ? a
        : gcd(
            b,
            a % b);
  }
}

/*
 * //Approach-2 (Brute Force Recursion)
 * //T.C : O(3^n)
 * //S.C : O(n)
 * 
 * class Solution {
 * 
 * static final int MOD = 1_000_000_007;
 * 
 * public int subsequencePairCount(
 * int[] nums
 * ) {
 * 
 * return solve(
 * nums,
 * 0,
 * 0,
 * 0
 * );
 * }
 * 
 * private int solve(
 * int[] nums,
 * int index,
 * int gcd1,
 * int gcd2
 * ) {
 * 
 * if (index == nums.length) {
 * 
 * return (
 * gcd1 > 0 &&
 * gcd2 > 0 &&
 * gcd1 == gcd2
 * ) ? 1 : 0;
 * }
 * 
 * int current =
 * nums[index];
 * 
 * long answer = 0;
 * 
 * answer += solve(
 * nums,
 * index + 1,
 * gcd(gcd1, current),
 * gcd2
 * );
 * 
 * answer += solve(
 * nums,
 * index + 1,
 * gcd1,
 * gcd(gcd2, current)
 * );
 * 
 * answer += solve(
 * nums,
 * index + 1,
 * gcd1,
 * gcd2
 * );
 * 
 * return
 * (int)
 * (answer % MOD);
 * }
 * 
 * private int gcd(
 * int a,
 * int b
 * ) {
 * 
 * return b == 0
 * ? a
 * : gcd(
 * b,
 * a % b
 * );
 * }
 * }
 */