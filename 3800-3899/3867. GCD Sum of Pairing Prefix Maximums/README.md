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

# 3867. GCD Sum of Pairing Prefix Maximums

## Tags

```text
Math
Greatest Common Divisor (GCD)
Sorting
Greedy
Array
Simulation
Java
C++
```

---

# Intuition

For every element,

we first compute

```text
GCD(Current Element,

Maximum Element Seen So Far)
```

This creates a new array of GCD values.

Next,

sort the array

and pair

```text
Smallest

with

Largest
```

The answer is the sum of

```text
GCD

of every pair.
```

---

# Key Observation

Instead of working directly on the original array,

we transform it into

```text
Prefix GCD Array
```

where

```text
prefixGcd[i]

=

gcd(nums[i],

Maximum Prefix Value)
```

After sorting,

pairing the smallest and largest values naturally follows the problem statement.

---

# Approaches

1. Prefix GCD + Sorting (Optimal)

---

# Approach 1 — Prefix GCD + Sorting

## Idea

Traverse the array once.

Maintain

```text
Maximum Value Seen
```

For every element,

compute

```text
gcd(current,

maximum)
```

Store the result.

After processing,

sort the GCD array.

Finally,

pair

```text
First

with

Last
```

and add

```text
gcd(pair)
```

to the answer.

---

# Algorithm

### Step 1

Initialize

```text
Maximum = 0
```

---

### Step 2

Traverse the array.

---

### Step 3

Update

```text
Maximum
```

---

### Step 4

Compute

```text
gcd(current,

maximum)
```

Store inside

```text
Prefix GCD Array
```

---

### Step 5

Sort the GCD array.

---

### Step 6

Pair

```text
Smallest

with

Largest
```

Compute their

```text
GCD
```

and add it to the answer.

---

### Step 7

Return the final sum.

---

# Flowchart

```text
              Start

                │

                ▼

         Read Input Array

                │

                ▼

 Maintain Maximum Prefix

                │

                ▼

 Compute Prefix GCD

                │

                ▼

 Store In New Array

                │

                ▼

         Sort GCD Array

                │

                ▼

 Pair First & Last

                │

                ▼

 Compute Pair GCD

                │

                ▼

 Add To Answer

                │

                ▼

 Return Answer
```

---

# Example

Input

```text
nums = [6,10,15,9]
```

Maximum Prefix

```text
6

10

15

15
```

Prefix GCD

```text
6

10

15

3
```

After Sorting

```text
3

6

10

15
```

Pairs

```text
(3,15)

gcd = 3
```

```text
(6,10)

gcd = 2
```

Answer

```text
3 + 2

=

5
```

---

# Dry Run

Input

```text
[6,10,15]
```

Maximum Prefix

```text
6

10

15
```

Prefix GCD

```text
6

10

15
```

Sorted

```text
6

10

15
```

Pairs

```text
(6,15)

gcd = 3
```

Answer

```text
3
```

---

# Memory Visualization

```text
Input Array

      │

      ▼

Maximum Prefix

      │

      ▼

Prefix GCD

      │

      ▼

Sort

      │

      ▼

Pair Ends

      │

      ▼

Sum Of GCDs
```

---

# Why This Works?

Each prefix GCD depends only on

```text
Current Value

and

Largest Prefix Value
```

Once the transformed array is built,

sorting allows direct pairing of

```text
Smallest

with

Largest
```

The required answer is obtained by summing the GCD of every such pair.

---

# Complexity Analysis

## Approach 1 — Prefix GCD + Sorting

### Time Complexity

```text
O(n log n)
```

- Building the prefix GCD array takes **O(n)**.
- Sorting takes **O(n log n)**.
- Pair processing takes **O(n)**.

Overall complexity is dominated by sorting.

---

### Space Complexity

```text
O(n)
```

For storing the prefix GCD array.

---

# Java Solution

## Approach 1 — Prefix GCD + Sorting (Optimal)

```java
//Approach-1 (Prefix GCD + Sorting)
//T.C : O(n log n)
//S.C : O(n)

import java.util.Arrays;

class Solution {

    public long gcdSum(int[] nums) {

        int n = nums.length;

        int[] prefixGcd =
            new int[n];

        int maximum = 0;

        for (
            int i = 0;
            i < n;
            i++
        ) {

            maximum =
                Math.max(
                    maximum,
                    nums[i]
                );

            prefixGcd[i] =
                gcd(
                    nums[i],
                    maximum
                );
        }

        Arrays.sort(
            prefixGcd
        );

        long answer = 0;

        for (
            int i = 0;
            i < n / 2;
            i++
        ) {

            answer +=
                gcd(
                    prefixGcd[i],
                    prefixGcd[n - i - 1]
                );
        }

        return answer;
    }

    private int gcd(
        int a,
        int b
    ) {

        while (b != 0) {

            int temp =
                a % b;

            a = b;

            b = temp;
        }

        return a;
    }
}
```

---

# C++ Solution

## Approach 1 — Prefix GCD + Sorting (Optimal)

```cpp
//Approach-1 (Prefix GCD + Sorting)
//T.C : O(n log n)
//S.C : O(n)

class Solution {
public:

    long long gcdSum(
        vector<int>& nums
    ) {

        int n = nums.size();

        vector<int> prefixGcd(
            n
        );

        int maximum = 0;

        for (
            int i = 0;
            i < n;
            i++
        ) {

            maximum =
                max(
                    maximum,
                    nums[i]
                );

            prefixGcd[i] =
                gcd(
                    nums[i],
                    maximum
                );
        }

        sort(
            prefixGcd.begin(),
            prefixGcd.end()
        );

        long long answer = 0;

        for (
            int i = 0;
            i < n / 2;
            i++
        ) {

            answer +=
                gcd(
                    prefixGcd[i],
                    prefixGcd[n - i - 1]
                );
        }

        return answer;
    }

private:

    int gcd(
        int a,
        int b
    ) {

        while (b != 0) {

            int temp =
                a % b;

            a = b;

            b = temp;
        }

        return a;
    }
};
```

---

# Complexity Comparison

| Approach             | Algorithm                   |      Time      |  Space   |
| :------------------- | :-------------------------- | :------------: | :------: |
| Prefix GCD + Sorting | Prefix Processing + Sorting | **O(n log n)** | **O(n)** |

---

# Final Complexity

```text
Approach 1 (Prefix GCD + Sorting)

Time Complexity  : O(n log n)

Space Complexity : O(n)
```

---

# Conclusion

- ✅ Traverse the array once while maintaining the **maximum prefix value**.
- ✅ Compute the **GCD** of each element with the current maximum and store it in a new array.
- ✅ Sort the transformed GCD array.
- ✅ Pair the smallest element with the largest, the second smallest with the second largest, and so on.
- ✅ Sum the GCD of each pair to obtain the final answer.
- ✅ The algorithm runs in **O(n log n)** time, dominated by the sorting step.
