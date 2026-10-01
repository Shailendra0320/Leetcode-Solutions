# 20. Valid Parentheses

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

---

# 🧩 Problem Statement

Given a string `s` containing only the characters:

```text
'('  ')'  '{'  '}'  '['  ']'
```

determine whether the string is valid.

A string is valid when:

1. Every opening bracket is closed by the **same type** of bracket.
2. Brackets are closed in the **correct order**.
3. Every closing bracket has a corresponding opening bracket.

The official LeetCode problem uses exactly these three validity rules and constrains `1 <= s.length <= 10^4`. citeturn104999search0

### Example 1

```text
Input:
s = "()"

Output:
true
```

### Example 2

```text
Input:
s = "()[]{}"

Output:
true
```

### Example 3

```text
Input:
s = "(]"

Output:
false
```

The opening `(` cannot be closed by `]`.

### Example 4

```text
Input:
s = "([)]"

Output:
false
```

The brackets are balanced in count, but the nesting order is incorrect.

### Example 5

```text
Input:
s = "{[]}"

Output:
true
```

The nesting is correct:

```text
{
  [
    ]
}
```

---

# 🎯 What Is the Problem Really Asking?

The problem is not just about counting opening and closing brackets.

For example:

```text
([)]
```

has:

```text
2 opening brackets
2 closing brackets
```

but it is still invalid.

Why?

Because nested brackets follow **LIFO** order:

```text
Last Opened
     ↓
First Closed
```

For:

```text
([{}])
```

the opening order is:

```text
(
[
{
```

so the closing order must be:

```text
}
]
)
```

This is exactly the behavior of a **stack**.

---

# 💡 Core Insight

When an opening bracket appears, we do not yet know when it will close, so we store it.

When a closing bracket appears, it must match the **most recently opened bracket that is still active**.

Therefore:

```text
Opening bracket
      ↓
    PUSH

Closing bracket
      ↓
Compare with STACK TOP
      ↓
   Match?
   /    \\
 Yes     No
  ↓        ↓
 POP     false
```

At the end:

```text
stack must be empty
```

If anything remains, some opening brackets were never closed.

---

# 🧠 Why a Stack Is the Correct Data Structure

Consider:

```text
s = "({[]})"
```

After reading the opening brackets:

```text
(
({
({[
```

The stack is conceptually:

```text
Top → [
       {
       (
```

Now the next closing bracket is:

```text
]
```

It must match the top:

```text
[
```

We cannot use a queue because a queue gives FIFO order, while nested brackets require LIFO order.

So:

```text
Nested Matching
      ↓
     LIFO
      ↓
    STACK
```

---

# 🚀 Approach 1 — Stack with Direct Matching

## 💡 Idea

Use a stack to store the opening brackets.

For every character:

### Opening bracket

Push:

```text
(
[
{
```

### Closing bracket

First check whether the stack is empty.

If it is empty, there is no opening bracket available to match this closing bracket.

Otherwise take the top opening bracket and verify:

```text
')' ↔ '('
']' ↔ '['
'}' ↔ '{'
```

If the pair does not match:

```text
return false
```

Otherwise pop the matched opening bracket.

After scanning the entire string:

```text
return stack.isEmpty()
```

---

# 🏗️ Approach 1 — Architecture Diagram

```text
                         INPUT STRING
                              │
                              ▼
                    ┌──────────────────┐
                    │    stack = []    │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │   Read s[i]      │
                    └────────┬─────────┘
                             │
                    ┌────────┴────────┐
                    │                 │
                 Opening           Closing
                 bracket            bracket
                    │                 │
                    ▼                 ▼
              ┌───────────┐    ┌───────────────┐
              │   PUSH    │    │ stack empty ? │
              └─────┬─────┘    └──────┬───┬────┘
                    │                 Yes  No
                    │                  │    │
                    │                  ▼    ▼
                    │               false  ┌────────────────┐
                    │                      │ Compare top   │
                    │                      │ with current  │
                    │                      └──────┬───┬─────┘
                    │                             │   │
                    │                         mismatch match
                    │                             │   │
                    │                             ▼   ▼
                    │                           false pop
                    │                                 │
                    └─────────────────────────────────┘
                                      │
                                      ▼
                               Next character
                                      │
                                      ▼
                                End of string
                                      │
                                      ▼
                               stack empty ?
                                /          \\
                              Yes           No
                               │             │
                               ▼             ▼
                             true          false
```

---

# 🔄 Approach 1 — Data Flow

```text
Current Character
       │
       ▼
Opening or Closing?
       │
   ┌───┴───────────┐
   │               │
Opening          Closing
   │               │
   ▼               ▼
 PUSH         Stack Empty?
                  │
             ┌────┴────┐
            Yes        No
             │          │
             ▼          ▼
           false      Check Top
                          │
                    ┌─────┴─────┐
                    │           │
                  Match      Mismatch
                    │           │
                    ▼           ▼
                   POP        false
                    │
                    └────┬──────┘
                         ▼
                       Continue
```

---

# 🧪 Approach 1 — Detailed Dry Run

Consider:

```text
s = "{[()]}"
```

### Step 1 — `{`

Opening bracket:

```text
push '{'
```

Stack:

```text
{
```

### Step 2 — `[`

```text
push '['
```

Stack:

```text
[
{
```

### Step 3 — `(`

```text
push '('
```

Stack:

```text
(
[
{
```

### Step 4 — `)`

Top:

```text
(
```

Current:

```text
)
```

They match.

Pop:

```text
[
{
```

### Step 5 — `]`

Top:

```text
[
```

Matches.

Pop:

```text
{
```

### Step 6 — `}`

Top:

```text
{
```

Matches.

Pop:

```text
[]
```

End of string:

```text
stack is empty
```

Therefore:

```text
true
```

---

# ❌ Approach 1 — Invalid Dry Run

Consider:

```text
s = "([)]"
```

Read:

```text
(
[
```

Stack:

```text
[
(
```

Now current character:

```text
)
```

But stack top is:

```text
[
```

Expected closing bracket:

```text
]
```

We received:

```text
)
```

So:

```text
mismatch → false
```

This is why simply counting bracket types is not enough.

---

# ✅ Approach 1 — Correctness Proof

Maintain this invariant:

> The stack contains exactly the opening brackets that have been seen but not yet matched, in their nesting order.

### Opening bracket

When we see `(`, `[`, or `{`, it must eventually be closed. Pushing it stores this pending opening bracket.

### Closing bracket

A closing bracket must correspond to the **most recently opened unmatched bracket**. That bracket is exactly the stack top.

If the types differ, the string is invalid.

If they match, popping removes the completed pair.

### End of string

If the stack is not empty, one or more opening brackets were never closed.

Therefore:

```text
Valid
⇔ every closing bracket matches stack top
AND stack is empty at the end
```

Hence the algorithm is correct.

---

# 💻 Approach 1 — Java

```java
//Approach-1 (Stack with Direct Matching)
//T.C : O(n)
//S.C : O(n)

class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if ((ch == ')' && top != '(') ||
                    (ch == ']' && top != '[') ||
                    (ch == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
```

---

# 💻 Approach 1 — C++

```cpp
//Approach-1 (Stack with Direct Matching)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:
    bool isValid(string s) {
        stack<char> st;

        for (char ch : s) {
            if (ch == '(' || ch == '[' || ch == '{') {
                st.push(ch);
            }
            else {
                if (st.empty()) {
                    return false;
                }

                char top = st.top();
                st.pop();

                if ((ch == ')' && top != '(') ||
                    (ch == ']' && top != '[') ||
                    (ch == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return st.empty();
    }
};
```

---

# 🐍 Approach 1 — Python

```python
#Approach-1 (Stack with Direct Matching)
#T.C : O(n)
#S.C : O(n)

class Solution:
    def isValid(self, s: str) -> bool:
        stack = []

        for ch in s:
            if ch in "([{":
                stack.append(ch)
            else:
                if not stack:
                    return False

                top = stack.pop()

                if ((ch == ')' and top != '(') or
                    (ch == ']' and top != '[') or
                    (ch == '}' and top != '{')):
                    return False

        return not stack
```

---

# ⏱️ Approach 1 — Complexity

Every character is processed once.

Each opening bracket is pushed once and each matched closing bracket causes one pop.

Therefore:

```text
Time Complexity  : O(n)
Space Complexity : O(n)
```

The worst-case space occurs for a string such as:

```text
(((((([
```

where many opening brackets remain active simultaneously.

---

# ⚡ Approach 2 — Stack of Expected Closing Brackets

## 💡 Idea

The first approach stores the **opening bracket** and then checks which closing bracket should match it.

We can simplify this.

Instead of storing:

```text
(
[
{
```

store what we **expect to see later**:

```text
( → )
[ → ]
{ → }
```

So when we see an opening bracket:

```text
(
```

push:

```text
)
```

Then when a closing bracket arrives, the check becomes simply:

```text
current == stack.top()
```

If yes:

```text
pop
```

If no:

```text
false
```

This turns bracket matching into one generic comparison.

---

# 🏗️ Approach 2 — Architecture Diagram

```text
                         INPUT STRING
                              │
                              ▼
                    ┌──────────────────┐
                    │    stack = []    │
                    └────────┬─────────┘
                             │
                             ▼
                       Read s[i]
                             │
                    ┌────────┴────────┐
                    │                 │
                 Opening           Closing
                    │                 │
                    ▼                 ▼
            ┌────────────────┐   ┌───────────────┐
            │ Convert to     │   │ stack empty ? │
            │ expected closer│   └──────┬───┬────┘
            └───────┬────────┘          │Yes│No
                    │                   ▼   │
                    ▼                 false │
             ┌───────────────┐             ▼
             │ PUSH expected│      ┌────────────────┐
             │ closing char  │      │ top == current │
             └───────┬───────┘      │      ?         │
                     │              └──────┬───┬─────┘
                     │                     │No │Yes
                     │                     ▼   ▼
                     │                   false pop
                     │                         │
                     └─────────────────────────┘
                               │
                               ▼
                         Next character
                               │
                               ▼
                         End of string
                               │
                               ▼
                         stack empty?
                          /         \\
                        Yes          No
                         │            │
                         ▼            ▼
                       true         false
```

---

# 🔄 Approach 2 — Data Flow

```text
Opening Bracket
      │
      ▼
Convert to Expected Closer
      │
      ▼
Push onto Stack
      │
      ▼
Later Closing Bracket
      │
      ▼
Compare with Stack Top
      │
   ┌──┴──┐
 Match  No Match
   │        │
   ▼        ▼
  POP     false
   │
   ▼
Continue
```

---

# 🧪 Approach 2 — Detailed Dry Run

Consider:

```text
s = "({[]})"
```

### `(`

Push expected closer:

```text
push ')'
```

Stack:

```text
)
```

### `{`

Push:

```text
}
```

Stack:

```text
}
)
```

### `[`

Push:

```text
]
```

Stack:

```text
]
}
)
```

### `]`

Current:

```text
]
```

Top:

```text
]
```

Match → pop.

Stack:

```text
}
)
```

### `}`

Current:

```text
}
```

Top:

```text
}
```

Match → pop.

Stack:

```text
)
```

### `)`

Current:

```text
)
```

Top:

```text
)
```

Match → pop.

Stack:

```text
[]
```

Final result:

```text
true
```

---

# ✅ Approach 2 — Correctness Proof

The stack stores the **exact closing bracket expected next** for every currently open bracket.

For example:

```text
(
```

stores:

```text
)
```

and:

```text
[
```

stores:

```text
]
```

Because nested brackets close in LIFO order, the closing bracket that arrives next must be the stack top.

Thus:

```text
stack.top() == current
```

is exactly the condition for a valid closing bracket.

If the comparison fails, the nesting is invalid.

If all characters are processed and the stack is empty, every expected closing bracket was received.

Therefore the algorithm correctly determines whether the string is valid.

---

# 💻 Approach 2 — Java

```java
//Approach-2 (Stack of Expected Closing Brackets)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            }
            else if (ch == '[') {
                stack.push(']');
            }
            else if (ch == '{') {
                stack.push('}');
            }
            else {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
```

---

# 💻 Approach 2 — C++

```cpp
//Approach-2 (Stack of Expected Closing Brackets)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
public:
    bool isValid(string s) {
        stack<char> st;

        for (char ch : s) {
            if (ch == '(') {
                st.push(')');
            }
            else if (ch == '[') {
                st.push(']');
            }
            else if (ch == '{') {
                st.push('}');
            }
            else {
                if (st.empty() || st.top() != ch) {
                    return false;
                }

                st.pop();
            }
        }

        return st.empty();
    }
};
```

---

# 🐍 Approach 2 — Python

```python
#Approach-2 (Stack of Expected Closing Brackets)
#T.C : O(n)
#S.C : O(n)

class Solution2:
    def isValid(self, s: str) -> bool:
        stack = []

        for ch in s:
            if ch == '(':
                stack.append(')')
            elif ch == '[':
                stack.append(']')
            elif ch == '{':
                stack.append('}')
            else:
                if not stack or stack.pop() != ch:
                    return False

        return not stack
```

---

# ⏱️ Approach 2 — Complexity

Every character is processed once.

Therefore:

```text
Time Complexity  : O(n)
Space Complexity : O(n)
```

---

# 🆚 Approach 1 vs Approach 2

| Feature        | Approach 1          | Approach 2                  |
| -------------- | ------------------- | --------------------------- |
| Stack stores   | Opening brackets    | Expected closing brackets   |
| Matching logic | Explicit conditions | Direct equality             |
| Time           | `O(n)`              | `O(n)`                      |
| Space          | `O(n)`              | `O(n)`                      |
| Main advantage | Very explicit       | Cleaner generic matching    |
| Easy to debug  | ✅                  | ✅                          |
| Easy to extend | Good                | ✅                          |
| Main idea      | Match actual pairs  | Store expected future state |

Both approaches are optimal in time complexity.

---

# 🧠 Why Approach 2 Is More Elegant

Approach 1 asks three separate questions:

```text
')' → top must be '('
']' → top must be '['
'}' → top must be '{'
```

Approach 2 changes what is stored.

Instead of remembering:

```text
What did I open?
```

we remember:

```text
What am I expecting to close?
```

So the verification becomes:

```text
current closing bracket
          ==
      stack.top()
```

This is a useful general programming technique:

> **Store the state you will need for the next operation, not necessarily the state you originally observed.**

---

# 🔬 Why a Counter Alone Does Not Work

A balance counter can tell us how many brackets are currently open.

But it cannot tell us **which type** is open.

Consider:

```text
([)]
```

The balance is:

```text
( → 1
[ → 2
) → 1
] → 0
```

The final balance is zero.

But the string is invalid because:

```text
[ was opened last
```

so `]` should have been the first closer.

Instead, `)` appears first.

Therefore:

```text
Counter → tracks quantity
Stack   → tracks quantity + order + type
```

For LeetCode 20, we need the stack.

---

# 🧪 Important Invalid Cases

## Wrong bracket type

```text
(]
```

Expected:

```text
)
```

Received:

```text
]
```

Result:

```text
false
```

---

## Incorrect nesting

```text
([)]
```

The counts are balanced, but the order is wrong.

```text
false
```

---

## Extra closing bracket

```text
)
```

The stack is empty when the closing bracket arrives.

```text
false
```

---

## Unclosed opening brackets

```text
(([
```

The scan finishes with a non-empty stack.

```text
false
```

---

# ✅ Important Valid Cases

```text
()
```

```text
()[]{}
```

```text
{[]}
```

```text
([{}])
```

```text
((()))
```

All satisfy the required matching and nesting order.

---

# 🎯 Pattern Recognition

LeetCode 20 is one of the most important examples of the:

```text
Matching Pairs + Stack
```

pattern.

Whenever you see:

```text
nested structure
+
matching symbols
+
last opened must close first
```

think:

```text
STACK
```

This pattern appears in:

```text
Parentheses validation
Expression parsing
Nested structures
HTML/XML-style matching
Undo-style nested states
Compiler parsing
```

---

# 🧠 Interview Thought Process

When seeing this problem in an interview, think in this order:

```text
Step 1:
Do counts alone work?
        ↓
No, because of ([)]

Step 2:
Do I need nesting order?
        ↓
Yes

Step 3:
Which data structure gives LIFO?
        ↓
Stack

Step 4:
What should the stack store?
        ↓
Opening brackets
OR
Expected closing brackets

Step 5:
What must be true at the end?
        ↓
Stack must be empty
```

---

# 🗣️ Interview Explanation

A clean answer:

> "I use a stack because the most recently opened bracket must be the first one closed. For every opening bracket I push it onto the stack. For every closing bracket I check whether the stack is empty and whether its top is the matching opening bracket. If not, the string is invalid; otherwise I pop the opening bracket. At the end, the stack must be empty. This gives O(n) time and O(n) space."

A more concise version using Approach 2:

> "I push the expected closing bracket whenever I see an opening bracket. Then every closing bracket only needs to match the stack top. If it does, pop; otherwise return false. Finally, the stack must be empty."

---

# ⚠️ Common Mistakes

## 1. Checking only counts

Wrong idea:

```text
number of '(' == number of ')'
```

This cannot detect:

```text
([)]
```

---

## 2. Using a counter instead of a stack

A counter loses bracket type and nesting order.

---

## 3. Forgetting the empty-stack case

For:

```text
]
```

there is no opening `[` to match it.

Return `false` immediately.

---

## 4. Forgetting the final empty-stack check

For:

```text
((
```

there is no mismatch during scanning, but the string is still invalid because brackets remain open.

---

## 5. Using FIFO instead of LIFO

Nested structures require:

```text
Last In → First Out
```

so a queue is incorrect.

---

## 6. Matching different bracket types

The exact pairs are:

```text
( ↔ )
[ ↔ ]
{ ↔ }
```

---

# 🧾 Quick Revision

## Approach 1

```text
Opening → push opening bracket

Closing →
    if stack empty → false
    if top doesn't match → false
    else pop

End → stack must be empty
```

## Approach 2

```text
( → push )
[ → push ]
{ → push }

Closing bracket →
    if stack empty → false
    if top != current → false
    else pop

End → stack must be empty
```

---

# 🔑 Core Formula / Rule

```text
Valid Parentheses
=
Correct Matching
+
Correct Nesting Order
+
No Unmatched Brackets
```

Or, in stack form:

```text
Every closing bracket
        ↓
Must match stack.top()
        ↓
And final stack must be empty
```

---

# 📊 Complexity Summary

Let:

```text
n = s.length()
```

### Approach 1

```text
Time Complexity  : O(n)
Space Complexity : O(n)
```

### Approach 2

```text
Time Complexity  : O(n)
Space Complexity : O(n)
```

Both are optimal in time because every character must be inspected.

The best memory usage cannot be reduced below the size needed to represent the active nesting in the general case, which can be `O(n)`.

---

# ⭐ One-Line Insight

> **The most recently opened bracket must be the first one closed, so use a stack to enforce LIFO matching.**

---

# 🏷️ Tags

`String, Stack, Parentheses, Brackets, LIFO, Matching Pairs, String Parsing, Simulation, Expression Parsing, Data Structures, LeetCode, Easy`

---

# 📚 Final Takeaway

The complete problem can be visualized as:

```text
                    INPUT STRING
                         │
                         ▼
                  Read character
                         │
              ┌──────────┴──────────┐
              │                     │
          Opening                 Closing
              │                     │
              ▼                     ▼
            PUSH              Check Stack Top
                                    │
                              ┌─────┴─────┐
                              │           │
                            Match      Mismatch
                              │           │
                              ▼           ▼
                             POP        false
                              │
                              ▼
                           Continue
                              │
                              ▼
                         End of string
                              │
                              ▼
                         Stack empty?
                          /         \\
                        Yes          No
                         │            │
                         ▼            ▼
                       true         false
```

The deepest concept is:

```text
Nested structure
      ↓
LIFO ordering
      ↓
Stack
```

And the cleanest optimization is to store the expected closer:

```text
( → )
[ → ]
{ → }
```

so validation becomes a direct stack-top comparison.

For LeetCode 20, the essential checklist is:

```text
✅ Opening bracket → push
✅ Closing bracket → match stack top
✅ Match → pop
✅ Mismatch → false
✅ Empty stack on closing → false
✅ Non-empty stack at end → false
```

Once this pattern becomes familiar, a large class of nested-bracket and expression-parsing problems becomes much easier to recognize and solve. citeturn104999search0
