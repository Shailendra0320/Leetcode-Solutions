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

# 3116. Kth Smallest Amount With Single Denomination Combination

## Problem Link

🔗 [LeetCode 3116 - Kth Smallest Amount With Single Denomination Combination](https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/)

---

# Intuition

We are given an array `coins` containing different coin denominations and an integer `k`.

For every coin, we can create an infinite sequence of multiples.

For example:

```text
coins = [2, 5]
```

Multiples of `2` are:

```text
2, 4, 6, 8, 10, 12, 14, ...
```

Multiples of `5` are:

```text
5, 10, 15, 20, 25, ...
```

We need to find the:

```text
k-th smallest distinct amount
```

The word **distinct** is very important.

For example:

```text
coins = [2, 5]
```

The value:

```text
10
```

is a multiple of both `2` and `5`.

But `10` should be counted only once.

Therefore, we cannot simply merge or count all multiples independently.

---

The key observation is:

Instead of directly finding the k-th valid amount, we can ask:

```text
How many valid distinct amounts are <= x?
```

If we can efficiently calculate this count, then we can use:

```text
Binary Search on Answer
```

because the count is monotonic.

---

# Main Idea

The solution combines four important concepts:

```text
Bitmask
   +
LCM / GCD
   +
Inclusion-Exclusion
   +
Binary Search
```

The overall idea is:

```text
                         coins[]
                            |
                            ▼
                    Generate subsets
                            |
                            ▼
                    Calculate subset LCM
                            |
                            ▼
                 Inclusion-Exclusion
                            |
                            ▼
                    count(x)
                            |
                            ▼
                    Binary Search
                            |
                            ▼
             Smallest x where count(x) >= k
                            |
                            ▼
                         Answer
```

---

# Important Observation 1 — Counting Multiples

For one coin `c`, the number of multiples of `c` that are less than or equal to `x` is:

```text
x / c
```

For example:

```text
x = 20
c = 5
```

Then:

```text
20 / 5 = 4
```

The multiples are:

```text
5, 10, 15, 20
```

So there are `4` multiples.

---

# Important Observation 2 — Duplicate Multiples

Consider:

```text
coins = [3, 6]
x = 12
```

Multiples of `3`:

```text
3, 6, 9, 12
```

Count:

```text
12 / 3 = 4
```

Multiples of `6`:

```text
6, 12
```

Count:

```text
12 / 6 = 2
```

If we simply add:

```text
4 + 2 = 6
```

then:

```text
6
12
```

are counted twice.

Therefore, we need to subtract the common multiples.

---

# Important Observation 3 — LCM

The common multiples of two or more numbers are multiples of their:

```text
LCM (Least Common Multiple)
```

For:

```text
3 and 6
```

we have:

```text
LCM(3, 6) = 6
```

Therefore, common multiples of `3` and `6` are:

```text
6, 12, 18, 24, ...
```

The number of common multiples less than or equal to `x` is:

```text
x / LCM
```

For:

```text
x = 12
```

we get:

```text
12 / 6 = 2
```

Therefore:

```text
count = 4 + 2 - 2
      = 4
```

The distinct valid values are:

```text
3, 6, 9, 12
```

---

# Important Observation 4 — Inclusion-Exclusion

When there are multiple coins, we use the:

```text
Inclusion-Exclusion Principle
```

For:

```text
coins = [2, 3, 5]
```

the formula becomes:

```text
count(x)

= x/2
+ x/3
+ x/5

- x/LCM(2,3)
- x/LCM(2,5)
- x/LCM(3,5)

+ x/LCM(2,3,5)
```

The pattern is:

```text
1 selected coin  → +
2 selected coins → -
3 selected coins → +
4 selected coins → -
...
```

So:

```text
Odd number of selected coins → Add

Even number of selected coins → Subtract
```

---

# Approaches

We can solve the problem using two approaches:

1. **Binary Search + On-Demand Inclusion-Exclusion**
2. **Binary Search + Precomputed Subset LCM and Signs**

The second approach is preferred because all subset LCMs and signs are calculated only once.

---

# Approach 1 — Binary Search + On-Demand Inclusion-Exclusion

## Idea

We perform binary search over the possible answer.

For every value `mid`, we calculate:

```text
count(mid)
```

where:

```text
count(mid)
```

means:

```text
Number of distinct valid amounts <= mid
```

If:

```text
count(mid) >= k
```

then `mid` can contain the k-th smallest valid amount.

Therefore, we try to find a smaller answer:

```text
high = mid - 1
```

Otherwise:

```text
count(mid) < k
```

means `mid` is too small.

So:

```text
low = mid + 1
```

---

# Why Binary Search Works

The function:

```text
count(x)
```

is monotonic.

As `x` increases, the number of valid amounts cannot decrease.

For example:

```text
x = 5   → count = 3
x = 8   → count = 5
x = 10  → count = 6
x = 15  → count = 9
```

Therefore, the condition:

```text
count(x) >= k
```

has the following form:

```text
False False False False True True True True
                       ↑
                First valid value
```

Binary Search can find this first valid value efficiently.

---

# Bitmask Representation

Suppose:

```text
coins = [2, 3, 5]
```

There are:

```text
2^3 = 8
```

possible subsets.

We represent every subset using a bitmask.

For example:

```text
101
```

means:

```text
coin 0 → selected → 2
coin 1 → not selected
coin 2 → selected → 5
```

Therefore:

```text
subset = {2, 5}
```

The empty subset:

```text
000
```

is ignored.

---

# Subset Enumeration

For every mask:

```text
1
2
3
...
2^n - 1
```

we determine which coins are selected.

For every selected coin:

```text
currentLCM = lcm(currentLCM, coin)
```

Then:

```text
selectedCoins = number of selected coins
```

If:

```text
selectedCoins % 2 == 1
```

we add:

```text
x / currentLCM
```

Otherwise, we subtract it.

---

# Approach 1 Visualization

```text
                         Candidate x
                              |
                              ▼
                      Generate subsets
                              |
                              ▼
                     Select coins
                              |
                              ▼
                       Calculate LCM
                              |
                              ▼
                           x / LCM
                              |
                    ┌─────────┴─────────┐
                    │                   │
                    ▼                   ▼
             Odd subset size     Even subset size
                    │                   │
                    ▼                   ▼
                  +count              -count
                    │                   │
                    └─────────┬─────────┘
                              ▼
                   Inclusion-Exclusion
                              |
                              ▼
                         count(x)
                              |
                              ▼
                      Compare with k
```

---

# Detailed Dry Run — Approach 1

Consider:

```text
coins = [2, 5]
k = 7
```

The distinct valid amounts are:

```text
2, 4, 5, 6, 8, 10, 12, 14, ...
```

Therefore:

```text
Answer = 12
```

Suppose Binary Search checks:

```text
mid = 10
```

---

## Step 1 — Multiples of 2

```text
2, 4, 6, 8, 10
```

Count:

```text
10 / 2 = 5
```

---

## Step 2 — Multiples of 5

```text
5, 10
```

Count:

```text
10 / 5 = 2
```

---

## Step 3 — Common Multiples

```text
LCM(2,5) = 10
```

Common multiples:

```text
10
```

Count:

```text
10 / 10 = 1
```

---

## Step 4 — Inclusion-Exclusion

```text
count(10)
=
10/2
+
10/5
-
10/10
```

Therefore:

```text
count(10)
=
5 + 2 - 1
=
6
```

But:

```text
k = 7
```

and:

```text
6 < 7
```

Therefore `10` is too small.

We search right:

```text
low = 11
```

---

## Step 5 — Check 12

For:

```text
x = 12
```

Multiples of `2`:

```text
2, 4, 6, 8, 10, 12
```

Count:

```text
12 / 2 = 6
```

Multiples of `5`:

```text
5, 10
```

Count:

```text
12 / 5 = 2
```

Common multiples:

```text
12 / LCM(2,5)
=
12 / 10
=
1
```

Therefore:

```text
count(12)
=
6 + 2 - 1
=
7
```

Now:

```text
count(12) >= k
```

So `12` is a valid candidate.

Binary Search eventually returns:

```text
12
```

---

# Approach 1 — Correctness

For every subset of coins, we count numbers divisible by all selected coins using their LCM.

Inclusion-Exclusion ensures that:

```text
Numbers counted multiple times
```

are correctly added and subtracted so that every distinct valid amount is counted exactly once.

Therefore:

```text
count(x)
```

is exactly the number of distinct valid amounts less than or equal to `x`.

Since `count(x)` is monotonic, Binary Search finds the smallest value satisfying:

```text
count(x) >= k
```

That value is exactly the k-th smallest valid amount.

---

# Approach 1 — Complexity

Let:

```text
n = coins.length
```

There are:

```text
2^n
```

subsets.

For every subset, we inspect up to `n` coins.

Therefore, one counting operation requires:

```text
O(n * 2^n)
```

Binary Search performs:

```text
O(log(k * minCoin))
```

iterations.

Therefore:

```text
Time Complexity:
O(n * 2^n * log(k * minCoin))
```

Space:

```text
Space Complexity:
O(1)
```

excluding recursion or constant auxiliary variables.

---

# Approach 1 — Java Code

```java
// Approach-1 (Binary Search + On-Demand Inclusion-Exclusion)
// T.C : O(n * 2^n * log(k * minCoin))
// S.C : O(1)

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;

        long minCoin = coins[0];

        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long low = 1;
        long high = minCoin * (long) k;

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (countMultiples(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long countMultiples(long target, int[] coins) {
        int n = coins.length;
        int numSubsets = 1 << n;

        long totalCount = 0;

        for (int mask = 1; mask < numSubsets; mask++) {
            long currentLCM = 1;
            int selectedCoins = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentLCM = lcm(currentLCM, coins[i]);
                    selectedCoins++;
                }
            }

            long contribution = target / currentLCM;

            if ((selectedCoins & 1) == 1) {
                totalCount += contribution;
            } else {
                totalCount -= contribution;
            }
        }

        return totalCount;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
```

---

# Approach 2 — Binary Search + Precomputed Subset LCM

## Idea

In Approach 1, every Binary Search iteration recalculates:

```text
LCM of every subset
```

But the coin array never changes.

Therefore, the LCM of a subset is always the same.

For example:

```text
coins = [2, 3, 5]
```

The LCM of:

```text
{2,5}
```

will always be:

```text
10
```

There is no reason to calculate it again for every Binary Search iteration.

So we precompute:

```text
lcms[mask]
```

and:

```text
signs[mask]
```

once.

Then every `count(x)` operation becomes much faster.

---

# Precomputing Subset LCM

For every mask, we find its least significant set bit.

In Java:

```java
Integer.numberOfTrailingZeros(mask)
```

gives the index of that bit.

Then we remove that bit:

```java
int prevMask =
    mask ^ (1 << leastSetBit);
```

The current subset can be represented as:

```text
prevMask + one new coin
```

Therefore:

```text
LCM(mask)
=
LCM(LCM(prevMask), newCoin)
```

---

# Example of Subset LCM Calculation

Suppose:

```text
coins = [2, 3, 5]
```

Consider:

```text
mask = 111
```

The least significant set bit is:

```text
bit 0
```

Therefore:

```text
prevMask = 111 ^ 001
         = 110
```

`110` represents:

```text
{3,5}
```

Its LCM is:

```text
LCM(3,5) = 15
```

Now add coin `2`:

```text
LCM(15,2) = 30
```

Therefore:

```text
lcms[111] = 30
```

---

# Precomputing Signs

The Inclusion-Exclusion sign depends on the number of selected coins.

```text
1 selected coin  → +
2 selected coins → -
3 selected coins → +
4 selected coins → -
```

So while constructing a mask from `prevMask`:

```text
signs[mask] = -signs[prevMask]
```

This automatically produces the correct Inclusion-Exclusion sign.

---

# Subset Table

For:

```text
coins = [2, 3, 5]
```

we get:

| Mask  | Subset    | LCM  | Sign |
| ----- | --------- | ---- | ---- |
| `001` | `{2}`     | `2`  | `+`  |
| `010` | `{3}`     | `3`  | `+`  |
| `011` | `{2,3}`   | `6`  | `-`  |
| `100` | `{5}`     | `5`  | `+`  |
| `101` | `{2,5}`   | `10` | `-`  |
| `110` | `{3,5}`   | `15` | `-`  |
| `111` | `{2,3,5}` | `30` | `+`  |

Therefore:

```text
count(x)
=
x/2
+ x/3
+ x/5
- x/6
- x/10
- x/15
+ x/30
```

---

# Approach 2 Visualization

```text
                         coins[]
                            |
                            ▼
                    Generate all masks
                            |
                            ▼
                   Find least set bit
                            |
                            ▼
                       prevMask
                            |
                            ▼
                  Calculate subset LCM
                            |
                            ▼
                     Store lcms[mask]
                            |
                            ▼
                   Calculate subset sign
                            |
                            ▼
                    Store signs[mask]
                            |
                            ▼
                    Binary Search
                            |
                            ▼
                       count(mid)
                            |
                            ▼
                sign × (mid / LCM)
                            |
                            ▼
                 Compare with k
```

---

# Detailed Dry Run — Approach 2

Consider:

```text
coins = [2, 3, 5]
```

There are:

```text
2^3 = 8
```

subsets.

The empty subset is ignored.

---

## Mask 001

Selected coin:

```text
2
```

Therefore:

```text
LCM = 2
Sign = +
```

---

## Mask 010

Selected coin:

```text
3
```

Therefore:

```text
LCM = 3
Sign = +
```

---

## Mask 011

Selected coins:

```text
2, 3
```

Therefore:

```text
LCM(2,3) = 6
```

Two coins are selected, so:

```text
Sign = -
```

---

## Mask 101

Selected coins:

```text
2, 5
```

Therefore:

```text
LCM(2,5) = 10
```

Two coins are selected:

```text
Sign = -
```

---

## Mask 111

Selected coins:

```text
2, 3, 5
```

Therefore:

```text
LCM(2,3,5) = 30
```

Three coins are selected:

```text
Sign = +
```

---

# Counting After Precomputation

Once `lcms[]` and `signs[]` are available, we simply calculate:

```java
totalCount += signs[mask] * (target / lcms[mask]);
```

For example:

```text
target = 30
coins = [2,3,5]
```

Then:

```text
30/2  = 15
30/3  = 10
30/5  = 6

30/6  = 5
30/10 = 3
30/15 = 2

30/30 = 1
```

Therefore:

```text
count(30)
=
15 + 10 + 6
- 5 - 3 - 2
+ 1
```

So:

```text
count(30) = 22
```

---

# Approach 2 — Binary Search

The smallest possible valid amount is at least:

```text
1
```

So:

```text
low = 1
```

For the upper bound, consider the smallest coin.

Every multiple of the smallest coin is valid.

Therefore, the k-th valid amount can never be greater than:

```text
minCoin * k
```

So:

```java
long high = minCoin * (long) k;
```

---

If:

```text
count(mid) >= k
```

then `mid` is large enough:

```text
high = mid - 1;
```

Otherwise:

```text
low = mid + 1;
```

At the end:

```text
low
```

is the smallest value satisfying:

```text
count(low) >= k
```

---

# Approach 2 Flowchart

```text
                         Start
                           |
                           ▼
                Precompute subset LCMs
                           |
                           ▼
                  Precompute signs
                           |
                           ▼
                    low = 1
                    high = minCoin * k
                           |
                           ▼
                    low <= high ?
                     /          \
                   Yes           No
                    |             |
                    ▼             ▼
                   mid          Answer
                    |
                    ▼
               count(mid)
                    |
                    ▼
             count(mid) >= k ?
                /          \
              Yes           No
               |             |
               ▼             ▼
        high = mid - 1   low = mid + 1
               |             |
               └──────┬──────┘
                      |
                      ▼
                   Repeat
```

---

# Approach 2 — Correctness

For every subset, the LCM represents numbers divisible by every selected coin.

The Inclusion-Exclusion sign determines whether the subset contribution should be added or subtracted.

Therefore, every valid amount is counted exactly once.

Thus:

```text
count(x)
```

correctly represents the number of distinct valid amounts `<= x`.

Since `count(x)` is monotonic, Binary Search finds the smallest value for which:

```text
count(x) >= k
```

That value is the k-th smallest valid amount.

---

# Approach 2 — Complexity

Let:

```text
n = coins.length
```

There are:

```text
2^n
```

subsets.

The subset LCMs and signs are precomputed once.

Each Binary Search iteration processes:

```text
2^n
```

subsets.

Binary Search requires:

```text
O(log(k * minCoin))
```

iterations.

Therefore:

```text
Time Complexity:
O(2^n * log(k * minCoin))
```

plus the one-time subset precomputation.

Space:

```text
Space Complexity:
O(2^n)
```

because we store:

```text
lcms[]
signs[]
```

---

# Approach 2 — Java Code

```java
// Approach-2 (Binary Search + Precomputed Subset LCM)
// T.C : O(2^n * log(k * minCoin))
// S.C : O(2^n)

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int numSubsets = 1 << n;

        long[] lcms = new long[numSubsets];
        int[] signs = new int[numSubsets];

        lcms[0] = 1;
        signs[0] = -1;

        for (int mask = 1; mask < numSubsets; mask++) {
            int leastSetBit =
                Integer.numberOfTrailingZeros(mask);

            int prevMask =
                mask ^ (1 << leastSetBit);

            if (prevMask == 0) {
                lcms[mask] =
                    coins[leastSetBit];

                signs[mask] = 1;
            } else {
                lcms[mask] =
                    lcm(
                        lcms[prevMask],
                        coins[leastSetBit]
                    );

                signs[mask] =
                    -signs[prevMask];
            }
        }

        long minCoin = coins[0];

        for (int coin : coins) {
            minCoin =
                Math.min(minCoin, coin);
        }

        long low = 1;
        long high =
            minCoin * (long) k;

        long result = high;

        while (low <= high) {
            long mid =
                low + (high - low) / 2;

            if (countMultiples(
                    mid,
                    numSubsets,
                    lcms,
                    signs
                ) >= k) {

                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private long countMultiples(
        long target,
        int numSubsets,
        long[] lcms,
        int[] signs
    ) {
        long totalCount = 0;

        for (int mask = 1;
             mask < numSubsets;
             mask++) {

            totalCount +=
                signs[mask] *
                (target / lcms[mask]);
        }

        return totalCount;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    private long lcm(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        return (a / gcd(a, b)) * b;
    }
}
```

---

# C++ Solution

```cpp
// Approach-2 (Binary Search + Precomputed Subset LCM)
// T.C : O(2^n * log(k * minCoin))
// S.C : O(2^n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long findKthSmallest(
        vector<int>& coins,
        int k
    ) {
        int n = coins.size();
        int numSubsets = 1 << n;

        vector<long long> lcms(numSubsets);
        vector<int> signs(numSubsets);

        lcms[0] = 1;
        signs[0] = -1;

        for (int mask = 1;
             mask < numSubsets;
             mask++) {

            int leastSetBit =
                __builtin_ctz(mask);

            int prevMask =
                mask ^ (1 << leastSetBit);

            if (prevMask == 0) {
                lcms[mask] =
                    coins[leastSetBit];

                signs[mask] = 1;
            } else {
                lcms[mask] =
                    lcm(
                        lcms[prevMask],
                        (long long)coins[leastSetBit]
                    );

                signs[mask] =
                    -signs[prevMask];
            }
        }

        long long minCoin = coins[0];

        for (int coin : coins) {
            minCoin =
                min(minCoin, (long long)coin);
        }

        long long low = 1;
        long long high =
            minCoin * k;

        long long result = high;

        while (low <= high) {
            long long mid =
                low + (high - low) / 2;

            if (countMultiples(
                    mid,
                    numSubsets,
                    lcms,
                    signs
                ) >= k) {

                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

private:
    long long gcd(
        long long a,
        long long b
    ) {
        while (b != 0) {
            long long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    long long lcm(
        long long a,
        long long b
    ) {
        return (a / gcd(a, b)) * b;
    }

    long long countMultiples(
        long long target,
        int numSubsets,
        const vector<long long>& lcms,
        const vector<int>& signs
    ) {
        long long totalCount = 0;

        for (int mask = 1;
             mask < numSubsets;
             mask++) {

            totalCount +=
                signs[mask] *
                (target / lcms[mask]);
        }

        return totalCount;
    }
};
```

---

# Approach Comparison

| Feature          | Approach 1                          | Approach 2                     |
| ---------------- | ----------------------------------- | ------------------------------ |
| Main Technique   | Binary Search + Inclusion-Exclusion | Binary Search + Precomputation |
| LCM Calculation  | Repeated                            | Precomputed                    |
| Sign Calculation | Repeated                            | Precomputed                    |
| Count Complexity | `O(n × 2^n)`                        | `O(2^n)`                       |
| Binary Search    | Yes                                 | Yes                            |
| Space Complexity | `O(1)`                              | `O(2^n)`                       |
| Implementation   | Simple                              | Optimized                      |
| Recommended      | Good                                | **Preferred**                  |

---

# Why Approach 2 Is Better

Approach 1 recalculates the LCM for every subset during every Binary Search iteration.

For example:

```text
Binary Search iteration 1
    ↓
Calculate subset LCMs

Binary Search iteration 2
    ↓
Calculate subset LCMs again

Binary Search iteration 3
    ↓
Calculate subset LCMs again

...
```

This is unnecessary because the coin array does not change.

Approach 2 performs:

```text
Calculate subset LCMs
        ↓
      Once
        ↓
Store them
        ↓
Reuse during Binary Search
```

Therefore, Approach 2 reduces the work performed during every `count(x)` operation.

---

# Important Formula

For every non-empty subset:

```text
Contribution =
sign × floor(x / LCM(subset))
```

Therefore:

```text
count(x)
=
Σ sign × floor(x / LCM(subset))
```

The final answer is:

```text
Smallest x such that:

count(x) >= k
```

---

# Edge Cases

## Case 1 — Only One Coin

For:

```text
coins = [5]
```

valid amounts are:

```text
5, 10, 15, 20, ...
```

Therefore:

```text
answer = 5 * k
```

The algorithm handles this naturally.

---

## Case 2 — Duplicate Coins

Consider:

```text
coins = [2, 2]
```

Both denominations produce the same multiples.

Inclusion-Exclusion removes duplicate counting.

---

## Case 3 — One Coin Is a Multiple of Another

Consider:

```text
coins = [3, 6]
```

Every multiple of `6` is already a multiple of `3`.

The LCM:

```text
LCM(3,6) = 6
```

allows Inclusion-Exclusion to remove duplicate values.

---

## Case 4 — Large Answer

The answer can be large.

Therefore, Java uses:

```text
long
```

and C++ uses:

```text
long long
```

for calculations involving:

```text
LCM
low
high
mid
count
answer
```

---

# Overflow-Safe LCM

Instead of:

```text
a * b / gcd(a,b)
```

we calculate:

```text
(a / gcd(a,b)) * b
```

This is safer because division happens before multiplication.

Java:

```java
private long lcm(long a, long b) {
    return (a / gcd(a, b)) * b;
}
```

C++:

```cpp
long long lcm(long long a, long long b) {
    return (a / gcd(a, b)) * b;
}
```

---

# Why We Do Not Generate All Amounts

A brute-force approach might try:

```text
1
2
3
4
5
6
...
```

and check whether every number is divisible by one of the coins.

This is inefficient because the answer can be very large.

Instead, we ask:

```text
How many valid values are <= x?
```

This allows us to use Binary Search.

The transformation is:

```text
Find k-th valid amount
        ↓
Count valid amounts <= x
        ↓
Binary Search x
```

This is the core optimization.

---

# Overall Algorithm

```text
1. Let n = coins.length.

2. Generate all non-empty subsets using bitmasks.

3. Calculate the LCM of every subset.

4. Calculate the Inclusion-Exclusion sign of every subset.

5. Find the minimum coin.

6. Set:

       low = 1
       high = minCoin * k

7. Perform Binary Search.

8. For every mid:

       Calculate count(mid).

9. If:

       count(mid) >= k

   search the left half.

10. Otherwise:

       search the right half.

11. Return the smallest valid value.
```

---

# Complete Algorithm Diagram

```text
                         LeetCode 3116
                              |
                              ▼
                 K-th Smallest Valid Amount
                              |
                              ▼
                       Count(x) Function
                              |
                              ▼
                  Inclusion-Exclusion
                              |
                              ▼
                    Subset LCM Calculation
                              |
                              ▼
                         count(x)
                              |
                              ▼
                    Binary Search on x
                              |
                              ▼
                  count(x) >= k ?
                     /           \
                   Yes            No
                    |              |
                    ▼              ▼
               Search Left    Search Right
                    |              |
                    └──────┬───────┘
                           |
                           ▼
                Smallest Valid Value
                           |
                           ▼
                         Answer
```

---

# Complexity Diagram

```text
                         n coins
                            |
                            ▼
                       2^n subsets
                            |
              ┌─────────────┴─────────────┐
              │                           │
              ▼                           ▼
         Approach 1                  Approach 2
              │                           │
              ▼                           ▼
       O(n × 2^n)                  O(2^n)
       per count                   per count
              │                           │
              └─────────────┬─────────────┘
                            ▼
                     Binary Search
                            |
                            ▼
                  O(log(k × minCoin))
```

---

# Final Complexity Comparison

```text
┌─────────────────────────────────────┬──────────────────────────────┬──────────────┐
│ Approach                            │ Time                         │ Space        │
├─────────────────────────────────────┼──────────────────────────────┼──────────────┤
│ Binary Search + On-Demand           │ O(n × 2^n × log(k × minCoin))│ O(1)         │
│ Inclusion-Exclusion                 │                              │              │
├─────────────────────────────────────┼──────────────────────────────┼──────────────┤
│ Binary Search + Precomputed LCM     │ O(2^n × log(k × minCoin))   │ O(2^n)       │
│ and Signs                            │                              │              │
└─────────────────────────────────────┴──────────────────────────────┴──────────────┘
```

---

# Key Takeaways

The main concepts used in this problem are:

```text
1. Binary Search on Answer

2. Inclusion-Exclusion Principle

3. Bitmask Subset Enumeration

4. LCM

5. GCD

6. Monotonic Counting Function

7. Precomputation

8. Overflow-Safe Arithmetic
```

The most important idea is:

```text
Do not generate the k-th amount directly.

Instead:

Count how many valid amounts <= x
                 ↓
          Binary Search x
```

---

# Final Summary

```text
                    LeetCode 3116
                          |
                          ▼
             K-th Smallest Valid Amount
                          |
                          ▼
                 Binary Search
                          |
                          ▼
                     count(x)
                          |
                          ▼
               Inclusion-Exclusion
                          |
                          ▼
                  Subset Enumeration
                          |
                          ▼
                     LCM / GCD
                          |
                          ▼
              Distinct Valid Amounts
                          |
                          ▼
              First x where count(x) >= k
                          |
                          ▼
                       Answer
```

The preferred solution combines:

```text
Bitmask
   +
LCM / GCD
   +
Inclusion-Exclusion
   +
Precomputation
   +
Binary Search
```

to efficiently find the k-th smallest distinct valid amount.

---

# Tags

`Array` `Math` `Binary Search` `Number Theory` `Bit Manipulation` `Bitmask` `LCM` `GCD` `Inclusion-Exclusion` `Precomputation` `LeetCode`
