# 2996. Smallest Missing Integer Greater Than Sequential Prefix Sum

## 🔗 Problem Link

**LeetCode Problem:** https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/

---

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode (Main):** https://leetcode.com/u/Shailu03/

**LeetCode (Second):** https://leetcode.com/Shailu03/

---

# 📌 Problem Statement

You are given an integer array `nums`.

A **sequential prefix** is a prefix of the array where every consecutive pair of elements satisfies:

```text
nums[i] = nums[i - 1] + 1
```

The sequential prefix starts from `nums[0]` and continues until this condition is violated.

The **sequential prefix sum** is the sum of all elements belonging to this prefix.

After finding this sum, return the **smallest integer greater than or equal to the sequential prefix sum that does not appear in `nums`**.

---

# 💡 Intuition

The problem can be solved in two simple stages:

```text
1. Find the sequential prefix sum.
2. Find the smallest missing integer starting from that sum.
```

For the first part, we traverse the array from left to right.

As long as the current element is exactly one greater than the previous element, it belongs to the sequential prefix.

For example:

```text
nums = [1,2,3,5,6]
```

The sequential prefix is:

```text
1 → 2 → 3
```

because:

```text
2 = 1 + 1
3 = 2 + 1
```

But:

```text
5 != 3 + 1
```

so the prefix ends.

The prefix sum is:

```text
1 + 2 + 3 = 6
```

Now we start checking from `6`.

If `6` exists in the array, we try `7`, then `8`, and so on until we find a number that is not present.

---

# 🔍 Key Observation

The sequential prefix can be identified using:

```text
nums[i] == nums[i - 1] + 1
```

Once this condition becomes false, there is no need to check any later elements for the prefix.

After calculating the prefix sum, we need efficient existence checks.

Therefore, we store all array elements inside a:

```text
HashSet
```

This allows us to check whether a number exists in approximately:

```text
O(1)
```

average time.

---

# 🚀 Approach — HashSet + Sequential Prefix

The solution uses:

```text
Sequential Prefix
        +
HashSet
```

### Step 1 — Store All Elements

Insert every element of `nums` into a `HashSet`.

```text
seen = {all elements of nums}
```

This allows fast lookup.

---

### Step 2 — Calculate Sequential Prefix Sum

Initialize:

```text
prefixSum = nums[0]
```

Then traverse the remaining elements.

If:

```text
nums[i] == nums[i - 1] + 1
```

add the current element:

```text
prefixSum += nums[i]
```

Otherwise, stop.

---

### Step 3 — Find the Smallest Missing Integer

Start with:

```text
prefixSum
```

While the value already exists:

```text
prefixSum++
```

Once the value is not present in the set, return it.

---

# 📝 Algorithm

1. Create a `HashSet<Integer>`.
2. Insert every element of `nums` into the set.
3. Initialize:

```text
prefixSum = nums[0]
```

4. Traverse the array from index `1`.
5. Check whether:

```text
nums[i] == nums[i - 1] + 1
```

6. If true, add `nums[i]` to `prefixSum`.
7. If false, stop the prefix traversal.
8. While `prefixSum` exists in the `HashSet`, increment it.
9. Return `prefixSum`.

---

# 🌳 Flowchart

```text
                         Start
                           │
                           ▼
                  Create HashSet
                           │
                           ▼
                Insert all array elements
                           │
                           ▼
                 prefixSum = nums[0]
                           │
                           ▼
                  Check next element
                           │
                           ▼
            nums[i] == nums[i-1] + 1 ?
                    ┌──────┴──────┐
                   Yes            No
                    │              │
                    ▼              ▼
             Add nums[i]      Stop prefix
             to prefixSum          │
                    │              │
                    └──────┬───────┘
                           ▼
                Is prefixSum present?
                    ┌──────┴──────┐
                   Yes            No
                    │              │
                    ▼              ▼
              prefixSum++      Return answer
                    │
                    └──────► Check again
```

---

# 📖 Example 1

### Input

```text
nums = [1,2,3,5,6]
```

### Sequential Prefix

```text
1 → 2 → 3
```

The next element is:

```text
5
```

Since:

```text
5 != 3 + 1
```

the sequential prefix ends.

### Prefix Sum

```text
1 + 2 + 3 = 6
```

Now check:

```text
6
```

Since `6` exists in the array, try:

```text
7
```

`7` does not exist.

Therefore:

```text
Answer = 7
```

---

# 🔄 Dry Run

Consider:

```text
nums = [2,3,4,7,8]
```

### Step 1 — HashSet

```text
seen = {2,3,4,7,8}
```

### Step 2 — Sequential Prefix

Start:

```text
prefixSum = 2
```

Check `3`:

```text
3 == 2 + 1
```

So:

```text
prefixSum = 2 + 3 = 5
```

Check `4`:

```text
4 == 3 + 1
```

So:

```text
prefixSum = 5 + 4 = 9
```

Check `7`:

```text
7 != 4 + 1
```

Stop.

Therefore:

```text
prefixSum = 9
```

### Step 3 — Find Missing Integer

Check:

```text
9 ∈ seen ?
```

No.

Therefore:

```text
Answer = 9
```

---

# 🧠 Why Use HashSet?

After calculating the prefix sum, we may need to check multiple consecutive numbers.

For example:

```text
prefixSum = 6
```

and the array contains:

```text
6, 7, 8, 9
```

We need to efficiently check:

```text
6
7
8
9
10
```

A `HashSet` provides average `O(1)` lookup time.

Therefore, instead of scanning the complete array for every number, we can directly check:

```text
seen.contains(prefixSum)
```

---

# ⚠️ Important Cases

### Case 1 — Prefix Sum Is Missing

```text
nums = [1,2,3]
```

Sequential prefix:

```text
1 → 2 → 3
```

Prefix sum:

```text
6
```

If `6` is not present:

```text
Answer = 6
```

---

### Case 2 — Prefix Sum Already Exists

Suppose:

```text
nums = [1,2,3,6,7]
```

Sequential prefix:

```text
1 → 2 → 3
```

Prefix sum:

```text
6
```

But `6` already exists.

Try:

```text
7
```

`7` also exists.

Try:

```text
8
```

If `8` is missing:

```text
Answer = 8
```

---

# 🎯 Key Takeaways

- ✅ Identify the longest sequential prefix.
- ✅ Calculate its sum.
- ✅ Store all elements in a `HashSet`.
- ✅ Start checking from the prefix sum.
- ✅ Increment until a missing value is found.
- ✅ `HashSet` provides average `O(1)` lookup.
- ✅ The solution requires only one main traversal plus the missing-value search.

---

# 📊 Complexity Preview

```text
Time Complexity  : O(n) average

Space Complexity : O(n)
```
# 💻 Java Solution

## Approach — HashSet + Sequential Prefix

```java
//Approach-1 (HashSet + Sequential Prefix)
//T.C : O(n) average
//S.C : O(n)

import java.util.HashSet;
import java.util.Set;

class Solution {

    public int missingInteger(
        int[] nums
    ) {

        Set<Integer> seen =
            new HashSet<>();

        for (
            int val : nums
        ) {

            seen.add(val);
        }

        int prefixSum =
            nums[0];

        for (
            int i = 1;
            i < nums.length;
            i++
        ) {

            if (
                nums[i] ==
                nums[i - 1] + 1
            ) {

                prefixSum +=
                    nums[i];

            } else {

                break;
            }
        }

        while (
            seen.contains(
                prefixSum
            )
        ) {

            prefixSum++;
        }

        return prefixSum;
    }
}
```

---

# 💻 C++ Solution

## Approach — HashSet + Sequential Prefix

```cpp
//Approach-1 (HashSet + Sequential Prefix)
//T.C : O(n) average
//S.C : O(n)

class Solution {
public:

    int missingInteger(
        vector<int>& nums
    ) {

        unordered_set<int> seen;

        for (
            int val : nums
        ) {

            seen.insert(val);
        }

        int prefixSum =
            nums[0];

        for (
            int i = 1;
            i < nums.size();
            i++
        ) {

            if (
                nums[i] ==
                nums[i - 1] + 1
            ) {

                prefixSum +=
                    nums[i];

            } else {

                break;
            }
        }

        while (
            seen.count(
                prefixSum
            )
        ) {

            prefixSum++;
        }

        return prefixSum;
    }
};
```

---

# 🔎 Detailed Explanation

The solution has two independent parts.

### Part 1 — Find the Sequential Prefix Sum

We begin with:

```text
prefixSum = nums[0]
```

Then compare every element with the previous element.

The condition:

```text
nums[i] == nums[i - 1] + 1
```

means that the sequence is still consecutive.

For example:

```text
nums = [3,4,5,9,10]
```

The sequential prefix is:

```text
3 → 4 → 5
```

Therefore:

```text
prefixSum = 3 + 4 + 5
          = 12
```

When we reach:

```text
9
```

the condition becomes:

```text
9 != 5 + 1
```

so we stop.

---

# Part 2 — Find the Smallest Missing Integer

After obtaining the prefix sum, we use the `HashSet` to check whether it already exists.

Suppose:

```text
prefixSum = 12
```

If:

```text
12 ∈ nums
```

we increment:

```text
13
```

If `13` also exists:

```text
14
```

We continue until:

```text
prefixSum ∉ nums
```

That value is the answer.

---

# 🧮 Example

```text
nums = [1,2,3,6,7,8]
```

Sequential prefix:

```text
1 → 2 → 3
```

Prefix sum:

```text
1 + 2 + 3 = 6
```

Now check the `HashSet`:

```text
6 → exists
7 → exists
8 → exists
9 → missing
```

Therefore:

```text
Answer = 9
```

---

# ⚡ Why This Approach Is Efficient

A straightforward approach could repeatedly scan the complete array to determine whether a candidate number exists.

That could result in unnecessary repeated work.

Instead, we store all values in:

```text
HashSet
```

Then existence checks are approximately:

```text
O(1)
```

on average.

So the solution efficiently combines:

```text
Sequential Traversal
+
HashSet Lookup
```

---

# 📊 Complexity Analysis

Let:

```text
n = nums.length
```

### Time Complexity

Building the set:

```text
O(n)
```

Finding the sequential prefix:

```text
O(n)
```

Finding the missing integer:

```text
O(k)
```

where `k` is the number of consecutive values checked after the prefix sum.

For the given constraints, this is handled efficiently, giving:

```text
Average Time Complexity: O(n)
```

---

### Space Complexity

The `HashSet` stores the elements of the array:

```text
O(n)
```

Therefore:

```text
Space Complexity: O(n)
```

---

# 🆚 Java vs C++

| Feature | Java | C++ |
|:---|:---|:---|
| Hash Structure | `HashSet<Integer>` | `unordered_set<int>` |
| Insert | `seen.add()` | `seen.insert()` |
| Lookup | `seen.contains()` | `seen.count()` |
| Prefix Check | `nums[i] == nums[i-1] + 1` | Same |
| Average Lookup | O(1) | O(1) |
| Extra Space | O(n) | O(n) |

---

# 🎯 Final Takeaways

```text
1. Store all numbers in a HashSet.
2. Find the longest sequential prefix.
3. Calculate its sum.
4. Start checking from that sum.
5. Skip values already present.
6. Return the first missing value.
```

The key idea is that the **sequential prefix determines where the search starts**, while the **HashSet makes finding the missing integer efficient**.

---

# ⏱️ Final Complexity

```text
Time Complexity  : O(n) average

Space Complexity : O(n)
```

### Techniques Used

```text
✓ HashSet
✓ Sequential Prefix
✓ Array Traversal
✓ Constant-Time Average Lookup
```