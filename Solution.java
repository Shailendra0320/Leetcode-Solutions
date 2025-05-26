class Solution {
  public int largestPathValue(String colors, int[][] edges) {
    int n = colors.length();
    List<Integer>[] graph = new ArrayList[n];
    int[] indegree = new int[n];

    for (int i = 0; i < n; i++)
      graph[i] = new ArrayList<>();

    for (int[] edge : edges) {
      graph[edge[0]].add(edge[1]);
      indegree[edge[1]]++;
    }

    int[][] dp = new int[n][26];
    Queue<Integer> queue = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }

    int result = 0, visited = 0;

    while (!queue.isEmpty()) {
      int node = queue.poll();
      visited++;

      int colorIndex = colors.charAt(node) - 'a';
      dp[node][colorIndex]++;
      result = Math.max(result, dp[node][colorIndex]);

      for (int neighbor : graph[node]) {
        for (int c = 0; c < 26; c++) {
          dp[neighbor][c] = Math.max(dp[neighbor][c], dp[node][c]);
        }

        indegree[neighbor]--;
        if (indegree[neighbor] == 0) {
          queue.offer(neighbor);
        }
      }
    }

    return visited == n ? result : -1;
  }
}

// 2nd Solution

/*
 * 
 * 
 * class Solution {
 * public int largestPathValue(String colors, int[][] edges) {
 * int n = colors.length();
 * List<Integer>[] g = new List[n];
 * Arrays.setAll(g, k -> new ArrayList<>());
 * int[] indeg = new int[n];
 * for (int[] e : edges) {
 * int a = e[0], b = e[1];
 * g[a].add(b);
 * ++indeg[b];
 * }
 * Deque<Integer> q = new ArrayDeque<>();
 * int[][] dp = new int[n][26];
 * for (int i = 0; i < n; ++i) {
 * if (indeg[i] == 0) {
 * q.offer(i);
 * int c = colors.charAt(i) - 'a';
 * ++dp[i][c];
 * }
 * }
 * int cnt = 0;
 * int ans = 1;
 * while (!q.isEmpty()) {
 * int i = q.pollFirst();
 * ++cnt;
 * for (int j : g[i]) {
 * if (--indeg[j] == 0) {
 * q.offer(j);
 * }
 * int c = colors.charAt(j) - 'a';
 * for (int k = 0; k < 26; ++k) {
 * dp[j][k] = Math.max(dp[j][k], dp[i][k] + (c == k ? 1 : 0));
 * ans = Math.max(ans, dp[j][k]);
 * }
 * }
 * }
 * return cnt == n ? ans : -1;
 * }
 * }
 */