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

# 3345. Smallest Divisible Digit Product I

## Tags

```text
Brute Force
Math
Simulation
Number Theory
Digit Manipulation
Java
C++
```

---

# Intuition

We need to find the **smallest integer** greater than or equal to `n` whose **product of digits** is divisible by `t`.

Instead of trying to derive the answer mathematically, we can simply examine numbers one by one starting from `n`.

For each candidate number,

- Compute the product of its digits.
- Check whether the product is divisible by `t`.

The first number satisfying the condition is guaranteed to be the answer.

---

# Key Observation

For every candidate number,

```text
Digit Product

=

Product of All Digits
```

If

```text
Digit Product % t == 0
```

then that number satisfies the requirement.

Since we check numbers in increasing order, the **first valid number** is automatically the smallest possible answer.

---

# Approaches

1. Brute Force + Digit Simulation (Optimal for Given Constraints)

---

# Approach 1 — Brute Force + Digit Simulation

## Idea

Start from the given number `n`.

For each number,

- Extract every digit.
- Multiply all digits together.
- Check divisibility by `t`.

If divisible,

return the current number immediately.

Otherwise,

continue with the next integer.

---

# Algorithm

### Step 1

Start iterating from

```text
num = n
```

---

### Step 2

Initialize

```text
product = 1
```

---

### Step 3

Extract every digit using

```text
digit = num % 10
```

Multiply it into

```text
product
```

and remove the last digit using

```text
num /= 10
```

---

### Step 4

After processing all digits,

check

```text
product % t == 0
```

---

### Step 5

If true,

return the current number.

Otherwise,

continue searching.

---

### Step 6

If no valid number is found within the search range,

return

```text
-1
```

---

# Flowchart

```text
                 Start
                   │
                   ▼
            num = n
                   │
                   ▼
       Compute Digit Product
                   │
                   ▼
      Is Product Divisible by t?
            ┌───────────────┐
          Yes              No
           │                │
           ▼                ▼
     Return num       num = num + 1
                            │
                            ▼
                     Repeat Process
```

---

# Example

### Input

```text
n = 10

t = 2
```

Digit Product

```text
1 × 0 = 0
```

Since

```text
0 % 2 = 0
```

Answer

```text
10
```

---

# Dry Run

### Input

```text
n = 15

t = 8
```

Check

```text
15

Product = 1 × 5 = 5

5 % 8 ≠ 0
```

Next

```text
16

Product = 1 × 6 = 6

6 % 8 ≠ 0
```

Next

```text
17

Product = 1 × 7 = 7

7 % 8 ≠ 0
```

Next

```text
18

Product = 1 × 8 = 8

8 % 8 = 0
```

Return

```text
18
```

---

# Memory Visualization

```text
Current Number
      │
      ▼
Extract Digits
      │
      ▼
Multiply Digits
      │
      ▼
Check Divisibility
      │
      ▼
Answer / Next Number
```

---

# Why Brute Force Works?

The search begins from the smallest allowed number,

```text
n
```

and proceeds in increasing order.

Every candidate is checked exactly once.

Therefore,

the first number satisfying

```text
Digit Product % t == 0
```

must be the smallest valid answer.

---

# Complexity Analysis

## Approach 1 — Brute Force + Digit Simulation

### Time Complexity

```text
O(k × d)
```

where

- `k` = Number of candidate numbers checked.
- `d` = Number of digits in each number.

---

### Space Complexity

```text
O(1)
```

Only a few integer variables are used.

---

# Java Solution

## Approach 1 — Brute Force + Digit Simulation (Optimal for Given Constraints)

```java
//Approach-1 (Brute Force + Digit Simulation)
//T.C : O(k × d)
//S.C : O(1)

class Solution {

    public int smallestNumber(
        int n,
        int t
    ) {

        for (
            int num = n;
            num <= n + 100;
            num++
        ) {

            int product = 1;

            int temp = num;

            while (
                temp > 0
            ) {

                product *=
                    temp % 10;

                temp /= 10;
            }

            if (
                product % t == 0
            ) {

                return num;
            }
        }

        return -1;
    }
}
```

---

# C++ Solution

## Approach 1 — Brute Force + Digit Simulation (Optimal for Given Constraints)

```cpp
//Approach-1 (Brute Force + Digit Simulation)
//T.C : O(k × d)
//S.C : O(1)

class Solution {
public:

    int smallestNumber(
        int n,
        int t
    ) {

        for (
            int num = n;
            num <= n + 100;
            num++
        ) {

            int product = 1;

            int temp = num;

            while (
                temp > 0
            ) {

                product *=
                    temp % 10;

                temp /= 10;
            }

            if (
                product % t == 0
            ) {

                return num;
            }
        }

        return -1;
    }
};
```

---

# Memory Visualization

```text
          Start from n
               │
               ▼
      Current Candidate Number
               │
               ▼
        Extract Each Digit
               │
               ▼
      Compute Digit Product
               │
               ▼
   Is Product Divisible by t?
        ┌──────────────┐
      Yes             No
       │               │
       ▼               ▼
 Return Number    Check Next Number
```

---

# Edge Cases

## Case 1 — First Number is Valid

```text
Input

n = 10

t = 2

Digit Product

1 × 0 = 0

0 % 2 = 0

Answer = 10
```

---

## Case 2 — Need to Search Further

```text
Input

n = 15

t = 8

15 → Product = 5

16 → Product = 6

17 → Product = 7

18 → Product = 8

Answer = 18
```

---

## Case 3 — Number Contains Zero

```text
Input

n = 105

t = 7

Digit Product

1 × 0 × 5 = 0

0 % 7 = 0

Answer = 105
```

---

## Case 4 — Large Divisor

```text
Input

n = 21

t = 9

21 → Product = 2

22 → Product = 4

23 → Product = 6

24 → Product = 8

25 → Product = 10

...

Continue until a valid number is found.
```

---

# Complexity Analysis

| Operation             |  Complexity  |
| :-------------------- | :----------: |
| Check each candidate  | **O(k × d)** |
| Compute digit product |   **O(d)**   |
| Extra Space           |   **O(1)**   |

where

- **k** = Number of candidate numbers checked.
- **d** = Number of digits in each number.

---

# Complexity Comparison

| Approach                       | Algorithm                     |     Time     |  Space   |
| :----------------------------- | :---------------------------- | :----------: | :------: |
| Brute Force + Digit Simulation | Check candidates sequentially | **O(k × d)** | **O(1)** |

---

# Final Complexity

```text
Approach 1 (Brute Force + Digit Simulation)

Time Complexity  : O(k × d)

Space Complexity : O(1)
```

---

# Conclusion

- ✅ Start checking numbers from `n` in increasing order.
- ✅ Compute the product of all digits for each candidate.
- ✅ Verify whether the digit product is divisible by `t`.
- ✅ Return the first valid number immediately since it is the smallest possible answer.
- ✅ The algorithm is simple to implement and uses **constant extra space**, making it efficient for the given problem constraints.
