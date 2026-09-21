# 3524. Find X Value of Array I

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

# 🧩 Problem Statement

You are given an array `nums` of positive integers and a positive integer `k`.

You may remove any prefix and any suffix, as long as:

- the removed prefix and suffix do not overlap,
- the remaining array is non-empty,
- either removed part may be empty.

For every remainder:

```text
x = 0, 1, ..., k - 1
```

count how many operations leave a remaining product whose remainder modulo `k` is `x`.

Return:

```text
result[x]
```

for every `x`.

The important constraints are:

```text
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= 5
```

[LeetCode 3524 — Find X Value of Array I](https://leetcode.com/problems/find-x-value-of-array-i/)

---

# 💡 What Is the Question Really Asking?

The prefix/suffix operation looks more complicated than it really is.

Suppose:

```text
nums = [1, 2, 3, 4, 5]
```

and we want to keep:

```text
[2, 3, 4]
```

We simply remove:

```text
prefix = [1]
suffix = [5]
```

Therefore:

> **Every allowed operation corresponds to exactly one non-empty contiguous subarray.**

So the original problem can be rewritten as:

```text
Consider every non-empty subarray.
        ↓
Compute product % k.
        ↓
Count how many subarrays produce each remainder.
```

This is the key transformation.

---

# 🧠 Core Observation

A direct enumeration of all subarrays costs:

```text
O(n²)
```

but:

```text
n <= 100000
```

so we need something better.

The crucial constraint is:

```text
k <= 5
```

There are only `k` possible product remainders:

```text
0, 1, ..., k-1
```

Therefore we do not need to store the actual product.

We only need:

```text
product % k
```

This leads directly to a DP with only `k` states.

---

# 🏗️ Solution Architecture

```text
                         nums
                           |
                           v
                +---------------------+
                | Process nums[i]     |
                +----------+----------+
                           |
                           v
                     num % k
                           |
             +-------------+-------------+
             |                           |
             v                           v
      Start new subarray          Extend old subarray
             |                           |
             v                           v
        newDp[numMod]          newDp[(r*numMod)%k]
             |                           |
             +-------------+-------------+
                           |
                           v
                       newDp[]
                           |
                           v
                  Add to result[]
                           |
                           v
                     dp = newDp
                           |
                           v
                       Next num
```

---

# 📐 Why Product Modulo Is Enough

For any integers `a` and `b`:

```text
(a * b) % k
=
((a % k) * (b % k)) % k
```

Therefore, if an existing subarray has:

```text
product % k = r
```

and we append:

```text
num
```

then:

```text
newRemainder
=
(r * (num % k)) % k
```

We never need the full product.

This also prevents overflow.

---

# 1️⃣ Approach 1 — Brute Force

The most direct solution is to enumerate every non-empty subarray.

For every starting index:

```text
left = 0 ... n-1
```

maintain a running product modulo `k` while extending `right`.

For every `right`:

```text
productMod =
productMod * (nums[right] % k) % k
```

Then:

```text
result[productMod]++
```

This is correct because every non-empty subarray corresponds to one valid operation.

However, there are:

```text
n(n+1)/2
```

subarrays, so the time complexity is:

```text
O(n²)
```

which is too slow for `n = 100000`.

---

## 🔄 Brute Force Flow

```text
                 Start
                   |
                   v
              Choose left
                   |
                   v
             product = 1
                   |
                   v
             Choose right
                   |
                   v
       product = product * nums[right] % k
                   |
                   v
         result[product]++
                   |
                   v
             Next right
                   |
                   v
              Next left
```

---

## ✅ Java — Approach 1

```java
//Approach-1 (Brute Force)
//T.C : O(n^2)
//S.C : O(k)

class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];

        for (int left = 0; left < nums.length; left++) {

            long product = 1;

            for (int right = left; right < nums.length; right++) {

                product = product * (nums[right] % k) % k;

                result[(int) product]++;
            }
        }

        return result;
    }
}
```

---

## ✅ C++ — Approach 1

```cpp
//Approach-1 (Brute Force)
//T.C : O(n^2)
//S.C : O(k)

class Solution {
public:
    vector<long long> resultArray(vector<int>& nums, int k) {

        vector<long long> result(k, 0);

        for (int left = 0; left < nums.size(); left++) {

            long long product = 1;

            for (int right = left; right < nums.size(); right++) {

                product = product * (nums[right] % k) % k;

                result[product]++;
            }
        }

        return result;
    }
};
```

---

# 2️⃣ Approach 2 — DP on Product Remainders

This is the optimal solution.

Define:

```text
dp[r]
```

as:

> Number of non-empty subarrays ending at the previous index whose product modulo `k` equals `r`.

Now process the current element:

```text
num
```

and calculate:

```text
numMod = num % k
```

We build:

```text
newDp[]
```

for all subarrays ending at the current index.

There are exactly two ways a current subarray can be formed.

---

# 🔹 Case 1 — Start a New Subarray

The one-element subarray:

```text
[num]
```

has remainder:

```text
num % k
```

so:

```text
newDp[numMod] += 1
```

---

# 🔹 Case 2 — Extend an Existing Subarray

Suppose an old subarray has:

```text
product % k = r
```

Appending `num` gives:

```text
(r * numMod) % k
```

Therefore:

```text
newDp[(r * numMod) % k] += dp[r]
```

for every:

```text
r = 0 ... k-1
```

---

# 🔹 Accumulate Into the Final Answer

Every subarray represented by `newDp` is a valid remaining subarray.

Therefore:

```text
result[r] += newDp[r]
```

Then:

```text
dp = newDp
```

and continue.

---

# 🔄 Approach 2 Flowchart

```text
                     nums[i]
                        |
                        v
                  numMod = num % k
                        |
             +----------+----------+
             |                     |
             v                     v
       Start [num]          Extend previous
             |                     |
             v                     v
       newDp[numMod]       newR = (r*numMod)%k
             |                     |
             +----------+----------+
                        |
                        v
                     newDp[]
                        |
                        v
              result += newDp
                        |
                        v
                   dp = newDp
                        |
                        v
                    Next num
```

---

## ✅ Java — Approach 2

```java
//Approach-2 (DP on Product Remainders)
//T.C : O(n * k)
//S.C : O(k)

class Solution2 {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];

        // dp[r] = number of subarrays ending at the
        // previous position with product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            int numMod = num % k;

            long[] newDp = new long[k];

            // Start a new subarray with only num.
            newDp[numMod] = 1;

            // Extend every previous subarray.
            for (int r = 0; r < k; r++) {

                int newRemainder =
                    (int) ((long) r * numMod % k);

                newDp[newRemainder] += dp[r];
            }

            // Accumulate subarrays ending here.
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
    }
}
```

---

## ✅ C++ — Approach 2

```cpp
//Approach-2 (DP on Product Remainders)
//T.C : O(n * k)
//S.C : O(k)

class Solution2 {
public:
    vector<long long> resultArray(vector<int>& nums, int k) {

        vector<long long> result(k, 0);

        // dp[r] = number of subarrays ending at the
        // previous position with product % k == r
        vector<long long> dp(k, 0);

        for (int num : nums) {

            int numMod = num % k;

            vector<long long> newDp(k, 0);

            // Start a new subarray.
            newDp[numMod] = 1;

            // Extend every previous subarray.
            for (int r = 0; r < k; r++) {

                int newRemainder =
                    (int) (1LL * r * numMod % k);

                newDp[newRemainder] += dp[r];
            }

            // Add all current-ending subarrays to the answer.
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
    }
};
```

---

# 🧪 Detailed Dry Run

Consider:

```text
nums = [1, 2, 3]
k = 3
```

All non-empty subarrays are:

```text
[1]
[2]
[3]
[1,2]
[2,3]
[1,2,3]
```

Their product remainders are:

```text
[1]       → 1
[2]       → 2
[3]       → 0

[1,2]     → 2
[2,3]     → 0

[1,2,3]   → 0
```

Therefore:

```text
remainder 0 → 3
remainder 1 → 1
remainder 2 → 2
```

Final:

```text
[3,1,2]
```

---

# 🔍 DP Dry Run

Start:

```text
dp    = [0,0,0]
result = [0,0,0]
```

## Process `1`

```text
numMod = 1
```

Start:

```text
newDp = [0,1,0]
```

Accumulate:

```text
result = [0,1,0]
dp     = [0,1,0]
```

This represents:

```text
[1]
```

---

## Process `2`

```text
numMod = 2
```

Start new:

```text
[2] → remainder 2
```

So:

```text
newDp[2] = 1
```

Extend previous:

```text
[1] → [1,2]

old remainder = 1
new remainder = (1 * 2) % 3
              = 2
```

Thus:

```text
newDp[2] = 2
```

These are:

```text
[2]
[1,2]
```

Accumulate:

```text
result = [0,1,2]
```

---

## Process `3`

```text
numMod = 0
```

Start:

```text
[3] → 0
```

Extend previous subarrays:

```text
[2]     → 0
[1,2]   → 0
```

because:

```text
anything * 0 % 3 = 0
```

So:

```text
newDp = [3,0,0]
```

These correspond to:

```text
[3]
[2,3]
[1,2,3]
```

Final:

```text
result = [3,1,2]
```

---

# 🧠 Why Every Subarray Is Counted Exactly Once

Take any non-empty subarray:

```text
[l, r]
```

Look at its final element:

```text
nums[r]
```

When processing `nums[r]`, exactly one of two cases applies:

### If:

```text
l == r
```

the subarray is created by:

```text
newDp[numMod] = 1
```

### If:

```text
l < r
```

then:

```text
[l, r-1]
```

was already represented in `dp`.

Appending:

```text
nums[r]
```

creates:

```text
[l, r]
```

Therefore every subarray is generated exactly once.

Since every operation corresponds to exactly one subarray, every operation is counted exactly once.

---

# 🔑 Why the Original Operation Equals a Subarray

Suppose the remaining subarray is:

```text
nums[l...r]
```

Then the removed parts are:

```text
prefix = nums[0...l-1]
suffix = nums[r+1...n-1]
```

These do not overlap because:

```text
l <= r
```

and the remaining array is non-empty.

Conversely, every legal prefix/suffix removal leaves exactly some:

```text
nums[l...r]
```

Therefore:

```text
Allowed operation
       ⇕
Non-empty contiguous subarray
```

This bijection is the first major insight of the problem.

---

# 🧮 Why We Never Calculate the Full Product

The values can be as large as:

```text
10^9
```

and a subarray can contain up to:

```text
10^5
```

elements.

The actual product would become far too large.

But modulo multiplication lets us work entirely with small values:

```text
(a * b) % k
=
((a % k) * (b % k)) % k
```

So only:

```text
0 ... k-1
```

matter.

Since:

```text
k <= 5
```

there are at most five states.

---

# 🧠 Why `k <= 5` Is the Main Hint

The array size is huge:

```text
n <= 100000
```

but the modulus is tiny:

```text
k <= 5
```

This is a classic state-compression signal.

Instead of remembering:

```text
many different products
```

we remember only:

```text
k different remainders
```

So:

```text
Huge input dimension
+
Tiny state dimension
        ↓
Dynamic Programming
```

---

# ⚠️ Common Mistakes

## 1. Forgetting the length-1 subarray

For every `num`, we must create:

```text
[num]
```

using:

```text
newDp[num % k] = 1
```

---

## 2. Tracking the actual product

Never let the product grow directly.

Always work modulo `k`.

---

## 3. Using `int` for the answer

The number of subarrays can be approximately:

```text
n(n+1)/2
```

For:

```text
n = 100000
```

that is around:

```text
5,000,050,000
```

which exceeds signed 32-bit integer range.

So Java should return:

```text
long[]
```

and C++ should use:

```text
vector<long long>
```

The official problem constraints make this important. citeturn758379search0turn513297search0

---

## 4. Updating `dp` in place

The current value should transform the states from the previous position.

Use:

```text
newDp
```

then:

```text
dp = newDp
```

This keeps the recurrence clean and avoids accidentally reusing the current element multiple times.

---

# 🧩 Pattern Recognition

Whenever you see:

```text
Count subarrays
+
Property can be summarized by a small state
```

think:

```text
DP over subarrays ending at current index
```

Here the small state is:

```text
product % k
```

so:

```text
dp[remainder]
```

is the natural state.

This same idea appears in many:

```text
Modulo DP
Subarray Counting
State Compression
Product/Sum Remainder
```

problems.

---

# 🔥 Deep Mental Model

Do not think:

```text
There are O(n²) subarrays.
```

Think:

```text
All subarrays ending here
        ↓
Group them by remainder
        ↓
Only k groups
```

For every new element:

```text
old groups
   ↓
multiply each remainder by num % k
   ↓
new groups
```

This compresses an enormous number of subarrays into at most:

```text
k <= 5
```

states.

---

# 📌 DP State Cheat Sheet

### State

```text
dp[r]
=
number of subarrays ending at previous index
with product % k == r
```

### Current value

```text
numMod = num % k
```

### Start new

```text
newDp[numMod] += 1
```

### Extend

```text
newR = (r * numMod) % k
newDp[newR] += dp[r]
```

### Global answer

```text
result[r] += newDp[r]
```

### Move forward

```text
dp = newDp
```

---

# 📊 Complexity Comparison

Let:

```text
n = nums.length
k <= 5
```

| Approach     |    Time |  Space |
| ------------ | ------: | -----: |
| Brute Force  | `O(n²)` | `O(k)` |
| Remainder DP | `O(nk)` | `O(k)` |

Since `k` is at most `5`, the optimal solution is effectively:

```text
O(n)
```

for the given constraints.

---

# 🏆 Which Approach Should You Prefer?

### Approach 1 — Brute Force

Use it for:

```text
Understanding
Verification
First-principles derivation
```

but it is too slow for the real constraints.

### Approach 2 — DP on Remainders

Use it for:

```text
Final solution
Interview
Large input
```

The key insight is:

```text
n is large
k is tiny
```

so compress by:

```text
product % k
```

---

# 🧪 Important Edge Cases

## `k = 1`

Every product has remainder:

```text
0
```

so:

```text
result[0]
```

contains the number of all non-empty subarrays:

```text
n(n+1)/2
```

---

## Single Element

For:

```text
nums = [7]
k = 3
```

only subarray:

```text
[7]
```

has remainder:

```text
7 % 3 = 1
```

so:

```text
result = [0,1,0]
```

---

## Element Divisible by `k`

If:

```text
num % k = 0
```

then every subarray containing that element has remainder:

```text
0
```

because:

```text
r * 0 % k = 0
```

---

# 🎯 Interview Thought Process

```text
Prefix/suffix removals
        ↓
Remaining array = one subarray
        ↓
Count every non-empty subarray
        ↓
Need product % k
        ↓
Actual product is huge
        ↓
Only track remainder
        ↓
k <= 5
        ↓
dp[remainder]
        ↓
Start new subarray
or extend old subarray
        ↓
Accumulate answer
        ↓
O(nk)
```

---

# 🚀 Why the Optimal DP Is Fast

A naive solution treats every subarray separately.

The DP groups many subarrays together.

For a fixed ending position:

```text
thousands of possible subarrays
```

become:

```text
at most k remainder groups
```

Therefore each element requires only:

```text
O(k)
```

work.

Total:

```text
O(nk)
```

Since:

```text
k <= 5
```

this is effectively linear.

---

# 📝 Final Summary

The first major observation is:

```text
Removing a non-overlapping prefix and suffix
leaves exactly one non-empty subarray.
```

Therefore, the problem is really asking us to count every non-empty subarray according to:

```text
product % k
```

The second major observation is:

```text
k <= 5
```

so we can store only the remainder of each subarray product.

Define:

```text
dp[r]
```

as the number of subarrays ending at the previous index with product remainder `r`.

For each `num`:

```text
newDp[num % k] += 1

newDp[(r * (num % k)) % k] += dp[r]
```

Then accumulate:

```text
result += newDp
```

Final complexity:

```text
Time  : O(nk)
Space : O(k)
```

---

# 💎 One-Line Insight

> **Every allowed operation leaves one non-empty subarray, and because `k <= 5`, we can count all such subarrays by their product remainder using `k` DP states.**

---

# 🧠 Interview Cheat Sheet

```text
Allowed operation
        ↓
Remaining part = non-empty subarray
        ↓
Count subarrays by product % k
        ↓
Actual product too large
        ↓
Track only remainder
        ↓
dp[r] = count of subarrays ending here
        ↓
Start:
newDp[num % k] += 1

Extend:
newDp[(r*num)%k] += dp[r]
        ↓
result += newDp
        ↓
O(nk) time
O(k) space
```

---

## 🏷️ Tags

`Array, Dynamic Programming, Math, Modular Arithmetic, Subarray, Counting, Product, Remainder, State Compression, 1D DP, Prefix and Suffix, LeetCode, Medium`
