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

# 70. Climbing Stairs

## Tags

```text
Dynamic Programming
Memoization
Tabulation
Space Optimization
Recursion
Fibonacci
Java
C++
```

---

# Intuition

At every step, we have only **two choices**:

```text
1 Step

or

2 Steps
```

If we are currently standing at stair `n`, then we can reach it only from:

```text
n - 1

or

n - 2
```

Hence,

```text
Ways(n)

=

Ways(n-1)

+

Ways(n-2)
```

This is exactly the **Fibonacci recurrence**.

---

# Approaches

1. Recursion + Memoization
2. Bottom-Up DP (Tabulation)
3. Space Optimized DP

---

# Approach 1 — Memoization (Top-Down DP)

## Idea

Solve the problem recursively.

Store already-computed answers in a DP array.

Whenever the same state appears again,

reuse the stored answer.

---

# Recurrence

```text
Ways(n)

=

Ways(n-1)

+

Ways(n-2)
```

Base Cases

```text
Ways(1)=1

Ways(2)=2
```

---

# Recursion Tree

```text
               f(5)

           /         \

        f(4)         f(3)

      /     \       /    \

   f(3)    f(2)  f(2)   f(1)

   /  \

f(2) f(1)
```

Memoization prevents computing

```text
f(3)

f(2)

again.
```

---

# Flowchart

```text
          Start

            │

            ▼

       Is n <= 2 ?

      ┌─────┴─────┐

     Yes         No

      │           │

      ▼           ▼

 Return n     Already in DP ?

                  │

          ┌───────┴───────┐

         Yes             No

          │               │

          ▼               ▼

 Return DP[n]     Solve Recursively

                  │

                  ▼

      dp[n]=f(n-1)+f(n-2)

                  │

                  ▼

             Return Answer
```

---

# Dry Run

Input

```text
n = 5
```

Calculation

```text
f(5)

=

f(4)

+

f(3)

=

5

+

3

=

8
```

Answer

```text
8
```

---

# Visualization

```text
1

↓

2

↓

3

↓

5

↓

8
```

Each value equals

```text
Previous

+

Previous Previous
```

---

# Memory Visualization

```text
dp[]

Index

0 1 2 3 4 5

↓

- 1 2 3 5 8
```

Every state is computed only once.

---

# Approach 2 — Bottom-Up DP

Instead of recursion,

start from

```text
1

↓

2

↓

3

↓

...

↓

n
```

Fill the DP table iteratively.

---

# Approach 3 — Space Optimized DP

Observe that only

```text
Previous

and

Previous Previous
```

values are required.

Therefore,

instead of using an array,

store only two variables.

Space becomes

```text
O(1)
```

---

# Complexity Analysis

## Approach 1 — Memoization

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

## Approach 2 — Tabulation

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

## Approach 3 — Space Optimized

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

# Complexity Comparison

| Approach | Time | Space |
|:---------|:----:|:-----:|
| Memoization | O(n) | O(n) |
| Tabulation | O(n) | O(n) |
| Space Optimized | O(n) | O(1) |

---
# Java Solution

## Approach 1 — Memoization (Top-Down DP)

```java
//Approach-1 (Memoization / Top-Down DP)
//T.C : O(n)
//S.C : O(n)

import java.util.Arrays;

class Solution {

    int helper(int n, int[] dp) {

        if (n == 1 || n == 2) {
            return n;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        dp[n] =
            helper(n - 1, dp) +
            helper(n - 2, dp);

        return dp[n];
    }

    public int climbStairs(int n) {

        int[] dp = new int[n + 1];

        Arrays.fill(dp, -1);

        return helper(n, dp);
    }
}
```

---

## Approach 2 — Tabulation (Bottom-Up DP)

```java
//Approach-2 (Tabulation / Bottom-Up DP)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int climbStairs(int n) {

        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;

        dp[2] = 2;

        for (int i = 3; i <= n; i++) {

            dp[i] =
                dp[i - 1] +
                dp[i - 2];
        }

        return dp[n];
    }
}
```

---

## Approach 3 — Space Optimized DP

```java
//Approach-3 (Space Optimized DP)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public int climbStairs(int n) {

        if (n <= 2) {
            return n;
        }

        int previousTwo = 1;

        int previousOne = 2;

        for (int i = 3; i <= n; i++) {

            int current =
                previousOne +
                previousTwo;

            previousTwo = previousOne;

            previousOne = current;
        }

        return previousOne;
    }
}
```

---

# C++ Solution

## Approach 1 — Memoization (Top-Down DP)

```cpp
//Approach-1 (Memoization / Top-Down DP)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    int helper(
        int n,
        vector<int>& dp
    ) {

        if (n == 1 || n == 2) {
            return n;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        dp[n] =
            helper(n - 1, dp) +
            helper(n - 2, dp);

        return dp[n];
    }

    int climbStairs(int n) {

        vector<int> dp(
            n + 1,
            -1
        );

        return helper(n, dp);
    }
};
```

---

## Approach 2 — Tabulation (Bottom-Up DP)

```cpp
//Approach-2 (Tabulation / Bottom-Up DP)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    int climbStairs(int n) {

        if (n <= 2) {
            return n;
        }

        vector<int> dp(n + 1);

        dp[1] = 1;

        dp[2] = 2;

        for (int i = 3; i <= n; i++) {

            dp[i] =
                dp[i - 1] +
                dp[i - 2];
        }

        return dp[n];
    }
};
```

---

## Approach 3 — Space Optimized DP

```cpp
//Approach-3 (Space Optimized DP)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:

    int climbStairs(int n) {

        if (n <= 2) {
            return n;
        }

        int previousTwo = 1;

        int previousOne = 2;

        for (int i = 3; i <= n; i++) {

            int current =
                previousOne +
                previousTwo;

            previousTwo = previousOne;

            previousOne = current;
        }

        return previousOne;
    }
};
```

---

# Final Complexity

```text
Approach 1 (Memoization)

Time Complexity  : O(n)

Space Complexity : O(n)

----------------------------------------

Approach 2 (Tabulation)

Time Complexity  : O(n)

Space Complexity : O(n)

----------------------------------------

Approach 3 (Space Optimized)

Time Complexity  : O(n)

Space Complexity : O(1)
```

---

# Conclusion

- ✅ The problem follows the **Fibonacci recurrence**, where the number of ways to reach the current stair depends on the previous two stairs.
- ✅ **Memoization** avoids repeated recursive computations by storing previously computed results.
- ✅ **Tabulation** builds the solution iteratively from the base cases, eliminating recursion overhead.
- ✅ **Space Optimized DP** further improves memory usage by storing only the last two computed values, achieving **O(1)** auxiliary space while maintaining **O(n)** time complexity.