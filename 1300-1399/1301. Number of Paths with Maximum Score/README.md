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

# 1301. Number of Paths with Maximum Score

## Tags

```text
Dynamic Programming
Matrix
2D DP
Bottom-Up DP
Grid
Simulation
Java
C++
```

---

# Intuition

We start from

```text
S
```

and need to reach

```text
E
```

while

- collecting the **maximum possible score**
- counting **how many paths** achieve that maximum score.

Each move can only be

```text
↑

←

↖
```

when solving from

```text
Bottom → Top
```

(or equivalently Down, Right and Diagonal when thinking from the start).

Therefore,

for every cell,

we need to know two things

```text
Maximum Score

and

Number of Maximum Score Paths
```

This naturally leads to a

```text
2-D Dynamic Programming
```

solution.

---

# Key Observation

Every cell depends only on

```text
Bottom Cell

Right Cell

Bottom-Right Diagonal Cell
```

Choose the neighbour having

```text
Largest Score
```

If multiple neighbours have the same score,

```text
Sum Their Path Counts
```

Finally,

if the current cell contains a digit,

```text
Add It

to

Current Maximum Score
```

---

# Approaches

1. Bottom-Up Dynamic Programming (Optimal)

---

# Approach 1 — Bottom-Up DP

## Idea

Maintain two DP tables.

First DP Table

```text
Maximum Score
```

Second DP Table

```text
Number of Ways
```

Traverse the board

```text
Bottom Right

↓

Top Left
```

At every cell

- Ignore blocked cells
- Find the neighbour having the maximum score
- Merge path counts if multiple neighbours give the same score
- Add current digit to the score

---

# Algorithm

### Step 1

Create

```text
Score DP

Count DP
```

---

### Step 2

Initialize destination

```text
S

Score = 0

Ways = 1
```

---

### Step 3

Traverse

```text
Bottom Right

↓

Top Left
```

---

### Step 4

Ignore

```text
Obstacle (X)
```

---

### Step 5

Choose maximum among

```text
Down

Right

Diagonal
```

---

### Step 6

If equal scores exist

```text
Add Their Ways
```

---

### Step 7

If current cell contains

```text
Digit
```

Add it to

```text
Maximum Score
```

---

### Step 8

Answer becomes

```text
Score DP[0][0]

Count DP[0][0]
```

---

# Flowchart

```text
            Start

              │

              ▼

      Initialize DP Tables

              │

              ▼

     Traverse Bottom → Top

              │

              ▼

        Current Cell = X ?

        ┌────────┴────────┐

       Yes               No

        │                 │

        ▼                 ▼

     Skip Cell     Check 3 Neighbours

                          │

                          ▼

              Choose Maximum Score

                          │

                          ▼

           Multiple Maximum Scores ?

                 ┌────────┴────────┐

                Yes               No

                 │                 │

                 ▼                 ▼

          Add All Ways       Keep One Way

                 │

                 ▼

          Current Cell Digit ?

                 │

          ┌──────┴───────┐

         Yes            No

          │              │

          ▼              ▼

      Add Digit       Keep Score

              │

              ▼

         Continue DP

              │

              ▼

        Return Answer
```

---

# Example

Input

```text
["E23",
 "2X2",
 "12S"]
```

Board

```text
+---+---+---+
| E | 2 | 3 |
+---+---+---+
| 2 | X | 2 |
+---+---+---+
| 1 | 2 | S |
+---+---+---+
```

Valid Maximum Score Path

```text
S

↑

2

↖

3

←

E
```

Collected Score

```text
2 + 3

=

5
```

Number of Maximum Score Paths

```text
1
```

Answer

```text
[5,1]
```

---

# Dry Run

Destination

```text
S

Score = 0

Ways = 1
```

↓

Process

```text
2

↓

1

↓

2

↓

3

↓

E
```

DP chooses

```text
Maximum Score
```

Whenever two neighbours produce

```text
Same Score
```

their path counts are added.

Final DP

```text
Maximum Score = 7

Ways = 1
```

---

# DP Visualization

Maximum Score DP

```text
7 7 5

4 X 2

3 2 0
```

Ways DP

```text
1 1 1

1 0 1

1 1 1
```

---

# Memory Visualization

```text
Board

      │

      ▼

Score DP

      │

      ▼

Ways DP

      │

      ▼

Bottom-Up Traversal

      │

      ▼

Maximum Score

      │

      ▼

Number of Paths
```

---

# Why Dynamic Programming Works?

Every state depends only on

```text
Three Previously Computed States
```

Since we process

```text
Bottom Right

↓

Top Left
```

all required states are already computed.

Therefore,

every cell is solved exactly once.

---

# Complexity Analysis

## Approach 1 — Bottom-Up Dynamic Programming

### Time Complexity

```text
O(n²)
```

Each cell is processed once.

---

### Space Complexity

```text
O(n²)
```

Used for

- Score DP
- Path Count DP

---

# Java Solution

## Approach 1 — Bottom-Up Dynamic Programming (Optimal)

```java
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

                if (
                    row == size - 1 &&
                    col == size - 1
                ) {

                    continue;
                }

                int bestScore = -1;

                int ways = 0;

                if (
                    row + 1 < size &&
                    maxScore[row + 1][col] != -1
                ) {

                    if (
                        maxScore[row + 1][col] >
                        bestScore
                    ) {

                        bestScore =
                            maxScore[row + 1][col];

                        ways =
                            pathCount[row + 1][col];

                    } else if (
                        maxScore[row + 1][col] ==
                        bestScore
                    ) {

                        ways =
                            (ways +
                             pathCount[row + 1][col])
                             % mod;
                    }
                }

                if (
                    col + 1 < size &&
                    maxScore[row][col + 1] != -1
                ) {

                    if (
                        maxScore[row][col + 1] >
                        bestScore
                    ) {

                        bestScore =
                            maxScore[row][col + 1];

                        ways =
                            pathCount[row][col + 1];

                    } else if (
                        maxScore[row][col + 1] ==
                        bestScore
                    ) {

                        ways =
                            (ways +
                             pathCount[row][col + 1])
                             % mod;
                    }
                }

                if (
                    row + 1 < size &&
                    col + 1 < size &&
                    maxScore[row + 1][col + 1] != -1
                ) {

                    if (
                        maxScore[row + 1][col + 1] >
                        bestScore
                    ) {

                        bestScore =
                            maxScore[row + 1][col + 1];

                        ways =
                            pathCount[row + 1][col + 1];

                    } else if (
                        maxScore[row + 1][col + 1] ==
                        bestScore
                    ) {

                        ways =
                            (ways +
                             pathCount[row + 1][col + 1])
                             % mod;
                    }
                }

                if (bestScore == -1) {

                    continue;
                }

                char current =
                    board.get(row).charAt(col);

                if (
                    Character.isDigit(current)
                ) {

                    bestScore +=
                        current - '0';
                }

                maxScore[row][col] =
                    bestScore;

                pathCount[row][col] =
                    ways;
            }
        }

        if (pathCount[0][0] == 0) {

            return new int[]{0, 0};
        }

        return new int[]{
            maxScore[0][0],
            pathCount[0][0]
        };
    }
}
```

---

# C++ Solution

## Approach 1 — Bottom-Up Dynamic Programming (Optimal)

```cpp
//Approach-1 (Bottom-Up Dynamic Programming)
//T.C : O(n²)
//S.C : O(n²)

class Solution {
public:

    vector<int> pathsWithMaxScore(
        vector<string>& board
    ) {

        int n = board.size();

        const int MOD = 1000000007;

        vector<vector<int>> maxScore(
            n,
            vector<int>(n, -1)
        );

        vector<vector<int>> pathCount(
            n,
            vector<int>(n, 0)
        );

        maxScore[n - 1][n - 1] = 0;

        pathCount[n - 1][n - 1] = 1;

        for (int row = n - 1; row >= 0; row--) {

            for (int col = n - 1; col >= 0; col--) {

                if (board[row][col] == 'X') {

                    continue;
                }

                if (
                    row == n - 1 &&
                    col == n - 1
                ) {

                    continue;
                }

                int bestScore = -1;

                int ways = 0;

                vector<pair<int,int>> nextCells = {
                    {row + 1, col},
                    {row, col + 1},
                    {row + 1, col + 1}
                };

                for (auto &cell : nextCells) {

                    int r = cell.first;

                    int c = cell.second;

                    if (
                        r >= n ||
                        c >= n ||
                        maxScore[r][c] == -1
                    ) {

                        continue;
                    }

                    if (
                        maxScore[r][c] >
                        bestScore
                    ) {

                        bestScore =
                            maxScore[r][c];

                        ways =
                            pathCount[r][c];

                    } else if (
                        maxScore[r][c] ==
                        bestScore
                    ) {

                        ways =
                            (ways +
                             pathCount[r][c])
                             % MOD;
                    }
                }

                if (bestScore == -1) {

                    continue;
                }

                if (
                    isdigit(board[row][col])
                ) {

                    bestScore +=
                        board[row][col] - '0';
                }

                maxScore[row][col] =
                    bestScore;

                pathCount[row][col] =
                    ways;
            }
        }

        if (pathCount[0][0] == 0) {

            return {0, 0};
        }

        return {
            maxScore[0][0],
            pathCount[0][0]
        };
    }
};
```

---

# Complexity Comparison

| Approach | Algorithm | Time | Space |
|:---------|:----------|:----:|:-----:|
| Bottom-Up DP | 2D Dynamic Programming | **O(n²)** | **O(n²)** |

---

# Final Complexity

```text
Approach 1 (Bottom-Up Dynamic Programming)

Time Complexity  : O(n²)

Space Complexity : O(n²)
```

---

# Conclusion

- ✅ Use two DP tables: one for the **maximum score** and another for the **number of maximum-score paths**.
- ✅ Traverse the board from **bottom-right to top-left** so that all dependent states are already computed.
- ✅ At each cell, select the neighboring state with the highest score.
- ✅ If multiple neighbors provide the same maximum score, add their path counts modulo **10⁹ + 7**.
- ✅ This bottom-up DP computes the answer efficiently in **O(n²)** time.