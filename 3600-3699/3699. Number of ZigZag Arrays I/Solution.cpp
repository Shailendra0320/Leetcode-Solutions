class Solution {
public:

    static constexpr long long MOD = 1000000007LL;

    int zigZagArrays(
        int n,
        int l,
        int r
    ) {

        if (n == 1) {
            return (r - l + 1) % MOD;
        }

        int m = r - l + 1;

        vector<long long> dpUp(m, 1);
        vector<long long> dpDown(m, 1);

        for (int len = 2; len <= n; len++) {

            vector<long long> prefixUp(m + 1, 0);
            vector<long long> prefixDown(m + 1, 0);

            for (int i = 0; i < m; i++) {

                prefixUp[i + 1] =
                    (
                        prefixUp[i]
                        +
                        dpUp[i]
                    ) % MOD;

                prefixDown[i + 1] =
                    (
                        prefixDown[i]
                        +
                        dpDown[i]
                    ) % MOD;
            }

            vector<long long> nextUp(m);
            vector<long long> nextDown(m);

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

            dpUp = move(nextUp);
            dpDown = move(nextDown);
        }

        long long answer = 0;

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
};