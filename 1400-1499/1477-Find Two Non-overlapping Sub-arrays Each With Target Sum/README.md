# 1477. Find Two Non-overlapping Sub-arrays Each With Target Sum

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

# 🧩 Problem Statement

You are given an array `arr` of positive integers and an integer `target`.

Find **two non-overlapping subarrays** such that:

```text
sum(subarray1) = target
sum(subarray2) = target
```

Among all valid pairs, return the minimum possible:

```text
length(subarray1) + length(subarray2)
```

If no such pair exists, return:

```text
-1
```

The two subarrays must not overlap.

For intervals:

```text
[l1, r1]
[l2, r2]
```

after ordering them from left to right, we need:

```text
r1 < l2
```

### Link

[LeetCode 1477 — Find Two Non-overlapping Sub-arrays Each With Target Sum](https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/)

---

# 💡 What Is the Question Really Asking?

There are really two problems:

```text
1. Find subarrays whose sum is target.
2. Choose two non-overlapping ones with minimum total length.
```

The important observation is that the objective is **minimum total length**, not the number of elements covered by some larger region.

A useful way to think about the problem is:

```text
Every valid target-sum subarray
        ↓
becomes an interval [left, right]
        ↓
For each interval,
find the shortest compatible interval before it
```

That turns the problem into:

```text
Sliding Window
      +
Prefix DP
```

---

# 🧠 Core Observation

Because every element is positive:

```text
arr[i] > 0
```

the running sum is monotonic with respect to the sliding-window pointers.

When we move `right` forward:

```text
sum increases
```

When we move `left` forward:

```text
sum decreases
```

Therefore we can find every target-sum subarray in linear time.

Suppose we find:

```text
current window = [left, right]
```

Its length is:

```text
right - left + 1
```

Any compatible previous target-sum subarray must end before:

```text
left
```

So we only need to know:

```text
the shortest target-sum subarray completely before left
```

Store that in:

```text
best[left]
```

Then:

```text
candidate
=
best[left] + (right - left + 1)
```

and minimize the answer.

This is the central insight.

---

# 🏗️ Solution Architecture

```text
                       Input Array
                           |
                           v
                +----------------------+
                | Positive Elements    |
                +----------+-----------+
                           |
                           v
                +----------------------+
                | Sliding Window       |
                | Find sum == target   |
                +----------+-----------+
                           |
                           v
                    Current Interval
                        [l, r]
                           |
                           v
              +------------------------+
              | best[l]                |
              | shortest valid target  |
              | subarray before l      |
              +-----------+------------+
                          |
                          v
               best[l] + currentLength
                          |
                          v
                 Minimize Answer
                          |
                          v
                     Final Result
```

---

# 📌 DP State

Define:

```text
best[i]
```

as:

> The minimum length of a subarray whose sum is `target` and which lies completely inside the first `i` elements.

Therefore:

```text
best[0] = INF
```

because an empty prefix contains no non-empty target-sum subarray.

For a current target-sum window:

```text
[l, r]
```

we combine it with:

```text
best[l]
```

because `best[l]` only uses:

```text
indices 0 ... l-1
```

and therefore cannot overlap the current window.

---

# 1️⃣ Approach 1 — Sliding Window + Prefix DP

This is the most natural solution because all array values are positive.

## Step 1 — Expand the Window

Move `right` from:

```text
0 → n-1
```

and add:

```text
arr[right]
```

to the running sum.

---

## Step 2 — Shrink If Necessary

If:

```text
sum > target
```

move `left` forward:

```text
sum -= arr[left]
left++
```

until:

```text
sum <= target
```

Because values are positive, once the sum becomes too large, moving `left` is the only way to reduce it.

---

## Step 3 — Found a Target-Sum Window

If:

```text
sum == target
```

then:

```text
length = right - left + 1
```

and we can combine it with:

```text
best[left]
```

if such a previous window exists.

---

## Step 4 — Store the Current Window

The current target-sum window may be the shortest valid window available for future positions.

So:

```text
best[right + 1]
=
min(best[right], currentLength)
```

If no current target window ends here:

```text
best[right + 1] = best[right]
```

---

# 🔄 Approach 1 Flowchart

```text
                         Start
                           |
                           v
                  left = 0, sum = 0
                           |
                           v
                    Move right
                           |
                           v
                 sum += arr[right]
                           |
                           v
                     sum > target?
                       /        \
                     Yes         No
                      |           |
                      v           v
                Shrink left    sum == target?
                                   /      \
                                 Yes       No
                                  |         |
                                  v         |
                         current length     |
                                  |         |
                                  v         |
                        combine best[left]  |
                                  |         |
                                  +----+----+
                                       |
                                       v
                            update best[right+1]
                                       |
                                       v
                                  Next right
```

---

# ✅ Java — Approach 1

```java
//Approach-1 (Sliding Window + DP)
//T.C : O(n)
//S.C : O(n)

class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = n + 1;

        // best[i] = minimum length of a target-sum
        // subarray completely inside the first i elements.
        int[] best = new int[n + 1];

        java.util.Arrays.fill(best, INF);

        int left = 0;
        int sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {

                int length = right - left + 1;

                // Any previous subarray stored in best[left]
                // ends before the current subarray begins.
                if (best[left] != INF) {
                    answer = Math.min(
                        answer,
                        best[left] + length
                    );
                }

                // Current window can be the best candidate
                // for future subarrays.
                best[right + 1] = Math.min(
                    best[right],
                    length
                );

            } else {
                best[right + 1] = best[right];
            }
        }

        return answer == INF ? -1 : answer;
    }
}
```

---

# ✅ C++ — Approach 1

```cpp
//Approach-1 (Sliding Window + DP)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:
    int minSumOfLengths(vector<int>& arr, int target) {

        int n = arr.size();
        int INF = n + 1;

        // best[i] = minimum length of a target-sum
        // subarray completely inside the first i elements.
        vector<int> best(n + 1, INF);

        int left = 0;
        int sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {

                int length = right - left + 1;

                if (best[left] != INF) {
                    answer = min(
                        answer,
                        best[left] + length
                    );
                }

                best[right + 1] = min(
                    best[right],
                    length
                );

            } else {
                best[right + 1] = best[right];
            }
        }

        return answer == INF ? -1 : answer;
    }
};
```

---

# 🧪 Detailed Dry Run

Consider:

```text
arr = [7, 3, 4, 7]
target = 7
```

The valid target-sum subarrays are:

```text
[0,0] → [7]       length = 1
[1,2] → [3,4]     length = 2
[3,3] → [7]       length = 1
```

The first and last intervals are non-overlapping:

```text
[0,0]
      [3,3]
```

Their total length is:

```text
1 + 1 = 2
```

So:

```text
answer = 2
```

---

## 🔍 DP State During the Example

After discovering:

```text
[0,0]
```

we record:

```text
best[1] = 1
```

When we later find:

```text
[3,3]
```

its starting position is:

```text
left = 3
```

and:

```text
best[3] = 1
```

Therefore:

```text
candidate = best[3] + 1
          = 2
```

---

# 🧠 Why `best[left]` Guarantees Non-Overlap

Suppose the current target-sum subarray is:

```text
[l, r]
```

By definition:

```text
best[l]
```

only uses elements inside:

```text
[0, l-1]
```

The current subarray begins at:

```text
l
```

Therefore:

```text
previous end < l
```

which guarantees:

```text
previous interval ∩ current interval = ∅
```

So the DP state itself enforces the non-overlapping condition.

---

# 2️⃣ Approach 2 — Prefix Sum + HashMap + DP

The same problem can also be solved using prefix sums.

Define:

```text
prefix[i]
=
sum of the first i elements
```

For a subarray:

```text
[l, r]
```

we have:

```text
sum(l...r)
=
prefix[r+1] - prefix[l]
```

We want:

```text
prefix[r+1] - prefix[l] = target
```

Therefore:

```text
prefix[l]
=
prefix[r+1] - target
```

So while scanning the array, if we have already seen:

```text
prefix[r+1] - target
```

then a target-sum subarray ends at the current position.

---

# 🧠 Prefix DP State

We still use:

```text
best[i]
```

with the same meaning:

> shortest target-sum subarray completely inside the first `i` elements.

If the current subarray is:

```text
[left, i-1]
```

then its length is:

```text
i - left
```

and the compatible previous answer is:

```text
best[left]
```

Therefore:

```text
answer =
min(answer, best[left] + i - left)
```

---

# 🔄 Approach 2 Flowchart

```text
                    prefix sum
                        |
                        v
              required = prefix - target
                        |
                        v
                Seen before?
                   /       \
                 No         Yes
                 |           |
                 |           v
                 |       Find [left,i-1]
                 |           |
                 |           v
                 |       length = i-left
                 |           |
                 |           v
                 |       best[left]
                 |           |
                 +-----+-----+
                       |
                       v
                  update best
                       |
                       v
                    Continue
```

---

# ✅ Java — Approach 2

```java
//Approach-2 (Prefix Sum + HashMap + DP)
//T.C : O(n)
//S.C : O(n)

import java.util.HashMap;
import java.util.Map;

class Solution2 {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = n + 1;

        int[] best = new int[n + 1];

        java.util.Arrays.fill(best, INF);

        // prefix sum -> prefix index
        Map<Integer, Integer> first = new HashMap<>();

        first.put(0, 0);

        int prefix = 0;
        int answer = INF;

        for (int i = 1; i <= n; i++) {

            prefix += arr[i - 1];

            int required = prefix - target;

            if (first.containsKey(required)) {

                int left = first.get(required);
                int length = i - left;

                if (best[left] != INF) {
                    answer = Math.min(
                        answer,
                        best[left] + length
                    );
                }

                best[i] = Math.min(
                    best[i - 1],
                    length
                );

            } else {
                best[i] = best[i - 1];
            }

            first.put(prefix, i);
        }

        return answer == INF ? -1 : answer;
    }
}
```

---

# ✅ C++ — Approach 2

```cpp
//Approach-2 (Prefix Sum + HashMap + DP)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
public:
    int minSumOfLengths(vector<int>& arr, int target) {

        int n = arr.size();
        int INF = n + 1;

        vector<int> best(n + 1, INF);

        // prefix sum -> prefix index
        unordered_map<int, int> first;

        first[0] = 0;

        int prefix = 0;
        int answer = INF;

        for (int i = 1; i <= n; i++) {

            prefix += arr[i - 1];

            int required = prefix - target;

            if (first.count(required)) {

                int left = first[required];
                int length = i - left;

                if (best[left] != INF) {
                    answer = min(
                        answer,
                        best[left] + length
                    );
                }

                best[i] = min(
                    best[i - 1],
                    length
                );

            } else {
                best[i] = best[i - 1];
            }

            first[prefix] = i;
        }

        return answer == INF ? -1 : answer;
    }
};
```

---

# ⚖️ Approach Comparison

| Feature                | Sliding Window + DP  | Prefix Sum + HashMap + DP             |
| ---------------------- | -------------------- | ------------------------------------- |
| Main technique         | Sliding Window       | Prefix Sum                            |
| Needs positive numbers | Yes                  | No                                    |
| Time                   | `O(n)`               | `O(n)` average                        |
| Space                  | `O(n)`               | `O(n)`                                |
| Main strength          | Very intuitive here  | More general subarray-sum technique   |
| Best mental model      | Find window directly | Find window through prefix difference |

For this problem, the sliding-window approach is particularly natural because all values are positive.

---

# 🧠 Why Sliding Window Is `O(n)`

The code contains:

```text
for right = 0...n-1
```

and inside it:

```text
while (sum > target)
    left++
```

This may appear to be nested loops, but both pointers only move forward.

Therefore:

```text
right moves at most n times
left moves at most n times
```

So total work is:

```text
O(n)
```

This is a classic amortized `O(n)` sliding-window pattern.

---

# 🎯 Interview Thought Process

A clean derivation is:

```text
Need two subarrays
        ↓
Each sum must equal target
        ↓
All numbers are positive
        ↓
Sliding window finds target-sum intervals
        ↓
Each interval is [left,right]
        ↓
Need a previous interval before left
        ↓
Store shortest previous interval in best[left]
        ↓
candidate = best[left] + currentLength
        ↓
Minimize the answer
```

This is the main pattern to remember.

---

# 🧩 Pattern Recognition

Whenever you see:

```text
positive array
+
subarray sum = target
```

think:

```text
Sliding Window
```

Whenever you see:

```text
non-overlapping subarrays
+
minimum total length
```

think:

```text
Prefix DP / best-so-far
```

Together:

```text
Sliding Window
      +
Prefix DP
```

---

# 🔥 Deep Mental Model

Treat every valid target-sum subarray as an interval:

```text
[l, r]
```

For the current interval, ask:

> What is the cheapest valid interval that finishes before `l`?

The answer is already stored:

```text
best[l]
```

So instead of checking every pair of intervals:

```text
current × previous
```

we use:

```text
best previous + current
```

This reduces pairwise optimization to constant work per candidate interval.

---

# 🧮 Mathematical View

For each valid subarray:

```text
I = [l, r]
```

define its cost:

```text
cost(I) = r - l + 1
```

We need two intervals:

```text
I1 = [l1, r1]
I2 = [l2, r2]
```

such that, after ordering them:

```text
r1 < l2
```

and minimize:

```text
cost(I1) + cost(I2)
```

The DP converts this into:

```text
min(
    best[left] + currentLength
)
```

over all valid current windows.

---

# ⚠️ Common Mistakes

## 1. Using Sliding Window With Negative Numbers

The sliding-window proof depends on:

```text
arr[i] > 0
```

If negative numbers were allowed, expanding or shrinking the window would no longer behave monotonically.

---

## 2. Using `best[right]`

For current:

```text
[left, right]
```

the previous interval must finish before:

```text
left
```

Therefore the correct state is:

```text
best[left]
```

not:

```text
best[right]
```

---

## 3. Forgetting to Carry Forward `best`

If no new target window ends at position `i`, previous information must remain:

```text
best[i] = best[i-1]
```

---

## 4. Combining Overlapping Windows

Two target-sum subarrays can both be individually valid but still overlap.

Only combine a current window with:

```text
best[left]
```

because it is guaranteed to lie before the current one.

---

## 5. Returning `INF`

If fewer than two compatible windows exist, return:

```text
-1
```

---

# 🧪 Edge Cases

### Only one target-sum subarray

There is no valid pair:

```text
answer = -1
```

### Two single-element target windows

Example:

```text
arr = [3, 1, 3]
target = 3
```

The two `[3]` windows are non-overlapping:

```text
answer = 2
```

### Overlapping target windows

The DP automatically prevents invalid combinations.

### Many target windows

We keep only the shortest previous window because any longer previous window can never produce a better total with the same current window.

---

# 🏆 Why Only the Minimum Previous Length Is Needed

Suppose before `left` we have several target-sum windows:

```text
length = 2
length = 4
length = 6
```

For the same current window of length `L`:

```text
2 + L
4 + L
6 + L
```

The smallest previous length always gives the smallest total.

Therefore:

```text
best[left]
```

contains exactly the information future windows need.

This is a common dynamic-programming optimization:

> Store only the best prefix state instead of all previous possibilities.

---

# 🚀 Why Pairwise Enumeration Is Unnecessary

A naive solution could:

```text
1. Find all target-sum windows.
2. Try every pair.
3. Check overlap.
4. Minimize total length.
```

If there are `m` target-sum windows:

```text
O(m²)
```

pair checks may be required.

The DP approach avoids this.

For each current interval:

```text
one lookup → best[left]
```

so the total remains linear.

---

# 📈 Complexity Analysis

Let:

```text
n = arr.length
```

### Approach 1

```text
Time  : O(n)
Space : O(n)
```

### Approach 2

```text
Time  : O(n) average
Space : O(n)
```

The first approach has deterministic linear traversal because both sliding-window pointers only move forward.

---

# 📌 Formula / State Cheat Sheet

### Current window

```text
length = right - left + 1
```

### Previous compatible window

```text
best[left]
```

### Candidate

```text
candidate = best[left] + length
```

### DP update

```text
best[right + 1]
=
min(best[right], length)
```

### Final result

```text
minimum candidate
```

or:

```text
-1
```

---

# 📝 Final Summary

The problem becomes simple once every valid target-sum subarray is viewed as an interval.

Because the array contains positive integers:

```text
Sliding Window
```

finds target-sum intervals in `O(n)`.

For a current interval:

```text
[left, right]
```

we only need the shortest target-sum interval completely before `left`.

That information is stored in:

```text
best[left]
```

So:

```text
answer =
min(
    best[left] + (right-left+1)
)
```

The complete solution is:

```text
Sliding Window
      +
Prefix DP
      =
O(n) time, O(n) space
```

---

# 💎 One-Line Insight

> **For every target-sum window `[l,r]`, pair it with the shortest target-sum window completely before `l`; prefix DP stores that shortest compatible window.**

---

# 🧠 Interview Cheat Sheet

```text
Positive numbers?
        ↓
Sliding Window

Find [l,r] with sum = target
        ↓
length = r-l+1

Need another interval?
        ↓
Must end before l

Best previous?
        ↓
best[l]

Candidate?
        ↓
best[l] + length

Minimize all candidates
        ↓
Answer

Complexity:
O(n) time
O(n) space
```

---

## 🏷️ Tags

`Array, Sliding Window, Dynamic Programming, Prefix DP, Hash Table, Prefix Sum, Subarray, Two Pointers, Greedy, Optimization, LeetCode, Medium`
