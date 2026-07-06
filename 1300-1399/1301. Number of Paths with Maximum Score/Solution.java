//Approach-1 (Bottom-Up Dynamic Programming)
//T.C : O(n²)
//S.C : O(n²)

import java.util.*;

class Solution {

  public int[] pathsWithMaxScore(List<String> board) {

    int size = board.size();

    int mod = 1000000007;

    int[][] maxScore = new int[size][size];

    int[][] pathCount = new int[size][size];

    for (int i = 0; i < size; i++) {

      Arrays.fill(maxScore[i], -1);
    }

    maxScore[size - 1][size - 1] = 0;

    pathCount[size - 1][size - 1] = 1;

    for (int row = size - 1; row >= 0; row--) {

      for (int col = size - 1; col >= 0; col--) {

        if (board.get(row).charAt(col) == 'X') {

          continue;
        }

        if (row == size - 1 &&
            col == size - 1) {

          continue;
        }

        int bestScore = -1;

        int ways = 0;

        if (row + 1 < size &&
            maxScore[row + 1][col] != -1) {

          if (maxScore[row + 1][col] > bestScore) {

            bestScore = maxScore[row + 1][col];

            ways = pathCount[row + 1][col];

          } else if (maxScore[row + 1][col] == bestScore) {

            ways = (ways +
                pathCount[row + 1][col])
                % mod;
          }
        }

        if (col + 1 < size &&
            maxScore[row][col + 1] != -1) {

          if (maxScore[row][col + 1] > bestScore) {

            bestScore = maxScore[row][col + 1];

            ways = pathCount[row][col + 1];

          } else if (maxScore[row][col + 1] == bestScore) {

            ways = (ways +
                pathCount[row][col + 1])
                % mod;
          }
        }

        if (row + 1 < size &&
            col + 1 < size &&
            maxScore[row + 1][col + 1] != -1) {

          if (maxScore[row + 1][col + 1] > bestScore) {

            bestScore = maxScore[row + 1][col + 1];

            ways = pathCount[row + 1][col + 1];

          } else if (maxScore[row + 1][col + 1] == bestScore) {

            ways = (ways +
                pathCount[row + 1][col + 1])
                % mod;
          }
        }

        if (bestScore == -1) {

          continue;
        }

        char current = board.get(row).charAt(col);

        if (Character.isDigit(current)) {

          bestScore += current - '0';
        }

        maxScore[row][col] = bestScore;

        pathCount[row][col] = ways;
      }
    }

    if (pathCount[0][0] == 0) {

      return new int[] { 0, 0 };
    }

    return new int[] {
        maxScore[0][0],
        pathCount[0][0]
    };
  }
}

/*
 * //Approach-2 (Top-Down Dynamic Programming + Memoization)
 * //T.C : O(n²)
 * //S.C : O(n²)
 * 
 * import java.util.*;
 * 
 * class Solution {
 * 
 * private final int MOD = 1000000007;
 * 
 * private int[][] score;
 * 
 * private int[][] ways;
 * 
 * private boolean[][] visited;
 * 
 * public int[] pathsWithMaxScore(List<String> board) {
 * 
 * int n = board.size();
 * 
 * score = new int[n][n];
 * 
 * ways = new int[n][n];
 * 
 * visited = new boolean[n][n];
 * 
 * int[] answer = dfs(
 * 0,
 * 0,
 * board
 * );
 * 
 * if (answer[1] == 0) {
 * 
 * return new int[]{0, 0};
 * }
 * 
 * return answer;
 * }
 * 
 * private int[] dfs(
 * int row,
 * int col,
 * List<String> board
 * ) {
 * 
 * int n = board.size();
 * 
 * if (
 * row >= n ||
 * col >= n ||
 * board.get(row).charAt(col) == 'X'
 * ) {
 * 
 * return new int[]{-1, 0};
 * }
 * 
 * if (
 * row == n - 1 &&
 * col == n - 1
 * ) {
 * 
 * return new int[]{0, 1};
 * }
 * 
 * if (visited[row][col]) {
 * 
 * return new int[]{
 * score[row][col],
 * ways[row][col]
 * };
 * }
 * 
 * visited[row][col] = true;
 * 
 * int bestScore = -1;
 * 
 * int totalWays = 0;
 * 
 * int[][] directions = {
 * {1, 0},
 * {0, 1},
 * {1, 1}
 * };
 * 
 * for (int[] direction : directions) {
 * 
 * int[] next =
 * dfs(
 * row + direction[0],
 * col + direction[1],
 * board
 * );
 * 
 * if (next[0] == -1) {
 * 
 * continue;
 * }
 * 
 * if (next[0] > bestScore) {
 * 
 * bestScore = next[0];
 * 
 * totalWays = next[1];
 * 
 * } else if (
 * next[0] == bestScore
 * ) {
 * 
 * totalWays =
 * (totalWays + next[1])
 * % MOD;
 * }
 * }
 * 
 * if (bestScore == -1) {
 * 
 * score[row][col] = -1;
 * 
 * ways[row][col] = 0;
 * 
 * return new int[]{-1, 0};
 * }
 * 
 * char current =
 * board.get(row).charAt(col);
 * 
 * if (
 * Character.isDigit(current)
 * ) {
 * 
 * bestScore +=
 * current - '0';
 * }
 * 
 * score[row][col] = bestScore;
 * 
 * ways[row][col] = totalWays;
 * 
 * return new int[]{
 * bestScore,
 * totalWays
 * };
 * }
 * }
 */