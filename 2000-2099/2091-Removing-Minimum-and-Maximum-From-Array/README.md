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

# 2091. Removing Minimum and Maximum From Array

## Problem Statement

You are given a 0-indexed array of distinct integers `nums`.

The array contains one minimum element and one maximum element. You must remove both elements.

In one deletion, you can remove either the first element or the last element of the array.

Return the minimum number of deletions required to remove both the minimum and maximum elements.

Source: [LeetCode 2091](https://leetcode.com/problems/removing-minimum-and-maximum-from-array/). citeturn0search0

---

# Examples

## Example 1

```text
Input:
nums = [2,10,7,5,4,1,8,6]

Output:
5
```

Minimum = `1` at index `5`.

Maximum = `10` at index `1`.

We can remove `2` elements from the front and `3` from the back:

```text
2 + 3 = 5
```

---

## Example 2

```text
Input:
nums = [0,-4,19,1,8,-2,-3,5]

Output:
3
```

Minimum = `-4` at index `1`.

Maximum = `19` at index `2`.

Removing the first three elements removes both.

Answer:

```text
3
```

---

## Example 3

```text
Input:
nums = [101]

Output:
1
```

The only element is both the minimum and maximum, so one deletion is enough.

---

# Constraints

```text
1 <= nums.length <= 10^5
-10^5 <= nums[i] <= 10^5
All integers in nums are distinct
```

---

# Core Observation

Only the **indices** of the minimum and maximum matter.

Let:

```text
left = min(minIndex, maxIndex)
right = max(minIndex, maxIndex)
```

There are exactly three useful strategies:

```text
1. Remove both from the front.
2. Remove both from the back.
3. Remove one from the front and one from the back.
```

We calculate all three and take the minimum.

---

# Approach 1 — Direct Simulation Idea

## Intuition

A straightforward idea is to repeatedly delete elements from either end until both the minimum and maximum disappear.

However, physically performing deletions is unnecessary.

The number of deletions depends only on where the two target elements are located.

So the simulation can be reduced to calculating the three possible costs.

---

## Complexity Analysis

Finding the minimum and maximum indices requires one traversal:

```text
Time Complexity  → O(n)
Space Complexity → O(1)
```

---

## Java Code

```java
class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        int fromFront = right + 1;
        int fromBack = n - left;
        int fromBoth = (left + 1) + (n - right);

        return Math.min(fromFront,
                Math.min(fromBack, fromBoth));
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
    int minimumDeletions(vector<int>& nums) {
        int n = nums.size();

        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int left = min(minIndex, maxIndex);
        int right = max(minIndex, maxIndex);

        int fromFront = right + 1;
        int fromBack = n - left;
        int fromBoth = (left + 1) + (n - right);

        return min({fromFront, fromBack, fromBoth});
    }
};
```

---

# Approach 2 — Optimal Three-Case Greedy

## Intuition

Every deletion is either from the:

```text
front
```

or:

```text
back
```

Therefore, for the two special elements, only three useful combinations exist.

### Case 1 — Both From Front

The farther element is at index `right`.

To remove it from the front, we need:

```text
right + 1
```

deletions.

---

### Case 2 — Both From Back

The earlier element is at index `left`.

To remove it from the back, we need:

```text
n - left
```

deletions.

---

### Case 3 — One From Each Side

Remove the element at `left` from the front:

```text
left + 1
```

Remove the element at `right` from the back:

```text
n - right
```

Total:

```text
left + 1 + n - right
```

Therefore:

```text
answer =
min(
    right + 1,
    n - left,
    left + 1 + n - right
)
```

---

# Algorithm

```text
1. Find minIndex.
2. Find maxIndex.
3. Set:
       left  = min(minIndex, maxIndex)
       right = max(minIndex, maxIndex)
4. Calculate:
       A = right + 1
       B = n - left
       C = left + 1 + n - right
5. Return min(A, B, C).
```

---

# Detailed Dry Run

Consider:

```text
nums = [2,10,7,5,4,1,8,6]
```

```text
n = 8
```

Minimum:

```text
1 → index 5
```

Maximum:

```text
10 → index 1
```

So:

```text
left = 1
right = 5
```

## Option 1 — Both From Front

```text
right + 1
= 5 + 1
= 6
```

## Option 2 — Both From Back

```text
n - left
= 8 - 1
= 7
```

## Option 3 — One From Each Side

```text
left + 1 + n - right
= 1 + 1 + 8 - 5
= 5
```

Therefore:

```text
min(6, 7, 5) = 5
```

Answer:

```text
5
```

---

# Detailed Dry Run — Example 2

```text
nums = [0,-4,19,1,8,-2,-3,5]
```

```text
n = 8
minIndex = 1
maxIndex = 2
```

Therefore:

```text
left = 1
right = 2
```

Costs:

```text
Both front:
right + 1 = 3

Both back:
n - left = 7

One each:
left + 1 + n - right
= 2 + 6
= 8
```

Answer:

```text
min(3,7,8) = 3
```

---

# Detailed Dry Run — Example 3

```text
nums = [101]
```

```text
n = 1
left = 0
right = 0
```

Costs:

```text
right + 1 = 1
n - left = 1
left + 1 + n - right = 2
```

Answer:

```text
1
```

---

# Correctness Proof

## Lemma 1 — The Three Cases Are Exhaustive

Each target element must be removed from either the front or the back.

For two elements, this produces exactly these meaningful cases:

```text
front + front
back + back
front + back
```

The reverse `back + front` is represented by the same mixed cost.

Therefore, no other strategy can do better.

---

## Lemma 2 — Front Cost

The farther target is at index `right`.

Removing it from the front requires deleting:

```text
0 ... right
```

which contains:

```text
right + 1
```

elements.

---

## Lemma 3 — Back Cost

The earlier target is at index `left`.

Removing it from the back requires deleting:

```text
left ... n-1
```

which contains:

```text
n - left
```

elements.

---

## Lemma 4 — Mixed Cost

Remove the left target from the front:

```text
left + 1
```

Then remove the right target from the back:

```text
n - right
```

Total:

```text
left + 1 + n - right
```

---

## Theorem

Every valid deletion strategy belongs to one of the three cases.

The algorithm computes the exact cost of each case and returns the minimum.

Therefore, the returned value is the minimum number of deletions required.

---

# Complexity Analysis

Let:

```text
n = nums.length
```

We scan the array once to find the minimum and maximum:

```text
O(n)
```

The three costs are calculated in constant time:

```text
O(1)
```

Therefore:

```text
Time Complexity  → O(n)
Space Complexity → O(1)
```

---

# Complexity Comparison

| Approach               |  Time Complexity | Space Complexity | Practical |
| ---------------------- | ---------------: | ---------------: | --------- |
| Physical Simulation    | `O(n²)` or worse |           `O(n)` | ❌        |
| Three-Case Calculation |           `O(n)` |           `O(1)` | ✅        |

---

# Why We Don't Actually Delete Anything

We do not need to modify `nums`.

Once we know:

```text
minIndex
maxIndex
```

the answer can be calculated mathematically.

This avoids unnecessary array operations and keeps the solution:

```text
O(n) time
O(1) extra space
```

---

# Code Walkthrough

## 1. Find Minimum Index

```java
if (nums[i] < nums[minIndex]) {
    minIndex = i;
}
```

Stores the position of the smallest value.

---

## 2. Find Maximum Index

```java
if (nums[i] > nums[maxIndex]) {
    maxIndex = i;
}
```

Stores the position of the largest value.

---

## 3. Normalize the Positions

```java
int left = Math.min(minIndex, maxIndex);
int right = Math.max(minIndex, maxIndex);
```

Now:

```text
left <= right
```

regardless of which target is the minimum or maximum.

---

## 4. Calculate Three Costs

```java
int fromFront = right + 1;
int fromBack = n - left;
int fromBoth = (left + 1) + (n - right);
```

---

## 5. Return the Minimum

```java
return Math.min(
    fromFront,
    Math.min(fromBack, fromBoth)
);
```

---

# Edge Cases

## Case 1 — One Element

```text
nums = [5]
```

The same element is both minimum and maximum.

Answer:

```text
1
```

---

## Case 2 — Minimum and Maximum at Both Ends

```text
nums = [1,5,6,10]
```

Remove:

```text
1 from front
1 from back
```

Answer:

```text
2
```

---

## Case 3 — Both Near the Front

```text
nums = [1,10,5,6,7,8]
```

Removing both from the front can be optimal.

---

## Case 4 — Both Near the Back

If both target elements are near the end, removing from the back can be optimal.

---

## Case 5 — One Near Each End

The mixed strategy can be best:

```text
left + 1 + n - right
```

---

# Common Mistakes

## Mistake 1 — Checking Only Front Deletions

Sometimes the back strategy is smaller.

---

## Mistake 2 — Checking Only Back Deletions

Sometimes removing from the front is optimal.

---

## Mistake 3 — Forgetting the Mixed Strategy

The mixed strategy is often the optimal one.

---

## Mistake 4 — Using Values Instead of Indices

The deletion count depends on the **positions**, not the numerical values.

---

## Mistake 5 — Actually Removing Elements

No simulation is needed.

Just calculate the three possible costs.

---

# Key Takeaways

```text
1. Find the indices of minimum and maximum.

2. Normalize them as:
       left <= right

3. Calculate three possibilities:
       both from front
       both from back
       one from each side

4. Take the minimum.

5. No array modification is required.

6. The optimal complexity is O(n) time and O(1) space.
```

---

# One-Line Insight

> **Find the minimum and maximum indices, calculate the cost of removing both from the front, both from the back, or one from each side, and return the smallest cost.**

---

# Final Summary

```text
             Find min & max
                   │
                   ▼
        left = min(indexes)
       right = max(indexes)
                   │
       ┌───────────┼───────────┐
       ▼           ▼           ▼
    Front        Back       Both sides
   right + 1    n - left   left+1+n-right
       │           │           │
       └───────────┼───────────┘
                   ▼
                 min
                   │
                   ▼
                Answer
```

---

# Final Complexity

```text
Time Complexity  → O(n)
Space Complexity → O(1)
```

---

# Tags

`Array` `Greedy` `Simulation` `Math` `Indexing` `LeetCode` `Medium`
