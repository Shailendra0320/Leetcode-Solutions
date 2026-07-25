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

# 3536. Maximum Product of Two Digits

## Tags

```text
Math
Greedy
Simulation
Number Theory
Java
C++
```

---

# Intuition

We need to find

```text
The Largest Product
```

that can be obtained using

```text
Any Two Digits
```

of the given number.

Instead of storing all digits and sorting them,

we only need to keep track of

```text
The Largest

and

The Second Largest Digit.
```

---

# Key Observation

While traversing the digits,

if the current digit is larger than the current maximum,

it becomes the new maximum,

and the previous maximum becomes the second maximum.

Otherwise,

if it is only larger than the second maximum,

update the second maximum.

At the end,

the answer is simply

```text
Largest Digit

×

Second Largest Digit.
```

---

# Approaches

1. Greedy + Digit Traversal (Optimal)

---

# Approach 1 — Greedy + Digit Traversal

## Idea

Traverse every digit of the number using

```text
Modulo (%)

and

Division (/).
```

Maintain two variables

```text
max1

max2
```

representing the largest and second largest digits encountered so far.

Finally,

multiply them.

---

# Algorithm

### Step 1

Initialize

```text
max1 = 0

max2 = 0
```

---

### Step 2

Traverse all digits of the number.

---

### Step 3

Extract the last digit using

```text
n % 10
```

---

### Step 4

Update

```text
max1

and

max2
```

accordingly.

---

### Step 5

Remove the last digit using

```text
n /= 10
```

---

### Step 6

Return

```text
max1 × max2.
```

---

# Flowchart

```text
             Start

               │

               ▼

      Initialize max1, max2

               │

               ▼

        While n > 0

               │

               ▼

      Extract Last Digit

               │

               ▼

 Update Largest Digits

               │

               ▼

 Remove Last Digit

               │

               ▼

 Repeat Until End

               │

               ▼

 Return max1 × max2
```

---

# Example

Input

```text
n = 2736
```

Digits

```text
2

7

3

6
```

Largest Digits

```text
7

6
```

Answer

```text
7 × 6 = 42
```

---

# Dry Run

Input

```text
n = 58321
```

Processing

```text
Digit = 1

max1 = 1

max2 = 0

↓

Digit = 2

max1 = 2

max2 = 1

↓

Digit = 3

max1 = 3

max2 = 2

↓

Digit = 8

max1 = 8

max2 = 3

↓

Digit = 5

max1 = 8

max2 = 5
```

Answer

```text
8 × 5 = 40
```

---

# Memory Visualization

```text
Input Number

      │

      ▼

Extract Digit

      │

      ▼

Update max1 & max2

      │

      ▼

Process Remaining Digits

      │

      ▼

Return Product
```

---

# Why Greedy Works?

At every step,

we only care about the

```text
Two Largest Digits
```

seen so far.

Any smaller digit can never produce a larger product than the current top two.

Thus,

maintaining only two variables guarantees the optimal answer.

---

# Complexity Analysis

## Approach 1 — Greedy + Digit Traversal

### Time Complexity

```text
O(d)
```

where

```text
d
```

is the number of digits in the input.

---

### Space Complexity

```text
O(1)
```

Only two integer variables are maintained.

---

# Java Solution

## Approach 1 — Greedy + Digit Traversal (Optimal)

```java
//Approach-1 (Greedy + Digit Traversal)
//T.C : O(d)
//S.C : O(1)

class Solution {

    public int maxProduct(
        int n
    ) {

        int max1 = 0;

        int max2 = 0;

        while (
            n > 0
        ) {

            int digit =
                n % 10;

            if (
                digit > max1
            ) {

                max2 =
                    max1;

                max1 =
                    digit;

            } else if (
                digit > max2
            ) {

                max2 =
                    digit;
            }

            n /= 10;
        }

        return
            max1 * max2;
    }
}
```

---

# C++ Solution

## Approach 1 — Greedy + Digit Traversal (Optimal)

```cpp
//Approach-1 (Greedy + Digit Traversal)
//T.C : O(d)
//S.C : O(1)

class Solution {
public:

    int maxProduct(
        int n
    ) {

        int max1 = 0;

        int max2 = 0;

        while (
            n > 0
        ) {

            int digit =
                n % 10;

            if (
                digit > max1
            ) {

                max2 =
                    max1;

                max1 =
                    digit;

            } else if (
                digit > max2
            ) {

                max2 =
                    digit;
            }

            n /= 10;
        }

        return
            max1 * max2;
    }
};
```

---

# Complexity Comparison

| Approach                 | Algorithm |   Time   |  Space   |
| :----------------------- | :-------- | :------: | :------: |
| Greedy + Digit Traversal | Greedy    | **O(d)** | **O(1)** |

---

# Final Complexity

```text
Approach 1 (Greedy + Digit Traversal)

Time Complexity  : O(d)

Space Complexity : O(1)
```

---

# Conclusion

- ✅ Traverse the digits of the number exactly once.
- ✅ Maintain the **largest** and **second largest** digits encountered.
- ✅ Update the two maximum values greedily while processing each digit.
- ✅ Multiply the two largest digits to obtain the maximum possible product.
- ✅ The algorithm runs in **O(d)** time with **O(1)** extra space, where **d** is the number of digits.
