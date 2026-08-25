# Profiles

## GitHub

⭐ GitHub Repository:

https://github.com/Shailendra0320

---

## LeetCode Profiles

🔥 Main Profile:

https://leetcode.com/u/ShailendraLeetcode03/

🚀 Alternate Profile:

https://leetcode.com/u/Shailu03/

---

# 3718. Smallest Missing Multiple of K

## 🔗 Problem Link

**LeetCode:** https://leetcode.com/problems/smallest-missing-multiple-of-k/

---

# Problem Statement

You are given an integer array `nums` and an integer `k`.

Return the **smallest positive multiple of `k` that is missing from `nums`**.

A positive integer `x` is a multiple of `k` if:

```text
x % k == 0
```

Therefore, the positive multiples of `k` are:

```text
k, 2k, 3k, 4k, 5k, ...
```

We need to find the **first multiple of `k` that does not appear in `nums`**.

---

# Example 1

```text
Input:
nums = [8, 2, 3, 4, 6]
k = 2

Output:
10
```

### Explanation

The positive multiples of `2` are:

```text
2, 4, 6, 8, 10, 12, ...
```

Check them one by one:

```text
2  → Present
4  → Present
6  → Present
8  → Present
10 → Missing
```

Therefore:

```text
Answer = 10
```

---

# Example 2

```text
Input:
nums = [1, 4, 7, 10, 15]
k = 5

Output:
5
```

### Explanation

The positive multiples of `5` are:

```text
5, 10, 15, 20, ...
```

Check them:

```text
5  → Missing
```

Therefore:

```text
Answer = 5
```

---

# Constraints

```text
1 <= nums.length <= 100

1 <= nums[i] <= 100

1 <= k <= 100
```

These constraints are small, but the goal is still to build an efficient and clean membership-checking solution.

---

# Intuition

At first glance, the problem looks like a searching problem.

We need to find:

```text
the smallest positive multiple of k
that is not present in nums
```

The natural sequence of candidates is:

```text
k
2k
3k
4k
5k
...
```

There is no reason to check numbers that are not multiples of `k`.

For example, if:

```text
k = 3
```

the only candidates are:

```text
3, 6, 9, 12, 15, ...
```

We can therefore generate the candidates in increasing order and stop as soon as we find one that is missing.

The only question is:

> How can we quickly determine whether a candidate exists in `nums`?

A direct search through the array would require `O(n)` time for every candidate.

Instead, we can store all values in a hash-based structure or a boolean presence array.

Then membership checking becomes:

```text
O(1) average
```

for a hash set, or:

```text
O(1)
```

for a boolean array.

---

# Key Observation

The most important observation is:

> We only need to check multiples of `k`, and they are already naturally ordered.

The candidates are:

```text
k < 2k < 3k < 4k < ...
```

Suppose we check:

```text
k
2k
3k
...
mk
```

and all of them exist in `nums`.

If:

```text
(m + 1)k
```

does not exist, then it must be the answer.

Why?

Because every smaller positive multiple has already been verified to exist.

Therefore:

```text
First Missing Multiple
        =
Smallest Missing Multiple
```

---

# Approach 1 — Brute Force Enumeration

Before optimizing, let's understand the straightforward solution.

## Idea

Start with:

```text
multiple = k
```

Then repeatedly check whether `multiple` exists in the array.

If it exists:

```text
multiple += k
```

If it does not exist:

```text
return multiple
```

---

# Brute Force Algorithm

```text
1. Start with multiple = k.
2. Search the entire nums array for multiple.
3. If found:
       multiple += k
       repeat
4. If not found:
       return multiple.
```

---

# Brute Force Visualization

```text
                    Start
                      |
                      v
                multiple = k
                      |
                      v
              Search nums for multiple
                      |
                Is it present?
                 /          \
               Yes           No
                |             |
                v             v
        multiple += k      Return multiple
                |
                v
            Search again
```

---

# Problem With Brute Force

Suppose the array contains many consecutive multiples of `k`.

For every candidate, we scan the entire array:

```text
Check k      → O(n)
Check 2k     → O(n)
Check 3k     → O(n)
...
```

If there are `O(n)` candidate multiples, the total becomes:

```text
O(n²)
```

For the given constraints this may still be manageable, but it is unnecessary.

We can do better.

---

# Approach 2 — Hash Set + Enumeration

This is the **general recommended approach**.

## Main Idea

First store every number from `nums` in a hash set.

Then enumerate:

```text
k
2k
3k
4k
...
```

For each multiple, check:

```text
Does it exist in the set?
```

If yes:

```text
Continue
```

If no:

```text
Return it
```

---

# Step-by-Step Algorithm

## Step 1 — Store All Numbers

Create a set:

```text
Set = {}
```

For:

```text
nums = [8, 2, 3, 4, 6]
```

we get:

```text
Set = {2, 3, 4, 6, 8}
```

---

## Step 2 — Start With k

The smallest positive multiple of `k` is:

```text
k
```

So:

```text
multiple = k
```

---

## Step 3 — Check Membership

If:

```text
multiple
```

is present in the set, move to the next multiple:

```text
multiple += k
```

---

## Step 4 — Return the First Missing Multiple

If:

```text
multiple
```

is not present in the set:

```text
return multiple
```

Because candidates are checked from smallest to largest, this is guaranteed to be the smallest missing multiple.

---

# Approach 2 Visualization

```text
                         nums, k
                            |
                            v
                     Build Hash Set
                            |
                            v
                       multiple = k
                            |
                            v
                 Is multiple in Set?
                     /           \
                   Yes            No
                    |              |
                    v              v
              multiple += k    Return multiple
                    |
                    v
             Check next multiple
```

---

# Detailed Example

Consider:

```text
nums = [8, 2, 3, 4, 6]
k = 2
```

## Step 1 — Build the Set

```text
Set = {2, 3, 4, 6, 8}
```

---

## Step 2 — Check 2

```text
2 ∈ Set
```

Yes.

Move to:

```text
4
```

---

## Step 3 — Check 4

```text
4 ∈ Set
```

Yes.

Move to:

```text
6
```

---

## Step 4 — Check 6

```text
6 ∈ Set
```

Yes.

Move to:

```text
8
```

---

## Step 5 — Check 8

```text
8 ∈ Set
```

Yes.

Move to:

```text
10
```

---

## Step 6 — Check 10

```text
10 ∉ Set
```

Therefore:

```text
Answer = 10
```

---

# Dry Run Table

For:

```text
nums = [8, 2, 3, 4, 6]
k = 2
```

| Step | Multiple | Present? | Action      |
| :--: | -------: | :------: | :---------- |
|  1   |      `2` |   Yes    | Continue    |
|  2   |      `4` |   Yes    | Continue    |
|  3   |      `6` |   Yes    | Continue    |
|  4   |      `8` |   Yes    | Continue    |
|  5   |     `10` |    No    | Return `10` |

Final answer:

```text
10
```

---

# Why the Hash Set Works

A normal array requires scanning to determine whether a value exists:

```text
nums = [8,2,3,4,6]

Is 10 present?

8  → No
2  → No
3  → No
4  → No
6  → No
```

This costs:

```text
O(n)
```

A hash set allows us to perform:

```text
set.contains(10)
```

in:

```text
O(1) average
```

Therefore, instead of repeatedly scanning the array, we perform fast membership checks.

---

# Why the First Missing Multiple Is the Answer

The multiples are generated in strictly increasing order:

```text
k < 2k < 3k < 4k < ...
```

Suppose the algorithm returns:

```text
x = m × k
```

because `x` is missing.

Before reaching `x`, the algorithm checked:

```text
k
2k
3k
...
(m - 1)k
```

and every one of them was present.

Therefore, there cannot be a smaller missing positive multiple.

Hence:

```text
x = Smallest Missing Multiple of k
```

This is the central correctness argument.

---

# Why the Loop Always Terminates

Let:

```text
n = nums.length
```

The array contains only `n` elements.

Therefore, it can contain at most `n` distinct multiples of `k`.

Now consider the first:

```text
n + 1
```

positive multiples:

```text
k
2k
3k
...
(n + 1)k
```

There are `n + 1` different values but only `n` array positions.

So at least one of these multiples must be missing.

Therefore, the algorithm must terminate after at most:

```text
n + 1
```

candidate checks.

This gives the overall linear-time bound.

---

# Approach 3 — Boolean Presence Array

Because the constraints are:

```text
1 <= nums[i] <= 100
```

we can take advantage of the small value range.

Instead of using a `HashSet`, we can use:

```text
boolean[101]
```

where:

```text
present[x] = true
```

means `x` exists in the array.

---

# Boolean Array Visualization

```text
Value:

1  2  3  4  5  6  7  8  9  10 ...
   |     |     |     |
   v     v     v     v

present:

F  T  T  T  F  T  F  T  F  F ...
```

For:

```text
nums = [8,2,3,4,6]
```

we mark:

```text
present[2] = true
present[3] = true
present[4] = true
present[6] = true
present[8] = true
```

Then check:

```text
2 → true
4 → true
6 → true
8 → true
10 → false
```

Return:

```text
10
```

---

# Approach 3 — Why It Is Efficient

The boolean array has fixed size:

```text
101
```

So its space usage is:

```text
O(1)
```

with respect to `n`.

Membership checks are also:

```text
O(1)
```

Therefore:

```text
Time Complexity  = O(n)
Space Complexity = O(1)
```

This is the most memory-efficient solution for the given constraints.

---

# Approach Comparison

| Approach                    |       Time       |  Space   | Recommendation                    |
| :-------------------------- | :--------------: | :------: | :-------------------------------- |
| Brute Force + Array Search  |    **O(n²)**     | **O(1)** | Conceptual baseline               |
| HashSet + Enumeration       | **O(n)** average | **O(n)** | ⭐ General solution               |
| Boolean Array + Enumeration |     **O(n)**     | **O(1)** | ⭐⭐⭐ Best for given constraints |

---

# Recommended Approach

For a general problem, use:

```text
HashSet + Enumeration
```

because it directly expresses the idea:

```text
Store numbers
      ↓
Generate multiples
      ↓
Check membership
      ↓
Return first missing
```

For the exact LeetCode constraints, the boolean-array version is even more space-efficient.

---

# Important Detail — Start From 1

The first positive multiple of `k` is:

```text
1 × k
```

not:

```text
0 × k
```

If we accidentally start from `0`:

```text
0 × k = 0
```

But `0` is not a positive multiple.

Therefore:

```text
for (int i = 1; ; i++)
```

is correct.

---

# Common Mistakes

## Mistake 1 — Checking Every Integer

Do not check:

```text
1, 2, 3, 4, 5, 6, ...
```

Only check:

```text
k, 2k, 3k, 4k, ...
```

---

## Mistake 2 — Starting From Zero

Incorrect:

```java
for (int i = 0; ; i++)
```

because the first candidate would be:

```text
0
```

Correct:

```java
for (int i = 1; ; i++)
```

---

## Mistake 3 — Searching the Array Every Time

This:

```java
for (int i = 1; ; i++) {
    int x = i * k;

    // Scan nums to find x
}
```

can lead to:

```text
O(n²)
```

Use a set or presence array instead.

---

## Mistake 4 — Sorting the Array

Sorting is unnecessary.

We do not need the array to be ordered.

The only operation we need is:

```text
Does this number exist?
```

A hash set or boolean array handles this directly.

---

## Mistake 5 — Ignoring Numbers That Are Not Multiples of k

Suppose:

```text
k = 5
```

and:

```text
nums = [1,4,7,10,15]
```

The numbers:

```text
1, 4, 7
```

do not matter.

We only care about:

```text
5, 10, 15, 20, ...
```

Therefore:

```text
5
```

is immediately the answer.

---

# Correctness Proof

We can prove the algorithm using three simple observations.

## Lemma 1 — Every Candidate Is a Positive Multiple of k

The algorithm generates:

```text
x = i × k
```

where:

```text
i >= 1
```

Therefore:

```text
x > 0
```

and:

```text
x % k = 0
```

So every candidate is a valid positive multiple of `k`.

---

## Lemma 2 — Candidates Are Checked in Increasing Order

The algorithm checks:

```text
k
2k
3k
4k
...
```

Since:

```text
k > 0
```

we have:

```text
k < 2k < 3k < 4k < ...
```

Therefore, candidates are considered from smallest to largest.

---

## Lemma 3 — The Returned Candidate Is Missing

The algorithm returns a candidate only when:

```text
candidate ∉ nums
```

Therefore, the returned value is definitely missing from the array.

---

## Theorem

The algorithm returns the smallest positive multiple of `k` missing from `nums`.

Because:

1. Every generated candidate is a positive multiple of `k`.
2. Candidates are checked in increasing order.
3. The algorithm returns the first candidate not present in `nums`.

Therefore, the returned candidate is exactly the smallest missing positive multiple of `k`.

---

# Full Algorithm

```text
1. Create a presence structure for nums.

2. Start:
       multiple = k

3. While true:

       If multiple is not present:
           return multiple

       multiple += k
```

---

# Complete Flowchart

```text
                         ┌───────────────┐
                         │     Start     │
                         └───────┬───────┘
                                 │
                                 ▼
                      ┌────────────────────┐
                      │ Store nums in Set  │
                      └─────────┬──────────┘
                                │
                                ▼
                      ┌────────────────────┐
                      │ multiple = k       │
                      └─────────┬──────────┘
                                │
                                ▼
                    ┌────────────────────────┐
                    │ Is multiple present?   │
                    └──────────┬─────────────┘
                         Yes   │   No
                          ┌────┘    └────┐
                          │              │
                          ▼              ▼
                 ┌────────────────┐  ┌──────────────┐
                 │ multiple += k  │  │ Return       │
                 └───────┬────────┘  │ multiple     │
                         │           └──────┬───────┘
                         │                  │
                         └───────┐          ▼
                                 │         End
                                 ▼
                         Check next multiple
```

---

# Java Solution — HashSet

```java
import java.util.HashSet;

class Solution {
    public int missingMultiple(int[] nums, int k) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        for (int i = 1; ; i++) {

            int multiple = i * k;

            if (!set.contains(multiple)) {
                return multiple;
            }
        }
    }
}
```

---

# Java Solution — Boolean Array

For the given constraints, this version is even more space-efficient.

```java
class Solution {
    public int missingMultiple(int[] nums, int k) {

        boolean[] present = new boolean[101];

        for (int num : nums) {
            present[num] = true;
        }

        for (int i = 1; ; i++) {

            int multiple = i * k;

            if (multiple >= present.length ||
                !present[multiple]) {

                return multiple;
            }
        }
    }
}
```

---

# C++ Solution — Hash Set

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {

        unordered_set<int> st;

        for (int num : nums) {
            st.insert(num);
        }

        for (int i = 1; ; i++) {

            int multiple = i * k;

            if (!st.count(multiple)) {
                return multiple;
            }
        }
    }
};
```

---

# C++ Solution — Boolean Array

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {

        bool present[101] = {};

        for (int num : nums) {
            present[num] = true;
        }

        for (int i = 1; ; i++) {

            int multiple = i * k;

            if (multiple >= 101 ||
                !present[multiple]) {

                return multiple;
            }
        }
    }
};
```

---

# Java Code Explanation

## Build the Presence Structure

```java
boolean[] present = new boolean[101];
```

For every value:

```java
present[num] = true;
```

After this, we can answer:

```text
Does x exist?
```

using:

```java
present[x]
```

---

## Generate Multiples

```java
for (int i = 1; ; i++) {
    int multiple = i * k;
```

This generates:

```text
k
2k
3k
4k
...
```

---

## Find the Missing Multiple

```java
if (multiple >= present.length ||
    !present[multiple]) {
    return multiple;
}
```

If the candidate is greater than the tracked input range, it cannot be present in `nums`.

Otherwise, we directly inspect the boolean array.

---

# C++ Code Explanation

The C++ HashSet solution uses:

```cpp
unordered_set<int> st;
```

to store all values.

Then:

```cpp
if (!st.count(multiple))
```

checks whether the current multiple is missing.

The first missing candidate is returned immediately.

---

# Detailed Dry Run — Example 2

Input:

```text
nums = [1, 4, 7, 10, 15]
k = 5
```

Build:

```text
Set = {1, 4, 7, 10, 15}
```

Generate multiples:

```text
5
10
15
20
...
```

Check:

```text
5 → Missing
```

So:

```text
Answer = 5
```

Notice that:

```text
1, 4, 7
```

are irrelevant because they are not multiples of `5`.

---

# Detailed Dry Run — Consecutive Multiples

Input:

```text
nums = [3, 6, 9, 12, 20]
k = 3
```

Set:

```text
{3, 6, 9, 12, 20}
```

Candidates:

```text
3  → Present
6  → Present
9  → Present
12 → Present
15 → Missing
```

Therefore:

```text
Answer = 15
```

The value `20` does not affect the answer because:

```text
20 % 3 != 0
```

---

# Detailed Dry Run — k Greater Than Most Values

Input:

```text
nums = [1, 2, 3, 4]
k = 10
```

The first candidate is:

```text
10
```

But:

```text
10 ∉ nums
```

Therefore:

```text
Answer = 10
```

No other candidate needs to be checked.

---

# Detailed Dry Run — All Possible Multiples in the Array

Input:

```text
nums = [2, 4, 6, 8, 10]
k = 2
```

Candidates:

```text
2  → Present
4  → Present
6  → Present
8  → Present
10 → Present
12 → Missing
```

Therefore:

```text
Answer = 12
```

---

# Complexity Analysis

Let:

```text
n = nums.length
```

## Approach 1 — Brute Force

Each multiple may require a full scan of `nums`.

```text
Time Complexity  : O(n²)
Space Complexity : O(1)
```

---

## Approach 2 — Hash Set

Building the set:

```text
O(n)
```

Checking the multiples:

```text
O(n)
```

on average.

Therefore:

```text
Time Complexity  : O(n)
Space Complexity : O(n)
```

---

## Approach 3 — Boolean Array

Building the presence array:

```text
O(n)
```

Checking candidates:

```text
O(n)
```

The presence array has fixed size `101`.

Therefore:

```text
Time Complexity  : O(n)
Space Complexity : O(1)
```

---

# Complexity Comparison

| Approach                    |       Time       |  Space   | Best Use                          |
| :-------------------------- | :--------------: | :------: | :-------------------------------- |
| Brute Force                 |    **O(n²)**     | **O(1)** | Learning / baseline               |
| HashSet + Enumeration       | **O(n)** average | **O(n)** | ⭐ General solution               |
| Boolean Array + Enumeration |     **O(n)**     | **O(1)** | ⭐⭐⭐ Best for given constraints |

---

# Edge Cases

## Edge Case 1 — k Is Missing Immediately

```text
nums = [1, 2, 3]
k = 5
```

Check:

```text
5 → Missing
```

Answer:

```text
5
```

---

## Edge Case 2 — Several Multiples Are Present

```text
nums = [2, 4, 6, 8]
k = 2
```

Check:

```text
2 → Present
4 → Present
6 → Present
8 → Present
10 → Missing
```

Answer:

```text
10
```

---

## Edge Case 3 — Unrelated Values Exist

```text
nums = [2, 3, 5, 8, 11]
k = 2
```

Multiples:

```text
2, 4, 6, 8, 10, ...
```

Check:

```text
2 → Present
4 → Missing
```

Answer:

```text
4
```

The values:

```text
3, 5, 11
```

do not matter.

---

# Important Pattern

This problem belongs to the common pattern:

```text
Presence Check + Ordered Candidate Generation
```

The general pattern is:

```text
1. Identify the only candidates that can be answers.
2. Generate candidates in the required order.
3. Store input values for fast membership checks.
4. Return the first candidate that fails the membership check.
```

This pattern appears in many array and hash-table problems.

---

# Interview Explanation

A concise interview explanation could be:

> I only need to consider positive multiples of `k`, so I generate `k, 2k, 3k, ...` in increasing order. I first store all values from `nums` in a hash set so membership checks are O(1) on average. The first generated multiple that is not present must be the smallest missing multiple because all smaller multiples have already been checked.

Then mention the constraint optimization:

> Since `nums[i] <= 100`, a boolean presence array of size 101 can replace the hash set and reduce extra space to O(1).

---

# One-Line Insight

```text
Store the numbers, generate k, 2k, 3k, ...
and return the first multiple that is not present.
```

---

# Final Takeaways

- ✅ Only multiples of `k` can be answers.
- ✅ Generate candidates as `k, 2k, 3k, ...`.
- ✅ Start from `1 * k`, not `0 * k`.
- ✅ Store `nums` for fast membership checks.
- ✅ The first missing multiple is automatically the smallest missing multiple.
- ✅ A HashSet provides `O(1)` average membership checks.
- ✅ A boolean array is even more space-efficient under the given constraints.
- ✅ No sorting is necessary.
- ✅ No binary search is necessary.
- ✅ No complex mathematics is necessary.
- ✅ The HashSet approach runs in `O(n)` average time and `O(n)` space.
- ✅ The Boolean-array approach runs in `O(n)` time and `O(1)` space.

---

# Final Algorithm Summary

```text
                         LeetCode 3718
                              |
                              v
                Smallest Missing Multiple of K
                              |
                              v
                    Store nums in Set
                              |
                              v
                       multiple = k
                              |
                              v
                   Is multiple present?
                       /            \
                     Yes             No
                      |               |
                      v               v
               multiple += k      Return multiple
                      |
                      v
                Check next multiple
```

The complete idea is:

```text
nums
  ↓
Presence Structure
  ↓
k
  ↓
2k
  ↓
3k
  ↓
4k
  ↓
...
  ↓
First Missing Multiple
  ↓
Answer
```

---

# Final Java Solution

```java
class Solution {
    public int missingMultiple(int[] nums, int k) {

        boolean[] present = new boolean[101];

        for (int num : nums) {
            present[num] = true;
        }

        for (int i = 1; ; i++) {

            int multiple = i * k;

            if (multiple >= present.length ||
                !present[multiple]) {

                return multiple;
            }
        }
    }
}
```

---

# Final C++ Solution

```cpp
class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {

        bool present[101] = {};

        for (int num : nums) {
            present[num] = true;
        }

        for (int i = 1; ; i++) {

            int multiple = i * k;

            if (multiple >= 101 ||
                !present[multiple]) {

                return multiple;
            }
        }
    }
};
```

---

# Final Complexity

```text
Time Complexity  : O(n)

Space Complexity : O(1)
```

for the Boolean-array implementation.

---

# Tags

`Array` `Hash Table` `Enumeration` `Math` `Number Theory` `Set` `Simulation` `LeetCode` `Easy`
