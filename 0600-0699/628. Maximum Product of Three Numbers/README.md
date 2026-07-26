# Maximum Product of Three Numbers

## 🔗 Problem Link

**LeetCode 628:** https://leetcode.com/problems/maximum-product-of-three-numbers/

---

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode:** https://leetcode.com/u/Shailu03/

**LeetCode (Alternative):** https://leetcode.com/Shailu03/

---

# 📌 Problem Statement

Given an integer array `nums`, return the **maximum product** that can be obtained by multiplying **any three distinct elements** of the array.

The array may contain:

- Positive integers
- Negative integers
- Zero

Our task is to determine which three numbers produce the **largest possible product**.

---

# 💡 Intuition

At first glance, the problem appears straightforward.

Since we need the maximum product, our first instinct is to simply choose the **three largest numbers** in the array.

For example,

```text
1  3  5  8

Largest three

3 × 5 × 8 = 120
```

This strategy works perfectly when all numbers are positive.

However, the presence of **negative numbers** changes everything.

Recall the basic property of multiplication:

```text
Positive × Positive = Positive

Negative × Negative = Positive

Positive × Negative = Negative
```

This means two very small (highly negative) numbers can produce a very large positive value.

For example,

```text
-100 × -50 = 5000
```

Now multiply this result with a large positive number,

```text
5000 × 10 = 50000
```

which is much larger than

```text
10 × 9 × 8 = 720
```

Therefore, choosing only the three largest numbers is **not always optimal**.

Instead, we must also consider the possibility of selecting the **two smallest (most negative) numbers** together with the **largest positive number**.

Hence, the answer can come from only **two possible combinations**.

---

# 🔍 Key Observation

After sorting the array in ascending order,

```text
nums[0] ≤ nums[1] ≤ nums[2] ≤ ... ≤ nums[n−1]
```

the important elements automatically move to the ends of the array.

The largest values are located at the end,

```text
nums[n−3]
nums[n−2]
nums[n−1]
```

while the smallest (possibly most negative) values are located at the beginning,

```text
nums[0]
nums[1]
```

Therefore, only two candidate products are capable of producing the maximum answer.

---

## Case 1 — Three Largest Numbers

Choose the last three elements.

```text
nums[n−3] × nums[n−2] × nums[n−1]
```

Example

```text
Sorted

[1,2,3,4,5]

Answer

3 × 4 × 5 = 60
```

This case is optimal when the array mainly consists of positive numbers.

---

## Case 2 — Two Smallest + Largest

Choose the first two elements and the last element.

```text
nums[0] × nums[1] × nums[n−1]
```

Example

```text
Sorted

[-100,-50,1,2,10]

Answer

(-100) × (-50) × 10

= 50000
```

Although the first two numbers are negative, their multiplication becomes positive and often produces a much larger result.

---

# ❓ Why Are There Only Two Cases?

Suppose we try any other combination.

Consider the sorted array

```text
[-10,-8,-2,1,5,9]
```

Some random triplets are

```text
-8 × 1 × 9

-2 × 5 × 9

-10 × 1 × 5

-8 × 5 × 9
```

None of these can outperform

```text
9 × 5 × 1
```

or

```text
(-10) × (-8) × 9
```

because:

- Replacing one of the largest positive numbers with a smaller positive number decreases the product.
- Replacing one of the smallest negative numbers with a less negative value also decreases the product obtained after multiplication.
- Any mixed combination eventually becomes smaller than one of these two extreme cases.

Thus, every optimal solution must belong to one of these two possibilities.

---

# 🚀 Approach — Sorting + Compare Two Products

Since sorting places all smallest and largest elements at fixed positions,

we simply:

1. Sort the array.
2. Compute the product of the three largest numbers.
3. Compute the product of the two smallest numbers and the largest number.
4. Return whichever product is larger.

Instead of checking every possible triplet, we only evaluate **two carefully selected candidates**.

This reduces the problem from considering

```text
O(n³)
```

possible triplets to just

```text
2
```

product calculations after sorting.

---

# 📝 Algorithm

### Step 1

Sort the array in ascending order.

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

Compute the product of the three largest elements.

```text
case1

=

nums[n−1]
×

nums[n−2]
×

nums[n−3]
```

---

### Step 4

Compute the product of the two smallest elements and the largest element.

```text
case2

=

nums[0]
×

nums[1]
×

nums[n−1]
```

---

### Step 5

Return

```text
max(case1, case2)
```

because the larger of these two values is guaranteed to be the maximum possible product.

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
         ┌─────────────────────────────────┐
         │ Compute Product of Largest Three│
         └─────────────────────────────────┘
                          │
                          ▼
      ┌──────────────────────────────────────────┐
      │ Compute Product of Two Smallest + Largest│
      └──────────────────────────────────────────┘
                          │
                          ▼
              Compare Both Products
                          │
                          ▼
             Return Maximum Product
                          │
                          ▼
                         End
```

---

# 📖 Example 1

```text
Input

nums = [1,2,3]
```

Sorted

```text
[1,2,3]
```

Case 1

```text
1 × 2 × 3 = 6
```

Case 2

```text
1 × 2 × 3 = 6
```

Answer

```text
6
```

---

# 📖 Example 2

```text
Input

nums = [-10,-10,5,2]
```

Sorted

```text
[-10,-10,2,5]
```

Case 1

```text
5 × 2 × (-10)

= -100
```

Case 2

```text
(-10) × (-10) × 5

= 500
```

Maximum

```text
500
```

Return

```text
500
```

---

# 🔄 Dry Run

### Input

```text
nums = [-4,-3,-2,-1,60]
```

### Step 1

Sort

```text
[-4,-3,-2,-1,60]
```

### Step 2

Compute Case 1

```text
60 × (-1) × (-2)

= 120
```

### Step 3

Compute Case 2

```text
(-4) × (-3) × 60

= 720
```

### Step 4

Compare

```text
120

vs

720
```

Maximum

```text
720
```

Return

```text
720
```

---

# 🧠 Why This Approach Works

Sorting arranges all numbers from the smallest to the largest.

The largest possible positive product can only be achieved in one of two ways:

- By selecting the **three largest positive numbers**, or
- By selecting the **two most negative numbers** together with the **largest positive number**.

Every other combination replaces one of these extreme values with a less favorable element, which can only decrease (or at best equal) the resulting product.

Therefore, comparing only these two candidate products is sufficient to guarantee the correct answer.

This makes the algorithm both **simple** and **mathematically optimal**.

# Java Solution

## Approach 1 — Sorting + Compare Two Candidate Products (Optimal)

```java
//Approach-1 (Sorting + Compare Two Candidate Products)
//T.C : O(n log n)
//S.C : O(1)   //Ignoring sorting space

import java.util.Arrays;

class Solution {

    public int maximumProduct(
        int[] nums
    ) {

        Arrays.sort(
            nums
        );

        int n =
            nums.length;

        int case1 =
            nums[n - 1] *
            nums[n - 2] *
            nums[n - 3];

        int case2 =
            nums[0] *
            nums[1] *
            nums[n - 1];

        return
            Math.max(
                case1,
                case2
            );
    }
}
```

---

# C++ Solution

## Approach 1 — Sorting + Compare Two Candidate Products (Optimal)

```cpp
//Approach-1 (Sorting + Compare Two Candidate Products)
//T.C : O(n log n)
//S.C : O(1)   //Ignoring sorting space

class Solution {
public:

    int maximumProduct(
        vector<int>& nums
    ) {

        sort(
            nums.begin(),
            nums.end()
        );

        int n =
            nums.size();

        int case1 =
            nums[n - 1] *
            nums[n - 2] *
            nums[n - 3];

        int case2 =
            nums[0] *
            nums[1] *
            nums[n - 1];

        return
            max(
                case1,
                case2
            );
    }
};
```

---

# 📊 Memory Visualization

```text
Example

nums = [-10,-10,2,5]

                 Sort
                   │
                   ▼

      ┌──────┬──────┬──────┬──────┐
      │ -10  │ -10  │  2   │  5   │
      └──────┴──────┴──────┴──────┘
         ▲      ▲              ▲
         │      │              │
         │      │              │
      Smallest Smallest    Largest


Case 1

Largest Three

5 × 2 × (-10)

= -100


Case 2

Two Smallest + Largest

(-10) × (-10) × 5

= 500


Answer

max(-100, 500)

= 500
```

---

# ⚠️ Edge Cases

## Case 1 — All Positive Numbers

```text
Input

[1,2,3,4]

Largest Three

4 × 3 × 2 = 24

Answer = 24
```

---

## Case 2 — Large Negative Values

```text
Input

[-10,-10,1,2,5]

Largest Three

5 × 2 × 1 = 10

Two Smallest + Largest

(-10) × (-10) × 5 = 500

Answer = 500
```

---

## Case 3 — Contains Zero

```text
Input

[-5,-4,0,2]

Largest Three

2 × 0 × (-4) = 0

Two Smallest + Largest

(-5) × (-4) × 2 = 40

Answer = 40
```

---

## Case 4 — Exactly Three Elements

```text
Input

[-2,3,4]

Only One Possible Triplet

(-2) × 3 × 4

= -24
```

---

## Case 5 — All Negative Numbers

```text
Input

[-5,-4,-3,-2]

Largest Three

(-2) × (-3) × (-4)

= -24

Two Smallest + Largest

(-5) × (-4) × (-2)

= -40

Answer

-24
```

---

# 📈 Complexity Analysis

| Operation               |             Complexity              |
| :---------------------- | :---------------------------------: |
| Sorting the array       |           **O(n log n)**            |
| Computing two products  |              **O(1)**               |
| Comparing products      |              **O(1)**               |
| Overall Time Complexity |           **O(n log n)**            |
| Extra Space             | **O(1)** _(Ignoring sorting space)_ |

---

# 📊 Complexity Comparison

| Approach          | Idea                                |      Time      |                Space                |
| :---------------- | :---------------------------------- | :------------: | :---------------------------------: |
| Brute Force       | Check every triplet                 |   **O(n³)**    |              **O(1)**               |
| Sorting (Optimal) | Compare only two candidate products | **O(n log n)** | **O(1)** _(Ignoring sorting space)_ |

---

# 🎯 Why Is This Optimal?

Instead of checking all possible triplets, which would require

```text
nC3

=

n(n−1)(n−2)
/──────────
     6
```

possible combinations,

the algorithm leverages the properties of a **sorted array**.

After sorting, only the extreme elements can contribute to the maximum product:

- The **three largest numbers**, or
- The **two smallest (most negative) numbers** together with the **largest positive number**.

Every other triplet contains at least one element that is less favorable than these extremes, so it can never produce a larger product.

Thus, evaluating only **two products** is sufficient to guarantee the correct answer.

---

# ✅ Conclusion

- ✅ Sorting places all important candidates at the ends of the array.
- ✅ Only **two possible products** need to be evaluated.
- ✅ The first considers the **three largest values**.
- ✅ The second considers the **two most negative values** and the **largest positive value**.
- ✅ Returning the larger of these two products always yields the maximum possible product.
- ✅ This approach is simple, mathematically sound, easy to implement, and significantly faster than checking every possible triplet.
