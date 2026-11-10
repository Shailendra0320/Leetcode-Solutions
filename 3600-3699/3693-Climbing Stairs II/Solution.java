//Approach-1 (Recursion)
//T.C: O(3^n)
//S.C: O(n)

class Solution {
    public int climbStairs(int n, int[] costs) {
        return solve(0, n, costs);
    }

    private int solve(int curr, int n, int[] costs) {
        if (curr == n) {
            return 0;
        }

        int answer = Integer.MAX_VALUE;

        for (int jump = 1; jump <= 3; jump++) {
            int next = curr + jump;

            if (next <= n) {
                int cost = costs[next - 1] + jump * jump;
                answer = Math.min(answer, cost + solve(next, n, costs));
            }
        }

        return answer;
    }
}

//Approach-2 (Recursion + Memoization)
//T.C: O(n)
//S.C: O(n)

class Solution2 {
    public int climbStairs(int n, int[] costs) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return solve(0, n, costs, memo);
    }

    private int solve(int curr, int n, int[] costs, int[] memo) {
        if (curr == n) {
            return 0;
        }

        if (memo[curr] != -1) {
            return memo[curr];
        }

        int answer = Integer.MAX_VALUE;

        for (int jump = 1; jump <= 3; jump++) {
            int next = curr + jump;

            if (next <= n) {
                int cost = costs[next - 1] + jump * jump;
                answer = Math.min(answer, cost + solve(next, n, costs, memo));
            }
        }

        return memo[curr] = answer;
    }
}

//Approach-3 (Bottom-Up Dynamic Programming)
//T.C: O(n)
//S.C: O(n)

class Solution3 {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int jump = 1; jump <= 3; jump++) {
                int prev = i - jump;

                if (prev >= 0) {
                    int cost = costs[i - 1] + jump * jump;

                    dp[i] = Math.min(dp[i], dp[prev] + cost);
                }
            }
        }

        return dp[n];
    }
}

//Approach-4 (Space Optimized DP: Rolling Array)
//T.C: O(n)
//S.C: O(1)

class Solution4 {
    public int climbStairs(int n, int[] costs) {
        int INF = Integer.MAX_VALUE;

        int[] dp = new int[4];
        Arrays.fill(dp, INF);

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            int current = INF;

            for (int jump = 1; jump <= 3; jump++) {
                int prev = i - jump;

                if (prev >= 0) {
                    int cost = costs[i - 1] + jump * jump;

                    current = Math.min(
                        current,
                        dp[prev % 4] + cost
                    );
                }
            }

            dp[i % 4] = current;
        }

        return dp[n % 4];
    }
}