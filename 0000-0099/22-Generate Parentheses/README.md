# 22. Generate Parentheses — Backtracking and Dynamic Programming

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

## 🏷️ Tags

`String, Backtracking, Dynamic Programming, Recursion, Catalan Number, DFS, Balanced Parentheses, Enumeration, Constraint Satisfaction, LeetCode, Medium`

---

# 📌 Problem

Given an integer `n`, generate all combinations of `n` pairs of well-formed parentheses.

### Example

```text
Input: n = 3

Output:
["((()))","(()())","(())()","()(())","()()()"]
```

---

# 🔍 What Is the Problem Really Asking?

We must construct every string containing:

```text
n opening parentheses '('
n closing parentheses ')'
```

such that no prefix ever contains more closing parentheses than opening parentheses.

For example:

```text
((()))   ✅
(()())   ✅
(())()   ✅
()(())   ✅
()()()   ✅
```

But:

```text
())(()   ❌
```

is invalid because the number of closing parentheses becomes greater than the number of opening parentheses.

The key condition is:

```text
close <= open
```

throughout the construction.

---

# 💡 Core Insight

Maintain two counters:

```text
open  = number of '(' used
close = number of ')' used
```

We have two possible choices.

### Add `'('`

Allowed when:

```text
open < n
```

### Add `')'`

Allowed only when:

```text
close < open
```

When:

```text
open == n && close == n
```

we have one complete valid answer.

---

# 🌳 Approach 1 — Backtracking / DFS

Backtracking builds the string one character at a time and immediately rejects choices that would make the prefix invalid.

```text
                         Start
                           |
                    open=0, close=0
                           |
                 ┌─────────┴─────────┐
                 |                   |
            open < n?           close < open?
                 |                   |
               add '('           add ')'
                 |                   |
               recurse            recurse
                 |                   |
              backtrack          backtrack
                 └─────────┬─────────┘
                           |
                  open==n && close==n
                           |
                           ▼
                     store answer
```

## 🔄 Data Flow

```text
n
│
▼
open = 0, close = 0, current = ""
│
├── open < n
│      └── current + '('
│             └── DFS
│
└── close < open
       └── current + ')'
              └── DFS
                     │
                     ▼
             complete string
                     │
                     ▼
                 answer
```

## 🧪 Dry Run — n = 3

```text
""
 |
"("
 |
"(("
 |
"((("
 |
"((()"
 |
"((())"
 |
"((()))"  → store
```

Backtracking then explores the remaining valid branches:

```text
((()))
(()())
(())()
()(())
()()()
```

There are exactly five answers for `n = 3`.

## ✅ Correctness

At every step:

```text
open <= n
close <= open
```

Therefore no generated prefix is invalid.

When:

```text
open == close == n
```

the string contains exactly `n` opening and `n` closing parentheses and every prefix is valid. Thus every stored result is valid.

Conversely, every valid parentheses string can be constructed by making the same valid prefix choices, so no valid answer is missed.

## ☕ Java — Approach 1

```java
import java.util.*;

class Solution {

    //Approach-1 (Backtracking / DFS)
    //T.C : O(Cn * n)
    //S.C : O(n) auxiliary, excluding output

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backtrack(int n, int open, int close,
                           StringBuilder current, List<String> ans) {

        if (open == n && close == n) {
            ans.add(current.toString());
            return;
        }

        if (open < n) {
            current.append('(');
            backtrack(n, open + 1, close, current, ans);
            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {
            current.append(')');
            backtrack(n, open, close + 1, current, ans);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
```

## 💻 C++ — Approach 1

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {

    //Approach-1 (Backtracking / DFS)
    //T.C : O(Cn * n)
    //S.C : O(n) auxiliary, excluding output

public:
    void backtrack(int n, int open, int close,
                   string& current, vector<string>& ans) {

        if (open == n && close == n) {
            ans.push_back(current);
            return;
        }

        if (open < n) {
            current.push_back('(');
            backtrack(n, open + 1, close, current, ans);
            current.pop_back();
        }

        if (close < open) {
            current.push_back(')');
            backtrack(n, open, close + 1, current, ans);
            current.pop_back();
        }
    }

    vector<string> generateParenthesis(int n) {
        vector<string> ans;
        string current;

        backtrack(n, 0, 0, current, ans);

        return ans;
    }
};
```

## 🐍 Python — Approach 1

```python
class Solution:

    #Approach-1 (Backtracking / DFS)
    #T.C : O(Cn * n)
    #S.C : O(n) auxiliary, excluding output

    def generateParenthesis(self, n: int):
        ans = []
        current = []

        def backtrack(open_count, close_count):
            if open_count == n and close_count == n:
                ans.append("".join(current))
                return

            if open_count < n:
                current.append('(')
                backtrack(open_count + 1, close_count)
                current.pop()

            if close_count < open_count:
                current.append(')')
                backtrack(open_count, close_count + 1)
                current.pop()

        backtrack(0, 0)
        return ans
```

## ⏱️ Complexity — Approach 1

The number of valid strings is the `n`th Catalan number:

```text
Cn = 1 / (n + 1) * C(2n, n)
```

Each answer has length `2n`.

```text
Time:  O(Cn * n)
Space: O(n) auxiliary, excluding output
Output: O(Cn * n)
```

---

# 🌳 Approach 2 — Dynamic Programming / Catalan Decomposition

A non-empty valid parentheses string can be uniquely decomposed as:

```text
S = "(" + A + ")" + B
```

where:

```text
A = valid sequence with i pairs
B = valid sequence with n - 1 - i pairs
```

Define:

```text
dp[k] = all valid parentheses strings containing k pairs
```

Then:

```text
dp[0] = [""]
```

and for every `n`:

```text
dp[n] =
    for i = 0 ... n-1
        for A in dp[i]
            for B in dp[n-1-i]
                "(" + A + ")" + B
```

## 🏗️ Architecture / Flow Diagram

```text
dp[0] = [""]
    |
    ▼
build dp[1]
    |
    ▼
build dp[2]
    |
    ▼
build dp[3]
    |
    ▼
   ...
    |
    ▼
build dp[n]

For each split i:

A ← dp[i]
B ← dp[n-1-i]

Result = "(" + A + ")" + B
```

## 🧪 Dry Run — n = 3

Previously:

```text
dp[0] = [""]
dp[1] = ["()"]
dp[2] = ["(())", "()()"]
```

For `dp[3]`:

### i = 0

```text
A = dp[0]
B = dp[2]
```

Results:

```text
"()" + "(())" = "()(())"
"()" + "()()" = "()()()"
```

### i = 1

```text
A = dp[1]
B = dp[1]
```

Result:

```text
"(" + "()" + ")" + "()"
= "(())()"
```

### i = 2

```text
A = dp[2]
B = dp[0]
```

Results:

```text
"(" + "(())" + ")" = "((()))"
"(" + "()()" + ")" = "(()())"
```

So:

```text
dp[3] =
[
    "()(())",
    "()()()",
    "(())()",
    "((()))",
    "(()())"
]
```

Order does not matter.

## ✅ Correctness

Take any valid non-empty parentheses string.

Its first `'('` has a unique matching `')'`. Everything between them is a valid parentheses sequence `A`, and everything after them is another valid sequence `B`.

If `A` contains `i` pairs, then `B` contains:

```text
n - 1 - i
```

pairs.

Therefore the string is generated by exactly one split:

```text
"(" + A + ")" + B
```

Since the DP considers every `i` and every valid `A` and `B`, every valid string is produced.

## ☕ Java — Approach 2

```java
import java.util.*;

class Solution2 {

    //Approach-2 (Dynamic Programming / Catalan Decomposition)
    //T.C : O(Cn * n)
    //S.C : O(Cn * n)

    public List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            dp.add(new ArrayList<>());
        }

        dp.get(0).add("");

        for (int pairs = 1; pairs <= n; pairs++) {
            for (int leftPairs = 0; leftPairs < pairs; leftPairs++) {

                int rightPairs = pairs - 1 - leftPairs;

                for (String left : dp.get(leftPairs)) {
                    for (String right : dp.get(rightPairs)) {
                        dp.get(pairs).add("(" + left + ")" + right);
                    }
                }
            }
        }

        return dp.get(n);
    }
}
```

## 💻 C++ — Approach 2

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution2 {

    //Approach-2 (Dynamic Programming / Catalan Decomposition)
    //T.C : O(Cn * n)
    //S.C : O(Cn * n)

public:
    vector<string> generateParenthesis(int n) {
        vector<vector<string>> dp(n + 1);

        dp[0].push_back("");

        for (int pairs = 1; pairs <= n; pairs++) {
            for (int leftPairs = 0; leftPairs < pairs; leftPairs++) {

                int rightPairs = pairs - 1 - leftPairs;

                for (const string& left : dp[leftPairs]) {
                    for (const string& right : dp[rightPairs]) {
                        dp[pairs].push_back("(" + left + ")" + right);
                    }
                }
            }
        }

        return dp[n];
    }
};
```

## 🐍 Python — Approach 2

```python
class Solution2:

    #Approach-2 (Dynamic Programming / Catalan Decomposition)
    #T.C : O(Cn * n)
    #S.C : O(Cn * n)

    def generateParenthesis(self, n: int):
        dp = [[] for _ in range(n + 1)]
        dp[0].append("")

        for pairs in range(1, n + 1):
            for left_pairs in range(pairs):
                right_pairs = pairs - 1 - left_pairs

                for left in dp[left_pairs]:
                    for right in dp[right_pairs]:
                        dp[pairs].append("(" + left + ")" + right)

        return dp[n]
```

## ⏱️ Complexity — Approach 2

There are `Cn` final answers and every answer has length `2n`.

```text
Time:  O(Cn * n)
Space: O(Cn * n)
```

This includes storing the DP-generated strings.

---

# ⚖️ Approach Comparison

| Feature                    | Backtracking         | DP / Catalan Decomposition      |
| -------------------------- | -------------------- | ------------------------------- |
| Technique                  | DFS + Backtracking   | Dynamic Programming             |
| Main idea                  | Build valid prefixes | Combine smaller valid sequences |
| Pruning                    | Yes                  | Not needed                      |
| Time                       | `O(Cn * n)`          | `O(Cn * n)`                     |
| Auxiliary space            | `O(n)`               | `O(Cn * n)`                     |
| Implementation             | Direct               | Mathematical                    |
| Memory usage               | Lower                | Higher                          |
| Best for direct generation | ✅                   | ✅                              |

---

# 🧠 Pattern Recognition

When a problem asks:

```text
Generate all valid combinations
```

and there are constraints that can eliminate invalid partial states, think:

```text
Backtracking + Pruning
```

For balanced parentheses, the critical invariant is:

```text
close <= open
```

This single condition prevents invalid branches.

When you see a recursive decomposition of valid structures, also consider:

```text
Dynamic Programming
Catalan Number
```

---

# ❌ Common Mistakes

### 1. Generate all permutations and filter

This creates many invalid strings unnecessarily.

Instead:

```text
Generate only valid prefixes.
```

### 2. Allow closing before opening

Never allow:

```text
close > open
```

### 3. Forget backtracking

Always:

```text
append
recurse
remove
```

### 4. Use `open == close` as the only condition

`close <= open` must hold throughout the recursion, not only at the end.

---

# 🎯 Interview Explanation

> I solve the problem using backtracking. I keep track of how many opening and closing parentheses have been used. An opening parenthesis can be added while `open < n`, while a closing parenthesis can be added only when `close < open`. This guarantees that every generated prefix is valid. Once both counters reach `n`, I add the string to the result. Since the number of valid strings is the Catalan number `Cn` and each result has length `2n`, the output-sensitive complexity is `O(Cn * n)`.

---

# 📝 Quick Revision

```text
State:
open, close, current

Opening:
open < n

Closing:
close < open

Base:
open == n && close == n

Backtracking:
append → recurse → remove
```

---

# 🔢 Catalan Numbers

The number of valid combinations is:

```text
Cn = 1 / (n + 1) * C(2n, n)
```

For example:

```text
n = 1 → 1
n = 2 → 2
n = 3 → 5
n = 4 → 14
n = 5 → 42
n = 6 → 132
```

This rapid growth is why output generation itself becomes the dominant cost.

---

# 🚀 One-Line Insight

> Generate only strings where `close <= open`; this turns parentheses validation into a clean backtracking problem.

---

# ✅ Final Takeaway

The most important idea is:

```text
Do not generate invalid states.
```

Instead of:

```text
Generate everything → validate
```

use:

```text
Generate only valid possibilities
```

The backtracking solution expresses this directly through:

```text
open < n
close < open
```

The DP solution exposes the Catalan structure:

```text
Valid(n)
=
(
Valid(i)
)
Valid(n - 1 - i)
```

---
