// import java.util.*;

// class Solution {

//     static final int MOD = 1_000_000_007;

//     public int assignEdgeWeights(int[][] edges) {

//         int n = edges.length + 1;

//         List<Integer>[] adj = new ArrayList[n + 1];

//         for (int i = 0; i <= n; i++) {

//             adj[i] = new ArrayList<>();

//         }

//         for (int[] edge : edges) {

//             adj[edge[0]].add(edge[1]);

//             adj[edge[1]].add(edge[0]);

//         }

//         int depth = dfs(adj, 1, -1);

//         long answer = 1;

//         for (int i = 1; i < depth; i++) {

//             answer = (answer * 2) % MOD;

//         }

//         return (int) answer;

//     }

//     private int dfs(List<Integer>[] adj, int node, int parent) {

//         int depth = 0;

//         for (int child : adj[node]) {

//             if (child != parent) {

//                 depth = Math.max(

//                     depth,

//                     1 + dfs(adj, child, node)

//                 );

//             }

//         }

//         return depth;

//     }

// }

import java.util.*;

class Solution {

  static final int MOD = 1_000_000_007;

  int[] depth;

  public void dfs(

      int node,

      List<List<Integer>> graph,

      int parent,

      int currentDepth

  ) {

    depth[node] = currentDepth;

    for (int child : graph.get(node)) {

      if (child != parent) {

        dfs(

            child,

            graph,

            node,

            currentDepth + 1

        );

      }

    }

  }

  private int modPow(long base, int exponent) {

    long result = 1;

    while (exponent > 0) {

      if ((exponent & 1) == 1) {

        result = (result * base) % MOD;

      }

      base = (base * base) % MOD;

      exponent >>= 1;

    }

    return (int) result;

  }

  public int assignEdgeWeights(int[][] edges) {

    int n = edges.length + 1;

    depth = new int[n + 1];

    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i <= n; i++) {

      graph.add(new ArrayList<>());

    }

    for (int[] edge : edges) {

      int u = edge[0];

      int v = edge[1];

      graph.get(u).add(v);

      graph.get(v).add(u);

    }

    dfs(1, graph, -1, 0);

    int maxDepth = 0;

    for (int node = 1; node <= n; node++) {

      maxDepth = Math.max(maxDepth, depth[node]);

    }

    return modPow(2, maxDepth - 1);

  }

}
