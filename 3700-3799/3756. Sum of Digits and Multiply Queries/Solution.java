//Approach-1 (Prefix Sum + Rolling Hash)
//T.C : O(n + q)
//S.C : O(n)

class Solution {

    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(
        String s,
        int[][] queries
    ) {

        int n = s.length();

        long[] power10 =
            new long[n + 1];

        power10[0] = 1;

        for (int i = 1; i <= n; i++) {

            power10[i] =
                (power10[i - 1] * 10)
                % MOD;
        }

        long[] prefixHash =
            new long[n + 1];

        int[] prefixCount =
            new int[n + 1];

        int[] prefixSum =
            new int[n + 1];

        for (int i = 0; i < n; i++) {

            int digit =
                s.charAt(i) - '0';

            prefixCount[i + 1] =
                prefixCount[i];

            prefixSum[i + 1] =
                prefixSum[i];

            prefixHash[i + 1] =
                prefixHash[i];

            if (digit != 0) {

                prefixCount[i + 1]++;

                prefixSum[i + 1] += digit;

                prefixHash[i + 1] =
                    (
                        prefixHash[i] * 10
                        + digit
                    ) % MOD;
            }
        }

        int[] answer =
            new int[queries.length];

        for (
            int i = 0;
            i < queries.length;
            i++
        ) {

            int left =
                queries[i][0];

            int right =
                queries[i][1];

            int digitSum =
                prefixSum[right + 1]
                -
                prefixSum[left];

            int digits =
                prefixCount[right + 1]
                -
                prefixCount[left];

            long number =
                (
                    prefixHash[right + 1]
                    -
                    (
                        prefixHash[left]
                        *
                        power10[digits]
                    ) % MOD
                    +
                    MOD
                ) % MOD;

            answer[i] =
                (int)
                (
                    number
                    *
                    digitSum
                    % MOD
                );
        }

        return answer;
    }
}