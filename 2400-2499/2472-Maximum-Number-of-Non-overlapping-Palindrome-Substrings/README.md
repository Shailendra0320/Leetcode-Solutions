# 2472. Maximum Number of Non-overlapping Palindrome Substrings

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

# 🧩 Problem Statement

You are given a string `s` and an integer `k`.

Select as many substrings as possible such that:

1. Every selected substring is a palindrome.
2. Every selected substring has length at least `k`.
3. Selected substrings do not overlap.
4. The number of selected substrings is maximized.

Return the maximum number.

[LeetCode 2472 — Maximum Number of Non-overlapping Palindrome Substrings](https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/)

---

# 💡 What Is the Question Really Asking?

There are two subproblems:

```text
1. Find palindromic substrings of length >= k.
2. Pick the maximum number of non-overlapping ones.
```

The important objective is **maximum count**, not maximum total length.

So:

```text
one long palindrome
```

may be worse than:

```text
two shorter non-overlapping palindromes
```

when both are valid.

---

# 🏗️ Solution Architecture

```text
                         String s
                            |
                            v
               +------------------------+
               | Palindrome Detection   |
               | pal[i][j]              |
               +-----------+------------+
                           |
                           v
               +------------------------+
               | Keep length >= k       |
               +-----------+------------+
                           |
                           v
               +------------------------+
               | Non-overlap Selection  |
               +-----------+------------+
                           |
                  +--------+--------+
                  |                 |
                  v                 v
             Prefix DP        Earliest-End
                              Greedy
                  |                 |
                  +--------+--------+
                           |
                           v
                    Maximum Count
```

---

# 🔍 Core Observation

For substring `s[i...j]`:

```text
s[i...j] is palindrome
```

iff:

```text
s[i] == s[j]
AND
s[i+1...j-1] is palindrome
```

Therefore:

```text
pal[i][j] =
s[i] == s[j]
&&
(j - i < 2 || pal[i+1][j-1])
```

Then every valid palindrome can be viewed as an interval:

```text
[i, j]
```

Now the second part is an interval-selection problem.

---

# 1️⃣ Approach 1 — Palindrome DP + Prefix DP

## Palindrome DP

Define:

```text
pal[i][j] = true
```

when `s[i...j]` is a palindrome.

Process substrings by increasing length because:

```text
pal[i][j]
```

depends on:

```text
pal[i+1][j-1]
```

### Base Cases

```text
length = 1 → always palindrome
length = 2 → palindrome if characters match
```

### Recurrence

```text
pal[i][j] =
s[i] == s[j]
AND
(j - i < 2 OR pal[i+1][j-1])
```

---

## Prefix DP

Define:

```text
dp[i]
=
maximum number of valid palindromes
using the first i characters
```

For a palindrome `[left, right]` with length at least `k`:

```text
dp[right + 1]
=
max(dp[right + 1], dp[left] + 1)
```

Why `dp[left]`?

Because `dp[left]` only uses indices:

```text
0 ... left-1
```

so it cannot overlap `[left, right]`.

We also have the option to skip the current character:

```text
dp[right + 1] = max(dp[right + 1], dp[right])
```

---

## 🔄 Approach 1 Flow

```text
Build pal[i][j]
      |
      v
Scan right endpoint
      |
      +---- skip current position
      |
      +---- find [left,right] palindrome
                  |
                  v
             length >= k
                  |
                  v
        dp[left] + 1
                  |
                  v
            maximize dp
```

## ✅ Java

```java
//Approach-1 (Palindrome DP + Prefix DP)
//T.C : O(n^2)
//S.C : O(n^2)

class Solution {
    public int maxPalindromes(String s, int k) {

        int n = s.length();
        boolean[][] pal = new boolean[n][n];

        for (int len = 1; len <= n; len++) {
            for (int left = 0; left + len <= n; left++) {

                int right = left + len - 1;

                if (s.charAt(left) == s.charAt(right) &&
                    (len <= 2 || pal[left + 1][right - 1])) {
                    pal[left][right] = true;
                }
            }
        }

        int[] dp = new int[n + 1];

        for (int right = 0; right < n; right++) {

            // Skip s[right]
            dp[right + 1] = Math.max(dp[right + 1], dp[right]);

            // Select a valid palindrome ending at right
            for (int left = 0; left <= right; left++) {

                int length = right - left + 1;

                if (length >= k && pal[left][right]) {
                    dp[right + 1] = Math.max(
                        dp[right + 1],
                        dp[left] + 1
                    );
                }
            }
        }

        return dp[n];
    }
}
```

## ✅ C++

```cpp
//Approach-1 (Palindrome DP + Prefix DP)
//T.C : O(n^2)
//S.C : O(n^2)

class Solution {
public:
    int maxPalindromes(string s, int k) {

        int n = s.size();
        vector<vector<bool>> pal(n, vector<bool>(n, false));

        for (int len = 1; len <= n; len++) {
            for (int left = 0; left + len <= n; left++) {

                int right = left + len - 1;

                if (s[left] == s[right] &&
                    (len <= 2 || pal[left + 1][right - 1])) {
                    pal[left][right] = true;
                }
            }
        }

        vector<int> dp(n + 1, 0);

        for (int right = 0; right < n; right++) {

            dp[right + 1] = max(dp[right + 1], dp[right]);

            for (int left = 0; left <= right; left++) {

                int length = right - left + 1;

                if (length >= k && pal[left][right]) {
                    dp[right + 1] = max(
                        dp[right + 1],
                        dp[left] + 1
                    );
                }
            }
        }

        return dp[n];
    }
};
```

---

# 2️⃣ Approach 2 — Palindrome DP + Earliest-Finish Greedy

Once all valid palindromes are known, each one is simply an interval:

```text
[left, right]
```

We want the maximum number of non-overlapping intervals, and every selected interval contributes exactly:

```text
1
```

This is the classic **unweighted interval scheduling** setting.

The greedy rule is:

> **Choose the feasible interval that finishes earliest.**

Why?

If two available intervals are:

```text
A = [l1, r1]
B = [l2, r2]
```

and:

```text
r1 < r2
```

then choosing `A` leaves at least as much room for every future interval as choosing `B`.

So an earliest finishing feasible palindrome is always safe.

Because we scan `right` from left to right, the first feasible palindrome ending at that position is an earliest-finishing choice.

## 🔄 Approach 2 Flow

```text
Build palindrome table
        |
        v
Scan right endpoints left → right
        |
        v
Find a valid palindrome starting
after previous selected end
        |
       YES
        |
        v
Select it
        |
        v
Update previousEnd
        |
        v
Continue
```

## ✅ Java

```java
//Approach-2 (Palindrome DP + Earliest-Finish Greedy)
//T.C : O(n^2)
//S.C : O(n^2)

class Solution2 {
    public int maxPalindromes(String s, int k) {

        int n = s.length();
        boolean[][] pal = new boolean[n][n];

        for (int len = 1; len <= n; len++) {
            for (int left = 0; left + len <= n; left++) {

                int right = left + len - 1;

                if (s.charAt(left) == s.charAt(right) &&
                    (len <= 2 || pal[left + 1][right - 1])) {
                    pal[left][right] = true;
                }
            }
        }

        int answer = 0;
        int previousEnd = -1;

        for (int right = 0; right < n; right++) {

            for (int left = previousEnd + 1; left <= right; left++) {

                int length = right - left + 1;

                if (length >= k && pal[left][right]) {
                    answer++;
                    previousEnd = right;
                    break;
                }
            }
        }

        return answer;
    }
}
```

## ✅ C++

```cpp
//Approach-2 (Palindrome DP + Earliest-Finish Greedy)
//T.C : O(n^2)
//S.C : O(n^2)

class Solution2 {
public:
    int maxPalindromes(string s, int k) {

        int n = s.size();
        vector<vector<bool>> pal(n, vector<bool>(n, false));

        for (int len = 1; len <= n; len++) {
            for (int left = 0; left + len <= n; left++) {

                int right = left + len - 1;

                if (s[left] == s[right] &&
                    (len <= 2 || pal[left + 1][right - 1])) {
                    pal[left][right] = true;
                }
            }
        }

        int answer = 0;
        int previousEnd = -1;

        for (int right = 0; right < n; right++) {

            for (int left = previousEnd + 1; left <= right; left++) {

                int length = right - left + 1;

                if (length >= k && pal[left][right]) {
                    answer++;
                    previousEnd = right;
                    break;
                }
            }
        }

        return answer;
    }
};
```

---

# 🧪 Detailed Dry Run

Consider:

```text
s = "abaccdbbd"
k = 3
```

Some valid palindromes are:

```text
"aba"  → [0,2]
"bccb" → [3,6]
"dbbd" → [5,8]
```

The important point is that `"aba"` and `"bccb"` are compatible:

```text
[0,2] then [3,6]
```

so we can select:

```text
"aba"
"bccb"
```

giving:

```text
answer = 2
```

### Prefix DP interpretation

After selecting:

```text
[0,2]
```

we have:

```text
dp[3] = 1
```

When `[3,6]` is considered:

```text
dp[7] = max(dp[7], dp[3] + 1)
      = max(dp[7], 2)
```

Therefore the best count becomes:

```text
2
```

---

# 🔬 Another Small Example

```text
s = "aaaa"
k = 2
```

Possible valid palindromes include:

```text
"aa" → [0,1]
"aa" → [1,2]
"aa" → [2,3]
"aaa" → [0,2]
"aaa" → [1,3]
"aaaa" → [0,3]
```

We want the **maximum count**, not the longest palindrome.

So the best choice is:

```text
[0,1] + [2,3]
```

giving:

```text
2
```

Choosing:

```text
[0,3]
```

would give only:

```text
1
```

This example clearly shows why maximizing substring count is important.

---

# 🧠 Correctness Proof — Approach 1

### Lemma 1 — Palindrome table is correct

For every substring `s[i...j]`:

- If `i == j`, it is a palindrome.
- If `j = i + 1`, it is a palindrome exactly when the two characters match.
- Otherwise, it is a palindrome exactly when:
  - `s[i] == s[j]`, and
  - `s[i+1...j-1]` is a palindrome.

Therefore the recurrence correctly identifies every palindrome.

### Lemma 2 — DP transition never creates an overlap

For a selected palindrome:

```text
[left, right]
```

we add:

```text
dp[left] + 1
```

`dp[left]` uses only indices:

```text
0 ... left-1
```

so it lies completely before the palindrome.

Therefore selected intervals never overlap.

### Lemma 3 — DP considers every valid choice

Every valid palindrome `[left, right]` is considered when processing `right`.

The transition either:

```text
skips the current character
```

or:

```text
selects the palindrome
```

Therefore every possible valid solution is represented by some sequence of DP transitions.

Hence `dp[n]` is the maximum possible number of non-overlapping palindromic substrings.

---

# 🧠 Correctness Proof — Approach 2

The palindrome table identifies all valid intervals.

Now consider the selection phase.

Suppose the earliest-finish feasible palindrome is:

```text
A = [l1, r1]
```

and some optimal solution starts with another feasible interval:

```text
B = [l2, r2]
```

with:

```text
r1 <= r2
```

Replace `B` with `A`.

Because `A` finishes no later than `B`, every interval that came after `B` is still compatible with `A`.

Therefore there is always an optimal solution beginning with the earliest-finishing feasible palindrome.

Repeating this argument after every selection proves that the greedy strategy maximizes the number of non-overlapping intervals.

---

# ⚠️ Common Mistakes

## 1. Maximizing length instead of count

Wrong objective:

```text
maximum total characters
```

Correct objective:

```text
maximum number of substrings
```

---

## 2. Forgetting the `k` constraint

A palindrome is usable only if:

```text
length >= k
```

---

## 3. Allowing overlapping palindromes

If one selected interval is:

```text
[l1, r1]
```

the next must start at:

```text
> r1
```

not:

```text
>= r1
```

---

## 4. Building palindrome DP in the wrong order

Since:

```text
pal[i][j]
```

uses:

```text
pal[i+1][j-1]
```

the inner substring must already be known.

Processing by increasing length solves this naturally.

---

## 5. Picking the longest palindrome

A longer palindrome is not automatically better.

The goal is:

```text
maximum number
```

so shorter compatible palindromes may produce a better answer.

---

# 🎯 Interview Thought Process

```text
Need substrings
      ↓
Need palindromes
      ↓
Use palindrome DP
      ↓
Need length >= k
      ↓
Every valid palindrome becomes [left,right]
      ↓
Need maximum number of non-overlapping intervals
      ↓
Recognize interval scheduling
      ↓
Use:
  Prefix DP
  or
  Earliest-finish Greedy
```

This is the main pattern to remember.

---

# 🧩 Pattern Recognition

This problem combines two reusable patterns.

### Pattern 1 — Palindrome DP

Whenever you see:

```text
substring
+
palindrome
```

consider:

```text
pal[i][j]
```

with:

```text
s[i] == s[j]
&&
inner substring is palindrome
```

### Pattern 2 — Interval Scheduling

Whenever you see:

```text
intervals
+
non-overlapping
+
maximize number
```

consider:

```text
earliest finish time
```

So:

```text
2472
=
Palindrome DP
+
Interval Scheduling
```

---

# 🔥 Deep Mental Model

The most powerful transformation is:

```text
String
  ↓
Palindrome substrings
  ↓
Intervals [l,r]
  ↓
Discard length < k
  ↓
Maximum-cardinality non-overlapping interval set
```

The problem stops being "just a string problem".

It becomes:

```text
Generate intervals
+
Select intervals
```

This decomposition is extremely useful in interviews.

---

# 📌 Formula Sheet

### Palindrome

```text
pal[i][j] =
s[i] == s[j]
AND
(j - i < 2 || pal[i+1][j-1])
```

### Valid palindrome

```text
pal[i][j] == true
AND
j - i + 1 >= k
```

### Prefix DP

```text
dp[i]
=
maximum number using first i characters
```

Transition:

```text
dp[r+1] = max(dp[r+1], dp[r])
```

and for a valid palindrome:

```text
dp[r+1] = max(dp[r+1], dp[l] + 1)
```

### Non-overlap

Intervals `[l1,r1]` and `[l2,r2]` are non-overlapping when:

```text
r1 < l2
```

after ordering them from left to right.

---

# 📈 Complexity Analysis

Let:

```text
n = s.length()
```

## Approach 1

Palindrome preprocessing:

```text
O(n²)
```

Prefix DP:

```text
O(n²)
```

Total:

```text
O(n²)
```

Space:

```text
O(n²)
```

---

## Approach 2

Palindrome preprocessing:

```text
O(n²)
```

Greedy scan:

```text
O(n²)
```

Total:

```text
O(n²)
```

Space:

```text
O(n²)
```

---

# 🏆 Which Approach Should You Prefer?

### Approach 1 — Prefix DP

Best when:

- You want the safest systematic derivation.
- You are comfortable with DP.
- You want the optimal transition explicitly represented.

Core idea:

```text
dp[left] + 1
```

---

### Approach 2 — Earliest-Finish Greedy

Best when:

- You recognize interval scheduling.
- Every chosen interval has equal value.
- You want the selection phase to be very elegant.

Core idea:

```text
Finish as early as possible.
```

Both approaches are:

```text
O(n²) time
O(n²) space
```

---

# 🧠 Edge Cases

### `k = 1`

Every character is a palindrome.

Therefore every character can be selected separately:

```text
answer = n
```

### No valid palindrome

If no palindrome has length at least `k`:

```text
answer = 0
```

### Entire string is a palindrome

We may choose the entire string, but that does not guarantee it is optimal if multiple shorter palindromes can produce a larger count.

### Highly repetitive string

Strings such as:

```text
"aaaaaa"
```

have many overlapping palindromes.

The selection strategy becomes essential.

---

# 🚀 Why This Is a Good DP + Greedy Problem

The problem demonstrates a valuable algorithmic habit:

> **Separate object generation from object selection.**

First:

```text
Generate all valid palindrome intervals.
```

Then:

```text
Select an optimal compatible subset.
```

The first phase uses:

```text
Dynamic Programming
```

The second phase can use:

```text
Dynamic Programming
```

or:

```text
Greedy Interval Scheduling
```

This two-stage design is broadly reusable.

---

# 📝 Final Summary

The key is to recognize that every valid palindrome can be represented as an interval:

```text
[left, right]
```

First use palindrome DP to determine:

```text
Is s[left...right] a palindrome?
```

Then enforce:

```text
right - left + 1 >= k
```

Finally, maximize the number of non-overlapping intervals using either:

```text
Prefix DP
```

or:

```text
Earliest-Finish Greedy
```

The overall complexity is:

```text
Time  : O(n²)
Space : O(n²)
```

---

# 💎 One-Line Insight

> **Convert every palindrome of length at least `k` into an interval, then maximize the number of non-overlapping intervals using prefix DP or earliest-finish greedy.**

---

# 🧠 Interview Cheat Sheet

```text
Palindrome?
    ↓
pal[i][j]

Minimum length?
    ↓
j - i + 1 >= k

Represent it as?
    ↓
Interval [i,j]

Cannot overlap?
    ↓
nextStart > previousEnd

Maximize what?
    ↓
Number of intervals

Patterns?
    ↓
Palindrome DP
+
Interval Scheduling
```

---

## 🏷️ Tags

`Dynamic Programming, String, Palindrome, Palindrome DP, Greedy, Interval Scheduling, Prefix DP, Substring, Non-overlapping Intervals, LeetCode, Medium`
