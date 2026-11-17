## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320\
**LeetCode (Main):** https://leetcode.com/u/Shailu03/
**LeetCode (Alternate):** https://leetcode.com/u/ShailendraLeetcode03/

# LeetCode 3105 -- Longest Strictly Increasing or Strictly Decreasing Subarray

A plain-English guide to finding the longest contiguous subarray that is
strictly increasing or strictly decreasing using **Greedy Counting +
Array Traversal**. Both approaches run in linear time and constant extra
space.

------------------------------------------------------------------------

## 📌 Table of Contents

1.  [Problem Statement](#1-problem-statement)
2.  [Examples](#2-examples)
3.  [Constraints](#3-constraints)
4.  [Understand Strict Monotonicity](#4-understand-strict-monotonicity)
5.  [Important Observations](#5-important-observations)
6.  [Approach 1 -- Single Pass with Two
    Counters](#6-approach-1--single-pass-with-two-counters)
7.  [Approach 2 -- Two Independent
    Passes](#7-approach-2--two-independent-passes)
8.  [Dry Runs](#8-dry-runs)
9.  [Correctness Proofs](#9-correctness-proofs)
10. [Complexity Comparison](#10-complexity-comparison)
11. [Edge Cases](#11-edge-cases)
12. [Common Mistakes](#12-common-mistakes)
13. [Interview Explanation](#13-interview-explanation)
14. [Quick Revision](#14-quick-revision)
15. [Final Recommendation](#15-final-recommendation)

------------------------------------------------------------------------

## 1. Problem Statement

You are given an integer array `nums`.

Return the length of the longest **subarray** of `nums` that is either:

-   **Strictly increasing:** every element is greater than the previous
    element.
-   **Strictly decreasing:** every element is smaller than the previous
    element.

A subarray must contain contiguous elements from the original array.

**Important:** The comparison must be strict. Equal adjacent values
break both increasing and decreasing sequences. A single element is
valid and has length `1`.

**LeetCode method signature:**

``` java
public int longestMonotonicSubarray(int[] nums)
```

------------------------------------------------------------------------

## 2. Examples

### Example 1

**Input:**

``` text
nums = [1,4,3,3,2]
```

**Output:**

``` text
2
```

**Explanation:** The longest strictly increasing subarray is `[1,4]`.
The longest strictly decreasing subarrays include `[4,3]` and `[3,2]`.
The maximum length is `2`.

### Example 2

**Input:**

``` text
nums = [3,3,3,3]
```

**Output:**

``` text
1
```

**Explanation:** Every adjacent pair contains equal values, so neither a
strictly increasing nor a strictly decreasing subarray can contain more
than one element.

### Example 3

**Input:**

``` text
nums = [3,2,1]
```

**Output:**

``` text
3
```

**Explanation:** The entire array `[3,2,1]` is strictly decreasing, so
its length is `3`.

------------------------------------------------------------------------

## 3. Constraints

``` text
1 <= nums.length <= 50
1 <= nums[i] <= 50
```

The array is small, but a linear-time solution is straightforward. We
only need to compare adjacent elements and maintain a few counters.

------------------------------------------------------------------------

## 4. Understand Strict Monotonicity

A subarray is strictly increasing when:

\[ nums\[i\] \> nums\[i-1\] \]

A subarray is strictly decreasing when:

\[ nums\[i\] \< nums\[i-1\] \]

If:

\[ nums\[i\] = nums\[i-1\] \]

both patterns break.

Example:

``` text
nums = [1, 3, 5, 4, 2, 2, 7]

          Increasing: [1, 3, 5]
                       Decreasing: [5, 4, 2]
                                     Equal values break the run
```

The longest valid subarray here has length `3`.

------------------------------------------------------------------------

## 5. Important Observations

### Observation 1 -- Only adjacent comparisons matter

To determine whether a subarray is strictly increasing or decreasing,
compare each element with its immediate predecessor.

### Observation 2 -- Maintain the current run length

For an increasing pair, extend the increasing counter and reset the
decreasing counter to `1`. For a decreasing pair, do the opposite.

### Observation 3 -- Equal elements reset both directions

When two adjacent values are equal, neither strict direction continues.
Both counters become `1`.

### Observation 4 -- A single element is always valid

Initialize the counters and answer to `1`, because the constraints
guarantee a non-empty array.

------------------------------------------------------------------------

## 6. Approach 1 -- Single Pass with Two Counters

### Intuition

We can solve the problem in one traversal by maintaining two counters:

-   `inc`: length of the current strictly increasing subarray.
-   `dec`: length of the current strictly decreasing subarray.

For every adjacent pair:

1.  If the current value is greater, increment `inc` and reset `dec` to
    `1`.
2.  If the current value is smaller, increment `dec` and reset `inc` to
    `1`.
3.  If both values are equal, reset both counters to `1`.

After every comparison, update the answer using the larger counter.

### Algorithm

1.  Initialize `inc = 1`, `dec = 1`, and `answer = 1`.
2.  Traverse the array from index `1`.
3.  Compare `nums[i]` with `nums[i - 1]`.
4.  Extend the matching direction and reset the other direction.
5.  Update `answer = max(answer, inc, dec)`.
6.  Return `answer`.

### Flowchart

``` mermaid
flowchart TD
    A([Start]) --> B[Initialize inc = 1, dec = 1, answer = 1]
    B --> C[Traverse from index 1]
    C --> D{Compare nums[i] and nums[i-1]}
    D -->|Greater| E[inc++; dec = 1]
    D -->|Smaller| F[dec++; inc = 1]
    D -->|Equal| G[inc = 1; dec = 1]
    E --> H[Update answer]
    F --> H
    G --> H
    H --> I{More elements?}
    I -->|Yes| C
    I -->|No| J([Return answer])
```

### Dry Run -- Example 1

Input:

``` text
nums = [1,4,3,3,2]
```

Initialize:

``` text
inc = 1
dec = 1
answer = 1
```

    i   Previous   Current Relation       inc   dec   answer
  --- ---------- --------- ------------ ----- ----- --------
    1          1         4 Increasing       2     1        2
    2          4         3 Decreasing       1     2        2
    3          3         3 Equal            1     1        2
    4          3         2 Decreasing       1     2        2

Final answer:

\[ `\boxed{2}`{=tex} \]

### Dry Run -- Example 2

Input:

``` text
nums = [3,3,3,3]
```

Every adjacent pair is equal, so both counters reset to `1` each time.

    i   Previous   Current   inc   dec   answer
  --- ---------- --------- ----- ----- --------
    1          3         3     1     1        1
    2          3         3     1     1        1
    3          3         3     1     1        1

Final answer:

\[ `\boxed{1}`{=tex} \]

### Correctness Explanation

At every index, `inc` stores the length of the longest strictly
increasing subarray ending at that index, and `dec` stores the
corresponding strictly decreasing length. If the current value is
greater than its predecessor, only the increasing run can be extended;
the decreasing run must restart at the current element. The smaller and
equal cases are handled symmetrically. Since every valid monotonic
subarray ending at an index must either extend the matching previous run
or consist only of the current element, these counters are correct at
every step. Taking their maximum over all indices therefore returns the
longest valid subarray.

### Complexity

-   **T.C.:** `O(n)` -- Each array element is processed once.
-   **S.C.:** `O(1)` -- Only three counters are maintained.

### Java

``` java
//Approach-1 (Single Pass with Two Counters)
//T.C: O(n)
//S.C: O(1)

class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int inc = 1;
        int dec = 1;
        int answer = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                inc++;
                dec = 1;
            } else if (nums[i] < nums[i - 1]) {
                dec++;
                inc = 1;
            } else {
                inc = 1;
                dec = 1;
            }

            answer = Math.max(answer, Math.max(inc, dec));
        }

        return answer;
    }
}
```

### C++

``` cpp
//Approach-1 (Single Pass with Two Counters)
//T.C: O(n)
//S.C: O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int longestMonotonicSubarray(vector<int>& nums) {
        int inc = 1;
        int dec = 1;
        int answer = 1;

        for (int i = 1; i < nums.size(); i++) {
            if (nums[i] > nums[i - 1]) {
                inc++;
                dec = 1;
            } else if (nums[i] < nums[i - 1]) {
                dec++;
                inc = 1;
            } else {
                inc = 1;
                dec = 1;
            }

            answer = max(answer, max(inc, dec));
        }

        return answer;
    }
};
```

### Python

``` python
#Approach-1 (Single Pass with Two Counters)
#T.C: O(n)
#S.C: O(1)

from typing import List

class Solution:
    def longestMonotonicSubarray(self, nums: List[int]) -> int:
        inc = 1
        dec = 1
        answer = 1

        for i in range(1, len(nums)):
            if nums[i] > nums[i - 1]:
                inc += 1
                dec = 1
            elif nums[i] < nums[i - 1]:
                dec += 1
                inc = 1
            else:
                inc = 1
                dec = 1

            answer = max(answer, inc, dec)

        return answer
```

------------------------------------------------------------------------

## 7. Approach 2 -- Two Independent Passes

### Intuition

Instead of tracking both directions together, we can solve two smaller
problems independently:

1.  Find the longest strictly increasing subarray.
2.  Find the longest strictly decreasing subarray.

For each scan, maintain a current run length. If the required comparison
holds, increment the counter. Otherwise, reset it to `1`. Track the
maximum length and return the larger result from the two scans.

### Algorithm

1.  Initialize `increasing = 1` and `maxIncreasing = 1`.
2.  Scan the array. If `nums[i] > nums[i - 1]`, increment the increasing
    counter; otherwise reset it to `1`.
3.  Update `maxIncreasing`.
4.  Repeat the scan for strictly decreasing runs using
    `nums[i] < nums[i - 1]`.
5.  Return the maximum of `maxIncreasing` and `maxDecreasing`.

### Flowchart

``` mermaid
flowchart TD
    A([Start]) --> B[Scan for longest increasing run]
    B --> C[Reset run when nums[i] <= nums[i-1]]
    C --> D[Store maxIncreasing]
    D --> E[Scan for longest decreasing run]
    E --> F[Reset run when nums[i] >= nums[i-1]]
    F --> G[Store maxDecreasing]
    G --> H[Return max of both]
```

### Dry Run -- Example 1

Input:

``` text
nums = [1,4,3,3,2]
```

**Increasing scan:**

    i Comparison           Current length   Maximum
  --- ------------------ ---------------- ---------
    1 `1 < 4`                           2         2
    2 `4 < 3` is false                  1         2
    3 `3 < 3` is false                  1         2
    4 `3 < 2` is false                  1         2

``` text
maxIncreasing = 2
```

**Decreasing scan:**

    i Comparison           Current length   Maximum
  --- ------------------ ---------------- ---------
    1 `1 > 4` is false                  1         1
    2 `4 > 3`                           2         2
    3 `3 > 3` is false                  1         2
    4 `3 > 2`                           2         2

``` text
maxDecreasing = 2
```

Final answer:

\[ `\max`{=tex}(2,2)=`\boxed{2}`{=tex} \]

### Correctness Explanation

The first scan checks every possible ending position for a strictly
increasing subarray and maintains its exact current length, resetting
whenever the strict increasing condition fails. Therefore,
`maxIncreasing` is the longest strictly increasing subarray. The second
scan applies the same reasoning to strictly decreasing subarrays, so
`maxDecreasing` is the longest strictly decreasing subarray. Every valid
answer belongs to one of these two categories. Taking the maximum of the
two values therefore gives the required result.

### Complexity

-   **T.C.:** `O(n)` -- Two complete linear scans take `O(2n)`, which is
    `O(n)`.
-   **S.C.:** `O(1)` -- Only a constant number of variables are used.

### Java

``` java
//Approach-2 (Two Independent Passes)
//T.C: O(n)
//S.C: O(1)

class Solution2 {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;

        int increasing = 1;
        int maxIncreasing = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                increasing++;
            } else {
                increasing = 1;
            }

            maxIncreasing = Math.max(maxIncreasing, increasing);
        }

        int decreasing = 1;
        int maxDecreasing = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                decreasing++;
            } else {
                decreasing = 1;
            }

            maxDecreasing = Math.max(maxDecreasing, decreasing);
        }

        return Math.max(maxIncreasing, maxDecreasing);
    }
}
```

### C++

``` cpp
//Approach-2 (Two Independent Passes)
//T.C: O(n)
//S.C: O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution2 {
public:
    int longestMonotonicSubarray(vector<int>& nums) {
        int n = nums.size();

        int increasing = 1;
        int maxIncreasing = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                increasing++;
            } else {
                increasing = 1;
            }

            maxIncreasing = max(maxIncreasing, increasing);
        }

        int decreasing = 1;
        int maxDecreasing = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                decreasing++;
            } else {
                decreasing = 1;
            }

            maxDecreasing = max(maxDecreasing, decreasing);
        }

        return max(maxIncreasing, maxDecreasing);
    }
};
```

### Python

``` python
#Approach-2 (Two Independent Passes)
#T.C: O(n)
#S.C: O(1)

from typing import List

class Solution2:
    def longestMonotonicSubarray(self, nums: List[int]) -> int:
        n = len(nums)

        increasing = 1
        max_increasing = 1

        for i in range(1, n):
            if nums[i] > nums[i - 1]:
                increasing += 1
            else:
                increasing = 1

            max_increasing = max(max_increasing, increasing)

        decreasing = 1
        max_decreasing = 1

        for i in range(1, n):
            if nums[i] < nums[i - 1]:
                decreasing += 1
            else:
                decreasing = 1

            max_decreasing = max(max_decreasing, decreasing)

        return max(max_increasing, max_decreasing)
```

------------------------------------------------------------------------

## 8. Dry Runs

### Compare Both Approaches on Example 3

Input:

``` text
nums = [3,2,1]
```

-   The longest strictly increasing subarray has length `1`.
-   The longest strictly decreasing subarray is `[3,2,1]`, with length
    `3`.

Both approaches return:

\[ `\boxed{3}`{=tex} \]

### Direction Change

For:

``` text
nums = [1,2,3,2,1]
```

-   Increasing run: `[1,2,3]`, length `3`.
-   Decreasing run: `[3,2,1]`, length `3`.

Answer:

``` text
3
```

### Equal Elements in the Middle

For:

``` text
nums = [1,2,2,3,4]
```

The equal pair breaks the increasing run. The longest valid run is
`[2,3,4]`, with length `3`.

------------------------------------------------------------------------

## 9. Correctness Proofs

### Approach 1 -- Single-Pass Counter Invariant

After processing index `i`, `inc` equals the length of the longest
strictly increasing subarray ending at `i`, and `dec` equals the length
of the longest strictly decreasing subarray ending at `i`. The
comparison between `nums[i]` and `nums[i-1]` determines exactly which
run can be extended. The other run must restart at length `1`; equality
restarts both. Thus the invariant holds at every index. Since the
algorithm records the maximum of both counters at every step, it finds
the longest valid subarray overall.

### Approach 2 -- Independent Scan Proof

During the increasing scan, the current counter is extended exactly when
the next element is greater than the previous element. Otherwise, it
resets to `1`. Therefore, the maximum recorded counter is exactly the
longest strictly increasing subarray. The decreasing scan proves the
same result for strictly decreasing subarrays. Since the problem asks
for the longer of these two categories, taking the maximum of their
recorded lengths is correct.

------------------------------------------------------------------------

## 10. Complexity Comparison

  -------------------------------------------------------------------------
  Approach             Time Complexity          Extra Space Notes
  --------------- -------------------- -------------------- ---------------
  Single Pass                   `O(n)`               `O(1)` Tracks both
  with Two                                                  directions
  Counters                                                  together

  Two Independent               `O(n)`               `O(1)` Finds each
  Passes                                                    direction
                                                            separately
  -------------------------------------------------------------------------

Both approaches are optimal in asymptotic complexity. The first performs
one traversal, while the second performs two.

------------------------------------------------------------------------

## 11. Edge Cases

  Case                             Example           Expected Result
  -------------------------------- --------------- -----------------
  Single element                   `[5]`                           1
  All equal                        `[3,3,3,3]`                     1
  Fully increasing                 `[1,2,3,4]`                     4
  Fully decreasing                 `[4,3,2,1]`                     4
  Direction changes                `[1,2,1,2]`                     2
  Equal values break a run         `[1,2,2,3,4]`                   3
  Strictly decreasing at the end   `[1,3,5,4,2]`                   3

------------------------------------------------------------------------

## 12. Common Mistakes

1.  **Confusing subarray with subsequence.** A subarray must be
    contiguous; you cannot skip elements.
2.  **Using `>=` or `<=`.** The problem requires strict comparisons, so
    equal values must break the current run.
3.  **Forgetting to reset the opposite counter.** When the direction
    changes, the previous direction cannot continue.
4.  **Initializing counters to zero.** A single element is a valid
    subarray, so initialize counters and answer to `1`.
5.  **Tracking only increasing or only decreasing runs.** The answer may
    come from either direction.
6.  **Updating the answer before processing the current comparison.**
    Update it after the counters have been adjusted.

------------------------------------------------------------------------

## 13. Interview Explanation

"I traverse the array and maintain two counters: one for the current
strictly increasing subarray and one for the current strictly decreasing
subarray. If the current element is greater than the previous one, I
extend the increasing counter and reset the decreasing counter. If it is
smaller, I do the reverse. If both are equal, I reset both counters
because strict monotonicity is broken. I update the maximum after every
comparison. This takes `O(n)` time and `O(1)` extra space."

------------------------------------------------------------------------

## 14. Quick Revision

### Approach 1 -- Single Pass with Two Counters

``` text
inc = 1
dec = 1
answer = 1

for i from 1 to n-1:
    if nums[i] > nums[i-1]:
        inc++
        dec = 1
    else if nums[i] < nums[i-1]:
        dec++
        inc = 1
    else:
        inc = 1
        dec = 1

    answer = max(answer, inc, dec)

return answer
```

### Approach 2 -- Two Independent Passes

``` text
Find longest strictly increasing run.
Find longest strictly decreasing run.

answer = max(maxIncreasing, maxDecreasing)

return answer
```

### Remember

-   A subarray is contiguous.
-   Strictly increasing means `nums[i] > nums[i-1]`.
-   Strictly decreasing means `nums[i] < nums[i-1]`.
-   Equal adjacent elements break both patterns.
-   Initialize all run lengths to `1`.
-   Both approaches use `O(n)` time and `O(1)` extra space.

------------------------------------------------------------------------

## 15. Final Recommendation

Use **Approach 1 -- Single Pass with Two Counters** for the final
submission. It is concise, processes the array only once, and tracks
both directions simultaneously.

Approach 2 is also optimal and can be easier to explain when treating
increasing and decreasing runs as two independent problems.

Both approaches achieve:

\[ `\boxed{\text{Time} = O(n), \quad \text{Extra Space} = O(1)}`{=tex}
\]

------------------------------------------------------------------------

## Sources

-   [Official LeetCode 3105 -- Longest Strictly Increasing or Strictly
    Decreasing
    Subarray](https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/)
-   [LeetCode Doocs -- 3105
    Explanation](https://leetcode.doocs.org/en/lc/3105/)
-   [NeetCode -- 3105 Solutions and
    Explanation](https://neetcode.io/solutions/longest-strictly-increasing-or-strictly-decreasing-subarray)
