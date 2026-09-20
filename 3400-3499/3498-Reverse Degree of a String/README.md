# 3498. Reverse Degree of a String

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

# 🧩 Problem Statement

Given a string `s`, calculate its **reverse degree**.

For every character:

- `a -> 26`
- `b -> 25`
- `c -> 24`
- ...
- `y -> 2`
- `z -> 1`

Multiply the reverse-alphabet value by the character's **1-based position in the string**, then add all products.

For index `i` (0-based):

```text
reverseValue = 26 - (s[i] - 'a')
position      = i + 1

contribution  = reverseValue * position
```

Therefore:

```text
answer =
Σ ((i + 1) * (26 - (s[i] - 'a')))
```

[LeetCode 3498 — Reverse Degree of a String](https://leetcode.com/problems/reverse-degree-of-a-string/)

---

# 💡 What Is the Question Really Asking?

This is a **weighted sum** problem.

Every character has a fixed score from the reversed alphabet:

```text
a  b  c  ...  y  z
26 25 24 ...  2  1
```

and every position gives that score a weight:

```text
1st character  -> ×1
2nd character  -> ×2
3rd character  -> ×3
...
```

So the entire problem is:

```text
Character
   ↓
Reverse alphabet value
   ↓
Multiply by position
   ↓
Add to answer
```

The official constraints are:

```text
1 <= s.length <= 1000
s contains only lowercase English letters
```

---

# 🏗️ Solution Architecture

```text
                         String s
                            |
                            v
                  +-------------------+
                  | Traverse each     |
                  | character         |
                  +---------+---------+
                            |
                            v
                +-----------------------+
                | Reverse alphabet      |
                | value                 |
                | 26 - (c - 'a')        |
                +-----------+-----------+
                            |
                            v
                +-----------------------+
                | 1-based position      |
                | i + 1                 |
                +-----------+-----------+
                            |
                            v
                +-----------------------+
                | contribution =        |
                | value × position      |
                +-----------+-----------+
                            |
                            v
                     Add to answer
                            |
                            v
                        Return
```

---

# 🔍 Core Observation

For a lowercase character `c`:

```text
normal index = c - 'a'
```

Examples:

```text
a -> 0
b -> 1
c -> 2
...
z -> 25
```

The reverse alphabet value is:

```text
26 - normal index
```

Therefore:

```text
reverseValue = 26 - (c - 'a')
```

Equivalent formula:

```text
reverseValue = 'z' - c + 1
```

Both produce:

```text
a -> 26
b -> 25
...
z -> 1
```

---

# 1️⃣ Approach 1 — Direct Character Arithmetic

For every character:

```text
reverseValue = 26 - (s[i] - 'a')
```

The problem uses a **1-based** position:

```text
i + 1
```

So:

```text
answer += reverseValue * (i + 1)
```

---

## 🔄 Approach 1 Flowchart

```text
                  Start
                    |
                    v
              answer = 0
                    |
                    v
             i = 0 ... n-1
                    |
                    v
        reverseValue = 26 - (s[i]-'a')
                    |
                    v
          contribution =
          reverseValue × (i+1)
                    |
                    v
            answer += contribution
                    |
                    v
                  Return
```

---

## ✅ Java

```java
//Approach-1 (Direct Character Arithmetic)
//T.C : O(n)
//S.C : O(1)

class Solution {
    public int reverseDegree(String s) {

        int answer = 0;

        for (int i = 0; i < s.length(); i++) {

            int reverseValue =
                26 - (s.charAt(i) - 'a');

            answer += reverseValue * (i + 1);
        }

        return answer;
    }
}
```

---

## ✅ C++

```cpp
//Approach-1 (Direct Character Arithmetic)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:
    int reverseDegree(string s) {

        int answer = 0;

        for (int i = 0; i < s.size(); i++) {

            int reverseValue =
                26 - (s[i] - 'a');

            answer += reverseValue * (i + 1);
        }

        return answer;
    }
};
```

---

# 2️⃣ Approach 2 — Reverse Alphabet Formula

Instead of starting from the normal alphabet index, directly measure the distance from `z`:

```text
reverseValue = 'z' - c + 1
```

For example:

```text
c = 'a'

'z' - 'a' + 1
= 25 + 1
= 26
```

and:

```text
c = 'z'

'z' - 'z' + 1
= 1
```

This is mathematically identical to Approach 1.

---

## ✅ Java

```java
//Approach-2 (Reverse Alphabet Value)
//T.C : O(n)
//S.C : O(1)

class Solution2 {
    public int reverseDegree(String s) {

        int answer = 0;

        for (int i = 0; i < s.length(); i++) {

            int reverseValue =
                'z' - s.charAt(i) + 1;

            answer += reverseValue * (i + 1);
        }

        return answer;
    }
}
```

---

## ✅ C++

```cpp
//Approach-2 (Reverse Alphabet Value)
//T.C : O(n)
//S.C : O(1)

class Solution2 {
public:
    int reverseDegree(string s) {

        int answer = 0;

        for (int i = 0; i < s.size(); i++) {

            int reverseValue =
                'z' - s[i] + 1;

            answer += reverseValue * (i + 1);
        }

        return answer;
    }
};
```

---

# ⚖️ Approach Comparison

| Feature              | Approach 1            | Approach 2        |
| -------------------- | --------------------- | ----------------- |
| Formula              | `26 - (c-'a')`        | `'z' - c + 1`     |
| Time                 | `O(n)`                | `O(n)`            |
| Space                | `O(1)`                | `O(1)`            |
| Extra data structure | None                  | None              |
| Main idea            | Normal alphabet index | Distance from `z` |

Both are exactly equivalent.

---

# 🧪 Detailed Dry Run — `s = "abc"`

Reverse alphabet values:

```text
a -> 26
b -> 25
c -> 24
```

Positions are 1-based:

```text
a -> 1
b -> 2
c -> 3
```

Now calculate:

```text
a: 26 × 1 = 26
b: 25 × 2 = 50
c: 24 × 3 = 72
```

Therefore:

```text
answer = 26 + 50 + 72
       = 148
```

So:

```text
Output = 148
```

---

# 🧪 Detailed Dry Run — `s = "zaza"`

Reverse values:

```text
z -> 1
a -> 26
z -> 1
a -> 26
```

Positions:

```text
1, 2, 3, 4
```

Contributions:

```text
z: 1  × 1 = 1
a: 26 × 2 = 52
z: 1  × 3 = 3
a: 26 × 4 = 104
```

Therefore:

```text
answer = 1 + 52 + 3 + 104
       = 160
```

So:

```text
Output = 160
```

---

# 🧠 Important Detail — 1-Based Position

Java and C++ use 0-based indexing:

```text
index:     0  1  2
```

But the problem uses 1-based position:

```text
position:  1  2  3
```

Therefore:

```text
position = i + 1
```

This is one of the easiest places to make an off-by-one error.

---

# 🔥 Why the Reverse Value Formula Works

Normal alphabet positions are:

```text
a = 1
b = 2
...
z = 26
```

Reverse positions should satisfy:

```text
reverse = 27 - normal
```

because:

```text
a: 27 - 1  = 26
b: 27 - 2  = 25
...
z: 27 - 26 = 1
```

Since:

```text
normal = c - 'a' + 1
```

we get:

```text
reverse
= 27 - (c - 'a' + 1)
= 26 - (c - 'a')
```

That is the exact formula used.

---

# 🎯 Interview Thought Process

```text
Need reverse degree
        ↓
Each character contributes:
reverseAlphabetValue × position
        ↓
Reverse alphabet value?
        ↓
a=26, ..., z=1
        ↓
26 - (c-'a')
        ↓
String position is 1-based
        ↓
i+1
        ↓
Add contribution
        ↓
Return total
```

---

# 🧩 Pattern Recognition

This is a simple:

```text
String Traversal
+
Character Mapping
+
Weighted Sum
```

Whenever a problem says:

```text
For every character:
value(character) × position
```

think:

```text
One linear scan
```

No advanced data structure is required.

---

# 🔥 Deep Mental Model

Think of each character as a score:

```text
a → 26
b → 25
c → 24
...
z → 1
```

Then the position is its multiplier:

```text
1st → ×1
2nd → ×2
3rd → ×3
...
```

So:

```text
Reverse Degree
=
Weighted Sum of Character Scores
```

---

# 📌 Formula Sheet

### Reverse alphabet value

```text
reverseValue = 26 - (c - 'a')
```

Equivalent:

```text
reverseValue = 'z' - c + 1
```

### Position

```text
position = i + 1
```

### Contribution

```text
contribution =
(i + 1) * (26 - (s[i] - 'a'))
```

### Final Answer

```text
answer =
Σ [(i + 1) * (26 - (s[i] - 'a'))]
```

---

# ⚠️ Common Mistakes

## 1. Using normal alphabet values

Wrong:

```text
a = 1
b = 2
...
z = 26
```

Correct:

```text
a = 26
b = 25
...
z = 1
```

---

## 2. Using `i` instead of `i + 1`

The problem uses 1-indexed string positions.

Correct:

```text
i + 1
```

---

## 3. Actually reversing the string

The word "reverse" refers to the **alphabet mapping**.

For:

```text
"abc"
```

we do not change it to:

```text
"cba"
```

The string remains:

```text
abc
```

Only the character values become:

```text
26, 25, 24
```

---

## 4. Using a HashMap unnecessarily

The mapping can be calculated directly using character arithmetic.

No map is required.

---

# 🧪 Edge Cases

### Single `a`

```text
26 × 1 = 26
```

### Single `z`

```text
1 × 1 = 1
```

### Repeated characters

For:

```text
s = "aaaa"
```

the reverse value is always `26`, but the position changes:

```text
26×1 + 26×2 + 26×3 + 26×4
```

### Maximum length

With:

```text
n <= 1000
```

a single linear scan is easily sufficient.

---

# 🚀 Why No DP or Greedy Is Needed

Each character's contribution depends only on:

```text
character
+
its position
```

Characters do not depend on one another.

Therefore:

```text
answer = sum of independent contributions
```

A single pass is enough.

---

# 📊 Complexity Analysis

Let:

```text
n = s.length()
```

Every character is visited exactly once.

### Time

```text
O(n)
```

### Space

Only constant-size variables are used:

```text
O(1)
```

Therefore:

```text
Time  : O(n)
Space : O(1)
```

---

# 🏆 Why This Is Optimal

We must inspect the string to account for each character's contribution.

Therefore an `O(n)` scan is necessary in the general case.

Our solution performs exactly one such scan and uses constant extra memory.

So:

```text
O(n) time
O(1) space
```

is optimal.

---

# 📝 Final Summary

The problem is a weighted-sum calculation.

For every position `i`:

```text
reverseValue = 26 - (s[i] - 'a')
position      = i + 1
```

Then:

```text
answer += reverseValue × position
```

The complete formula is:

```text
answer =
Σ [(i + 1) × (26 - (s[i] - 'a'))]
```

No sorting, DP, hash table, or advanced data structure is required.

---

# 💎 One-Line Insight

> **Map `a → 26` through `z → 1`, multiply each value by its 1-based position, and sum all contributions.**

---

# 🧠 Interview Cheat Sheet

```text
String
  ↓
For each i
  ↓
reverse = 26 - (s[i] - 'a')
  ↓
position = i + 1
  ↓
answer += reverse × position
  ↓
Return answer

Complexity:
O(n) time
O(1) space
```

---

## 🏷️ Tags

`String, Math, Character Mapping, Counting, Simulation, ASCII, Weighted Sum, String Traversal, LeetCode, Easy`
