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

# 3336. Find the Number of Subsequences With Equal GCD

## Tags

```text
Dynamic Programming
Memoization
Recursion
Greatest Common Divisor (GCD)
Math
Array
Java
C++
```

---

# Intuition

We need to divide the elements into

```text
Subsequence 1

Subsequence 2

or

Ignore
```

such that

```text
GCD(Subsequence 1)

=

GCD(Subsequence 2)
```

Instead of generating every possible pair of subsequences,

we keep track of only the

```text
Current GCD

of both subsequences.
```

This significantly reduces the number of states.

---

# Key Observation

Each element has only

```text
3 Choices
```

```text
1. Add to First Subsequence

2. Add to Second Subsequence

3. Ignore
```

The current state can therefore be represented by

```text
(Current Index,

Current GCD of First,

Current GCD of Second)
```

This naturally leads to a

```text
3-D Dynamic Programming
```

solution.

---

# Approaches

1. Dynamic Programming + Memoization (Optimal)

---

# Approach 1 — Dynamic Programming + Memoization

## Idea

Process every element one by one.

For every element,

we recursively explore all three choices.

Whenever we reach the end,

check whether

```text
Both Subsequences

are non-empty

and

their GCDs are equal.
```

Store every computed state inside

```text
DP[index][gcd1][gcd2]
```

to avoid repeated computations.

---

# Algorithm

### Step 1

Initialize

```text
DP[index][gcd1][gcd2]

=

-1
```

---

### Step 2

Start recursion from

```text
Index = 0

GCD1 = 0

GCD2 = 0
```

---

### Step 3

For every element,

perform three recursive calls.

```text
Take in First

Take in Second

Skip
```

---

### Step 4

Update GCD using

```text
gcd(previousGCD, currentValue)
```

---

### Step 5

When all elements are processed,

check

```text
gcd1 > 0

gcd2 > 0

gcd1 == gcd2
```

If true,

return

```text
1
```

otherwise

```text
0
```

---

### Step 6

Memoize the answer and return it.

---

# Flowchart

```text
               Start

                 │

                 ▼

      Current Element

                 │

                 ▼

      Three Possible Choices

      ┌──────┬──────┬──────┐
      │      │      │
      ▼      ▼      ▼

 First     Second   Ignore

      │      │      │

      └──┬───┴───┬──┘
         │       │

         ▼

   Update GCD States

         │

         ▼

      End of Array ?

      ┌──────┴──────┐

     No            Yes

      │             │

      ▼             ▼

 Continue     gcd1 == gcd2 ?

                    │

              ┌─────┴─────┐

             Yes         No

              │            │

              ▼            ▼

             1             0
```

---

# Example

Input

```text
nums = [2,4,6]
```

One possible partition

```text
Subsequence 1

[2,4]

GCD = 2
```

```text
Subsequence 2

[6]

GCD = 6
```

Not Valid

---

Another partition

```text
Subsequence 1

[2]

GCD = 2
```

```text
Subsequence 2

[2,4]

GCD = 2
```

Valid

Every valid partition contributes

```text
1
```

to the final answer.

---

# Dry Run

Input

```text
[2,4]
```

Start

```text
Index = 0

GCD1 = 0

GCD2 = 0
```

↓

Choose

```text
2

into First
```

State

```text
(1,2,0)
```

↓

Choose

```text
4

into Second
```

State

```text
(2,2,4)
```

↓

Not Equal

Return

```text
0
```

Another path

```text
2

into First
```

↓

Skip

```text
4
```

↓

Only one subsequence exists

Return

```text
0
```

DP ensures every state is computed only once.

---

# Memory Visualization

```text
Current Index

        │

        ▼

Current GCD1

        │

        ▼

Current GCD2

        │

        ▼

DP Memoization

        │

        ▼

Reuse Computed States
```

---

# Why Dynamic Programming Works?

The future answer depends only on

```text
Current Index

Current GCD of First Subsequence

Current GCD of Second Subsequence
```

Therefore,

these three values uniquely define a state.

Memoizing every state prevents exponential recomputation,

reducing the solution to a manageable number of states.

---

# Complexity Analysis

## Approach 1 — Dynamic Programming + Memoization

### Time Complexity

```text
O(n × G²)

where

G = Maximum Possible GCD (200)
```

---

### Space Complexity

```text
O(n × G²)
```

for the DP table.

---

# Java Solution

## Approach 1 — Dynamic Programming + Memoization (Optimal)

```java
//Approach-1 (Dynamic Programming + Memoization)
//T.C : O(n × G²)
//S.C : O(n × G²)

import java.util.Arrays;

class Solution {

    static final int MOD = 1_000_000_007;

    int[][][] dp;

    public int subsequencePairCount(
        int[] nums
    ) {

        int n = nums.length;

        dp = new int[n][201][201];

        for (int[][] first : dp) {

            for (int[] second : first) {

                Arrays.fill(
                    second,
                    -1
                );
            }
        }

        return solve(
            nums,
            0,
            0,
            0
        );
    }

    private int solve(
        int[] nums,
        int index,
        int gcd1,
        int gcd2
    ) {

        if (index == nums.length) {

            return (
                gcd1 > 0 &&
                gcd2 > 0 &&
                gcd1 == gcd2
            ) ? 1 : 0;
        }

        if (
            dp[index][gcd1][gcd2]
            != -1
        ) {

            return
                dp[index][gcd1][gcd2];
        }

        int current =
            nums[index];

        long answer = 0;

        answer += solve(
            nums,
            index + 1,
            gcd(gcd1, current),
            gcd2
        );

        answer += solve(
            nums,
            index + 1,
            gcd1,
            gcd(gcd2, current)
        );

        answer += solve(
            nums,
            index + 1,
            gcd1,
            gcd2
        );

        dp[index][gcd1][gcd2] =
            (int)
            (answer % MOD);

        return
            dp[index][gcd1][gcd2];
    }

    private int gcd(
        int a,
        int b
    ) {

        return b == 0
            ? a
            : gcd(
                b,
                a % b
            );
    }
}
```

---

# C++ Solution

## Approach 1 — Dynamic Programming + Memoization (Optimal)

```cpp
//Approach-1 (Dynamic Programming + Memoization)
//T.C : O(n × G²)
//S.C : O(n × G²)

class Solution {
public:

    static const int MOD =
        1000000007;

    vector<vector<vector<int>>> dp;

    int subsequencePairCount(
        vector<int>& nums
    ) {

        int n = nums.size();

        dp.assign(
            n,
            vector<vector<int>>(
                201,
                vector<int>(
                    201,
                    -1
                )
            )
        );

        return solve(
            nums,
            0,
            0,
            0
        );
    }

    int solve(
        vector<int>& nums,
        int index,
        int gcd1,
        int gcd2
    ) {

        if (index == nums.size()) {

            return (
                gcd1 > 0 &&
                gcd2 > 0 &&
                gcd1 == gcd2
            ) ? 1 : 0;
        }

        if (
            dp[index][gcd1][gcd2]
            != -1
        ) {

            return
                dp[index][gcd1][gcd2];
        }

        int current =
            nums[index];

        long long answer = 0;

        answer += solve(
            nums,
            index + 1,
            gcd(gcd1, current),
            gcd2
        );

        answer += solve(
            nums,
            index + 1,
            gcd1,
            gcd(gcd2, current)
        );

        answer += solve(
            nums,
            index + 1,
            gcd1,
            gcd2
        );

        return dp[index][gcd1][gcd2] =
            answer % MOD;
    }

    int gcd(
        int a,
        int b
    ) {

        return b == 0
            ? a
            : gcd(
                b,
                a % b
            );
    }
};
```

---

# Complexity Comparison

| Approach                          | Algorithm |     Time      |     Space     |
| :-------------------------------- | :-------- | :-----------: | :-----------: |
| Dynamic Programming + Memoization | 3D DP     | **O(n × G²)** | **O(n × G²)** |

---

# Final Complexity

```text
Approach 1 (Dynamic Programming + Memoization)

Time Complexity  : O(n × G²)

Space Complexity : O(n × G²)

where

n = Number of Elements

G = Maximum Possible GCD (200)
```

---

# Conclusion

- ✅ Each element has exactly **three choices**: include it in the first subsequence, include it in the second subsequence, or ignore it.
- ✅ The DP state is defined by **(index, gcd₁, gcd₂)**, which uniquely determines all future decisions.
- ✅ Memoization prevents recomputation of identical states, significantly reducing the search space.
- ✅ The Euclidean algorithm efficiently updates the GCD after each selection.
- ✅ The solution counts all valid pairs of non-empty subsequences having the **same GCD** while taking results modulo **10⁹ + 7**.
