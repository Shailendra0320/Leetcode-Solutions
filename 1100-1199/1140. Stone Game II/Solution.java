//Approach-1 (Dynamic Programming + Game Theory + Suffix Sum)
//T.C : O(n^3)
//S.C : O(n^2)

class Solution {

    public int stoneGameII(
        int[] piles
    ) {

        int n =
            piles.length;

        int[][] dp =
            new int[n][n + 1];

        int[] suffixSum =
            new int[n];

        suffixSum[n - 1] =
            piles[n - 1];

        for (
            int i = n - 2;
            i >= 0;
            i--
        ) {

            suffixSum[i] =
                suffixSum[i + 1] +
                piles[i];
        }

        return solve(
            0,
            1,
            piles,
            dp,
            suffixSum
        );
    }

    private int solve(
        int i,
        int M,
        int[] piles,
        int[][] dp,
        int[] suffixSum
    ) {

        int n =
            piles.length;

        if (
            i >= n
        ) {

            return 0;
        }

        if (
            i + 2 * M >= n
        ) {

            return suffixSum[i];
        }

        if (
            dp[i][M] != 0
        ) {

            return dp[i][M];
        }

        int minOpponent =
            Integer.MAX_VALUE;

        for (
            int X = 1;
            X <= 2 * M;
            X++
        ) {

            minOpponent =
                Math.min(
                    minOpponent,
                    solve(
                        i + X,
                        Math.max(M, X),
                        piles,
                        dp,
                        suffixSum
                    )
                );
        }

        dp[i][M] =
            suffixSum[i] -
            minOpponent;

        return dp[i][M];
    }
}