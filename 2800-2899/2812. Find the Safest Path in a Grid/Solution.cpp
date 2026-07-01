//Approach-1 (Multi-Source BFS + Binary Search + BFS)
//T.C : O(n² log n)
//S.C : O(n²)

class Solution {
public:

    vector<vector<int>> directions{
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };

    bool canReach(
        vector<vector<int>>& distance,
        int n,
        int limit
    ) {

        if (
            distance[0][0] < limit ||
            distance[n - 1][n - 1] < limit
        ) {
            return false;
        }

        vector<vector<bool>> visited(
            n,
            vector<bool>(n, false)
        );

        queue<pair<int, int>> bfsQueue;

        bfsQueue.push({0, 0});

        visited[0][0] = true;

        while (!bfsQueue.empty()) {

            auto current = bfsQueue.front();

            bfsQueue.pop();

            int row = current.first;

            int col = current.second;

            if (
                row == n - 1 &&
                col == n - 1
            ) {
                return true;
            }

            for (auto& direction : directions) {

                int nextRow = row + direction[0];

                int nextCol = col + direction[1];

                if (
                    nextRow >= 0 &&
                    nextRow < n &&
                    nextCol >= 0 &&
                    nextCol < n &&
                    !visited[nextRow][nextCol] &&
                    distance[nextRow][nextCol] >= limit
                ) {

                    visited[nextRow][nextCol] = true;

                    bfsQueue.push(
                        {
                            nextRow,
                            nextCol
                        }
                    );
                }
            }
        }

        return false;
    }

    int maximumSafenessFactor(
        vector<vector<int>>& grid
    ) {

        int n = grid.size();

        vector<vector<int>> distance(
            n,
            vector<int>(n, -1)
        );

        queue<pair<int, int>> bfsQueue;

        for (int row = 0; row < n; row++) {

            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 1) {

                    distance[row][col] = 0;

                    bfsQueue.push(
                        {
                            row,
                            col
                        }
                    );
                }
            }
        }

        while (!bfsQueue.empty()) {

            auto current = bfsQueue.front();

            bfsQueue.pop();

            for (auto& direction : directions) {

                int nextRow =
                    current.first + direction[0];

                int nextCol =
                    current.second + direction[1];

                if (
                    nextRow >= 0 &&
                    nextRow < n &&
                    nextCol >= 0 &&
                    nextCol < n &&
                    distance[nextRow][nextCol] == -1
                ) {

                    distance[nextRow][nextCol] =
                        distance[current.first][current.second] + 1;

                    bfsQueue.push(
                        {
                            nextRow,
                            nextCol
                        }
                    );
                }
            }
        }

        if (
            distance[0][0] == 0 ||
            distance[n - 1][n - 1] == 0
        ) {
            return 0;
        }

        int low = 0;

        int high = 2 * n;

        int answer = 0;

        while (low <= high) {

            int middle = (low + high) / 2;

            if (
                canReach(
                    distance,
                    n,
                    middle
                )
            ) {

                answer = middle;

                low = middle + 1;

            } else {

                high = middle - 1;
            }
        }

        return answer;
    }
};