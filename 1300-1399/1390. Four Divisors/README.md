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

# 1390. Four Divisors

# Intuition

We need to:

- find numbers having exactly 4 divisors
- add the sum of those divisors

Example:

```text
num = 21

Divisors:
1, 3, 7, 21

Count = 4
Sum = 32
```

So contribution of `21` is:

```text
32
```

---

# Approach

## Key Observation

A divisor always appears in pairs:

```text
i and num / i
```

So:

- iterate from `1` to `sqrt(num)`
- whenever divisor found:
  - count both divisors
  - add both divisors

If:

- total divisor count becomes exactly `4`
  → return divisor sum

Else:

- return `0`

---

# Divisor Pair Diagram

```text
num = 21

i = 1
21 % 1 == 0

Divisors:
1 and 21

----------------

i = 3
21 % 3 == 0

Divisors:
3 and 7
```

Final divisors:

```text
1, 3, 7, 21
```

Count:

```text
4
```

Sum:

```text
32
```

---

# Important Case

Perfect squares are invalid here.

Example:

```text
16

Divisors:
1,2,4,8,16
```

`4` repeats as square root.

So:

- divisor count becomes odd
- cannot have exactly 4 divisors

Hence:

```java
if(i * i == num) {
    return 0;
}
```

---

# Dry Run

Input:

```text
nums = [21,4,7]
```

---

## For 21

Divisors:

```text
1,3,7,21
```

Count:

```text
4
```

Sum:

```text
32
```

Contribution:

```text
32
```

---

## For 4

Perfect square.

Contribution:

```text
0
```

---

## For 7

Divisors:

```text
1,7
```

Count:

```text
2
```

Contribution:

```text
0
```

---

Final Answer:

```text
32
```

---

# Complexity

## Time Complexity

```text
O(n * sqrt(num))
```

Where:

- `n` = size of array

---

## Space Complexity

```text
O(1)
```

---

# Java Solution

```java
class Solution {

    public int sumFourDivisors(int[] nums) {

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {

            sum += getContributions(nums[i]);
        }

        return sum;
    }

    public int getContributions(int num) {

        int count = 0;

        int sum = 0;

        for (int i = 1; i * i <= num; i++) {

            if (num % i == 0) {

                if (i * i == num) {

                    return 0;
                }

                count += 2;

                sum += i;

                sum += num / i;
            }
        }

        return count == 4 ? sum : 0;
    }
}
```
