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

# 338. Counting Bits

## Tags

```text
Dynamic Programming
Bit Manipulation
Array
Math
Java
C++
```

---

# Intuition

For every integer,

we need to count the number of

```text
Set Bits (1's)
```

in its binary representation.

Calculating the count independently for every number would repeat a lot of work.

Instead,

we can reuse the answer of a

```text
Smaller Number.
```

---

# Key Observation

For any integer

```text
i
```

removing its last binary bit is equivalent to

```text
i >> 1
```

The least significant bit contributes

```text
(i & 1)
```

Therefore,

```text
countBits(i)

=

countBits(i >> 1)

+

(i & 1)
```

This relation allows us to compute answers incrementally.

---

# Approaches

1. Dynamic Programming + Bit Manipulation (Optimal)

---

# Approach 1 — Dynamic Programming + Bit Manipulation

## Idea

Create an array

```text
ans[]
```

where

```text
ans[i]
```

stores the number of set bits in

```text
i.
```

For every number,

reuse the previously computed answer of

```text
i >> 1
```

and add

```text
1
```

only if the last bit is set.

---

# Algorithm

### Step 1

Create an array

```text
ans

of size n + 1.
```

---

### Step 2

Initialize

```text
ans[0] = 0.
```

---

### Step 3

Traverse from

```text
1

to

n.
```

---

### Step 4

Compute

```text
ans[i]

=

ans[i >> 1]

+

(i & 1)
```

---

### Step 5

Return the answer array.

---

# Flowchart

```text
             Start

               │

               ▼

      Create Answer Array

               │

               ▼

     Traverse i = 1 to n

               │

               ▼

 ans[i] = ans[i >> 1]

       + (i & 1)

               │

               ▼

 Continue Until n

               │

               ▼

     Return Answer Array
```

---

# Example

Input

```text
n = 5
```

Output

```text
[0,1,1,2,1,2]
```

---

# Dry Run

Input

```text
n = 5
```

```text
ans[0] = 0

ans[1] = ans[0] + 1 = 1

ans[2] = ans[1] + 0 = 1

ans[3] = ans[1] + 1 = 2

ans[4] = ans[2] + 0 = 1

ans[5] = ans[2] + 1 = 2
```

Final Answer

```text
[0,1,1,2,1,2]
```

---

# Memory Visualization

```text
         i

         │

         ▼

      i >> 1

         │

         ▼

 Previously Computed Answer

         │

         ▼

 Add Last Bit

         │

         ▼

     ans[i]
```

---

# Why Dynamic Programming Works?

Every number can be reduced to

```text
i >> 1
```

whose answer has already been computed.

The only additional contribution comes from the

```text
Least Significant Bit.
```

Thus,

each value is computed exactly once,

giving an optimal linear-time solution.

---

# Complexity Analysis

## Approach 1 — Dynamic Programming + Bit Manipulation

### Time Complexity

```text
O(n)
```

Each number is processed once.

---

### Space Complexity

```text
O(n)
```

The answer array stores

```text
n + 1
```

values.

---

# Java Solution

## Approach 1 — Dynamic Programming + Bit Manipulation (Optimal)

```java
//Approach-1 (Dynamic Programming + Bit Manipulation)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int[] countBits(
        int n
    ) {

        int[] answer =
            new int[n + 1];

        for (
            int i = 1;
            i <= n;
            i++
        ) {

            answer[i] =
                answer[i >> 1] +
                (i & 1);
        }

        return answer;
    }
}
```

---

# C++ Solution

## Approach 1 — Dynamic Programming + Bit Manipulation (Optimal)

```cpp
//Approach-1 (Dynamic Programming + Bit Manipulation)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    vector<int> countBits(
        int n
    ) {

        vector<int> answer(
            n + 1,
            0
        );

        for (
            int i = 1;
            i <= n;
            i++
        ) {

            answer[i] =
                answer[i >> 1] +
                (i & 1);
        }

        return answer;
    }
};
```

---

# Complexity Comparison

| Approach                               | Algorithm |   Time   |  Space   |
| :------------------------------------- | :-------- | :------: | :------: |
| Dynamic Programming + Bit Manipulation | DP        | **O(n)** | **O(n)** |

---

# Final Complexity

```text
Approach 1 (Dynamic Programming + Bit Manipulation)

Time Complexity  : O(n)

Space Complexity : O(n)
```

---

# Conclusion

- ✅ Use **Dynamic Programming** to reuse previously computed answers.
- ✅ Observe that `i >> 1` removes the least significant bit.
- ✅ `(i & 1)` determines whether the current number contributes one additional set bit.
- ✅ Compute each value using the recurrence `ans[i] = ans[i >> 1] + (i & 1)`.
- ✅ Every number is processed exactly once, resulting in an efficient **O(n)** solution.
