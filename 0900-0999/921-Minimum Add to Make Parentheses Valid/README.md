# 921. Minimum Add to Make Parentheses Valid — Greedy Balance and Stack

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

## 🏷️ Tags

`String, Greedy, Stack, Parentheses, Counting, Simulation, String Parsing, Balance, Two Pointers, Matching, Linear Scan, LeetCode, Medium`

---

# 📌 Problem

A parentheses string is valid when every opening parenthesis has a matching closing parenthesis and the order is correct.

Given a string `s` containing only `'('` and `')'`, return the **minimum number of parentheses that must be added** to make `s` valid.

We only need the minimum count, not the final string.

## Examples

### Example 1

```text
Input: s = "())"
Output: 1
```

Add one `'('` to get:

```text
(())
```

### Example 2

```text
Input: s = "((\(" 
Output: 3
```

We need three closing parentheses:

```text
((()))
```

### Example 3

```text
Input: s = "()"
Output: 0
```

The string is already valid.

### Example 4

```text
Input: s = "()))(("
Output: 4
```

There are unmatched closing and opening parentheses.

---

# 🔍 What Is the Problem Really Asking?

Every invalidity comes from one of two situations:

```text
1. A ')' appears when no unmatched '(' is available.
2. Some '(' are still unmatched after the scan ends.
```

For example:

```text
s = "())"
```

The first two characters form `()`.

The third `)` has no opening parenthesis available, so one `'('` must be added.

For:

```text
s = "(("
```

both opening parentheses remain unmatched, so two `')'` characters must be added.

This suggests a direct greedy balance strategy.

---

# 💡 Core Insight

Maintain:

```text
open   = currently unmatched '(' count
answer = number of parentheses we must add
```

For each character:

### If the character is `'('`

```text
open++
```

### If the character is `')'`

If an opening parenthesis is available:

```text
open--
```

Otherwise the closing parenthesis is unmatched, so we must insert an opening parenthesis:

```text
answer++
```

After processing the complete string, every remaining unmatched opening parenthesis needs one closing parenthesis:

```text
answer += open
```

So the final answer is:

```text
unmatched closing fixes + unmatched opening count
```

---

# 🌳 Approach 1 — Greedy Balance Counting

## Idea

The important observation is that we never need the exact positions of unmatched parentheses.

We only need to know:

```text
How many unmatched '(' are currently available?
```

When a `')'` arrives:

```text
open > 0
```

means it can be matched immediately.

When:

```text
open == 0
```

the `')'` cannot possibly be matched using any earlier character, so one `'('` is unavoidable.

At the end, every unmatched `'('` needs one `')'`.

This is greedy because every decision is forced and never makes a future situation worse.

---

# 🏗️ Architecture / Flow Diagram — Approach 1

```text
                         Start
                           |
                           ▼
                    open = 0
                  answer = 0
                           |
                           ▼
                       Read s[i]
                           |
                 ┌─────────┴─────────┐
                 │                   │
               '('                 ')'
                 │                   │
                 ▼                   ▼
              open++            open > 0 ?
                                     |
                              ┌──────┴──────┐
                              │             │
                             Yes            No
                              │             │
                              ▼             ▼
                           open--        answer++
                              │             │
                              └──────┬──────┘
                                     |
                                     ▼
                                  next char
                                     |
                                     ▼
                                End of string
                                     |
                                     ▼
                               answer += open
                                     |
                                     ▼
                                   return
```

# 🔄 Data Flow — Approach 1

```text
Input
  |
  ▼
open = 0, answer = 0
  |
  ▼
Scan character
  |
  ├── '(' → open++
  |
  └── ')' →
          ├── open > 0 → open--
          └── open == 0 → answer++
  |
  ▼
After scan
  |
  └── answer += open
  |
  ▼
Minimum additions
```

---

# 🧪 Dry Run — Approach 1

Take:

```text
s = "))(("
```

Initial:

```text
open = 0
answer = 0
```

### i = 0 → `)`

No opening is available.

```text
answer = 1
open = 0
```

### i = 1 → `)`

Again unmatched.

```text
answer = 2
open = 0
```

### i = 2 → `(`

```text
open = 1
```

### i = 3 → `(`

```text
open = 2
```

At the end:

```text
answer = 2
open = 2
```

Therefore:

```text
result = answer + open
       = 2 + 2
       = 4
```

We need two inserted opening parentheses for the initial `))` and two inserted closing parentheses for the final `((`.

---

# ✅ Correctness — Approach 1

At any point, `open` exactly represents the number of unmatched opening parentheses in the processed prefix.

For `'('`, one new unmatched opening parenthesis is created, so `open` increases by one.

For `')'`:

- If `open > 0`, matching the closing parenthesis with an available opening parenthesis requires no insertion and reduces `open` by one.
- If `open == 0`, no previous opening parenthesis can match this closing parenthesis. Therefore at least one insertion is necessary, and inserting `'('` is sufficient.

After the entire string is processed, each remaining unmatched `'('` requires exactly one `')'`.

Thus:

```text
answer + open
```

is both necessary and sufficient, so it is the minimum possible number of additions.

---

# ☕ Java — Approach 1

```text
//Approach-1 (Greedy Balance Counting)
//T.C : O(n)
//S.C : O(1)
```

```java
class Solution {

    public int minAddToMakeValid(String s) {
        int open = 0;
        int answer = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    answer++;
                }
            }
        }

        return answer + open;
    }
}
```

# 💻 C++ — Approach 1

```text
//Approach-1 (Greedy Balance Counting)
//T.C : O(n)
//S.C : O(1)
```

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {

public:
    int minAddToMakeValid(string s) {
        int open = 0;
        int answer = 0;

        for (char ch : s) {

            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    answer++;
                }
            }
        }

        return answer + open;
    }
};
```

# 🐍 Python — Approach 1

```text
#Approach-1 (Greedy Balance Counting)
#T.C : O(n)
#S.C : O(1)
```

```python
class Solution:

    def minAddToMakeValid(self, s: str) -> int:
        open_count = 0
        answer = 0

        for ch in s:

            if ch == '(':
                open_count += 1
            else:
                if open_count > 0:
                    open_count -= 1
                else:
                    answer += 1

        return answer + open_count
```

---

# ⏱️ Complexity — Approach 1

```text
Time:  O(n)
Space: O(1)
```

The string is scanned once and only two integers are maintained.

---

# 🌳 Approach 2 — Stack-Based Matching

## Idea

The greedy solution stores only the count of unmatched opening parentheses.

A second approach is to explicitly store every unmatched parenthesis in a stack.

For each character:

```text
'(' → push it
```

For `')'`:

```text
If the top is '(' → pop and form a valid pair.
Otherwise → push ')' because it is unmatched.
```

At the end, every character left in the stack is unmatched.

Each unmatched parenthesis needs exactly one opposite parenthesis inserted, so:

```text
answer = stack.size()
```

This approach is less space-efficient but makes matching very explicit.

---

# 🏗️ Architecture / Flow Diagram — Approach 2

```text
                         Start
                           |
                           ▼
                       stack = []
                           |
                           ▼
                       Read s[i]
                           |
                 ┌─────────┴─────────┐
                 │                   │
               '('                 ')'
                 │                   │
                 ▼                   ▼
             push '('          stack top == '(' ?
                                     |
                              ┌──────┴──────┐
                              │             │
                             Yes            No
                              │             │
                              ▼             ▼
                            pop()       push ')'
                              │             │
                              └──────┬──────┘
                                     |
                                     ▼
                                  next char
                                     |
                                     ▼
                                stack.size()
                                     |
                                     ▼
                                   answer
```

# 🔄 Data Flow — Approach 2

```text
s
|
▼
stack = []
|
├── '(' → push
|
└── ')' →
       ├── top '(' → pop
       └── otherwise → push ')'
|
▼
End
|
▼
stack.size()
|
▼
minimum additions
```

---

# 🧪 Dry Run — Approach 2

Take:

```text
s = "))(("
```

Initial:

```text
stack = []
```

### i = 0 → `)`

No matching opening parenthesis exists.

```text
stack = [')']
```

### i = 1 → `)`

Again unmatched.

```text
stack = [')', ')']
```

### i = 2 → `(`

```text
stack = [')', ')', '(']
```

### i = 3 → `(`

```text
stack = [')', ')', '(', '(']
```

At the end:

```text
stack.size() = 4
```

Therefore the minimum number of additions is:

```text
4
```

---

# ✅ Correctness — Approach 2

The stack always contains exactly the unmatched parentheses of the processed prefix.

An opening parenthesis is stored because it may need a future closing parenthesis.

When a closing parenthesis arrives and the top is `'('`, the two parentheses form a valid pair, so removing them is optimal and requires no insertion.

If the stack is empty or its top is already `')'`, the new closing parenthesis cannot be matched with a previous opening parenthesis. Therefore it must remain unmatched and is pushed onto the stack.

After processing the full string, each remaining unmatched parenthesis requires exactly one inserted opposite parenthesis.

Therefore `stack.size()` is the minimum number of additions.

---

# ☕ Java — Approach 2

```text
//Approach-2 (Stack-Based Matching)
//T.C : O(n)
//S.C : O(n)
```

```java
import java.util.*;

class Solution2 {

    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(ch);
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
        }

        return stack.size();
    }
}
```

# 💻 C++ — Approach 2

```text
//Approach-2 (Stack-Based Matching)
//T.C : O(n)
//S.C : O(n)
```

```cpp
class Solution2 {

public:
    int minAddToMakeValid(string s) {
        stack<char> st;

        for (char ch : s) {

            if (ch == '(') {
                st.push(ch);
            } else {
                if (!st.empty() && st.top() == '(') {
                    st.pop();
                } else {
                    st.push(ch);
                }
            }
        }

        return st.size();
    }
};
```

# 🐍 Python — Approach 2

```text
#Approach-2 (Stack-Based Matching)
#T.C : O(n)
#S.C : O(n)
```

```python
class Solution2:

    def minAddToMakeValid(self, s: str) -> int:
        stack = []

        for ch in s:

            if ch == '(':
                stack.append(ch)
            else:
                if stack and stack[-1] == '(':
                    stack.pop()
                else:
                    stack.append(ch)

        return len(stack)
```

---

# ⏱️ Complexity — Approach 2

```text
Time:  O(n)
Space: O(n)
```

Each character is pushed and popped at most once.

---

# ⚖️ Approach Comparison

| Feature | Greedy Balance Counting | Stack-Based Matching |
|---|---|---|
| Main idea | Count unmatched parentheses | Store unmatched parentheses |
| Time | `O(n)` | `O(n)` |
| Space | `O(1)` | `O(n)` |
| Explicit matching | Implicit | Explicit |
| Implementation | Very simple | Simple |
| Best memory usage | ✅ | ❌ |
| Best overall | ✅ | Strong alternative |

---

# 🧠 Pattern Recognition

When a problem asks:

```text
Minimum additions to make parentheses valid
```

think about:

```text
Balance
Unmatched opening parentheses
Unmatched closing parentheses
Greedy matching
```

A full parser is unnecessary because the input contains only one parenthesis type.

The main invariant is:

```text
open >= 0
```

where `open` represents unmatched `'('` after processing the current prefix.

---

# 🔥 Why the Greedy Choice Is Optimal

Suppose we see:

```text
')'
```

and:

```text
open > 0
```

Matching it with an existing `'('` is always optimal because it costs zero additions.

If:

```text
open == 0
```

then no previous character can match the `')'`. Therefore one opening parenthesis must be inserted. There is no cheaper alternative.

At the end, if `open = k`, exactly `k` closing parentheses are necessary.

Therefore every counted addition is forced, making the greedy solution globally optimal.

---

# ❌ Common Mistakes

## 1. Counting only total numbers of parentheses

For:

```text
")(" 
```

the counts are equal, but the string is invalid.

The ordering matters.

---

## 2. Forgetting unmatched opening parentheses

For:

```text
"((("
```

the scan may never need to insert an opening parenthesis, but three closing parentheses are still required.

Therefore the final answer must include:

```text
answer + open
```

---

## 3. Forgetting unmatched closing parentheses

For:

```text
")))"
```

three opening parentheses must be inserted.

---

## 4. Using brute force

Trying every possible insertion position is unnecessary.

The balance invariant gives a direct `O(n)` solution.

---

## 5. Overcomplicating the problem with a general parser

Because there is only one parenthesis type, a simple balance count is enough.

---

# 🎯 Interview Explanation

> I maintain the number of unmatched opening parentheses and the number of insertions required. For every `'('`, I increment the opening count. For every `')'`, if an opening parenthesis is available I match it by decrementing the count; otherwise this closing parenthesis is unmatched, so I must insert one `'('` and increment the answer. After the scan, every remaining unmatched opening parenthesis needs one `')'`. Therefore the result is `answer + open`, giving `O(n)` time and `O(1)` space.

---

# 📝 Quick Revision

```text
open = 0
answer = 0

'(':
    open++

')':
    if open > 0:
        open--
    else:
        answer++

final:
answer + open
```

Stack version:

```text
'(' → push

')':
    if top == '(':
        pop
    else:
        push ')'

final:
stack.size()
```

---

# 📊 Useful Examples

```text
"()"       -> 0
"())"      -> 1
"(("       -> 2
")))"      -> 3
")("       -> 2
"()))(("   -> 4
"()(()"    -> 1
```

---

# 🚀 One-Line Insight

> Every unmatched `')'` needs one inserted `'('`, and every `'('` left unmatched at the end needs one inserted `')'`.

---

# ✅ Final Takeaway

The problem can be reduced to counting two things:

```text
1. Unmatched closing parentheses encountered during the scan.
2. Unmatched opening parentheses remaining at the end.
```

The greedy solution tracks both implicitly and gives:

```text
Time:  O(n)
Space: O(1)
```

The stack solution explicitly stores the unmatched structure and gives:

```text
Time:  O(n)
Space: O(n)
```

For interviews, the **Greedy Balance Counting** approach is the preferred solution because it is simpler, optimal in time, and uses constant auxiliary space.
