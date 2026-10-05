# 856. Score of Parentheses — Stack and Recursive Structure

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

## 🏷️ Tags

`String, Stack, Recursion, Divide and Conquer, Parentheses, Simulation, Parsing, Nested Structure, Depth, LeetCode, Medium`

---

# 📌 Problem

Given a balanced parentheses string `s`, return the **score** of the string.

The scoring rules are:

```text
() = 1
AB = A + B
(A) = 2 * A
```

where `A` and `B` are balanced parentheses strings.

### Example 1

```text
Input: s = "()"

Output: 1
```

### Example 2

```text
Input: s = "(())"

Output: 2
```

Explanation:

```text
(()) = 2 * ()
     = 2 * 1
     = 2
```

### Example 3

```text
Input: s = "()()"

Output: 2
```

Explanation:

```text
() + () = 1 + 1 = 2
```

### Example 4

```text
Input: s = "(()(()))"

Output: 6
```

---

# 🔍 What Is the Problem Really Asking?

The important part is understanding that parentheses can represent either:

1. A direct pair:

```text
()
```

which has score:

```text
1
```

2. A nested expression:

```text
(A)
```

which doubles the score:

```text
2 * A
```

3. Multiple independent expressions:

```text
AB
```

whose scores are added:

```text
A + B
```

So the problem is essentially asking us to evaluate a **nested expression represented by parentheses**.

---

# 💡 Core Insight

A stack is a natural fit because parentheses create a nested structure.

Whenever we see:

```text
(
```

we start a new nested level.

Whenever we see:

```text
)
```

we finish the current level and calculate its contribution.

The key observation is:

```text
()
```

directly contributes:

```text
1
```

while:

```text
(A)
```

contributes:

```text
2 * A
```

Therefore, we can maintain the score of every currently open level.

---

# 🌳 Approach 1 — Stack of Scores

## State Definition

Maintain a stack where every element represents the score accumulated inside one parenthesis level.

Initially:

```text
stack = [0]
```

The bottom `0` represents the score outside all parentheses.

### When we see `'('`

Push a new score:

```text
stack.push(0)
```

This creates a new nested level.

### When we see `')'`

Let:

```text
inner = stack.pop()
```

Now there are two possibilities.

### Case 1 — Empty pair

If:

```text
inner == 0
```

then the current parentheses are:

```text
()
```

and their score is:

```text
1
```

### Case 2 — Nested expression

If:

```text
inner > 0
```

then the current structure is:

```text
(A)
```

and its score becomes:

```text
2 * inner
```

Finally, add the calculated value to the parent level.

---

# 🏗️ Architecture / Flow Diagram — Approach 1

```text
                         Start
                           |
                           v
                    stack = [0]
                           |
                           v
                       Read s[i]
                           |
                 +---------+---------+
                 |                   |
                '('                 ')'
                 |                   |
                 v                   v
          push new level       pop inner score
                                      |
                            +---------+---------+
                            |                   |
                       inner == 0           inner > 0
                            |                   |
                            v                   v
                          score = 1       score = 2 * inner
                            |                   |
                            +---------+---------+
                                      |
                                      v
                            add score to parent
                                      |
                                      v
                                More chars?
                                 /       \
                               Yes        No
                                |          |
                                v          v
                             continue    answer
```

---

# 🔄 Data Flow — Approach 1

```text
String
  |
  v
stack = [0]
  |
  v
Process each character
  |
  +--> '('
  |       |
  |       v
  |   push(0)
  |
  +--> ')'
          |
          v
      inner = pop()
          |
      +---+---+
      |       |
   inner=0  inner>0
      |       |
      v       v
     1     2 * inner
      \       /
       \     /
        v   v
     add to parent
          |
          v
       continue
          |
          v
       stack[0]
          |
          v
        answer
```

---

# 🧪 Dry Run — Approach 1

Take:

```text
s = "(()(()))"
```

Initial:

```text
stack = [0]
```

### i = 0 → `'('`

```text
stack = [0, 0]
```

### i = 1 → `'('`

```text
stack = [0, 0, 0]
```

### i = 2 → `')'`

The top level contains nothing, so:

```text
inner = 0
score = 1
```

Add to parent:

```text
stack = [0, 0, 1]
```

### i = 3 → `'('`

```text
stack = [0, 0, 1, 0]
```

### i = 4 → `'('`

```text
stack = [0, 0, 1, 0, 0]
```

### i = 5 → `')'`

Again, an empty pair:

```text
score = 1
```

```text
stack = [0, 0, 1, 1]
```

### i = 6 → `')'`

The inner score is:

```text
1
```

Therefore:

```text
score = 2 * 1 = 2
```

```text
stack = [0, 0, 1, 2]
```

### i = 7 → `')'`

The inner score is:

```text
1 + 2 = 3
```

Therefore:

```text
score = 2 * 3 = 6
```

Final:

```text
stack = [6]
```

Therefore:

```text
answer = 6
```

---

# ✅ Correctness — Approach 1

At every point, each stack entry stores the score accumulated inside one currently open parenthesis level.

For:

```text
()
```

the inner score is zero, so we assign:

```text
1
```

For:

```text
(A)
```

the inner score is non-zero, so the score becomes:

```text
2 * A
```

For consecutive expressions:

```text
AB
```

their scores are added to the same parent stack level.

Therefore, every valid parentheses structure is evaluated according to the three rules:

```text
() = 1
AB = A + B
(A) = 2 * A
```

Hence the final value in the bottom stack element is the correct score.

---

# ☕ Java — Approach 1

```text
//Approach-1 (Stack of Scores)
//T.C : O(n)
//S.C : O(n)
```

```java
import java.util.*;

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(0);
            } else {
                int inner = stack.pop();
                int score = inner == 0 ? 1 : 2 * inner;
                stack.push(stack.pop() + score);
            }
        }

        return stack.peek();
    }
}
```

---

# 💻 C++ — Approach 1

```text
//Approach-1 (Stack of Scores)
//T.C : O(n)
//S.C : O(n)
```

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int scoreOfParentheses(string s) {
        stack<int> st;
        st.push(0);

        for (char ch : s) {
            if (ch == '(') {
                st.push(0);
            } else {
                int inner = st.top();
                st.pop();

                int score = (inner == 0) ? 1 : 2 * inner;

                int parent = st.top();
                st.pop();

                st.push(parent + score);
            }
        }

        return st.top();
    }
};
```

---

# 🐍 Python — Approach 1

```text
#Approach-1 (Stack of Scores)
#T.C : O(n)
#S.C : O(n)
```

```python
class Solution:
    def scoreOfParentheses(self, s: str) -> int:
        stack = [0]

        for ch in s:
            if ch == '(':
                stack.append(0)
            else:
                inner = stack.pop()
                score = 1 if inner == 0 else 2 * inner
                stack[-1] += score

        return stack[-1]
```

---

# ⏱️ Complexity — Approach 1

```text
Time:  O(n)
Space: O(n)
```

Each character is processed exactly once.

The stack can contain up to `O(n)` nested levels.

---

# 🌳 Approach 2 — Depth / Running Score

The stack is not strictly necessary.

We can use the nesting depth to determine the contribution of every primitive pair.

The important observation is:

```text
()
```

contributes:

```text
2^depth
```

where `depth` is the number of currently open parentheses before the `()` pair closes.

For example:

```text
()
```

At depth `0`:

```text
2^0 = 1
```

For:

```text
(())
```

the inner `()` is at depth `1`:

```text
2^1 = 2
```

For:

```text
((()))
```

the innermost pair is at depth `2`:

```text
2^2 = 4
```

Therefore, whenever we encounter the pattern:

```text
()
```

we add:

```text
1 << depth
```

to the answer.

---

# 🏗️ Architecture / Flow Diagram — Approach 2

```text
                         Start
                           |
                           v
                    depth = 0
                    answer = 0
                           |
                           v
                       Read s[i]
                           |
                 +---------+---------+
                 |                   |
                '('                 ')'
                 |                   |
                 v                   v
              depth++          check previous
                                    |
                                  "()" ?
                                 /      \
                               Yes       No
                                |         |
                                v         v
                         answer += 2^depth
                                          |
                                          v
                                     depth--
                                          |
                                          v
                                   More chars?
                                      /   \
                                    Yes    No
                                     |      |
                                     v      v
                                  continue answer
```

---

# 🔄 Data Flow — Approach 2

```text
s
|
v
depth = 0
answer = 0
|
v
Read current character
|
+--> '('
|      |
|      v
|   depth++
|
+--> ')'
       |
       v
   If previous char == '('
       |
       v
   answer += 2^depth
       |
       v
   depth--
       |
       v
     continue
       |
       v
   final answer
```

---

# 🧪 Dry Run — Approach 2

Take:

```text
s = "(()(()))"
```

Start:

```text
depth = 0
answer = 0
```

### i = 0 → `'('`

```text
depth = 1
```

### i = 1 → `'('`

```text
depth = 2
```

### i = 2 → `')'`

Previous character is `'('`, so this is:

```text
()
```

Contribution:

```text
2^1 = 2
```

```text
answer = 2
depth = 1
```

### i = 3 → `'('`

```text
depth = 2
```

### i = 4 → `'('`

```text
depth = 3
```

### i = 5 → `')'`

Primitive pair:

```text
()
```

Contribution:

```text
2^2 = 4
```

```text
answer = 6
depth = 2
```

### i = 6 → `')'`

Not a primitive pair:

```text
depth = 1
```

### i = 7 → `')'`

Not a primitive pair:

```text
depth = 0
```

Final:

```text
answer = 6
```

Therefore:

```text
6
```

---

# ☕ Java — Approach 2

```text
//Approach-2 (Depth Based)
//T.C : O(n)
//S.C : O(1)
```

```java
class Solution {
    public int scoreOfParentheses(String s) {
        int depth = 0;
        int score = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                if (s.charAt(i - 1) == '(') {
                    score += 1 << (depth - 1);
                }
                depth--;
            }
        }

        return score;
    }
}
```

---

# 💻 C++ — Approach 2

```text
//Approach-2 (Depth Based)
//T.C : O(n)
//S.C : O(1)
```

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int scoreOfParentheses(string s) {
        int depth = 0;
        int score = 0;

        for (int i = 0; i < (int)s.size(); i++) {
            if (s[i] == '(') {
                depth++;
            } else {
                if (s[i - 1] == '(') {
                    score += 1 << (depth - 1);
                }
                depth--;
            }
        }

        return score;
    }
};
```

---

# 🐍 Python — Approach 2

```text
#Approach-2 (Depth Based)
#T.C : O(n)
#S.C : O(1)
```

```python
class Solution:
    def scoreOfParentheses(self, s: str) -> int:
        depth = 0
        score = 0

        for i, ch in enumerate(s):
            if ch == '(':
                depth += 1
            else:
                if s[i - 1] == '(':
                    score += 1 << (depth - 1)
                depth -= 1

        return score
```

---

# ⏱️ Complexity — Approach 2

```text
Time:  O(n)
Space: O(1)
```

Only two integer variables are required:

```text
depth
score
```

No stack is needed.

---

# ⚖️ Approach Comparison

| Feature            | Stack of Scores                   | Depth Based                           |
| ------------------ | --------------------------------- | ------------------------------------- |
| Main idea          | Store score of each nesting level | Calculate primitive pair contribution |
| Time               | `O(n)`                            | `O(n)`                                |
| Space              | `O(n)`                            | `O(1)`                                |
| Uses Stack         | ✅                                | ❌                                    |
| Handles nesting    | ✅                                | ✅                                    |
| Easy to understand | ✅                                | Strong                                |
| Memory optimized   | ❌                                | ✅                                    |
| Best overall       | Strong                            | ✅                                    |

---

# 🧠 Pattern Recognition

When a problem contains:

```text
Nested parentheses
+
Rules based on nesting
```

look for:

```text
Stack
```

or:

```text
Depth / nesting level
```

A useful observation is:

```text
Each primitive "()" contributes a value based on its depth.
```

For this problem:

```text
depth 0 → 1
depth 1 → 2
depth 2 → 4
depth 3 → 8
```

which follows:

```text
2^depth
```

---

# ❌ Common Mistakes

### 1. Treating every `()` as `1`

For:

```text
(())
```

the inner pair is worth `1`, but the outer parentheses double it:

```text
2 * 1 = 2
```

---

### 2. Forgetting concatenation

For:

```text
()()
```

the score is:

```text
1 + 1 = 2
```

not:

```text
2 * 2
```

---

### 3. Incorrectly multiplying every closing parenthesis

Only a structure of the form:

```text
(A)
```

gets doubled.

A direct:

```text
()
```

gets a score of:

```text
1
```

---

### 4. Confusing depth with index

The depth represents the number of currently open parentheses.

For:

```text
((()))
```

the innermost `()` is evaluated at depth `2`, so:

```text
2^2 = 4
```

---

### 5. Using recursion without considering stack depth

A recursive solution can naturally represent the grammar, but deep nesting can make the call stack large.

The iterative stack/depth solutions avoid recursive function calls.

---

# 🎯 Interview Explanation

> I solve this using a stack of scores. I keep one score for every currently open parenthesis level. When I see `'('`, I push a new zero score. When I see `')'`, I pop the inner score. If it is zero, the pair is `()`, so its score is `1`. Otherwise it represents `(A)`, so its score is `2 * A`. I then add that score to the parent level. This directly follows the scoring rules and runs in `O(n)` time with `O(n)` space.

### Optimized Interview Version

> We can also solve it in `O(n)` time and `O(1)` space by tracking nesting depth. Every primitive `()` contributes `2^depth`, where `depth` is the number of open parentheses before that pair closes. Whenever we find `()`, we add that contribution to the answer and then decrease the depth.

---

# 📝 Quick Revision

```text
Rules:

()   = 1
AB   = A + B
(A)  = 2 * A
```

### Stack Approach

```text
stack = [0]

'(':
    push(0)

')':
    inner = pop()

    if inner == 0:
        score = 1
    else:
        score = 2 * inner

    parent += score

answer = stack[0]
```

### Depth Approach

```text
depth = 0
score = 0

'(':
    depth++

')':
    if previous character == '(':
        score += 2^(depth - 1)

    depth--
```

---

# 🚀 One-Line Insight

> Every primitive `()` contributes `2^depth`, while nested expressions are automatically handled by the current parenthesis depth.

---

# ✅ Final Takeaway

The main challenge is recognizing that the parentheses form a nested expression.

The scoring rules:

```text
() = 1
AB = A + B
(A) = 2 * A
```

can be directly simulated using a stack.

The stack solution is:

```text
O(n) time
O(n) space
```

The depth-based solution improves the auxiliary space to:

```text
O(n) time
O(1) space
```

For interviews, the **Depth Based** approach is the preferred solution when you are comfortable with the `2^depth` observation because it is optimal in both time and auxiliary space.

The **Stack of Scores** approach is an excellent alternative when you want the implementation to closely mirror the recursive structure of the parentheses.
