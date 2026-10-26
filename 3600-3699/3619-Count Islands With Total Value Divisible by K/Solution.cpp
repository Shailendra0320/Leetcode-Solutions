//Approach-1 (Iterative DFS with In-Place Marking)
//T.C : O(m * n)
//S.C : O(m * n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    const int dr[4] = {-1, 1, 0, 0};
    const int dc[4] = {0, 0, -1, 1};

    long long exploreIsland(vector<vector<int>>& grid,
                            int sr, int sc) {
        int m = grid.size();
        int n = grid[0].size();

        long long total = grid[sr][sc];
        vector<int> st;

        st.push_back(sr * n + sc);
        grid[sr][sc] = 0;

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
                    grid[nr][nc] > 0) {

                    total += grid[nr][nc];
                    grid[nr][nc] = 0;
                    st.push_back(nr * n + nc);
                }
            }
        }

        return total;
    }

public:
    int countIslands(vector<vector<int>>& grid, int k) {
        int m = grid.size();
        int n = grid[0].size();
        int answer = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] > 0) {
                    long long total = exploreIsland(grid, r, c);

                    if (total % k == 0) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
};


//Approach-2 (BFS with Separate Visited Matrix)
//T.C : O(m * n)
//S.C : O(m * n)

class Solution2 {
private:
    const int dr[4] = {-1, 1, 0, 0};
    const int dc[4] = {0, 0, -1, 1};

public:
    int countIslands(vector<vector<int>>& grid, int k) {
        int m = grid.size();
        int n = grid[0].size();

        vector<vector<bool>> visited(
            m, vector<bool>(n, false)
        );

        queue<int> q;
        int answer = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0 || visited[r][c]) {
                    continue;
                }

                long long total = 0;

                visited[r][c] = true;
                q.push(r * n + c);

                while (!q.empty()) {
                    int cell = q.front();
                    q.pop();

                    int cr = cell / n;
                    int cc = cell % n;

                    total += grid[cr][cc];

                    for (int d = 0; d < 4; d++) {
                        int nr = cr + dr[d];
                        int nc = cc + dc[d];

                        if (nr >= 0 && nr < m &&
                            nc >= 0 && nc < n &&
                            grid[nr][nc] > 0 &&
                            !visited[nr][nc]) {

                            visited[nr][nc] = true;
                            q.push(nr * n + nc);
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
};