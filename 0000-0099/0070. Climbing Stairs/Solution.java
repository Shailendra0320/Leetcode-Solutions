//Approach-1 (Memoization / Top-Down DP)
//T.C : O(n)
//S.C : O(n)

import java.util.Arrays;

class Solution {

    private int helper(int n, int[] dp) {

        if (n == 1 || n == 2) {
            return n;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        dp[n] =
            helper(n - 1, dp) +
            helper(n - 2, dp);

        return dp[n];
    }

    public int climbStairs(int n) {

        int[] dp = new int[n + 1];

        Arrays.fill(dp, -1);

        return helper(n, dp);
    }
}


/*
//Approach-2 (Tabulation / Bottom-Up DP)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int climbStairs(int n) {

        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;

        dp[2] = 2;

        for (int i = 3; i <= n; i++) {

            dp[i] =
                dp[i - 1] +
                dp[i - 2];
        }

        return dp[n];
    }
}
*/


/*
//Approach-3 (Space Optimized DP)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public int climbStairs(int n) {

        if (n <= 2) {
            return n;
        }

        int previousTwo = 1;

        int previousOne = 2;

        for (int i = 3; i <= n; i++) {

            int current =
                previousOne +
                previousTwo;

            previousTwo = previousOne;

            previousOne = current;
        }

        return previousOne;
    }
}