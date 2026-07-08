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

# 3756. Sum of Digits and Multiply Queries

## Tags

```text
Prefix Sum
String
Hashing
Rolling Hash
Math
Simulation
Queries
Java
C++
```

---

# Intuition

For every query,

we need to calculate

```text
(Number after removing all zeros)

×

(Sum of all remaining digits)
```

inside the given substring.

Doing this independently for every query would require

```text
O(length of substring)
```

which is too slow.

Instead,

we preprocess the string once using

```text
Prefix Sum

+

Prefix Hash
```

so every query can be answered in

```text
O(1)
```

time.

---

# Key Observation

Each query requires three values.

```text
1. Sum of Non-Zero Digits

2. Number of Non-Zero Digits

3. Number formed after removing zeros
```

All three can be obtained using prefix arrays.

---

# Approaches

1. Prefix Sum + Prefix Hash (Optimal)

---

# Approach 1 — Prefix Sum + Prefix Hash

## Idea

Precompute

```text
Prefix Digit Sum
```

to obtain the sum of non-zero digits.

Precompute

```text
Prefix Count
```

to know how many non-zero digits exist.

Precompute

```text
Prefix Rolling Hash
```

to reconstruct the number formed after removing zeros.

Finally,

for every query,

calculate

```text
Answer

=

(Number Without Zeros)

×

(Digit Sum)

mod 1e9+7
```

---

# Algorithm

### Step 1

Precompute

```text
Power of 10
```

---

### Step 2

Build

```text
Prefix Sum

Prefix Count

Prefix Hash
```

---

### Step 3

For every character

Ignore

```text
0
```

Otherwise

update

```text
Prefix Sum

Prefix Count

Prefix Hash
```

---

### Step 4

For every query

find

```text
Digit Sum
```

using

```text
Prefix Sum
```

---

### Step 5

Find

```text
Number of Digits
```

using

```text
Prefix Count
```

---

### Step 6

Recover the compressed number

using

```text
Rolling Hash
```

and

```text
Power of 10
```

---

### Step 7

Return

```text
Compressed Number

×

Digit Sum

(mod 1e9+7)
```

---

# Flowchart

```text
              Start

                │

                ▼

      Read Input String

                │

                ▼

 Precompute Power Of 10

                │

                ▼

 Build Prefix Arrays

      ┌──────────────┐
      │ Prefix Sum   │
      │ Prefix Count │
      │ Prefix Hash  │
      └──────────────┘

                │

                ▼

        Process Query

                │

                ▼

 Obtain Sum Of Digits

                │

                ▼

 Obtain Digit Count

                │

                ▼

 Recover Number

                │

                ▼

 Multiply

                │

                ▼

 Return Answer
```

---

# Example

Input

```text
s = "105203"
```

Query

```text
[0,5]
```

Original

```text
105203
```

Remove Zeros

```text
1523
```

Digit Sum

```text
1 + 5 + 2 + 3

=

11
```

Compressed Number

```text
1523
```

Answer

```text
1523 × 11

=

16753
```

---

# Dry Run

String

```text
105203
```

Prefix Sum

```text
1

1

6

8

8

11
```

Prefix Count

```text
1

1

2

3

3

4
```

Compressed Number

```text
1

1

15

152

152

1523
```

For Query

```text
[0,5]
```

Digit Sum

```text
11
```

Compressed Number

```text
1523
```

Answer

```text
16753
```

---

# Memory Visualization

```text
Input String

        │

        ▼

Power Of 10

        │

        ▼

Prefix Sum

        │

        ▼

Prefix Count

        │

        ▼

Prefix Hash

        │

        ▼

Queries

        │

        ▼

O(1) Answer
```

---

# Why Prefix Sum + Hashing Works?

The prefix arrays store enough information to answer every query independently.

Using

```text
Prefix Sum
```

we obtain

```text
Sum of Digits
```

Using

```text
Prefix Count
```

we know how many non-zero digits belong to the query.

Using

```text
Rolling Hash
```

and

```text
Power of 10
```

we reconstruct the compressed number in constant time.

Thus,

every query is answered in

```text
O(1)
```

after preprocessing.

---

# Complexity Analysis

## Approach 1 — Prefix Sum + Rolling Hash

### Time Complexity

```text
Preprocessing

O(n)

+

Each Query

O(1)

Overall

O(n + q)
```

---

### Space Complexity

```text
O(n)
```

for

```text
Power Of 10

Prefix Sum

Prefix Count

Prefix Hash
```

---

# Java Solution

## Approach 1 — Prefix Sum + Rolling Hash (Optimal)

```java
//Approach-1 (Prefix Sum + Rolling Hash)
//T.C : O(n + q)
//S.C : O(n)

class Solution {

    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(
        String s,
        int[][] queries
    ) {

        int n = s.length();

        long[] power10 = new long[n + 1];

        power10[0] = 1;

        for (int i = 1; i <= n; i++) {

            power10[i] =
                (power10[i - 1] * 10) % MOD;
        }

        long[] prefixHash = new long[n + 1];

        int[] prefixCount = new int[n + 1];

        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {

            int digit =
                s.charAt(i) - '0';

            prefixCount[i + 1] =
                prefixCount[i];

            prefixSum[i + 1] =
                prefixSum[i];

            prefixHash[i + 1] =
                prefixHash[i];

            if (digit != 0) {

                prefixCount[i + 1]++;

                prefixSum[i + 1] += digit;

                prefixHash[i + 1] =
                    (prefixHash[i] * 10 + digit)
                    % MOD;
            }
        }

        int[] answer =
            new int[queries.length];

        for (
            int i = 0;
            i < queries.length;
            i++
        ) {

            int left =
                queries[i][0];

            int right =
                queries[i][1];

            int digitSum =
                prefixSum[right + 1] -
                prefixSum[left];

            int digits =
                prefixCount[right + 1] -
                prefixCount[left];

            long number =
                (
                    prefixHash[right + 1]
                    -
                    (
                        prefixHash[left] *
                        power10[digits]
                    ) % MOD
                    +
                    MOD
                ) % MOD;

            answer[i] =
                (int)
                (
                    number *
                    digitSum
                % MOD
                );
        }

        return answer;
    }
}
```

---

# C++ Solution

## Approach 1 — Prefix Sum + Rolling Hash (Optimal)

```cpp
//Approach-1 (Prefix Sum + Rolling Hash)
//T.C : O(n + q)
//S.C : O(n)

class Solution {
public:

    static constexpr int MOD =
        1000000007;

    vector<int> sumAndMultiply(
        string s,
        vector<vector<int>>& queries
    ) {

        int n = s.size();

        vector<long long> power10(
            n + 1
        );

        power10[0] = 1;

        for (int i = 1; i <= n; i++) {

            power10[i] =
                (power10[i - 1] * 10)
                % MOD;
        }

        vector<long long> prefixHash(
            n + 1
        );

        vector<int> prefixCount(
            n + 1
        );

        vector<int> prefixSum(
            n + 1
        );

        for (int i = 0; i < n; i++) {

            int digit =
                s[i] - '0';

            prefixCount[i + 1] =
                prefixCount[i];

            prefixSum[i + 1] =
                prefixSum[i];

            prefixHash[i + 1] =
                prefixHash[i];

            if (digit != 0) {

                prefixCount[i + 1]++;

                prefixSum[i + 1] += digit;

                prefixHash[i + 1] =
                    (
                        prefixHash[i] * 10
                        + digit
                    ) % MOD;
            }
        }

        vector<int> answer(
            queries.size()
        );

        for (
            int i = 0;
            i < queries.size();
            i++
        ) {

            int left =
                queries[i][0];

            int right =
                queries[i][1];

            int digitSum =
                prefixSum[right + 1]
                -
                prefixSum[left];

            int digits =
                prefixCount[right + 1]
                -
                prefixCount[left];

            long long number =
                (
                    prefixHash[right + 1]
                    -
                    (
                        prefixHash[left]
                        *
                        power10[digits]
                    ) % MOD
                    +
                    MOD
                ) % MOD;

            answer[i] =
                (
                    number *
                    digitSum
                ) % MOD;
        }

        return answer;
    }
};
```

---

# Complexity Comparison

| Approach | Algorithm | Time | Space |
|:---------|:----------|:----:|:-----:|
| Prefix Sum + Rolling Hash | Prefix Arrays + Hashing | **O(n + q)** | **O(n)** |

---

# Final Complexity

```text
Approach 1 (Prefix Sum + Rolling Hash)

Preprocessing Time : O(n)

Each Query         : O(1)

Overall Time       : O(n + q)

Space Complexity   : O(n)
```

---

# Conclusion

- ✅ Precompute **Power of 10** for efficient hash extraction.
- ✅ Build **Prefix Sum**, **Prefix Count**, and **Prefix Rolling Hash** in one traversal.
- ✅ Use the prefix arrays to answer each query in **O(1)** time.
- ✅ The rolling hash reconstructs the number formed after removing zeros without rebuilding the substring.
- ✅ Overall complexity is **O(n + q)**, making this approach optimal for handling multiple queries efficiently.