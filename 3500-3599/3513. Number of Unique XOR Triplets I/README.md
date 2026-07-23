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

# 3513. Number of Unique XOR Triplets I

## Tags

```text
Bit Manipulation
Math
Greedy
Array
Simulation
Java
C++
```

---

# Intuition

We need to determine

```text
How Many Distinct XOR Values
```

can be obtained by selecting triplets from the given array.

Instead of generating every possible triplet,

we observe a mathematical property of

```text
XOR Operation.
```

The answer depends only on the size of the array,

making brute force unnecessary.

---

# Key Observation

If

```text
n < 3
```

then every element itself contributes a unique XOR value,

so the answer is simply

```text
n.
```

Otherwise,

the XOR values cover every number representable using

```text
⌈log₂(n)⌉ Bits.
```

Hence,

the total number of distinct XOR values becomes

```text
2^k
```

where

```text
k = Number of Bits Required to Represent n.
```

---

# Approaches

1. Bit Manipulation + Mathematical Observation (Optimal)

---

# Approach 1 — Bit Manipulation + Mathematical Observation

## Idea

Handle the small case separately.

For

```text
n < 3
```

return

```text
n.
```

Otherwise,

find the number of bits required to represent

```text
n
```

using

```text
Integer.numberOfLeadingZeros().
```

Finally,

compute

```text
2^bits
```

using a left shift.

---

# Algorithm

### Step 1

Find the size of the array.

---

### Step 2

If

```text
n < 3
```

return

```text
n.
```

---

### Step 3

Compute

```text
bits =
32 - Integer.numberOfLeadingZeros(n)
```

---

### Step 4

Return

```text
1 << bits
```

which equals

```text
2^bits.
```

---

# Flowchart

```text
              Start

                │

                ▼

          Compute n

                │

                ▼

          Is n < 3 ?

        ┌───────┴────────┐

       Yes              No

        │                │

        ▼                ▼

    Return n      Compute Bit Count

                         │

                         ▼

                Return 2^bits

                         │

                         ▼

                       End
```

---

# Example

Input

```text
nums = [1,2,3]
```

```text
n = 3
```

Bits Required

```text
2
```

Answer

```text
2² = 4
```

---

# Dry Run

Input

```text
nums = [1,2,3,4,5]
```

Length

```text
n = 5
```

Bit Count

```text
bits = 3
```

Answer

```text
1 << 3

=

8
```

---

# Memory Visualization

```text
Input Array

      │

      ▼

Length (n)

      │

      ▼

Bit Count

      │

      ▼

Power of Two

      │

      ▼

Final Answer
```

---

# Why This Works?

The mathematical properties of XOR guarantee that,

once at least three elements are available,

all possible XOR values representable within the required bit width become achievable.

Therefore,

the answer depends only on

```text
The Number of Bits
```

required to represent

```text
n
```

instead of enumerating every triplet.

---

# Complexity Analysis

## Approach 1 — Bit Manipulation + Mathematical Observation

### Time Complexity

```text
O(1)
```

Only a few arithmetic and bit operations are performed.

---

### Space Complexity

```text
O(1)
```

No extra data structures are used.

---

# Java Solution

## Approach 1 — Bit Manipulation + Mathematical Observation (Optimal)

```java
//Approach-1 (Bit Manipulation + Mathematical Observation)
//T.C : O(1)
//S.C : O(1)

class Solution {

    public int uniqueXorTriplets(
        int[] nums
    ) {

        int n =
            nums.length;

        if (
            n < 3
        ) {

            return n;
        }

        int bits =
            32 -
            Integer.numberOfLeadingZeros(
                n
            );

        return
            1 << bits;
    }
}
```

---

# C++ Solution

## Approach 1 — Bit Manipulation + Mathematical Observation (Optimal)

```cpp
//Approach-1 (Bit Manipulation + Mathematical Observation)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:

    int uniqueXorTriplets(
        vector<int>& nums
    ) {

        int n =
            nums.size();

        if (
            n < 3
        ) {

            return n;
        }

        int bits =
            32 -
            __builtin_clz(
                n
            );

        return
            1 << bits;
    }
};
```

---

# Complexity Comparison

| Approach                                    | Algorithm        |   Time   |  Space   |
| :------------------------------------------ | :--------------- | :------: | :------: |
| Bit Manipulation + Mathematical Observation | Bit Manipulation | **O(1)** | **O(1)** |

---

# Final Complexity

```text
Approach 1 (Bit Manipulation + Mathematical Observation)

Time Complexity  : O(1)

Space Complexity : O(1)
```

---

# Conclusion

- ✅ Handle the special case where `n < 3` by returning `n`.
- ✅ For `n ≥ 3`, the answer depends only on the number of bits required to represent `n`.
- ✅ Compute the bit count efficiently using `Integer.numberOfLeadingZeros()` in Java or `__builtin_clz()` in C++.
- ✅ Return `2^bits` using a left shift (`1 << bits`).
- ✅ The solution performs only a few arithmetic and bit operations, achieving **O(1)** time and **O(1)** space complexity.
