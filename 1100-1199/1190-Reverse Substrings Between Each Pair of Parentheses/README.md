# 1190. Reverse Substrings Between Each Pair of Parentheses

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

---

# 🧩 Problem Statement

Given a string `s` containing lowercase English letters and parentheses, reverse the string inside every matching pair of parentheses, starting from the **innermost pair**.

The final answer must contain **no parentheses**.

The official constraints are:

```text
1 <= s.length <= 2000
s contains lowercase English letters and parentheses
all parentheses are balanced
```

citeturn944062view0

### Example 1

```text
Input:
s = "(abcd)"

Output:
"dcba"
```

### Example 2

```text
Input:
s = "(u(love)i)"

Output:
"iloveu"
```

The inner `"love"` is reversed first, then the enclosing substring is reversed. citeturn944062view0

### Example 3

```text
Input:
s = "(ed(et(oc))el)"

Output:
"leetcode"
```

---

# 🎯 What Is the Problem Really Asking?

The difficult part is not reversing a string.

The difficult part is handling **nested reversals** correctly.

For:

```text
(ed(et(oc))el)
```

the structure is:

```text
Nested Parentheses
        ↓
Process Inner First
        ↓
Reverse
        ↓
Merge Into Outer Level
```

This naturally suggests a **stack**.

But there is an even better observation:

> Reversing a substring can be simulated by changing the direction in which we traverse it.

That gives us two useful approaches.

---

# 🚀 Approach 1 — Matching Parentheses + Direction Reversal

## 💡 Core Idea

First match every pair of parentheses:

```text
open ↔ close
```

Then traverse the string.

When we reach a parenthesis:

```text
jump directly to its matching parenthesis
+
reverse traversal direction
```

When we see a normal letter:

```text
append it
```

We never physically reverse a substring.

---

## 🏗️ Approach 1 — Architecture Diagram

```text
                     INPUT STRING
                          │
                          ▼
              ┌──────────────────────┐
              │ Match parentheses     │
              │ using a stack         │
              └──────────┬───────────┘
                         │
                         ▼
              ┌──────────────────────┐
              │ pair[i] = matching   │
              │ parenthesis index    │
              └──────────┬───────────┘
                         │
                         ▼
              ┌──────────────────────┐
              │ i = 0                │
              │ direction = +1       │
              └──────────┬───────────┘
                         │
                         ▼
              ┌──────────────────────┐
              │ Current char?        │
              └───────┬───────┬──────┘
                      │letter  │bracket
                      ▼        ▼
               ┌───────────┐ ┌─────────────────┐
               │ append    │ │ i = pair[i]    │
               │ character │ │ direction *= -1│
               └─────┬─────┘ └────────┬────────┘
                     │                │
                     └───────┬────────┘
                             ▼
                    i += direction
                             │
                             ▼
                       inside bounds?
                       /           \\
                     Yes             No
                      │               │
                      └── continue    ▼
                                     Done
```

---

## 🔄 Approach 1 — Data Flow

```text
String
  │
  ▼
Match parentheses
  │
  ▼
pair[index]
  │
  ▼
Traverse
  │
  ├── letter ────────→ append
  │
  └── bracket ───────→ jump to pair[index]
                              │
                              ▼
                       reverse direction
                              │
                              ▼
                         continue
                              │
                              ▼
                           result
```

---

## 🧠 Why Direction Reversal Works

Suppose:

```text
(abc)
```

Instead of doing:

```text
reverse("abc")
→ "cba"
```

we can traverse:

```text
c → b → a
```

in reverse order.

The matching-parenthesis jump tells us where to start the reversed traversal.

So:

```text
Physical Reverse
       ↓
Traversal Direction Change
```

This is the key optimization.

---

## 🧪 Approach 1 — Dry Run

Consider:

```text
s = "(u(love)i)"
```

Matching pairs:

```text
0 ↔ 9
2 ↔ 7
```

Start:

```text
i = 0
direction = +1
```

### At index 0

Character:

```text
(
```

Jump:

```text
0 → 9
```

Reverse direction:

```text
+1 → -1
```

### Traverse backward

We encounter:

```text
i
```

append:

```text
"i"
```

At the inner parenthesis boundary, jump to the matching endpoint and flip direction again. The letters inside `love` are then visited in the reverse order while the outer traversal continues correctly.

The complete traversal produces:

```text
"iloveu"
```

which is the required result. citeturn944062view0

---

## ✅ Approach 1 — Correctness

For every matching pair:

```text
(L ... R)
```

when traversal reaches either endpoint:

1. it jumps to the other endpoint;
2. the direction changes;
3. the characters inside the pair are therefore visited in reverse order.

Nested pairs cause additional direction changes, so inner reversals happen naturally before outer reversals.

Parentheses themselves are never appended.

Therefore the generated characters are exactly the required final string.

---

## 💻 Approach 1 — Java

```java
import java.util.*;

//Approach-1 (Matching Parentheses + Direction Reversal)
//T.C : O(n)
//S.C : O(n)

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();

        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            else if (s.charAt(i) == ')') {
                int open = stack.pop();

                pair[open] = i;
                pair[i] = open;
            }
        }

        StringBuilder ans = new StringBuilder();

        int i = 0;
        int direction = 1;

        while (i >= 0 && i < n) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == ')') {
                i = pair[i];
                direction = -direction;
            }
            else {
                ans.append(ch);
            }

            i += direction;
        }

        return ans.toString();
    }
}
```

---

## 💻 Approach 1 — C++

```cpp
#include <bits/stdc++.h>
using namespace std;

//Approach-1 (Matching Parentheses + Direction Reversal)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:
    string reverseParentheses(string s) {
        int n = s.size();

        vector<int> pair(n);
        stack<int> st;

        for (int i = 0; i < n; i++) {
            if (s[i] == '(') {
                st.push(i);
            }
            else if (s[i] == ')') {
                int open = st.top();
                st.pop();

                pair[open] = i;
                pair[i] = open;
            }
        }

        string ans;

        int i = 0;
        int direction = 1;

        while (i >= 0 && i < n) {
            if (s[i] == '(' || s[i] == ')') {
                i = pair[i];
                direction = -direction;
            }
            else {
                ans += s[i];
            }

            i += direction;
        }

        return ans;
    }
};
```

---

## ⏱️ Approach 1 — Complexity

Matching all parentheses takes:

```text
O(n)
```

The traversal also takes:

```text
O(n)
```

Therefore:

```text
Time Complexity  : O(n)
Space Complexity : O(n)
```

This is the optimal approach in asymptotic time.

---

# ⚡ Approach 2 — Stack of Partial Strings

## 💡 Core Idea

This approach directly simulates the nested structure.

Maintain a stack of partial strings.

### When we see `(`

Save the current outer string and start a new inner string.

### When we see `)`

The current string is complete:

```text
reverse(current)
```

Then append it to the previous outer string.

This exactly mirrors the nested structure.

---

## 🏗️ Approach 2 — Architecture Diagram

```text
                    INPUT STRING
                         │
                         ▼
               ┌──────────────────┐
               │ Current character│
               └────────┬─────────┘
                        │
             ┌──────────┼──────────┐
             │          │          │
             ▼          ▼          ▼
          Letter        (          )
             │          │          │
             ▼          ▼          ▼
          append      push       reverse
                      outer      current
                       │            │
                       ▼            ▼
                  new builder    pop outer
                                     │
                                     ▼
                              append reversed
                                     │
                                     ▼
                                  continue
```

---

## 🔄 Approach 2 — Data Flow

```text
Character
   │
   ├── letter
   │      ↓
   │   append to current
   │
   ├── '('
   │      ↓
   │   push current
   │      ↓
   │   create new current
   │
   └── ')'
          ↓
       reverse current
          ↓
       pop outer
          ↓
       append reversed content
          ↓
       continue
```

---

## 🧪 Approach 2 — Dry Run

Consider:

```text
s = "(u(love)i)"
```

Initially:

```text
stack = []
current = ""
```

Read `(`:

```text
stack = [""]
current = ""
```

Read `u`:

```text
current = "u"
```

Read inner `(`:

```text
stack = ["u"]
current = ""
```

Read:

```text
love
```

so:

```text
current = "love"
```

Read `)`:

```text
reverse("love") = "evol"
```

Pop outer:

```text
"u" + "evol"
```

Continue with `i` and then the outer `)`.

Final reversal gives:

```text
"iloveu"
```

---

## ✅ Approach 2 — Correctness

At every opening parenthesis we begin a separate inner substring.

At its closing parenthesis:

```text
reverse(inner)
```

and merge it back into the outer substring.

Because the stack is LIFO, the deepest nested substring is completed first, exactly as required by the problem.

Therefore every parenthesized region is reversed in the correct order.

---

## 💻 Approach 2 — Java

```java
import java.util.*;

//Approach-2 (Stack of Partial Strings)
//T.C : O(n^2) worst-case
//S.C : O(n)

class Solution2 {
    public String reverseParentheses(String s) {
        Deque<StringBuilder> stack = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(current);
                current = new StringBuilder();
            }
            else if (ch == ')') {
                current.reverse();

                StringBuilder outer = stack.pop();
                outer.append(current);

                current = outer;
            }
            else {
                current.append(ch);
            }
        }

        return current.toString();
    }
}
```

---

## 💻 Approach 2 — C++

```cpp
#include <bits/stdc++.h>
using namespace std;

//Approach-2 (Stack of Partial Strings)
//T.C : O(n^2) worst-case
//S.C : O(n)

class Solution2 {
public:
    string reverseParentheses(string s) {
        stack<string> st;
        string current;

        for (char ch : s) {
            if (ch == '(') {
                st.push(current);
                current.clear();
            }
            else if (ch == ')') {
                reverse(current.begin(), current.end());

                string outer = st.top();
                st.pop();

                outer += current;
                current = outer;
            }
            else {
                current += ch;
            }
        }

        return current;
    }
};
```

---

## ⏱️ Approach 2 — Complexity

With deep nesting, the same characters can be reversed multiple times.

For example:

```text
((((abcd))))
```

Therefore:

```text
Time Complexity  : O(n²) worst-case
Space Complexity : O(n)
```

It is simpler to visualize, but less efficient than Approach 1.

---

# 🆚 Approach 1 vs Approach 2

| Feature                        | Approach 1                   | Approach 2         |
| ------------------------------ | ---------------------------- | ------------------ |
| Main technique                 | Matching indices + direction | Stack of strings   |
| Time                           | `O(n)`                       | `O(n²)` worst-case |
| Space                          | `O(n)`                       | `O(n)`             |
| Physically reverses substrings | ❌                           | ✅                 |
| Handles nesting                | Direction changes            | Stack              |
| Implementation                 | More advanced                | More intuitive     |
| Best performance               | ✅                           | Good alternative   |

---

# 🧠 Why Approach 1 Is Faster

Approach 2 physically performs:

```text
reverse(current)
```

again and again.

With deep nesting, the same characters can be processed repeatedly.

Approach 1 never reverses the characters.

Instead:

```text
parenthesis
     ↓
jump to matching parenthesis
     ↓
flip direction
```

So each character is handled only a constant number of times.

Therefore:

```text
Approach 1 → O(n)
Approach 2 → O(n²) worst-case
```

---

# ⚠️ Common Mistakes

### 1. Reversing the entire string

Only the substring inside each matching pair should be reversed.

### 2. Processing outer parentheses before inner ones

Nested expressions must effectively be processed from the inside out.

### 3. Keeping parentheses in the result

Parentheses are control characters and must not appear in the final answer.

### 4. Forgetting to flip direction

In Approach 1:

```text
direction = -direction
```

is essential.

### 5. Forgetting to jump to the matching parenthesis

In Approach 1:

```text
i = pair[i]
```

is what makes the reverse traversal possible.

---

# 🎯 Pattern Recognition

This problem teaches an important optimization:

```text
Nested Reversal
      ↓
Can I simulate reversal instead of performing it?
```

Here the answer is yes:

```text
Matching Parentheses
+
Traversal Direction
```

General pattern:

```text
Physical transformation
        ↓
Change traversal / interpretation
        ↓
Avoid repeated work
```

---

# 🗣️ Interview Explanation

> "First I match every opening parenthesis with its closing parenthesis using a stack. Then I traverse the string with a direction variable. When I encounter a parenthesis, I jump to its matching parenthesis and reverse the direction. This makes me traverse the substring inside that pair in reverse order without physically reversing it. Since each character is visited only a constant number of times, the solution is O(n) time and O(n) space."

---

# 🧾 Quick Revision

## Approach 1

```text
1. Match parentheses
2. Store matching indices
3. Start i = 0, direction = +1
4. Letter → append
5. Parenthesis → jump to pair[i]
6. direction *= -1
7. Continue
```

## Approach 2

```text
1. '(' → push current string
2. letters → append
3. ')' → reverse current
4. pop outer string
5. append reversed content
6. continue
```

---

# ⭐ One-Line Insight

> **Do not physically reverse nested substrings; match the parentheses and simulate every reversal by changing traversal direction.**

---

# 📊 Complexity Summary

```text
Approach 1:
Time  : O(n)
Space : O(n)

Approach 2:
Time  : O(n²) worst-case
Space : O(n)
```

---

# 🏷️ Tags

`String, Stack, Parentheses, String Manipulation, Simulation, Two Pointers, Parsing, StringBuilder, Deque, Nested Strings, LeetCode, Medium`

---

# 📚 Final Takeaway

The simple solution is:

```text
Stack
  ↓
Build inner substring
  ↓
Reverse at ')'
  ↓
Merge into outer
```

The optimized solution is:

```text
Match parentheses
       ↓
Traverse string
       ↓
Bracket found?
       ↓
Jump to matching bracket
       ↓
Flip direction
       ↓
Continue
```

The key lesson is:

```text
Instead of reversing the characters,
reverse the way you traverse them.
```

That is what turns the nested-reversal problem into an `O(n)` solution. citeturn944062view0
