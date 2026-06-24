class Solution {
public:

    static constexpr long long MOD = 1000000007LL;

    int zigZagArrays(
        int n,
        int l,
        int r
    ) {

        int m = r - l + 1;

        if (n == 1) {
            return m;
        }

        int size = 2 * m;

        vector<vector<long long>> transition(
            size,
            vector<long long>(size, 0)
        );

        for (
            int current = 0;
            current < m;
            current++
        ) {

            for (
                int previous = 0;
                previous < current;
                previous++
            ) {

                transition[current][m + previous] = 1;
            }
        }

        for (
            int current = 0;
            current < m;
            current++
        ) {

            for (
                int previous = current + 1;
                previous < m;
                previous++
            ) {

                transition[m + current][previous] = 1;
            }
        }

        vector<long long> state(size);

        for (int value = 0; value < m; value++) {

            state[value] = value;

            state[m + value] = m - value - 1;
        }

        long long power = n - 2;

        auto currentMatrix = transition;

        while (power > 0) {

            if (power & 1) {

                state =
                    multiply(
                        currentMatrix,
                        state
                    );
            }

            currentMatrix =
                multiply(
                    currentMatrix,
                    currentMatrix
                );

            power >>= 1;
        }

        long long answer = 0;

        for (long long value : state) {

            answer =
                (
                    answer
                    +
                    value
                ) % MOD;
        }

        return (int)answer;
    }

private:

    vector<long long> multiply(
        vector<vector<long long>>& matrix,
        vector<long long>& vectorState
    ) {

        int size = matrix.size();

        vector<long long> result(size);

        for (int row = 0; row < size; row++) {

            long long sum = 0;

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
                        vectorState[col]
                    ) % MOD;
            }

            result[row] = sum;
        }

        return result;
    }

    vector<vector<long long>> multiply(
        vector<vector<long long>>& first,
        vector<vector<long long>>& second
    ) {

        int size = first.size();

        vector<vector<long long>> result(
            size,
            vector<long long>(size, 0)
        );

        for (int row = 0; row < size; row++) {

            for (int mid = 0; mid < size; mid++) {

                if (first[row][mid] == 0) {
                    continue;
                }

                long long value =
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
};