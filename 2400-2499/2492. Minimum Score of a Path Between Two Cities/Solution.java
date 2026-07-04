//Approach-1 (Breadth-First Search)
//T.C : O(V + E)
//S.C : O(V + E)

import java.util.*;

class Solution {

  public int minScore(int n, int[][] roads) {

        List<int[]>[] adjacencyList = new ArrayList[n + 1];

        for (int city = 1; city <= n; city++) {

            adjacencyList[city] = new ArrayList<>();
        }

        for (int[] road : roads) {

            int source = road[0];

            int destination = road[1];

            int distance = road[2];

            adjacencyList[source].add(
                new int[]{
                    destination,
                    distance
                }
            );

            adjacencyList[destination].add(
                new int[]{
                    source,
                    distance
                }
            );
        }

        boolean[] visited = new boolean[n + 1];

        Queue<Integer> queue =
            new LinkedList<>();

        queue.offer(1);

        visited[1] = true;

        int answer = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {

            int currentCity = queue.poll();

            for (int[] neighbour : adjacencyList[currentCity]) {

                int nextCity = neighbour[0];

                int weight = neighbour[1];

                answer = Math.min(
                    answer,
                    weight
                );

                if (!visited[nextCity]) {

                    visited[nextCity] = true;

                    queue.offer(nextCity);
                }
            }
        }

        return answer;
    }
}```

---

##Approach 2—Depth-

First Search

```java
/*
 * //Approach-2 (Depth-First Search)
 * //T.C : O(V + E)
 * //S.C : O(V + E)
 * 
 * import java.util.*;
 * 
 * class Solution {
 * 
 * int answer = Integer.MAX_VALUE;
 * 
 * public int minScore(int n, int[][] roads) {
 * 
 * List<int[]>[] adjacencyList = new ArrayList[n + 1];
 * 
 * for (int city = 1; city <= n; city++) {
 * 
 * adjacencyList[city] = new ArrayList<>();
 * }
 * 
 * for (int[] road : roads) {
 * 
 * adjacencyList[road[0]].add(
 * new int[]{
 * road[1],
 * road[2]
 * }
 * );
 * 
 * adjacencyList[road[1]].add(
 * new int[]{
 * road[0],
 * road[2]
 * }
 * );
 * }
 * 
 * boolean[] visited = new boolean[n + 1];
 * 
 * dfs(
 * 1,
 * adjacencyList,
 * visited
 * );
 * 
 * return answer;
 * }
 * 
 * private void dfs(
 * int city,
 * List<int[]>[] adjacencyList,
 * boolean[] visited
 * ) {
 * 
 * visited[city] = true;
 * 
 * for (int[] neighbour : adjacencyList[city]) {
 * 
 * answer = Math.min(
 * answer,
 * neighbour[1]
 * );
 * 
 * if (!visited[neighbour[0]]) {
 * 
 * dfs(
 * neighbour[0],
 * adjacencyList,
 * visited
 * );
 * }
 * }
 * }
 * }
 */

/*
 * //Approach-3 (Disjoint Set Union / Union Find)
 * //T.C : O((V + E) α(V))
 * //S.C : O(V)
 * 
 * class Solution {
 * 
 * int[] parent;
 * 
 * public int minScore(int n, int[][] roads) {
 * 
 * parent = new int[n + 1];
 * 
 * for (int i = 1; i <= n; i++) {
 * 
 * parent[i] = i;
 * }
 * 
 * for (int[] road : roads) {
 * 
 * union(
 * road[0],
 * road[1]
 * );
 * }
 * 
 * int root = find(1);
 * 
 * int answer = Integer.MAX_VALUE;
 * 
 * for (int[] road : roads) {
 * 
 * if (
 * find(road[0]) == root
 * ) {
 * 
 * answer = Math.min(
 * answer,
 * road[2]
 * );
 * }
 * }
 * 
 * return answer;
 * }
 * 
 * private int find(int node) {
 * 
 * if (parent[node] != node) {
 * 
 * parent[node] =
 * find(parent[node]);
 * }
 * 
 * return parent[node];
 * }
 * 
 * private void union(
 * int u,
 * int v
 * ) {
 * 
 * int parentU = find(u);
 * 
 * int parentV = find(v);
 * 
 * if (parentU != parentV) {
 * 
 * parent[parentV] = parentU;
 * }
 * }
 * }
 */