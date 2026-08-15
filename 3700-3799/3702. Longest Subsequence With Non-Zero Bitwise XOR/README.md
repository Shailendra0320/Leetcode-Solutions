# 3702. Longest Subsequence With Non-Zero Bitwise XOR

> **LeetCode | Bit Manipulation | XOR | Greedy | Mathematical Observation | Array**

---

# 🔗 Problem Links

- **LeetCode:** https://leetcode.com/problems/longest-subsequence-with-non-zero-bitwise-xor/
- **GitHub:** https://github.com/Shailendra0320
- **LeetCode Profile:** https://leetcode.com/u/ShailendraLeetcode03/
- **Alternate LeetCode Profile:** https://leetcode.com/u/Shailu03/

---

# 📌 Problem Statement

You are given an integer array `nums`.

Return the length of the **longest subsequence** whose bitwise XOR is **non-zero**.

If no such subsequence exists, return:

```text
0
```

---

# 🏷️ Tags

```text
Bit Manipulation
XOR
Greedy
Mathematical Observation
Array
Subsequence
Java
C++
```

---

# 🧠 Intuition

At first glance, this problem looks like a subsequence problem where we need to try many different combinations.

However, generating all subsequences would require:

```text
2^n
```

possibilities.

The key observation is that we only need to calculate the XOR of the **entire array** and check whether the array contains at least one non-zero element.

There are only three possible cases:

```text
                    Total XOR
                        │
              ┌─────────┴─────────┐
              │                   │
           != 0                   = 0
              │                   │
              ▼             ┌─────┴─────┐
         Answer = n          │           │
                         All Zero    Non-Zero Exists
                            │           │
                            ▼           ▼
                       Answer = 0   Answer = n - 1
```

---

# 🔑 Key Observation

## Case 1 — Total XOR Is Non-Zero

If:

```text
totalXor != 0
```

then the entire array is already a valid subsequence.

Therefore:

```text
answer = n
```

---

## Case 2 — Total XOR Is Zero and All Elements Are Zero

If:

```text
totalXor == 0
```

and every element is zero, then every possible subsequence also has XOR `0`.

For example:

```text
[0, 0, 0]
```

Every subsequence has:

```text
XOR = 0
```

Therefore:

```text
answer = 0
```

---

## Case 3 — Total XOR Is Zero but a Non-Zero Element Exists

Suppose:

```text
totalXor = 0
```

and there is at least one:

```text
x != 0
```

Remove that one non-zero element.

Because:

```text
x ^ remainingXor = 0
```

we get:

```text
remainingXor = x
```

Since:

```text
x != 0
```

the remaining XOR is also non-zero.

Therefore, we can always create a valid subsequence of length:

```text
n - 1
```

So:

```text
answer = n - 1
```

---

# 🚀 Approaches

```text
1. XOR Observation + Boolean Check       → Best
2. XOR Observation + Non-Zero Count      → Second Best
```

Both achieve:

```text
Time:  O(n)
Space: O(1)
```

The first solution is slightly cleaner because it only tracks whether a non-zero element exists.

---

# 🥇 Approach 1 — XOR Observation + Boolean Check

## Algorithm

Maintain two variables:

```text
totalXor
hasNonZero
```

Traverse the array once.

For every value:

```text
totalXor ^= value
```

If:

```text
value != 0
```

set:

```text
hasNonZero = true
```

Finally:

```text
if no non-zero element:
    return 0

if total XOR != 0:
    return n

otherwise:
    return n - 1
```

---

# 🔄 Flowchart

```text
                         START
                           │
                           ▼
                 totalXor = 0
                 hasNonZero = false
                           │
                           ▼
                   Traverse nums
                           │
                           ▼
                 totalXor ^= nums[i]
                           │
                           ▼
                 nums[i] != 0 ?
                    /          \
                  YES           NO
                   │             │
                   ▼             │
          hasNonZero = true      │
                   │             │
                   └──────┬──────┘
                          ▼
                   More elements?
                          │
                          ▼
                   Finish traversal
                          │
                          ▼
                hasNonZero == false?
                    /           \
                  YES            NO
                   │              │
                   ▼              ▼
               return 0    totalXor != 0?
                              /       \
                            YES        NO
                             │          │
                             ▼          ▼
                         return n   return n - 1
```

---

# 🧪 Dry Run

Input:

```text
nums = [1, 2, 3]
```

Initial:

```text
totalXor = 0
hasNonZero = false
```

### Process `1`

```text
totalXor = 0 ^ 1
          = 1

hasNonZero = true
```

### Process `2`

```text
totalXor = 1 ^ 2
          = 3
```

### Process `3`

```text
totalXor = 3 ^ 3
          = 0
```

Final state:

```text
totalXor = 0
hasNonZero = true
```

Therefore:

```text
answer = n - 1
       = 3 - 1
       = 2
```

One valid subsequence is:

```text
[1, 2]
```

whose XOR is:

```text
1 ^ 2 = 3
```

which is non-zero.

---

# 💻 Java — Approach 1

```java
class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;

        for (int val : nums) {
            totalXor ^= val;

            if (val != 0) {
                hasNonZero = true;
            }
        }

        if (!hasNonZero) {
            return 0;
        }

        if (totalXor != 0) {
            return nums.length;
        }

        return nums.length - 1;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}
```

---

# ⏱️ Complexity — Approach 1

### Time Complexity

```text
O(n)
```

We traverse the array exactly once.

### Space Complexity

```text
O(1)
```

Only two variables are used:

```text
totalXor
hasNonZero
```

---

# 🥈 Approach 2 — XOR + Non-Zero Count

## Idea

The second approach uses the same mathematical observation.

Instead of:

```text
boolean hasNonZero
```

we maintain:

```text
nonZeroCount
```

Every time we encounter a non-zero value:

```text
nonZeroCount++
```

At the end:

```text
nonZeroCount == 0
```

means that every element is zero.

Otherwise, at least one non-zero element exists.

---

# 🔄 Approach 2 Flowchart

```text
                    START
                      │
                      ▼
             xor = 0
             nonZeroCount = 0
                      │
                      ▼
                Traverse nums
                      │
                      ▼
                 xor ^= nums[i]
                      │
                      ▼
                nums[i] != 0?
                  /         \
                YES          NO
                 │            │
                 ▼            │
          nonZeroCount++      │
                 │            │
                 └─────┬──────┘
                       ▼
                Finish traversal
                       │
                       ▼
                  xor != 0?
                  /       \
                YES        NO
                 │          │
                 ▼          ▼
             return n   nonZeroCount == 0?
                           /       \
                         YES        NO
                          │          │
                          ▼          ▼
                      return 0   return n - 1
```

---

# 💻 Java — Approach 2

```java
class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        int nonZeroCount = 0;

        for (int num : nums) {
            xor ^= num;

            if (num != 0) {
                nonZeroCount++;
            }
        }

        if (xor != 0) {
            return nums.length;
        }

        if (nonZeroCount == 0) {
            return 0;
        }

        return nums.length - 1;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}
```

---

# ⏱️ Complexity — Approach 2

### Time Complexity

```text
O(n)
```

The array is traversed exactly once.

### Space Complexity

```text
O(1)
```

Only:

```text
xor
nonZeroCount
```

are maintained.

---

# 📊 Approach Comparison

| Feature           | Approach 1    | Approach 2  |
| ----------------- | ------------- | ----------- |
| Technique         | XOR + Boolean | XOR + Count |
| Time              | `O(n)`        | `O(n)`      |
| Space             | `O(1)`        | `O(1)`      |
| Extra Information | Boolean       | Count       |
| Simplicity        | ⭐⭐⭐⭐⭐    | ⭐⭐⭐⭐    |
| Recommended       | ✅ Best       | Second Best |

---

# 🏆 Why Approach 1 Is Better

Both solutions have exactly the same asymptotic complexity.

However, we only need to know:

```text
Does at least one non-zero element exist?
```

We do **not** need to know:

```text
How many non-zero elements exist?
```

Therefore:

```java
boolean hasNonZero
```

expresses the requirement more directly than:

```java
int nonZeroCount
```

So Approach 1 is the preferred implementation.

---

# 🧠 XOR Properties Used

## XOR With Zero

```text
x ^ 0 = x
```

---

## XOR With Itself

```text
x ^ x = 0
```

---

## XOR Is Reversible

If:

```text
a ^ b = c
```

then:

```text
c ^ b = a
```

These properties allow us to prove that when the total XOR is zero, removing any non-zero element produces a remaining XOR equal to that non-zero element.

---

# ⚠️ Common Mistake

Do not simply write:

```text
if totalXor == 0:
    return n - 1
```

because this fails for:

```text
[0, 0, 0]
```

Here:

```text
totalXor = 0
```

but there is no non-zero element to remove.

Therefore, the all-zero case must be handled separately.

---

# 🎯 Final Complexity

```text
┌────────────────────────────────────┐
│        Optimal Solution            │
├────────────────────────────────────┤
│ Time Complexity  : O(n)             │
│ Space Complexity : O(1)             │
│ Technique        : XOR Observation  │
└────────────────────────────────────┘
```

---

# ⭐ Key Takeaway

The entire problem can be reduced to:

```text
totalXor != 0
        │
        ▼
      answer = n
```

Otherwise:

```text
totalXor == 0
        │
        ▼
Are all elements zero?
      /       \
    YES        NO
     │          │
     ▼          ▼
answer = 0   answer = n - 1
```

The important lesson is:

> **Do not immediately treat a subsequence problem as a DP or backtracking problem. Look for mathematical properties that can characterize the optimal answer directly.**

---
