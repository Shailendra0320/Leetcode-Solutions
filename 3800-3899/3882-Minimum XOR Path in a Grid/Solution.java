//Approach-1 (Full Grid DP with XOR States)
//T.C : O(m * n * 1024)
//S.C : O(m * n * 1024)

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MAX_XOR = 1024;

        boolean[][][] dp = new boolean[m][n][MAX_XOR];

        dp[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int value = grid[i][j];

                if (i > 0) {
                    for (int x = 0; x < MAX_XOR; x++) {
                        if (dp[i - 1][j][x]) {
                            dp[i][j][x ^ value] = true;
                        }
                    }
                }

                if (j > 0) {
                    for (int x = 0; x < MAX_XOR; x++) {
                        if (dp[i][j - 1][x]) {
                            dp[i][j][x ^ value] = true;
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
}

//Approach-2 (Rolling Row DP with XOR States)
//T.C : O(m * n * 1024)
//S.C : O(n * 1024)

class Solution2 {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MAX_XOR = 1024;

        boolean[][] prev = new boolean[n][MAX_XOR];

        for (int i = 0; i < m; i++) {
            boolean[][] curr = new boolean[n][MAX_XOR];

            for (int j = 0; j < n; j++) {
                int value = grid[i][j];

                if (i == 0 && j == 0) {
                    curr[j][value] = true;
                    continue;
                }

                if (i > 0) {
                    for (int x = 0; x < MAX_XOR; x++) {
                        if (prev[j][x]) {
                            curr[j][x ^ value] = true;
                        }
                    }
                }

                if (j > 0) {
                    for (int x = 0; x < MAX_XOR; x++) {
                        if (curr[j - 1][x]) {
                            curr[j][x ^ value] = true;
                        }
                    }
                }
            }

            prev = curr;
        }

        for (int x = 0; x < MAX_XOR; x++) {
            if (prev[n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
}