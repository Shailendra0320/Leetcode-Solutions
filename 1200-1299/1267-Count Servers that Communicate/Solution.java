// Approach-1 (Brute Force: Scan the Current Row and Column)
// T.C: O(m * n * (m + n))
// S.C: O(1)

class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int answer = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                boolean canCommunicate = false;

                for (int col = 0; col < n; col++) {
                    if (col != j && grid[i][col] == 1) {
                        canCommunicate = true;
                        break;
                    }
                }

                if (!canCommunicate) {
                    for (int row = 0; row < m; row++) {
                        if (row != i && grid[row][j] == 1) {
                            canCommunicate = true;
                            break;
                        }
                    }
                }

                if (canCommunicate) {
                    answer++;
                }
            }
        }

        return answer;
    }
}

// Approach-2 (Row and Column Frequency Counting)
// T.C: O(m * n)
// S.C: O(m + n)

class Solution2 {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 &&
                    (rowCount[i] > 1 || colCount[j] > 1)) {
                    answer++;
                }
            }
        }

        return answer;
    }
}

// Approach-3 (In-Place Marking: Rows First, Then Columns)
// T.C: O(m * n)
// S.C: O(1)

class Solution3 {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int answer = 0;

        for (int i = 0; i < m; i++) {
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    count++;
                }
            }

            if (count > 1) {
                answer += count;

                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        grid[i][j] = -1;
                    }
                }
            }
        }

        for (int j = 0; j < n; j++) {
            int total = 0;
            int unmarked = 0;

            for (int i = 0; i < m; i++) {
                if (grid[i][j] != 0) {
                    total++;
                }

                if (grid[i][j] == 1) {
                    unmarked++;
                }
            }

            if (total > 1) {
                answer += unmarked;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == -1) {
                    grid[i][j] = 1;
                }
            }
        }

        return answer;
    }
}