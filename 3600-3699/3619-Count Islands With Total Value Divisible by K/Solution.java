//Approach-1 (Iterative DFS with In-Place Marking)
//T.C : O(m * n)
//S.C : O(m * n)

import java.util.*;

class Solution {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private long exploreIsland(int[][] grid, int sr, int sc) {
        int m = grid.length;
        int n = grid[0].length;

        long total = grid[sr][sc];
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(sr * n + sc);
        grid[sr][sc] = 0;

        while (!stack.isEmpty()) {
            int cell = stack.pop();
            int r = cell / n;
            int c = cell % n;

            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr >= 0 && nr < m &&
                    nc >= 0 && nc < n &&
                    grid[nr][nc] > 0) {

                    total += grid[nr][nc];
                    grid[nr][nc] = 0;
                    stack.push(nr * n + nc);
                }
            }
        }

        return total;
    }

    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int answer = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] > 0) {
                    long total = exploreIsland(grid, r, c);

                    if (total % k == 0) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
}

//Approach-2 (BFS with Separate Visited Matrix)
//T.C : O(m * n)
//S.C : O(m * n)

class Solution2 {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        int answer = 0;

        Deque<Integer> queue = new ArrayDeque<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0 || visited[r][c]) {
                    continue;
                }

                long total = 0;

                visited[r][c] = true;
                queue.offer(r * n + c);

                while (!queue.isEmpty()) {
                    int cell = queue.poll();
                    int cr = cell / n;
                    int cc = cell % n;

                    total += grid[cr][cc];

                    for (int d = 0; d < 4; d++) {
                        int nr = cr + DR[d];
                        int nc = cc + DC[d];

                        if (nr >= 0 && nr < m &&
                            nc >= 0 && nc < n &&
                            grid[nr][nc] > 0 &&
                            !visited[nr][nc]) {

                            visited[nr][nc] = true;
                            queue.offer(nr * n + nc);
                        }
                    }
                }

                if (total % k == 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}