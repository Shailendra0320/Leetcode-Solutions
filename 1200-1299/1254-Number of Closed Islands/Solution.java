//Approach-1 (DFS with Boundary Check)
//T.C : O(m * n)
//S.C : O(m * n)

import java.util.*;

class Solution {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private boolean exploreIsland(int[][] grid, int sr, int sc) {
        int m = grid.length;
        int n = grid[0].length;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(sr * n + sc);
        grid[sr][sc] = 1;

        boolean closed = true;

        while (!stack.isEmpty()) {
            int cell = stack.pop();
            int r = cell / n;
            int c = cell % n;

            if (r == 0 || r == m - 1 || c == 0 || c == n - 1) {
                closed = false;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr >= 0 && nr < m &&
                    nc >= 0 && nc < n &&
                    grid[nr][nc] == 0) {

                    grid[nr][nc] = 1;
                    stack.push(nr * n + nc);
                }
            }
        }

        return closed;
    }

    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int answer = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    if (exploreIsland(grid, r, c)) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
}

//Approach-2 (Flood Boundary Land First)
//T.C : O(m * n)
//S.C : O(m * n)

class Solution2 {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private void flood(int[][] grid, int sr, int sc) {
        int m = grid.length;
        int n = grid[0].length;

        if (grid[sr][sc] != 0) {
            return;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(sr * n + sc);
        grid[sr][sc] = 1;

        while (!stack.isEmpty()) {
            int cell = stack.pop();
            int r = cell / n;
            int c = cell % n;

            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr >= 0 && nr < m &&
                    nc >= 0 && nc < n &&
                    grid[nr][nc] == 0) {

                    grid[nr][nc] = 1;
                    stack.push(nr * n + nc);
                }
            }
        }
    }

    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int r = 0; r < m; r++) {
            flood(grid, r, 0);
            flood(grid, r, n - 1);
        }

        for (int c = 0; c < n; c++) {
            flood(grid, 0, c);
            flood(grid, m - 1, c);
        }

        int answer = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    answer++;
                    flood(grid, r, c);
                }
            }
        }

        return answer;
    }
}