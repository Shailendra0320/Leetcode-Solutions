# 1520. Maximum Number of Non-Overlapping Substrings

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

# 🧩 Problem Statement

You are given a string `s` containing lowercase English letters.

We need to select a collection of **non-empty substrings** satisfying:

1. The selected substrings do not overlap.
2. If a substring contains a character `c`, it must contain **all occurrences** of `c` in the entire string.
3. Among all valid collections, maximize the number of selected substrings.
4. If multiple collections have the same maximum count, minimize their total length.

It is guaranteed that the minimum-total-length solution is unique.

### Link

[LeetCode 1520 — Maximum Number of Non-Overlapping Substrings](https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/)

---

# 💡 What Is the Question Really Asking?

The important condition is:

> If a substring contains a character, it must contain **every occurrence** of that character in the whole string.

So every valid substring behaves like a **closed interval of character occurrences**.

The problem becomes:

```text
String
  ↓
Find first/last occurrence of each character
  ↓
Build minimal valid intervals
  ↓
Choose non-overlapping intervals
  ↓
Maximize count
  ↓
Break ties with minimum total length
```

---

# 🧠 Core Observation

For every character `c`, compute:

```text
first[c] = first occurrence of c
last[c]  = last occurrence of c
```

For example, in:

```text
s = "adefaddaccc"
```

we have:

```text
a → [0,7]
d → [1,6]
e → [2,2]
f → [3,3]
c → [8,10]
```

If a substring contains `a`, it must include the whole interval:

```text
[0,7]
```

If a substring contains `d`, it must include:

```text
[1,6]
```

The ranges interact. When a character inside a candidate interval has a later `last` occurrence, the candidate must expand.

---

# 🏗️ Solution Architecture

```text
                         String s
                            |
                            v
                +----------------------+
                | First / Last        |
                | occurrence of each  |
                | character            |
                +----------+-----------+
                           |
                           v
                +----------------------+
                | Start only at a first |
                | occurrence             |
                +----------+-----------+
                           |
                           v
                +----------------------+
                | Expand right endpoint |
                | using last[c]          |
                +----------+-----------+
                           |
                           v
                +----------------------+
                | Any first[c] < left?  |
                +----------+-----------+
                           |
                 +---------+---------+
                 |                   |
                Yes                  No
                 |                   |
                 v                   v
              Invalid            Valid interval
                                     |
                                     v
                         +------------------------+
                         | Greedy selection       |
                         | disjoint / nested      |
                         +------------------------+
                                     |
                                     v
                              Final substrings
```

---

# 🔍 Step 1 — First and Last Occurrences

Create arrays of size `26`:

```text
first[26]
last[26]
```

For each character, record its first and last position.

This gives the smallest possible range that can contain all occurrences of that character.

---

# 🔍 Step 2 — Why Can We Start Only at `first[c]`?

Suppose a substring starts at an occurrence of `c` that is **not** the first occurrence.

Then there exists another occurrence of `c` before the substring.

But the substring already contains `c`, so it is required to contain **all** occurrences of `c`.

Contradiction.

Therefore every useful minimal candidate must start at:

```text
first[c]
```

This reduces the number of possible starting positions to at most `26`.

---

# 🔍 Step 3 — Expand the Candidate

For a starting position `left`:

```text
right = last[s[left]]
```

Now scan every position inside `[left, right]`.

If we see character `c`, the candidate must include all of `c`:

```text
right = max(right, last[c])
```

So the interval may grow while it is being scanned.

Example:

```text
Start:
[a ........ a]

Inside the range we find b:
[a .. b ..... a ..... b]

Now b forces the interval to expand.
```

Continue until `right` stops changing.

---

# 🚫 When Is a Candidate Invalid?

Suppose our candidate starts at:

```text
left
```

while scanning, we see a character `c` whose first occurrence is:

```text
first[c] < left
```

That means `c` also appears before our candidate.

Since our candidate contains `c`, it would have to include that earlier occurrence too.

But the candidate cannot move left because we are constructing the minimal interval for this start.

Therefore:

```text
first[c] < left
```

means:

```text
candidate is invalid
```

---

# ✅ What Is a Minimal Valid Interval?

A candidate:

```text
[left, right]
```

is minimal valid when:

- `left` is the first occurrence of `s[left]`.
- Every character inside the interval has all its occurrences inside the interval.
- `right` is expanded only as far as necessary.

This is the only type of interval we need to consider.

---

# 1️⃣ Approach 1 — Greedy Expansion + Replacement

This approach directly exploits the special structure of the valid intervals.

We scan possible starts from left to right.

For each first occurrence:

```text
1. Build its minimal valid interval.
2. Reject it if invalid.
3. If it is disjoint from the previous selection, add it.
4. If it overlaps the previous selection, replace the previous one.
```

The replacement step is the subtle part.

---

# 🧠 Why Can We Replace an Overlapping Interval?

Suppose the previously selected interval is:

```text
A = [L1, R1]
```

and the current valid candidate is:

```text
B = [L2, R2]
```

with:

```text
L1 < L2
```

and the two intervals overlap.

Because `A` is valid, it contains every occurrence of every character appearing in `B`.

Therefore the current interval cannot extend beyond `A`:

```text
R2 <= R1
```

So `B` is contained inside `A`.

Replacing:

```text
A → B
```

keeps the same number of selected substrings while reducing the total length.

That exactly matches the problem's tie-break rule.

---

# 🔄 Approach 1 Flowchart

```text
             Start
               |
               v
      Compute first[] / last[]
               |
               v
      Scan i = 0 ... n-1
               |
               v
      Is i first[c]?
          /        \
        No          Yes
         |           |
         |           v
         |    Build minimal interval
         |           |
         |           v
         |      Valid candidate?
         |        /       \
         |      No         Yes
         |       |           |
         |       |           v
         |       |      left > previousEnd?
         |       |        /          \
         |       |      Yes          No
         |       |       |            |
         |       |       v            v
         |       |      Add       Replace previous
         +-------+---------------------+
                        |
                        v
                     Answer
```

---

# ✅ Java — Approach 1

```java
//Approach-1 (Greedy Interval Expansion + Replacement)
//T.C : O(26 * n) = O(n)
//S.C : O(26)

class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence of every character.
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<String> answer = new ArrayList<>();
        int previousEnd = -1;

        for (int i = 0; i < n; i++) {

            int c = s.charAt(i) - 'a';

            // Only a first occurrence can start
            // a minimal valid substring.
            if (first[c] != i) {
                continue;
            }

            int left = i;
            int right = last[c];
            boolean valid = true;

            // Expand until every contained character
            // is completely covered.
            for (int j = left; j <= right; j++) {

                int current = s.charAt(j) - 'a';

                // This character occurs before left,
                // so this candidate cannot be valid.
                if (first[current] < left) {
                    valid = false;
                    break;
                }

                right = Math.max(right, last[current]);
            }

            if (!valid) {
                continue;
            }

            String current = s.substring(left, right + 1);

            if (left > previousEnd) {

                // Disjoint from the previous interval.
                answer.add(current);
                previousEnd = right;

            } else {

                // Current interval is nested inside the
                // previous selected interval, so it is shorter.
                answer.set(answer.size() - 1, current);
                previousEnd = right;
            }
        }

        return answer;
    }
}
```

---

# ✅ C++ — Approach 1

```cpp
//Approach-1 (Greedy Interval Expansion + Replacement)
//T.C : O(26 * n) = O(n)
//S.C : O(26)

class Solution {
public:
    vector<string> maxNumOfSubstrings(string s) {

        int n = s.size();

        vector<int> first(26, n);
        vector<int> last(26, -1);

        // Find first and last occurrence of every character.
        for (int i = 0; i < n; i++) {
            int c = s[i] - 'a';

            first[c] = min(first[c], i);
            last[c] = i;
        }

        vector<string> answer;
        int previousEnd = -1;

        for (int i = 0; i < n; i++) {

            int c = s[i] - 'a';

            if (first[c] != i) {
                continue;
            }

            int left = i;
            int right = last[c];
            bool valid = true;

            for (int j = left; j <= right; j++) {

                int current = s[j] - 'a';

                if (first[current] < left) {
                    valid = false;
                    break;
                }

                right = max(right, last[current]);
            }

            if (!valid) {
                continue;
            }

            string current = s.substr(left, right - left + 1);

            if (left > previousEnd) {
                answer.push_back(current);
                previousEnd = right;
            } else {
                answer.back() = current;
                previousEnd = right;
            }
        }

        return answer;
    }
};
```

---

# 2️⃣ Approach 2 — Generate Valid Intervals + Earliest-Finish Greedy

The same problem can be explained using classical interval scheduling.

## Step 1

Generate every minimal valid interval:

```text
[left, right]
```

using the same first/last occurrence expansion.

There are at most `26` possible candidate starts because the string contains only lowercase English letters.

## Step 2

Sort these intervals by their right endpoint.

Then use the classic greedy rule:

> **Choose the interval that finishes earliest and does not overlap the previous choice.**

This maximizes the number of selected intervals.

For this problem's special valid-interval structure, overlapping candidates are nested, so earliest-finish selection also gives the minimum-total-length solution among maximum-count solutions.

---

# 🔄 Approach 2 Flowchart

```text
       First / Last occurrence
                 |
                 v
       Generate valid intervals
                 |
                 v
       Sort by ending position
                 |
                 v
       left > previousEnd ?
            /           \
          Yes            No
           |              |
           v              v
        Select          Skip
           |              |
           +------+-------+
                  |
                  v
              Next interval
                  |
                  v
                Answer
```

---

# ✅ Java — Approach 2

```java
//Approach-2 (Valid Intervals + Earliest-Finish Greedy)
//T.C : O(26 * n + 26 log 26) = O(n)
//S.C : O(26)

class Solution2 {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Build all minimal valid intervals.
        for (int c = 0; c < 26; c++) {

            if (last[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];
            boolean valid = true;

            for (int i = left; i <= right; i++) {

                int current = s.charAt(i) - 'a';

                if (first[current] < left) {
                    valid = false;
                    break;
                }

                right = Math.max(right, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Earliest finish first.
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> answer = new ArrayList<>();
        int previousEnd = -1;

        for (int[] interval : intervals) {

            int left = interval[0];
            int right = interval[1];

            if (left > previousEnd) {
                answer.add(s.substring(left, right + 1));
                previousEnd = right;
            }
        }

        return answer;
    }
}
```

---

# ✅ C++ — Approach 2

```cpp
//Approach-2 (Valid Intervals + Earliest-Finish Greedy)
//T.C : O(26 * n + 26 log 26) = O(n)
//S.C : O(26)

class Solution2 {
public:
    vector<string> maxNumOfSubstrings(string s) {

        int n = s.size();

        vector<int> first(26, n);
        vector<int> last(26, -1);

        for (int i = 0; i < n; i++) {
            int c = s[i] - 'a';

            first[c] = min(first[c], i);
            last[c] = i;
        }

        vector<pair<int, int>> intervals;

        // Build all minimal valid intervals.
        for (int c = 0; c < 26; c++) {

            if (last[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];
            bool valid = true;

            for (int i = left; i <= right; i++) {

                int current = s[i] - 'a';

                if (first[current] < left) {
                    valid = false;
                    break;
                }

                right = max(right, last[current]);
            }

            if (valid) {
                intervals.push_back({left, right});
            }
        }

        sort(
            intervals.begin(),
            intervals.end(),
            [](const pair<int, int>& a,
               const pair<int, int>& b) {
                return a.second < b.second;
            }
        );

        vector<string> answer;
        int previousEnd = -1;

        for (auto& interval : intervals) {

            int left = interval.first;
            int right = interval.second;

            if (left > previousEnd) {
                answer.push_back(
                    s.substr(left, right - left + 1)
                );
                previousEnd = right;
            }
        }

        return answer;
    }
};
```

---

# 🧪 Detailed Dry Run — Example 1

```text
s = "adefaddaccc"
```

First and last positions:

```text
a → [0,7]
d → [1,6]
e → [2,2]
f → [3,3]
c → [8,10]
```

### Candidate from `a`

Start:

```text
[0,7]
```

This becomes:

```text
"adefadda"
```

It is valid, but very large.

### Candidate from `d`

Start:

```text
[1,6]
```

Inside it is `a`, but:

```text
first[a] = 0 < 1
```

Therefore:

```text
d-candidate = invalid
```

### Candidate from `e`

```text
[2,2] → "e"
```

Valid.

### Candidate from `f`

```text
[3,3] → "f"
```

Valid.

### Candidate from `c`

```text
[8,10] → "ccc"
```

Valid.

Final selection:

```text
"e"
"f"
"ccc"
```

So the answer is:

```text
["e", "f", "ccc"]
```

---

# 🧪 Detailed Dry Run — Example 2

```text
s = "abbaccd"
```

Important valid candidates include:

```text
"abba"
"bb"
"cc"
"d"
```

Both of these collections have three substrings:

```text
["d", "abba", "cc"]
["d", "bb", "cc"]
```

But their total lengths are:

```text
1 + 4 + 2 = 7
```

and:

```text
1 + 2 + 2 = 5
```

So the required answer is:

```text
["d", "bb", "cc"]
```

This example demonstrates why the tie-break rule matters.

---

# 🧠 Why Minimal Valid Intervals Are Enough

Suppose `[l,r]` is a valid substring but it can be shortened to `[l,r']` while remaining valid.

Then the shorter one is always at least as useful because it:

```text
keeps the same number of chosen substrings
and
leaves more room for other substrings
```

So an optimal answer never needs a non-minimal version when a smaller valid interval is available.

This lets us focus only on the minimal interval associated with each possible character start.

---

# 🧩 Why Valid Minimal Intervals Are Disjoint or Nested

Suppose two valid minimal intervals are:

```text
A = [l1, r1]
B = [l2, r2]
```

with:

```text
l1 < l2
```

If they overlap, then every character in `B` also lies inside `A`.

Because `A` is valid, it contains all occurrences of those characters.

Therefore:

```text
r2 <= r1
```

So `B` is contained inside `A`.

Thus two minimal valid candidates cannot form a crossing pattern like:

```text
l1 < l2 <= r1 < r2
```

They are either:

```text
disjoint
```

or:

```text
nested
```

This special interval structure is what makes the greedy solution work so well.

---

# 🎯 Interview Thought Process

```text
Condition says:
if substring contains c,
it must contain all c's
        ↓
For each c, know first[c] and last[c]
        ↓
Any valid minimal substring starts at first[c]
        ↓
Expand right using last[characters inside]
        ↓
If some character starts before left → invalid
        ↓
Now we have minimal valid intervals
        ↓
Valid intervals are disjoint or nested
        ↓
Use greedy interval selection
        ↓
Maximum number
+
minimum total length
```

---

# 🧠 Pattern Recognition

This problem combines several reusable patterns.

### Pattern 1 — First / Last Occurrence

Whenever a substring has a condition involving **all occurrences** of a character:

```text
first[c]
last[c]
```

should be one of the first ideas you consider.

### Pattern 2 — Interval Expansion

Start with:

```text
[left, last[s[left]]]
```

and expand whenever a new character forces a larger right boundary.

### Pattern 3 — Interval Scheduling

After valid substrings become intervals:

```text
maximize count of non-overlapping intervals
```

points toward earliest-finish greedy.

### Pattern 4 — Nested Interval Replacement

When a later valid interval lies inside an already selected one:

```text
same count
+
shorter total length
```

so replacement is beneficial.

---

# 🔥 Deep Mental Model

Think of each character as carrying a mandatory range:

```text
c → [first[c], last[c]]
```

If you include `c`, you must include its entire range.

But adding that range may introduce another character.

That character brings its own mandatory range.

So the interval grows like a closure process:

```text
Start
  ↓
Character requires a range
  ↓
Range introduces new characters
  ↓
Those characters require more range
  ↓
Expand again
  ↓
Stop when stable
```

If expansion discovers a character whose first occurrence lies before the starting point, the candidate is impossible.

---

# 📌 Candidate Construction Formula

Start with:

```text
left = first[c]
right = last[c]
```

Then for every position `i` in the interval:

```text
if first[s[i]] < left:
    invalid

right = max(right, last[s[i]])
```

At the end:

```text
[left, right]
```

is the minimal valid substring for that start.

---

# ⚠️ Common Mistakes

## 1. Starting at every index

A valid minimal substring cannot start at a non-first occurrence.

---

## 2. Expanding only once

A newly discovered character can force the interval to grow again.

You must continue until the right boundary stabilizes.

---

## 3. Forgetting `first[c] < left`

This is the key invalidation rule.

If a contained character appears before the candidate starts, the candidate cannot satisfy the problem condition.

---

## 4. Maximizing total length

The primary objective is:

```text
maximum number of substrings
```

Only after that do we minimize:

```text
total length
```

---

## 5. Ignoring nested candidates

A large valid interval may contain several smaller valid intervals.

The smaller intervals can be much better because they can increase the number of selected substrings.

---

# 🧮 Complexity Analysis

Let:

```text
n = s.length()
```

There are only `26` possible lowercase letters.

For each possible first character, the expansion scans at most `n` positions.

Therefore:

```text
O(26n) = O(n)
```

### Approach 1

```text
Time  : O(26n) = O(n)
Space : O(26) = O(1)
```

apart from the returned result.

### Approach 2

Interval generation:

```text
O(26n)
```

Sorting at most 26 intervals:

```text
O(26 log 26)
```

Overall:

```text
Time  : O(n)
Space : O(26) = O(1)
```

apart from the returned result.

---

# ⚖️ Approach Comparison

| Feature                     | Approach 1                        | Approach 2                    |
| --------------------------- | --------------------------------- | ----------------------------- |
| First / Last occurrence     | Yes                               | Yes                           |
| Minimal interval generation | Yes                               | Yes                           |
| Selection strategy          | Greedy + replacement              | Sort + earliest finish        |
| Time                        | `O(n)`                            | `O(n)`                        |
| Extra space                 | `O(1)`                            | `O(1)`                        |
| Main idea                   | Exploit nested structure directly | Classical interval scheduling |
| Implementation              | More specialized                  | More general concept          |

---

# 🏆 Which Approach Should You Prefer?

### Approach 1 — Greedy + Replacement

Best for the actual LeetCode solution because it directly uses the special structure of this problem:

```text
disjoint OR nested
```

It avoids even the tiny sorting step.

### Approach 2 — Generate + Sort + Greedy

Best for learning because it decomposes the problem into:

```text
1. Generate valid intervals
2. Solve interval scheduling
```

That makes the connection to a classic greedy pattern very clear.

---

# 🧠 Correctness Proof

## Lemma 1 — Every valid minimal substring starts at a first occurrence

Suppose a valid substring starts at index `i` with character `c`, but:

```text
first[c] < i
```

Then the substring contains `c` but not its earlier occurrence.

That violates the rule requiring all occurrences of `c` to be included.

Therefore a minimal valid substring must start at:

```text
first[c]
```

---

## Lemma 2 — Expansion finds the minimal valid interval

Starting with:

```text
[left, last[s[left]]]
```

ensures that the first character is fully covered.

Whenever another character is encountered, extending to its `last` occurrence is necessary.

Repeating this until the interval stops expanding guarantees that every contained character is completely covered.

No unnecessary positions are included beyond the required right boundary.

Therefore the resulting interval is the minimal valid interval for that start.

---

## Lemma 3 — Overlapping minimal valid intervals are nested

Take two minimal valid intervals:

```text
A = [l1,r1]
B = [l2,r2]
```

with:

```text
l1 < l2
```

If they overlap, every character in `B` lies inside `A`.

Since `A` contains all occurrences of every character it contains, all required occurrences of those characters must also be inside `A`.

Hence:

```text
r2 <= r1
```

So `B` is contained inside `A`.

Thus overlapping candidates are nested.

---

## Lemma 4 — Replacement preserves the optimum

If a later candidate overlaps the previously selected candidate, Lemma 3 says it is nested inside the previous one.

Replacing the previous interval with the nested interval:

```text
keeps the same number of selected substrings
```

and:

```text
reduces total length
```

Therefore replacement is always safe and improves the tie-break objective.

---

## Theorem

The algorithm generates all relevant minimal valid substrings, selects them without overlap, maximizes their count, and minimizes total length among maximum-count selections.

Therefore it returns the required unique solution.

---

# 🚀 Why This Solution Is So Efficient

A naive solution could enumerate many substrings:

```text
O(n²) candidates
```

and then verify them or compare them with one another.

That would be too slow for:

```text
n = 100000
```

Instead, the solution uses the fact that the alphabet contains only:

```text
26 letters
```

So the number of useful candidate starts is tiny.

The algorithm effectively compresses the search space from:

```text
all O(n²) substrings
```

to:

```text
at most 26 meaningful minimal intervals
```

That is the major optimization.

---

# 📊 Example of the Nested Structure

Consider candidate intervals:

```text
A = [0,7]
B = [2,2]
C = [3,3]
D = [8,10]
```

Here:

```text
B ⊂ A
C ⊂ A
A and D are disjoint
```

Choosing:

```text
A + D
```

gives:

```text
2 substrings
```

while:

```text
B + C + D
```

gives:

```text
3 substrings
```

This illustrates why a large valid substring is not necessarily the best choice.

---

# 📝 Final Summary

The key insight is to turn the character condition into intervals.

For every character:

```text
first[c]
last[c]
```

define its mandatory occurrence range.

Then for each first occurrence:

```text
start at first[c]
expand right using last[characters inside]
reject if some character starts before left
```

This gives the minimal valid substrings.

Those intervals have a special structure:

```text
disjoint
OR
nested
```

so greedy selection works.

The most direct implementation:

```text
Disjoint → add
Nested   → replace larger interval
```

runs in:

```text
Time  : O(n)
Space : O(1)
```

apart from the returned answer.

---

# 💎 One-Line Insight

> **Convert each character into its `[first,last]` range, expand minimal valid intervals, then greedily keep disjoint intervals and replace larger nested ones with shorter candidates.**

---

# 🧠 Interview Cheat Sheet

```text
Condition:
contains c → must contain every c
        ↓
Compute first[c], last[c]
        ↓
Candidate starts only at first[c]
        ↓
Expand right using last[c]
        ↓
If first[c] < left → invalid
        ↓
Minimal valid interval
        ↓
Candidates are disjoint or nested
        ↓
Greedy:
    disjoint → add
    nested   → replace
        ↓
Maximum count
+
minimum total length
        ↓
O(n)
```

---

## 🏷️ Tags

`String, Greedy, Hash Table, Interval Scheduling, Range, First and Last Occurrence, Substring, Two Pointers, Array, LeetCode, Hard`
