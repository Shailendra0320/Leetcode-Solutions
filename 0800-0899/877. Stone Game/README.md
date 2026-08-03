# Stone Game

## 🔗 Problem Link

**LeetCode 877:** https://leetcode.com/problems/stone-game/

---

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode:** https://leetcode.com/u/Shailu03/

**LeetCode (Alternative):** https://leetcode.com/Shailu03/

---

# 📌 Problem Statement

Alice and Bob play a game with piles of stones.

- There are an **even** number of piles arranged in a row.
- Each pile has a **positive** integer number of stones `piles[i]`.
- The **total** number of stones is **odd**, so there are **no ties**.

Alice and Bob take turns, with **Alice starting first**.

On each turn, a player takes the **entire pile** of stones from either the
**beginning** or the **end** of the row.

This continues until no piles remain. The player with the **most stones wins**.

Assuming both play optimally, return `true` if Alice wins the game, or `false` if Bob wins.

---

# 💡 Intuition

This is a classic two-player optimal game on a line of piles.

Each move shrinks the remaining segment from one of the two ends.

At first glance, it looks like we must simulate every possible sequence of moves.

But there is a deeper observation.

Because the number of piles is **even**, Alice always picks exactly `n / 2` piles,
and Bob also picks exactly `n / 2` piles.

More importantly, Alice can always force a strategy where she takes:

- **all even-indexed piles**, or
- **all odd-indexed piles**

whichever group has the larger sum.

Since the total number of stones is odd, these two group sums cannot be equal.
Therefore one of them is strictly larger, and Alice can always choose that group.

Hence **Alice always wins**.

---

# 🎨 Game Board Diagram

```text
Initial Board

piles = [5, 3, 4, 5]

        LEFT END                         RIGHT END
           ▼                                 ▼
        ┌─────┬─────┬─────┬─────┐
        │  5  │  3  │  4  │  5  │
        └─────┴─────┴─────┴─────┘
          i=0   i=1   i=2   i=3

On every turn, a player MUST pick one of the two ends only.
```

---

# 🔍 Key Observation

Index the piles as:

```text
0, 1, 2, 3, ..., n-1
```

Think of two alternating patterns:

```text
Even indices: piles[0], piles[2], piles[4], ...
Odd indices : piles[1], piles[3], piles[5], ...
```

Alice goes first. Whatever Bob does, Alice can always respond in such a way that
she collects one complete pattern (all even or all odd indices).

---

# 🎨 Parity Strategy Diagram

```text
piles = [5, 3, 4, 5]

Index:     0     1     2     3
         ┌─────┬─────┬─────┬─────┐
         │  5  │  3  │  4  │  5  │
         └─────┴─────┴─────┴─────┘
            ▲           ▲
            │           │
         EVEN        EVEN
         group       group
         ------------+------------
              SUM = 5 + 4 = 9

                 ▲           ▲
                 │           │
              ODD         ODD
              group       group
              ------------+------------
                   SUM = 3 + 5 = 8

Alice compares:

        Even Sum = 9
        Odd  Sum = 8

        9 > 8  →  Alice chooses EVEN group
               →  Alice score = 9
               →  Bob   score = 8
               →  Alice WINS
```

---

# 🎨 Why Alice Can Always Force a Pattern

```text
Turn Order with 4 piles:

  Turn 1 → Alice
  Turn 2 → Bob
  Turn 3 → Alice
  Turn 4 → Bob

Alice wants EVEN indices {0, 2}

Case A: Alice starts by taking LEFT (index 0)
        Remaining: [3, 4, 5]
        Bob must take 3 or 5
          - If Bob takes 3 → remaining [4, 5] → Alice takes 4 (index 2)
          - If Bob takes 5 → remaining [3, 4] → Alice takes 4 (index 2)
        Alice got indices 0 and 2 ✓

Case B: Alice starts by taking RIGHT (index 3) for ODD strategy
        She can similarly force {1, 3}

Whatever Bob chooses, Alice can always answer from the correct end
to stay on her chosen parity group.
```

---

# 🚀 Approach 1 — Mathematical Insight (Optimal)

The solution is extremely simple once the observation is clear.

1. Realize Alice can always pick the better of the even-index / odd-index groups.
2. Realize the total is odd, so no tie is possible.
3. Therefore Alice always wins.
4. Return `true`.

No simulation, no DP, no extra memory needed.

---

# 🚀 Approach 2 — Dynamic Programming (Interval DP)

If we want a general game-theory solution (useful for similar problems), we use DP.

Define:

```text
dp[i][j] = maximum stone difference
           (current player − opponent)
           for subarray piles[i..j]
```

### Recurrence

The current player has two choices:

```text
Take left  → piles[i] − dp[i+1][j]
Take right → piles[j] − dp[i][j−1]
```

So:

```text
dp[i][j] = max(
    piles[i] − dp[i+1][j],
    piles[j] − dp[i][j−1]
)
```

### Base Case

```text
dp[i][i] = piles[i]
```

When only one pile remains, the current player takes it.

### Final Answer

Alice starts on the full array `piles[0..n−1]`.

```text
Alice wins  ⇔  dp[0][n−1] > 0
```

---

# 📝 Algorithm — Approach 1 (Mathematical)

### Step 1

Observe that Alice can always force the better parity group.

### Step 2

Observe that total stones is odd → no ties.

### Step 3

Return `true`.

---

# 📝 Algorithm — Approach 2 (DP)

### Step 1

Create a 2D DP table of size `n × n`.

### Step 2

Initialize diagonal:

```text
dp[i][i] = piles[i]
```

### Step 3

For every subarray length from `2` to `n`:

```text
for len = 2 to n:
    for i = 0 to n − len:
        j = i + len − 1
        dp[i][j] = max(
            piles[i] − dp[i+1][j],
            piles[j] − dp[i][j−1]
        )
```

### Step 4

Return:

```text
dp[0][n−1] > 0
```

---

# 🌳 Flowchart — Approach 1

```text
                  Start
                    │
                    ▼
         Observe even number of piles
                    │
                    ▼
     Alice can choose even or odd index group
                    │
                    ▼
          Total stones is odd
                    │
                    ▼
           One group is strictly larger
                    │
                    ▼
              Alice always wins
                    │
                    ▼
               Return true
                    │
                    ▼
                   End
```

---

# 🌳 Flowchart — Approach 2

```text
                  Start
                    │
                    ▼
             Read Input Array
                    │
                    ▼
          Create dp[n][n] table
                    │
                    ▼
      Initialize dp[i][i] = piles[i]
                    │
                    ▼
     Fill DP for increasing lengths
                    │
                    ▼
   dp[i][j] = max(take left, take right)
                    │
                    ▼
        Check dp[0][n−1] > 0 ?
               /         \
             Yes          No
              │            │
              ▼            ▼
        Return true   Return false
              │            │
              └──────┬─────┘
                     ▼
                    End
```

---

# 🎨 Recurrence Diagram

```text
Current range piles[i .. j]

                    piles[i .. j]
                    /          \
                   /            \
          Take LEFT            Take RIGHT
          piles[i]             piles[j]
               |                    |
               v                    v
     opponent plays          opponent plays
     on piles[i+1..j]        on piles[i..j-1]
               |                    |
               v                    v
   piles[i] - dp[i+1][j]    piles[j] - dp[i][j-1]
               \                    /
                \                  /
                 \                /
                  \              /
                   \            /
                    \          /
                     \        /
                      \      /
                       \    /
                        \  /
                         \/
                take the MAXIMUM
                         |
                         v
                      dp[i][j]
```

---

# 📖 Example 1

```text
Input

piles = [5,3,4,5]
```

Even-index sum:

```text
5 + 4 = 9
```

Odd-index sum:

```text
3 + 5 = 8
```

Alice chooses even indices → score `9`.

Bob gets `8`.

Alice wins → `true`

---

# 📖 Example 2

```text
Input

piles = [3,7,2,3]
```

Even-index sum:

```text
3 + 2 = 5
```

Odd-index sum:

```text
7 + 3 = 10
```

Alice chooses odd indices → score `10`.

Bob gets `5`.

Alice wins → `true`

---

# 🔄 Dry Run — Turn By Turn

### Input

```text
piles = [5, 3, 4, 5]
```

Alice decides to take EVEN group (sum 9).

```text
Step 0 — Start
┌─────┬─────┬─────┬─────┐
│  5  │  3  │  4  │  5  │
└─────┴─────┴─────┴─────┘
Alice score = 0
Bob   score = 0

Step 1 — Alice takes LEFT 5 (index 0)
┌─────┬─────┬─────┐
│  3  │  4  │  5  │
└─────┴─────┴─────┘
Alice = 5
Bob   = 0

Step 2 — Bob takes LEFT 3
┌─────┬─────┐
│  4  │  5  │
└─────┴─────┘
Alice = 5
Bob   = 3

Step 3 — Alice takes LEFT 4 (index 2 of original)
┌─────┐
│  5  │
└─────┘
Alice = 9
Bob   = 3

Step 4 — Bob takes last 5
(empty)
Alice = 9
Bob   = 8

Result: 9 > 8 → Alice wins → true
```

---

# 🔄 Dry Run — Approach 2 (DP Table Fill)

### Input

```text
piles = [5, 3, 4, 5]
n = 4
```

### Step 1 — Base Case (length 1)

```text
        j→  0    1    2    3
      i↓
      0   [ 5 ] [   ] [   ] [   ]
      1   [   ] [ 3 ] [   ] [   ]
      2   [   ] [   ] [ 4 ] [   ]
      3   [   ] [   ] [   ] [ 5 ]
```

### Step 2 — Length 2

```text
dp[0][1] = max(5-3, 3-5) = 2
dp[1][2] = max(3-4, 4-3) = 1
dp[2][3] = max(4-5, 5-4) = 1

        j→  0    1    2    3
      i↓
      0   [ 5 ] [ 2 ] [   ] [   ]
      1   [   ] [ 3 ] [ 1 ] [   ]
      2   [   ] [   ] [ 4 ] [ 1 ]
      3   [   ] [   ] [   ] [ 5 ]
```

### Step 3 — Length 3

```text
dp[0][2] = max(5-1, 4-2) = 4
dp[1][3] = max(3-1, 5-1) = 4

        j→  0    1    2    3
      i↓
      0   [ 5 ] [ 2 ] [ 4 ] [   ]
      1   [   ] [ 3 ] [ 1 ] [ 4 ]
      2   [   ] [   ] [ 4 ] [ 1 ]
      3   [   ] [   ] [   ] [ 5 ]
```

### Step 4 — Length 4

```text
dp[0][3] = max(5-4, 5-4) = 1

        j→  0    1    2    3
      i↓
      0   [ 5 ] [ 2 ] [ 4 ] [ 1 ]  ← answer
      1   [   ] [ 3 ] [ 1 ] [ 4 ]
      2   [   ] [   ] [ 4 ] [ 1 ]
      3   [   ] [   ] [   ] [ 5 ]
```

### Result

```text
dp[0][3] = 1 > 0 → Alice wins → true
```

---

# 🧠 Why Approach 1 Works

Alice and Bob each take exactly `n / 2` piles.

The piles Alice can force herself into are always one of the two alternating patterns:

```text
Even indices OR Odd indices
```

She simply picks the pattern with the larger sum.

Because the total is odd, the two pattern sums are unequal.
Therefore Alice's sum is always strictly greater than Bob's sum.

No matter how Bob plays, Alice has a winning strategy.

---

# 🧠 Why Approach 2 Works

`dp[i][j]` stores the best score difference the current player can force.

Taking a pile gives `+piles[x]`, but then the opponent plays optimally on the remaining range, which subtracts their best difference.

By always choosing the maximum of the two ends, every subproblem is solved optimally.

The full-array value `dp[0][n−1]` tells us Alice's best difference over Bob.
If it is positive, Alice wins.

---

# Java Solution

## Approach 1 — Mathematical Insight (Optimal)

```java
//Approach-1 (Mathematical Insight - Alice Always Wins)
//T.C : O(1)
//S.C : O(1)

class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}
```

---

## Approach 2 — Dynamic Programming (Interval DP)

```java
//Approach-2 (Dynamic Programming - Interval DP)
//T.C : O(n^2)
//S.C : O(n^2)

class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(
                    piles[i] - dp[i + 1][j],
                    piles[j] - dp[i][j - 1]
                );
            }
        }

        return dp[0][n - 1] > 0;
    }
}
```

---

# C++ Solution

## Approach 1 — Mathematical Insight (Optimal)

```cpp
//Approach-1 (Mathematical Insight - Alice Always Wins)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:
    bool stoneGame(vector<int>& piles) {
        return true;
    }
};
```

---

## Approach 2 — Dynamic Programming (Interval DP)

```cpp
//Approach-2 (Dynamic Programming - Interval DP)
//T.C : O(n^2)
//S.C : O(n^2)

class Solution {
public:
    bool stoneGame(vector<int>& piles) {
        int n = piles.size();
        vector<vector<int>> dp(n, vector<int>(n, 0));

        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = max(
                    piles[i] - dp[i + 1][j],
                    piles[j] - dp[i][j - 1]
                );
            }
        }

        return dp[0][n - 1] > 0;
    }
};
```

---

# 📊 Memory Visualization

```text
Example

piles = [5, 3, 4, 5]

Indices:   0   1   2   3
         ┌───┬───┬───┬───┐
         │ 5 │ 3 │ 4 │ 5 │
         └───┴───┴───┴───┘
           ▲       ▲
           │       │
        Even     Even
           sum = 9

               ▲       ▲
               │       │
            Odd      Odd
               sum = 8

Alice picks Even group (9) > Odd group (8)
Alice wins
```

---

# 🎨 Decision Tree Diagram (Example)

```text
                    [5, 3, 4, 5]
                    Alice's turn
                   /            \
                  /              \
           take 5 L            take 5 R
                |                  |
           [3, 4, 5]            [5, 3, 4]
           Bob's turn           Bob's turn
           /        \           /        \
      take 3      take 5   take 5      take 4
         |           |        |           |
      [4, 5]      [3, 4]   [3, 4]      [5, 3]
      Alice       Alice    Alice       Alice
      /    \      /    \   /    \      /    \
    4      5    3      4  3      4    5      3

In every optimal branch, Alice can finish with more stones.
Final optimal difference dp[0][3] = 1 > 0 → true
```

---

# ⚠️ Edge Cases

## Case 1 — Minimum Size (2 piles)

```text
Input

[1, 2]

┌─────┬─────┐
│  1  │  2  │
└─────┴─────┘
Even sum = 1
Odd  sum = 2

Alice takes the larger group → wins → true
```

---

## Case 2 — Equal Looking Ends

```text
Input

[5, 3, 4, 5]

Both ends are 5, but strategy still exists.
Alice can force even-index sum 9 > 8 → true
```

---

## Case 3 — Larger Odd Group

```text
Input

[3, 7, 2, 3]

┌─────┬─────┬─────┬─────┐
│  3  │  7  │  2  │  3  │
└─────┴─────┴─────┴─────┘
Odd sum = 10 > Even sum = 5
Alice forces odd indices → true
```

---

## Case 4 — Already Favorable First Move

```text
Input

[1, 100, 1, 100]

Odd sum = 200 > Even sum = 2
Alice still wins by choosing odd pattern → true
```

---

# 📈 Complexity Analysis

### Approach 1 — Mathematical

| Operation               | Complexity |
| :---------------------- | :--------: |
| Decision / Return       |  **O(1)**  |
| Overall Time Complexity |  **O(1)**  |
| Extra Space             |  **O(1)**  |

### Approach 2 — DP

| Operation                 |   Complexity   |
| :------------------------ | :------------: |
| Filling DP table          |   **O(n²)**    |
| Computing final answer    |    **O(1)**    |
| Overall Time Complexity   |   **O(n²)**    |
| Extra Space               |   **O(n²)**    |

---

# 📊 Complexity Comparison

| Approach              | Idea                                      |   Time   |  Space   |
| :-------------------- | :---------------------------------------- | :------: | :------: |
| Brute Force Recursion | Try all move sequences                    | **O(2ⁿ)** | **O(n)** |
| Interval DP           | Optimal score difference on subarrays     | **O(n²)** | **O(n²)** |
| Mathematical (Best)   | Alice always wins by parity group choice  | **O(1)**  | **O(1)** |

---

# 🎯 Why These Approaches Work

### Mathematical

Alice can always secure the better of the two alternating pile groups.
Total stones being odd guarantees a strict win.

### DP

Every subarray game is solved optimally using score difference.
A positive full-array difference means Alice wins under optimal play.

For this specific problem constraints, the mathematical proof is enough.
The DP approach is the reusable pattern for similar stone / game problems.

---

# ✅ Conclusion

- ✅ Alice and Bob always take an equal number of piles.
- ✅ Alice can force all even-indexed or all odd-indexed piles.
- ✅ Because total stones is odd, Alice always has a winning group.
- ✅ Mathematical solution: simply return `true` in **O(1)** time.
- ✅ DP solution also correctly computes the winner in **O(n²)** time.
- ✅ Prefer the mathematical solution for this problem; keep DP for interviews / similar variants.
