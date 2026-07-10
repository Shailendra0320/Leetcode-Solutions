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

# 553. Optimal Division

## Tags

```text
Greedy
Math
String
Simulation
Array
Java
C++
```

---

# Intuition

We need to insert parentheses into the division expression so that the final result becomes

```text
Maximum
```

Since division is **not associative**,

```text
a / b / c

≠

a / (b / c)
```

Therefore,

the placement of parentheses directly affects the result.

---

# Key Observation

To maximize

```text
a / b / c / d ...
```

we should make the denominator

```text
As Small As Possible
```

The smallest denominator is obtained by dividing all remaining numbers together.

Hence,

```text
a / (b / c / d / ...)
```

is always optimal.

---

# Approaches

1. Greedy + String Construction (Optimal)

---

# Approach 1 — Greedy + String Construction

## Idea

There are only three cases.

If

```text
Length = 1
```

Return

```text
Number
```

If

```text
Length = 2
```

Return

```text
a / b
```

Otherwise,

wrap every number after the first one inside a single pair of parentheses.

```text
a / (b / c / d / ...)
```

This guarantees the maximum possible value.

---

# Algorithm

### Step 1

If

```text
n == 1
```

Return

```text
nums[0]
```

---

### Step 2

If

```text
n == 2
```

Return

```text
nums[0] / nums[1]
```

---

### Step 3

Create a

```text
StringBuilder
```

---

### Step 4

Append

```text
nums[0]

/(

nums[1]
```

---

### Step 5

Append remaining numbers

```text
/nums[i]
```

---

### Step 6

Append

```text
)
```

---

### Step 7

Return the generated expression.

---

# Flowchart

```text
             Start

               │

               ▼

        Read Input Array

               │

               ▼

          n == 1 ?

       ┌────────┴────────┐

      Yes               No

       │                 │

       ▼                 ▼

 Return Number      n == 2 ?

                 ┌───────┴────────┐

                Yes              No

                 │                │

                 ▼                ▼

           Return a/b      Build String

                                 │

                                 ▼

                       a/(b/c/d/...)

                                 │

                                 ▼

                           Return Answer
```

---

# Example

Input

```text
nums = [1000,100,10,2]
```

Without Parentheses

```text
1000 / 100 / 10 / 2

=

0.5
```

Optimal Expression

```text
1000 / (100 / 10 / 2)
```

Evaluate denominator

```text
100 / 10

=

10

10 / 2

=

5
```

Final Result

```text
1000 / 5

=

200
```

Answer

```text
"1000/(100/10/2)"
```

---

# Dry Run

Input

```text
[1000,100,10,2]
```

Start

```text
1000/(
```

↓

Append

```text
100
```

↓

Append

```text
/10
```

↓

Append

```text
/2
```

↓

Append

```text
)
```

Result

```text
1000/(100/10/2)
```

---

# Memory Visualization

```text
Input Array

       │

       ▼

Special Cases

       │

       ▼

StringBuilder

       │

       ▼

Append Numbers

       │

       ▼

Add Parentheses

       │

       ▼

Return Expression
```

---

# Why Greedy Works?

To maximize

```text
A / B
```

we should minimize

```text
B
```

Placing all remaining divisions inside one pair of parentheses minimizes the denominator.

Thus,

```text
a / (b / c / d / ...)
```

always produces the largest possible result.

---

# Complexity Analysis

## Approach 1 — Greedy + String Construction

### Time Complexity

```text
O(n)
```

Each number is appended exactly once.

---

### Space Complexity

```text
O(n)
```

Required for constructing the output string.

---

# Java Solution

## Approach 1 — Greedy + String Construction (Optimal)

```java
//Approach-1 (Greedy + String Construction)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public String optimalDivision(int[] nums) {

        int n = nums.length;

        if (n == 1) {

            return String.valueOf(
                nums[0]
            );
        }

        if (n == 2) {

            return nums[0]
                + "/"
                + nums[1];
        }

        StringBuilder answer =
            new StringBuilder();

        answer.append(nums[0]);

        answer.append("/(");

        answer.append(nums[1]);

        for (
            int i = 2;
            i < n;
            i++
        ) {

            answer.append("/");

            answer.append(nums[i]);
        }

        answer.append(")");

        return answer.toString();
    }
}
```

---

# C++ Solution

## Approach 1 — Greedy + String Construction (Optimal)

```cpp
//Approach-1 (Greedy + String Construction)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    string optimalDivision(
        vector<int>& nums
    ) {

        int n = nums.size();

        if (n == 1) {

            return to_string(
                nums[0]
            );
        }

        if (n == 2) {

            return
                to_string(nums[0])
                + "/"
                + to_string(nums[1]);
        }

        string answer =
            to_string(nums[0])
            + "/("
            + to_string(nums[1]);

        for (
            int i = 2;
            i < n;
            i++
        ) {

            answer += "/";

            answer +=
                to_string(nums[i]);
        }

        answer += ")";

        return answer;
    }
};
```

---

# Complexity Comparison

| Approach                     | Algorithm |   Time   |  Space   |
| :--------------------------- | :-------- | :------: | :------: |
| Greedy + String Construction | Greedy    | **O(n)** | **O(n)** |

---

# Final Complexity

```text
Approach 1 (Greedy + String Construction)

Time Complexity  : O(n)

Space Complexity : O(n)
```

---

# Conclusion

- ✅ Handle arrays of size **1** and **2** as special cases.
- ✅ For arrays of size **3 or more**, place all elements after the first inside a single pair of parentheses.
- ✅ The optimal expression is always:

```text
a / (b / c / d / ...)
```

- ✅ This minimizes the denominator, thereby maximizing the overall result.
- ✅ The solution constructs the expression in **O(n)** time using a `StringBuilder` (Java) or `string` (C++).
