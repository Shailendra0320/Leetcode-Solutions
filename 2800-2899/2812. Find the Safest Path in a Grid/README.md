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

# 2812. Find the Safest Path in a Grid

## Tags

```text
Graph
Breadth-First Search (BFS)
Multi-Source BFS
Binary Search
Matrix
Shortest Path
Grid
Java
C++
```

---

# Intuition

Every cell in the grid has a **safeness factor**, which is defined as the minimum distance from that cell to any thief.

Our objective is **not** to minimize distance or find the shortest path.

Instead, we need to maximize the **minimum safeness factor** among all cells in the chosen path.

Therefore, the solution can be divided into two independent tasks:

1. Compute the safeness factor of every cell.
2. Find the maximum safeness value for which a path still exists.

---

# Key Observation

Suppose the grid is

```text
0 0 1

0 0 0

1 0 0
```

The thieves are located at

```text
(0,2)

(2,0)
```

Every cell stores its distance from the nearest thief.

The distance matrix becomes

```text
2 1 0

1 2 1

0 1 2
```

Now instead of walking on the original grid,

we walk on this distance matrix.

Our goal becomes

```text
Find a path

whose minimum value

is as large as possible.
```

---

# Approaches

1. Multi-Source BFS + Binary Search + BFS
2. Multi-Source BFS + Priority Queue (Dijkstra Variant)

---

# Approach 1 — Multi-Source BFS + Binary Search

## Step 1

Run a **Multi-Source BFS** starting from every thief.

This computes

```text
distance to nearest thief
```

for every cell.

---

### Multi-Source BFS Visualization

Original Grid

```text
0 0 1

0 0 0

1 0 0
```

Start BFS simultaneously from

```text
★

★

(thieves)
```

Distance Matrix

```text
2 1 0

1 2 1

0 1 2
```

---

## Step 2

Suppose we guess

```text
Safeness = k
```

Now,

ignore every cell having

```text
distance < k
```

Then simply check whether

```text
(0,0)

↓

(n−1,n−1)
```

is still reachable.

If reachable,

increase

```text
k
```

Otherwise,

decrease

```text
k
```

This naturally suggests

```text
Binary Search
```

---

# Binary Search Flow

```text
low = 0

high = maximum possible distance

          │

          ▼

Take Mid

          │

          ▼

Can Reach ?

          │

   ┌──────┴──────┐

  YES           NO

   │             │

   ▼             ▼

Increase      Decrease

Answer          High

          │

          ▼

Repeat
```

---

# Reachability Check

For every binary-search value

```text
k
```

perform a normal BFS.

Allowed Cells

```text
distance >= k
```

Blocked Cells

```text
distance < k
```

If destination is reachable,

the safeness factor is valid.

---

# BFS Traversal

```text
Start

(0,0)

     │

     ▼

Visit Neighbours

     │

     ▼

Distance >= k ?

 ┌─────────────┐

 │             │

YES           NO

 │             │

 ▼             ▼

Push        Ignore

Queue

 │

 ▼

Continue

 │

 ▼

Reach Destination ?

 │

 ▼

Return True / False
```

---

# Example

Input

```text
0 0 1

0 0 0

1 0 0
```

Distance Matrix

```text
2 1 0

1 2 1

0 1 2
```

Binary Search

Try

```text
k = 2
```

Allowed

```text
2

2

2
```

No path.

Try

```text
k = 1
```

Allowed

```text
2 1

1 2 1

1 2
```

Path exists.

Answer

```text
1
```

---

# Memory Visualization

Original Grid

```text
0 0 1

0 0 0

1 0 0
```

↓

Multi-Source BFS

↓

Distance Matrix

```text
2 1 0

1 2 1

0 1 2
```

↓

Binary Search

```text
k = 0

↓

k = 1

↓

k = 2
```

↓

BFS Validation

↓

Maximum Valid Safeness

```text
Answer
```

---

# Why Binary Search Works?

Suppose

```text
k = 3
```

is possible.

Then

```text
0

1

2
```

are also possible.

If

```text
k = 3
```

is impossible,

then

```text
4

5

6
```

are also impossible.

Therefore,

the answer space is

```text
Monotonic
```

which makes

```text
Binary Search
```

the perfect choice.

---

# Complexity Analysis

## Approach 1 — Multi-Source BFS + Binary Search

### Time Complexity

```text
O(n² log n)
```

Reason

- Multi-source BFS

```text
O(n²)
```

- Binary Search

```text
O(log n)
```

- BFS for every binary-search step

```text
O(n²)
```

Overall

```text
O(n² log n)
```

### Space Complexity

```text
O(n²)
```

for

- Distance Matrix
- Visited Matrix
- BFS Queue

---

# Java Solution

```java
//Approach-1 (Multi-Source BFS + Binary Search + BFS)
//T.C : O(n² log n)
//S.C : O(n²)

import java.util.*;

class Solution {

    int[][] directions = {
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        int[][] distance = new int[n][n];

        for (int[] row : distance) {

            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new LinkedList<>();

        for (int row = 0; row < n; row++) {

            for (int col = 0; col < n; col++) {

                if (grid.get(row).get(col) == 1) {

                    distance[row][col] = 0;

                    queue.offer(
                        new int[]{
                            row,
                            col
                        }
                    );
                }
            }
        }

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            for (int[] dir : directions) {

                int nextRow = current[0] + dir[0];

                int nextCol = current[1] + dir[1];

                if (
                    nextRow >= 0 &&
                    nextRow < n &&
                    nextCol >= 0 &&
                    nextCol < n &&
                    distance[nextRow][nextCol] == -1
                ) {

                    distance[nextRow][nextCol] =
                        distance[current[0]][current[1]] + 1;

                    queue.offer(
                        new int[]{
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

            if (canReach(distance, n, middle)) {

                answer = middle;

                low = middle + 1;

            } else {

                high = middle - 1;
            }
        }

        return answer;
    }

    private boolean canReach(
        int[][] distance,
        int n,
        int limit
    ) {

        if (
            distance[0][0] < limit ||
            distance[n - 1][n - 1] < limit
        ) {

            return false;
        }

        boolean[][] visited =
            new boolean[n][n];

        Queue<int[]> queue =
            new LinkedList<>();

        queue.offer(
            new int[]{0,0}
        );

        visited[0][0] = true;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            if (
                current[0] == n - 1 &&
                current[1] == n - 1
            ) {

                return true;
            }

            for (int[] dir : directions) {

                int nextRow =
                    current[0] + dir[0];

                int nextCol =
                    current[1] + dir[1];

                if (
                    nextRow >= 0 &&
                    nextRow < n &&
                    nextCol >= 0 &&
                    nextCol < n &&
                    !visited[nextRow][nextCol] &&
                    distance[nextRow][nextCol] >= limit
                ) {

                    visited[nextRow][nextCol] =
                        true;

                    queue.offer(
                        new int[]{
                            nextRow,
                            nextCol
                        }
                    );
                }
            }
        }

        return false;
    }
}
```

---

# C++ Solution

```cpp
//Approach-1 (Multi-Source BFS + Binary Search + BFS)
//T.C : O(n² log n)
//S.C : O(n²)

class Solution {
public:

    vector<vector<int>> directions{
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };

    bool canReach(
        vector<vector<int>>& distance,
        int n,
        int limit
    ){

        if(
            distance[0][0] < limit ||
            distance[n-1][n-1] < limit
        ){

            return false;
        }

        queue<pair<int,int>> queue;

        vector<vector<bool>> visited(
            n,
            vector<bool>(n,false)
        );

        queue.push({0,0});

        visited[0][0] = true;

        while(!queue.empty()){

            auto current = queue.front();

            queue.pop();

            int row = current.first;

            int col = current.second;

            if(
                row == n-1 &&
                col == n-1
            ){

                return true;
            }

            for(auto &dir : directions){

                int nextRow =
                    row + dir[0];

                int nextCol =
                    col + dir[1];

                if(
                    nextRow >= 0 &&
                    nextRow < n &&
                    nextCol >= 0 &&
                    nextCol < n &&
                    !visited[nextRow][nextCol] &&
                    distance[nextRow][nextCol] >= limit
                ){

                    visited[nextRow][nextCol] =
                        true;

                    queue.push(
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
            vector<int>(n,-1)
        );

        queue<pair<int,int>> queue;

        for(int row=0;row<n;row++){

            for(int col=0;col<n;col++){

                if(grid[row][col]==1){

                    distance[row][col]=0;

                    queue.push({row,col});
                }
            }
        }

        while(!queue.empty()){

            auto current=queue.front();

            queue.pop();

            for(auto &dir:directions){

                int nextRow=current.first+dir[0];

                int nextCol=current.second+dir[1];

                if(
                    nextRow>=0 &&
                    nextRow<n &&
                    nextCol>=0 &&
                    nextCol<n &&
                    distance[nextRow][nextCol]==-1
                ){

                    distance[nextRow][nextCol]=
                    distance[current.first][current.second]+1;

                    queue.push(
                        {
                            nextRow,
                            nextCol
                        }
                    );
                }
            }
        }

        if(
            distance[0][0]==0 ||
            distance[n-1][n-1]==0
        ){

            return 0;
        }

        int low=0;

        int high=2*n;

        int answer=0;

        while(low<=high){

            int middle=(low+high)/2;

            if(
                canReach(
                    distance,
                    n,
                    middle
                )
            ){

                answer=middle;

                low=middle+1;

            }else{

                high=middle-1;
            }
        }

        return answer;
    }
};
```

---

# Approach Comparison

| Approach | Algorithm | Time | Space |
|:---------|:----------|:----:|:-----:|
| Multi-Source BFS + Binary Search | Compute distance, then binary search the answer | **O(n² log n)** | **O(n²)** |
| Multi-Source BFS + Max Heap (Dijkstra Variant) | Greedy best-first traversal | **O(n² log n)** | **O(n²)** |

---

# Final Complexity

```text
Time Complexity  : O(n² log n)

Space Complexity : O(n²)
```

---

# Conclusion

- ✅ Multi-Source BFS efficiently computes the minimum distance from every cell to the nearest thief.
- ✅ Binary Search exploits the monotonic nature of the safeness factor.
- ✅ BFS validates whether a path exists for the chosen safeness.
- ✅ Combining these techniques yields an efficient solution for large grids.