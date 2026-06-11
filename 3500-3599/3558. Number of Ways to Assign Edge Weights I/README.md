# Profiles

## GitHub

⭐ GitHub Repository:

https://github.com/Shailendra0320

---

## LeetCode Profiles

🔥 Main Profile:

https://leetcode.com/u/ShailendraLeetcode03/

🚀 Alternate Profile:

https://leetcode.com/u/Shailu03/

---

# 3558. Number of Ways to Assign Edge Weights I

# Intuition

The tree is rooted at:

```text

Node 1

```

The answer depends only on:

```text

Maximum Depth of the Tree

```

Once the maximum depth is known:

```text

Answer = 2^(MaximumDepth - 1)

```

modulo:

```text

1e9 + 7

```

---

# Approach 1 — DFS Height Calculation

## Idea

Compute:

```text

Longest path from root to leaf

```

using DFS.

Then:

```text

Answer = 2^(depth-1)

```

---

# Tree Visualization

```text

            1

          /   \

         2     3

        /

       4

      /

     5

```

Depth:

```text

1 → 2 → 4 → 5



Depth = 3 edges

```

---

# Flowchart

```text

Build Graph



      │

      ▼



Start DFS From Root



      │

      ▼



Find Maximum Depth



      │

      ▼



Compute



2^(depth-1)



      │

      ▼



Return Answer

```

---

# DFS Traversal Diagram

```text

dfs(1)



 │

 ├── dfs(2)

 │      │

 │      └── dfs(4)

 │              │

 │              └── dfs(5)

 │

 └── dfs(3)

```

Maximum Depth:

```text

3

```

Answer:

```text

2^(3-1)

=

4

```

---

# Approach 2 — DFS + Binary Exponentiation

## Idea

Store depth of every node.

Find:

```text

Maximum depth

```

Then compute:

```text

2^(maxDepth-1)

```

using:

```text

Binary Exponentiation

```

---

# Memory Visualization

```text

depth[]



Node 1 → 0

Node 2 → 1

Node 3 → 1

Node 4 → 2

Node 5 → 3

```

Maximum:

```text

3

```

---

# Dry Run

Input

```text

edges =



[

 [1,2],

 [1,3],

 [2,4],

 [4,5]

]

```

Graph:

```text

1

├──2

│  └──4

│      └──5

└──3

```

Maximum Depth:

```text

3

```

Answer:

```text

2^(3-1)



= 4

```

---

# Complexity Analysis

## Approach 1

### Time Complexity

```text

O(n)

```

### Space Complexity

```text

O(n)

```

---

## Approach 2

### Time Complexity

```text

O(n + log(depth))

```

### Space Complexity

```text

O(n)

```

---

# Java Solution 1

```java

import java.util.*;



class Solution {



    static final int MOD = 1_000_000_007;



    public int assignEdgeWeights(int[][] edges) {



        int n = edges.length + 1;



        List<Integer>[] adj = new ArrayList[n + 1];



        for (int i = 0; i <= n; i++) {

            adj[i] = new ArrayList<>();

        }



        for (int[] edge : edges) {



            adj[edge[0]].add(edge[1]);

            adj[edge[1]].add(edge[0]);

        }



        int depth = dfs(adj, 1, -1);



        long answer = 1;



        for (int i = 1; i < depth; i++) {

            answer = (answer * 2) % MOD;

        }



        return (int) answer;

    }



    private int dfs(List<Integer>[] adj, int node, int parent) {



        int depth = 0;



        for (int child : adj[node]) {



            if (child != parent) {



                depth = Math.max(

                    depth,

                    1 + dfs(adj, child, node)

                );

            }

        }



        return depth;

    }

}

```

---

# Java Solution 2

```java

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

```

---

# C++ Solution 1

```cpp

class Solution {

public:



    static constexpr int MOD = 1000000007;



    int dfs(

        vector<vector<int>>& graph,

        int node,

        int parent

    ) {

        int depth = 0;



        for (int child : graph[node]) {



            if (child != parent) {



                depth = max(

                    depth,

                    1 + dfs(graph, child, node)

                );

            }

        }



        return depth;

    }



    int assignEdgeWeights(vector<vector<int>>& edges) {



        int n = edges.size() + 1;



        vector<vector<int>> graph(n + 1);



        for (auto& edge : edges) {



            int u = edge[0];

            int v = edge[1];



            graph[u].push_back(v);

            graph[v].push_back(u);

        }



        int depth = dfs(graph, 1, -1);



        long long answer = 1;



        for (int i = 1; i < depth; i++) {



            answer = (answer * 2) % MOD;

        }



        return (int)answer;

    }

};

```

---

# C++ Solution 2

```cpp

class Solution {

public:



    static constexpr int MOD = 1000000007;



    vector<int> depth;



    void dfs(

        int node,

        vector<vector<int>>& graph,

        int parent,

        int currentDepth

    ) {



        depth[node] = currentDepth;



        for (int child : graph[node]) {



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



    long long modPow(

        long long base,

        int exponent

    ) {



        long long result = 1;



        while (exponent > 0) {



            if (exponent & 1) {

                result = (result * base) % MOD;

            }



            base = (base * base) % MOD;



            exponent >>= 1;

        }



        return result;

    }



    int assignEdgeWeights(vector<vector<int>>& edges) {



        int n = edges.size() + 1;



        vector<vector<int>> graph(n + 1);



        depth.assign(n + 1, 0);



        for (auto& edge : edges) {



            int u = edge[0];

            int v = edge[1];



            graph[u].push_back(v);

            graph[v].push_back(u);

        }



        dfs(1, graph, -1, 0);



        int maxDepth = 0;



        for (int node = 1; node <= n; node++) {

            maxDepth = max(maxDepth, depth[node]);

        }



        return (int)modPow(2, maxDepth - 1);

    }

};

```
