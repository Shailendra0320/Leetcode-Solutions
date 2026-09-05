# 3904. Smallest Stable Index II

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode (Main):** https://leetcode.com/u/Shailu03/

**LeetCode (Alternate):** https://leetcode.com/u/ShailendraLeetcode03/

---

## 🔗 Problem Link

[LeetCode - 3904. Smallest Stable Index II](https://leetcode.com/problems/smallest-stable-index-ii/)

---

# 📝 Problem Statement

You are given an integer array `nums` of length `n` and an integer `k`.

For every index `i`, define its **instability score** as:

```text
max(nums[0..i]) - min(nums[i..n-1])
```

Where:

- `max(nums[0..i])` is the maximum value from index `0` through `i`.
- `min(nums[i..n-1])` is the minimum value from index `i` through `n - 1`.

An index `i` is called **stable** if:

```text
max(nums[0..i]) - min(nums[i..n-1]) <= k
```

Return the **smallest stable index**.

If no stable index exists, return:

```text
-1
```

---

# 🔒 Constraints

```text
1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
0 <= k <= 10^9
```

---

# 💡 Understanding the Problem

For every index `i`, the array is viewed using two overlapping ranges:

```text
                     index i
                        ↓
nums = [ 0 .......... i .......... n-1 ]
        |-------------|-------------|
             PREFIX         SUFFIX
```

For that index we need two values:

```text
1. Maximum value in nums[0..i]
2. Minimum value in nums[i..n-1]
```

Then:

```text
instability(i)
        = prefix maximum - suffix minimum
```

The index is stable when that value is at most `k`.

---

# 🔎 Example

Consider:

```text
nums = [5, 0, 1, 4]
k = 3
```

At `i = 2`:

```text
PREFIX = [5, 0, 1]
SUFFIX = [1, 4]
```

Therefore:

```text
max(PREFIX) = 5
min(SUFFIX) = 1

instability = 5 - 1 = 4
```

Since:

```text
4 > 3
```

index `2` is not stable.

At `i = 3`:

```text
PREFIX = [5, 0, 1, 4]
SUFFIX = [4]

max(PREFIX) = 5
min(SUFFIX) = 4

instability = 5 - 4 = 1
```

Now:

```text
1 <= 3
```

So index `3` is stable, and because we scan from left to right, it is the smallest stable index.

---

# 🚨 Brute Force Approach

The most direct solution is to compute the instability score independently for every index.

For each `i`:

```text
1. Scan nums[0..i] to find the maximum.
2. Scan nums[i..n-1] to find the minimum.
3. Compute max - min.
4. Check whether it is <= k.
```

However, both scans can take `O(n)` for every index.

Therefore:

```text
n indices × O(n) work per index

= O(n^2)
```

With `n` up to `10^5`, this is too slow.

We need to reuse information between neighboring indices.

---

# 🧠 Key Observation 1: Prefix Maximum

Define:

```text
prefixMax[i] = max(nums[0..i])
```

Instead of recomputing the maximum prefix from scratch, we maintain it incrementally.

For example:

```text
nums = [5, 0, 1, 4]
```

The prefix maximum at every index is:

```text
Index       0   1   2   3
nums        5   0   1   4
prefixMax   5   5   5   5
```

Because:

```text
prefixMax[0] = nums[0]

prefixMax[i] = max(prefixMax[i-1], nums[i])
```

---

# 🧠 Key Observation 2: Suffix Minimum

Define:

```text
suffixMin[i] = min(nums[i..n-1])
```

For:

```text
nums = [5, 0, 1, 4]
```

we get:

```text
Index       0   1   2   3
nums        5   0   1   4
suffixMin   0   0   1   4
```

The recurrence is:

```text
suffixMin[n-1] = nums[n-1]

suffixMin[i] = min(nums[i], suffixMin[i+1])
```

---

# ⭐ Main Formula

Once we know the prefix maximum and suffix minimum:

```text
instability(i)
    = prefixMax[i] - suffixMin[i]
```

Therefore:

```text
if prefixMax[i] - suffixMin[i] <= k
    index i is stable
```

This turns the problem into a simple `O(1)` check for every index after preprocessing.

---

# 🚀 Approach 1: Prefix Maximum + Suffix Minimum

This is the most straightforward and easiest approach to understand.

We create two arrays:

```text
prefixMax[]
suffixMin[]
```

Then we scan all indices from left to right.

The first index satisfying:

```text
prefixMax[i] - suffixMin[i] <= k
```

is the answer.

---

## 🧩 Detailed Algorithm

### Step 1 — Build `prefixMax[]`

Traverse from left to right.

```text
prefixMax[0] = nums[0]

for i = 1 to n-1:
    prefixMax[i] = max(prefixMax[i-1], nums[i])
```

### Step 2 — Build `suffixMin[]`

Traverse from right to left.

```text
suffixMin[n-1] = nums[n-1]

for i = n-2 down to 0:
    suffixMin[i] = min(nums[i], suffixMin[i+1])
```

### Step 3 — Find the first stable index

Traverse from left to right:

```text
for i = 0 to n-1:
    score = prefixMax[i] - suffixMin[i]

    if score <= k:
        return i
```

If no index satisfies the condition:

```text
return -1
```

---

# 📐 Approach 1 Diagram

```text
                         nums
                          |
                          v
                 +------------------+
                 | 5 | 0 | 1 | 4  |
                 +------------------+
                    |           |
                    |           |
             LEFT  <|           |>  RIGHT
                    |           |
                    v           v
             Prefix Maximum   Suffix Minimum
                    |           |
                    v           v
              +-----------+  +-----------+
              | 5 5 5 5   |  | 0 0 1 4  |
              +-----------+  +-----------+
                    |           |
                    +-----+-----+
                          |
                          v
                 prefixMax[i]
                       -
                 suffixMin[i]
                          |
                          v
                    instability
                          |
                    compare with k
                          |
                 +--------+--------+
                 |                 |
              <= k                > k
                 |                 |
                 v                 v
              STABLE           NOT STABLE
```

---

# 🧪 Approach 1 — Complete Dry Run

Consider:

```text
nums = [5, 0, 1, 4]
k = 3
```

## Step 1: Prefix Maximum

Start with:

```text
prefixMax[0] = 5
```

Then:

```text
prefixMax[1] = max(5, 0) = 5
prefixMax[2] = max(5, 1) = 5
prefixMax[3] = max(5, 4) = 5
```

Therefore:

```text
prefixMax = [5, 5, 5, 5]
```

---

## Step 2: Suffix Minimum

Start from the last element:

```text
suffixMin[3] = 4
```

Then move backward:

```text
suffixMin[2] = min(1, 4) = 1
suffixMin[1] = min(0, 1) = 0
suffixMin[0] = min(5, 0) = 0
```

Therefore:

```text
suffixMin = [0, 0, 1, 4]
```

---

## Step 3: Check Every Index

### Index 0

```text
prefixMax[0] = 5
suffixMin[0] = 0

score = 5 - 0 = 5
```

```text
5 > 3
```

Not stable.

---

### Index 1

```text
prefixMax[1] = 5
suffixMin[1] = 0

score = 5 - 0 = 5
```

```text
5 > 3
```

Not stable.

---

### Index 2

```text
prefixMax[2] = 5
suffixMin[2] = 1

score = 5 - 1 = 4
```

```text
4 > 3
```

Not stable.

---

### Index 3

```text
prefixMax[3] = 5
suffixMin[3] = 4

score = 5 - 4 = 1
```

```text
1 <= 3
```

Stable!

Therefore:

```text
Answer = 3
```

---

# ✅ Java — Approach 1

```java
//Approach-1 (Prefix Maximum + Suffix Minimum)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];

        prefixMax[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }

        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        for (int i = 0; i < n; i++) {

            long instability =
                (long) prefixMax[i] - suffixMin[i];

            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}
```

---

# ✅ C++ — Approach 1

```cpp
//Approach-1 (Prefix Maximum + Suffix Minimum)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:
    int firstStableIndex(vector<int>& nums, int k) {

        int n = nums.size();

        vector<int> prefixMax(n);
        vector<int> suffixMin(n);

        prefixMax[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefixMax[i] = max(prefixMax[i - 1], nums[i]);
        }

        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = min(suffixMin[i + 1], nums[i]);
        }

        for (int i = 0; i < n; i++) {

            long long instability =
                (long long) prefixMax[i] - suffixMin[i];

            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
};
```

---

# 🚀 Approach 2: Suffix Minimum + Running Prefix Maximum

We do not actually need to store `prefixMax[]` for every index.

While scanning from left to right, we can maintain one variable:

```text
leftMax
```

At index `i`:

```text
leftMax = max(leftMax, nums[i])
```

After this update:

```text
leftMax = max(nums[0..i])
```

That is exactly the prefix maximum required by the problem.

We still need `suffixMin[i]`, so we keep only the suffix-minimum array.

---

# 📐 Approach 2 Diagram

```text
              Build suffixMin[]
                     |
                     v
            +-------------------+
            | suffixMin[]       |
            +-------------------+
                     |
                     v
nums -----> left-to-right scan
                     |
                     v
              maintain leftMax
                     |
                     v
        +---------------------------+
        | score = leftMax           |
        |         - suffixMin[i]    |
        +---------------------------+
                     |
                     v
              score <= k ?
                /       \
              YES        NO
               |          |
               v          v
           return i     continue
```

---

# 🧠 Why Approach 2 Works

Suppose we are currently processing index `i`.

Before processing it, assume:

```text
leftMax = max(nums[0..i-1])
```

After:

```text
leftMax = max(leftMax, nums[i])
```

we obtain:

```text
leftMax = max(nums[0..i])
```

which is exactly the required prefix maximum.

At the same time:

```text
suffixMin[i] = min(nums[i..n-1])
```

Therefore:

```text
instability(i)
    = leftMax - suffixMin[i]
```

is still exactly the same value as in Approach 1.

The only difference is that we do not store all prefix maxima.

---

# 🧪 Approach 2 — Dry Run

Again consider:

```text
nums = [5, 0, 1, 4]
k = 3
```

First calculate:

```text
suffixMin = [0, 0, 1, 4]
```

Now scan from left to right.

---

### Index 0

```text
leftMax = max(0, 5) = 5
rightMin = suffixMin[0] = 0

score = 5 - 0 = 5
```

Not stable.

---

### Index 1

```text
leftMax = max(5, 0) = 5
rightMin = suffixMin[1] = 0

score = 5 - 0 = 5
```

Not stable.

---

### Index 2

```text
leftMax = max(5, 1) = 5
rightMin = suffixMin[2] = 1

score = 5 - 1 = 4
```

Not stable.

---

### Index 3

```text
leftMax = max(5, 4) = 5
rightMin = suffixMin[3] = 4

score = 5 - 4 = 1
```

```text
1 <= 3
```

Therefore:

```text
Answer = 3
```

---

# ✅ Java — Approach 2

```java
//Approach-2 (Suffix Minimum + Running Prefix Maximum)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        int[] suffixMin = new int[n];

        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        int leftMax = 0;

        for (int i = 0; i < n; i++) {

            leftMax = Math.max(leftMax, nums[i]);

            long instability =
                (long) leftMax - suffixMin[i];

            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}
```

---

# ✅ C++ — Approach 2

```cpp
//Approach-2 (Suffix Minimum + Running Prefix Maximum)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:
    int firstStableIndex(vector<int>& nums, int k) {

        int n = nums.size();

        vector<int> suffixMin(n);

        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = min(suffixMin[i + 1], nums[i]);
        }

        int leftMax = 0;

        for (int i = 0; i < n; i++) {

            leftMax = max(leftMax, nums[i]);

            long long instability =
                (long long) leftMax - suffixMin[i];

            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
};
```

---

# 🧪 More Examples

## Example 1

```text
Input:
nums = [5, 0, 1, 4]
k = 3

Output:
3
```

Scores:

```text
i = 0 -> 5
 i = 1 -> 5
 i = 2 -> 4
 i = 3 -> 1
```

The first score `<= 3` occurs at index `3`.

---

## Example 2

```text
Input:
nums = [3, 2, 1]
k = 1

Output:
-1
```

For every index:

```text
prefix maximum = 3
suffix minimum = 1
score = 3 - 1 = 2
```

Since:

```text
2 > 1
```

there is no stable index.

---

## Example 3

```text
Input:
nums = [0]
k = 0

Output:
0
```

At index `0`:

```text
max([0]) = 0
min([0]) = 0
score = 0
```

Since:

```text
0 <= 0
```

index `0` is stable.

---

## Example 4

```text
Input:
nums = [2, 5, 7]
k = 5
```

At index `0`:

```text
max([2]) = 2
min([2, 5, 7]) = 2

score = 2 - 2 = 0
```

Therefore the first index is already stable.

```text
Output = 0
```

---

# 🔍 Important Insight About the First Index

At `i = 0`:

```text
nums[0..0] = [nums[0]]
```

So the prefix maximum is simply:

```text
nums[0]
```

The suffix minimum is:

```text
min(nums[0..n-1])
```

Therefore:

```text
score(0)
    = nums[0] - globalMinimum
```

This can sometimes immediately tell us that index `0` is stable.

---

# 🔍 Important Insight About the Last Index

At `i = n - 1`:

```text
nums[i..n-1]
```

contains only one element.

Therefore:

```text
suffixMin[n-1] = nums[n-1]
```

and the score becomes:

```text
max(nums[0..n-1]) - nums[n-1]
```

So the last index is stable exactly when that difference is at most `k`.

---

# ✅ Correctness Proof

We prove that the algorithm always returns the correct answer.

## Lemma 1: Prefix maximum is correct

For every index `i`:

```text
prefixMax[i] = max(nums[0..i])
```

Base case:

```text
prefixMax[0] = nums[0]
```

which is the maximum of the one-element prefix.

For `i > 0`:

```text
prefixMax[i]
    = max(prefixMax[i-1], nums[i])
```

By the induction hypothesis, `prefixMax[i-1]` is the maximum of `nums[0..i-1]`.

Taking its maximum with `nums[i]` therefore gives the maximum of `nums[0..i]`.

Hence the prefix maximum is correct for every index.

---

## Lemma 2: Suffix minimum is correct

For every index `i`:

```text
suffixMin[i] = min(nums[i..n-1])
```

Base case:

```text
suffixMin[n-1] = nums[n-1]
```

which is correct for the one-element suffix.

For earlier indices:

```text
suffixMin[i]
    = min(nums[i], suffixMin[i+1])
```

By the induction hypothesis, `suffixMin[i+1]` is the minimum of `nums[i+1..n-1]`.

Taking the minimum with `nums[i]` therefore gives the minimum of `nums[i..n-1]`.

Hence the suffix minimum is correct for every index.

---

## Lemma 3: The computed score is the true instability score

For each index `i`, we have:

```text
prefixMax[i] = max(nums[0..i])
```

and:

```text
suffixMin[i] = min(nums[i..n-1])
```

Therefore:

```text
prefixMax[i] - suffixMin[i]
```

is exactly:

```text
max(nums[0..i]) - min(nums[i..n-1])
```

which is the problem's definition of instability score.

So the algorithm correctly determines whether each index is stable.

---

## Theorem: The returned index is the smallest stable index

The algorithm checks indices in increasing order:

```text
0, 1, 2, ..., n-1
```

For every index it checks whether:

```text
instability <= k
```

When the first valid index is found, every smaller index has already been checked and was not stable.

Therefore the returned index is exactly the **smallest stable index**.

If no index satisfies the condition, returning `-1` is correct.

Hence the algorithm is correct.

---

# 📊 Complexity Analysis

## Approach 1

Build `prefixMax[]`:

```text
O(n)
```

Build `suffixMin[]`:

```text
O(n)
```

Check all indices:

```text
O(n)
```

Total:

```text
Time Complexity  : O(n)
Space Complexity : O(n)
```

Two arrays of size `n` are stored.

---

## Approach 2

Build `suffixMin[]`:

```text
O(n)
```

Scan the array while maintaining `leftMax`:

```text
O(n)
```

Total:

```text
Time Complexity  : O(n)
Space Complexity : O(n)
```

Only one auxiliary array is stored.

---

# ⚖️ Approach Comparison

| Approach   | Prefix Max       | Suffix Min      | Time | Extra Space |
| ---------- | ---------------- | --------------- | ---- | ----------- |
| Approach 1 | Stored in array  | Stored in array | O(n) | O(n)        |
| Approach 2 | Running variable | Stored in array | O(n) | O(n)        |

### 🏆 Recommended Approach

**Approach 2** is preferable for implementation because it avoids storing `prefixMax[]` separately.

Instead of:

```text
prefixMax[]
```

we maintain:

```text
leftMax
```

while traversing from left to right.

The resulting algorithm remains `O(n)` time and uses only one auxiliary array.

---

# ❌ Why Not Sorting?

Sorting does not directly help because the answer depends on **index ranges**:

```text
nums[0..i]
nums[i..n-1]
```

Reordering the elements would destroy their original positions.

The problem is therefore naturally suited to prefix/suffix preprocessing rather than sorting.

---

# ⚠️ Common Mistakes

## 1. Using the global maximum

The problem asks for:

```text
max(nums[0..i])
```

not:

```text
max(nums[0..n-1])
```

The left range ends at the current index.

---

## 2. Using the global minimum

The problem asks for:

```text
min(nums[i..n-1])
```

not the minimum of the entire array for every index.

---

## 3. Excluding `nums[i]`

Both ranges include index `i`:

```text
nums[0..i]
nums[i..n-1]
```

So `nums[i]` belongs to both sides.

Do not accidentally calculate:

```text
nums[0..i-1]
```

or:

```text
nums[i+1..n-1]
```

---

## 4. Building suffix minimum in the wrong direction

Suffix information naturally moves from right to left:

```text
n-1 -> n-2 -> ... -> 0
```

---

## 5. Returning the last valid index

The problem asks for the **smallest** stable index.

Therefore, scan from:

```text
0 -> n-1
```

and return immediately when a valid index is found.

---

## 6. Using `O(n²)` repeated scans

Do not calculate the prefix maximum and suffix minimum from scratch for every index.

Reuse previously computed information through prefix/suffix preprocessing.

---

# 🧩 Edge Cases

## Edge Case 1: Single Element

```text
nums = [7]
k = 0
```

```text
max([7]) - min([7])
= 7 - 7
= 0
```

Therefore:

```text
Answer = 0
```

---

## Edge Case 2: `k = 0`

A stable index must satisfy:

```text
max(prefix) - min(suffix) = 0
```

So the two values must be exactly equal.

---

## Edge Case 3: No Stable Index

```text
nums = [3, 2, 1]
k = 1
```

Every score is:

```text
3 - 1 = 2
```

Since:

```text
2 > 1
```

return:

```text
-1
```

---

## Edge Case 4: First Index Is Stable

```text
nums = [2, 5, 7]
k = 5
```

At index `0`:

```text
2 - 2 = 0
```

So:

```text
Answer = 0
```

---

## Edge Case 5: Stable Index Appears Only at the End

```text
nums = [5, 0, 1, 4]
k = 3
```

The scores are:

```text
5, 5, 4, 1
```

Only index `3` is stable.

```text
Answer = 3
```

---

# 🧠 Pattern Recognition

This problem is an important example of the:

```text
PREFIX + SUFFIX PREPROCESSING
```

pattern.

Whenever a problem asks something like:

```text
max(left part) - min(right part)
```

or:

```text
min(left part) + max(right part)
```

consider whether prefix/suffix arrays can store the required range information once.

The general transformation is:

```text
Repeated range calculation
          ↓
Precompute range information
          ↓
O(1) query for each index
          ↓
O(n) total solution
```

---

# 🎯 Key Takeaways

- Every index has an instability score based on a prefix maximum and suffix minimum.
- The prefix maximum can be maintained from left to right.
- The suffix minimum can be computed from right to left.
- After preprocessing, each stability check takes `O(1)` time.
- Scanning from left to right ensures the first valid index is the smallest stable index.
- The brute-force solution is `O(n²)`, while the optimized solution is `O(n)`.
- Approach 2 is more memory-efficient than Approach 1 because it stores only `suffixMin[]`.

---

# 🏁 Final Summary

The central idea is simple:

```text
For every index i:

    leftMax  = max(nums[0..i])
    rightMin = min(nums[i..n-1])

    instability = leftMax - rightMin

    if instability <= k:
        return i

return -1
```

The trick is to avoid recalculating `leftMax` and `rightMin` for every index.

Use:

```text
Prefix Maximum
+
Suffix Minimum
```

to solve the problem efficiently in:

```text
O(n) time
O(n) extra space
```

---

# 🏷️ Tags

`Array` `Prefix Maximum` `Suffix Minimum` `Prefix Suffix` `Preprocessing` `Greedy` `Medium`

---

## 📌 Recommended GitHub Title

```text
3904. Smallest Stable Index II | Prefix & Suffix | Java & C++
```
