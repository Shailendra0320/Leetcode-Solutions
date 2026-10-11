# 2778. Sum of Squares of Special Elements — Divisibility Scan and Divisor Pairs

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

## 🏷️ Tags

`Array, Enumeration, Math, Number Theory, Divisors, Divisibility, Square, Counting, Linear Scan, Optimization, LeetCode, Easy`

---

# 📌 Problem

You are given a **1-indexed integer array** `nums` of length `n`.

An element `nums[i]` is called **special** if its 1-based index divides `n` evenly:

```text
n % i == 0
```

Return the sum of the squares of all special elements.

**Indexing reminder:** The problem statement uses 1-based positions, but Java, C++, and Python arrays use 0-based indexing. Therefore, a special 1-based position `i` is accessed as `nums[i - 1]` in code.

---

# 🧪 Examples

## Example 1

```text
Input: nums = [1,2,3,4]
Output: 21
```

The array length is `n = 4`. Its special positions are `1`, `2`, and `4`, because each divides `4` evenly.

| 1-based position `i` | `4 % i` | Special? | Value | Square |
| -------------------: | ------: | -------- | ----: | -----: |
|                    1 |       0 | Yes      |     1 |      1 |
|                    2 |       0 | Yes      |     2 |      4 |
|                    3 |       1 | No       |     3 |      — |
|                    4 |       0 | Yes      |     4 |     16 |

```text
answer = 1² + 2² + 4²
       = 1 + 4 + 16
       = 21
```

## Example 2

```text
Input: nums = [2,7,1,19,18,3]
Output: 63
```

Here `n = 6`. The divisors of `6` are `1`, `2`, `3`, and `6`. The corresponding special values are `2`, `7`, `1`, and `3`.

```text
answer = 2² + 7² + 1² + 3²
       = 4 + 49 + 1 + 9
       = 63
```

---

# 🔍 What Is the Problem Really Asking?

We do **not** square every element of the array. We only square an element if its position is a divisor of the array length.

For example, when `n = 6`, the valid positions are:

```text
1, 2, 3, 6
```

Positions `4` and `5` are not special, because `6 % 4 != 0` and `6 % 5 != 0`.

The problem therefore has four simple steps:

1. Consider a 1-based position `i`.
2. Check whether `n % i == 0`.
3. If it is a divisor, square `nums[i - 1]`.
4. Add that square to the answer.

The central idea is **divisibility of the index**, not the value stored at that index.

---

# 💡 Core Insight

There are two useful approaches.

## Approach 1 — Divisibility Scan

Check every possible position from `1` through `n`:

```text
if n % i == 0:
    answer += nums[i - 1] * nums[i - 1]
```

This approach is the simplest and takes `O(n)` time with `O(1)` auxiliary space.

## Approach 2 — Enumerate Divisors in Pairs

Divisors occur in pairs. If `d` divides `n`, then:

```text
other = n / d
```

is also a divisor.

For example, the divisor pairs for `n = 12` are:

```text
(1, 12)
(2, 6)
(3, 4)
```

One member of every pair is at most `sqrt(n)`, so it is enough to test candidates while:

```text
d * d <= n
```

When `d` divides `n`, process both `d` and `n / d`. If `d * d == n`, both values are the same divisor, so count it only once.

This approach takes `O(√n)` time and `O(1)` auxiliary space.

---

# 🌳 Approach 1 — Divisibility Scan

## Idea

Maintain only:

```text
answer = 0
n = nums.length
```

Loop through the 1-based positions `i = 1, 2, ..., n`. Whenever `n % i == 0`, the position is special, so add the square of `nums[i - 1]`.

We do not need a separate list of special positions. Each qualifying element can be added immediately.

## 🏗️ Architecture / Flow Diagram — Approach 1

```text
                         Start
                           |
                           v
                  n = nums.length
                    answer = 0
                           |
                           v
                       i = 1 to n
                           |
                           v
                       Check n % i
                           |
                    +------+------+
                    |             |
                  equals 0      not 0
                    |             |
                    v             v
           add nums[i-1]²        skip
                    |
                    v
               next position
                    |
                    v
              return answer
```

## 🔄 Data Flow — Approach 1

```text
nums
  |
  v
n = nums.length
  |
  v
Try each 1-based position i
  |
  v
Does n % i == 0?
  |
  +--> No  -> ignore nums[i - 1]
  |
  +--> Yes -> square nums[i - 1]
                    |
                    v
              add to answer
                    |
                    v
               next position
                    |
                    v
                final sum
```

## 🧪 Dry Run — Approach 1

Use:

```text
nums = [2,7,1,19,18,3]
n = 6
answer = 0
```

| `i` | `6 % i` | Action                   | Running answer |
| --: | ------: | ------------------------ | -------------: |
|   1 |       0 | Add `nums[0]² = 2² = 4`  |              4 |
|   2 |       0 | Add `nums[1]² = 7² = 49` |             53 |
|   3 |       0 | Add `nums[2]² = 1² = 1`  |             54 |
|   4 |       2 | Skip                     |             54 |
|   5 |       1 | Skip                     |             54 |
|   6 |       0 | Add `nums[5]² = 3² = 9`  |             63 |

Final answer:

```text
63
```

## ✅ Correctness — Approach 1

The loop visits every 1-based position from `1` through `n` exactly once. A position is special precisely when `n % i == 0`. The algorithm adds `nums[i - 1]²` if and only if this condition is true.

Therefore every special element contributes its square exactly once, while every non-special element is ignored. The computed sum is exactly the required answer.

---

# ⏱️ Complexity — Approach 1

```text
Time:  O(n)
Space: O(1) auxiliary
```

The loop checks all `n` positions. Each modulo test, square, and addition takes constant time. Only a few integer variables are used.

---

# 🌳 Approach 2 — Enumerate Divisors in Pairs

## Idea

Every divisor `d` of `n` is paired with `n / d`:

```text
d * (n / d) = n
```

For example, if `n = 36`, the divisor pairs are:

```text
(1, 36)
(2, 18)
(3, 12)
(4, 9)
(6, 6)
```

We only need to try `d` values up to `sqrt(n)`. If `d` divides `n`, add the square at position `d` and also the square at position `n / d`, unless these positions are the same.

The code uses the 1-based divisor as the position, so it accesses:

```text
nums[d - 1]
nums[other - 1]
```

This avoids checking every index individually.

## 🏗️ Architecture / Flow Diagram — Approach 2

```text
                         Start
                           |
                           v
                    d = 1, answer = 0
                           |
                           v
                     d * d <= n?
                       /      \
                     Yes       No
                      |         |
                      v         v
                  n % d == 0?  return answer
                   /      \
                 Yes       No
                  |         |
                  v         |
          add nums[d - 1]²  |
                  |         |
                  v         |
             other = n / d |
                  |         |
                  v         |
             other != d?   |
              /      \     |
            Yes       No   |
             |         |   |
             v         |   |
       add nums[other-1]²  |
             |             |
             +-------------+
                           |
                           v
                        d = d + 1
                           |
                           └── repeat
```

## 🔄 Data Flow — Approach 2

```text
n
|
v
Try d = 1, 2, ..., floor(sqrt(n))
|
v
Does n % d == 0?
|
+--> No -> move to next d
|
+--> Yes
       |
       +--> add square at position d
       |
       +--> other = n / d
                |
                +--> if other != d, add square at position other
|
v
All divisor pairs processed
|
v
Return sum
```

## 🧪 Dry Run — Approach 2

Use:

```text
nums = [2,7,1,19,18,3]
n = 6
answer = 0
```

The loop runs while `d * d <= 6`, so only `d = 1` and `d = 2` are checked.

### `d = 1`

```text
6 % 1 == 0
other = 6 / 1 = 6
```

Add positions `1` and `6`:

```text
nums[0]² + nums[5]²
= 2² + 3²
= 4 + 9
= 13
```

### `d = 2`

```text
6 % 2 == 0
other = 6 / 2 = 3
```

Add positions `2` and `3`:

```text
nums[1]² + nums[2]²
= 7² + 1²
= 49 + 1
= 50
```

Combined answer:

```text
13 + 50 = 63
```

The next candidate is `d = 3`, but:

```text
3 * 3 > 6
```

so the loop ends. Every divisor has been counted.

## ✅ Correctness — Approach 2

Every divisor `d` of `n` has a paired divisor `n / d`. For every pair, at least one member is at most `sqrt(n)`. Therefore testing candidates up to the square root finds every divisor pair.

Whenever `d` divides `n`, the algorithm adds the squares at positions `d` and `n / d`. If `d == n / d`, the divisor is a square root of `n`; the duplicate check ensures that the same position is added only once.

Consequently, every divisor of `n` is processed exactly once. Since special elements are exactly those whose 1-based indices divide `n`, the algorithm returns the correct sum of squares.

---

# ⏱️ Complexity — Approach 2

```text
Time:  O(sqrt(n))
Space: O(1) auxiliary
```

Only candidates up to `sqrt(n)` are tested, and each divisor pair is processed in constant time. The algorithm uses a constant number of variables.

---

# ☕ Java — Both Approaches

```java
//Approach-1 (Divisibility Scan)
//T.C : O(n)
//S.C : O(1)

import java.util.*;

class Solution {

    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                answer += nums[i - 1] * nums[i - 1];
            }
        }

        return answer;
    }
}

//Approach-2 (Enumerate Divisors in Pairs)
//T.C : O(sqrt(n))
//S.C : O(1)

class Solution2 {

    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int answer = 0;

        for (int d = 1; d * d <= n; d++) {
            if (n % d == 0) {
                answer += nums[d - 1] * nums[d - 1];

                int other = n / d;

                if (other != d) {
                    answer += nums[other - 1] * nums[other - 1];
                }
            }
        }

        return answer;
    }
}
```

---

# 💻 C++ — Both Approaches

```cpp
//Approach-1 (Divisibility Scan)
//T.C : O(n)
//S.C : O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {

public:
    int sumOfSquares(vector<int>& nums) {
        int n = nums.size();
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                answer += nums[i - 1] * nums[i - 1];
            }
        }

        return answer;
    }
};

//Approach-2 (Enumerate Divisors in Pairs)
//T.C : O(sqrt(n))
//S.C : O(1)

class Solution2 {

public:
    int sumOfSquares(vector<int>& nums) {
        int n = nums.size();
        int answer = 0;

        for (int d = 1; d * d <= n; d++) {
            if (n % d == 0) {
                answer += nums[d - 1] * nums[d - 1];

                int other = n / d;

                if (other != d) {
                    answer += nums[other - 1] * nums[other - 1];
                }
            }
        }

        return answer;
    }
};
```

---

# 🐍 Python — Both Approaches

```python
#Approach-1 (Divisibility Scan)
#T.C : O(n)
#S.C : O(1)

class Solution:

    def sumOfSquares(self, nums: list[int]) -> int:
        n = len(nums)
        answer = 0

        for i in range(1, n + 1):
            if n % i == 0:
                answer += nums[i - 1] * nums[i - 1]

        return answer


#Approach-2 (Enumerate Divisors in Pairs)
#T.C : O(sqrt(n))
#S.C : O(1)

class Solution2:

    def sumOfSquares(self, nums: list[int]) -> int:
        n = len(nums)
        answer = 0
        d = 1

        while d * d <= n:
            if n % d == 0:
                answer += nums[d - 1] * nums[d - 1]

                other = n // d

                if other != d:
                    answer += nums[other - 1] * nums[other - 1]

            d += 1

        return answer
```

---

# ⚖️ Approach Comparison

| Feature                 | Approach 1: Divisibility Scan | Approach 2: Divisor Pairs     |
| ----------------------- | ----------------------------- | ----------------------------- |
| Main idea               | Test every 1-based position   | Test divisors up to `sqrt(n)` |
| Time complexity         | `O(n)`                        | `O(sqrt(n))`                  |
| Auxiliary space         | `O(1)`                        | `O(1)`                        |
| Implementation          | Very simple                   | Uses divisor symmetry         |
| Extra data structure    | None                          | None                          |
| Best for readability    | ✅                            | Good                          |
| Best asymptotic runtime | Good                          | ✅                            |

**Practical note:** The constraint is `n <= 50`, so both approaches are easily fast enough. The direct scan is the simplest implementation; the divisor-pair method is a useful number-theory optimization to learn.

---

# 🧠 Pattern Recognition

When a problem says an array element is special based on whether its **index divides the array length**, translate that directly into:

```text
n % i == 0
```

Then choose one of two strategies:

```text
Simple constraint / clear code -> scan all indices
Need fewer checks             -> enumerate divisor pairs up to sqrt(n)
```

Divisor-pair enumeration is useful in many number-theory tasks, including finding all factors of a positive integer.

---

# ❌ Common Mistakes

## 1. Mixing up 1-based and 0-based indexing

The problem refers to position `i` starting at `1`, while arrays in code start at `0`.

Correct:

```text
nums[i - 1]
```

when `i` is the 1-based divisor.

## 2. Reversing the modulo condition

Correct:

```text
n % i == 0
```

This asks whether `i` divides `n`.

## 3. Squaring every array value

Only positions that divide `n` are special. Values at other positions must be ignored.

## 4. Double-counting a square-root divisor

If `n = 36` and `d = 6`, then `other = n / d = 6` too. The position must be included once, not twice.

Use:

```text
if other != d
```

## 5. Forgetting to check divisibility before using the paired divisor

Only when `n % d == 0` does `n / d` form the corresponding integer divisor pair.

---

# 🎯 Interview Explanation

> I first get the array length `n`. A position `i` is special exactly when `n % i == 0`, so I iterate from `1` through `n` and add `nums[i - 1] * nums[i - 1]` whenever this condition holds. This is `O(n)` time and `O(1)` auxiliary space. An optimized alternative checks divisors only up to `sqrt(n)`: for each divisor `d`, its paired divisor is `n / d`, so I process both positions and avoid double-counting when they are equal. That method takes `O(sqrt(n))` time and `O(1)` space.

---

# 📝 Quick Revision

## Approach 1 — Divisibility Scan

```text
n = nums.length
answer = 0

for i = 1 to n:
    if n % i == 0:
        answer += nums[i - 1]²

return answer
```

## Approach 2 — Divisor Pairs

```text
d = 1
while d * d <= n:
    if n % d == 0:
        add nums[d - 1]²
        other = n / d
        if other != d:
            add nums[other - 1]²
    d++
```

---

# 🚀 One-Line Insight

> Square an element only when its 1-based index is a divisor of the array length.

---

# ✅ Final Takeaway

The fundamental condition is:

```text
n % i == 0
```

When it holds, include:

```text
nums[i - 1] * nums[i - 1]
```

The direct scan is the simplest solution:

```text
Time:  O(n)
Space: O(1)
```

Enumerating divisors in pairs improves the asymptotic number of checks:

```text
Time:  O(sqrt(n))
Space: O(1)
```

Both approaches return the sum of squares of all special elements. With the problem's small constraints, **Approach 1 is the clearest recommendation**, while **Approach 2 demonstrates an efficient divisor-enumeration pattern**.
'''
path.write_text(content, encoding='utf-8')
print(path)
