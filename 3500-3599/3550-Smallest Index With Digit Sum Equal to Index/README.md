# 3550. Smallest Index With Digit Sum Equal to Index

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

---

## 🧩 Problem Statement

Given an integer array `nums`, return the **smallest index** `i` such that the sum of the digits of `nums[i]` is equal to `i`.

Return `-1` if no such index exists.

### Example 1

```text
nums = [1,3,2]

i = 0 → digitSum(1) = 1 ≠ 0
i = 1 → digitSum(3) = 3 ≠ 1
i = 2 → digitSum(2) = 2 = 2

Answer = 2
```

### Example 2

```text
nums = [1,10,11]

i = 0 → digitSum(1) = 1 ≠ 0
i = 1 → digitSum(10) = 1 = 1

Answer = 1
```

Even though index `2` also works:

```text
digitSum(11) = 1 + 1 = 2
```

the required answer is `1` because we need the **smallest** valid index.

---

# 🎯 What Is the Question Really Asking?

For every index `i`, check:

```text
digitSum(nums[i]) == i
```

Because the question asks for the **smallest** valid index, we scan from left to right:

```text
0 → 1 → 2 → 3 → ...
```

The first valid index is automatically the answer.

So the problem reduces to:

```text
Scan
  ↓
Calculate digit sum
  ↓
Compare with current index
  ↓
First match → return index
  ↓
No match → return -1
```

There is no need for:

```text
Sorting
HashMap
Dynamic Programming
Recursion
Binary Search
```

---

# 💡 Core Insight

The problem contains two simple tasks:

```text
1. Calculate the digit sum of nums[i]
2. Check whether that sum equals i
```

The important optimization is the **early return**.

Since we scan from the smallest index upward, once:

```text
digitSum(nums[i]) == i
```

becomes true, no later index can be a better answer.

---

# 🔢 Digit Sum — Arithmetic Method

For a number such as:

```text
1234
```

we repeatedly extract the last digit.

```text
1234 % 10 = 4
1234 / 10 = 123

123 % 10 = 3
123 / 10 = 12

12 % 10 = 2
12 / 10 = 1

1 % 10 = 1
1 / 10 = 0
```

Therefore:

```text
digitSum(1234)
= 4 + 3 + 2 + 1
= 10
```

General process:

```text
sum = 0

while num > 0:
    sum += num % 10
    num /= 10
```

---

# 🚀 Approach 1 — Arithmetic Digit Sum

## 💡 Idea

For every index `i`:

1. Copy `nums[i]` to a temporary variable.
2. Extract digits using `% 10`.
3. Remove digits using `/ 10`.
4. Compute the digit sum.
5. Compare the digit sum with `i`.
6. Return immediately if they are equal.

---

## 🏗️ Architecture / Flow Diagram

```text
                    ┌──────────────────────┐
                    │       nums[]         │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      Start           │
                    │       i = 0          │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │   num = nums[i]      │
                    │      sum = 0         │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │       num > 0 ?      │
                    └───────┬───────┬──────┘
                            │ Yes   │ No
                            ▼       │
                 ┌────────────────┐ │
                 │ sum += num % 10│ │
                 └───────┬────────┘ │
                         │          │
                         ▼          │
                 ┌────────────────┐ │
                 │   num /= 10    │ │
                 └───────┬────────┘ │
                         │          │
                         └──────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      sum == i ?      │
                    └───────┬───────┬──────┘
                            │ Yes   │ No
                            ▼       ▼
                    ┌────────────┐ ┌──────────────┐
                    │  return i  │ │     i++      │
                    └────────────┘ └──────┬───────┘
                                         │
                                         ▼
                              ┌────────────────────┐
                              │ i < nums.length ?  │
                              └───────┬───────┬────┘
                                      │ Yes   │ No
                                      │       ▼
                                      │   ┌─────────┐
                                      │   │ return  │
                                      │   │   -1    │
                                      │   └─────────┘
                                      │
                                      └──────→ Repeat
```

---

## 🔄 Approach 1 Data Flow

```text
Array Element
     │
     ▼
nums[i]
     │
     ▼
Temporary Integer
     │
     ├──── % 10 ────→ Extract Last Digit
     │                     │
     │                     ▼
     │                  Add to Sum
     │
     └──── / 10 ────→ Remove Last Digit
                           │
                           ▼
                       Repeat
                           │
                           ▼
                       Digit Sum
                           │
                           ▼
                    Compare with i
                           │
                    ┌──────┴──────┐
                    ▼             ▼
                  Equal        Not Equal
                    │             │
                    ▼             ▼
                return i          i++
```

---

## 🧪 Approach 1 Example

```text
nums = [1,10,11]
```

### Index 0

```text
num = 1

1 % 10 = 1
sum = 1

1 == 0 ?  No
```

### Index 1

```text
num = 10

10 % 10 = 0
sum = 0
10 / 10 = 1

1 % 10 = 1
sum = 1
1 / 10 = 0

sum = 1

1 == 1 ? Yes
```

Return:

```text
1
```

---

## ✅ Why Approach 1 Works

The loop checks:

```text
i = 0
i = 1
i = 2
...
```

For each index, it computes the exact digit sum and checks the required condition.

Therefore:

- every earlier index has already been checked;
- the returned index satisfies the condition;
- no smaller valid index exists.

So the returned index is the required smallest valid index.

---

# ⚡ Approach 2 — String Digit Sum

## 💡 Idea

Instead of extracting digits mathematically, convert the number to a string.

For:

```text
1234
```

we get:

```text
"1234"
```

Then process each character:

```text
'1' → 1
'2' → 2
'3' → 3
'4' → 4
```

Therefore:

```text
digitSum = 1 + 2 + 3 + 4 = 10
```

Then compare:

```text
digitSum == i
```

---

## 🏗️ Architecture / Flow Diagram

```text
                    ┌──────────────────────┐
                    │       nums[]         │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      Start           │
                    │       i = 0          │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │ Convert nums[i]       │
                    │     to String         │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │   sum = 0             │
                    │   Read characters     │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │  More characters?    │
                    └───────┬───────┬──────┘
                            │ Yes   │ No
                            ▼       │
                 ┌────────────────┐ │
                 │ ch = current   │ │
                 │ character      │ │
                 └───────┬────────┘ │
                         │          │
                         ▼          │
                 ┌────────────────┐ │
                 │ sum += ch - '0'│ │
                 └───────┬────────┘ │
                         │          │
                         └──────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      sum == i ?      │
                    └───────┬───────┬──────┘
                            │ Yes   │ No
                            ▼       ▼
                    ┌────────────┐ ┌──────────────┐
                    │  return i  │ │     i++      │
                    └────────────┘ └──────┬───────┘
                                         │
                                         ▼
                              ┌────────────────────┐
                              │ i < nums.length ?  │
                              └───────┬───────┬────┘
                                      │ Yes   │ No
                                      │       ▼
                                      │   ┌─────────┐
                                      │   │ return  │
                                      │   │   -1    │
                                      │   └─────────┘
                                      │
                                      └──────→ Repeat
```

---

## 🔄 Approach 2 Data Flow

```text
Array Element
     │
     ▼
nums[i]
     │
     ▼
Convert to String
     │
     ▼
Characters
     │
     ├── '1' ──→ 1
     ├── '2' ──→ 2
     ├── '3' ──→ 3
     └── '4' ──→ 4
                 │
                 ▼
              Add Digits
                 │
                 ▼
              Digit Sum
                 │
                 ▼
          Compare with Index
                 │
          ┌──────┴──────┐
          ▼             ▼
        Equal        Not Equal
          │             │
          ▼             ▼
      return i           i++
```

---

## 🧪 Approach 2 Example

```text
nums[i] = 1234
```

Convert:

```text
1234 → "1234"
```

Process:

```text
'1' → 1
'2' → 2
'3' → 3
'4' → 4
```

Therefore:

```text
sum = 1 + 2 + 3 + 4
    = 10
```

Then:

```text
10 == i ?
```

If the current index is `10`, the condition is true.

---

## ✅ Why Approach 2 Works

Converting an integer into its decimal representation preserves exactly the digits whose sum we need.

Each character:

```text
ch - '0'
```

converts the digit character back into its integer value.

So the computed sum is exactly the digit sum of `nums[i]`.

The same left-to-right scan guarantees that the first match is the smallest valid index.

---

# 🔄 Both Approaches — Architecture Comparison

```text
                         nums[i]
                            │
                ┌───────────┴───────────┐
                │                       │
                ▼                       ▼
       ┌─────────────────┐     ┌─────────────────┐
       │    Approach 1   │     │    Approach 2   │
       │    Arithmetic   │     │      String     │
       └────────┬────────┘     └────────┬────────┘
                │                       │
                ▼                       ▼
          num % 10                String.valueOf()
                │                       │
                ▼                       ▼
        Extract digit            Iterate characters
                │                       │
                ▼                       ▼
            num / 10                 ch - '0'
                │                       │
                ▼                       ▼
          Digit Sum                 Digit Sum
                │                       │
                └───────────┬───────────┘
                            ▼
                     Compare with i
                            │
                       sum == i ?
                       /        \
                     Yes        No
                      │           │
                      ▼           ▼
                  return i       i++
```

---

# 🧠 Why Both Approaches Return the Same Answer

The difference is only **how the digit sum is calculated**.

### Approach 1

```text
Number
  ↓
% 10 / 10
  ↓
Digits
  ↓
Sum
```

### Approach 2

```text
Number
  ↓
String
  ↓
Characters
  ↓
Digits
  ↓
Sum
```

Everything after that is identical:

```text
Digit Sum
    ↓
Compare with Index
    ↓
First Match
    ↓
Answer
```

---

# 🧪 Complete Dry Run

Consider:

```text
nums = [1,10,11]
```

| Index | Value | Digit Calculation | Digit Sum | `sum == index` |
| ----: | ----: | ----------------- | --------: | :------------: |
|   `0` |   `1` | `1`               |       `1` |       ❌       |
|   `1` |  `10` | `1 + 0`           |       `1` |       ✅       |
|   `2` |  `11` | `1 + 1`           |       `2` |       ✅       |

The first valid index is:

```text
1
```

Therefore:

```text
Answer = 1
```

---

# ⚠️ Edge Cases

## Case 1 — Index `0`

```text
nums = [0]
```

```text
digitSum(0) = 0
```

Therefore:

```text
answer = 0
```

---

## Case 2 — Multiple Valid Indices

```text
nums = [1,10,11]
```

Valid:

```text
index 1 → digitSum(10) = 1
index 2 → digitSum(11) = 2
```

Return:

```text
1
```

because it is the smallest.

---

## Case 3 — No Valid Index

```text
nums = [1,2,3]
```

No index satisfies the condition.

Return:

```text
-1
```

---

## Case 4 — Number Contains Zero

```text
nums[i] = 10
```

Digit sum:

```text
1 + 0 = 1
```

The zero is correctly included.

---

# ❌ Common Mistakes

### 1. Comparing the whole number with the index

Wrong:

```text
nums[i] == i
```

Correct:

```text
digitSum(nums[i]) == i
```

---

### 2. Returning the last valid index

The problem asks for the **smallest** valid index.

Always scan from left to right and return immediately.

---

### 3. Forgetting index `0`

The answer can legitimately be:

```text
0
```

---

### 4. Overengineering the problem

There is no need for:

```text
Sorting
HashMap
DP
Binary Search
```

A simple linear scan is enough.

---

# 🎯 Pattern Recognition

This is a classic combination of:

```text
Array Traversal
       +
Digit Manipulation
       +
Early Return
```

Whenever a problem asks for:

```text
smallest/first index
+
condition involving digits
```

the natural strategy is:

```text
Left → Right
```

and return immediately when the condition is satisfied.

---

# 🗣️ Interview Explanation

A clean interview explanation:

> "I iterate through the array from left to right because I need the smallest valid index. For each element, I calculate its digit sum. In the first approach, I use modulo 10 and integer division to extract digits. In the second approach, I convert the number to a string and sum the digit characters. If the digit sum equals the current index, I immediately return that index. If no index matches, I return -1."

---

# 💻 Java — Both Approaches in One File

```java
//Approach-1 (Arithmetic Digit Sum)
//T.C : O(n * D)
//S.C : O(1)

class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            if (sum == i) {
                return i;
            }
        }

        return -1;
    }
}


//Approach-2 (String Digit Sum)
//T.C : O(n * D)
//S.C : O(D)

class Solution2 {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            String s = String.valueOf(nums[i]);
            int sum = 0;

            for (char ch : s.toCharArray()) {
                sum += ch - '0';
            }

            if (sum == i) {
                return i;
            }
        }

        return -1;
    }
}
```

---

# 💻 C++ — Both Approaches in One File

```cpp
#include <bits/stdc++.h>
using namespace std;

//Approach-1 (Arithmetic Digit Sum)
//T.C : O(n * D)
//S.C : O(1)

class Solution {
public:
    int smallestIndex(vector<int>& nums) {
        for (int i = 0; i < nums.size(); i++) {
            int num = nums[i];
            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            if (sum == i) {
                return i;
            }
        }

        return -1;
    }
};


//Approach-2 (String Digit Sum)
//T.C : O(n * D)
//S.C : O(D)

class Solution2 {
public:
    int smallestIndex(vector<int>& nums) {
        for (int i = 0; i < nums.size(); i++) {
            string s = to_string(nums[i]);
            int sum = 0;

            for (char ch : s) {
                sum += ch - '0';
            }

            if (sum == i) {
                return i;
            }
        }

        return -1;
    }
};
```

---

# 📊 Complexity Analysis

Let:

```text
n = nums.length
D = maximum number of digits in nums[i]
```

## Approach 1 — Arithmetic Digit Sum

```text
Time Complexity  : O(n × D)
Space Complexity : O(1)
```

## Approach 2 — String Digit Sum

```text
Time Complexity  : O(n × D)
Space Complexity : O(D)
```

Since the problem restricts the value of each number to a small number of digits, the practical runtime is effectively:

```text
O(n)
```

---

# 🆚 Approach Comparison

| Feature            | Approach 1: Arithmetic | Approach 2: String  |
| ------------------ | ---------------------- | ------------------- |
| Digit extraction   | `% 10`, `/ 10`         | Character traversal |
| Time               | `O(n × D)`             | `O(n × D)`          |
| Extra Space        | `O(1)`                 | `O(D)`              |
| String conversion  | ❌                     | ✅                  |
| Memory efficient   | ✅                     | Good                |
| Easy to understand | ✅                     | ✅                  |
| Best choice        | ✅                     | Alternative         |

---

# 🧾 Quick Revision

```text
For every index i:

1. Calculate digitSum(nums[i])
2. Check:
      digitSum == i
3. If true:
      return i
4. If loop finishes:
      return -1
```

### Core Condition

```text
digitSum(nums[i]) == i
```

### Core Strategy

```text
Scan from left → right
        ↓
Find first valid index
        ↓
Return immediately
```

---

# ⭐ One-Line Insight

> **Scan from left to right, calculate the digit sum of each value, and return the first index where `digitSum(nums[i]) == i`.**

---

# 📚 Final Takeaway

The problem is intentionally simple.

The optimal idea is:

```text
Linear Scan
     +
Digit Sum
     +
Early Return
```

The entire solution can be summarized as:

```text
nums[i]
   │
   ▼
Digit Sum
   │
   ▼
Compare with i
   │
   ├── Equal ──→ return i
   │
   └── Not Equal
            │
            ▼
          i++
            │
            ▼
        Continue
            │
            ▼
           -1
```

The main lesson is not a complex data structure. It is recognizing that the requirement for the **smallest index** naturally suggests a left-to-right scan with an immediate return.
