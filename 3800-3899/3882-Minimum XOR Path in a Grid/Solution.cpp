//Approach-1 (Full Grid DP with XOR States)
//T.C : O(m * n * 1024)
//S.C : O(m * n * 1024)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minCost(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        const int MAX_XOR = 1024;

        vector<vector<vector<char>>> dp(
            m,
            vector<vector<char>>(
                n, vector<char>(MAX_XOR, 0)
            )
        );

        dp[0][0][grid[0][0]] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int value = grid[i][j];

                if (i > 0) {
                    for (int x = 0; x < MAX_XOR; x++) {
                        if (dp[i - 1][j][x]) {
                            dp[i][j][x ^ value] = 1;
                        }
                    }
                }

                if (j > 0) {
                    for (int x = 0; x < MAX_XOR; x++) {
                        if (dp[i][j - 1][x]) {
                            dp[i][j][x ^ value] = 1;
                        }
                    }
                }
            }
        }

        for (int x = 0; x < MAX_XOR; x++) {
            if (dp[m - 1][n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
};

//Approach-2 (Rolling Row DP with XOR States)
//T.C : O(m * n * 1024)
//S.C : O(n * 1024)

class Solution2 {
public:
    int minCost(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        const int MAX_XOR = 1024;

        vector<vector<char>> prev(
            n, vector<char>(MAX_XOR, 0)
        );

        for (int i = 0; i < m; i++) {
            vector<vector<char>> curr(
                n, vector<char>(MAX_XOR, 0)
            );

            for (int j = 0; j < n; j++) {
                int value = grid[i][j];

                if (i == 0 && j == 0) {
                    curr[j][value] = 1;
                    continue;
                }

                if (i > 0) {
                    for (int x = 0; x < MAX_XOR; x++) {
                        if (prev[j][x]) {
                            curr[j][x ^ value] = 1;
                        }
                    }
                }

                if (j > 0) {
                    for (int x = 0; x < MAX_XOR; x++) {
                        if (curr[j - 1][x]) {
                            curr[j][x ^ value] = 1;
                        }
                    }
                }
            }

            prev.swap(curr);
        }

        for (int x = 0; x < MAX_XOR; x++) {
            if (prev[n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
};