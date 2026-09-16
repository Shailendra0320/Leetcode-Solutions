//Approach-1 (Dynamic Programming + Prefix Sum)
//T.C : O(n * k)
//S.C : O(n * k)

class Solution {
    private static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k) {

        // dp[i][j] = number of ways to choose j segments
        // using the first i points.
        long[][] dp = new long[n + 1][k + 1];

        // Choosing 0 segments has exactly 1 way.
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= k; j++) {

            long prefix = 0;

            for (int i = 1; i <= n; i++) {

                // Add dp[i - 1][j - 1] to the prefix.
                prefix = (prefix + dp[i - 1][j - 1]) % MOD;

                // Two possibilities:
                // 1. Point i-1 is not the right endpoint.
                // 2. A new segment ends at point i-1.
                dp[i][j] = (dp[i - 1][j] + prefix) % MOD;
            }
        }

        return (int) dp[n][k];
    }
}


//Approach-2 (Combinatorics + Modular Arithmetic)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
    private static final long MOD = 1_000_000_007L;

    private long power(long base, long exponent) {

        long result = 1;

        while (exponent > 0) {

            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }

    public int numberOfSets(int n, int k) {

        int N = n + k - 1;
        int R = 2 * k;

        if (R > N) {
            return 0;
        }

        long[] fact = new long[N + 1];
        long[] invFact = new long[N + 1];

        fact[0] = 1;

        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[N] = power(fact[N], MOD - 2);

        for (int i = N; i >= 1; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }

        long answer = fact[N];

        answer = answer * invFact[R] % MOD;
        answer = answer * invFact[N - R] % MOD;

        return (int) answer;
    }
}