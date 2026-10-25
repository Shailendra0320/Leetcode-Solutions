//Approach-1 (DFS with Boundary Check)
//T.C : O(m * n)
//S.C : O(m * n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    const int dr[4] = {-1, 1, 0, 0};
    const int dc[4] = {0, 0, -1, 1};

    bool exploreIsland(vector<vector<int>>& grid, int sr, int sc) {
        int m = grid.size();
        int n = grid[0].size();

        vector<int> st;
        st.push_back(sr * n + sc);
        grid[sr][sc] = 1;

        bool closed = true;

        while (!st.empty()) {
            int cell = st.back();
            st.pop_back();

            int r = cell / n;
            int c = cell % n;

            if (r == 0 || r == m - 1 || c == 0 || c == n - 1) {
                closed = false;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < m &&
                    nc >= 0 && nc < n &&
                    grid[nr][nc] == 0) {

                    grid[nr][nc] = 1;
                    st.push_back(nr * n + nc);
                }
            }
        }

        return closed;
    }

public:
    int closedIsland(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
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
};

//Approach-2 (Flood Boundary Land First)
//T.C : O(m * n)
//S.C : O(m * n)

class Solution2 {
private:
    const int dr[4] = {-1, 1, 0, 0};
    const int dc[4] = {0, 0, -1, 1};

    void flood(vector<vector<int>>& grid, int sr, int sc) {
        int m = grid.size();
        int n = grid[0].size();

        if (grid[sr][sc] != 0) {
            return;
        }

        vector<int> st;
        st.push_back(sr * n + sc);
        grid[sr][sc] = 1;

        while (!st.empty()) {
            int cell = st.back();
            st.pop_back();

            int r = cell / n;
            int c = cell % n;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < m &&
                    nc >= 0 && nc < n &&
                    grid[nr][nc] == 0) {

                    grid[nr][nc] = 1;
                    st.push_back(nr * n + nc);
                }
            }
        }
    }

public:
    int closedIsland(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();

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
};