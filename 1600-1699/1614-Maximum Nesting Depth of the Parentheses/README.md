# 1614. Maximum Nesting Depth of the Parentheses

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

---

# 🧩 Problem Statement

Given a valid parentheses string `s` representing an arithmetic expression, return its **maximum nesting depth**.

The nesting depth is the maximum number of parentheses that are open at the same time around any part of the expression.

The expression can contain:

```text
digits:        0-9
operators:     + - * /
parentheses:   ( )
```

The official problem guarantees that `s` is a valid parentheses string and gives the constraint `1 <= s.length <= 100`. The official examples include `(1+(2*3)+((8)/4))+1 → 3`.

---

# 🎯 What Is the Problem Really Asking?

We do **not** need to evaluate the arithmetic expression.

For example, in:

```text
(1+(2*3)+((8)/4))+1
```

we do not care about:

```text
2 * 3
8 / 4
1 + ...
```

Only the parentheses matter.

The real question is:

> **What is the largest number of currently open `(` characters at any point in the string?**

So the problem becomes:

```text
'('  → enter one deeper level
')'  → leave one level

answer = maximum level reached
```

---

# 💡 Core Insight

Maintain a running variable:

```text
depth
```

It represents the number of currently unmatched opening parentheses.

For every character:

```text
'(' → depth++
')' → depth--
other → ignore
```

Every time we increase the depth, update:

```text
maxDepth = max(maxDepth, depth)
```

This gives the answer in one left-to-right scan.

---

# 📌 Important Invariant

After processing any prefix of the string:

```text
depth
```

is exactly:

```text
number of '(' seen
-
number of ')' seen
```

Because the input is guaranteed to be valid, `depth` never becomes negative and finishes at `0`.

Therefore, the maximum value ever reached by `depth` is exactly the maximum nesting depth.

---

# 🚀 Approach 1 — Depth Counter / Balance

## 💡 Idea

Use two variables:

```text
depth = current nesting depth
maxDepth = maximum nesting depth seen so far
```

Whenever an opening parenthesis appears:

```text
depth++
maxDepth = max(maxDepth, depth)
```

Whenever a closing parenthesis appears:

```text
depth--
```

Digits and operators are irrelevant.

---

## 🏗️ Approach 1 — Architecture Diagram

```text
                         INPUT STRING
                              │
                              ▼
                    ┌──────────────────┐
                    │ depth = 0        │
                    │ maxDepth = 0     │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │    Read s[i]     │
                    └────────┬─────────┘
                             │
                             ▼
                 ┌─────────────────────────┐
                 │       What is s[i]?     │
                 └───────┬──────┬──────────┘
                         │      │
                      '('      ')'
                         │      │
                         ▼      ▼
                   ┌────────┐ ┌────────┐
                   │depth++ │ │depth-- │
                   └───┬────┘ └───┬────┘
                       │          │
                       ▼          │
               ┌────────────────┐ │
               │ update         │ │
               │ maxDepth       │ │
               └───────┬────────┘ │
                       │          │
                       └────┬─────┘
                            │
                            ▼
                    Other character?
                            │
                            ▼
                          ignore
                            │
                            ▼
                     Next character
                            │
                            ▼
                  More characters?
                    /            \
                  Yes             No
                   │               │
                   └── repeat      ▼
                             return maxDepth
```

---

## 🔄 Approach 1 — Data Flow

```text
Input String
     │
     ▼
Read Character
     │
     ├── '(' ─────→ depth++
     │                 │
     │                 ▼
     │           update maxDepth
     │
     ├── ')' ─────→ depth--
     │
     └── other ───→ ignore
                         │
                         ▼
                    next character
                         │
                         ▼
                    final maxDepth
```

---

## 🔎 Why the Counter Is Enough

A stack is not necessary if we only want the **depth**, not the exact matching parenthesis positions.

Consider:

```text
((()))
```

The relevant information is only:

```text
(
 → 1
(
 → 2
(
 → 3
)
 → 2
)
 → 1
)
 → 0
```

We never need to know which specific `(` matches which `)`.

We only need the count of currently active openings.

That is why one integer is sufficient.

---

## 🧪 Approach 1 — Detailed Dry Run

Consider the official example:

```text
s = "(1+(2*3)+((8)/4))+1"
```

Track only parentheses:

```text
(  (  )  (  (  )  )  )
```

Now scan the original expression:

| Character | Action    | Depth | Max Depth |
| --------- | --------- | ----: | --------: |
| `(`       | `depth++` |     1 |         1 |
| `1`       | ignore    |     1 |         1 |
| `+`       | ignore    |     1 |         1 |
| `(`       | `depth++` |     2 |         2 |
| `2`       | ignore    |     2 |         2 |
| `*`       | ignore    |     2 |         2 |
| `3`       | ignore    |     2 |         2 |
| `)`       | `depth--` |     1 |         2 |
| `+`       | ignore    |     1 |         2 |
| `(`       | `depth++` |     2 |         2 |
| `(`       | `depth++` |     3 |         3 |
| `8`       | ignore    |     3 |         3 |
| `)`       | `depth--` |     2 |         3 |
| `/`       | ignore    |     2 |         3 |
| `4`       | ignore    |     2 |         3 |
| `)`       | `depth--` |     1 |         3 |
| `)`       | `depth--` |     0 |         3 |
| `+`       | ignore    |     0 |         3 |
| `1`       | ignore    |     0 |         3 |

Final:

```text
maxDepth = 3
```

The official explanation notes that the `8` is inside three nested parentheses.

---

## ✅ Approach 1 — Correctness Proof

We maintain the invariant:

> `depth` equals the number of currently open parentheses after processing the current prefix of the string.

### Case 1 — `'('`

One new parenthesis is opened.

Therefore:

```text
depth++
```

preserves the invariant.

### Case 2 — `')'`

One currently open parenthesis is closed.

Therefore:

```text
depth--
```

preserves the invariant.

### Case 3 — digit/operator

These characters do not affect nesting.

Therefore `depth` remains unchanged.

Since `maxDepth` records the largest value reached by `depth`, it is exactly the maximum nesting depth.

Therefore the algorithm is correct.

---

## 💻 Approach 1 — Java

```java
//Approach-1 (Depth Counter / Balance)
//T.C : O(n)
//S.C : O(1)

class Solution {
    public int maxDepth(String s) {
        int depth = 0;
        int maxDepth = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            }
            else if (ch == ')') {
                depth--;
            }
        }

        return maxDepth;
    }
}
```

---

## 💻 Approach 1 — C++

```cpp
//Approach-1 (Depth Counter / Balance)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:
    int maxDepth(string s) {
        int depth = 0;
        int maxDepth = 0;

        for (char ch : s) {
            if (ch == '(') {
                depth++;
                maxDepth = max(maxDepth, depth);
            }
            else if (ch == ')') {
                depth--;
            }
        }

        return maxDepth;
    }
};
```

---

## ⏱️ Approach 1 — Complexity

Every character is processed once.

```text
Time Complexity  : O(n)
Space Complexity : O(1)
```

This is the preferred approach because it is both linear and constant-space.

---

# ⚡ Approach 2 — Stack-Based Parentheses Tracking

## 💡 Idea

We can also explicitly store the currently open parentheses.

Use a stack:

```text
'(' → push
')' → pop
```

At any point:

```text
current depth = stack.size()
```

Whenever we push an opening parenthesis, update the maximum stack size.

This approach makes the nesting structure visually explicit, although the stack stores more information than necessary.

---

## 🏗️ Approach 2 — Architecture Diagram

```text
                         INPUT STRING
                              │
                              ▼
                    ┌──────────────────┐
                    │ Stack = empty    │
                    │ maxDepth = 0     │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │    Read s[i]     │
                    └────────┬─────────┘
                             │
                             ▼
                 ┌─────────────────────────┐
                 │       What is s[i]?     │
                 └───────┬──────┬──────────┘
                         │      │
                      '('      ')'
                         │      │
                         ▼      ▼
                 ┌──────────┐ ┌──────────┐
                 │ push '(' │ │   pop    │
                 └────┬─────┘ └────┬─────┘
                      │            │
                      ▼            │
              ┌────────────────┐   │
              │ depth =        │   │
              │ stack.size()   │   │
              └───────┬────────┘   │
                      │            │
                      ▼            │
              ┌────────────────┐   │
              │ update max     │   │
              └───────┬────────┘   │
                      │            │
                      └──────┬─────┘
                             │
                             ▼
                      Next character
                             │
                             ▼
                   More characters?
                    /            \
                  Yes             No
                   │               │
                   └── repeat      ▼
                             return maxDepth
```

---

## 🔄 Approach 2 — Data Flow

```text
Input Character
      │
      ├── '(' ─────→ push into stack
      │                 │
      │                 ▼
      │            stack.size()
      │                 │
      │                 ▼
      │            update max
      │
      ├── ')' ─────→ pop stack
      │
      └── other ───→ ignore
                         │
                         ▼
                    next character
```

---

## 🧪 Approach 2 — Detailed Dry Run

Consider:

```text
s = "()(())((()()))"
```

### First section

```text
(
stack size = 1

)
stack size = 0
```

Maximum so far:

```text
1
```

### Second section

```text
(
stack size = 1

(
stack size = 2

)
stack size = 1

)
stack size = 0
```

Maximum:

```text
2
```

### Final section

```text
(
stack size = 1

(
stack size = 2

(
stack size = 3

)
stack size = 2

)
stack size = 1

)
stack size = 0
```

Maximum:

```text
3
```

Therefore:

```text
answer = 3
```

which matches the official example.

---

## ✅ Approach 2 — Correctness Proof

The stack contains exactly the opening parentheses that have not yet been closed.

Therefore:

```text
stack.size()
```

is exactly the current nesting depth.

Every:

```text
(
```

adds one active level, while every:

```text
)
```

removes one active level.

Hence the largest stack size reached during the scan is the maximum nesting depth.

Therefore the algorithm is correct.

---

## 💻 Approach 2 — Java

```java
import java.util.*;

//Approach-2 (Stack-Based Parentheses Tracking)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
    public int maxDepth(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int maxDepth = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
                maxDepth = Math.max(maxDepth, stack.size());
            }
            else if (ch == ')') {
                stack.pop();
            }
        }

        return maxDepth;
    }
}
```

---

## 💻 Approach 2 — C++

```cpp
#include <bits/stdc++.h>
using namespace std;

//Approach-2 (Stack-Based Parentheses Tracking)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
public:
    int maxDepth(string s) {
        stack<char> st;
        int maxDepth = 0;

        for (char ch : s) {
            if (ch == '(') {
                st.push(ch);
                maxDepth = max(maxDepth, (int)st.size());
            }
            else if (ch == ')') {
                st.pop();
            }
        }

        return maxDepth;
    }
};
```

---

## ⏱️ Approach 2 — Complexity

Every character is processed once:

```text
Time Complexity  : O(n)
Space Complexity : O(n)
```

The stack may contain up to `n/2` opening parentheses in the worst case.

---

# 🆚 Approach 1 vs Approach 2

| Feature                      | Approach 1      | Approach 2       |
| ---------------------------- | --------------- | ---------------- |
| Main idea                    | Running balance | Explicit stack   |
| Time                         | `O(n)`          | `O(n)`           |
| Extra Space                  | `O(1)`          | `O(n)`           |
| Stores each open parenthesis | ❌              | ✅               |
| Current depth                | `depth`         | `stack.size()`   |
| Implementation               | Minimal         | More explicit    |
| Best performance             | ✅              | Good alternative |
| Best for learning            | ✅              | ✅               |

---

# 🧠 Why Approach 1 Is Better

Both approaches have the same time complexity:

```text
O(n)
```

But they use different amounts of memory.

### Counter

The counter stores only:

```text
depth
maxDepth
```

So:

```text
O(1) space
```

### Stack

The stack explicitly stores every currently open parenthesis.

For:

```text
((((((1))))))
```

the stack grows with nesting depth.

Therefore:

```text
O(n) space
```

For LeetCode 1614, the counter is clearly the cleaner and more memory-efficient solution.

---

# 🧮 Deeper Mathematical View — Prefix Balance

The problem can also be seen as a prefix-balance problem.

Map each parenthesis to a number:

```text
'(' → +1
')' → -1
```

Then the nesting depth after every character is the prefix sum.

Example:

```text
( ( ) ( ) )
```

becomes:

```text
+1 +1 -1 +1 -1 -1
```

Prefix balances:

```text
1
2
1
2
1
0
```

Therefore:

```text
maximum nesting depth = maximum prefix balance = 2
```

This is a powerful pattern to remember.

---

# 🎯 Pattern Recognition

Whenever you see:

```text
balanced parentheses
+
maximum nesting / depth
```

think immediately:

```text
'(' → +1
')' → -1
answer → maximum running balance
```

This pattern is useful in:

```text
Parentheses problems
Expression parsing
Nested blocks
Bracket depth
HTML/XML-like nesting
Recursive structures
```

---

# ⚠️ Common Mistakes

## 1. Counting total parentheses

For:

```text
()()()
```

the number of parentheses is `6`, but the maximum nesting depth is only:

```text
1
```

Depth measures **simultaneously open** parentheses.

---

## 2. Counting opening parentheses globally

For:

```text
()((()))
```

there are four opening parentheses, but the maximum depth is:

```text
3
```

because the first pair closes before the deeper group starts.

---

## 3. Evaluating the arithmetic expression

Digits and operators are irrelevant.

You do not need to calculate:

```text
1 + 2 * 3
```

Only parentheses matter.

---

## 4. Updating maximum at the wrong time

The safest point to update is immediately after opening:

```text
depth++
maxDepth = max(maxDepth, depth)
```

---

## 5. Using a stack when only a count is required

A stack is valid, but if the problem asks only for depth, storing the actual parentheses is unnecessary.

---

# 🗣️ Interview Explanation

A strong interview answer:

> "We don't need to evaluate the arithmetic expression. We only care about the parentheses. I maintain a current depth. Every opening parenthesis increases the depth by one, and every closing parenthesis decreases it by one. After every opening parenthesis I update the maximum depth. Since the input is guaranteed to be a valid parentheses expression, the maximum value reached by this balance is the required nesting depth. This runs in O(n) time and O(1) extra space."

---

# 🧾 Quick Revision

## Approach 1 — Counter

```text
depth = 0
maxDepth = 0

for each character:

    '(':
        depth++
        maxDepth = max(maxDepth, depth)

    ')':
        depth--

    other:
        ignore

return maxDepth
```

## Approach 2 — Stack

```text
'(':
    push
    maxDepth = max(maxDepth, stack.size())

')':
    pop

return maxDepth
```

---

# 🧪 More Examples

## Example 1

```text
s = "(1+(2*3)+((8)/4))+1"
```

Maximum active parentheses:

```text
3
```

Answer:

```text
3
```

## Example 2

```text
s = "(1)+((2))+(((3)))"
```

Depths reached:

```text
1
2
3
```

Answer:

```text
3
```

## Example 3

```text
s = "()(())((()()))"
```

Maximum nesting:

```text
3
```

Answer:

```text
3
```

These correspond to the official examples.

---

# ⭐ One-Line Insight

> **Maximum nesting depth is simply the maximum number of unmatched opening parentheses at any point in the string.**

---

# 📊 Complexity Summary

```text
Approach 1 — Counter
Time  : O(n)
Space : O(1)

Approach 2 — Stack
Time  : O(n)
Space : O(n)
```

---

# 🏷️ Tags

`String, Stack, Parentheses, Math, Simulation, String Parsing, Balance, Prefix Sum, Counting, Linear Scan, LeetCode, Easy`

---

# 📚 Final Takeaway

The complete problem can be reduced to one simple rule:

```text
'(' → depth + 1
')' → depth - 1
```

and then:

```text
answer = maximum depth reached
```

The whole algorithm:

```text
                 INPUT STRING
                      │
                      ▼
                Read character
                      │
          ┌───────────┼───────────┐
          │           │           │
          ▼           ▼           ▼
         '('         ')'        other
          │           │           │
          ▼           ▼           ▼
       depth++      depth--      ignore
          │
          ▼
    update maximum
          │
          ▼
      next character
          │
          ▼
    return maxDepth
```

The most important pattern is:

```text
Balanced Parentheses
        ↓
Running Balance
        ↓
Maximum Balance
        ↓
Maximum Nesting Depth
```

For LeetCode 1614, the **counter solution is the preferred implementation** because it achieves `O(n)` time with only `O(1)` extra space.

---

## 🔗 Problem Reference

[LeetCode 1614. Maximum Nesting Depth of the Parentheses](https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/)
