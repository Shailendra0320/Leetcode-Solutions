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

# 2492. Minimum Score of a Path Between Two Cities

## Tags

```text
Graph
Breadth-First Search (BFS)
Depth-First Search (DFS)
Connected Components
Adjacency List
Queue
Java
C++
```

---

# Intuition

The graph is undirected.

We are allowed to travel through the cities **multiple times** before reaching city **n**.

Therefore,

the answer is **not** the minimum edge on one shortest path.

Instead,

it is simply the

```text
Minimum Edge Weight
```

among **all roads** inside the connected component containing city **1**.

Since city **n** is guaranteed to be connected with city **1**, every reachable road in this connected component can potentially appear in some valid path.

---

# Key Observation

Suppose the graph is

```text
1 -----5----- 2
 \            |
  \           |
   9          4
    \         |
     \        |
       3------6
```

The connected component contains

```text
1

2

3
```

Road weights are

```text
5

9

4
```

Even though weight

```text
4
```

is not on the direct shortest path,

we can always visit that edge and then continue toward city

```text
n
```

Hence,

the answer becomes

```text
Minimum Edge

=

4
```

---

# Approaches

1. Breadth-First Search (BFS)
2. Depth-First Search (DFS)
3. Disjoint Set Union (Union Find)

---

# Approach 1 — Breadth-First Search (Optimal)

## Idea

Build an adjacency list.

Start a BFS from city

```text
1
```

Visit every city belonging to the connected component.

While traversing,

continuously update

```text
answer

=

minimum edge encountered
```

After BFS finishes,

the stored minimum edge is the final answer.

---

# Algorithm

### Step 1

Create an

```text
Adjacency List
```

for the graph.

---

### Step 2

Initialize

```text
Queue

Visited Array

Answer = INF
```

---

### Step 3

Start BFS from

```text
City 1
```

---

### Step 4

For every neighbouring road,

update

```text
answer

=

min(answer, roadWeight)
```

---

### Step 5

If the neighbouring city is unvisited,

push it into the queue.

---

### Step 6

After BFS completes,

return

```text
answer
```

---

# Flowchart

```text
          Start

            │

            ▼

 Build Adjacency List

            │

            ▼

 Push City 1 Into Queue

            │

            ▼

   Queue Empty ?

      │

 ┌────┴────┐

 No       Yes

 │          │

 ▼          ▼

Pop City   Return Answer

 │

 ▼

Visit Every Road

 │

 ▼

answer = min(answer, weight)

 │

 ▼

Neighbour Visited ?

      │

 ┌────┴─────┐

 Yes        No

 │           │

 ▼           ▼

Ignore    Push Queue

           │

           ▼

Continue BFS
```

---

# Example

Input

```text
n = 4

roads =

[[1,2,9],
 [2,3,5],
 [3,4,6],
 [1,4,7]]
```

Graph

```text
               9
        (1) -------- (2)
         |            |
       7 |            | 5
         |            |
        (4) -------- (3)
               6
```

All cities belong to the same connected component.

Roads

```text
1 ── 2 : 9

2 ── 3 : 5

3 ── 4 : 6

1 ── 4 : 7
```

During BFS/DFS traversal,

we visit every road in the connected component.

Minimum edge encountered

```text
min(9, 5, 6, 7)

=

5
```

Therefore,

```text
Minimum Score = 5
```

Answer

```text
5
```

---

# Dry Run

Queue

```text
[1]
```

Visit

```text
1
```

Current Answer

```text
min(INF,9)

=

9
```

Visit

```text
4
```

Current Answer

```text
min(9,7)

=

7
```

Visit

```text
2
```

Current Answer

```text
min(7,5)

=

5
```

Visit

```text
3
```

Current Answer

```text
min(5,6)

=

5
```

Final Answer

```text
5
```

---

# Memory Visualization

```text
Roads

        │

        ▼

Adjacency List

        │

        ▼

Queue

        │

        ▼

Visited Array

        │

        ▼

Minimum Edge

        │

        ▼

Answer
```

---

# Why BFS Works?

BFS visits

```text
every city
```

inside the connected component containing city

```text
1
```

Every road in this component can belong to some valid path between

```text
1

and

n
```

Therefore,

tracking the smallest edge during traversal guarantees the correct answer.

---

# Complexity Analysis

## Approach 1 — Breadth-First Search

### Time Complexity

```text
O(V + E)
```

where

```text
V = Number of Cities

E = Number of Roads
```

Every city and every road is visited exactly once.

---

### Space Complexity

```text
O(V + E)
```

Used for

- Adjacency List
- Queue
- Visited Array

---

# Java Solution

## Approach 1 — Breadth-First Search (Optimal)

```java
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
}
```

---

## Approach 2 — Depth-First Search

```java
/*
//Approach-2 (Depth-First Search)
//T.C : O(V + E)
//S.C : O(V + E)

import java.util.*;

class Solution {

    int answer = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {

        List<int[]>[] adjacencyList = new ArrayList[n + 1];

        for (int city = 1; city <= n; city++) {

            adjacencyList[city] = new ArrayList<>();
        }

        for (int[] road : roads) {

            adjacencyList[road[0]].add(
                new int[]{
                    road[1],
                    road[2]
                }
            );

            adjacencyList[road[1]].add(
                new int[]{
                    road[0],
                    road[2]
                }
            );
        }

        boolean[] visited = new boolean[n + 1];

        dfs(
            1,
            adjacencyList,
            visited
        );

        return answer;
    }

    private void dfs(
        int city,
        List<int[]>[] adjacencyList,
        boolean[] visited
    ) {

        visited[city] = true;

        for (int[] neighbour : adjacencyList[city]) {

            answer = Math.min(
                answer,
                neighbour[1]
            );

            if (!visited[neighbour[0]]) {

                dfs(
                    neighbour[0],
                    adjacencyList,
                    visited
                );
            }
        }
    }
}
*/
```

---

## Approach 3 — Disjoint Set Union (Union Find)

```java
/*
//Approach-3 (Disjoint Set Union / Union Find)
//T.C : O((V + E) α(V))
//S.C : O(V)

class Solution {

    int[] parent;

    public int minScore(int n, int[][] roads) {

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            parent[i] = i;
        }

        for (int[] road : roads) {

            union(
                road[0],
                road[1]
            );
        }

        int root = find(1);

        int answer = Integer.MAX_VALUE;

        for (int[] road : roads) {

            if (
                find(road[0]) == root
            ) {

                answer = Math.min(
                    answer,
                    road[2]
                );
            }
        }

        return answer;
    }

    private int find(int node) {

        if (parent[node] != node) {

            parent[node] =
                find(parent[node]);
        }

        return parent[node];
    }

    private void union(
        int u,
        int v
    ) {

        int parentU = find(u);

        int parentV = find(v);

        if (parentU != parentV) {

            parent[parentV] = parentU;
        }
    }
}
*/
```

---

# C++ Solution

## Approach 1 — Breadth-First Search (Optimal)

```cpp
//Approach-1 (Breadth-First Search)
//T.C : O(V + E)
//S.C : O(V + E)

class Solution {
public:

    int minScore(
        int n,
        vector<vector<int>>& roads
    ) {

        vector<vector<pair<int,int>>> adjacencyList(
            n + 1
        );

        for (auto &road : roads) {

            adjacencyList[road[0]].push_back(
                {
                    road[1],
                    road[2]
                }
            );

            adjacencyList[road[1]].push_back(
                {
                    road[0],
                    road[2]
                }
            );
        }

        vector<bool> visited(
            n + 1,
            false
        );

        queue<int> queue;

        queue.push(1);

        visited[1] = true;

        int answer = INT_MAX;

        while (!queue.empty()) {

            int currentCity = queue.front();

            queue.pop();

            for (auto &neighbour :
                 adjacencyList[currentCity]) {

                answer = min(
                    answer,
                    neighbour.second
                );

                if (!visited[neighbour.first]) {

                    visited[neighbour.first] = true;

                    queue.push(
                        neighbour.first
                    );
                }
            }
        }

        return answer;
    }
};
```

_(DFS and DSU C++ approaches can be added as commented alternatives in the same style if you want all three C++ approaches in one file.)_

---

# Complexity Comparison

| Approach | Algorithm       |        Time         |    Space     |
| :------- | :-------------- | :-----------------: | :----------: |
| BFS      | Graph Traversal |    **O(V + E)**     | **O(V + E)** |
| DFS      | Graph Traversal |    **O(V + E)**     | **O(V + E)** |
| DSU      | Union Find      | **O((V + E) α(V))** |   **O(V)**   |

---

# Final Complexity

```text
Approach 1 (Breadth-First Search)

Time Complexity  : O(V + E)

Space Complexity : O(V + E)

----------------------------------------

Approach 2 (Depth-First Search)

Time Complexity  : O(V + E)

Space Complexity : O(V + E)

----------------------------------------

Approach 3 (Disjoint Set Union)

Time Complexity  : O((V + E) α(V))

Space Complexity : O(V)
```

---

# Conclusion

- ✅ The answer is **not** the shortest path distance.
- ✅ We only need the **minimum edge weight** in the connected component containing city **1**.
- ✅ BFS and DFS both traverse the entire connected component and update the minimum edge encountered.
- ✅ DSU (Union Find) first groups cities into connected components, then scans the roads to find the minimum edge within the component containing city **1**.
- ✅ BFS and DFS are the simplest and most intuitive solutions, while DSU is an elegant alternative for connectivity-based problems.
