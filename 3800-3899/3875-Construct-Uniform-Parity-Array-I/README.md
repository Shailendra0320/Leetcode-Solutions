# 3875. Construct Uniform Parity Array I

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode (Main):** https://leetcode.com/u/Shailu03/

**LeetCode (Alternate):** https://leetcode.com/u/ShailendraLeetcode03/

---

## 🔗 Problem Link

[LeetCode - 3875. Construct Uniform Parity Array I](https://leetcode.com/problems/construct-uniform-parity-array-i/)

---

## 📝 Problem Statement

You are given an array `nums1` of length `n`, where all elements are distinct.

You need to construct another array `nums2` of the same length such that:

- Every element of `nums2` has the same parity.
- That means either **all elements are even** or **all elements are odd**.
- For every index `i`, `nums2[i]` can be formed by either:
  - keeping `nums1[i]`, or
  - choosing some `j != i` and replacing it with `nums1[i] - nums1[j]`.

Return `true` if it is possible to construct such an array; otherwise, return `false`.

---

## 🔒 Constraints

- `1 <= n == nums1.length <= 100`
- `1 <= nums1[i] <= 100`
- All elements of `nums1` are distinct.

---

# 💡 Key Observation

The answer is **always `true`**.

There are only two possible parity cases.

### Case 1: All elements already have the same parity

If all elements are even:

```text
even, even, even, even
```

We can simply keep every element unchanged.

So `nums2` is already uniform.

Similarly, if all elements are odd:

```text
odd, odd, odd, odd
```

we can again keep every element unchanged.

Therefore, the answer is `true`.

---

### Case 2: The array contains both even and odd elements

Suppose an element is even and another element is odd.

For any **even** element `x`, choose an odd element `y`:

```text
even - odd = odd
```

For any **odd** element `x`, choose an even element `y`:

```text
odd - even = odd
```

Therefore, every element can be transformed into an **odd** number.

So the complete array can always be made:

```text
odd, odd, odd, odd, ...
```

Hence the answer is again `true`.

---

# 🧠 Approach 1: Check Whether Both Parities Exist

This approach directly follows the parity observation.

We scan the array and record whether at least one even and at least one odd value are present.

```text
                 Start
                   |
                   v
          Scan all elements
                   |
          +--------+--------+
          |                 |
       Even?              Odd?
          |                 |
          v                 v
   evenPresent=true   oddPresent=true
          |                 |
          +--------+--------+
                   |
                   v
      Is one parity missing?
             /          \
           Yes            No
            |              |
            v              v
         return true    Both exist
                           |
                           v
                 Convert every element
                   using opposite parity
                           |
                           v
                      return true
```

Even in the mixed-parity case, an odd result is always possible for every element.

---

## 🔍 Algorithm

1. Initialize:
   - `isEvenPresent = false`
   - `isOddPresent = false`
2. Traverse every value in `nums1`.
3. If the value is even, set `isEvenPresent = true`.
4. Otherwise, set `isOddPresent = true`.
5. If either parity is missing, the original array already has uniform parity, so return `true`.
6. Otherwise, both parities exist.
7. Every element can then be converted to odd using an element of the opposite parity.
8. Return `isOddPresent`.

---

## ✅ Java — Approach 1

```java
//Approach-1 (Check Both Parities)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public boolean uniformArray(int[] nums1) {

        boolean isEvenPresent = false;
        boolean isOddPresent = false;

        for (int val : nums1) {

            if (val % 2 == 0) {
                isEvenPresent = true;
            } else {
                isOddPresent = true;
            }
        }

        if (!isEvenPresent || !isOddPresent) {
            return true;
        }

        return isOddPresent;
    }
}
```

### Why this code works

When only one parity exists, the array is already uniform.

When both parities exist:

```text
even - odd = odd
odd  - even = odd
```

Thus every element can be made odd, so returning `true` is correct.

---

# 🚀 Approach 2: Direct Mathematical Observation

The strongest simplification is that **no computation is actually required**.

For every possible input, a valid uniform-parity array can always be constructed.

### Scenario A: All even

Keep all elements:

```text
[2, 8, 14, 20]

        ↓

[2, 8, 14, 20]
```

All are even.

### Scenario B: All odd

Keep all elements:

```text
[1, 5, 11, 17]

        ↓

[1, 5, 11, 17]
```

All are odd.

### Scenario C: Mixed parity

Choose an element with the opposite parity for every position:

```text
Even - Odd = Odd
Odd  - Even = Odd
```

Therefore, every element can be made odd.

So there is **no possible input for which the answer is `false`**.

---

## 🔍 Algorithm

```text
1. A uniform array is already valid when all numbers
   have the same parity.

2. If both parities exist:
      even - odd = odd
      odd  - even = odd

3. Therefore every element can be made odd.

4. Hence the answer is always true.
```

---

## ✅ Java — Approach 2

```java
//Approach-2 (Direct Mathematical Observation)
//T.C : O(1)
//S.C : O(1)

class Solution {

    public boolean uniformArray(int[] nums1) {

        return true;
    }
}
```

---

# 💻 C++ Solutions

## ✅ C++ — Approach 1

```cpp
//Approach-1 (Check Both Parities)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:
    bool uniformArray(vector<int>& nums1) {

        bool isEvenPresent = false;
        bool isOddPresent = false;

        for (int val : nums1) {

            if (val % 2 == 0) {
                isEvenPresent = true;
            } else {
                isOddPresent = true;
            }
        }

        if (!isEvenPresent || !isOddPresent) {
            return true;
        }

        return isOddPresent;
    }
};
```

---

## ✅ C++ — Approach 2

```cpp
//Approach-2 (Direct Mathematical Observation)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:
    bool uniformArray(vector<int>& nums1) {

        return true;
    }
};
```

---

# 🧪 Examples

## Example 1

```text
Input:
nums1 = [2, 4, 8]

All numbers are even.

Keep them unchanged:

nums2 = [2, 4, 8]

All elements have the same parity.

Output:
true
```

---

## Example 2

```text
Input:
nums1 = [1, 3, 7]

All numbers are odd.

Keep them unchanged:

nums2 = [1, 3, 7]

Output:
true
```

---

## Example 3

```text
Input:
nums1 = [2, 5, 8]

There are both even and odd numbers.

For 2:
2 - 5 = -3  -> odd

For 5:
5 - 2 = 3   -> odd

For 8:
8 - 5 = 3   -> odd

Therefore:

nums2 = [-3, 3, 3]

All elements are odd.

Output:
true
```

---

# 🔎 Detailed Dry Run

Consider:

```text
nums1 = [4, 7, 10, 13]
```

### Step 1: Detect parity

```text
4  -> even
7  -> odd
10 -> even
13 -> odd
```

So:

```text
isEvenPresent = true
isOddPresent  = true
```

### Step 2: Both parities exist

We can make every element odd by subtracting an element with opposite parity.

```text
4  - 7  = -3  -> odd
7  - 4  = 3   -> odd
10 - 7  = 3   -> odd
13 - 4  = 9   -> odd
```

Result:

```text
[-3, 3, 3, 9]
```

Every element is odd.

Therefore:

```text
answer = true
```

---

# ✅ Correctness Proof

We prove that the algorithm always returns `true`.

### Case 1: All elements have the same parity

If every number is even, keeping all elements unchanged produces an array of all even numbers.

If every number is odd, keeping all elements unchanged produces an array of all odd numbers.

Therefore, a valid `nums2` exists.

### Case 2: Both even and odd numbers exist

For every even number, there is at least one odd number available.

Subtracting an odd number from an even number gives an odd number:

```text
even - odd = odd
```

For every odd number, there is at least one even number available.

Subtracting an even number from an odd number gives an odd number:

```text
odd - even = odd
```

Thus every element can be transformed into an odd number.

Therefore, a valid `nums2` always exists.

Hence, for every valid input:

```text
answer = true
```

So the algorithm is correct.

---

# 📊 Complexity Analysis

## Approach 1

The array is scanned once.

```text
Time Complexity  : O(n)
Space Complexity : O(1)
```

## Approach 2

No iteration or extra data structure is required.

```text
Time Complexity  : O(1)
Space Complexity : O(1)
```

---

# ⚖️ Approach Comparison

| Approach   | Idea                                     | Time | Space |
| ---------- | ---------------------------------------- | ---- | ----- |
| Approach 1 | Check whether even/odd values exist      | O(n) | O(1)  |
| Approach 2 | Directly observe answer is always `true` | O(1) | O(1)  |

### Best practical approach

**Approach 2** is the most optimal implementation because the mathematical observation proves that every input is valid.

### Best explanatory approach

**Approach 1** is useful for understanding why the mixed-parity case is always solvable.

---

# ⚠️ Common Mistakes

### 1. Thinking mixed parity means the answer can be false

This is incorrect.

Mixed parity is actually the case where we can use:

```text
even - odd = odd
odd  - even = odd
```

to make every element odd.

### 2. Trying to construct the complete `nums2`

Construction is unnecessary once the mathematical property is recognized.

### 3. Forgetting that `j != i`

When parities are mixed, an opposite-parity element necessarily exists at another index, so the required `j != i` condition is satisfied.

---

# 🧩 Edge Cases

### Single element

```text
nums1 = [7]
```

The array is already uniform.

```text
true
```

### All even

```text
nums1 = [2, 6, 10, 14]
```

Already uniform.

```text
true
```

### All odd

```text
nums1 = [1, 5, 9, 13]
```

Already uniform.

```text
true
```

### Mixed parity

```text
nums1 = [2, 3, 8, 11]
```

Every element can be transformed into odd.

```text
true
```

---

# 🎯 Key Takeaways

- Parity is determined only by the remainder modulo `2`.
- Same-parity subtraction gives an even number.
- Opposite-parity subtraction gives an odd number.
- If the array already has uniform parity, we can keep it unchanged.
- If both parities exist, every element can be transformed into an odd number.
- Therefore, **every valid input has answer `true`**.
- The most optimized implementation is simply:

```java
return true;
```

---

# 🏷️ Tags

`Array` `Math` `Parity` `Mathematical Observation` `Construction` `Modular Arithmetic` `Easy`

---

## 📌 Recommended GitHub Title

```text
3875. Construct Uniform Parity Array I | Math | Parity | Java & C++
```
