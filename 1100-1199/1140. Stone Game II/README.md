# 1140. Stone Game II

## 🔗 Problem Link

**LeetCode 1140:** https://leetcode.com/problems/stone-game-ii/

---

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode:** https://leetcode.com/u/Shailu03/

---

# 📌 Problem Statement

Alice and Bob play a game using an array `piles`, where each element represents the number of stones in a pile.

Alice starts the game.

A variable `M` is initially set to:

```text
M = 1
```

During each turn, the current player can take:

```text
X
```

piles from the beginning of the remaining array, where:

```text
1 <= X <= 2M
```

After taking `X` piles, the value of `M` becomes:

```text
M = max(M, X)
```

The players continue until all piles have been taken.

Both players play optimally.

The goal is to determine the **maximum number of stones Alice can obtain**.

---

# 💡 Intuition

This is a classic **Game Theory + Dynamic Programming** problem.

At every state, we need to know:

```text
Current Position i

Current Limit M
```

The state can therefore be represented as:

```text
(i, M)
```

where:

- `i` = index of the first remaining pile.
- `M` = current maximum number controlling how many piles can be taken.

From this state, the current player can choose any:

```text
X = 1 ... 2M
```

piles.

After choosing `X`, the next state becomes:

```text
(i + X, max(M, X))
```

The important observation is that instead of calculating both players' scores separately, we can calculate the **maximum stones the current player can obtain** from every state.

---

# 🔍 Key Observation

Let:

```text
solve(i, M)
```

represent the maximum number of stones the current player can collect starting from index `i` with the current value of `M`.

Suppose the current player takes `X` piles.

The total number of stones remaining from index `i` is:

```text
suffixSum[i]
```

After taking `X` piles, the opponent gets the opportunity to play from:

```text
i + X
```

with:

```text
max(M, X)
```

Therefore, if the opponent can obtain:

```text
solve(i + X, max(M, X))
```

stones, the current player can obtain:

```text
suffixSum[i] - solve(i + X, max(M, X))
```

So the recurrence becomes:

```text
solve(i, M)

=

suffixSum[i]

-

min(
    solve(i + X, max(M, X))
)

for X = 1 ... 2M
```

We take the minimum opponent score because the current player wants to maximize their own score.

---

# 🧮 Why Do We Use Suffix Sum?

Instead of repeatedly calculating the sum of the remaining piles, we precompute:

```text
suffixSum[i]
```

where:

```text
suffixSum[i]
=
piles[i] + piles[i+1] + ... + piles[n-1]
```

For example:

```text
piles = [2,7,9,4,4]
```

The suffix sums are:

```text
index       suffixSum

0           26
1           24
2           17
3            8
4            4
```

Now the total number of stones remaining from index `i` can be obtained in:

```text
O(1)
```

time.

---

# 🚀 Approach — Dynamic Programming + Game Theory

We use:

```text
Memoization
+
Suffix Sum
+
Minimax Strategy
```

The DP state is:

```text
dp[i][M]
```

and represents the maximum number of stones the current player can collect from state `(i, M)`.

For every possible choice `X`:

```text
1 <= X <= 2M
```

we calculate the opponent's best possible score.

The current player then chooses the move that minimizes the opponent's score.

---

# 📝 Algorithm

### Step 1 — Build Suffix Sum

Traverse the array from right to left and calculate:

```text
suffixSum[i]
```

---

### Step 2 — Start DP

The initial state is:

```text
solve(0, 1)
```

because Alice starts from index `0` with:

```text
M = 1
```

---

### Step 3 — Base Case

If:

```text
i >= n
```

there are no piles left.

Return:

```text
0
```

---

### Step 4 — Take All Remaining Piles

If:

```text
i + 2M >= n
```

the current player can take all remaining piles.

Therefore:

```text
solve(i, M) = suffixSum[i]
```

---

### Step 5 — Check Memoization

If the state has already been calculated:

```text
dp[i][M] != 0
```

return the stored result.

---

### Step 6 — Try Every Possible Move

Try:

```text
X = 1 ... 2M
```

For every choice:

```text
nextM = max(M, X)
```

and calculate:

```text
solve(i + X, nextM)
```

---

### Step 7 — Minimize Opponent's Score

The opponent will also play optimally.

Therefore, the current player chooses the move that gives the opponent the smallest possible score:

```text
minOpponent =
    min(
        solve(i + X, max(M, X))
    )
```

---

### Step 8 — Calculate Current Player's Score

The total stones remaining are:

```text
suffixSum[i]
```

If the opponent eventually gets:

```text
minOpponent
```

then the current player gets:

```text
suffixSum[i] - minOpponent
```

---

# 🌳 Flowchart

```text
                         Start
                           │
                           ▼
                  Build Suffix Sum
                           │
                           ▼
                    solve(0, 1)
                           │
                           ▼
                    State (i, M)
                           │
                           ▼
                  Are all piles gone?
                     ┌─────┴─────┐
                    Yes          No
                     │            │
                     ▼            ▼
                   Return    Can take all?
                     0        ┌────┴────┐
                             Yes        No
                              │          │
                              ▼          ▼
                       Return suffix   Try X
                                      1 → 2M
                                         │
                                         ▼
                              Calculate opponent
                                         │
                                         ▼
                              Minimize opponent
                                         │
                                         ▼
                          Current = suffix - opponent
                                         │
                                         ▼
                                  Store in DP
                                         │
                                         ▼
                                    Return
```

---

# 📖 Example

```text
Input:

piles = [2,7,9,4,4]
```

Initially:

```text
i = 0
M = 1
```

Alice can take:

```text
X = 1
```

because:

```text
1 <= X <= 2M
```

After taking one pile:

```text
i = 1

M = max(1,1)

M = 1
```

Alice can also consider taking:

```text
X = 2
```

which changes the state to:

```text
i = 2

M = max(1,2)

M = 2
```

The DP evaluates both possibilities and all subsequent optimal moves.

The maximum stones Alice can obtain is:

```text
10
```

---

# 🔄 Dry Run Concept

Consider the initial state:

```text
solve(0,1)
```

Possible choices:

```text
X = 1
X = 2
```

For:

```text
X = 1
```

the next state is:

```text
solve(1,1)
```

For:

```text
X = 2
```

the next state is:

```text
solve(2,2)
```

The algorithm evaluates both states.

Conceptually:

```text
solve(0,1)
      │
      ├──────────────┐
      │              │
      ▼              ▼
 solve(1,1)       solve(2,2)
      │              │
      ▼              ▼
 Opponent score   Opponent score
      │              │
      └──────┬───────┘
             ▼
       Minimum Opponent
             │
             ▼
      suffixSum[0]
             -
       minOpponent
             │
             ▼
        Alice's Score
```

---

# 🧠 Why Minimax Works

Both players play optimally.

From a state `(i, M)`, the current player wants to maximize their own stones.

Because the total remaining stones are fixed:

```text
Current Player's Stones
+
Opponent's Stones
=
suffixSum[i]
```

maximizing the current player's score is equivalent to minimizing the opponent's score.

Therefore:

```text
Current Player's Best Score

=

Total Remaining Stones

-

Opponent's Minimum Best Score
```

This allows us to write the game as a clean minimax DP recurrence.

---

# ⚡ Optimization

The most important optimization is the suffix sum.

Without suffix sums, calculating the total remaining stones repeatedly could require additional work.

With:

```text
suffixSum[i]
```

we can get the remaining total instantly:

```text
suffixSum[i]
```

This makes each DP transition efficient.

---

# 📊 DP State

```text
dp[i][M]
```

means:

```text
Maximum stones the current player can obtain
starting from index i
with the current M value.
```

For example:

```text
dp[3][2]
```

means:

```text
Starting from pile index 3,
when M = 2,
what is the maximum number of stones
the current player can obtain?
```

---

# ⏱️ Complexity Analysis

Let:

```text
n = piles.length
```

The DP contains states based on:

```text
i
and
M
```

and each state may try up to:

```text
2M
```

possible moves.

For the given constraints, the memoized DP efficiently avoids recomputing the same game states.

The commonly stated complexity is approximately:

```text
Time Complexity  : O(n³)

Space Complexity : O(n²)
```

The `suffixSum` array requires:

```text
O(n)
```

additional space, while the DP table uses:

```text
O(n²)
```

---

# 🎯 Key Takeaways

- ✅ This is a **Game Theory + Dynamic Programming** problem.
- ✅ Represent each state using `(i, M)`.
- ✅ Use `suffixSum` to quickly calculate remaining stones.
- ✅ Try every valid choice `X` from `1` to `2M`.
- ✅ The next state becomes `(i + X, max(M, X))`.
- ✅ Minimize the opponent's best score.
- ✅ Convert that value into the current player's score using:

```text
suffixSum[i] - minOpponent
```

- ✅ Memoization prevents repeated computation of the same states.
- ✅ The final answer is:

```text
solve(0, 1)
```

# Java Solution

## Approach 1 — Dynamic Programming + Game Theory + Suffix Sum

```java
//Approach-1 (Dynamic Programming + Game Theory + Suffix Sum)
//T.C : O(n^3)
//S.C : O(n^2)

class Solution {

    public int stoneGameII(
        int[] piles
    ) {

        int n =
            piles.length;

        int[][] dp =
            new int[n][n + 1];

        int[] suffixSum =
            new int[n];

        suffixSum[n - 1] =
            piles[n - 1];

        for (
            int i = n - 2;
            i >= 0;
            i--
        ) {

            suffixSum[i] =
                suffixSum[i + 1] +
                piles[i];
        }

        return solve(
            0,
            1,
            piles,
            dp,
            suffixSum
        );
    }

    private int solve(
        int i,
        int M,
        int[] piles,
        int[][] dp,
        int[] suffixSum
    ) {

        int n =
            piles.length;

        if (
            i >= n
        ) {

            return 0;
        }

        if (
            i + 2 * M >= n
        ) {

            return suffixSum[i];
        }

        if (
            dp[i][M] != 0
        ) {

            return dp[i][M];
        }

        int minOpponent =
            Integer.MAX_VALUE;

        for (
            int X = 1;
            X <= 2 * M;
            X++
        ) {

            minOpponent =
                Math.min(
                    minOpponent,
                    solve(
                        i + X,
                        Math.max(M, X),
                        piles,
                        dp,
                        suffixSum
                    )
                );
        }

        dp[i][M] =
            suffixSum[i] -
            minOpponent;

        return dp[i][M];
    }
}
```

---

# C++ Solution

## Approach 1 — Dynamic Programming + Game Theory + Suffix Sum

```cpp
//Approach-1 (Dynamic Programming + Game Theory + Suffix Sum)
//T.C : O(n^3)
//S.C : O(n^2)

class Solution {
public:

    int stoneGameII(
        vector<int>& piles
    ) {

        int n =
            piles.size();

        vector<vector<int>> dp(
            n,
            vector<int>(n + 1, 0)
        );

        vector<int> suffixSum(n);

        suffixSum[n - 1] =
            piles[n - 1];

        for (
            int i = n - 2;
            i >= 0;
            i--
        ) {

            suffixSum[i] =
                suffixSum[i + 1] +
                piles[i];
        }

        return solve(
            0,
            1,
            piles,
            dp,
            suffixSum
        );
    }

private:

    int solve(
        int i,
        int M,
        vector<int>& piles,
        vector<vector<int>>& dp,
        vector<int>& suffixSum
    ) {

        int n =
            piles.size();

        if (
            i >= n
        ) {

            return 0;
        }

        if (
            i + 2 * M >= n
        ) {

            return suffixSum[i];
        }

        if (
            dp[i][M] != 0
        ) {

            return dp[i][M];
        }

        int minOpponent =
            INT_MAX;

        for (
            int X = 1;
            X <= 2 * M;
            X++
        ) {

            minOpponent =
                min(
                    minOpponent,
                    solve(
                        i + X,
                        max(M, X),
                        piles,
                        dp,
                        suffixSum
                    )
                );
        }

        dp[i][M] =
            suffixSum[i] -
            minOpponent;

        return dp[i][M];
    }
};
```

---

# 🔎 Detailed Recurrence

The core recurrence is:

```text
dp[i][M]

=

suffixSum[i]

-

min(
    dp[i + X][max(M, X)]
)

for 1 <= X <= 2M
```

### Why?

`dp[i][M]` represents the maximum stones the **current player** can obtain.

The total number of stones still available is:

```text
suffixSum[i]
```

After the current player chooses `X` piles, the opponent gets the remaining game state:

```text
(i + X, max(M, X))
```

The opponent will play optimally and maximize their own score.

Therefore, from the current player's perspective, we choose the move that gives the opponent the **minimum possible score**.

Hence:

```text
Current Player Score
=
Total Remaining Stones
-
Opponent Score
```

which gives:

```text
dp[i][M]
=
suffixSum[i]
-
minOpponent
```

---

# 🧩 DP State Explanation

```text
dp[i][M]
```

means:

> The maximum number of stones the current player can collect when the first remaining pile is at index `i` and the current value of `M` is `M`.

For example:

```text
dp[2][3]
```

means:

```text
Start from index 2
with M = 3
```

The player can choose up to:

```text
2 × M = 6
```

piles, subject to the number of remaining piles.

---

# 🔄 State Transition

For every state:

```text
(i, M)
```

try every possible:

```text
X = 1 ... 2M
```

After taking `X` piles:

```text
newIndex = i + X
```

and:

```text
newM = max(M, X)
```

Therefore:

```text
dp[i][M]

→

dp[i + X][max(M, X)]
```

---

# 🛑 Base Cases

### No Piles Remaining

If:

```text
i >= n
```

then:

```text
dp[i][M] = 0
```

because there are no stones left.

---

### Can Take Everything

If:

```text
i + 2M >= n
```

the current player can take all remaining piles.

Therefore:

```text
dp[i][M] = suffixSum[i]
```

This also avoids unnecessary recursion.

---

# 📊 Complexity Analysis

Let:

```text
n = piles.length
```

### Time Complexity

```text
O(n³)
```

The DP has `O(n²)` possible states, and each state may consider up to `O(n)` possible choices.

---

### Space Complexity

```text
O(n²)
```

for the memoization table.

The suffix sum array requires an additional:

```text
O(n)
```

space.

Therefore, the overall auxiliary space remains:

```text
O(n²)
```

---

# 🆚 Brute Force vs Dynamic Programming

| Approach                 |    Time     |   Space   | Repeated States |
| :----------------------- | :---------: | :-------: | :-------------: |
| Brute Force Recursion    | Exponential |   O(n)    |       Yes       |
| Memoization + Suffix Sum |  **O(n³)**  | **O(n²)** |       No        |

The major improvement comes from storing:

```text
dp[i][M]
```

so that the same game state is never solved repeatedly.

---

# 🎯 Final Takeaways

```text
Game State
    ↓
(i, M)
    ↓
Try X = 1 ... 2M
    ↓
Next State
(i + X, max(M, X))
    ↓
Minimize Opponent's Score
    ↓
Current Score
=
suffixSum[i] - minOpponent
```

### Final Complexity

```text
Time Complexity  : O(n³)

Space Complexity : O(n²)
```

The combination of **Game Theory, Memoization, and Suffix Sum** makes the solution efficient while keeping the recurrence clean and easy to reason about.
