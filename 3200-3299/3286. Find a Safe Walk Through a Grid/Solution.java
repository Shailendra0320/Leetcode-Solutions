import java.util.*;

class Solution {

  int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

  public boolean findSafeWalk(List<List<Integer>> grid, int health) {

    int m = grid.size();
    int n = grid.get(0).size();

    int[][] cost = new int[m][n];

    for (int[] row : cost) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }

    Deque<int[]> deque = new ArrayDeque<>();

    int startCost = grid.get(0).get(0);

    cost[0][0] = startCost;

    deque.offerFirst(new int[] { 0, 0, startCost });

    while (!deque.isEmpty()) {

      int[] current = deque.pollFirst();

      int row = current[0];
      int col = current[1];
      int healthUsed = current[2];

      if (healthUsed > cost[row][col]) {
        continue;
      }

      for (int[] dir : dirs) {

        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (nextRow >= 0 && nextRow < m &&
            nextCol >= 0 && nextCol < n) {

          int cellCost = grid.get(nextRow).get(nextCol);

          int newCost = healthUsed + cellCost;

          if (newCost < cost[nextRow][nextCol]) {

            cost[nextRow][nextCol] = newCost;

            if (cellCost == 0) {
              deque.offerFirst(new int[] { nextRow, nextCol, newCost });
            } else {
              deque.offerLast(new int[] { nextRow, nextCol, newCost });
            }
          }
        }
      }
    }

    return cost[m - 1][n - 1] < health;
  }
}

/*
 * Approach 2 — Dijkstra (Priority Queue)
 * 
 * Time Complexity: O(m × n log(m × n))
 * 
 * Space Complexity: O(m × n)
 * 
 * import java.util.*;
 * 
 * class Solution {
 * 
 * int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
 * 
 * public boolean findSafeWalk(List<List<Integer>> grid, int health) {
 * 
 * int m = grid.size();
 * int n = grid.get(0).size();
 * 
 * int[][] cost = new int[m][n];
 * 
 * for (int[] row : cost) {
 * Arrays.fill(row, Integer.MAX_VALUE);
 * }
 * 
 * PriorityQueue<int[]> pq =
 * new PriorityQueue<>((a,b) -> a[2] - b[2]);
 * 
 * int startCost = grid.get(0).get(0);
 * 
 * cost[0][0] = startCost;
 * 
 * pq.offer(new int[]{0,0,startCost});
 * 
 * while (!pq.isEmpty()) {
 * 
 * int[] current = pq.poll();
 * 
 * int row = current[0];
 * int col = current[1];
 * int healthUsed = current[2];
 * 
 * if (healthUsed > cost[row][col]) {
 * continue;
 * }
 * 
 * if (row == m - 1 && col == n - 1) {
 * return healthUsed < health;
 * }
 * 
 * for (int[] dir : dirs) {
 * 
 * int nextRow = row + dir[0];
 * int nextCol = col + dir[1];
 * 
 * if (nextRow >= 0 && nextRow < m &&
 * nextCol >= 0 && nextCol < n) {
 * 
 * int newCost =
 * healthUsed + grid.get(nextRow).get(nextCol);
 * 
 * if (newCost < cost[nextRow][nextCol]) {
 * 
 * cost[nextRow][nextCol] = newCost;
 * 
 * pq.offer(new int[]{
 * nextRow,
 * nextCol,
 * newCost
 * });
 * }
 * }
 * }
 * }
 * 
 * return false;
 * }
 * }
 * 
 */

/*
 * BFS using Deque (Optimal) — O(m × n)
 * Dijkstra using Priority Queue — O(m × n log(m × n))
 */