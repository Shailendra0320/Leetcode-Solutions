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

# 2574. Left and Right Sum Differences

# Intuition

For every index:

```text
answer[i] =
| leftSum - rightSum |
```

Where:

```text
leftSum
=
sum of elements before i
```

and

```text
rightSum
=
sum of elements after i
```

---

# Key Observation

Instead of calculating left and right sums separately for every index:

```text
O(n²)
```

We can maintain:

```text
total = total array sum

left = running left sum
```

Then:

```text
right = total - nums[i]
```

because we remove the current element from total before calculating.

---

# Approach

## Step 1

Calculate total array sum.

---

## Step 2

Maintain:

```text
left = 0
```

---

## Step 3

For every index:

Remove current element from total.

```text
total -= nums[i]
```

Now:

```text
total = right sum
```

Calculate:

```text
abs(left - total)
```

Store answer.

---

## Step 4

Update:

```text
left += nums[i]
```

---

# Flow Diagram

```text
Calculate Total Sum

        ↓

left = 0

        ↓

Remove Current Element

        ↓

Right Sum = Total

        ↓

| Left - Right |

        ↓

Store Answer

        ↓

Update Left Sum
```

---

# Example

Input

```text
nums = [10,4,8,3]
```

---

## Initial

```text
total = 25
left = 0
```

---

### i = 0

```text
total = 15

left = 0

|0 - 15| = 15
```

Answer:

```text
[15]
```

Update:

```text
left = 10
```

---

### i = 1

```text
total = 11

left = 10

|10 - 11| = 1
```

Answer:

```text
[15,1]
```

Update:

```text
left = 14
```

---

### i = 2

```text
total = 3

left = 14

|14 - 3| = 11
```

Answer:

```text
[15,1,11]
```

Update:

```text
left = 22
```

---

### i = 3

```text
total = 0

left = 22

|22 - 0| = 22
```

Answer:

```text
[15,1,11,22]
```

---

# Visualization

```text
Index = 2

10   4 | 8 | 3
↑  ↑       ↑

Left Sum  = 14

Right Sum = 3

Answer = |14 - 3|
       = 11
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

Ignoring output array.

---

# Java Solution

```java
class Solution {

    public int[] leftRightDifference(int[] nums) {

        int n = nums.length;

        int[] ans = new int[n];

        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int left = 0;

        for (int i = 0; i < n; i++) {

            total -= nums[i];

            int diff = left - total;

            ans[i] = diff < 0 ? -diff : diff;

            left += nums[i];
        }

        return ans;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    vector<int> leftRightDifference(vector<int>& nums) {

        int n = nums.size();

        vector<int> ans(n);

        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int left = 0;

        for (int i = 0; i < n; i++) {

            total -= nums[i];

            ans[i] = abs(left - total);

            left += nums[i];
        }

        return ans;
    }
};
```
