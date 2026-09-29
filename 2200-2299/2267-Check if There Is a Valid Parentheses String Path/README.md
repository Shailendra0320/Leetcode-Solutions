# 2267. Check if There Is a Valid Parentheses String Path

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

---

# 🧩 Problem Statement

You are given an `m x n` grid containing only `'('` and `')'`.

A valid path:

- starts at `(0, 0)`;
- ends at `(m - 1, n - 1)`;
- moves only **right** or **down**;
- forms a valid parentheses string when the visited characters are concatenated.

Return `true` if at least one valid path exists, otherwise return `false`.

The official constraints are `1 <= m,n <= 100`, and every grid cell is either `'('` or `')'`. The path length is always `m + n - 1`.  
Source: https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/

---

# 🎯 What Is the Problem Really Asking?

A normal grid problem asks:

```text
Can I reach the destination?
```

This problem asks something harder:

```text
Can I reach the destination
AND
make the characters along the path a valid parentheses string?
```

So every path creates a string such as:

```text
(()())
```

For a parentheses string to be valid, two conditions must hold:

```text
1. Prefix balance can never be negative.
2. Final balance must be exactly 0.
```

Use the standard balance interpretation:

```text
'(' → +1
')' → -1
```

Therefore, while moving through the grid, we only need to remember:

```text
current row
current column
current balance
```

That gives the central DP state:

```text
dp[row][column][balance]
```

---

# 💡 Core Insight

The complete path contains exactly:

```text
L = m + n - 1
```

cells.

A valid parentheses sequence must contain equal numbers of opening and closing parentheses, so:

```text
L must be even
```

This immediately eliminates many impossible cases.

We also know:

```text
start cell must be '('
end cell must be ')'
```

After these checks, the problem is a balance-state grid DP.

---

# 🔢 Balance Model

Suppose a path produces:

```text
( ( ) ( ) )
```

Its balance is:

```text
( → 1
( → 2
) → 1
( → 2
) → 1
) → 0
```

A path is valid only if:

```text
balance >= 0 at every step
```

and:

```text
balance == 0 at the destination
```

This lets us discard invalid path prefixes immediately.

---

# 🚀 Approach 1 — 3D Dynamic Programming

## 💡 Idea

Define:

```text
dp[i][j][b]
```

as:

> `true` if there is a path from `(0,0)` to `(i,j)` whose current parentheses balance is `b`.

At each cell, the path can come only from:

```text
up   → (i-1, j)
left → (i, j-1)
```

Then the current cell changes the balance:

```text
'(' → +1
')' → -1
```

So we transfer reachable balance states from the top or left.

---

## 🏗️ Approach 1 — Architecture Diagram

```text
                         GRID
                           │
                           ▼
                ┌─────────────────────┐
                │ Early checks        │
                │                     │
                │ path length even?   │
                │ start == '(' ?      │
                │ end == ')' ?        │
                └──────────┬──────────┘
                           │
                           ▼
                 ┌───────────────────┐
                 │ dp[0][0][1] = true│
                 └─────────┬─────────┘
                           │
                           ▼
                    Process cell
                           │
                           ▼
                ┌────────────────────┐
                │ Parent from UP or  │
                │ LEFT?              │
                └─────────┬──────────┘
                           │
                           ▼
                ┌────────────────────┐
                │ Current char       │
                │ '(' → b + 1        │
                │ ')' → b - 1        │
                └─────────┬──────────┘
                           │
                           ▼
                ┌────────────────────┐
                │ New balance < 0 ?  │
                └───────┬──────┬─────┘
                        Yes     No
                         │       │
                         ▼       ▼
                      discard  store state
                                  │
                                  ▼
                           next cell
                                  │
                                  ▼
                      destination reached
                                  │
                                  ▼
                       balance == 0 ?
                         /        \
                       Yes        No
                        │          │
                        ▼          ▼
                      true       false
```

---

## 🔄 Approach 1 — Data Flow

```text
Grid Cell
   │
   ▼
Current balance states
   │
   ├── from UP
   │
   └── from LEFT
         │
         ▼
Apply current character
         │
    ┌────┴────┐
    │         │
   '('       ')'
    │         │
    ▼         ▼
  b + 1     b - 1
    │         │
    └────┬────┘
         ▼
   balance >= 0 ?
         │
         ▼
   store reachable state
         │
         ▼
 destination + balance 0
```

---

## 🧠 Why We Need `balance` in the State

A plain grid DP:

```text
dp[i][j]
```

is not enough.

Two different paths can reach the same cell with different balances:

```text
Path A → balance = 0
Path B → balance = 2
```

Those states are not equivalent.

If the next character is:

```text
')'
```

then:

```text
balance 0 → -1   ❌
balance 2 → 1    ✅
```

Therefore the current balance changes what future moves are possible.

The DP must remember it.

---

## 🧪 Approach 1 — Dry Run

Consider the grid:

```text
[
    ['(', '('],
    [')', ')']
]
```

The path length is:

```text
2 + 2 - 1 = 3
```

Since `3` is odd, a valid parentheses string is impossible.

```text
return false
```

Now consider:

```text
[
    ['(', '('],
    [')', ')'],
    ['(', ')']
]
```

Path length:

```text
3 + 2 - 1 = 4
```

Possible path:

```text
( → ( → ) → )
```

Balance:

```text
1 → 2 → 1 → 0
```

So this path is valid.

The DP stores the balance after every step rather than storing the whole path.

---

## ✂️ Safe Pruning in Approach 1

Suppose after reaching a cell:

```text
balance = 5
```

and there are only:

```text
3 cells remaining
```

even if every remaining character is `')'`, the final balance can be at best:

```text
5 - 3 = 2
```

So the state can never reach zero.

Therefore we can safely keep only states satisfying:

```text
balance <= remainingCells
```

This reduces unnecessary states while preserving correctness.

---

## ✅ Approach 1 — Correctness Proof

We maintain the invariant:

> `dp[i][j][b]` is true exactly when there exists a path from the start to `(i,j)` with balance `b` and no prefix with negative balance.

### Base Case

The first cell must be `'('`.

Therefore:

```text
dp[0][0][1] = true
```

### Transition

Every path into `(i,j)` comes from either:

```text
(i-1,j)
```

or:

```text
(i,j-1)
```

The current cell changes the balance by `+1` or `-1`.

If the new balance becomes negative, the path prefix is invalid and must be discarded.

Otherwise, the new state is reachable.

### Final State

A valid parentheses string must finish with balance zero.

Therefore:

```text
dp[m-1][n-1][0]
```

is true exactly when a valid parentheses path exists.

---

## 💻 Approach 1 — Java

```java
//Approach-1 (3D Dynamic Programming)
//T.C : O(m * n * (m + n))
//S.C : O(m * n * (m + n))

class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int len = m + n - 1;

        if ((len & 1) == 1) {
            return false;
        }

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        boolean[][][] dp = new boolean[m][n][len + 1];
        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int delta = grid[i][j] == '(' ? 1 : -1;
                int remaining = len - (i + j + 1);

                for (int balance = 0; balance <= len; balance++) {
                    int prevBalance = balance - delta;

                    if (prevBalance < 0 || prevBalance > len) {
                        continue;
                    }

                    boolean reachable = false;

                    if (i > 0 && dp[i - 1][j][prevBalance]) {
                        reachable = true;
                    }

                    if (j > 0 && dp[i][j - 1][prevBalance]) {
                        reachable = true;
                    }

                    if (reachable && balance <= remaining) {
                        dp[i][j][balance] = true;
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}
```

---

## 💻 Approach 1 — C++

```cpp
//Approach-1 (3D Dynamic Programming)
//T.C : O(m * n * (m + n))
//S.C : O(m * n * (m + n))

class Solution {
public:
    bool hasValidPath(vector<vector<char>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int len = m + n - 1;

        if (len % 2 == 1) {
            return false;
        }

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        vector<vector<vector<bool>>> dp(
            m,
            vector<vector<bool>>(n, vector<bool>(len + 1, false))
        );

        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int delta = grid[i][j] == '(' ? 1 : -1;
                int remaining = len - (i + j + 1);

                for (int balance = 0; balance <= len; balance++) {
                    int prevBalance = balance - delta;

                    if (prevBalance < 0 || prevBalance > len) {
                        continue;
                    }

                    bool reachable = false;

                    if (i > 0 && dp[i - 1][j][prevBalance]) {
                        reachable = true;
                    }

                    if (j > 0 && dp[i][j - 1][prevBalance]) {
                        reachable = true;
                    }

                    if (reachable && balance <= remaining) {
                        dp[i][j][balance] = true;
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
};
```

---

## ⏱️ Approach 1 — Complexity

The balance can range up to:

```text
m + n - 1
```

Therefore the state count is:

```text
m × n × (m+n)
```

So:

```text
Time Complexity  : O(m × n × (m+n))
Space Complexity : O(m × n × (m+n))
```

---

# ⚡ Approach 2 — Rolling-Row DP with Balance Compression

## 💡 Main Idea

Approach 1 stores every row of the 3D DP.

But a cell only depends on:

```text
UP   → previous row
LEFT → current row
```

So older rows are unnecessary.

We keep only:

```text
prev[column][balance]
cur[column][balance]
```

This changes the space complexity from:

```text
O(m × n × (m+n))
```

to:

```text
O(n × (m+n))
```

while keeping the same time complexity.

---

## 🏗️ Approach 2 — Architecture Diagram

```text
                         GRID
                           │
                           ▼
                ┌─────────────────────┐
                │ Early checks        │
                │ parity / endpoints  │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │ Previous row DP     │
                │ prev[column][bal]   │
                └──────────┬──────────┘
                           │
                           ▼
                    Current cell
                           │
                  ┌────────┴────────┐
                  │                 │
                  ▼                 ▼
                FROM UP          FROM LEFT
                prev[j]          cur[j-1]
                  │                 │
                  └────────┬────────┘
                           ▼
                Apply '(' / ')' delta
                           │
                           ▼
                  ┌─────────────────┐
                  │ balance < 0 ?   │
                  └───────┬─────┬───┘
                          Yes    No
                           │      │
                           ▼      ▼
                        discard  store
                                   │
                                   ▼
                              next column
                                   │
                                   ▼
                              next row
                                   │
                                   ▼
                           prev = cur
                                   │
                                   ▼
                          destination [0]
```

---

## 🔄 Approach 2 — Data Flow

```text
Previous Row States ───────┐
                           │
Current Row Left States ───┤
                           ▼
                    Reachable balance
                           │
                           ▼
                   Apply current cell
                           │
                           ▼
                    New balance
                           │
                  ┌────────┴────────┐
                  │                 │
             invalid             valid
                  │                 │
               discard          store in cur
                                    │
                                    ▼
                               next cell
```

---

## 🧠 Why Rolling Rows Are Enough

For cell `(i,j)`:

```text
possible predecessors:

(i-1,j)   → previous row
(i,j-1)   → current row
```

There is no transition from:

```text
(i-2,j)
(i-3,j)
...
```

directly into `(i,j)`.

Therefore, once row `i` is finished, older rows can be discarded.

This is a standard **grid-DP space optimization**.

---

## ✂️ Approach 2 — Additional Safe Pruning

At a cell, suppose:

```text
balance = b
remaining = number of cells left
```

Every future `')'` can reduce the balance by only `1`.

Therefore if:

```text
b > remaining
```

the final balance can never become zero.

So we keep the state only when:

```text
0 <= b <= remaining
```

This reduces the number of useless balance states without changing the answer.

---

## 🧪 Approach 2 — Dry Run

Consider the same small valid path:

```text
(
(
)
)
```

At each cell, the DP carries only the possible balance values.

After the first cell:

```text
balance = {1}
```

After the second `'('`:

```text
balance = {2}
```

After the first `')'`:

```text
balance = {1}
```

After the final `')'`:

```text
balance = {0}
```

Since the destination contains balance `0`, a valid path exists.

The important part is that the DP stores **possible balances**, not complete path strings.

---

## ✅ Approach 2 — Correctness Proof

The rolling DP stores the same logical states as Approach 1.

For every cell, a state can come only from:

```text
UP
or
LEFT
```

The current character then changes the balance by `+1` or `-1`.

Negative balances are rejected because they represent invalid parentheses prefixes.

The pruning `balance <= remaining` only removes states that can never reach zero.

Thus the rolling DP preserves exactly all states that could lead to a valid path.

At the destination, balance zero is equivalent to a valid final parentheses string.

Therefore the rolling DP is correct.

---

## 💻 Approach 2 — Java

```java
//Approach-2 (Rolling-Row DP with Balance Compression)
//T.C : O(m * n * (m + n))
//S.C : O(n * (m + n))

class Solution2 {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int len = m + n - 1;

        if ((len & 1) == 1) {
            return false;
        }

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        boolean[][] prev = new boolean[n][len + 1];
        prev[0][1] = true;

        for (int i = 0; i < m; i++) {
            boolean[][] cur = new boolean[n][len + 1];

            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    cur[0][1] = true;
                    continue;
                }

                int delta = grid[i][j] == '(' ? 1 : -1;
                int remaining = len - (i + j + 1);

                for (int oldBalance = 0; oldBalance <= len; oldBalance++) {
                    boolean reachable = false;

                    if (i > 0 && prev[j][oldBalance]) {
                        reachable = true;
                    }

                    if (j > 0 && cur[j - 1][oldBalance]) {
                        reachable = true;
                    }

                    if (!reachable) {
                        continue;
                    }

                    int newBalance = oldBalance + delta;

                    if (newBalance >= 0 && newBalance <= remaining) {
                        cur[j][newBalance] = true;
                    }
                }
            }

            prev = cur;
        }

        return prev[n - 1][0];
    }
}
```

---

## 💻 Approach 2 — C++

```cpp
//Approach-2 (Rolling-Row DP with Balance Compression)
//T.C : O(m * n * (m + n))
//S.C : O(n * (m + n))

class Solution2 {
public:
    bool hasValidPath(vector<vector<char>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int len = m + n - 1;

        if (len % 2 == 1) {
            return false;
        }

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        vector<vector<bool>> prev(
            n,
            vector<bool>(len + 1, false)
        );

        prev[0][1] = true;

        for (int i = 0; i < m; i++) {
            vector<vector<bool>> cur(
                n,
                vector<bool>(len + 1, false)
            );

            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    cur[0][1] = true;
                    continue;
                }

                int delta = grid[i][j] == '(' ? 1 : -1;
                int remaining = len - (i + j + 1);

                for (int oldBalance = 0; oldBalance <= len; oldBalance++) {
                    bool reachable = false;

                    if (i > 0 && prev[j][oldBalance]) {
                        reachable = true;
                    }

                    if (j > 0 && cur[j - 1][oldBalance]) {
                        reachable = true;
                    }

                    if (!reachable) {
                        continue;
                    }

                    int newBalance = oldBalance + delta;

                    if (newBalance >= 0 && newBalance <= remaining) {
                        cur[j][newBalance] = true;
                    }
                }
            }

            prev = move(cur);
        }

        return prev[n - 1][0];
    }
};
```

---

## ⏱️ Approach 2 — Complexity

The balance dimension contains `O(m+n)` possible values.

Every cell can examine those states:

```text
Time Complexity  : O(m × n × (m+n))
Space Complexity : O(n × (m+n))
```

Compared with Approach 1, the time remains the same while the stored rows are reduced to two.

---

# 🆚 Approach 1 vs Approach 2

| Feature             | Approach 1             | Approach 2        |
| ------------------- | ---------------------- | ----------------- |
| Technique           | Full 3D DP             | Rolling-row DP    |
| State               | `row, column, balance` | `column, balance` |
| Time                | `O(mn(m+n))`           | `O(mn(m+n))`      |
| Space               | `O(mn(m+n))`           | `O(n(m+n))`       |
| Keeps all rows      | Yes                    | No                |
| Memory optimized    | No                     | Yes               |
| Easier to visualize | Yes                    | Yes               |
| Best for memory     |                        | ✅                |

---

# 🧠 Why We Cannot Use Ordinary DFS Alone

A brute-force DFS would explore many possible paths.

For an `m x n` grid, the number of down/right paths is:

```text
C(m+n-2, m-1)
```

which grows very quickly.

DP avoids recomputing equivalent states.

Many different paths can reach the same:

```text
(row, column, balance)
```

Once that state is known to be reachable, we do not need to remember how every individual path reached it.

That is the major optimization.

---

# 🔬 State Compression Insight

The entire path history can be huge:

```text
( ( ) ( ) ( ) ...
```

But the future only depends on:

```text
current position
+
current balance
```

Therefore:

```text
Full Path History
       ↓
Compress
       ↓
(row, column, balance)
```

This is a classic dynamic-programming state-compression pattern.

---

# 🧮 Valid Parentheses Conditions

A path produces a valid parentheses string exactly when:

### Condition 1 — No negative prefix

```text
balance >= 0
```

at every step.

### Condition 2 — Balanced at the end

```text
balance == 0
```

at the destination.

This is why the DP rejects negative states and checks destination balance zero.

---

# ⚠️ Common Mistakes

## 1. Using only `dp[i][j]`

That answers whether a cell is reachable, but not what balance the path has.

You need the balance dimension.

---

## 2. Allowing negative balance

A prefix such as:

```text
)(
```

is immediately invalid because the balance becomes `-1`.

---

## 3. Checking only the final balance

A path can finish at zero and still be invalid if an earlier prefix was negative.

For example:

```text
())(
```

ends with balance zero but is not valid.

---

## 4. Forgetting path-length parity

Every path has exactly:

```text
m+n-1
```

characters.

If that length is odd, a valid parentheses string is impossible.

---

## 5. Forgetting the endpoint checks

A valid sequence must begin with `'('` and end with `')'`.

These checks are cheap and should be done before the DP.

---

## 6. Brute-force path generation

Do not generate every down/right path explicitly.

The number of paths grows combinatorially.

Use DP to merge identical `(row, column, balance)` states.

---

# 🎯 Pattern Recognition

This problem combines three important patterns:

```text
Grid DP
   +
Prefix Balance
   +
State Compression
```

When a problem says:

```text
Choose a path through a grid
+
Characters on that path must satisfy a running condition
```

ask:

> **What is the smallest summary of the path that determines future validity?**

Here the answer is:

```text
current balance
```

So:

```text
(row, column, balance)
```

becomes the natural state.

---

# 🗣️ Interview Explanation

A strong interview explanation is:

> "Every down/right path has exactly `m+n-1` cells, so it creates a fixed-length parentheses string. I represent the prefix of that string by its balance, where '(' adds one and ')' subtracts one. A valid path must never have negative balance and must finish with zero balance. Therefore my DP state is the current grid cell plus the current balance. From each cell I transition from the top and left. The first implementation stores the full 3D state, while the optimized version keeps only the previous and current rows because those are the only rows needed for transitions."

---

# 🧾 Quick Revision

```text
1. len = m + n - 1

2. If len is odd:
       return false

3. First cell must be '('

4. Last cell must be ')'

5. Track balance:
       '(' → +1
       ')' → -1

6. Never allow balance < 0

7. At destination:
       balance must be 0
```

### DP State

```text
dp[i][j][balance]
```

means:

```text
A path exists from (0,0)
to (i,j)
with this balance.
```

---

# ⭐ One-Line Insight

> **Treat every grid path as a parentheses sequence and remember only its current balance; a valid path never goes below balance `0` and ends at balance `0`.**

---

# 📊 Complexity Summary

```text
Approach 1 — 3D DP
Time  : O(m × n × (m+n))
Space : O(m × n × (m+n))

Approach 2 — Rolling-Row DP
Time  : O(m × n × (m+n))
Space : O(n × (m+n))
```

---

# 🏷️ Tags

`Dynamic Programming, Grid DP, Matrix, String, Parentheses, Balance, State Compression, 3D DP, Space Optimization, Path Finding, Backtracking, Prefix Balance, LeetCode, Hard`

---

# 📚 Final Takeaway

The problem initially looks like a path-enumeration problem:

```text
Down / Right
     ↓
Many possible paths
```

But the important transformation is:

```text
Grid Path
    ↓
Parentheses String
    ↓
Prefix Balance
    ↓
DP State
```

The complete mental model is:

```text
                    GRID
                      │
                      ▼
               Move Right / Down
                      │
                      ▼
             Read '(' or ')'
                      │
                      ▼
              Update Balance
                      │
             ┌────────┴────────┐
             │                 │
          '(' +1            ')' -1
             │                 │
             └────────┬────────┘
                      ▼
              balance < 0 ?
                /         \
              Yes          No
               │             │
            discard       continue
                             │
                             ▼
                    reach destination?
                        /          \
                      No            Yes
                                    │
                                    ▼
                            balance == 0 ?
                               /       \
                             Yes       No
                              │         │
                              ▼         ▼
                            true      false
```

The most important lesson is:

```text
Do not store the whole path.
Store the information the future actually needs:
the current parentheses balance.
```

That is the key idea that turns exponential path exploration into dynamic programming.
