# 1658. Minimum Operations to Reduce X to Zero — Sliding Window and Prefix Sum

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

## 🧩 Problem Statement

You are given an integer array `nums` and an integer `x`.

In one operation, you can remove either the **leftmost** or **rightmost** element of the array and subtract its value from `x`.

Return the **minimum number of operations** required to make `x` exactly `0`.

If it is impossible, return `-1`.

### Example

```text
Input:
nums = [1,1,4,2,3]
x = 5

Output:
2
```

One optimal sequence is:

```text
Remove 3 from the right:
[1,1,4,2]    x = 2

Remove 2 from the right:
[1,1,4]      x = 0
```

So the answer is `2`.

---

# 🎯 What Is the Question Really Asking?

At first glance, the problem looks like a greedy removal problem: remove from the left or remove from the right until the removed sum becomes `x`.

The important observation is to look at the problem from the opposite direction.

Let:

```text
Total Sum = S
```

We remove elements whose sum is `x`.

Therefore, the elements that remain in the middle must have sum:

```text
S - x
```

So the original problem becomes:

> **Find the longest contiguous subarray whose sum is `S - x`.**

Why the **longest** subarray? If the longest remaining subarray has length `L`, then the number of removed elements is:

```text
n - L
```

Therefore:

```text
Minimum Operations = n - Maximum Length of Subarray With Sum (S - x)
```

This transformation is the central idea of the problem.

---

# 💡 Core Observation

Let:

```text
target = totalSum - x
```

Then:

```text
remove prefix + remove suffix = x
```

is equivalent to:

```text
keep middle subarray = totalSum - x
```

So:

```text
Original Problem
       ↓
Choose elements from both ends
       ↓
Think about the elements that remain
       ↓
Find longest subarray with sum = totalSum - x
       ↓
Answer = n - longestLength
```

---

# 🧠 Why Does the Remaining Part Have to Be Contiguous?

Operations can only remove:

- elements from the left end
- elements from the right end

Therefore, after all removals, anything left in the array must form one continuous middle segment.

For example:

```text
[2, 3, 1, 2, 4, 3]
 ↑                    ↑
remove left       remove right
```

The remaining elements always look like:

```text
[ ... remaining contiguous subarray ... ]
```

We cannot remove an element from the middle while keeping elements on both sides.

That is why the problem converts naturally into a **longest subarray** problem.

---

# 📐 Mathematical Derivation

Let:

```text
S = sum(nums)
```

Suppose we remove elements with total sum `x`.

Then:

```text
remainingSum = S - x
```

If the longest remaining subarray has length `L`, then:

```text
removedElements = n - L
```

Therefore:

```text
answer = n - L
```

where:

```text
L = longest subarray whose sum is S - x
```

So the complete formula is:

```text
target = totalSum - x
answer = n - longestSubarrayLength(target)
```

---

# 🚀 Approach 1 — Sliding Window

## Main Idea

Because the array contains positive integers, we can use a **sliding window**.

We maintain a window:

```text
[left ... right]
```

and track its sum with `windowSum`.

When we expand the window:

```text
windowSum += nums[right]
```

If the sum becomes too large:

```text
while (windowSum > target)
```

shrink it from the left:

```text
windowSum -= nums[left]
left++
```

Whenever:

```text
windowSum == target
```

we have found a valid subarray. We maximize its length.

---

## Sliding Window Diagram

```text
nums = [1, 1, 4, 2, 3]
target = 6

[1, 1, 4]
 ↑     ↑
left  right

sum = 1 + 1 + 4 = 6
length = 3
```

Therefore:

```text
answer = 5 - 3 = 2
```

---

## Sliding Window Flow

```text
Calculate totalSum
       ↓
target = totalSum - x
       ↓
Is target < 0?
   ┌───┴───┐
  Yes      No
   ↓        ↓
 return -1  Start sliding window
              ↓
        Expand right
              ↓
       sum > target?
          ┌──┴──┐
         Yes    No
          ↓      ↓
     Shrink left  Check sum == target
                       ↓
                 Update maxLength
                       ↓
                  Continue
                       ↓
              answer = n - maxLength
```

---

## Correctness of Approach 1

### 1. Every valid sequence of removals leaves a contiguous subarray

Since only the leftmost and rightmost elements can be removed, all remaining elements form one contiguous middle segment.

### 2. The remaining segment must have sum `totalSum - x`

The removed elements have total sum `x`. Therefore:

```text
remaining sum = totalSum - x
```

### 3. Minimizing removals is equivalent to maximizing the remaining length

If the remaining subarray has length `L`, then:

```text
operations = n - L
```

Thus minimizing operations is exactly the same as maximizing `L`.

Therefore, finding the longest subarray with sum `totalSum - x` produces the minimum possible number of operations.

---

# ⚡ Approach 2 — Prefix Sum + HashMap

The same transformation can be solved using prefix sums.

We want:

```text
sum(subarray) = target
```

For a subarray from `j + 1` to `i`:

```text
prefix[i] - prefix[j] = target
```

Therefore:

```text
prefix[j] = prefix[i] - target
```

So while scanning the array, for the current prefix sum we look for:

```text
currentPrefix - target
```

in a hash map.

---

## Prefix Sum Example

```text
nums = [1,1,4,2,3]
target = 6
```

Prefix sums:

```text
index:     0  1  2  3   4   5
prefix:    0  1  2  6   8  11
```

At prefix sum `6`:

```text
6 - target
= 6 - 6
= 0
```

Prefix sum `0` exists at index `0`.

Therefore:

```text
subarray = [1,1,4]
length = 3
```

So:

```text
answer = 5 - 3 = 2
```

---

# 🧠 Why Store the Earliest Prefix Index?

Suppose a particular prefix sum appears multiple times.

For a current index `i`, we want the longest possible subarray:

```text
length = i - previousIndex
```

Therefore, for each prefix sum, we store its **earliest occurrence**.

Earlier index → longer subarray.

This is a standard prefix-sum optimization.

---

# 🔬 Comparison of Both Approaches

| Feature                   |       Approach 1: Sliding Window |  Approach 2: Prefix Sum + HashMap |
| ------------------------- | -------------------------------: | --------------------------------: |
| Core Idea                 | Longest subarray with target sum |             Prefix-sum difference |
| Time                      |                             O(n) |                      O(n) average |
| Space                     |                             O(1) |                              O(n) |
| Requires positive numbers |                              Yes |                                No |
| Main data structure       |                     Two pointers |                           HashMap |
| Key strength              |             Constant extra space | More general subarray-sum pattern |

The sliding-window solution uses the fact that `nums` contains positive integers, while the prefix-sum method is a more general subarray-sum technique.

---

# 💻 Java — Two Best Approaches in One File

```java
import java.util.*;

//Approach-1 (Sliding Window)
//T.C : O(n)
//S.C : O(1)

class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;

        if (target < 0) {
            return -1;
        }

        if (target == 0) {
            return n;
        }

        int left = 0;
        int windowSum = 0;
        int maxLength = -1;

        for (int right = 0; right < n; right++) {
            windowSum += nums[right];

            while (left <= right && windowSum > target) {
                windowSum -= nums[left++];
            }

            if (windowSum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength == -1 ? -1 : n - maxLength;
    }
}


//Approach-2 (Prefix Sum + HashMap)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;

        if (target < 0) {
            return -1;
        }

        if (target == 0) {
            return n;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int prefixSum = 0;
        int maxLength = -1;

        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];

            if (map.containsKey(prefixSum - target)) {
                int previousIndex = map.get(prefixSum - target);
                maxLength = Math.max(maxLength, i - previousIndex);
            }

            map.putIfAbsent(prefixSum, i);
        }

        return maxLength == -1 ? -1 : n - maxLength;
    }
}
```

---

# 💻 C++ — Two Best Approaches in One File

```cpp
#include <bits/stdc++.h>
using namespace std;

//Approach-1 (Sliding Window)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int n = nums.size();
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;

        if (target < 0) {
            return -1;
        }

        if (target == 0) {
            return n;
        }

        int left = 0;
        int windowSum = 0;
        int maxLength = -1;

        for (int right = 0; right < n; right++) {
            windowSum += nums[right];

            while (left <= right && windowSum > target) {
                windowSum -= nums[left++];
            }

            if (windowSum == target) {
                maxLength = max(maxLength, right - left + 1);
            }
        }

        return maxLength == -1 ? -1 : n - maxLength;
    }
};


//Approach-2 (Prefix Sum + HashMap)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
public:
    int minOperations(vector<int>& nums, int x) {
        int n = nums.size();
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;

        if (target < 0) {
            return -1;
        }

        if (target == 0) {
            return n;
        }

        unordered_map<int, int> mp;
        mp[0] = -1;

        int prefixSum = 0;
        int maxLength = -1;

        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];

            if (mp.find(prefixSum - target) != mp.end()) {
                int previousIndex = mp[prefixSum - target];
                maxLength = max(maxLength, i - previousIndex);
            }

            if (mp.find(prefixSum) == mp.end()) {
                mp[prefixSum] = i;
            }
        }

        return maxLength == -1 ? -1 : n - maxLength;
    }
};
```

---

# 🧪 Important Test Cases

### Case 1

```text
nums = [1,1,4,2,3]
x = 5

total = 11
target = 6

longest subarray = [1,1,4]
length = 3

answer = 5 - 3 = 2
```

### Case 2

```text
nums = [5,6,7,8,9]
x = 4
```

Here:

```text
total = 35
target = 31
```

No subarray has sum `31`.

Therefore:

```text
answer = -1
```

### Case 3

```text
nums = [3,2,20,1,1,3]
x = 10
```

```text
total = 30
target = 20
```

Longest subarray with sum `20`:

```text
[20]
```

So:

```text
answer = 6 - 1 = 5
```

### Case 4

```text
nums = [1,1]
x = 2
```

```text
total = 2
target = 0
```

We remove both elements:

```text
answer = 2
```

---

# ⚠️ Common Mistakes

## Mistake 1 — Directly Trying Every Prefix and Suffix

Trying every possible number of removals from the left and right can lead to O(n²) time.

The subarray transformation reduces the problem to O(n).

## Mistake 2 — Finding the Shortest Subarray

We need the **longest** subarray with sum:

```text
totalSum - x
```

Not the shortest.

Remember:

```text
operations = n - remainingLength
```

## Mistake 3 — Using Sliding Window Without Positive Numbers

Sliding window relies on the fact that expanding the window increases the sum and shrinking it decreases the sum.

That monotonic behavior comes from positive numbers.

For arbitrary negative numbers, this sliding-window technique is not valid.

## Mistake 4 — Forgetting `map.put(0, -1)`

In the prefix-sum solution:

```java
map.put(0, -1);
```

is essential. It allows a valid subarray starting from index `0` to be detected.

---

# 🎯 Pattern Recognition

When you see:

```text
Remove elements from left/right
+
Minimize number of removals
+
Array contains positive values
```

ask:

> **What remains after the removals?**

Often, the problem transforms into:

```text
Minimum removals
        ↓
Maximum elements remaining
        ↓
Longest subarray
```

For this problem:

```text
Minimum operations
        ↓
Longest remaining subarray
        ↓
Subarray sum = totalSum - x
```

This is the key pattern.

---

# 🗣️ Interview Thought Process

A strong explanation is:

> “Instead of deciding which elements to remove from the two ends, I consider the elements that remain. Since only elements from the ends can be removed, the remaining elements must form a contiguous subarray. If the total array sum is S, then the remaining subarray must have sum S - x. To minimize the number of removals, I need to maximize the length of that subarray. Because all numbers are positive, I can find that longest subarray using a sliding window in O(n) time and O(1) space.”

---

# 📊 Complexity

### Approach 1 — Sliding Window

```text
Time Complexity  : O(n)
Space Complexity : O(1)
```

### Approach 2 — Prefix Sum + HashMap

```text
Time Complexity  : O(n) average
Space Complexity : O(n)
```

---

# 🏷️ Tags

`Array` `Sliding Window` `Two Pointers` `Prefix Sum` `Hash Map` `Subarray` `Longest Subarray` `Positive Integers` `Optimization` `LeetCode` `Medium`

---

# 🔥 One-Line Insight

> **Instead of minimizing what you remove, maximize what you can keep: find the longest subarray with sum `totalSum - x`, then answer `n - length`.**

---

# ✅ Final Takeaway

The hardest part of LeetCode 1658 is not the sliding window itself. The real trick is the transformation:

```text
Remove from both ends
        ↓
Look at what remains
        ↓
Remaining part is contiguous
        ↓
Required remaining sum = totalSum - x
        ↓
Find longest subarray with that sum
        ↓
Answer = n - longestLength
```

Once this transformation becomes familiar, the problem becomes a standard **longest subarray with target sum** problem.
