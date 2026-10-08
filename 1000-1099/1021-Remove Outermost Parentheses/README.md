# 1021. Remove Outermost Parentheses | String | Stack | Java & C++

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode (Main):** https://leetcode.com/u/Shailu03/

**LeetCode (Alternate):** https://leetcode.com/u/ShailendraLeetcode03/

---

## 🔗 Problem Link

[LeetCode 1021. Remove Outermost Parentheses](https://leetcode.com/problems/remove-outermost-parentheses/)

---

# 📝 Problem Statement

You are given a valid parentheses string `s`.

A valid parentheses string can be decomposed into one or more **primitive** valid parentheses strings.

A primitive valid parentheses string is a non-empty valid parentheses string that cannot be split into two non-empty valid parentheses strings.

For every primitive component, remove its outermost pair of parentheses and return the resulting string.

For example:

```text
s = "(()())(())"

Primitive components:

(()())
(())

Remove their outermost pairs:

(()())  ->  ()()
(())    ->  ()

Answer = "()()()"
```

---

# 🎯 What Does “Outermost” Mean?

For one primitive:

```text
(  inner  )
^         ^
|         |
outer     outer
opening   closing
```

Only those two parentheses are removed.

All parentheses inside them remain.

For:

```text
((()))
```

we remove only the first and last parentheses:

```text
((()))
  ↓
(())
```

---

# 🧠 Core Observation

The key is to track the **current nesting depth**.

Think of `depth` as:

```text
Number of currently open '(' parentheses
```

Rules:

```text
'('  -> depth increases by 1
')'  -> depth decreases by 1
```

The outermost opening parenthesis of a primitive is exactly the parenthesis that changes:

```text
0 -> 1
```

The outermost closing parenthesis is exactly the parenthesis that changes:

```text
1 -> 0
```

Those are the only two characters we need to skip.

---

# 📐 Depth Visualization

For:

```text
(()())
```

track the depth:

```text
Character:   (  (  )  (  )  )

Before:      0  1  2  1  2  1
After:       1  2  1  2  1  0
```

The outer pair is:

```text
( : 0 -> 1
) : 1 -> 0
```

So we skip those two.

The remaining characters are:

```text
()()
```

---

# 🏗️ Solution Architecture

```text
                         INPUT
                           |
                           v
                  Valid Parentheses String
                           |
                           v
                    depth = 0
                           |
                           v
                    Process each char
                           |
                +----------+----------+
                |                     |
              '('                   ')'
                |                     |
                v                     v
          depth > 0 ?              depth--
           /       \                  |
         YES       NO                 v
          |         |           depth > 0 ?
       append      skip          /       \
          |         |          YES        NO
          +----+----+           |          |
               |             append       skip
               v                |          |
             depth++            +----+-----+
               |                     |
               +----------+----------+
                          |
                          v
                    Next character
                          |
                          v
                         DONE
                          |
                          v
                       RESULT
```

---

# 🔥 Approach 1 — Depth Counting

This is the recommended approach.

We maintain:

```text
depth
```

For every character:

### Opening `(`

If:

```text
depth == 0
```

then this `(` starts a new primitive.

It is the outermost opening parenthesis, so skip it.

Otherwise:

```text
depth > 0
```

so the `(` is inside the primitive and must be kept.

Then increase `depth`.

### Closing `)`

First decrease `depth`.

If after decreasing:

```text
depth == 0
```

then this `)` closes the primitive's outermost `(`, so skip it.

Otherwise it is an inner closing parenthesis and must be kept.

---

# ⭐ The Most Important Rule

For opening parenthesis:

```text
Check first, then increment.
```

For closing parenthesis:

```text
Decrement first, then check.
```

So:

```text
'(':
    if depth > 0 -> keep
    depth++

')':
    depth--
    if depth > 0 -> keep
```

This timing is the heart of the solution.

---

# ✅ Java — Approach 1

```java
//Approach-1 (Depth Counting)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public String removeOuterParentheses(String s) {

        StringBuilder answer = new StringBuilder();

        int depth = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {

                if (depth > 0) {
                    answer.append(ch);
                }

                depth++;

            } else {

                depth--;

                if (depth > 0) {
                    answer.append(ch);
                }
            }
        }

        return answer.toString();
    }
}
```

---

# ✅ C++ — Approach 1

```cpp
//Approach-1 (Depth Counting)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    string removeOuterParentheses(string s) {

        string answer;
        int depth = 0;

        for (char ch : s) {

            if (ch == '(') {

                if (depth > 0) {
                    answer += ch;
                }

                depth++;

            } else {

                depth--;

                if (depth > 0) {
                    answer += ch;
                }
            }
        }

        return answer;
    }
};
```

---

# 🔍 Detailed Java Explanation

## 1. Create the answer

```java
StringBuilder answer = new StringBuilder();
```

We need to construct a new string containing all non-outermost parentheses.

`StringBuilder` is efficient for repeated appends.

---

## 2. Initialize depth

```java
int depth = 0;
```

At the beginning, no opening parenthesis is active.

---

## 3. Process `(`

```java
if (ch == '(') {

    if (depth > 0) {
        answer.append(ch);
    }

    depth++;
}
```

Suppose:

```text
depth = 0
```

The current `(` starts a primitive, so it is outermost.

We skip it.

Suppose:

```text
depth = 1
```

Then another `(` is nested inside the primitive, so we keep it.

---

## 4. Process `)`

```java
depth--;
```

The current `)` closes one currently open parenthesis.

If the new depth becomes zero:

```text
depth = 0
```

then the primitive has just ended, so this is its outermost closing parenthesis.

We skip it.

Otherwise, it is an inner closing parenthesis and we keep it.

---

# 🧪 Dry Run 1

Input:

```text
s = "(()())(())"
```

Primitive decomposition:

```text
(()())
(())
```

## First primitive: `(()())`

```text
char   depth before   action        depth after
------------------------------------------------
(           0         skip               1
(           1         keep               2
)           2         keep               1
(           1         keep               2
)           2         keep               1
)           1         skip               0
```

Output:

```text
()()
```

## Second primitive: `(())`

```text
char   depth before   action        depth after
------------------------------------------------
(           0         skip               1
(           1         keep               2
)           2         keep               1
)           1         skip               0
```

Output:

```text
()
```

Combined result:

```text
()() + ()

= ()()()
```

---

# 🧪 Dry Run 2

Input:

```text
s = "((()))"
```

Trace:

```text
char   before   action   after
--------------------------------
(        0      skip       1
(        1      keep       2
(        2      keep       3
)        3      keep       2
)        2      keep       1
)        1      skip       0
```

Result:

```text
(())
```

---

# 🧪 Dry Run 3

Input:

```text
s = "()"
```

Processing:

```text
(
 depth = 0 -> outermost -> skip
 depth = 1

)
 depth = 1 -> decrease to 0
 outermost -> skip
```

Final result:

```text
""
```

This is correct because `()` itself is a primitive and its complete outer pair is removed.

---

# 🧪 Dry Run 4 — Nested + Multiple Primitives

Input:

```text
s = "(()())(())(()(()))"
```

Primitive components:

```text
(()())
(())
(()(()))
```

After removing each primitive's outer pair:

```text
(()())  -> ()()
(())    -> ()
(()(()))-> ()(())
```

So:

```text
Answer = ()()()(())
```

---

# 🚀 Approach 2 — Explicit Primitive Boundary Tracking

A second approach is to explicitly identify where every primitive starts and ends.

A primitive ends exactly when:

```text
depth == 0
```

after processing its closing parenthesis.

For example:

```text
(()())(())
```

Depth returns to zero at the ends of:

```text
(()())
       ^

(())
    ^
```

If a primitive occupies:

```text
[start, end]
```

then its outermost parentheses are at:

```text
start
end
```

so we append only:

```text
s[start + 1 ... end - 1]
```

---

# ✅ Java — Approach 2

```java
//Approach-2 (Primitive Boundary Tracking)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public String removeOuterParentheses(String s) {

        StringBuilder answer = new StringBuilder();

        int depth = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
            }

            if (depth == 0) {
                answer.append(s, start + 1, i);
                start = i + 1;
            }
        }

        return answer.toString();
    }
}
```

---

# ✅ C++ — Approach 2

```cpp
//Approach-2 (Primitive Boundary Tracking)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    string removeOuterParentheses(string s) {

        string answer;

        int depth = 0;
        int start = 0;

        for (int i = 0; i < s.size(); i++) {

            if (s[i] == '(') {
                depth++;
            } else {
                depth--;
            }

            if (depth == 0) {
                answer += s.substr(start + 1, i - start - 1);
                start = i + 1;
            }
        }

        return answer;
    }
};
```

---

# ⚖️ Approach Comparison

| Feature                 | Approach 1: Depth Counting      | Approach 2: Primitive Boundaries     |
| ----------------------- | ------------------------------- | ------------------------------------ |
| Main idea               | Skip parentheses at outer depth | Extract inner part of each primitive |
| Time                    | `O(n)`                          | `O(n)`                               |
| Auxiliary Space         | `O(1)`                          | `O(1)`                               |
| Output Space            | `O(n)`                          | `O(n)`                               |
| Extra boundary variable | No                              | Yes                                  |
| Simplicity              | ✅ Excellent                    | Good                                 |
| Recommended             | ✅ Yes                          | Good alternative                     |

---

# 🏆 Why Approach 1 Is Best

Approach 1 is the most direct implementation of the key observation.

It does not need to explicitly:

```text
find primitive start
find primitive end
extract substring
```

Instead, every character is handled exactly when we know whether it is outermost.

So the logic is:

```text
outer -> skip
inner -> keep
```

This makes it short, efficient, and easy to remember.

---

# 🔬 Why a Stack Is Not Necessary

At first glance, parentheses problems often suggest:

```text
Stack
```

A stack would work here, but it stores information we do not need.

We do not care which particular `(` matches which `)`.

We only care about:

```text
How many '(' are currently open?
```

So instead of a full stack:

```text
Stack
  ↓
Only need its size
  ↓
Depth counter
```

This reduces the auxiliary space to `O(1)`.

---

# 📐 Complete Architecture Diagram

```text
                        VALID PARENTHESES
                              STRING
                                |
                                v
                          depth = 0
                                |
                                v
                       +----------------+
                       | Read character |
                       +--------+-------+
                                |
                  +-------------+-------------+
                  |                           |
                '('                         ')'
                  |                           |
                  v                           v
           depth == 0?                  depth-- first
              /    \                         |
            YES     NO                       v
             |       |                  depth == 0?
            skip    keep                  /      \
             |       |                  YES       NO
             +---+---+                   |          |
                 |                    skip         keep
                 v                       |          |
              depth++                    +-----+----+
                 |                              |
                 +---------------+--------------+
                                 |
                                 v
                            next character
                                 |
                                 v
                               DONE
                                 |
                                 v
                              RESULT
```

---

# 🧠 Mathematical View of Depth

For a valid parentheses string, define:

```text
depth(i) = number of '(' minus number of ')' seen up to position i
```

For an opening parenthesis:

```text
depth changes by +1
```

For a closing parenthesis:

```text
depth changes by -1
```

A primitive begins when:

```text
depth: 0 -> 1
```

and ends when:

```text
depth: 1 -> 0
```

Therefore the two characters that perform these transitions are exactly the outermost parentheses.

---

# ✅ Correctness Proof

We prove that the algorithm removes exactly the outermost pair from every primitive.

## Lemma 1 — Opening parenthesis at depth 0 is outermost

If an opening `(` is encountered while:

```text
depth = 0
```

then no other parenthesis is currently open.

Therefore this `(` starts a new primitive and is its outermost opening parenthesis.

The algorithm skips it.

---

## Lemma 2 — Opening parenthesis at depth greater than 0 is inside the primitive

If:

```text
depth > 0
```

before processing an opening `(`, then another opening parenthesis is already active.

Therefore the current `(` is nested inside the primitive and must remain.

The algorithm keeps it.

---

## Lemma 3 — Closing parenthesis that makes depth 0 is outermost

For a closing `)` the algorithm first executes:

```text
depth--
```

If this produces:

```text
depth = 0
```

then the closing parenthesis has matched the primitive's outermost opening parenthesis.

Therefore it is the outermost closing parenthesis and must be removed.

---

## Lemma 4 — Every other closing parenthesis is internal

If after processing `)`:

```text
depth > 0
```

then there are still open parentheses around it.

So the current `)` lies inside the primitive and must be kept.

---

## Conclusion

Every primitive loses exactly:

```text
1 outermost '('
+
1 outermost ')'
```

while all inner parentheses remain unchanged.

Therefore the algorithm returns the required string.

---

# ⚠️ Common Mistakes

## Mistake 1 — Removing Only the First and Last Character

Wrong for:

```text
(()())(())
```

There are multiple primitives, so each one has its own outer pair.

---

## Mistake 2 — Checking Closing Parenthesis Before Decreasing Depth

Incorrect idea:

```java
if (depth > 0) {
    answer.append(')');
}
depth--;
```

This would keep the primitive's outermost closing `)` because its depth is `1` before decrementing.

Correct:

```java
depth--;

if (depth > 0) {
    answer.append(')');
}
```

---

## Mistake 3 — Incrementing Before Checking `(`

Incorrect:

```java
depth++;
if (depth > 0) {
    answer.append('(');
}
```

This always sees at least depth `1` and may incorrectly keep the outermost opening parenthesis.

Correct:

```java
if (depth > 0) {
    answer.append('(');
}
depth++;
```

---

## Mistake 4 — Using a Stack Unnecessarily

A stack is valid, but unnecessary because every opening parenthesis is identical.

Only the number of currently open parentheses matters.

Use:

```text
depth
```

instead of:

```text
Stack<Character>
```

---

## Mistake 5 — Removing Every Parenthesis at Depth 1

Not every parenthesis associated with depth 1 is outermost.

The important transitions are:

```text
Opening: 0 -> 1
Closing: 1 -> 0
```

Those identify the primitive boundaries.

---

# 🧪 Edge Cases

## 1. Smallest Possible Input

```text
s = "()"
```

Result:

```text
""
```

---

## 2. Multiple Simple Primitives

```text
s = "()()()"
```

Each primitive is just `()`.

After removing all outer pairs:

```text
""
```

---

## 3. Completely Nested Primitive

```text
s = "((()))"
```

Result:

```text
(())
```

---

## 4. Multiple Nested Primitives

```text
s = "(()())(())"
```

Result:

```text
()()()
```

---

# ⏱️ Complexity Analysis

Let:

```text
n = s.length()
```

Every character is processed exactly once.

Therefore:

```text
Time Complexity: O(n)
```

For the depth-counting algorithm, the only auxiliary variable is:

```text
depth
```

plus the output builder.

Therefore:

```text
Auxiliary Space: O(1)
Output Space: O(n)
```

Overall storage including the returned answer is:

```text
O(n)
```

---

# 📊 Complexity Summary

```text
┌────────────────────────────────┐
│ Time Complexity    : O(n)      │
│ Auxiliary Space    : O(1)      │
│ Output Space       : O(n)      │
└────────────────────────────────┘
```

---

# 🎯 Interview Explanation

A clean interview explanation:

> I use a depth counter to track the current nesting level. For an opening parenthesis, if the current depth is zero, it is the outermost opening parenthesis of a primitive, so I skip it; otherwise I keep it. For a closing parenthesis, I decrease the depth first. If the new depth becomes zero, that closing parenthesis is the outermost closing parenthesis, so I skip it; otherwise I keep it. Thus every primitive loses exactly its outer pair. The solution runs in O(n) time and uses O(1) auxiliary space apart from the output.

---

# 🔥 Pattern Recognition

When you see a problem involving:

```text
Valid parentheses
+
Nested structure
+
Outer / inner level
```

think:

```text
DEPTH COUNTER
```

The common pattern is:

```text
'(' -> depth++
')' -> depth--
```

Then use the depth to determine whether the current character is:

```text
outermost
```

or:

```text
inside
```

---

# 🎓 Reusable Template

## Opening Parenthesis

```java
if (ch == '(') {

    if (depth > 0) {
        answer.append(ch);
    }

    depth++;
}
```

## Closing Parenthesis

```java
else {

    depth--;

    if (depth > 0) {
        answer.append(ch);
    }
}
```

This pattern is useful for:

```text
Parentheses depth
Nested expressions
Primitive parentheses
Bracket parsing
Balanced structures
```

---

# 📌 Quick Cheat Sheet

```text
depth = 0

For '(':
    if depth > 0 -> keep
    depth++

For ')':
    depth--
    if depth > 0 -> keep
```

Outer pair:

```text
0 -> 1   opening
1 -> 0   closing
```

Everything else is inside the primitive.

---

# 🗺️ Complete Problem Flow

```text
                 Valid Parentheses String
                           |
                           v
                     Track depth
                           |
             +-------------+-------------+
             |                           |
          Outer pair                 Inner pair
             |                           |
             v                           v
           Skip                         Keep
             |                           |
             +-------------+-------------+
                           |
                           v
                    Process all chars
                           |
                           v
                         RESULT
```

---

# 🏷️ Tags

```text
String
Stack
Depth
Parentheses
Simulation
String Manipulation
Parsing
```

---

# 🏆 Recommended GitHub Title

```text
1021. Remove Outermost Parentheses | String | Stack | Java & C++
```

---

# 📁 Recommended Folder Structure

```text
C:\Leetcode_Solutions
│
└── 1000-1099
    │
    └── 1021 Remove Outermost Parentheses
        │
        ├── README.md
        ├── Solution.java
        └── Solution.cpp
```

---

# 💻 VS Code Folder Creation

```powershell
cd C:\Leetcode_Solutions

cd "1000-1099"

mkdir "1021 Remove Outermost Parentheses"

cd "1021 Remove Outermost Parentheses"

ni README.md
ni Solution.java
ni Solution.cpp
```

---

# 🚀 Git Commands

```powershell
cd C:\Leetcode_Solutions

git status

git add "1000-1099/1021 Remove Outermost Parentheses"

git status

git commit -m "Add String solution for LeetCode 1021"
git pull origin main --rebase
git push origin main
```

---

# 📌 Repository Structure

```text
Leetcode_Solutions/
│
├── 1000-1099/
│   │
│   └── 1021 Remove Outermost Parentheses/
│       ├── README.md
│       ├── Solution.java
│       └── Solution.cpp
│
└── ...
```

---

# ⭐ Final Takeaway

The entire problem reduces to one observation:

```text
Outermost opening parenthesis:
0 -> 1

Outermost closing parenthesis:
1 -> 0
```

So:

```text
                 VALID STRING
                       |
                       v
                  TRACK DEPTH
                       |
             +---------+---------+
             |                   |
          0 -> 1              1 -> 0
             |                   |
             v                   v
        Skip '('             Skip ')'
             \                   /
              \                 /
               +------+- -------+
                      |
                      v
                 Keep all inner
                  parentheses
                      |
                      v
                    ANSWER
```

The reusable pattern is:

```text
VALID PARENTHESES
       ↓
TRACK DEPTH
       ↓
IDENTIFY OUTERMOST LEVEL
       ↓
SKIP OUTER PAIR
       ↓
KEEP INNER PARENTHESES
```

---

# 🔗 LeetCode

https://leetcode.com/problems/remove-outermost-parentheses/
