# Maximum Product of Two Elements in an Array

## 🔗 Problem Link

**LeetCode 1464:** https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/

---

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode:** https://leetcode.com/u/Shailu03/

**LeetCode (Alternative):** https://leetcode.com/Shailu03/

---

# 📌 Problem Statement

Given an integer array `nums`, choose **two distinct indices** `i` and `j` such that the value

```text
(nums[i] - 1) × (nums[j] - 1)
```

is maximized.

Return the maximum possible value.

---

# 💡 Intuition

The expression to maximize is

```text
(nums[i] - 1) × (nums[j] - 1)
```

Notice that subtracting `1` from every number does **not change their relative ordering**.

If one number is larger than another before subtraction,

```text
a > b
```

then after subtracting one,

```text
a - 1 > b - 1
```

also remains true.

Therefore, to maximize the product, we simply need the **two largest numbers** in the array.

After obtaining the two largest elements, subtract `1` from each and multiply them.

---

# 🔍 Key Observation

Suppose the array is sorted in ascending order.

```text
nums[0] ≤ nums[1] ≤ ... ≤ nums[n−2] ≤ nums[n−1]
```

The last two elements are the largest values in the array.

Thus, the maximum value will always be

```text
(nums[n−1] − 1)

×

(nums[n−2] − 1)
```

No other pair can produce a larger result because replacing either of these numbers with a smaller element decreases the product.

---

# 🚀 Approach — Sorting

The solution is straightforward.

1. Sort the array in ascending order.
2. Take the last two elements.
3. Subtract `1` from each.
4. Multiply them.
5. Return the result.

Sorting guarantees that the two largest elements are placed at the end of the array.

---

# 📝 Algorithm

### Step 1

Sort the array.

```text
Arrays.sort(nums)
```

---

### Step 2

Let

```text
n = nums.length
```

---

### Step 3

Select the two largest numbers.

```text
largest = nums[n−1]

secondLargest = nums[n−2]
```

---

### Step 4

Compute

```text
(largest − 1)

×

(secondLargest − 1)
```

---

### Step 5

Return the computed product.

---

# 🌳 Flowchart

```text
                  Start
                    │
                    ▼
             Read Input Array
                    │
                    ▼
               Sort the Array
                    │
                    ▼
      Pick the Two Largest Elements
                    │
                    ▼
      Subtract 1 from Both Elements
                    │
                    ▼
         Multiply the Two Values
                    │
                    ▼
             Return the Product
                    │
                    ▼
                   End
```

---

# 📖 Example 1

```text
Input

nums = [3,4,5,2]
```

Sorted

```text
[2,3,4,5]
```

Largest Numbers

```text
5

4
```

Answer

```text
(5−1)

×

(4−1)

=

4 × 3

=

12
```

---

# 📖 Example 2

```text
Input

nums = [1,5,4,5]
```

Sorted

```text
[1,4,5,5]
```

Largest Numbers

```text
5

5
```

Answer

```text
(5−1)

×

(5−1)

=

4 × 4

=

16
```

---

# 🔄 Dry Run

### Input

```text
nums = [10,2,5,2]
```

### Step 1

Sort

```text
[2,2,5,10]
```

---

### Step 2

Largest Elements

```text
10

5
```

---

### Step 3

Subtract One

```text
9

4
```

---

### Step 4

Multiply

```text
9 × 4

= 36
```

Return

```text
36
```

---

# 🧠 Why This Approach Works

Sorting arranges the elements in increasing order.

The two largest numbers always appear at the end of the sorted array.

Since subtracting `1` preserves the ordering of the numbers, choosing any smaller element instead of one of the two largest values would only decrease the final product.

Therefore,

```text
(nums[n−1] − 1)

×

(nums[n−2] − 1)
```

is guaranteed to be the maximum possible value.

This makes the solution simple, correct, and easy to implement.

# Java Solution

## Approach 1 — Sorting (Optimal for This Implementation)

```java
//Approach-1 (Sorting)
//T.C : O(n log n)
//S.C : O(1)   //Ignoring sorting space

import java.util.Arrays;

class Solution {

    public int maxProduct(
        int[] nums
    ) {

        Arrays.sort(
            nums
        );

        int n =
            nums.length;

        return
            (nums[n - 1] - 1) *
            (nums[n - 2] - 1);
    }
}
```

---

# C++ Solution

## Approach 1 — Sorting (Optimal for This Implementation)

```cpp
//Approach-1 (Sorting)
//T.C : O(n log n)
//S.C : O(1)   //Ignoring sorting space

class Solution {
public:

    int maxProduct(
        vector<int>& nums
    ) {

        sort(
            nums.begin(),
            nums.end()
        );

        int n =
            nums.size();

        return
            (nums[n - 1] - 1) *
            (nums[n - 2] - 1);
    }
};
```

---

# 📊 Memory Visualization

```text
Example

nums = [3,4,5,2]

               Sort
                 │
                 ▼

      ┌─────┬─────┬─────┬─────┐
      │  2  │  3  │  4  │  5  │
      └─────┴─────┴─────┴─────┘
                        ▲     ▲
                        │     │
                Second Largest
                              Largest

Subtract One

(5 - 1) = 4

(4 - 1) = 3

Answer

4 × 3 = 12
```

---

# ⚠️ Edge Cases

## Case 1 — Exactly Two Elements

```text
Input

[3,4]

Answer

(4 - 1) × (3 - 1)

= 3 × 2

= 6
```

---

## Case 2 — Duplicate Largest Values

```text
Input

[5,5,2]

Answer

(5 - 1) × (5 - 1)

= 4 × 4

= 16
```

---

## Case 3 — Already Sorted Array

```text
Input

[1,2,3,4,5]

Answer

(5 - 1) × (4 - 1)

= 4 × 3

= 12
```

---

## Case 4 — Unsorted Array

```text
Input

[8,1,6,2]

Sorted

[1,2,6,8]

Answer

(8 - 1) × (6 - 1)

= 7 × 5

= 35
```

---

# 📈 Complexity Analysis

| Operation                      |             Complexity              |
| :----------------------------- | :---------------------------------: |
| Sorting the array              |           **O(n log n)**            |
| Selecting two largest elements |              **O(1)**               |
| Computing the product          |              **O(1)**               |
| Overall Time Complexity        |           **O(n log n)**            |
| Extra Space                    | **O(1)** _(Ignoring sorting space)_ |

---

# 📊 Complexity Comparison

| Approach          | Idea                                  |      Time      |                Space                |
| :---------------- | :------------------------------------ | :------------: | :---------------------------------: |
| Brute Force       | Check every pair                      |   **O(n²)**    |              **O(1)**               |
| Sorting (Current) | Sort and use the largest two elements | **O(n log n)** | **O(1)** _(Ignoring sorting space)_ |
| Single Pass       | Track largest and second largest      |    **O(n)**    |              **O(1)**               |

---

# 🎯 Why This Approach Works

Sorting arranges all elements in ascending order.

After sorting,

```text
nums[0] ≤ nums[1] ≤ ... ≤ nums[n−2] ≤ nums[n−1]
```

the last two elements are guaranteed to be the largest values in the array.

Since subtracting `1` from every element preserves their ordering, the two largest elements before subtraction remain the two largest after subtraction.

Therefore,

```text
(nums[n−1] − 1)

×

(nums[n−2] − 1)
```

always produces the maximum possible value.

Instead of checking every possible pair, sorting allows us to identify the optimal pair immediately.

---

# ✅ Conclusion

- ✅ Sort the array in ascending order.
- ✅ Select the **largest** and **second largest** elements.
- ✅ Subtract `1` from both values.
- ✅ Multiply the resulting values.
- ✅ Return the computed product.
- ✅ This implementation is concise, easy to understand, and correctly computes the maximum product with an overall time complexity of **O(n log n)**.
