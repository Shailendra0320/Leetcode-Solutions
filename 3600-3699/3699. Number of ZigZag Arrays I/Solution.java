class Solution {

    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {

        if (n == 1) {
            return (r - l + 1) % MOD;
        }

        int m = r - l + 1;

        long[] dpUp = new long[m];
        long[] dpDown = new long[m];

        for (int i = 0; i < m; i++) {
            dpUp[i] = 1;
            dpDown[i] = 1;
        }

        for (int len = 2; len <= n; len++) {

            long[] prefixUp = new long[m + 1];
            long[] prefixDown = new long[m + 1];

            for (int i = 0; i < m; i++) {

                prefixUp[i + 1] =
                    (prefixUp[i] + dpUp[i]) % MOD;

                prefixDown[i + 1] =
                    (prefixDown[i] + dpDown[i]) % MOD;
            }

            long[] nextUp = new long[m];
            long[] nextDown = new long[m];

            for (int i = 0; i < m; i++) {

                nextUp[i] =
                    (
                        prefixDown[i]
                        -
                        prefixDown[0]
                        +
                        MOD
                    ) % MOD;

                nextDown[i] =
                    (
                        prefixUp[m]
                        -
                        prefixUp[i + 1]
                        +
                        MOD
                    ) % MOD;
            }

            dpUp = nextUp;
            dpDown = nextDown;
        }

        long answer = 0;

        for (int i = 0; i < m; i++) {

            answer =
                (
                    answer
                    +
                    dpUp[i]
                    +
                    dpDown[i]
                ) % MOD;
        }

        return (int) answer;
    }
}