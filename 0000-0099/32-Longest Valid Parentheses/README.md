# 32. Longest Valid Parentheses — Stack and Two-Pass Counter

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

## 🏷️ Tags

`String, Stack, Two Pointers, Greedy, Parentheses, Counting, String Parsing, Balance, Linear Scan, Matching, LeetCode, Hard`

---

# 📌 Problem

Given a string `s` containing only `'('` and `')'`, find the length of the longest valid (well-formed) parentheses substring.

A valid substring must have correctly matched parentheses and must be contiguous.

### Example 1

```text
Input: s = "(()"
Output: 2
```

### Example 2

```text
Input: s = ")()())"
Output: 4
```

### Example 3

```text
Input: s = ""
Output: 0
```

---

# 🔍 What Is the Problem Really Asking?

We need the maximum length of one contiguous substring that forms a valid parentheses sequence.

For:

```text
s = ")()())"
```

the longest valid substring is:

```text
()()
```

with length:

```text
4
```

The important difficulty is dealing with unmatched parentheses that break valid regions.

---

# 💡 Core Insight

There are two strong linear-time approaches.

**Approach 1:** Store indices in a stack. Unmatched positions act as boundaries, and the current valid length can be obtained with index subtraction.

**Approach 2:** Use two counters and scan in both directions. The forward scan handles extra closing parentheses, while the backward scan handles extra opening parentheses. This uses `O(1)` auxiliary space.

---

# 🌳 Approach 1 — Stack of Indices

Initialize:

```text
stack = [-1]
```

The `-1` represents the boundary before the string.

For each index `i`:

- If `s[i] == '('`, push `i`.
- If `s[i] == ')'`, pop once.
- If the stack becomes empty, the current `')'` is unmatched, so push `i` as the new boundary.
- Otherwise:

```text
length = i - stack.top()
```

Update the maximum.

---

# 🏗️ Architecture / Flow Diagram — Approach 1

```text
                    Start
                      |
                      v
                stack = [-1]
                      |
                      v
                 Scan index i
                      |
              +-------+-------+
              |               |
           '('             ')'
              |               |
              v               v
          push(i)          pop()
                              |
                     +--------+--------+
                     |                 |
                stack empty?       not empty
                     |                 |
                    Yes               Yes
                     |                 |
                     v                 v
                 push(i)        i - stack.top()
                                     |
                                     v
                                 update max
```

# 🔄 Data Flow — Approach 1

```text
s
 |
 v
stack = [-1]
 |
 +--> '(' --> push index
 |
 +--> ')' --> pop
              |
              +--> empty --> current index = boundary
              |
              +--> not empty --> i - top = valid length
                                         |
                                         v
                                       maxLen
```

# 🧪 Dry Run — Approach 1

Take:

```text
s = ")()())"
```

Start:

```text
stack = [-1]
maxLen = 0
```

`i = 0`, `')'`:

```text
pop -> stack = []
empty -> push 0

stack = [0]
```

`i = 1`, `'('`:

```text
push 1

stack = [0, 1]
```

`i = 2`, `')'`:

```text
pop

stack = [0]
length = 2 - 0 = 2
maxLen = 2
```

`i = 3`, `'('`:

```text
stack = [0, 3]
```

`i = 4`, `')'`:

```text
pop

stack = [0]
length = 4 - 0 = 4
maxLen = 4
```

`i = 5`, `')'`:

```text
pop -> stack = []
empty -> push 5
```

Final:

```text
answer = 4
```

---

# ✅ Correctness — Approach 1

The stack stores indices that separate the current valid region from unmatched parentheses.

The initial `-1` allows a valid substring beginning at index `0` to be measured correctly.

When an opening parenthesis is found, its index is stored because it may later be matched.

When a closing parenthesis is found, one unmatched opening is removed.

If the stack becomes empty, the closing parenthesis is unmatched. Therefore it becomes a new boundary.

Otherwise, the top index is the position immediately before the current valid suffix, so:

```text
i - stack.top()
```

is exactly the length of the valid substring ending at `i`.

Thus the maximum recorded length is the longest valid parentheses substring.

---

# ☕ Java — Approach 1

```text
//Approach-1 (Stack of Indices)
//T.C : O(n)
//S.C : O(n)
```

```java
import java.util.*;

class Solution {

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();

                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
```

# 💻 C++ — Approach 1

```text
//Approach-1 (Stack of Indices)
//T.C : O(n)
//S.C : O(n)
```

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {

public:
    int longestValidParentheses(string s) {
        stack<int> st;
        st.push(-1);

        int maxLen = 0;

        for (int i = 0; i < (int)s.size(); i++) {

            if (s[i] == '(') {
                st.push(i);
            } else {
                st.pop();

                if (st.empty()) {
                    st.push(i);
                } else {
                    maxLen = max(maxLen, i - st.top());
                }
            }
        }

        return maxLen;
    }
};
```

# 🐍 Python — Approach 1

```text
#Approach-1 (Stack of Indices)
#T.C : O(n)
#S.C : O(n)
```

```python
class Solution:

    def longestValidParentheses(self, s: str) -> int:
        stack = [-1]
        max_len = 0

        for i, ch in enumerate(s):
            if ch == '(':
                stack.append(i)
            else:
                stack.pop()

                if not stack:
                    stack.append(i)
                else:
                    max_len = max(max_len, i - stack[-1])

        return max_len
```

---

# ⏱️ Complexity — Approach 1

```text
Time:  O(n)
Space: O(n)
```

Every index is pushed and popped at most once.

---

# 🌳 Approach 2 — Two-Pass Counter / Constant Space

## Core Idea

Maintain:

```text
left  = number of '('
right = number of ')'
```

A valid region becomes balanced when:

```text
left == right
```

However, one direction is not sufficient.

For example:

```text
"(()"
```

has an unmatched opening parenthesis at the end.

Therefore we scan:

```text
1. Left → Right
2. Right → Left
```

### Left → Right

If:

```text
right > left
```

we found an unmatched closing parenthesis.

Reset:

```text
left = right = 0
```

When:

```text
left == right
```

the current length is:

```text
2 * right
```

### Right → Left

Now detect the opposite problem.

If:

```text
left > right
```

we found unmatched opening parentheses.

Reset:

```text
left = right = 0
```

When:

```text
left == right
```

the current length is:

```text
2 * left
```

---

# 🏗️ Architecture / Flow Diagram — Approach 2

```text
                         Start
                           |
                           v
                    Left -> Right
                           |
                    count '(' and ')'
                           |
                +----------+----------+
                |                     |
           left == right         right > left
                |                     |
                v                     v
            update max              reset
                |                     |
                +----------+----------+
                           |
                           v
                    Right -> Left
                           |
                    count '(' and ')'
                           |
                +----------+----------+
                |                     |
           left == right         left > right
                |                     |
                v                     v
            update max              reset
                           |
                           v
                         answer
```

# 🔄 Data Flow — Approach 2

```text
s
 |
 +--> Left -> Right
 |       |
 |       +--> '(' -> left++
 |       +--> ')' -> right++
 |       +--> equal -> update max
 |       +--> right > left -> reset
 |
 +--> Right -> Left
         |
         +--> '(' -> left++
         +--> ')' -> right++
         +--> equal -> update max
         +--> left > right -> reset
```

# 🧪 Dry Run — Approach 2

Take:

```text
s = "(()"
```

### Left → Right

```text
(  -> left = 1, right = 0
(  -> left = 2, right = 0
)  -> left = 2, right = 1
```

We never get:

```text
left == right
```

So this pass returns no complete answer.

### Right → Left

Scan:

```text
)
(
(
```

Counts:

```text
) -> left = 0, right = 1
( -> left = 1, right = 1
```

Now:

```text
left == right
```

Therefore:

```text
length = 2 * left = 2
```

Final answer:

```text
2
```

---

# ✅ Correctness — Approach 2

The forward scan detects positions where:

```text
right > left
```

Such a position has more closing parentheses than opening parentheses, so no valid substring can cross that boundary from the left.

The backward scan detects positions where:

```text
left > right
```

which represents unmatched opening parentheses when viewed from the right.

Every valid parentheses substring has equal numbers of opening and closing parentheses. The two scans ensure that both types of invalid boundaries are handled.

Therefore every valid balanced region that can contribute to the maximum is considered, and the largest discovered length is the answer.

---

# ☕ Java — Approach 2

```text
//Approach-2 (Two-Pass Counter / Constant Space)
//T.C : O(n)
//S.C : O(1)
```

```java
class Solution2 {

    public int longestValidParentheses(String s) {
        int maxLen = 0;
        int left = 0;
        int right = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLen = Math.max(maxLen, 2 * right);
            } else if (right > left) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;

        for (int i = s.length() - 1; i >= 0; i--) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return maxLen;
    }
}
```

# 💻 C++ — Approach 2

```text
//Approach-2 (Two-Pass Counter / Constant Space)
//T.C : O(n)
//S.C : O(1)
```

```cpp
class Solution2 {

public:
    int longestValidParentheses(string s) {
        int maxLen = 0;
        int left = 0;
        int right = 0;

        for (char ch : s) {

            if (ch == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLen = max(maxLen, 2 * right);
            } else if (right > left) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;

        for (int i = (int)s.size() - 1; i >= 0; i--) {

            if (s[i] == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLen = max(maxLen, 2 * left);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return maxLen;
    }
};
```

# 🐍 Python — Approach 2

```text
#Approach-2 (Two-Pass Counter / Constant Space)
#T.C : O(n)
#S.C : O(1)
```

```python
class Solution2:

    def longestValidParentheses(self, s: str) -> int:
        max_len = 0
        left = 0
        right = 0

        for ch in s:
            if ch == '(':
                left += 1
            else:
                right += 1

            if left == right:
                max_len = max(max_len, 2 * right)
            elif right > left:
                left = 0
                right = 0

        left = 0
        right = 0

        for ch in reversed(s):
            if ch == '(':
                left += 1
            else:
                right += 1

            if left == right:
                max_len = max(max_len, 2 * left)
            elif left > right:
                left = 0
                right = 0

        return max_len
```

---

# ⏱️ Complexity — Approach 2

```text
Time:  O(n)
Space: O(1)
```

The string is scanned twice, so the overall complexity remains linear.

---

# ⚖️ Approach Comparison

| Feature                  | Stack of Indices            | Two-Pass Counter            |
| ------------------------ | --------------------------- | --------------------------- |
| Time                     | `O(n)`                      | `O(n)`                      |
| Space                    | `O(n)`                      | `O(1)`                      |
| Main idea                | Store boundaries as indices | Track balance with counters |
| Handles extra `')'`      | Yes                         | Forward pass                |
| Handles extra `'('`      | Yes                         | Backward pass               |
| Easy to trace            | ✅                          | ✅                          |
| Constant auxiliary space | ❌                          | ✅                          |

---

# 🧠 Pattern Recognition

When you see:

```text
Parentheses + Longest Valid Substring
```

think about:

```text
Stack of indices
Balance counters
Invalid positions as boundaries
Two-direction scanning
```

If the problem asks for the **length** of the longest valid region, storing indices can be very useful because the length is obtained directly by subtraction.

---

# ❌ Common Mistakes

### 1. Using only one balance scan

A single left-to-right counter fails on cases like:

```text
"(()"
```

because the problem can also contain unmatched opening parentheses.

### 2. Storing characters instead of indices

For the stack method, indices allow:

```text
i - stack.top()
```

to give the exact valid length.

### 3. Forgetting the `-1` base

Use:

```text
stack = [-1]
```

so that valid substrings starting at index `0` are measured correctly.

### 4. Forgetting to reset

Forward pass:

```text
right > left -> reset
```

Backward pass:

```text
left > right -> reset
```

---

# 🎯 Interview Explanation

> I use a stack of indices and initialize it with `-1` as a boundary. For every opening parenthesis, I push its index. For every closing parenthesis, I pop. If the stack becomes empty, the current closing parenthesis is unmatched, so I push its index as the new boundary. Otherwise, the current valid length is `i - stack.peek()`. This gives `O(n)` time and `O(n)` space. An `O(1)` auxiliary-space alternative uses two counters and scans the string from both directions.

---

# 📝 Quick Revision

## Stack

```text
stack = [-1]

'(' -> push index

')' -> pop

empty -> push current index

not empty -> length = i - top
```

## Two Pass

```text
Left -> Right:
right > left -> reset

Right -> Left:
left > right -> reset

equal -> update answer
```

---

# 🚀 One-Line Insight

> Use stack boundaries to measure valid regions directly, or scan in both directions with counters to achieve `O(1)` auxiliary space.

---

# ✅ Final Takeaway

The main idea is to identify where valid parentheses regions begin and end.

The stack solution uses:

```text
unmatched index = boundary
current index - boundary = length
```

The two-pass solution uses:

```text
forward scan  -> extra ')'
backward scan -> extra '('
```

Both run in:

```text
O(n) time
```

while the two-pass approach achieves:

```text
O(1) auxiliary space
```
