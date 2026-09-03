# 3876. Construct Uniform Parity Array II

## LeetCode

**Problem:** 3876. Construct Uniform Parity Array II

**Main Profile:** https://leetcode.com/u/Shailu03/

**Alternate Profile:** https://leetcode.com/u/ShailendraLeetcode03/

**GitHub:** https://github.com/Shailendra0320

## Problem Statement

You are given an array `nums1` of `n` distinct integers.

Construct an array `nums2` of the same length such that all elements of `nums2` are either all odd or all even.

For every index `i`, choose exactly one:

```text
nums2[i] = nums1[i]

or

nums2[i] = nums1[i] - nums1[j]
where j != i and nums1[i] - nums1[j] >= 1
```

Return `true` if such an array can be constructed; otherwise return `false`.

## Examples

### Example 1

```text
Input:  nums1 = [1,4,7]
Output: true
```

Keep `1` and `7`, and change `4`:

```text
4 - 1 = 3
```

So:

```text
nums2 = [1,3,7]
```

All values are odd.

### Example 2

```text
Input:  nums1 = [2,3]
Output: false
```

For `2`, the only other value is `3`, but:

```text
2 - 3 = -1
```

which is invalid.

### Example 3

```text
Input:  nums1 = [4,6]
Output: true
```

Both values are already even.

## Constraints

```text
1 <= n == nums1.length <= 10^5
1 <= nums1[i] <= 10^9
nums1 consists of distinct integers.
```

# What Is the Question Really Asking?

The key is to determine whether the required **uniform parity** is possible.

There are two important facts:

```text
Even - Odd = Odd
Odd - Even = Odd

Even - Even = Even
Odd - Odd = Even
```

But unlike LeetCode 3875, here the subtraction must also satisfy:

```text
nums1[i] - nums1[j] >= 1
```

which means:

```text
nums1[j] < nums1[i]
```

So both **parity** and **ordering** matter.

---

# Core Observation

Let `mn` be the minimum element in the array.

The minimum element is special because there is no smaller element available to subtract from it.

## Case 1: Minimum is odd

Suppose:

```text
mn = odd
```

Every even element `x` is larger than `mn`.

Therefore:

```text
x - mn >= 1
```

and:

```text
Even - Odd = Odd
```

So every even element can be converted to odd, while every odd element can simply remain unchanged.

Therefore:

```text
minimum is odd -> true
```

Example:

```text
[1,4,7]

minimum = 1 (odd)

4 - 1 = 3
```

Result:

```text
[1,3,7]
```

All odd.

## Case 2: Minimum is even and an odd exists

Suppose:

```text
mn = even
```

and there is at least one odd value.

Because `mn` is the minimum, every odd value is larger than `mn`.

To make the minimum even value odd, we would need to subtract an odd value:

```text
Even - Odd = Odd
```

but every odd value is larger, so:

```text
mn - odd < 0
```

The subtraction is invalid.

So the array cannot be made uniformly odd.

It also cannot be made uniformly even in this mixed-parity situation.

Therefore:

```text
minimum is even + any odd exists -> false
```

## Case 3: All values are even

The array is already uniform.

Therefore:

```text
true
```

---

# Final Decision Rule

The whole problem reduces to:

```text
Find minimum value.

If minimum is odd:
    return true

If minimum is even:
    return true only when every value is even
    otherwise return false
```

An equivalent rule is:

```text
If both odd and even numbers exist,
the minimum element must be odd.
```

---

# Approach 1: Minimum Element + Odd Presence

## Idea

During one traversal:

1. Find the minimum value.
2. Check whether any odd value exists.

Then:

- Minimum odd → `true`
- Minimum even + odd exists → `false`
- Minimum even + no odd → `true`

## Flow

```text
             nums1
               |
        Find minimum
               |
         Is minimum odd?
          /           \
        YES            NO
         |              |
       true       Is any odd present?
                    /          \
                  YES           NO
                   |             |
                 false         true
```

## Java

```java
//Approach-1 (Minimum Element + Odd Presence)
//T.C : O(n)
//S.C : O(1)

class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        boolean isOddPresent = false;

        for (int val : nums1) {
            min = Math.min(min, val);

            if (val % 2 != 0) {
                isOddPresent = true;
            }
        }

        if (min % 2 != 0) {
            return true;
        }

        return !isOddPresent;
    }
}
```

## C++

```cpp
//Approach-1 (Minimum Element + Odd Presence)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:
    bool uniformArray(vector<int>& nums1) {
        int minVal = INT_MAX;
        bool isOddPresent = false;

        for (int val : nums1) {
            minVal = min(minVal, val);

            if (val % 2 != 0) {
                isOddPresent = true;
            }
        }

        if (minVal % 2 != 0) {
            return true;
        }

        return !isOddPresent;
    }
};
```

## Dry Run

For:

```text
nums1 = [2,3,7]
```

After the scan:

```text
min = 2
isOddPresent = true
```

Since:

```text
min is even
and
an odd value exists
```

return:

```text
false
```

---

# Approach 2: Minimum Odd + Smaller Even Check

## Idea

If there is no odd number, all values are even, so return `true`.

Otherwise find:

```text
minOdd = smallest odd number
```

For every even number `x`, check whether:

```text
x < minOdd
```

If yes, that even value cannot subtract a smaller odd number, so the construction is impossible.

Therefore:

```text
even < minOdd -> false
otherwise -> true
```

## Flow

```text
                nums1
                  |
            Find minimum odd
                  |
       Is there a minimum odd?
          /              \
        NO               YES
         |                |
       true        Check every even x
                           |
                    x < minOdd ?
                     /        \
                   YES         NO
                    |           |
                  false        true
```

## Java

```java
//Approach-2 (Minimum Odd + Smaller Even Check)
//T.C : O(n)
//S.C : O(1)

class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        for (int val : nums1) {
            if (val % 2 != 0) {
                minOdd = Math.min(minOdd, val);
            }
        }

        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        for (int val : nums1) {
            if (val % 2 == 0 && val < minOdd) {
                return false;
            }
        }

        return true;
    }
}
```

## C++

```cpp
//Approach-2 (Minimum Odd + Smaller Even Check)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:
    bool uniformArray(vector<int>& nums1) {
        int minOdd = INT_MAX;

        for (int val : nums1) {
            if (val % 2 != 0) {
                minOdd = min(minOdd, val);
            }
        }

        if (minOdd == INT_MAX) {
            return true;
        }

        for (int val : nums1) {
            if (val % 2 == 0 && val < minOdd) {
                return false;
            }
        }

        return true;
    }
};
```

## Dry Run

Consider:

```text
nums1 = [3,6,9,10]
```

Smallest odd:

```text
minOdd = 3
```

Even values:

```text
6, 10
```

Check:

```text
6 < 3  -> false
10 < 3 -> false
```

Therefore:

```text
true
```

A valid construction is:

```text
3  -> 3
6  -> 6 - 3 = 3
9  -> 9
10 -> 10 - 3 = 7
```

Result:

```text
[3,3,9,7]
```

All odd.

---

# Correctness Proof

We prove the rule:

```text
minimum odd -> true
minimum even + odd exists -> false
minimum even + no odd -> true
```

### All values even

Keeping every value unchanged gives an all-even `nums2`.

So the answer is `true`.

### All values odd

Keeping every value unchanged gives an all-odd `nums2`.

So the answer is `true`.

### Mixed parity and minimum is odd

Let `mn` be the minimum odd value.

Every even `x` satisfies:

```text
x > mn
```

so:

```text
x - mn >= 1
```

and the result is odd because:

```text
Even - Odd = Odd
```

Odd values can stay unchanged.

Thus an all-odd array exists.

So the answer is `true`.

### Mixed parity and minimum is even

Let `mn` be the minimum even value.

To make `mn` odd, we need to subtract an odd value. But every odd value is larger than `mn`, so:

```text
mn - odd < 0
```

which is forbidden.

Thus the minimum value cannot be changed into odd.

Because the input contains both parities, leaving it even cannot make the whole array uniform either.

Therefore the answer is `false`.

---

# Why the Minimum Is the Critical Element

The positivity condition:

```text
nums1[i] - nums1[j] >= 1
```

is equivalent to:

```text
nums1[j] < nums1[i]
```

The smallest element has no smaller candidate.

So if the minimum has the wrong parity for the only possible uniform construction, the whole problem becomes impossible.

This is the central trick.

---

# 3875 vs 3876

This problem is closely related to **3875. Construct Uniform Parity Array I**.

### 3875

There was no positive-difference restriction.

Therefore opposite-parity subtraction was always available, and the answer was always:

```text
true
```

### 3876

Now we require:

```text
nums1[i] - nums1[j] >= 1
```

So the subtracting element must be smaller.

That creates impossible cases such as:

```text
[2,3]
```

Therefore 3876 needs the minimum-element observation.

---

# Complexity Comparison

| Approach   | Time | Space | Main Idea                                 |
| ---------- | ---: | ----: | ----------------------------------------- |
| Approach 1 | O(n) |  O(1) | Check global minimum and odd presence     |
| Approach 2 | O(n) |  O(1) | Find minimum odd and reject smaller evens |

Both are optimal because at least one scan of the input is required.

### Preferred Approach

**Approach 1** is the simplest to remember:

```text
minimum odd -> true
minimum even + any odd -> false
minimum even + all even -> true
```

---

# Edge Cases

### Single Element

```text
[5]
```

Already uniform.

```text
true
```

### All Even

```text
[2,4,8,10]
```

Already all even.

```text
true
```

### All Odd

```text
[1,5,9]
```

Already all odd.

```text
true
```

### Mixed, Minimum Odd

```text
[1,4,7]
```

```text
true
```

### Mixed, Minimum Even

```text
[2,3,7]
```

```text
false
```

---

# Common Mistakes

### Mistake 1: Assuming mixed parity is always solvable

That was the main idea in 3875, but the positive-difference restriction changes everything here.

### Mistake 2: Ignoring the smallest element

The minimum cannot subtract any smaller element, so it is the decisive element.

### Mistake 3: Checking only whether an odd exists

An odd value is useful only if it is smaller than the even value we want to transform.

---

# Interview Thought Process

When you see:

```text
nums1[i] - nums1[j] >= 1
```

immediately rewrite it mentally as:

```text
nums1[j] < nums1[i]
```

Then write the parity rules:

```text
same parity    -> even
different      -> odd
```

Next ask:

> Which element cannot use a smaller element?

Answer:

```text
minimum
```

Then the whole problem collapses to a minimum/parity check.

---

# Quick Checklist

```text
1. Is the array already uniform?
2. What parity does subtraction produce?
3. Does the subtraction have to be positive?
4. What element has no smaller candidate?
5. What is the parity of the minimum?
6. If the minimum is even, does an odd exist?
```

Final rule:

```text
min odd                     -> true
min even + all even        -> true
min even + any odd         -> false
```

---

# One-Line Insight

> **If both parities exist, the minimum element must be odd; otherwise the minimum even value has no smaller odd value that can be subtracted from it.**

---

# Final Summary

The solution depends on combining:

```text
Parity
+
Positive subtraction
+
Minimum element
```

The final condition is:

```text
if minimum is odd:
    true

else if any odd exists:
    false

else:
    true
```

Both presented approaches run in:

```text
Time:  O(n)
Space: O(1)
```

# Tags

`Array` `Math` `Parity` `Greedy` `Constructive Algorithm` `Minimum` `LeetCode` `Medium`

## Source Verification

The problem statement, examples, constraints, and the minimum-odd characterization were cross-checked against published solution references.
