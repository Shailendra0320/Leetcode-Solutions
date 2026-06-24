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

# 3700. Number of ZigZag Arrays II

## Tags

```text
Dynamic Programming
Matrix Exponentiation
Graph
Math
Binary Exponentiation
```

---

# Intuition

A ZigZag Array follows:

```text
a1 < a2 > a3 < a4 ...
```

or

```text
a1 > a2 < a3 > a4 ...
```

We need to count all valid ZigZag arrays of length:

```text
n
```

where:

```text
l ≤ ai ≤ r
```

---

# Why DP Fails

For Part-I:

```text
DP + Prefix Sum
```

worked because:

```text
n was small
```

For Part-II:

```text
n can be very large
```

so:

```text
O(n × m)
```

 is too slow.

---

# Optimization

Use:

```text
Matrix Exponentiation
```

to reduce:

```text
O(n)
```

to:

```text
O(log n)
```

---

# State Representation

For each value:

```text
0 ... m-1
```

store:

```text
UP state

DOWN state
```

Total states:

```text
2 × m
```

---

# Graph Visualization

Example

```text
Values

1 2 3
```

States

```text
UP(1)
UP(2)
UP(3)

DOWN(1)
DOWN(2)
DOWN(3)
```

Transitions

```text
UP(3)
   ↑
DOWN(1)

UP(3)
   ↑
DOWN(2)

DOWN(1)
   ↑
UP(2)

DOWN(1)
   ↑
UP(3)
```

---

# Transition Matrix

Matrix:

```text
2m × 2m
```

stores:

```text
Can we move
between states?
```

---

# Binary Exponentiation

Instead of:

```text
Transition
applied

n times
```

we compute:

```text
Transition^(n-2)
```

using:

```text
Fast Power
```

---

# Flowchart

```text
Build Transition Matrix

          │
          ▼

Build Initial State

          │
          ▼

Binary Exponentiation

          │
          ▼

Matrix × Vector

          │
          ▼

Get Final State

          │
          ▼

Sum All Ways

          │
          ▼

Answer
```

---

# Complexity Analysis

## Time Complexity

```text
O((2m)^3 log n)
```

which simplifies to:

```text
O(m^3 log n)
```

---

## Space Complexity

```text
O(m²)
```

for transition matrix.

---

# Java Solution

```java
class Solution {

    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;

        if (n == 1) {
            return m;
        }

        int size = 2 * m;

        long[][] transition = new long[size][size];

        for (int current = 0; current < m; current++) {

            for (int previous = 0; previous < current; previous++) {

                transition[current][m + previous] = 1;
            }
        }

        for (int current = 0; current < m; current++) {

            for (int previous = current + 1; previous < m; previous++) {

                transition[m + current][previous] = 1;
            }
        }

        long[] state = new long[size];

        for (int value = 0; value < m; value++) {

            state[value] = value;

            state[m + value] = m - value - 1;
        }

        long power = n - 2;

        long[][] currentMatrix = transition;

        while (power > 0) {

            if ((power & 1) == 1) {

                state = multiply(currentMatrix, state);
            }

            currentMatrix =
                multiply(
                    currentMatrix,
                    currentMatrix
                );

            power >>= 1;
        }

        long answer = 0;

        for (long value : state) {

            answer =
                (
                    answer
                    +
                    value
                ) % MOD;
        }

        return (int) answer;
    }

    private long[] multiply(
        long[][] matrix,
        long[] vector
    ) {

        int size = matrix.length;

        long[] result =
            new long[size];

        for (int row = 0; row < size; row++) {

            long sum = 0;

            for (int col = 0; col < size; col++) {

                if (matrix[row][col] == 0) {
                    continue;
                }

                sum =
                    (
                        sum
                        +
                        matrix[row][col]
                        *
                        vector[col]
                    ) % MOD;
            }

            result[row] = sum;
        }

        return result;
    }

    private long[][] multiply(
        long[][] first,
        long[][] second
    ) {

        int size = first.length;

        long[][] result =
            new long[size][size];

        for (int row = 0; row < size; row++) {

            for (int mid = 0; mid < size; mid++) {

                if (first[row][mid] == 0) {
                    continue;
                }

                long value =
                    first[row][mid];

                for (int col = 0; col < size; col++) {

                    if (second[mid][col] == 0) {
                        continue;
                    }

                    result[row][col] =
                        (
                            result[row][col]
                            +
                            value
                            *
                            second[mid][col]
                        ) % MOD;
                }
            }
        }

        return result;
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

        int m = r - l + 1;

        if (n == 1) {
            return m;
        }

        int size = 2 * m;

        vector<vector<long long>> transition(
            size,
            vector<long long>(size, 0)
        );

        for (
            int current = 0;
            current < m;
            current++
        ) {

            for (
                int previous = 0;
                previous < current;
                previous++
            ) {

                transition[current][m + previous] = 1;
            }
        }

        for (
            int current = 0;
            current < m;
            current++
        ) {

            for (
                int previous = current + 1;
                previous < m;
                previous++
            ) {

                transition[m + current][previous] = 1;
            }
        }

        vector<long long> state(size);

        for (int value = 0; value < m; value++) {

            state[value] = value;

            state[m + value] = m - value - 1;
        }

        long long power = n - 2;

        auto currentMatrix = transition;

        while (power > 0) {

            if (power & 1) {

                state =
                    multiply(
                        currentMatrix,
                        state
                    );
            }

            currentMatrix =
                multiply(
                    currentMatrix,
                    currentMatrix
                );

            power >>= 1;
        }

        long long answer = 0;

        for (long long value : state) {

            answer =
                (
                    answer
                    +
                    value
                ) % MOD;
        }

        return (int)answer;
    }

private:

    vector<long long> multiply(
        vector<vector<long long>>& matrix,
        vector<long long>& vectorState
    ) {

        int size = matrix.size();

        vector<long long> result(size);

        for (int row = 0; row < size; row++) {

            long long sum = 0;

            for (int col = 0; col < size; col++) {

                if (matrix[row][col] == 0) {
                    continue;
                }

                sum =
                    (
                        sum
                        +
                        matrix[row][col]
                        *
                        vectorState[col]
                    ) % MOD;
            }

            result[row] = sum;
        }

        return result;
    }

    vector<vector<long long>> multiply(
        vector<vector<long long>>& first,
        vector<vector<long long>>& second
    ) {

        int size = first.size();

        vector<vector<long long>> result(
            size,
            vector<long long>(size, 0)
        );

        for (int row = 0; row < size; row++) {

            for (int mid = 0; mid < size; mid++) {

                if (first[row][mid] == 0) {
                    continue;
                }

                long long value =
                    first[row][mid];

                for (int col = 0; col < size; col++) {

                    if (second[mid][col] == 0) {
                        continue;
                    }

                    result[row][col] =
                        (
                            result[row][col]
                            +
                            value
                            *
                            second[mid][col]
                        ) % MOD;
                }
            }
        }

        return result;
    }
};
```