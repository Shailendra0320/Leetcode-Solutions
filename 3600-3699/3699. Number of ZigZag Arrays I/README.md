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

# 3699. Number of ZigZag Arrays I

## Tags

```text
Dynamic Programming
Prefix Sum
Combinatorics
Array
Math
```

---

# Intuition

A ZigZag Array follows:

```text
a1 < a2 > a3 < a4 > ...
```

or

```text
a1 > a2 < a3 > a4 < ...
```

We need to count all valid arrays of length:

```text
n
```

where every element belongs to:

```text
[l , r]
```

---

# Dynamic Programming State

Let:

```text
dpUp[i]
```

represent:

```text
Number of ways to build a zigzag array

ending at value i

where the last move was UP.
```

---

Let:

```text
dpDown[i]
```

represent:

```text
Number of ways to build a zigzag array

ending at value i

where the last move was DOWN.
```

---

# Transition

For an UP move:

```text
previous value

< current value
```

Therefore:

```text
dpUp[i]

=

sum(dpDown[j])

for all

j < i
```

---

For a DOWN move:

```text
previous value

> current value
```

Therefore:

```text
dpDown[i]

=

sum(dpUp[j])

for all

j > i
```

---

# Why Prefix Sum?

Direct computation:

```text
For every i

scan all previous values
```

Cost:

```text
O(m²)
```

---

Using Prefix Sum:

```text
Range Sum Query

O(1)
```

Transition becomes:

```text
O(m)
```

per level.

---

# Flowchart

```text
Start

   │
   ▼

Initialize

dpUp = 1

dpDown = 1

   │
   ▼

Build Prefix Sums

   │
   ▼

Compute

nextUp

nextDown

   │
   ▼

Move To Next Length

   │
   ▼

Length = n ?

   │
   ▼

Sum All States

   │
   ▼

Answer
```

---

# Example

Input

```text
n = 3

l = 1

r = 3
```

Values:

```text
1 2 3
```

Possible ZigZag Arrays:

```text
1 2 1

1 3 1

1 3 2

2 3 1

2 1 2

2 1 3

3 2 1

3 1 2

3 1 3
```

Answer:

```text
9
```

---

# DP Visualization

Suppose:

```text
Values

1 2 3
```

Initially:

```text
dpUp

1 1 1

dpDown

1 1 1
```

---

Prefix Sums:

```text
prefixUp

0 1 2 3

prefixDown

0 1 2 3
```

---

Next State:

```text
nextUp

0 1 2

nextDown

2 1 0
```

---

Continue until:

```text
length = n
```

---

# Complexity Analysis

## Time Complexity

```text
O(n × m)
```

where

```text
m = r - l + 1
```

---

## Space Complexity

```text
O(m)
```

Only current DP arrays are stored.

---

# Java Solution

```java
class Solution {

    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {

        if (n == 1) {
            return (r - l + 1) % MOD;
        }

        int m = r - l + 1;

        long[] dpUp = new long[m];
        long[] dpDown = new long[m];

        for (int i = 0; i < m; i++) {
            dpUp[i] = 1;
            dpDown[i] = 1;
        }

        for (int len = 2; len <= n; len++) {

            long[] prefixUp = new long[m + 1];
            long[] prefixDown = new long[m + 1];

            for (int i = 0; i < m; i++) {

                prefixUp[i + 1] =
                    (prefixUp[i] + dpUp[i]) % MOD;

                prefixDown[i + 1] =
                    (prefixDown[i] + dpDown[i]) % MOD;
            }

            long[] nextUp = new long[m];
            long[] nextDown = new long[m];

            for (int i = 0; i < m; i++) {

                nextUp[i] =
                    (
                        prefixDown[i]
                        -
                        prefixDown[0]
                        +
                        MOD
                    ) % MOD;

                nextDown[i] =
                    (
                        prefixUp[m]
                        -
                        prefixUp[i + 1]
                        +
                        MOD
                    ) % MOD;
            }

            dpUp = nextUp;
            dpDown = nextDown;
        }

        long answer = 0;

        for (int i = 0; i < m; i++) {

            answer =
                (
                    answer
                    +
                    dpUp[i]
                    +
                    dpDown[i]
                ) % MOD;
        }

        return (int) answer;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    static constexpr long long MOD = 1000000007LL;

    int zigZagArrays(
        int n,
        int l,
        int r
    ) {

        if (n == 1) {
            return (r - l + 1) % MOD;
        }

        int m = r - l + 1;

        vector<long long> dpUp(m, 1);
        vector<long long> dpDown(m, 1);

        for (int len = 2; len <= n; len++) {

            vector<long long> prefixUp(m + 1, 0);
            vector<long long> prefixDown(m + 1, 0);

            for (int i = 0; i < m; i++) {

                prefixUp[i + 1] =
                    (
                        prefixUp[i]
                        +
                        dpUp[i]
                    ) % MOD;

                prefixDown[i + 1] =
                    (
                        prefixDown[i]
                        +
                        dpDown[i]
                    ) % MOD;
            }

            vector<long long> nextUp(m);
            vector<long long> nextDown(m);

            for (int i = 0; i < m; i++) {

                nextUp[i] =
                    (
                        prefixDown[i]
                        -
                        prefixDown[0]
                        +
                        MOD
                    ) % MOD;

                nextDown[i] =
                    (
                        prefixUp[m]
                        -
                        prefixUp[i + 1]
                        +
                        MOD
                    ) % MOD;
            }

            dpUp = move(nextUp);
            dpDown = move(nextDown);
        }

        long long answer = 0;

        for (int i = 0; i < m; i++) {

            answer =
                (
                    answer
                    +
                    dpUp[i]
                    +
                    dpDown[i]
                ) % MOD;
        }

        return (int) answer;
    }
};
```