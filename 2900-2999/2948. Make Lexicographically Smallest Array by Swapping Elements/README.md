# Profiles

## GitHub

⭐ GitHub Repository:

https://github.com/Shailendra0320

---

## LeetCode Profiles

🔥 Main Profile:

https://leetcode.com/u/Shailu03/

🚀 Alternate Profile:

https://leetcode.com/u/ShailendraLeetcode03/

---

# 2948. Make Lexicographically Smallest Array by Swapping Elements

## Problem Statement

You are given a 0-indexed array of positive integers `nums` and a positive integer `limit`.

In one operation, you can choose two indices `i` and `j` and swap `nums[i]` and `nums[j]` if:

```text
|nums[i] - nums[j]| <= limit
```

You may perform this operation any number of times.

Return the **lexicographically smallest array** that can be obtained.

The official constraints are:

```text
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= limit <= 10^9
```

Source: [LeetCode 2948](https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/). citeturn0search0

---

# Example 1

```text
Input:
nums = [1,5,3,9,8]
limit = 2

Output:
[1,3,5,8,9]
```

We can swap `5` and `3` because:

```text
|5 - 3| = 2 <= 2
```

Then swap `9` and `8` because:

```text
|9 - 8| = 1 <= 2
```

Result:

```text
[1,3,5,8,9]
```

---

# Example 2

```text
Input:
nums = [1,7,6,18,2,1]
limit = 3

Output:
[1,6,7,18,1,2]
```

After sorting the values:

```text
1,1,2,6,7,18
```

The groups are:

```text
[1,1,2]
[6,7]
[18]
```

Each group can be rearranged independently.

---

# Example 3

```text
Input:
nums = [1,7,28,19,10]
limit = 3

Output:
[1,7,28,19,10]
```

No useful pair can be connected through a valid swap, so the original array remains unchanged. citeturn0search0

---

# Key Observation

The problem is not about performing swaps one by one.

Instead, think of every value as belonging to a **connected group**.

If sorted values are:

```text
1 3 5 8 9
```

and:

```text
limit = 2
```

then:

```text
3 - 1 = 2  <= 2
5 - 3 = 2  <= 2
8 - 5 = 3  > 2
9 - 8 = 1  <= 2
```

So the groups are:

```text
[1,3,5]
[8,9]
```

Values inside the same group can be moved among that group's original positions.

---

# Why Connected Groups Work

Consider:

```text
1, 3, 5
limit = 2
```

Although:

```text
|1 - 5| = 4 > 2
```

so `1` and `5` cannot swap directly, we have:

```text
1 ↔ 3
3 ↔ 5
```

Therefore values can move through `3`.

This means the entire group:

```text
[1,3,5]
```

can be rearranged.

The important point is:

> Swaps are transitive through intermediate values.

---

# Approach 1 — Brute Force Swapping

## Intuition

A straightforward approach is to repeatedly search for a smaller value that can be swapped into the current position.

For every index `i`, scan later positions `j`.

If:

```text
nums[j] < nums[i]
```

and:

```text
|nums[i] - nums[j]| <= limit
```

swap them.

This is easy to understand but does not efficiently handle the transitive nature of the allowed swaps.

---

## Algorithm

```text
1. Start with nums.

2. For every position i:
       Search later positions j.

3. If nums[j] < nums[i] and
   |nums[i] - nums[j]| <= limit:

       swap(nums[i], nums[j])

4. Continue until all positions have been processed.
```

---

## Complexity Analysis

There can be:

```text
O(n²)
```

pair checks.

Therefore:

```text
Time Complexity  → O(n²)
Space Complexity → O(1)
```

This is not suitable for:

```text
n = 100,000
```

---

## Java Code

```java
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                if (nums[j] < nums[i]
                        && Math.abs((long) nums[i] - nums[j]) <= limit) {

                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        return nums;
    }
}
```

---

## C++ Code

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> lexicographicallySmallestArray(
        vector<int>& nums,
        int limit
    ) {
        int n = nums.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                if (nums[j] < nums[i]
                    && abs((long long) nums[i] - nums[j]) <= limit) {

                    swap(nums[i], nums[j]);
                }
            }
        }

        return nums;
    }
};
```

> **Note:** This approach is only for understanding the basic idea. It is not suitable for the official constraints.

---

# Approach 2 — Sorting + Connected Groups

## Intuition

The optimal approach is to avoid simulating swaps.

Create pairs:

```text
(value, originalIndex)
```

Then sort them by value.

For example:

```text
nums = [1,5,3,9,8]
```

becomes:

```text
(1,0)
(3,2)
(5,1)
(8,4)
(9,3)
```

With:

```text
limit = 2
```

we get two groups:

```text
Group 1 → values [1,3,5]
Group 2 → values [8,9]
```

Inside every group:

```text
1. Values are already sorted.
2. Sort their original indices.
3. Assign the smallest value to the smallest index.
```

This gives the lexicographically smallest result.

---

# Why Consecutive Gaps Define Groups

After sorting:

```text
v[0] <= v[1] <= ... <= v[n-1]
```

If:

```text
v[i] - v[i-1] <= limit
```

the two values are directly connected.

If:

```text
v[i] - v[i-1] > limit
```

there is no value between them that can bridge the gap.

Therefore, a new connected group starts.

---

# Detailed Dry Run

Consider:

```text
nums = [1,5,3,9,8]
limit = 2
```

## Step 1 — Store `(value, index)`

```text
(1,0)
(5,1)
(3,2)
(9,3)
(8,4)
```

## Step 2 — Sort by Value

```text
(1,0)
(3,2)
(5,1)
(8,4)
(9,3)
```

## Step 3 — Find Groups

```text
3 - 1 = 2  → same group
5 - 3 = 2  → same group
8 - 5 = 3  → new group
9 - 8 = 1  → same group
```

So:

```text
Group 1:
values  = [1,3,5]
indices = [0,2,1]

Group 2:
values  = [8,9]
indices = [4,3]
```

## Step 4 — Sort Indices

Group 1:

```text
indices = [0,1,2]
values  = [1,3,5]
```

Assign:

```text
result[0] = 1
result[1] = 3
result[2] = 5
```

Group 2:

```text
indices = [3,4]
values  = [8,9]
```

Assign:

```text
result[3] = 8
result[4] = 9
```

Final result:

```text
[1,3,5,8,9]
```

---

# Why Sorting Group Indices Is Optimal

Suppose a connected group contains:

```text
values  = [3,5,8]
indices = [4,1,3]
```

Sort the indices:

```text
[1,3,4]
```

Assign:

```text
index 1 → 3
index 3 → 5
index 4 → 8
```

The smallest value goes to the earliest position.

This is exactly what lexicographical minimization requires.

---

# Approach 2 Algorithm

```text
1. Create (value, originalIndex) pairs.

2. Sort pairs by value.

3. Traverse the sorted pairs.

4. Start a new group whenever:

       currentValue - previousValue > limit

5. For each group:
       a. Collect original indices.
       b. Sort those indices.
       c. Values are already sorted.
       d. Assign values to indices in sorted order.

6. Return the result.
```

---

# Correctness Proof

## Lemma 1 — Every Value in a Group Is Reachable

For a sorted group:

```text
v1 <= v2 <= ... <= vk
```

every consecutive difference satisfies:

```text
v[i] - v[i-1] <= limit
```

Therefore consecutive values can be swapped.

Because the values form a connected chain, values can be moved through the group.

Hence all values in the group can be rearranged among its original positions.

---

## Lemma 2 — Different Groups Cannot Exchange Values

If two consecutive sorted values satisfy:

```text
v[i] - v[i-1] > limit
```

then there is no value between them that can bridge the gap.

Thus no sequence of valid swaps can connect the two groups.

---

## Lemma 3 — Sorted Values + Sorted Indices Give the Minimum

Inside one connected group, all values can be rearranged freely.

To minimize the array lexicographically:

```text
smallest value → smallest index
second smallest → second smallest index
...
```

Therefore sorting both lists and pairing them in order gives the minimum arrangement for that group.

---

## Theorem

The algorithm identifies exactly the connected groups of values that can exchange positions.

Each group is independently arranged in its lexicographically smallest form.

Since different groups cannot exchange values, the combination of all group-wise optimal arrangements is the globally lexicographically smallest reachable array.

Therefore the algorithm is correct.

---

# Complexity Analysis

Let:

```text
n = nums.length
```

Sorting the `(value, index)` pairs:

```text
O(n log n)
```

Sorting original indices across all groups is at most:

```text
O(n log n)
```

Therefore:

```text
Time Complexity  → O(n log n)
Space Complexity → O(n)
```

This is efficient for:

```text
n <= 10^5
```

---

# Complexity Comparison

| Approach                   | Time Complexity | Space Complexity | Practical |
| -------------------------- | --------------: | ---------------: | --------- |
| Brute Force                |         `O(n²)` |           `O(1)` | ❌        |
| Sorting + Connected Groups |    `O(n log n)` |           `O(n)` | ✅        |

---

# Java Code — Optimal Approach

```java
import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // {value, originalIndex}
        int[][] pairs = new int[n][2];

        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        // Sort by value.
        Arrays.sort(pairs, (a, b) ->
            Integer.compare(a[0], b[0])
        );

        int[] result = new int[n];

        int start = 0;

        while (start < n) {

            int end = start;

            // Find the connected group.
            while (end + 1 < n
                    && (long) pairs[end + 1][0] - pairs[end][0] <= limit) {
                end++;
            }

            int size = end - start + 1;

            int[] indices = new int[size];

            for (int i = 0; i < size; i++) {
                indices[i] = pairs[start + i][1];
            }

            // Smallest values should go to smallest indices.
            Arrays.sort(indices);

            for (int i = 0; i < size; i++) {
                result[indices[i]] = pairs[start + i][0];
            }

            start = end + 1;
        }

        return result;
    }
}
```

---

# C++ Code — Optimal Approach

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> lexicographicallySmallestArray(
        vector<int>& nums,
        int limit
    ) {
        int n = nums.size();

        // {value, originalIndex}
        vector<pair<int, int>> pairs;

        for (int i = 0; i < n; i++) {
            pairs.push_back({nums[i], i});
        }

        // Sort by value.
        sort(pairs.begin(), pairs.end());

        vector<int> result(n);

        int start = 0;

        while (start < n) {

            int end = start;

            // Find the connected group.
            while (end + 1 < n
                   && (long long)pairs[end + 1].first
                      - pairs[end].first <= limit) {
                end++;
            }

            vector<int> indices;

            for (int i = start; i <= end; i++) {
                indices.push_back(pairs[i].second);
            }

            // Smallest values -> smallest indices.
            sort(indices.begin(), indices.end());

            for (int i = 0; i < indices.size(); i++) {
                result[indices[i]] = pairs[start + i].first;
            }

            start = end + 1;
        }

        return result;
    }
};
```

---

# Code Walkthrough

## 1. Store Original Positions

```java
pairs[i][0] = nums[i];
pairs[i][1] = i;
```

We need the original index because sorting changes the order of the values.

---

## 2. Sort by Value

```java
Arrays.sort(pairs, ...)
```

Now adjacent values can be checked to determine connectivity.

---

## 3. Find a Connected Group

```java
while (end + 1 < n
        && pairs[end + 1][0] - pairs[end][0] <= limit)
```

As long as the consecutive gap is within `limit`, the values stay in the same group.

---

## 4. Sort Original Indices

```java
Arrays.sort(indices);
```

This identifies the earliest positions where the group's smallest values should go.

---

## 5. Assign Values

```java
result[indices[i]] = pairs[start + i][0];
```

Because both sequences are sorted:

```text
smallest value → smallest index
```

the result is lexicographically minimal.

---

# Why Union-Find Is Not Necessary

The problem can be interpreted as a connected-component problem, so DSU/Union-Find might seem natural.

But explicitly connecting every valid pair could require:

```text
O(n²)
```

edges.

Sorting solves the problem much more simply.

After sorting, only adjacent gaps matter:

```text
gap <= limit → same group
gap > limit  → new group
```

Therefore, we can identify all components in one sorted traversal.

---

# Edge Cases

## Case 1 — One Element

```text
nums = [5]
limit = 10
```

Nothing can change.

```text
[5]
```

---

## Case 2 — All Elements Connected

```text
nums = [5,3,4]
limit = 2
```

Sorted:

```text
3,4,5
```

All gaps are at most `2`.

Therefore, all values can be rearranged:

```text
[3,4,5]
```

---

## Case 3 — No Elements Connected

```text
nums = [1,10,20]
limit = 2
```

Every consecutive gap is greater than `2`.

Therefore:

```text
[1,10,20]
```

remains unchanged.

---

## Case 4 — Duplicate Values

```text
nums = [4,4,2,2]
limit = 2
```

Duplicate values naturally belong to the same group.

The same sorting approach works without any special handling.

---

# Common Mistakes

## Mistake 1 — Sorting the Entire Array

You cannot always sort all values globally.

Only values in the same connected group can be rearranged.

---

## Mistake 2 — Checking Only Direct Pairs

`1` and `5` may not directly swap when `limit = 2`, but:

```text
1 ↔ 3 ↔ 5
```

can still allow them to exchange positions.

---

## Mistake 3 — Grouping Using Original Indices

Groups are determined by **value differences**, so sort by value first.

---

## Mistake 4 — Losing Original Indices

Always store:

```text
(value, originalIndex)
```

---

## Mistake 5 — Not Sorting Group Indices

Even if the group's values are sorted, assigning them to arbitrary original indices may not produce the lexicographically smallest array.

---

# Key Takeaways

```text
1. Think in terms of connected components.

2. Sort (value, originalIndex) pairs.

3. A gap > limit starts a new group.

4. Values within a group can be freely rearranged.

5. Sort the group's original indices.

6. Assign sorted values to sorted indices.

7. Do not globally sort the array.

8. The optimal complexity is O(n log n).
```

---

# One-Line Insight

> **Sort the values, split them into connected groups wherever the consecutive gap exceeds `limit`, then place each group's smallest values into its smallest original indices.**

---

# Final Summary

```text
Original nums
      │
      ▼
(value, originalIndex)
      │
      ▼
Sort by value
      │
      ▼
Find groups using adjacent gaps
      │
      ├── gap <= limit → same group
      │
      └── gap > limit  → new group
      │
      ▼
Sort original indices in each group
      │
      ▼
Assign sorted values to sorted indices
      │
      ▼
Lexicographically Smallest Array
```

---

# Final Complexity

```text
Approach 1 — Brute Force

Time Complexity  → O(n²)
Space Complexity → O(1)


Approach 2 — Sorting + Connected Groups

Time Complexity  → O(n log n)
Space Complexity → O(n)
```

---

# Tags

`Array` `Sorting` `Greedy` `Connected Components` `Graph` `Union Find` `Lexicographical Order` `LeetCode` `Medium`
