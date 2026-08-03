//Approach-1 (Mathematical Insight - Alice Always Wins)
//T.C : O(1)
//S.C : O(1)

class Solution {

  public boolean stoneGame(
      int[] piles) {

    return true;
  }
}

/*
 * //Approach-2 (Dynamic Programming - Interval DP)
 * //T.C : O(n^2)
 * //S.C : O(n^2)
 *
 * class Solution {
 *
 * public boolean stoneGame(
 * int[] piles
 * ) {
 *
 * int n =
 * piles.length;
 *
 * int[][] dp =
 * new int[n][n];
 *
 * for (
 * int i = 0;
 * i < n;
 * i++
 * ) {
 *
 * dp[i][i] =
 * piles[i];
 * }
 *
 * for (
 * int len = 2;
 * len <= n;
 * len++
 * ) {
 *
 * for (
 * int i = 0;
 * i + len - 1 < n;
 * i++
 * ) {
 *
 * int j =
 * i + len - 1;
 *
 * dp[i][j] =
 * Math.max(
 * piles[i] - dp[i + 1][j],
 * piles[j] - dp[i][j - 1]
 * );
 * }
 * }
 *
 * return
 * dp[0][n - 1] > 0;
 * }
 * }
 */
