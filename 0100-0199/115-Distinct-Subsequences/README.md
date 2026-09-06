# 115. Distinct Subsequences

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

# Problem Statement

Given two strings `s` and `t`, return the number of distinct subsequences of `s` which equals `t`.

A subsequence is formed by deleting zero or more characters without changing the relative order of the remaining characters.

Example:

```text
s = "abcde"
t = "ace"
```

`"ace"` is a subsequence because we choose `a -> c -> e`.

---

# Examples

## Example 1

```text
s = "rabbbit"
t = "rabbit"

Answer = 3
```

There are three ways to choose two `b` characters from the three available `b`s while preserving order.

```text
r a b b b i t
    ^ ^       -> choose b1, b2
    ^   ^     -> choose b1, b3
      ^ ^     -> choose b2, b3
```

So:

```text
3
```

---

## Example 2

```text
s = "babgbag"
t = "bag"

Answer = 5
```

There are five different index selections that produce `"bag"`.

---

# Constraints

```text
1 <= s.length, t.length <= 1000
s and t consist of English letters.
```

---

# What Is the Question Really Asking?

We are not searching for `t` as a substring.

We are counting:

> **How many different ways can characters be selected from `s`, while preserving order, so that the selected characters form exactly `t`?**

For example:

```text
s = "aaa"
t = "aa"
```

There are three valid index selections:

```text
(0,1)
(0,2)
(1,2)
```

Therefore the answer is:

```text
3
```

This is a **counting subsequences** problem, which strongly suggests Dynamic Programming.

---

# Core Observation

Suppose we are processing:

```text
s[i-1]
```

and:

```text
t[j-1]
```

There are two cases.

## Case 1: Characters Are Different

```text
s[i-1] != t[j-1]
```

The source character cannot match the current target character.

So we must skip it:

```text
dp[i][j] = dp[i-1][j]
```

---

## Case 2: Characters Are Equal

```text
s[i-1] == t[j-1]
```

Now there are two choices.

### Choice 1: Take the character

Use `s[i-1]` to match `t[j-1]`.

Remaining problem:

```text
dp[i-1][j-1]
```

### Choice 2: Skip the character

Do not use `s[i-1]`.

Remaining problem:

```text
dp[i-1][j]
```

Therefore:

```text
dp[i][j] =
dp[i-1][j-1] + dp[i-1][j]
```

---

# DP Decision Diagram

```text
                    Compare
                 s[i-1] vs t[j-1]
                       |
             +---------+---------+
             |                   |
          Different            Same
             |                   |
             v                   v
           SKIP             +-----+-----+
             |               |           |
             v              TAKE        SKIP
          dp[i-1][j]         |           |
                             v           v
                         dp[i-1][j-1]  dp[i-1][j]
                              \           /
                               \         /
                                +-------+
                                    |
                                  dp[i][j]
```

---

# DP State

Define:

```text
dp[i][j]
```

as:

> Number of distinct subsequences of the first `i` characters of `s` that form the first `j` characters of `t`.

So:

```text
dp[i][j] =
ways to form t[0 .. j-1]
using s[0 .. i-1]
```

This definition makes the base cases straightforward.

---

# Base Cases

## Empty Target

There is exactly one way to form an empty string:

```text
choose nothing
```

Therefore:

```text
dp[i][0] = 1
```

for every `i`.

---

## Empty Source

A non-empty target cannot be formed from an empty source.

Therefore:

```text
dp[0][j] = 0
```

for every:

```text
j > 0
```

---

# 2D DP Architecture

```text
                 s
                 |
                 v
        +------------------+
        | Process characters|
        | from left to right|
        +------------------+
                 |
                 v
       Compare with target t
                 |
        +--------+--------+
        |                 |
      equal            different
        |                 |
        v                 v
   take / skip           skip
        |                 |
        +--------+--------+
                 |
                 v
              dp[n][m]
                 |
                 v
              Answer
```

---

# Approach 1: 2D Dynamic Programming

## Idea

Build a table of size:

```text
(n + 1) x (m + 1)
```

where:

```text
n = s.length()
m = t.length()
```

For every `(i, j)`:

```text
if s[i-1] == t[j-1]:

    dp[i][j] = dp[i-1][j-1] + dp[i-1][j]

else:

    dp[i][j] = dp[i-1][j]
```

The final answer is:

```text
dp[n][m]
```

---

## Java Code

```java
//Approach-1 (2D Dynamic Programming)
//T.C : O(n * m)
//S.C : O(n * m)

class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        long[][] dp = new long[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] =
                        dp[i - 1][j - 1] +
                        dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return (int) dp[n][m];
    }
}
```

---

## C++ Code

```cpp
//Approach-1 (2D Dynamic Programming)
//T.C : O(n * m)
//S.C : O(n * m)

class Solution {
public:
    int numDistinct(string s, string t) {
        int n = s.length();
        int m = t.length();

        vector<vector<long long>> dp(
            n + 1,
            vector<long long>(m + 1, 0)
        );

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (s[i - 1] == t[j - 1]) {
                    dp[i][j] =
                        dp[i - 1][j - 1] +
                        dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return (int) dp[n][m];
    }
};
```

---

# Approach 1: Dry Run

Consider:

```text
s = "bab"
t = "ba"
```

Initial table:

```text
        ""  b  a
""       1  0  0
b        1  0  0
ba       1  0  0
bab      1  0  0
```

Process first `b`:

```text
b == b
```

So:

```text
dp[1][1] = dp[0][0] + dp[0][1]
         = 1 + 0
         = 1
```

Now:

```text
        ""  b  a
""       1  0  0
b        1  1  0
```

Process `a`:

```text
a == a
```

So:

```text
dp[2][2] = dp[1][1] + dp[1][2]
         = 1 + 0
         = 1
```

Therefore there is one way to form `"ba"`.

When the final `b` is processed, it can be used to form the `b` in the target, but it cannot be used for the `a`.

Final answer remains:

```text
1
```

---

# Why Recursion Alone Is Too Slow

The same decision can first be written recursively:

```text
if characters match:

    take
    OR
    skip
```

This creates a branching recursion tree:

```text
                    (i,j)
                   /                     TAKE     SKIP
                 |         |
              (i-1,j-1)  (i-1,j)
                /  \       /                 /    \     /                  ...   ...  ...   ...
```

The same `(i, j)` states are recomputed many times.

So plain recursion is exponential.

Dynamic Programming stores the result of each state once.

---

# Approach 2: 1D Dynamic Programming

## Why Optimize?

In the 2D transition:

```text
dp[i][j]
```

depends only on:

```text
dp[i-1][j-1]
dp[i-1][j]
```

That means we only need the previous row.

So we can compress:

```text
2D DP
```

into:

```text
1D DP
```

---

# 1D State

Define:

```text
dp[j]
```

as the number of ways to form the first `j` characters of `t` using the source characters processed so far.

Initialize:

```text
dp[0] = 1
```

because the empty target has one way.

For every source character:

```text
if s[i] == t[j-1]:

    dp[j] += dp[j-1]
```

---

# Why We Iterate Backward

This is one of the most important details.

Suppose:

```text
s[i] == t[j-1]
```

We want `dp[j-1]` from the **previous source-character state**.

Therefore we must process:

```text
j = m -> 1
```

instead of:

```text
j = 1 -> m
```

### Forward Iteration Problem

If we go left to right:

```text
dp[j-1]
```

may have already been updated using the current source character.

That would allow the same source character to be used more than once.

### Correct Order

```text
m -> m-1 -> ... -> 2 -> 1
```

This preserves the old `dp[j-1]`.

---

# 1D DP Diagram

```text
Before processing s[i]:

dp = [1,  a,  b,  c,  d]
             ^

Process target from RIGHT to LEFT:

d -> c -> b -> a

So every dp[j-1] is still from
the previous source-character state.
```

---

## Java Code

```java
//Approach-2 (1D Dynamic Programming)
//T.C : O(n * m)
//S.C : O(m)

class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        long[] dp = new long[m + 1];

        dp[0] = 1;

        for (int i = 0; i < n; i++) {

            for (int j = m; j >= 1; j--) {

                if (s.charAt(i) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return (int) dp[m];
    }
}
```

---

## C++ Code

```cpp
//Approach-2 (1D Dynamic Programming)
//T.C : O(n * m)
//S.C : O(m)

class Solution {
public:
    int numDistinct(string s, string t) {
        int n = s.length();
        int m = t.length();

        vector<long long> dp(m + 1, 0);

        dp[0] = 1;

        for (int i = 0; i < n; i++) {

            for (int j = m; j >= 1; j--) {

                if (s[i] == t[j - 1]) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return (int) dp[m];
    }
};
```

---

# Approach 2: Dry Run

Take:

```text
s = "aaa"
t = "aa"
```

Initially:

```text
dp = [1,0,0]
```

---

## Process first `a`

Compare with target:

```text
aa
```

Backward:

```text
j = 2:
a != a? No, they are equal
dp[2] += dp[1]
dp[2] = 0

j = 1:
a == a
dp[1] += dp[0]
dp[1] = 1
```

Now:

```text
dp = [1,1,0]
```

---

## Process second `a`

Again backward.

```text
j = 2:
dp[2] += dp[1]
dp[2] = 1

j = 1:
dp[1] += dp[0]
dp[1] = 2
```

Now:

```text
dp = [1,2,1]
```

---

## Process third `a`

```text
j = 2:
dp[2] += dp[1]
       = 1 + 2
       = 3
```

Then:

```text
j = 1:
dp[1] += dp[0]
       = 2 + 1
       = 3
```

Final:

```text
dp = [1,3,3]
```

Answer:

```text
3
```

Exactly as expected:

```text
(0,1)
(0,2)
(1,2)
```

---

# Correctness Proof

## Lemma 1: Empty Target

There is exactly one way to create the empty target:

```text
select no characters
```

Therefore:

```text
dp[i][0] = 1
```

is correct.

---

## Lemma 2: Different Characters

If:

```text
s[i-1] != t[j-1]
```

then `s[i-1]` cannot represent `t[j-1]`.

So every valid subsequence must skip `s[i-1]`.

Therefore:

```text
dp[i][j] = dp[i-1][j]
```

---

## Lemma 3: Matching Characters

If:

```text
s[i-1] == t[j-1]
```

every valid solution belongs to exactly one of two groups:

```text
1. Use s[i-1]
2. Do not use s[i-1]
```

If we use it:

```text
dp[i-1][j-1]
```

ways exist.

If we skip it:

```text
dp[i-1][j]
```

ways exist.

So:

```text
dp[i][j]
=
dp[i-1][j-1] +
dp[i-1][j]
```

No valid subsequence is counted twice because the two groups are disjoint.

---

## Theorem

The DP counts every valid subsequence exactly once.

For every source character:

- mismatch → skip
- match → take or skip

Thus every possible valid index selection is represented by exactly one path in the DP.

Therefore:

```text
dp[n][m]
```

is exactly the number of distinct subsequences of `s` equal to `t`.

---

# Complexity Analysis

Let:

```text
n = s.length()
m = t.length()
```

## Approach 1

```text
Time Complexity:  O(n * m)
Space Complexity: O(n * m)
```

## Approach 2

```text
Time Complexity:  O(n * m)
Space Complexity: O(m)
```

### Best Approach

```text
Approach 2: 1D Dynamic Programming
```

because it has the same time complexity while reducing space from:

```text
O(n * m)
```

to:

```text
O(m)
```

---

# Edge Cases

## 1. Target Longer Than Source

```text
s = "abc"
t = "abcd"
```

Impossible.

```text
answer = 0
```

---

## 2. Both Strings Equal

```text
s = "abc"
t = "abc"
```

Only one way.

```text
answer = 1
```

---

## 3. No Matching Characters

```text
s = "abc"
t = "xyz"
```

No subsequence exists.

```text
answer = 0
```

---

## 4. Repeated Characters

```text
s = "aaaa"
t = "aa"
```

Answer:

```text
C(4,2) = 6
```

DP naturally counts all six index selections.

---

## 5. Empty Target

Conceptually:

```text
t = ""
```

There is one way:

```text
choose nothing
```

So:

```text
answer = 1
```

---

# Common Mistakes

## Mistake 1: Treating It as a Substring Problem

Characters do not need to be adjacent.

```text
abcde
```

contains subsequence:

```text
ace
```

---

## Mistake 2: Greedy Matching

Always choosing the first matching character does not count all possibilities.

For:

```text
aaa -> aa
```

greedy finds one path, but the answer is `3`.

---

## Mistake 3: Forgetting the Skip Choice

When characters match:

```text
take + skip
```

not just:

```text
take
```

---

## Mistake 4: Forward Loop in 1D DP

The optimized DP must process:

```text
j = m -> 1
```

because otherwise the current source character can be reused.

---

## Mistake 5: Wrong Empty-Target Base Case

Remember:

```text
empty target -> 1 way
```

not `0`.

---

# Interview Thought Process

When you see:

```text
count subsequences
```

think:

```text
DP
```

Then ask:

```text
Can current source character match current target character?
```

If no:

```text
skip
```

If yes:

```text
take OR skip
```

That gives:

```text
dp[i][j] =
    dp[i-1][j-1] + dp[i-1][j]
```

Finally ask:

```text
Can I reduce the DP dimension?
```

Since only the previous row is required:

```text
2D -> 1D
```

and because the previous `dp[j-1]` must be preserved:

```text
iterate j backwards
```

---

# Pattern Recognition

This problem belongs to the classic:

```text
Subsequence + Counting + Dynamic Programming
```

pattern.

Whenever a problem asks:

```text
How many ways?
```

and each character creates a:

```text
take / skip
```

decision, DP is usually a strong candidate.

---

# Mental Model

Imagine every character in `s` asks:

```text
"Should I use myself to match the next character of t?"
```

If the answer can be yes:

```text
USE
or
SKIP
```

If it cannot:

```text
SKIP
```

The DP counts all these valid decisions.

---

# 2D to 1D Optimization

The 2D state is:

```text
dp[i][j]
```

and it uses:

```text
dp[i-1][j-1]
dp[i-1][j]
```

So only the previous row is required.

Conceptually:

```text
Previous row:

dp[i-1][j-1] ----+
                 |
                 v
              dp[i][j]
                 ^
                 |
dp[i-1][j] ------+
```

Therefore we can maintain one array.

The price is that update order becomes important.

```text
2D:
previous row exists separately

1D:
previous row is overwritten

Therefore:
update from RIGHT -> LEFT
```

---

# Final Code to Remember

```java
//Approach-2 (1D Dynamic Programming)
//T.C : O(n * m)
//S.C : O(m)

class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        long[] dp = new long[m + 1];

        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = m; j >= 1; j--) {

                if (s.charAt(i) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return (int) dp[m];
    }
}
```

---

# Final Summary

The problem asks us to count how many subsequences of `s` equal `t`.

The key decision is:

```text
If s[i] != t[j]:
    skip s[i]

If s[i] == t[j]:
    take s[i]
    OR
    skip s[i]
```

This gives the DP recurrence:

```text
match:
    dp[i][j] = dp[i-1][j-1] + dp[i-1][j]

mismatch:
    dp[i][j] = dp[i-1][j]
```

The straightforward solution uses a 2D table:

```text
O(n * m) time
O(n * m) space
```

Because each state depends only on the previous row, we can optimize to:

```text
O(n * m) time
O(m) space
```

The optimized version must iterate the target backwards so that the current source character is not reused multiple times.

---

# One-Line Insight

> **When the current characters match, count both possibilities—take the source character or skip it; otherwise, skip it.**

---

# Tags

`Dynamic Programming` `String` `Subsequence` `Counting` `1D DP` `2D DP` `String DP` `LeetCode` `Hard`
