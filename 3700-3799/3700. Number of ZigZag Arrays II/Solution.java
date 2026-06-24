class Solution {

    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;

        if (n == 1) {
            return m;
        }

        int size = 2 * m;

        long[][] transition = new long[size][size];

        for (int current = 0; current < m; current++) {

            for (int previous = 0; previous < current; previous++) {

                transition[current][m + previous] = 1;
            }
        }

        for (int current = 0; current < m; current++) {

            for (int previous = current + 1; previous < m; previous++) {

                transition[m + current][previous] = 1;
            }
        }

        long[] state = new long[size];

        for (int value = 0; value < m; value++) {

            state[value] = value;

            state[m + value] = m - value - 1;
        }

        long power = n - 2;

        long[][] currentMatrix = transition;

        while (power > 0) {

            if ((power & 1) == 1) {

                state = multiply(currentMatrix, state);
            }

            currentMatrix =
                multiply(
                    currentMatrix,
                    currentMatrix
                );

            power >>= 1;
        }

        long answer = 0;

        for (long value : state) {

            answer =
                (
                    answer
                    +
                    value
                ) % MOD;
        }

        return (int) answer;
    }

    private long[] multiply(
        long[][] matrix,
        long[] vector
    ) {

        int size = matrix.length;

        long[] result =
            new long[size];

        for (int row = 0; row < size; row++) {

            long sum = 0;

            for (int col = 0; col < size; col++) {

                if (matrix[row][col] == 0) {
                    continue;
                }

                sum =
                    (
                        sum
                        +
                        matrix[row][col]
                        *
                        vector[col]
                    ) % MOD;
            }

            result[row] = sum;
        }

        return result;
    }

    private long[][] multiply(
        long[][] first,
        long[][] second
    ) {

        int size = first.length;

        long[][] result =
            new long[size][size];

        for (int row = 0; row < size; row++) {

            for (int mid = 0; mid < size; mid++) {

                if (first[row][mid] == 0) {
                    continue;
                }

                long value =
                    first[row][mid];

                for (int col = 0; col < size; col++) {

                    if (second[mid][col] == 0) {
                        continue;
                    }

                    result[row][col] =
                        (
                            result[row][col]
                            +
                            value
                            *
                            second[mid][col]
                        ) % MOD;
                }
            }
        }

        return result;
    }
}

/*
class Solution {
    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        long[][] trans = new long[size][size];

        for (int v = 0; v < m; v++) {
            for (int u = 0; u < v; u++) {
                trans[v][m + u] = 1;
            }
        }

        for (int v = 0; v < m; v++) {
            for (int u = v + 1; u < m; u++) {
                trans[m + v][u] = 1;
            }
        }

        long[] state = new long[size];

        for (int v = 0; v < m; v++) {
            state[v] = v;
            state[m + v] = m - 1 - v;
        }

        long power = n - 2;
        long[][] cur = trans;

        while (power > 0) {
            if ((power & 1) == 1) {
                state = multiply(cur, state);
            }
            cur = multiply(cur, cur);
            power >>= 1;
        }

        long ans = 0;

        for (long x : state) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }

    private long[] multiply(long[][] mat, long[] vec) {
        int n = mat.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;

            for (int j = 0; j < n; j++) {
                if (mat[i][j] != 0) {
                    sum = (sum + mat[i][j] * vec[j]) % MOD;
                }
            }

            res[i] = sum;
        }

        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;

                long val = a[i][k];

                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;

                    res[i][j] = (res[i][j] + val * b[k][j]) % MOD;
                }
            }
        }

        return res;
    }
}
*/