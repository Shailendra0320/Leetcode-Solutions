# 678. Valid Parenthesis String — Greedy Balance Range and Two Stacks

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

## 🏷️ Tags

`String, Greedy, Stack, Two Pointers, Parentheses, Simulation, String Parsing, Balance, Range Tracking, Matching, Linear Scan, LeetCode, Medium`

---

# 📌 Problem

Given a string `s` containing only:

```text
'('
')'
'*'
```

determine whether the string can be a valid parentheses string.

The character `'*'` can represent:

```text
'('
')'
or
''
```

where `''` means empty.

### Example 1

```text
Input: s = "()"

Output: true
```

### Example 2

```text
Input: s = "(*)"

Output: true
```

Here `'*'` can represent an empty string.

### Example 3

```text
Input: s = "(*))"

Output: true
```

Here `'*'` can represent `'('`.

### Example 4

```text
Input: s = "(((******))"

Output: false
```

---

# 🔍 What Is the Problem Really Asking?

The important part is that `'*'` is flexible.

For every `'*'`, we have three choices:

```text
'*' → '('
'*' → ')'
'*' → empty
```

We need to determine whether **at least one interpretation** makes the whole string valid.

A valid parentheses string must satisfy:

```text
balance >= 0
```

for every prefix, and:

```text
final balance = 0
```

The challenge is that a star creates multiple possible balances.

---

# 💡 Core Insight

There are two strong linear-time solutions.

### Approach 1 — Greedy Balance Range

Instead of tracking one balance, track:

```text
low  = minimum possible balance
high = maximum possible balance
```

For each character:

```text
'(':
    low++
    high++

')':
    low--
    high--

'*':
    low--
    high++
```

Then:

```text
low = max(low, 0)
```

because an actual valid prefix cannot have negative balance.

If:

```text
high < 0
```

then even the most optimistic interpretation is invalid.

At the end:

```text
low == 0
```

means at least one valid interpretation exists.

---

### Approach 2 — Two Stacks

Maintain indices of:

```text
openStack → '('
starStack → '*'
```

For every `')'`:

1. Match it with an unmatched `'('` if possible.
2. Otherwise use a previous `'*'` as `'('`.
3. Otherwise return `false`.

After the scan, remaining opening parentheses must be matched by later stars acting as `')'`.

Therefore every such pair must satisfy:

```text
openIndex < starIndex
```

---

# 🌳 Approach 1 — Greedy Balance Range

## State Definition

Maintain:

```text
low
high
```

where:

```text
low  = smallest possible unmatched-opening count
high = largest possible unmatched-opening count
```

We do not need every possible balance.

The complete set of possible balances can be compressed into this interval.

---

# 🏗️ Architecture / Flow Diagram — Approach 1

```text
                         Start
                           |
                           v
                    low = 0, high = 0
                           |
                           v
                       Read s[i]
                           |
              +------------+------------+
              |            |            |
             '('          ')'          '*'
              |            |            |
              v            v            v
          low++,high++  low--,high--  low--,high++
                                          high++
              \            |            /
               \           |           /
                +----------+----------+
                           |
                           v
                    low = max(low, 0)
                           |
                    high < 0 ?
                      /        \
                    Yes         No
                    |            |
                    v            v
                  false       continue
                                 |
                                 v
                             end of s
                                 |
                             low == 0 ?
                              /     \
                            Yes      No
                            |         |
                          true      false
```

---

# 🔄 Data Flow — Approach 1

```text
s
│
▼
low = 0, high = 0
│
▼
Process each character
│
├── '(' → [low + 1, high + 1]
│
├── ')' → [low - 1, high - 1]
│
└── '*' → [low - 1, high + 1]
│
▼
low = max(low, 0)
│
▼
If high < 0 → impossible
│
▼
After all characters:
low == 0 → valid
```

---

# 🧪 Dry Run — Approach 1

Take:

```text
s = "(*))"
```

Initial:

```text
low = 0
high = 0
```

### i = 0 → `'('`

```text
low = 1
high = 1
```

Range:

```text
[1, 1]
```

### i = 1 → `'*'`

The star may:

```text
')'    → balance -1
empty  → balance  0
'('    → balance +1
```

So:

```text
low = 0
high = 2
```

Range:

```text
[0, 2]
```

### i = 2 → `')'`

```text
low = -1
high = 1
```

Clamp:

```text
low = 0
```

Range:

```text
[0, 1]
```

### i = 3 → `')'`

```text
low = -1
high = 0
```

Clamp:

```text
low = 0
```

Final:

```text
low = 0
high = 0
```

Therefore:

```text
true
```

One valid interpretation is:

```text
(*))
 ↓
(())
```

---

# ✅ Correctness — Approach 1

At every position, `[low, high]` represents the minimum and maximum possible unmatched-opening counts among all interpretations of the current prefix.

For:

```text
'('
```

every possible balance increases by one.

For:

```text
')'
```

every possible balance decreases by one.

For:

```text
'*'
```

the balance can decrease by one, stay unchanged, or increase by one. Therefore the extreme values become:

```text
low - 1
high + 1
```

A valid prefix can never require a negative balance, so:

```text
low = max(low, 0)
```

If:

```text
high < 0
```

then no interpretation can make the prefix valid.

At the end, if:

```text
low == 0
```

then zero is a reachable final balance, so at least one interpretation is valid.

---

# ☕ Java — Approach 1

```text
//Approach-1 (Greedy Balance Range)
//T.C : O(n)
//S.C : O(1)
```

```java
class Solution {

    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                low++;
                high++;
            } else if (ch == ')') {
                low--;
                high--;
            } else {
                low--;
                high++;
            }

            low = Math.max(low, 0);

            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
}
```

# 💻 C++ — Approach 1

```text
//Approach-1 (Greedy Balance Range)
//T.C : O(n)
//S.C : O(1)
```

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {

public:
    bool checkValidString(string s) {
        int low = 0;
        int high = 0;

        for (char ch : s) {

            if (ch == '(') {
                low++;
                high++;
            } else if (ch == ')') {
                low--;
                high--;
            } else {
                low--;
                high++;
            }

            low = max(low, 0);

            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
};
```

# 🐍 Python — Approach 1

```text
#Approach-1 (Greedy Balance Range)
#T.C : O(n)
#S.C : O(1)
```

```python
class Solution:

    def checkValidString(self, s: str) -> bool:
        low = 0
        high = 0

        for ch in s:

            if ch == '(':
                low += 1
                high += 1
            elif ch == ')':
                low -= 1
                high -= 1
            else:
                low -= 1
                high += 1

            low = max(low, 0)

            if high < 0:
                return False

        return low == 0
```

---

# ⏱️ Complexity — Approach 1

```text
Time:  O(n)
Space: O(1)
```

Only two integer variables are required.

---

# 🌳 Approach 2 — Two Stacks

## State Definition

Keep two stacks of indices:

```text
openStack
starStack
```

### `'('`

```text
openStack.push(i)
```

### `'*'`

```text
starStack.push(i)
```

### `')'`

First use a real opening parenthesis:

```text
if openStack is not empty:
    pop openStack
```

Otherwise use a star as an opening parenthesis:

```text
else if starStack is not empty:
    pop starStack
```

Otherwise:

```text
return false
```

---

# 🏗️ Architecture / Flow Diagram — Approach 2

```text
                         Start
                           |
                           v
                openStack = []
                starStack = []
                           |
                           v
                       Read s[i]
                           |
             +-------------+-------------+
             |             |             |
            '('           '*'           ')'
             |             |             |
             v             v             v
        push open      push star     open available?
                                         |
                                  +------+------+
                                  |             |
                                 Yes            No
                                  |             |
                                  v             v
                              pop open      star available?
                                                |
                                          +-----+-----+
                                          |           |
                                         Yes          No
                                          |           |
                                          v           v
                                      pop star      false
```

After the scan:

```text
remaining '('
       |
       v
match with later '*'
       |
       v
openIndex < starIndex
       |
       v
     valid
```

---

# 🔄 Data Flow — Approach 2

```text
String
  |
  +--> '(' → openStack
  |
  +--> '*' → starStack
  |
  +--> ')'
        |
        +--> openStack available → pop open
        |
        +--> otherwise starStack available → pop star
        |
        +--> otherwise → false

After scan:
  |
  v
Match remaining opens with stars
  |
  +--> openIndex < starIndex → possible
  |
  +--> openIndex > starIndex → impossible
```

---

# 🧪 Dry Run — Approach 2

Take:

```text
s = "(*))"
```

### i = 0 → `'('`

```text
openStack = [0]
starStack = []
```

### i = 1 → `'*'`

```text
openStack = [0]
starStack = [1]
```

### i = 2 → `')'`

Use the real opening parenthesis:

```text
openStack = []
starStack = [1]
```

### i = 3 → `')'`

No real opening remains, so use the star:

```text
starStack = []
```

Both stacks are empty.

Therefore:

```text
true
```

---

# 🧪 Important Example

Take:

```text
s = "((*"
```

After scanning:

```text
openStack = [0, 1]
starStack = [2]
```

Only one star remains, so it can match one opening.

For the remaining pair:

```text
1 < 2
```

is valid.

But opening index `0` is still unmatched.

Therefore:

```text
false
```

---

# ✅ Correctness — Approach 2

During the scan, every closing parenthesis must be matched with something that occurs before it.

We prefer a real `'('` over `'*'`.

If no unmatched opening exists, a previous star can act as `'('`.

If neither exists, the current closing parenthesis cannot be matched, so the string is invalid.

After scanning, only unmatched opening parentheses and stars remain.

A star can act as `')'` for an unmatched opening only when it appears later:

```text
openIndex < starIndex
```

If:

```text
openIndex > starIndex
```

the star occurs too early and cannot close that opening parenthesis.

Thus all remaining openings can be matched exactly when the index ordering is valid.

---

# ☕ Java — Approach 2

```text
//Approach-2 (Two Stacks with Index Matching)
//T.C : O(n)
//S.C : O(n)
```

```java
import java.util.*;

class Solution2 {

    public boolean checkValidString(String s) {
        Stack<Integer> openStack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                openStack.push(i);
            } else if (s.charAt(i) == '*') {
                starStack.push(i);
            } else {

                if (!openStack.isEmpty()) {
                    openStack.pop();
                } else if (!starStack.isEmpty()) {
                    starStack.pop();
                } else {
                    return false;
                }
            }
        }

        while (!openStack.isEmpty() && !starStack.isEmpty()) {

            if (openStack.pop() > starStack.pop()) {
                return false;
            }
        }

        return openStack.isEmpty();
    }
}
```

# 💻 C++ — Approach 2

```text
//Approach-2 (Two Stacks with Index Matching)
//T.C : O(n)
//S.C : O(n)
```

```cpp
class Solution2 {

public:
    bool checkValidString(string s) {
        stack<int> openStack;
        stack<int> starStack;

        for (int i = 0; i < (int)s.size(); i++) {

            if (s[i] == '(') {
                openStack.push(i);
            } else if (s[i] == '*') {
                starStack.push(i);
            } else {

                if (!openStack.empty()) {
                    openStack.pop();
                } else if (!starStack.empty()) {
                    starStack.pop();
                } else {
                    return false;
                }
            }
        }

        while (!openStack.empty() && !starStack.empty()) {

            if (openStack.top() > starStack.top()) {
                return false;
            }

            openStack.pop();
            starStack.pop();
        }

        return openStack.empty();
    }
};
```

# 🐍 Python — Approach 2

```text
#Approach-2 (Two Stacks with Index Matching)
#T.C : O(n)
#S.C : O(n)
```

```python
class Solution2:

    def checkValidString(self, s: str) -> bool:
        open_stack = []
        star_stack = []

        for i, ch in enumerate(s):

            if ch == '(':
                open_stack.append(i)

            elif ch == '*':
                star_stack.append(i)

            else:
                if open_stack:
                    open_stack.pop()
                elif star_stack:
                    star_stack.pop()
                else:
                    return False

        while open_stack and star_stack:

            if open_stack[-1] > star_stack[-1]:
                return False

            open_stack.pop()
            star_stack.pop()

        return not open_stack
```

---

# ⏱️ Complexity — Approach 2

```text
Time:  O(n)
Space: O(n)
```

Every index is pushed and popped at most once.

---

# ⚖️ Approach Comparison

| Feature                         | Greedy Balance Range         | Two Stacks          |
| ------------------------------- | ---------------------------- | ------------------- |
| Main idea                       | Track possible balance range | Match using indices |
| Time                            | `O(n)`                       | `O(n)`              |
| Space                           | `O(1)`                       | `O(n)`              |
| Stores indices                  | No                           | Yes                 |
| Handles all `'*'` possibilities | Yes                          | Yes                 |
| Easier to optimize memory       | ✅                           | ❌                  |
| Direct matching visualization   | Good                         | ✅                  |
| Best overall                    | ✅                           | Strong alternative  |

---

# 🧠 Pattern Recognition

When a problem contains:

```text
Parentheses + wildcard
```

look for:

```text
Greedy range
```

because a wildcard changes the balance by:

```text
-1, 0, +1
```

If matching and ordering are important, also consider:

```text
Two stacks of indices
```

---

# ❌ Common Mistakes

### 1. Treating every `'*'` as the same character

A star can represent three possibilities:

```text
'('
')'
empty
```

### 2. Tracking only one balance

One balance loses information about the different interpretations of `'*'`.

Use:

```text
low
high
```

### 3. Forgetting `high < 0`

If:

```text
high < 0
```

even the maximum possible balance is negative, so the string cannot be valid.

### 4. Ignoring star ordering in the two-stack solution

Remaining pairs must satisfy:

```text
openIndex < starIndex
```

---

# 🎯 Interview Explanation

> I use a greedy balance range. `low` represents the minimum possible number of unmatched opening parentheses, and `high` represents the maximum. For `'('`, both increase; for `')'`, both decrease; and for `'*'`, `low` decreases while `high` increases because the star can represent a closing or opening parenthesis. I clamp `low` to zero because a valid prefix cannot have negative balance. If `high` becomes negative, no interpretation can work. At the end, `low == 0` means there is at least one valid interpretation. The solution runs in `O(n)` time and `O(1)` space.

---

# 📝 Quick Revision

```text
low  = minimum possible balance
high = maximum possible balance

'(':
    low++
    high++

')':
    low--
    high--

'*':
    low--
    high++

low = max(low, 0)

if high < 0:
    false

return low == 0
```

Two stacks:

```text
'(' → openStack
'*' → starStack

')':
    use '(' first
    otherwise use '*'
    otherwise false

remaining:
openIndex < starIndex
```

---

# 🚀 One-Line Insight

> Treat `'*'` as a flexible balance adjustment and track the complete feasible balance range instead of choosing one interpretation.

---

# ✅ Final Takeaway

The wildcard makes this problem different from ordinary valid-parentheses problems.

Instead of trying every interpretation:

```text
3^numberOfStars
```

the greedy approach compresses all possibilities into:

```text
[low, high]
```

and solves the problem in:

```text
O(n) time
O(1) space
```

The two-stack solution gives another `O(n)` approach by explicitly matching opening parentheses and wildcard positions while preserving index order.

For interviews, the **Greedy Balance Range** solution is the preferred approach because it is optimal in both time and auxiliary space.
