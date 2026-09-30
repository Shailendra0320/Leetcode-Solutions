# 1111. Maximum Nesting Depth of Two Valid Parentheses Strings

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

---

# 🧩 Problem Statement

You are given a valid parentheses string `seq`.

Split its characters into two **disjoint subsequences** `A` and `B` such that:

```text
A is a valid parentheses string
B is a valid parentheses string
```

Every character of `seq` must belong to exactly one of the two subsequences.

Return an array `answer` where:

```text
answer[i] = 0
```

means `seq[i]` belongs to `A`, and:

```text
answer[i] = 1
```

means `seq[i]` belongs to `B`.

Among all valid splits, minimize:

```text
max(depth(A), depth(B))
```

The official problem allows any optimal split because multiple answers can exist. The constraint is `1 <= seq.length <= 10000`, and `seq` is guaranteed to be a valid parentheses string. citeturn408031search0

---

# 🎯 What Is the Problem Really Asking?

The first instinct may be:

> "How do I choose which parenthesis goes into group `0` or group `1`?"

There can be many possible assignments.

The better question is:

> **How can we distribute the nesting levels between the two groups as evenly as possible?**

Consider:

```text
(((())))
```

The nesting levels are:

```text
( → level 1
( → level 2
( → level 3
( → level 4
) → level 4
) → level 3
) → level 2
) → level 1
```

Instead of putting all four levels into one group:

```text
Group 0 → 4
Group 1 → 0
```

we alternate:

```text
Level 1 → Group 1
Level 2 → Group 0
Level 3 → Group 1
Level 4 → Group 0
```

Now both groups have depth only `2`.

That is the central idea.

---

# 💡 Core Insight

A matching pair of parentheses always belongs to the same nesting level.

Therefore, if we assign a group based on the nesting depth:

```text
group = depth % 2
```

then both parentheses of a matching pair get the same group.

At the same time, nested levels alternate:

```text
1 → group 1
2 → group 0
3 → group 1
4 → group 0
...
```

This balances the maximum nesting depth between the two groups.

---

# 🧠 Valid Parentheses and Nesting Depth

A valid parentheses string can be viewed as a sequence of opening and closing operations:

```text
'(' → depth + 1
')' → depth - 1
```

For example:

```text
(()())
```

has depth evolution:

```text
( → 1
( → 2
) → 1
( → 2
) → 1
) → 0
```

The maximum depth is:

```text
2
```

For this problem, we use those nesting levels to decide which group receives each character.

---

# 🚀 Approach 1 — Nesting Depth Parity

## 💡 Idea

Maintain:

```text
depth
```

and process `seq` from left to right.

### For an opening parenthesis

First enter the new level:

```text
depth++
```

Then choose:

```text
answer[i] = depth % 2
```

### For a closing parenthesis

It closes the current level, so before decreasing `depth`, assign it to:

```text
answer[i] = depth % 2
```

Then:

```text
depth--
```

So the rules are:

```text
'(':
    depth++
    answer[i] = depth % 2

')':
    answer[i] = depth % 2
    depth--
```

---

## 🏗️ Approach 1 — Architecture Diagram

```text
                         seq
                          │
                          ▼
                 ┌──────────────────┐
                 │ depth = 0        │
                 │ answer[]         │
                 └────────┬─────────┘
                          │
                          ▼
                 ┌──────────────────┐
                 │   Read seq[i]    │
                 └────────┬─────────┘
                          │
                    ┌─────┴─────┐
                    │           │
                  '('          ')'
                    │           │
                    ▼           ▼
               depth++      current depth
                    │           │
                    └─────┬─────┘
                          ▼
                 group = depth % 2
                          │
                          ▼
                  answer[i] = group
                          │
                          ▼
                    seq[i] == ')'
                          │
                         Yes
                          ▼
                       depth--
                          │
                          ▼
                    Next character
                          │
                          ▼
                       Finish
```

---

## 🔄 Approach 1 — Data Flow

```text
Current Parenthesis
        │
        ▼
Determine nesting level
        │
        ▼
depth % 2
        │
        ▼
Choose Group 0 / Group 1
        │
        ▼
Store answer[i]
        │
        └── if ')' → decrease depth
```

---

## 🧪 Approach 1 — Detailed Dry Run

Take:

```text
seq = "(()())"
```

### Index 0

```text
seq[0] = '('

depth = 1
group = 1 % 2 = 1

answer[0] = 1
```

### Index 1

```text
seq[1] = '('

depth = 2
group = 2 % 2 = 0

answer[1] = 0
```

### Index 2

```text
seq[2] = ')'

current depth = 2
group = 2 % 2 = 0

answer[2] = 0
depth = 1
```

### Index 3

```text
seq[3] = '('

depth = 2
group = 0

answer[3] = 0
```

### Index 4

```text
seq[4] = ')'

current depth = 2
group = 0

answer[4] = 0
depth = 1
```

### Index 5

```text
seq[5] = ')'

current depth = 1
group = 1

answer[5] = 1
depth = 0
```

Final:

```text
answer = [1,0,0,0,0,1]
```

The two resulting subsequences are:

```text
Group 0 → "(())"
Group 1 → "()"
```

Both are valid parentheses strings.

Their depths are:

```text
depth(Group 0) = 2
depth(Group 1) = 1
```

and the maximum is:

```text
2
```

The original depth is also `2`, so this is optimal.

---

# 🔍 Approach 1 — Why Matching Parentheses Stay Together

Suppose a pair is:

```text
(...)
```

If its opening parenthesis starts at depth:

```text
d
```

then the matching closing parenthesis closes exactly that same nesting level.

Therefore both receive:

```text
d % 2
```

So a complete pair is never split between the two groups.

This guarantees each group's parentheses remain balanced.

---

# 🧮 Approach 1 — Why the Split Is Optimal

Let the maximum nesting depth of the original `seq` be:

```text
D
```

Imagine the deepest chain of `D` nested pairs.

Those `D` levels have to be distributed among only two groups.

By the pigeonhole principle, one group must contain at least:

```text
ceil(D / 2)
```

of those nested levels.

Therefore no solution can make:

```text
max(depth(A), depth(B))
```

smaller than:

```text
ceil(D / 2)
```

Our parity assignment gives:

```text
odd levels  → Group 1
even levels → Group 0
```

So the deepest group receives at most:

```text
ceil(D / 2)
```

levels.

Thus our assignment reaches the theoretical lower bound.

Therefore it is optimal.

---

# ✅ Approach 1 — Correctness Proof

We prove three properties.

### Property 1 — Every character receives exactly one group

Every position gets:

```text
answer[i] = 0 or 1
```

so every character belongs to exactly one subsequence.

### Property 2 — Both subsequences are valid VPS

For every opening parenthesis, we assign a group based on its nesting level.

Its matching closing parenthesis is at the same level, so it receives the same group.

Thus complete matching pairs remain together in one group.

Therefore both subsequences remain balanced.

### Property 3 — Maximum depth is minimized

If the original depth is `D`, any two-group split must give one group at least `ceil(D/2)` nested levels.

Parity assignment distributes odd and even levels between the two groups, so no group receives more than `ceil(D/2)` levels.

Therefore the split is optimal.

---

# 💻 Approach 1 — Java

```java
//Approach-1 (Nesting Depth Parity)
//T.C : O(n)
//S.C : O(1) auxiliary

class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];

        int depth = 0;

        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                depth++;
                answer[i] = depth % 2;
            }
            else {
                answer[i] = depth % 2;
                depth--;
            }
        }

        return answer;
    }
}
```

---

# 💻 Approach 1 — C++

```cpp
//Approach-1 (Nesting Depth Parity)
//T.C : O(n)
//S.C : O(1) auxiliary

class Solution {
public:
    vector<int> maxDepthAfterSplit(string seq) {
        int n = seq.size();
        vector<int> answer(n);

        int depth = 0;

        for (int i = 0; i < n; i++) {
            if (seq[i] == '(') {
                depth++;
                answer[i] = depth % 2;
            }
            else {
                answer[i] = depth % 2;
                depth--;
            }
        }

        return answer;
    }
};
```

---

## ⏱️ Approach 1 — Complexity

Every character is processed once.

```text
Time Complexity  : O(n)
Space Complexity : O(1) auxiliary
```

The returned answer array itself requires `O(n)` output space.

---

# ⚡ Approach 2 — Stack-Based Matching-Pair Assignment

## 💡 Idea

The first approach uses only the nesting depth.

The second approach makes the **matching pair relationship explicit**.

When we encounter:

```text
(
```

we choose its group according to its nesting level and push that group onto a stack.

When we encounter:

```text
)
```

we pop the group belonging to its matching opening parenthesis and assign the same group.

This makes the pair relationship very easy to visualize.

---

# 🏗️ Approach 2 — Architecture Diagram

```text
                         seq
                          │
                          ▼
                ┌───────────────────┐
                │ stack = []        │
                │ answer[]          │
                └─────────┬─────────┘
                          │
                          ▼
                   Read seq[i]
                          │
                    ┌─────┴─────┐
                    │           │
                  '('          ')'
                    │           │
                    ▼           ▼
            level = stack.size()+1
                    │        group = stack.pop()
                    ▼              │
             group = level % 2     │
                    │              │
                    └──────┬───────┘
                           ▼
                    answer[i] = group
                           │
                           ▼
                       Next char
                           │
                           ▼
                         Done
```

---

## 🔄 Approach 2 — Data Flow

```text
Opening '('
      │
      ▼
Current Nesting Level
      │
      ▼
level % 2
      │
      ▼
Choose Group
      │
      ├────────→ answer[i]
      │
      ▼
   push group
      │
      ▼
Matching ')'
      │
      ▼
   pop group
      │
      ▼
answer[i] = same group
```

---

## 🧪 Approach 2 — Detailed Dry Run

Consider:

```text
seq = "((()))"
```

Start:

```text
stack = []
```

### First `(`

```text
level = 1
group = 1

stack = [1]
answer = [1]
```

### Second `(`

```text
level = 2
group = 0

stack = [1,0]
answer = [1,0]
```

### Third `(`

```text
level = 3
group = 1

stack = [1,0,1]
answer = [1,0,1]
```

### First `)`

The matching opening parenthesis belongs to group `1`.

```text
group = stack.pop()
```

So:

```text
stack = [1,0]
answer = [1,0,1,1]
```

### Second `)`

Pop:

```text
group = 0

stack = [1]
answer = [1,0,1,1,0]
```

### Third `)`

Pop:

```text
group = 1

stack = []
answer = [1,0,1,1,0,1]
```

Final:

```text
[1,0,1,1,0,1]
```

Group `0` receives:

```text
()
```

Group `1` receives:

```text
(())
```

Both are valid.

---

# 🧠 Approach 2 — Why the Stack Works

The stack stores:

```text
group assignment of currently open parentheses
```

Because parentheses are nested, the most recently opened parenthesis is the first one to close.

That is exactly:

```text
LIFO
```

which is the behavior of a stack.

Example:

```text
((()))
```

Opening groups:

```text
push 1
push 0
push 1
```

Closing groups:

```text
pop 1
pop 0
pop 1
```

So every opening and its matching closing parenthesis receive the same group.

---

# ✅ Approach 2 — Correctness Proof

For every opening parenthesis, we assign a group and push it.

For every closing parenthesis, the top of the stack is exactly the group of the matching opening parenthesis.

Therefore:

```text
opening and matching closing → same group
```

So both subsequences remain balanced.

The group assignment is based on nesting depth parity, meaning nested levels alternate between the two groups.

Thus the maximum depth is split as evenly as possible, giving the optimal result.

---

# 💻 Approach 2 — Java

```java
import java.util.*;

//Approach-2 (Stack-Based Matching Pair Assignment)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                int group = (stack.size() + 1) % 2;

                answer[i] = group;
                stack.push(group);
            }
            else {
                answer[i] = stack.pop();
            }
        }

        return answer;
    }
}
```

---

# 💻 Approach 2 — C++

```cpp
#include <bits/stdc++.h>
using namespace std;

//Approach-2 (Stack-Based Matching Pair Assignment)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
public:
    vector<int> maxDepthAfterSplit(string seq) {
        int n = seq.size();
        vector<int> answer(n);

        stack<int> st;

        for (int i = 0; i < n; i++) {
            if (seq[i] == '(') {
                int group = (st.size() + 1) % 2;

                answer[i] = group;
                st.push(group);
            }
            else {
                answer[i] = st.top();
                st.pop();
            }
        }

        return answer;
    }
};
```

---

# ⏱️ Approach 2 — Complexity

Each parenthesis is:

```text
pushed once
popped once
```

Therefore:

```text
Time Complexity  : O(n)
Space Complexity : O(n)
```

---

# 🆚 Approach 1 vs Approach 2

| Feature                    | Approach 1   | Approach 2          |
| -------------------------- | ------------ | ------------------- |
| Main idea                  | Depth parity | Matching-pair stack |
| Technique                  | Counter      | Stack               |
| Time                       | `O(n)`       | `O(n)`              |
| Auxiliary Space            | `O(1)`       | `O(n)`              |
| Matching pairs stored      | ❌           | ✅                  |
| Uses parity                | ✅           | ✅                  |
| Pair relationship explicit | No           | Yes                 |
| Best performance           | ✅           | Good alternative    |
| Easiest implementation     | ✅           | Very intuitive      |

---

# 🧠 Why Approach 1 Is Better

The stack solution stores every currently open parenthesis's group.

But we do not actually need that information.

For any position, the required group can be determined directly from:

```text
current nesting depth
```

using:

```text
depth % 2
```

So instead of:

```text
stack of open groups
```

we only need:

```text
one integer depth
```

This gives:

```text
Approach 1 → O(n) time, O(1) auxiliary space
Approach 2 → O(n) time, O(n) space
```

---

# 🔬 Example: Deep Nesting

Suppose:

```text
seq = "((((()))))"
```

Maximum depth:

```text
5
```

Levels:

```text
1 → Group 1
2 → Group 0
3 → Group 1
4 → Group 0
5 → Group 1
```

So the deepest chain is distributed as:

```text
Group 0 → 2 levels
Group 1 → 3 levels
```

Therefore:

```text
max(depth(A), depth(B)) = 3
```

and:

```text
ceil(5 / 2) = 3
```

which is optimal.

---

# 🎯 Pattern Recognition

This problem is a combination of:

```text
Parentheses Depth
+
Greedy Assignment
+
Parity
```

Whenever you see:

```text
nested structure
+
multiple groups
+
minimize maximum depth
```

consider dividing by:

```text
nesting level
```

rather than by:

```text
string position
```

The essential transformation is:

```text
Deep nesting
    ↓
Number the nesting levels
    ↓
Distribute levels alternately
    ↓
Balanced group depths
```

---

# 🧠 Important Distinction

Do **not** assign based on index parity:

```text
answer[i] = i % 2
```

That has nothing to do with the nesting structure.

Use:

```text
answer[i] = depth % 2
```

because the optimization concerns **nesting depth**, not string position.

---

# ⚠️ Common Mistakes

## 1. Using `i % 2`

Wrong:

```text
group = i % 2
```

Correct:

```text
group = depth % 2
```

---

## 2. Splitting a matching pair

For:

```text
(...)
```

both parentheses must belong to the same group.

The depth-parity and stack approaches guarantee this.

---

## 3. Trying arbitrary assignments

The goal is not merely to create two valid VPS strings.

We also need to minimize:

```text
max(depth(A), depth(B))
```

Alternating nesting levels is what achieves the optimal balance.

---

## 4. Counting total parentheses instead of nesting

For:

```text
()()()
```

there are six parentheses, but the maximum depth is:

```text
1
```

Depth measures simultaneously open levels.

---

## 5. Forgetting that labels can be swapped

This:

```text
odd → 0
even → 1
```

and this:

```text
odd → 1
even → 0
```

are both valid optimal assignments.

---

# 🗣️ Interview Explanation

A strong explanation:

> "The key is to distribute nesting levels between the two subsequences. I maintain the current nesting depth and assign each parenthesis to `depth % 2`. Matching opening and closing parentheses are at the same nesting level, so they automatically go to the same group. This separates odd and even nesting levels between the two groups. If the original maximum depth is `D`, any two-group split must have one group with depth at least `ceil(D/2)`, and this parity assignment achieves exactly that bound. Therefore it is optimal in O(n) time and O(1) auxiliary space."

---

# 🧾 Quick Revision

## Approach 1 — Depth Parity

```text
depth = 0

For each character:

'(':
    depth++
    answer[i] = depth % 2

')':
    answer[i] = depth % 2
    depth--
```

Core idea:

```text
Odd level  → Group 1
Even level → Group 0
```

---

## Approach 2 — Stack

```text
For '(':
    group = (stack.size() + 1) % 2
    answer[i] = group
    push(group)

For ')':
    answer[i] = pop()
```

Core idea:

```text
Opening chooses group
Closing retrieves the same group
```

---

# ⭐ One-Line Insight

> **Split the parentheses by nesting-level parity: odd levels go to one group and even levels go to the other, keeping the two maximum depths as balanced as possible.**

---

# 📊 Complexity Summary

```text
Approach 1 — Depth Parity
Time  : O(n)
Space : O(1) auxiliary

Approach 2 — Stack
Time  : O(n)
Space : O(n)
```

---

# 🏷️ Tags

`String, Stack, Greedy, Parentheses, Depth, Balance, Parity, Subsequence, Simulation, Counting, Nested Structures, Optimization, LeetCode, Medium`

---

# 📚 Final Takeaway

The problem is not really about randomly splitting characters.

It is about splitting:

```text
NESTING LEVELS
```

For:

```text
(((())))
```

the levels are:

```text
1 2 3 4 4 3 2 1
```

and we assign:

```text
1 → Group 1
2 → Group 0
3 → Group 1
4 → Group 0
```

So:

```text
                  Original VPS
                       │
                       ▼
                 Track Depth
                       │
                       ▼
                 depth % 2
                  /                        /                   Group 1         Group 0
         odd levels     even levels
              │             │
              ▼             ▼
           Valid VPS      Valid VPS
              │             │
              └──────┬──────┘
                     ▼
              Balanced Depth
```

The key principle is:

```text
Do not split by character position.
Split by nesting level.
```

For two groups, the optimal assignment is:

```text
group = depth % 2
```

which gives the smallest possible maximum nesting depth. citeturn408031search0
