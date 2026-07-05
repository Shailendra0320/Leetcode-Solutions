// Approach-1 (Bottom-Up Dynamic Programming)
// T.C : O(n²)
// S.C : O(n²)

class Solution
{
public:
  vector<int> pathsWithMaxScore(vector<string> &board)
  {

    int n = board.size();

    const int MOD = 1000000007;

    vector<vector<int>> maxScore(
        n,
        vector<int>(n, -1));

    vector<vector<int>> pathCount(
        n,
        vector<int>(n, 0));

    maxScore[n - 1][n - 1] = 0;

    pathCount[n - 1][n - 1] = 1;

    for (int row = n - 1; row >= 0; row--)
    {

      for (int col = n - 1; col >= 0; col--)
      {

        if (board[row][col] == 'X')
        {

          continue;
        }

        if (
            row == n - 1 &&
            col == n - 1)
        {

          continue;
        }

        int bestScore = -1;

        int ways = 0;

        vector<pair<int, int>> directions = {
            {row + 1, col},
            {row, col + 1},
            {row + 1, col + 1}};

        for (auto &next : directions)
        {

          int newRow = next.first;

          int newCol = next.second;

          if (
              newRow >= n ||
              newCol >= n ||
              maxScore[newRow][newCol] == -1)
          {

            continue;
          }

          if (
              maxScore[newRow][newCol] >
              bestScore)
          {

            bestScore =
                maxScore[newRow][newCol];

            ways =
                pathCount[newRow][newCol];
          }
          else if (
              maxScore[newRow][newCol] ==
              bestScore)
          {

            ways =
                (ways +
                 pathCount[newRow][newCol]) %
                MOD;
          }
        }

        if (bestScore == -1)
        {

          continue;
        }

        if (
            isdigit(board[row][col]))
        {

          bestScore +=
              board[row][col] - '0';
        }

        maxScore[row][col] =
            bestScore;

        pathCount[row][col] =
            ways;
      }
    }

    if (pathCount[0][0] == 0)
    {

      return {0, 0};
    }

    return {
        maxScore[0][0],
        pathCount[0][0]};
  }
};

/*
//Approach-2 (Top-Down Dynamic Programming + Memoization)
//T.C : O(n²)
//S.C : O(n²)

class Solution {
public:

    const int MOD = 1000000007;

    vector<vector<int>> score;

    vector<vector<int>> ways;

    pair<int,int> dfs(
        int row,
        int col,
        vector<string>& board
    ) {

        int n = board.size();

        if (
            row < 0 ||
            col < 0 ||
            board[row][col] == 'X'
        ) {

            return {-1, 0};
        }

        if (
            row == n - 1 &&
            col == n - 1
        ) {

            return {0, 1};
        }

        if (score[row][col] != -2) {

            return {
                score[row][col],
                ways[row][col]
            };
        }

        vector<pair<int,int>> moves = {
            {row + 1, col},
            {row, col + 1},
            {row + 1, col + 1}
        };

        int bestScore = -1;

        long long totalWays = 0;

        for (auto &next : moves) {

            auto result =
                dfs(
                    next.first,
                    next.second,
                    board
                );

            if (result.first == -1) {

                continue;
            }

            if (result.first > bestScore) {

                bestScore = result.first;

                totalWays = result.second;

            }
            else if (
                result.first == bestScore
            ) {

                totalWays =
                    (totalWays +
                     result.second)
                    % MOD;
            }
        }

        if (bestScore == -1) {

            score[row][col] = -1;

            ways[row][col] = 0;

            return {-1, 0};
        }

        if (
            isdigit(board[row][col])
        ) {

            bestScore +=
                board[row][col] - '0';
        }

        score[row][col] = bestScore;

        ways[row][col] = totalWays;

        return {
            bestScore,
            (int) totalWays
        };
    }

    vector<int> pathsWithMaxScore(
        vector<string>& board
    ) {

        int n = board.size();

        score.assign(
            n,
            vector<int>(n, -2)
        );

        ways.assign(
            n,
            vector<int>(n, 0)
        );

        auto answer =
            dfs(
                0,
                0,
                board
            );

        if (answer.second == 0) {

            return {0, 0};
        }

        return {
            answer.first,
            answer.second
        };
    }
};
*/