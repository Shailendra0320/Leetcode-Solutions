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

# 3286. Find a Safe Walk Through a Grid

## Tags

```text
Graph
Grid
Matrix
0-1 BFS
Breadth-First Search (BFS)
Deque
Shortest Path
Graph Traversal
Java
C++
```

---

# Intuition

Each cell in the grid represents the amount of **health lost** when entering it.

```text
0 → Safe Cell

1 → Unsafe Cell
```

We start from the top-left corner and want to reach the bottom-right corner.

Instead of minimizing the number of moves,

our objective is to

```text
Minimize Total Health Lost
```

If the minimum health consumed is **strictly smaller** than the available health,

then reaching the destination is possible.

---

# Key Observation

Moving into

```text
Safe Cell
```

costs

```text
0
```

Moving into

```text
Unsafe Cell
```

costs

```text
1
```

Therefore every edge in the graph has only two possible weights

```text
0

or

1
```

Whenever a graph contains only

```text
0

and

1
```

edge weights,

the optimal shortest path algorithm becomes

```text
0-1 BFS
```

instead of Dijkstra.

---

# Why 0-1 BFS?

Normal BFS only works when every edge has equal weight.

Dijkstra works for arbitrary positive weights.

Here,

every edge weight is either

```text
0

or

1
```

Therefore

```text
0-1 BFS
```

computes the shortest path in

```text
O(V + E)
```

which is much faster than Dijkstra.

---

# Approaches

1. 0-1 BFS using Deque (Optimal)
2. Dijkstra using Priority Queue

---

# Approach 1 — 0-1 BFS

## Idea

Maintain

```text
Minimum Health Lost
```

to reach every cell.

Whenever we move

```text
Safe Cell

Cost = 0
```

push it to the

```text
Front
```

of the deque.

Whenever we move

```text
Unsafe Cell

Cost = 1
```

push it to the

```text
Back
```

of the deque.

This automatically processes smaller-cost paths first.

---

# Algorithm

Step 1

```text
Initialize

cost[][] = INF
```

---

Step 2

```text
Start from

(0,0)
```

Initial Cost

```text
grid[0][0]
```

---

Step 3

For every neighbour

```text
newCost = currentCost + cellValue
```

---

Step 4

If

```text
cellValue == 0
```

Insert into

```text
Front

of Deque
```

Else

```text
Back

of Deque
```

---

Step 5

Continue until deque becomes empty.

Answer becomes

```text
Minimum Health Lost
```

---

# Flowchart

```text
           Start

             │

             ▼

Initialize Cost Matrix

             │

             ▼

 Push Source Cell

             │

             ▼

 Pop Front Cell

             │

             ▼

 Visit Neighbours

             │

             ▼

Compute New Cost

             │

             ▼

Better Path ?

      │

 ┌────┴────┐

YES        NO

 │          │

 ▼          ▼

Update    Ignore

 │

 ▼

Cell Cost ?

 │

 ┌──────┴──────┐

0             1

 │             │

 ▼             ▼

Push Front   Push Back

 │

 ▼

Deque Empty ?

 │

 ▼

Return
```

---

# Visualization

Grid

```text
0 1 0

0 0 1

1 0 0
```

Health Cost

```text
0

↓

1

↓

1

↓

1

↓

1
```

Minimum Health Lost

```text
1
```

---

# Example

Input

```text
grid =

0 1 1

0 0 1

1 0 0

health = 2
```

Output

```text
true
```

Reason

Minimum health consumed

```text
1
```

Since

```text
1 < 2
```

the walk is safe.

---

# Dry Run

Initial Cost Matrix

```text
0 INF INF

INF INF INF

INF INF INF
```

Deque

```text
[(0,0)]
```

↓

Visit

```text
(0,1)

Cost = 1

Push Back
```

Visit

```text
(1,0)

Cost = 0

Push Front
```

Deque

```text
[(1,0),(0,1)]
```

Continue

↓

Eventually

```text
Destination Cost

=

1
```

Answer

```text
true
```

---

# Memory Visualization

```text
Grid

        │

        ▼

Cost Matrix

        │

        ▼

Deque

        │

        ▼

0-1 BFS

        │

        ▼

Minimum Cost

        │

        ▼

Compare With Health

        │

        ▼

Return Answer
```

---

# Why Does 0-1 BFS Work?

Whenever

```text
Edge Weight = 0
```

that path is always better than processing an edge having

```text
Weight = 1
```

Therefore

```text
Push Front
```

ensures smaller-cost paths are explored first.

Similarly,

```text
Weight = 1
```

is delayed by

```text
Push Back
```

Thus the deque always behaves like a priority queue for

```text
0

and

1
```

weights.

---

# Complexity Analysis

## Approach 1 — 0-1 BFS

### Time Complexity

```text
O(m × n)
```

Every cell enters the deque at most a few times.

Each edge is processed once.

---

### Space Complexity

```text
O(m × n)
```

Used for

- Cost Matrix
- Deque
- Grid Traversal

---

# Java Solution

```java
//Approach-1 (0-1 BFS using Deque)
//T.C : O(m × n)
//S.C : O(m × n)

import java.util.*;

class Solution {

    int[][] directions = {
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int rows = grid.size();

        int cols = grid.get(0).size();

        int[][] cost = new int[rows][cols];

        for (int[] row : cost) {

            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Deque<int[]> deque = new ArrayDeque<>();

        int startCost = grid.get(0).get(0);

        cost[0][0] = startCost;

        deque.offerFirst(
            new int[]{
                0,
                0,
                startCost
            }
        );

        while (!deque.isEmpty()) {

            int[] current = deque.pollFirst();

            int row = current[0];

            int col = current[1];

            int healthUsed = current[2];

            if (healthUsed > cost[row][col]) {

                continue;
            }

            for (int[] direction : directions) {

                int nextRow = row + direction[0];

                int nextCol = col + direction[1];

                if (
                    nextRow >= 0 &&
                    nextRow < rows &&
                    nextCol >= 0 &&
                    nextCol < cols
                ) {

                    int cellCost =
                        grid.get(nextRow).get(nextCol);

                    int newCost =
                        healthUsed + cellCost;

                    if (
                        newCost < cost[nextRow][nextCol]
                    ) {

                        cost[nextRow][nextCol] =
                            newCost;

                        if (cellCost == 0) {

                            deque.offerFirst(
                                new int[]{
                                    nextRow,
                                    nextCol,
                                    newCost
                                }
                            );

                        } else {

                            deque.offerLast(
                                new int[]{
                                    nextRow,
                                    nextCol,
                                    newCost
                                }
                            );
                        }
                    }
                }
            }
        }

        return cost[rows-1][cols-1] < health;
    }
}
```

---

# C++ Solution

```cpp
//Approach-1 (0-1 BFS using Deque)
//T.C : O(m × n)
//S.C : O(m × n)

class Solution {
public:

    vector<vector<int>> directions{
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };

    bool findSafeWalk(
        vector<vector<int>>& grid,
        int health
    ) {

        int rows = grid.size();

        int cols = grid[0].size();

        vector<vector<int>> cost(
            rows,
            vector<int>(
                cols,
                INT_MAX
            )
        );

        deque<vector<int>> dequeQueue;

        int startCost = grid[0][0];

        cost[0][0] = startCost;

        dequeQueue.push_front(
            {
                0,
                0,
                startCost
            }
        );

        while (!dequeQueue.empty()) {

            auto current =
                dequeQueue.front();

            dequeQueue.pop_front();

            int row = current[0];

            int col = current[1];

            int healthUsed = current[2];

            if (healthUsed > cost[row][col]) {

                continue;
            }

            for (auto &direction : directions) {

                int nextRow =
                    row + direction[0];

                int nextCol =
                    col + direction[1];

                if (
                    nextRow >= 0 &&
                    nextRow < rows &&
                    nextCol >= 0 &&
                    nextCol < cols
                ) {

                    int cellCost =
                        grid[nextRow][nextCol];

                    int newCost =
                        healthUsed + cellCost;

                    if (
                        newCost <
                        cost[nextRow][nextCol]
                    ) {

                        cost[nextRow][nextCol] =
                            newCost;

                        if (cellCost == 0) {

                            dequeQueue.push_front(
                                {
                                    nextRow,
                                    nextCol,
                                    newCost
                                }
                            );

                        } else {

                            dequeQueue.push_back(
                                {
                                    nextRow,
                                    nextCol,
                                    newCost
                                }
                            );
                        }
                    }
                }
            }
        }

        return cost[rows-1][cols-1] < health;
    }
};
```

---

# Approach Comparison

| Approach | Algorithm | Time | Space |
|:---------|:----------|:----:|:-----:|
| 0-1 BFS | Deque Based Shortest Path | **O(m × n)** | **O(m × n)** |
| Dijkstra | Priority Queue | **O(m × n log(m × n))** | **O(m × n)** |

---

# Final Complexity

```text
Time Complexity  : O(m × n)

Space Complexity : O(m × n)
```

---

# Why 0-1 BFS is Better than Dijkstra?

Since every move has only two possible costs

```text
0

or

1
```

a deque can always process the smallest-cost state first.

Therefore,

```text
0-1 BFS
```

achieves

```text
O(V + E)
```

instead of

```text
O(E log V)
```

required by Dijkstra.

---

# Conclusion

- ✅ Treat every cell as a graph node.
- ✅ Safe cells have edge weight **0**.
- ✅ Unsafe cells have edge weight **1**.
- ✅ Multi-step shortest path becomes a **0-1 BFS** problem.
- ✅ The deque guarantees optimal processing order without using a priority queue.
- ✅ Finally, compare the minimum health consumed with the available health to determine whether a safe path exists.