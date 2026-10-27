//Approach-1 (Open Top Row and Last Column)
//T.C : O(m * n)
//S.C : O(m * n)

import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        Arrays.fill(grid[0], '.');

        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        String[] answer = new String[m];

        for (int i = 0; i < m; i++) {
            answer[i] = new String(grid[i]);
        }

        return answer;
    }
}

//Approach-2 (Open First Column and Last Row)
//T.C : O(m * n)
//S.C : O(m * n)

class Solution2 {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        for (int i = 0; i < m; i++) {
            grid[i][0] = '.';
        }

        Arrays.fill(grid[m - 1], '.');

        String[] answer = new String[m];

        for (int i = 0; i < m; i++) {
            answer[i] = new String(grid[i]);
        }

        return answer;
    }
}