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

# 1081. Smallest Subsequence of Distinct Characters

## Tags

```text
Greedy
Stack
Monotonic Stack
String
Hashing
Array
Java
C++
```

---

# Intuition

We need to construct the

```text
Lexicographically Smallest
```

subsequence that contains

```text
Every Distinct Character

Exactly Once.
```

Whenever we encounter a character,

we decide whether it should appear earlier than characters already chosen.

If a larger character can still appear later,

we remove it to obtain a smaller lexicographical order.

---

# Key Observation

For every character,

we need to know

```text
Its Last Occurrence
```

If the current character is smaller than the top of the stack,

and the top character appears again later,

we can safely remove it.

Thus,

a

```text
Monotonic Increasing Stack
```

produces the optimal answer.

---

# Approaches

1. Greedy + Monotonic Stack (Optimal)

---

# Approach 1 — Greedy + Monotonic Stack

## Idea

Maintain a stack containing the current subsequence.

Before inserting a character,

remove larger characters from the top of the stack

only if

```text
They Appear Again Later.
```

Also maintain a

```text
Visited Array
```

to ensure every character is added only once.

---

# Algorithm

### Step 1

Store

```text
Last Occurrence

of every character.
```

---

### Step 2

Traverse the string.

---

### Step 3

If the current character already exists in the stack,

```text
Skip
```

it.

---

### Step 4

Otherwise,

while

```text
Stack is not empty

AND

Top Character > Current Character

AND

Top Character Appears Again Later
```

remove the top character.

---

### Step 5

Insert the current character into the stack.

---

### Step 6

Mark it as visited.

---

### Step 7

Return the stack as the answer.

---

# Flowchart

```text
              Start

                │

                ▼

      Compute Last Index

                │

                ▼

      Traverse Characters

                │

                ▼

Already In Stack ?

      ┌─────────┴─────────┐

     Yes                 No

      │                   │

      ▼                   ▼

    Ignore         Pop Larger Characters

                          │

                          ▼

                    Push Character

                          │

                          ▼

                  Continue Traversal

                          │

                          ▼

                  Return Stack
```

---

# Example

Input

```text
s = "bcabc"
```

Processing

```text
b

↓

bc

↓

a

(pop c)

(pop b)

↓

a

↓

ab

↓

abc
```

Answer

```text
"abc"
```

---

# Dry Run

Input

```text
"cbacdcbc"
```

Last Occurrence

```text
c → 7

b → 6

a → 2

d → 4
```

Processing

```text
c

↓

b

(pop c)

↓

a

(pop b)

↓

ac

↓

acd

↓

acdb
```

Final Answer

```text
"acdb"
```

---

# Memory Visualization

```text
Input String

      │

      ▼

Last Occurrence

      │

      ▼

Visited Array

      │

      ▼

Monotonic Stack

      │

      ▼

Smallest Subsequence
```

---

# Why Greedy + Stack Works?

Whenever we encounter a smaller character,

placing it earlier always improves the lexicographical order.

A larger character is removed only if it appears again later,

ensuring it can still be included.

Thus,

every character is pushed and popped at most once,

making the solution both correct and efficient.

---

# Complexity Analysis

## Approach 1 — Greedy + Monotonic Stack

### Time Complexity

```text
O(n)
```

Each character is pushed and popped at most once.

---

### Space Complexity

```text
O(1)
```

Only arrays of size

```text
26
```

and the stack are used.

---

# Java Solution

## Approach 1 — Greedy + Monotonic Stack (Optimal)

```java
//Approach-1 (Greedy + Monotonic Stack)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public String smallestSubsequence(
        String s
    ) {

        int[] lastIndex =
            new int[26];

        boolean[] inStack =
            new boolean[26];

        StringBuilder stack =
            new StringBuilder();

        for (
            int i = 0;
            i < s.length();
            i++
        ) {

            lastIndex[
                s.charAt(i) - 'a'
            ] = i;
        }

        for (
            int i = 0;
            i < s.length();
            i++
        ) {

            int current =
                s.charAt(i) - 'a';

            if (
                inStack[current]
            ) {

                continue;
            }

            while (
                stack.length() > 0
            ) {

                int top =
                    stack.charAt(
                        stack.length() - 1
                    ) - 'a';

                if (
                    top > current &&
                    lastIndex[top] > i
                ) {

                    stack.deleteCharAt(
                        stack.length() - 1
                    );

                    inStack[top] =
                        false;

                } else {

                    break;
                }
            }

            stack.append(
                (char) ('a' + current)
            );

            inStack[current] =
                true;
        }

        return stack.toString();
    }
}
```

---

# C++ Solution

## Approach 1 — Greedy + Monotonic Stack (Optimal)

```cpp
//Approach-1 (Greedy + Monotonic Stack)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:

    string smallestSubsequence(
        string s
    ) {

        vector<int> lastIndex(
            26
        );

        vector<bool> inStack(
            26,
            false
        );

        string stack = "";

        for (
            int i = 0;
            i < s.size();
            i++
        ) {

            lastIndex[
                s[i] - 'a'
            ] = i;
        }

        for (
            int i = 0;
            i < s.size();
            i++
        ) {

            int current =
                s[i] - 'a';

            if (
                inStack[current]
            ) {

                continue;
            }

            while (
                !stack.empty()
            ) {

                int top =
                    stack.back() - 'a';

                if (
                    top > current &&
                    lastIndex[top] > i
                ) {

                    inStack[top] =
                        false;

                    stack.pop_back();

                } else {

                    break;
                }
            }

            stack.push_back(
                s[i]
            );

            inStack[current] =
                true;
        }

        return stack;
    }
};
```

---

# Complexity Comparison

| Approach                 | Algorithm      |   Time   |  Space   |
| :----------------------- | :------------- | :------: | :------: |
| Greedy + Monotonic Stack | Greedy + Stack | **O(n)** | **O(1)** |

---

# Final Complexity

```text
Approach 1 (Greedy + Monotonic Stack)

Time Complexity  : O(n)

Space Complexity : O(1)
```

---

# Conclusion

- ✅ Store the **last occurrence** of every character.
- ✅ Maintain a **monotonic increasing stack** to build the answer.
- ✅ Remove larger characters only if they appear again later.
- ✅ Ensure each distinct character is included exactly once using a **visited array**.
- ✅ Every character is pushed and popped at most once, giving an efficient **O(n)** solution.
