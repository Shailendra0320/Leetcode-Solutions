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

# 3689. Maximum Total Value of Array After K Operations

# Intuition

The total value gained in one operation depends only on:

```text
Maximum Element - Minimum Element
```

Therefore, to maximize the total value:

```text
Always use the largest element
and
the smallest element.
```

Since every operation contributes the same maximum difference:

```text
(maximum - minimum)
```

The final answer becomes:

```text
(maximum - minimum) × k
```

---

# Key Observation

We do NOT need:

```text
Sorting
Priority Queue
Dynamic Programming
Simulation
```

We only need:

```text
Smallest Element
Largest Element
```

Because:

```text
Largest Difference

=

Largest Element - Smallest Element
```

---

# Mathematical Insight

Suppose:

```text
nums = [2, 8, 4, 10]
```

Then:

```text
minimum = 2
maximum = 10
```

Maximum value obtainable in one operation:

```text
10 - 2 = 8
```

For:

```text
k = 5
```

Answer:

```text
8 × 5 = 40
```

---

# Approach

## Step 1

Find:

```text
minimum element
```

and

```text
maximum element
```

from the array.

---

## Step 2

Compute:

```text
difference = maximum - minimum
```

---

## Step 3

Multiply by:

```text
k
```

---

## Step 4

Return answer.

---

# Flowchart

```text
Start

   │
   ▼

Traverse Array

   │
   ▼

Find Minimum Element

   │
   ▼

Find Maximum Element

   │
   ▼

Compute

(maximum - minimum)

   │
   ▼

Multiply by k

   │
   ▼

Return Answer

   │
   ▼

 End
```

---

# Visualization

```text
Input

nums = [2,8,4,10]
k = 5


          Array

     [2,8,4,10]

          │
          ▼

   Minimum = 2

          │
          ▼

   Maximum = 10

          │
          ▼

 Difference = 8

          │
          ▼

 8 × 5 = 40

          │
          ▼

      Answer
```

---

# Detailed Dry Run

### Input

```text
nums = [2,8,4,10]
k = 5
```

---

### Initial

```text
minimum = 2
maximum = 2
```

---

### Process 8

```text
minimum = 2
maximum = 8
```

---

### Process 4

```text
minimum = 2
maximum = 8
```

---

### Process 10

```text
minimum = 2
maximum = 10
```

---

### Compute Difference

```text
10 - 2 = 8
```

---

### Multiply by k

```text
8 × 5 = 40
```

---

### Final Answer

```text
40
```

---

# Memory Diagram

```text
nums

[2,8,4,10]

    │
    ▼

 ┌────────────┐
 │ minimum=2 │
 └────────────┘

 ┌────────────┐
 │ maximum=10│
 └────────────┘

    │
    ▼

 difference = 8

    │
    ▼

 answer = 8 × k
```

---

# Why This Works

The maximum value obtainable in a single operation is always:

```text
largest element - smallest element
```

No other pair can generate a larger difference.

Therefore:

```text
Best Value Per Operation

=
(maximum - minimum)
```

Applying this for:

```text
k operations
```

gives:

```text
(maximum - minimum) × k
```

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

Single traversal.

---

## Space Complexity

```text
O(1)
```

Only variables are used.

---

# Java Solution

```java
class Solution {

    public long maxTotalValue(int[] nums, int k) {

        int minimumValue = nums[0];
        int maximumValue = nums[0];

        for (int value : nums) {

            if (value < minimumValue) {
                minimumValue = value;
            }

            if (value > maximumValue) {
                maximumValue = value;
            }
        }

        return (long)(maximumValue - minimumValue) * k;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    long long maxTotalValue(vector<int>& nums, int k) {

        int minimumValue = nums[0];
        int maximumValue = nums[0];

        for (int value : nums) {

            minimumValue = min(minimumValue, value);
            maximumValue = max(maximumValue, value);
        }

        return 1LL * (maximumValue - minimumValue) * k;
    }
};
```
