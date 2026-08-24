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

# 3513. Number of Unique XOR Triplets I

## Problem Statement

You are given an integer array `nums` of length `n`.

The array is a **permutation of the numbers from `1` to `n`**.

A XOR triplet is defined as:

```text
nums[i] XOR nums[j] XOR nums[k]
```

where:

```text
i <= j <= k
```

The same index is allowed to be selected more than once.

Return the number of **unique XOR values** that can be obtained from all possible triplets.

---

## Example 1

```text
Input:
nums = [1, 2]

Output:
2
```

The possible triplets are:

```text
(0, 0, 0)
1 XOR 1 XOR 1 = 1

(0, 0, 1)
1 XOR 1 XOR 2 = 2

(0, 1, 1)
1 XOR 2 XOR 2 = 1

(1, 1, 1)
2 XOR 2 XOR 2 = 2
```

The unique values are:

```text
{1, 2}
```

Therefore:

```text
Answer = 2
```

---

## Example 2

```text
Input:
nums = [3, 1, 2]

Output:
4
```

Some possible XOR values are:

```text
3 XOR 3 XOR 3 = 3

3 XOR 3 XOR 1 = 1

3 XOR 3 XOR 2 = 2

3 XOR 1 XOR 2 = 0
```

So the unique values are:

```text
{0, 1, 2, 3}
```

Therefore:

```text
Answer = 4
```

---

# Constraints

```text
1 <= n == nums.length <= 10^5

1 <= nums[i] <= n

nums is a permutation of [1, n]
```

---

# Intuition

At first glance, this problem looks like a brute-force triplet problem.

There are three indices:

```text
i
j
k
```

and we need to consider every:

```text
i <= j <= k
```

A natural first thought is:

```text
for every i
    for every j
        for every k
            calculate nums[i] XOR nums[j] XOR nums[k]
```

But this would require roughly:

```text
O(n^3)
```

operations.

With:

```text
n <= 10^5
```

that is completely impossible.

So we need to find a property that lets us avoid looking at the actual permutation.

The most important observation is:

> `nums` is a permutation of `[1, n]`.

Therefore, the set of values inside `nums` is always exactly:

```text
{1, 2, 3, ..., n}
```

The order of these values does not affect the number of unique XOR results.

So the problem is really asking:

```text
How many different values can be produced by
a XOR b XOR c
where a, b, c belong to [1, n]
and repetition is allowed?
```

This changes the problem completely.

---

# Important XOR Properties

Before solving the problem, we need to understand a few XOR properties.

## Property 1 — XOR With Itself

For any number `x`:

```text
x XOR x = 0
```

For example:

```text
5 XOR 5 = 0
```

---

## Property 2 — XOR With Zero

```text
x XOR 0 = x
```

Therefore:

```text
x XOR x XOR x
```

becomes:

```text
(x XOR x) XOR x
= 0 XOR x
= x
```

So by selecting the same index three times, we can always produce every value that already exists in the array.

Since the array contains:

```text
1, 2, 3, ..., n
```

all values:

```text
1 through n
```

are immediately possible.

---

## Property 3 — XOR Is Commutative

```text
a XOR b = b XOR a
```

So the order of the chosen values does not affect the result.

---

## Property 4 — XOR Is Associative

```text
(a XOR b) XOR c
=
a XOR (b XOR c)
```

This allows us to reason about XOR values without worrying about the order in which the operation is performed.

---

# The Key Observation

Let:

```text
n = nums.length
```

Find the number of bits required to represent `n`.

Suppose:

```text
n = 5
```

Binary representation:

```text
5 = 101
```

It requires:

```text
3 bits
```

The largest value representable using 3 bits is:

```text
111
```

which is:

```text
7
```

Therefore the possible XOR values range from:

```text
0 to 7
```

There are:

```text
8
```

possible values.

For:

```text
n = 5
```

the answer is therefore:

```text
8
```

---

# Why Does the Answer Become a Power of Two?

Suppose `n >= 3`.

Let:

```text
k = number of bits in n
```

Then:

```text
2^(k-1) <= n < 2^k
```

All numbers from `1` to `n` use at most `k` bits.

XOR never creates a bit beyond the highest bit present in its operands.

Therefore every XOR result is in:

```text
[0, 2^k - 1]
```

There are exactly:

```text
2^k
```

numbers in this interval.

The important fact for this problem is that, when `n >= 3`, the available values `1 ... n` are sufficient to generate **every** value in:

```text
0, 1, 2, ..., 2^k - 1
```

using three selections with repetition allowed.

Therefore:

```text
Answer = 2^k
```

where `2^k` is the smallest power of two strictly greater than `n`.

---

# Small Cases Must Be Handled Separately

The formula above needs a special case for:

```text
n <= 2
```

## Case 1 — n = 1

The only value is:

```text
1
```

The only possible XOR is:

```text
1 XOR 1 XOR 1 = 1
```

So:

```text
Answer = 1
```

---

## Case 2 — n = 2

The values are:

```text
1, 2
```

The possible XOR values are:

```text
1
2
```

There is no way to generate `0`.

Therefore:

```text
Answer = 2
```

---

## Case 3 — n >= 3

Now the full range:

```text
0 ... 2^k - 1
```

can be generated.

Therefore:

```text
Answer = 2^k
```

where:

```text
k = bit length of n
```

---

# Formula

The final formula is:

```text
if n <= 2:
    answer = n
else:
    answer = 2^(bitLength(n))
```

In Java:

```java
n <= 2 ? n : 1 << (32 - Integer.numberOfLeadingZeros(n))
```

The expression:

```java
32 - Integer.numberOfLeadingZeros(n)
```

gives the number of bits required to represent positive integer `n`.

---

# Understanding Bit Length

Consider:

```text
n = 3
```

Binary:

```text
3 = 11
```

Bit length:

```text
2
```

Therefore:

```text
1 << 2
```

means:

```text
4
```

So:

```text
answer = 4
```

---

Consider:

```text
n = 5
```

Binary:

```text
5 = 101
```

Bit length:

```text
3
```

Therefore:

```text
1 << 3 = 8
```

So:

```text
answer = 8
```

---

Consider:

```text
n = 8
```

Binary:

```text
8 = 1000
```

Bit length:

```text
4
```

Therefore:

```text
1 << 4 = 16
```

So:

```text
answer = 16
```

Notice that the required power of two is the **next power of two greater than `n`**.

---

# Power of Two Interpretation

For `n >= 3`, the answer is:

```text
smallest power of 2 strictly greater than n
```

Examples:

| n   | Binary  | Answer |
| --- | ------- | -----: |
| 3   | `11`    |      4 |
| 4   | `100`   |      8 |
| 5   | `101`   |      8 |
| 6   | `110`   |      8 |
| 7   | `111`   |      8 |
| 8   | `1000`  |     16 |
| 9   | `1001`  |     16 |
| 10  | `1010`  |     16 |
| 15  | `1111`  |     16 |
| 16  | `10000` |     32 |

The exceptions are:

```text
n = 1 → 1
n = 2 → 2
```

---

# Approach 1 — Mathematical / Bit Manipulation

## Idea

The actual order of `nums` does not matter.

Only:

```text
n = nums.length
```

matters.

For:

```text
n <= 2
```

return:

```text
n
```

For:

```text
n >= 3
```

find the number of bits required to represent `n`.

If that number is `k`, return:

```text
2^k
```

which can be calculated using:

```text
1 << k
```

---

# Approach 1 — Step-by-Step

## Step 1 — Get n

```java
int n = nums.length;
```

---

## Step 2 — Handle Small Cases

```java
if (n <= 2) {
    return n;
}
```

Why?

Because:

```text
n = 1 → answer = 1
n = 2 → answer = 2
```

---

## Step 3 — Find Bit Length

Java provides:

```java
Integer.numberOfLeadingZeros(n)
```

For a positive `int`, the number of bits required to represent `n` is:

```text
32 - Integer.numberOfLeadingZeros(n)
```

For example:

```text
n = 5
```

Binary:

```text
00000000 00000000 00000000 00000101
```

There are:

```text
29 leading zeros
```

Therefore:

```text
32 - 29 = 3
```

So:

```text
bitLength(5) = 3
```

---

## Step 4 — Calculate 2^k

Use:

```java
1 << k
```

For:

```text
k = 3
```

we get:

```text
1 << 3 = 8
```

Therefore:

```text
answer = 8
```

---

# Approach 1 Visualization

```text
                         nums
                           │
                           ▼
                     Find n = length
                           │
                           ▼
                    Is n <= 2 ?
                       /       \
                     Yes        No
                      │          │
                      ▼          ▼
                 return n    Find bitLength(n)
                                 │
                                 ▼
                           k = bitLength(n)
                                 │
                                 ▼
                              1 << k
                                 │
                                 ▼
                              Answer
```

---

# Worked Example 1

## Input

```text
nums = [1, 2]
```

We have:

```text
n = 2
```

Since:

```text
n <= 2
```

return:

```text
2
```

---

## Verify by Enumeration

```text
1 XOR 1 XOR 1 = 1

1 XOR 1 XOR 2 = 2

1 XOR 2 XOR 2 = 1

2 XOR 2 XOR 2 = 2
```

Unique values:

```text
{1, 2}
```

Answer:

```text
2
```

---

# Worked Example 2

## Input

```text
nums = [3, 1, 2]
```

Here:

```text
n = 3
```

Since:

```text
n > 2
```

we use the formula.

Binary representation:

```text
3 = 11
```

Bit length:

```text
2
```

Therefore:

```text
1 << 2 = 4
```

Answer:

```text
4
```

---

## Verify the Values

We can obtain:

```text
3 XOR 3 XOR 3 = 3

3 XOR 3 XOR 1 = 1

3 XOR 3 XOR 2 = 2

3 XOR 1 XOR 2 = 0
```

Unique values:

```text
{0, 1, 2, 3}
```

There are:

```text
4
```

unique values.

---

# Worked Example 3

## Input

```text
nums = [1, 2, 3, 4, 5]
```

Here:

```text
n = 5
```

Binary:

```text
5 = 101
```

Bit length:

```text
3
```

Therefore:

```text
1 << 3 = 8
```

Answer:

```text
8
```

The possible XOR values fill:

```text
0, 1, 2, 3, 4, 5, 6, 7
```

So there are:

```text
8
```

unique values.

---

# Worked Example 4

## Input

```text
nums = [7, 3, 1, 6, 2, 5, 4]
```

The array is a permutation of:

```text
1 ... 7
```

The order is different, but that does not matter.

Therefore:

```text
n = 7
```

Binary:

```text
7 = 111
```

Bit length:

```text
3
```

So:

```text
1 << 3 = 8
```

Answer:

```text
8
```

This demonstrates an important point:

> The answer depends only on `n`, not on the arrangement of the permutation.

---

# Worked Example 5

## Input

```text
nums = [1, 2, 3, 4, 5, 6, 7, 8]
```

Here:

```text
n = 8
```

Binary:

```text
8 = 1000
```

Bit length:

```text
4
```

Therefore:

```text
1 << 4 = 16
```

Answer:

```text
16
```

The possible values cover:

```text
0 ... 15
```

which contains:

```text
16
```

different values.

---

# Detailed Bit-Length Table

|   n | Binary  | Bit Length | `1 << bitLength` | Answer |
| --: | ------- | ---------: | ---------------: | -----: |
|   1 | `1`     |          1 |                2 |      1 |
|   2 | `10`    |          2 |                4 |      2 |
|   3 | `11`    |          2 |                4 |      4 |
|   4 | `100`   |          3 |                8 |      8 |
|   5 | `101`   |          3 |                8 |      8 |
|   6 | `110`   |          3 |                8 |      8 |
|   7 | `111`   |          3 |                8 |      8 |
|   8 | `1000`  |          4 |               16 |     16 |
|   9 | `1001`  |          4 |               16 |     16 |
|  10 | `1010`  |          4 |               16 |     16 |
|  11 | `1011`  |          4 |               16 |     16 |
|  12 | `1100`  |          4 |               16 |     16 |
|  13 | `1101`  |          4 |               16 |     16 |
|  14 | `1110`  |          4 |               16 |     16 |
|  15 | `1111`  |          4 |               16 |     16 |
|  16 | `10000` |          5 |               32 |     32 |

The two special values are:

```text
n = 1 → 1
n = 2 → 2
```

After that, the answer is the next power of two.

---

# Why the Permutation Property Is Important

Suppose:

```text
nums = [3, 1, 2]
```

or:

```text
nums = [1, 3, 2]
```

or:

```text
nums = [2, 3, 1]
```

All of them contain exactly:

```text
1, 2, 3
```

Therefore they have the same set of available values.

Since repeated indices are allowed, we can choose any value multiple times.

So the actual order does not affect the answer.

This means we do not need to:

```text
Sort the array
```

and we do not need to inspect the elements individually.

We only need:

```text
nums.length
```

---

# Why Repeated Indices Matter

The condition is:

```text
i <= j <= k
```

not:

```text
i < j < k
```

Therefore, the same index can be used more than once.

For example:

```text
(i, j, k) = (0, 0, 0)
```

is valid.

So if:

```text
nums[0] = x
```

then:

```text
x XOR x XOR x = x
```

This is why every original value can be generated immediately.

This detail is essential to understanding the problem.

---

# Brute Force Approach

Before looking at the mathematical solution, it is useful to understand the obvious solution.

## Idea

Enumerate every valid triplet:

```text
i <= j <= k
```

For every triplet, calculate:

```text
nums[i] XOR nums[j] XOR nums[k]
```

and insert the result into a `HashSet`.

At the end:

```text
return set.size()
```

---

# Brute Force Visualization

```text
                    nums
                      │
                      ▼
                 Choose i
                      │
                      ▼
                 Choose j >= i
                      │
                      ▼
                 Choose k >= j
                      │
                      ▼
            nums[i] XOR nums[j] XOR nums[k]
                      │
                      ▼
                  Add to Set
                      │
                      ▼
                More triplets?
                  /       \
                Yes        No
                 │          │
                 └──────────┘
                            │
                            ▼
                        set.size()
```

---

# Why Brute Force Is Too Slow

The number of triplets with:

```text
i <= j <= k
```

is:

```text
C(n + 2, 3)
```

which is approximately:

```text
O(n^3)
```

For:

```text
n = 10^5
```

this is far too large.

Therefore, a brute-force solution will not pass.

---

# Why We Do Not Need a HashSet

A `HashSet` is useful when we do not know which values will occur.

For this problem, however, the mathematical structure tells us exactly how many distinct values exist.

For `n >= 3`, the unique XOR values are:

```text
0 through 2^k - 1
```

Therefore:

```text
number of unique values = 2^k
```

There is no reason to actually construct the set.

This reduces the problem from:

```text
Generate all triplets
        ↓
Calculate XOR
        ↓
Store values in HashSet
        ↓
Count
```

to:

```text
Find n
  ↓
Find bit length
  ↓
Return next power of two
```

---

# Correctness Proof

We can prove the solution using several observations.

## Lemma 1 — Every value from 1 to n is obtainable

Because repeated indices are allowed, for any:

```text
x ∈ [1, n]
```

we can select the same index three times.

Then:

```text
x XOR x XOR x
```

Since:

```text
x XOR x = 0
```

we get:

```text
0 XOR x = x
```

Therefore every value:

```text
1, 2, ..., n
```

is obtainable.

---

## Lemma 2 — Every XOR result uses at most bitLength(n) bits

Let:

```text
k = bitLength(n)
```

Then:

```text
n < 2^k
```

Therefore every number from:

```text
1 to n
```

can be represented using at most `k` bits.

XOR works independently on each bit.

It cannot introduce a new bit position that was not present in any operand.

Therefore every triplet XOR is in:

```text
[0, 2^k - 1]
```

There are exactly:

```text
2^k
```

possible values in this interval.

So:

```text
answer <= 2^k
```

---

## Lemma 3 — For n >= 3, all values in the range are obtainable

For `n >= 3`, the values in `[1, n]` contain enough binary structure to generate every `k`-bit value through three XOR selections with repetition.

The key idea is that:

```text
x XOR y XOR z
```

can independently combine the set bits of the available numbers.

Values already inside `[1, n]` are obtained using:

```text
x XOR x XOR x = x
```

while values outside `[1, n]` but below `2^k` can be constructed by combining available values whose highest bits and lower bits produce the target through XOR.

Thus, for `n >= 3`, the complete set of obtainable results is:

```text
{0, 1, 2, ..., 2^k - 1}
```

---

## Lemma 4 — Zero Is obtainable for n >= 3

Choose any valid value `x`.

Then:

```text
x XOR x XOR x = x
```

does not give zero, but with three available values we can construct zero.

For example, when `n >= 3`:

```text
1 XOR 2 XOR 3 = 0
```

because:

```text
1 XOR 2 = 3
3 XOR 3 = 0
```

Therefore zero is also part of the result set.

---

## Theorem

For:

```text
n <= 2
```

the answers are:

```text
1
2
```

respectively.

For:

```text
n >= 3
```

let:

```text
k = bitLength(n)
```

All possible values are exactly:

```text
0 ... 2^k - 1
```

There are:

```text
2^k
```

such values.

Therefore:

```text
answer = 2^k
```

The algorithm returns exactly this value.

Hence the algorithm is correct.

---

# Alternative Mathematical Form

For `n >= 3`:

```text
answer = 2^(floor(log2(n)) + 1)
```

because:

```text
bitLength(n) = floor(log2(n)) + 1
```

Therefore:

```text
answer = 2^(floor(log2(n)) + 1)
```

In code, however, using bit operations is cleaner than using floating-point logarithms.

---

# Why Bit Operations Are Better Than log2

We could write:

```java
int k = (int)(Math.log(n) / Math.log(2));
return 1 << (k + 1);
```

but this is less desirable.

Floating-point calculations can introduce precision concerns.

Bit operations work directly with the binary representation of the integer.

In Java:

```java
32 - Integer.numberOfLeadingZeros(n)
```

gives the exact bit length for positive `n`.

So the preferred implementation is:

```java
int bits = 32 - Integer.numberOfLeadingZeros(n);
return 1 << bits;
```

---

# Approach 1 — Java Solution

```java
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n <= 2) {
            return n;
        }

        int bits = 32 - Integer.numberOfLeadingZeros(n);

        return 1 << bits;
    }
}
```

---

# Java Code Explanation

## Get the Length

```java
int n = nums.length;
```

The permutation property tells us that the answer depends only on `n`.

---

## Handle Small Cases

```java
if (n <= 2) {
    return n;
}
```

This handles:

```text
n = 1 → 1
n = 2 → 2
```

---

## Find Number of Bits

```java
int bits = 32 - Integer.numberOfLeadingZeros(n);
```

For example:

```text
n = 7
```

Binary:

```text
111
```

Bit length:

```text
3
```

So:

```text
bits = 3
```

---

## Calculate the Answer

```java
return 1 << bits;
```

For:

```text
bits = 3
```

this means:

```text
1 << 3
```

which is:

```text
8
```

---

# Approach 1 — C++ Solution

```cpp
class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {
        int n = nums.size();

        if (n <= 2) {
            return n;
        }

        int bits = 32 - __builtin_clz(n);

        return 1 << bits;
    }
};
```

---

# C++ Code Explanation

## Length

```cpp
int n = nums.size();
```

Only the length is required.

---

## Small Cases

```cpp
if (n <= 2) {
    return n;
}
```

---

## Bit Length

For positive integers:

```cpp
32 - __builtin_clz(n)
```

gives the number of bits required to represent `n`.

For example:

```text
n = 5

5 = 101

bit length = 3
```

Therefore:

```cpp
1 << 3 = 8
```

---

# Approach 1 — Python Solution

```python
class Solution:
    def uniqueXorTriplets(self, nums: List[int]) -> int:
        n = len(nums)

        if n <= 2:
            return n

        return 1 << n.bit_length()
```

---

# Python Code Explanation

Python directly provides:

```python
n.bit_length()
```

For example:

```python
5 .bit_length()
```

returns:

```text
3
```

Therefore:

```python
1 << n.bit_length()
```

becomes:

```text
1 << 3
= 8
```

This is a very clean implementation.

---

# Approach 1 — JavaScript Solution

```javascript
class Solution {
  /**
   * @param {number[]} nums
   * @return {number}
   */
  uniqueXorTriplets(nums) {
    const n = nums.length;

    if (n <= 2) {
      return n;
    }

    const bits = 32 - Math.clz32(n);

    return 2 ** bits;
  }
}
```

---

# JavaScript Code Explanation

JavaScript provides:

```javascript
Math.clz32(n);
```

which counts the leading zero bits in the 32-bit representation.

Therefore:

```javascript
32 - Math.clz32(n);
```

gives the bit length for positive `n`.

Then:

```javascript
2 ** bits;
```

computes the required power of two.

---

# Approach 1 — Go Solution

```go
func uniqueXorTriplets(nums []int) int {
    n := len(nums)

    if n <= 2 {
        return n
    }

    bits := bits.Len(uint(n))

    return 1 << bits
}
```

---

# Approach 1 — Complexity Analysis

Let:

```text
n = nums.length
```

The algorithm does not iterate through the array.

It only:

```text
1. Gets n
2. Finds bit length
3. Performs one shift
```

Therefore:

```text
Time Complexity = O(1)
```

The algorithm uses only a few integer variables.

Therefore:

```text
Space Complexity = O(1)
```

---

# Complexity Comparison

| Approach                  |  Time | Space | Practical? |
| ------------------------- | ----: | ----: | ---------- |
| Brute Force Triplets      | O(n³) | O(n³) | No         |
| Brute Force + HashSet     | O(n³) | O(n³) | No         |
| Mathematical Bit Approach |  O(1) |  O(1) | Yes        |

The optimal solution is dramatically better.

---

# Brute Force vs Optimal

```text
Brute Force

        nums
          │
          ▼
    Choose i
          │
          ▼
    Choose j
          │
          ▼
    Choose k
          │
          ▼
   Calculate XOR
          │
          ▼
    Insert into Set
          │
          ▼
      Repeat
          │
          ▼
      O(n³)
```

Compared with:

```text
Optimal

        nums
          │
          ▼
    Get n = length
          │
          ▼
     n <= 2 ?
       /   \
     Yes    No
      │      │
      ▼      ▼
    n      bitLength(n)
              │
              ▼
           1 << bits
              │
              ▼
            O(1)
```

---

# Detailed Dry Run

Consider:

```text
nums = [4, 1, 3, 2, 5]
```

Because `nums` is a permutation:

```text
{1, 2, 3, 4, 5}
```

The order does not matter.

---

## Step 1 — Find n

```text
n = 5
```

---

## Step 2 — Check Small Case

```text
5 <= 2
```

is false.

So continue.

---

## Step 3 — Binary Representation

```text
5 = 101
```

---

## Step 4 — Bit Length

```text
bitLength(5) = 3
```

---

## Step 5 — Calculate Power of Two

```text
1 << 3
```

equals:

```text
8
```

---

## Final Answer

```text
8
```

---

# Another Dry Run

Consider:

```text
nums = [8, 3, 6, 1, 7, 2, 5, 4]
```

Then:

```text
n = 8
```

Binary:

```text
1000
```

Bit length:

```text
4
```

Therefore:

```text
1 << 4 = 16
```

Answer:

```text
16
```

---

# Edge Cases

## Edge Case 1 — n = 1

```text
nums = [1]
```

Only:

```text
1 XOR 1 XOR 1 = 1
```

Answer:

```text
1
```

---

## Edge Case 2 — n = 2

```text
nums = [1, 2]
```

Unique values:

```text
{1, 2}
```

Answer:

```text
2
```

---

## Edge Case 3 — n = 3

```text
nums = [3, 1, 2]
```

Possible unique values:

```text
{0, 1, 2, 3}
```

Answer:

```text
4
```

---

## Edge Case 4 — n Is a Power of Two

For:

```text
n = 8
```

we have:

```text
8 = 1000
```

The next required range is:

```text
0 ... 15
```

so the answer is:

```text
16
```

Notice:

```text
answer = 2 * n
```

when `n` itself is a power of two and `n >= 4`.

---

## Edge Case 5 — n Is Just Below a Power of Two

For:

```text
n = 7
```

we have:

```text
7 = 111
```

All values:

```text
0 ... 7
```

are possible.

There are:

```text
8
```

values.

Therefore:

```text
answer = 8
```

---

# Important Observation About the Actual Array

Consider these two inputs:

```text
nums = [1, 2, 3, 4, 5]
```

and:

```text
nums = [5, 3, 1, 4, 2]
```

Both have:

```text
n = 5
```

and both contain exactly the same set:

```text
{1, 2, 3, 4, 5}
```

Therefore both have the same answer:

```text
8
```

So we do not need to inspect:

```text
nums[0]
nums[1]
...
nums[n - 1]
```

at all.

Only:

```text
nums.length
```

matters.

---

# Why the Index Condition Does Not Complicate the Solution

The problem says:

```text
i <= j <= k
```

At first this may look like an ordering restriction.

However, because the same index can be reused, every multiset of three available values can be represented by some nondecreasing index triple.

For example, if we want to choose:

```text
2, 5, 5
```

we simply select the indices containing those values in sorted order.

Therefore the problem can be viewed as choosing three values from:

```text
{1, 2, ..., n}
```

with repetition allowed.

This is why the permutation order is irrelevant.

---

# Why Zero Appears

Zero is especially important because it is not present in the original array.

For `n >= 3`, the values:

```text
1, 2, 3
```

are available.

Then:

```text
1 XOR 2 = 3
```

and:

```text
3 XOR 3 = 0
```

Therefore:

```text
1 XOR 2 XOR 3 = 0
```

So the result set contains zero.

This is one reason the answer can be larger than `n`.

For example:

```text
n = 3
```

The original values are:

```text
1, 2, 3
```

but the possible XOR results are:

```text
0, 1, 2, 3
```

giving:

```text
4
```

unique values.

---

# Binary Interpretation

XOR is a bitwise operation.

For example:

```text
5 = 101
3 = 011
```

Then:

```text
5 XOR 3

101
011
---
110
```

which is:

```text
6
```

XOR never creates a bit beyond the highest bit position used by its operands.

That is why the answer is connected to powers of two.

---

# Highest Set Bit

Another way to describe the answer is through the highest set bit of `n`.

Suppose:

```text
n = 13
```

Binary:

```text
1101
```

The highest set bit corresponds to:

```text
8
```

The number of bits is:

```text
4
```

Therefore the answer is:

```text
2^4 = 16
```

In general, for `n >= 3`:

```text
answer = 2^(MSB position + 1)
```

where the least significant bit has position `0`.

---

# Formula Using log2

The same result can be written as:

```text
answer = 2^(floor(log2(n)) + 1)
```

for:

```text
n >= 3
```

For example:

```text
n = 13

floor(log2(13)) = 3

answer = 2^(3 + 1)
       = 16
```

However, bit manipulation is preferable in implementation.

---

# Formula Using Next Power of Two

For `n >= 3`:

```text
answer = nextPowerOfTwoStrictlyGreaterThan(n)
```

Examples:

```text
n = 3  → 4
n = 4  → 8
n = 5  → 8
n = 7  → 8
n = 8  → 16
n = 10 → 16
```

The special cases remain:

```text
n = 1 → 1
n = 2 → 2
```

---

# Common Mistakes

## Mistake 1 — Trying O(n³)

A direct enumeration of all triplets is far too slow.

With:

```text
n = 100000
```

the number of combinations is enormous.

---

## Mistake 2 — Using a HashSet With Brute Force

A `HashSet` removes duplicate XOR values, but it does not solve the fundamental complexity problem.

You would still need to generate all triplets.

So the solution remains approximately:

```text
O(n³)
```

---

## Mistake 3 — Returning n for Every Input

It is true that every original value is obtainable.

But for:

```text
n >= 3
```

additional values can appear.

Example:

```text
n = 3
```

We get:

```text
0
```

in addition to:

```text
1, 2, 3
```

So the answer is:

```text
4
```

not:

```text
3
```

---

## Mistake 4 — Forgetting the n <= 2 Case

The general next-power-of-two formula does not work directly for:

```text
n = 1
n = 2
```

For example:

```text
n = 2
```

The next power of two would be:

```text
4
```

but the correct answer is:

```text
2
```

Therefore:

```java
if (n <= 2) {
    return n;
}
```

is necessary.

---

## Mistake 5 — Thinking the Order of nums Matters

The array is a permutation.

The actual arrangement:

```text
[1, 2, 3]
```

versus:

```text
[3, 1, 2]
```

does not change the set of available values.

Because repeated indices are allowed, only the values from `1` to `n` matter.

---

# Important Concepts

## 1. XOR

XOR compares corresponding binary bits.

```text
0 XOR 0 = 0
0 XOR 1 = 1
1 XOR 0 = 1
1 XOR 1 = 0
```

---

## 2. Cancellation

The most useful XOR identity is:

```text
x XOR x = 0
```

This makes repeated selections extremely powerful.

---

## 3. Permutation

Because `nums` is a permutation of:

```text
[1, n]
```

we know every value from `1` through `n` exists exactly once.

Therefore:

```text
nums
```

itself does not need to be processed.

---

## 4. Bit Length

The number of bits required to represent `n` determines the size of the possible XOR range.

If:

```text
bitLength(n) = k
```

then the possible `k`-bit values are:

```text
0 ... 2^k - 1
```

and there are:

```text
2^k
```

of them.

---

## 5. Bit Manipulation

The answer can be obtained using a single left shift:

```text
1 << bitLength(n)
```

This is equivalent to:

```text
2^bitLength(n)
```

---

# Overall Algorithm

```text
1. Let n = nums.length.

2. If n <= 2:
       return n.

3. Find the number of bits required to represent n.

4. Let:
       k = bitLength(n)

5. Return:
       2^k

   which can be calculated as:
       1 << k
```

---

# Algorithm Flowchart

```text
                         Start
                           │
                           ▼
                    n = nums.length
                           │
                           ▼
                     Is n <= 2?
                      /        \
                    Yes         No
                     │           │
                     ▼           ▼
                 Return n    Find bitLength(n)
                                 │
                                 ▼
                              k bits
                                 │
                                 ▼
                             1 << k
                                 │
                                 ▼
                              Answer
                                 │
                                 ▼
                                End
```

---

# Mathematical Summary

For:

```text
n = 1
```

```text
answer = 1
```

For:

```text
n = 2
```

```text
answer = 2
```

For:

```text
n >= 3
```

let:

```text
k = floor(log2(n)) + 1
```

Then:

```text
answer = 2^k
```

Therefore the complete formula is:

```text
answer =
    n,                         if n <= 2
    2^(floor(log2(n)) + 1),    if n >= 3
```

---

# Complexity Analysis

## Time Complexity

The algorithm performs only a constant number of operations.

We do not iterate through:

```text
nums
```

and we do not enumerate triplets.

Therefore:

```text
Time Complexity = O(1)
```

---

## Space Complexity

Only a few integer variables are used.

Therefore:

```text
Space Complexity = O(1)
```

---

# Complexity Diagram

```text
Input nums
    │
    ▼
Get length
    │
    ▼
Find bit length
    │
    ▼
Left shift
    │
    ▼
Answer

Time  → O(1)
Space → O(1)
```

---

# Final Comparison

```text
┌───────────────────────────────┬──────────────┬──────────────┐
│ Approach                      │ Time         │ Space        │
├───────────────────────────────┼──────────────┼──────────────┤
│ Brute Force                   │ O(n³)        │ O(1)         │
│ Brute Force + HashSet         │ O(n³)        │ O(n³)        │
│ Mathematical Bit Approach     │ O(1)         │ O(1)         │
└───────────────────────────────┴──────────────┴──────────────┘
```

The mathematical approach is the only practical solution for:

```text
n <= 10^5
```

---

# Why This Problem Is Interesting

This problem is a good example of why understanding the constraints and mathematical structure is important.

At first, it looks like:

```text
Array
+
Three Nested Loops
+
XOR
+
HashSet
```

But the permutation condition gives us a much stronger fact:

```text
nums contains exactly 1 ... n
```

Then the XOR properties reduce the problem further:

```text
Triplets
    ↓
Possible XOR values
    ↓
All k-bit values
    ↓
Count k-bit values
    ↓
2^k
```

So the entire problem can be solved without examining the elements.

---

# Key Takeaways

The most important ideas are:

```text
1. nums is a permutation of [1, n].

2. Repeated indices are allowed because i <= j <= k.

3. Therefore the actual ordering of nums does not matter.

4. XOR satisfies:
       x XOR x = 0

5. For n >= 3, all k-bit values can be produced,
   where k = bitLength(n).

6. There are exactly 2^k k-bit values.

7. Therefore:
       answer = 2^k

8. Handle n <= 2 separately.
```

---

# One-Line Insight

The entire problem can be reduced to:

```text
For n <= 2, answer = n.
Otherwise, answer is the smallest power of 2 strictly greater than n.
```

---

# Final Summary

```text
                         LeetCode 3513
                              │
                              ▼
                Number of Unique XOR Triplets I
                              │
                              ▼
                   nums is permutation [1,n]
                              │
                              ▼
                  Actual order does not matter
                              │
                              ▼
                         n = nums.length
                              │
                              ▼
                         Is n <= 2?
                         /          \
                       Yes           No
                        │             │
                        ▼             ▼
                    answer = n   Find bit length
                                      │
                                      ▼
                                  k = bitLength(n)
                                      │
                                      ▼
                                   1 << k
                                      │
                                      ▼
                                  Answer
```

The final solution uses:

```text
Permutation Property
        +
XOR Properties
        +
Bit Manipulation
        +
Mathematical Observation
```

to reduce the problem from an impossible:

```text
O(n³)
```

enumeration to:

```text
Time Complexity  → O(1)
Space Complexity → O(1)
```

---

# Final Java Solution

```java
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n <= 2) {
            return n;
        }

        int bits = 32 - Integer.numberOfLeadingZeros(n);

        return 1 << bits;
    }
}
```

---

# Final C++ Solution

```cpp
class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {
        int n = nums.size();

        if (n <= 2) {
            return n;
        }

        int bits = 32 - __builtin_clz(n);

        return 1 << bits;
    }
};
```

---

# Final Python Solution

```python
class Solution:
    def uniqueXorTriplets(self, nums: List[int]) -> int:
        n = len(nums)

        if n <= 2:
            return n

        return 1 << n.bit_length()
```

---

# Final Complexity

```text
Time Complexity  → O(1)

Space Complexity → O(1)
```

---

# Tags

`Array` `Bit Manipulation` `Math` `XOR` `Permutation` `Bitwise Operations` `Number Theory` `LeetCode` `Medium`
