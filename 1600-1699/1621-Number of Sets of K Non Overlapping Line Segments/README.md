# 1621. Number of Sets of K Non-Overlapping Line Segments

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

# 🧩 Problem Statement

There are `n` points placed on a line:

```text
0, 1, 2, 3, ..., n - 1
```

We want to draw exactly `k` line segments.

Each segment is formed by choosing two points:

```text
start < end
```

The chosen segments must be **non-overlapping**.

They are allowed to touch at an endpoint:

```text
[0,2] and [2,4]
```

is valid.

But:

```text
[0,3] and [2,4]
```

is invalid because their interiors overlap.

Return the number of possible sets of exactly `k` non-overlapping segments.

Since the answer can be very large, return it modulo:

```text
1,000,000,007
```

### Link

[LeetCode 1621 — Number of Sets of K Non-Overlapping Line Segments](https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/)

---

# 💡 What Is the Question Really Asking?

The points are fixed:

```text
0 1 2 3 4 5 ...
```

Every segment is just an interval between two selected points.

For example:

```text
0-----2   2---------5
```

represents:

```text
[0,2]
[2,5]
```

The real challenge is:

> How many different collections of exactly `k` intervals can be formed without overlapping?

This is fundamentally a **counting + dynamic programming / combinatorics** problem.

---

# 🏗️ Solution Architecture

```text
                  n ordered points
                         |
                         v
               +--------------------+
               | Choose k segments  |
               +---------+----------+
                         |
                         v
               +--------------------+
               | Segments ordered    |
               | from left to right  |
               +---------+----------+
                         |
             +-----------+-----------+
             |                       |
             v                       v
      DP / State Counting       Combinatorial
                                 Transformation
             |                       |
             +-----------+-----------+
                         |
                         v
                     Answer
```

---

# 🔍 Core Observation

Suppose the `k` selected segments are ordered from left to right:

```text
[a1, b1]
[a2, b2]
...
[ak, bk]
```

Because touching is allowed:

```text
a1 < b1 <= a2 < b2 <= ... <= ak < bk
```

Notice what matters is not the exact geometric shape, but the ordered sequence of endpoints.

This lets us transform the problem into a counting problem.

---

# 1️⃣ Approach 1 — Dynamic Programming

A direct way to think about the problem is to process the points from left to right.

Define:

```text
dp[i][j]
=
number of ways to choose exactly j non-overlapping
segments using points 0...i
```

The key question is:

> What happens to the last point `i`?

There are two possibilities:

```text
1. Point i is not the new right endpoint of a segment.
2. Point i is the right endpoint of the last segment.
```

---

## 🔍 DP Transition

Suppose the last segment starts at point `p` and ends at `i`.

Then all previous `j - 1` segments must be completely contained in:

```text
0...p
```

because the previous last endpoint may equal `p` and touching is allowed.

Therefore:

```text
dp[i][j]
=
dp[i-1][j]
+
dp[0][j-1]
+
dp[1][j-1]
+
...
+
dp[i-1][j-1]
```

The first term means:

```text
Do not use point i as the endpoint of a new segment.
```

The summation counts all ways where the final segment ends at `i`.

---

# 🚀 Prefix-Sum Optimization

The summation:

```text
dp[0][j-1] + dp[1][j-1] + ... + dp[i-1][j-1]
```

would cost `O(n)` every time.

We can maintain:

```text
sum[j]
=
dp[0][j] + dp[1][j] + ... + dp[i-1][j]
```

Then:

```text
dp[i][j]
=
dp[i-1][j] + sum[j-1]
```

This reduces the complexity to:

```text
O(n * k)
```

---

# 🔄 DP Architecture

```text
                      dp[i][j]
                         |
             +-----------+-----------+
             |                       |
             v                       v
        Don't end a           End a new segment
        segment here           at point i
             |                       |
             v                       v
       dp[i-1][j]          sum[j-1] over all
                           valid previous ends
             |                       |
             +-----------+-----------+
                         |
                         v
                    dp[i][j]
```

---

## ✅ Approach 1 — Java

```java
//Approach-1 (DP + Prefix Sum)
//T.C : O(n * k)
//S.C : O(k)

class Solution {
    private static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k) {

        // dp[j] = number of ways for the previous point
        long[] dp = new long[k + 1];

        // sum[j] = dp[0][j] + dp[1][j] + ... for processed points
        long[] sum = new long[k + 1];

        // With zero points, choosing zero segments has one way.
        dp[0] = 1;
        sum[0] = 1;

        for (int i = 1; i < n; i++) {

            // Descending order keeps sum[j - 1]
            // from using the current i.
            for (int j = k; j >= 1; j--) {
                dp[j] = (dp[j] + sum[j - 1]) % MOD;
            }

            // Add current dp[i][j] into the prefix sums.
            for (int j = 0; j <= k; j++) {
                sum[j] += dp[j];

                if (sum[j] >= MOD) {
                    sum[j] -= MOD;
                }
            }
        }

        return (int) dp[k];
    }
}
```

---

## ✅ Approach 1 — C++

```cpp
//Approach-1 (DP + Prefix Sum)
//T.C : O(n * k)
//S.C : O(k)

class Solution {
public:
    static constexpr long long MOD = 1000000007LL;

    int numberOfSets(int n, int k) {

        // dp[j] = number of ways for the previous point
        vector<long long> dp(k + 1, 0);

        // sum[j] = prefix sum of dp[*][j]
        vector<long long> sum(k + 1, 0);

        dp[0] = 1;
        sum[0] = 1;

        for (int i = 1; i < n; i++) {

            // Descending order preserves the previous sum[j - 1].
            for (int j = k; j >= 1; j--) {
                dp[j] = (dp[j] + sum[j - 1]) % MOD;
            }

            // Add current row into prefix sums.
            for (int j = 0; j <= k; j++) {
                sum[j] += dp[j];

                if (sum[j] >= MOD) {
                    sum[j] -= MOD;
                }
            }
        }

        return (int)dp[k];
    }
};
```

---

# 🧪 DP Dry Run

Consider:

```text
n = 3
k = 2
```

Points:

```text
0 1 2
```

The only valid configuration is:

```text
[0,1]
[1,2]
```

because the segments touch at point `1`.

So:

```text
answer = 1
```

The DP eventually produces:

```text
dp[2] = 1
```

---

# 🧪 Another DP Example

Consider:

```text
n = 4
k = 2
```

The formula later tells us the answer is:

```text
C(4 + 2 - 1, 4)
=
C(5,4)
=
5
```

The DP also produces:

```text
dp[2] = 5
```

This gives us a useful cross-check between the dynamic-programming and combinatorial viewpoints.

---

# 2️⃣ Approach 2 — Combinatorial Formula

There is a beautiful mathematical simplification.

For exactly `k` segments, we have:

```text
2k endpoints
```

but endpoints can coincide because touching segments are allowed.

For example:

```text
[0,2]
[2,4]
```

uses endpoint `2` twice.

This means we can transform the problem into selecting positions with repetitions allowed in a structured way.

The final number of valid sets is:

```text
C(n + k - 1, 2k)
```

where:

```text
C(a, b) = a choose b
```

---

# 🔬 Why Does the Formula Appear?

Write the ordered segment endpoints as:

```text
a1 <= b1 <= a2 <= b2 <= ... <= ak <= bk
```

with the additional requirement that every segment has positive length:

```text
ai < bi
```

A standard transformation shifts the endpoint variables so that the strict inequalities become non-strict inequalities over a larger sequence.

After this transformation, the problem becomes equivalent to choosing:

```text
2k
```

positions from:

```text
n + k - 1
```

available positions.

Therefore:

```text
answer = C(n + k - 1, 2k)
```

This is the key combinatorial identity.

---

# 📐 Example of the Formula

Take:

```text
n = 4
k = 2
```

Then:

```text
answer = C(4 + 2 - 1, 4)
       = C(5, 4)
       = 5
```

So there are:

```text
5
```

different ways to choose two non-overlapping segments.

---

# 🔢 Computing the Combination

We need:

```text
C(n + k - 1, 2k)
```

under modulo:

```text
1,000,000,007
```

Because `n` can be large, factorials with modular inverses are convenient.

Using:

```text
C(N, R)
=
N! / (R! (N-R)!)
```

under modulo:

```text
C(N,R)
=
fact[N]
× invFact[R]
× invFact[N-R]
```

where modular inverses are calculated using Fermat's theorem:

```text
a^(MOD-2) mod MOD
```

---

# ✅ Approach 2 — Java

```java
//Approach-2 (Combinatorics + Modular Arithmetic)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
    private static final long MOD = 1_000_000_007L;

    private long power(long base, long exponent) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }

    public int numberOfSets(int n, int k) {

        int N = n + k - 1;
        int R = 2 * k;

        long[] fact = new long[N + 1];
        long[] invFact = new long[N + 1];

        fact[0] = 1;

        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[N] = power(fact[N], MOD - 2);

        for (int i = N; i >= 1; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }

        long answer = fact[N];

        answer = answer * invFact[R] % MOD;
        answer = answer * invFact[N - R] % MOD;

        return (int) answer;
    }
}
```

---

# ✅ Approach 2 — C++

```cpp
//Approach-2 (Combinatorics + Modular Arithmetic)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
public:
    static constexpr long long MOD = 1000000007LL;

    long long power(long long base, long long exponent) {

        long long result = 1;

        while (exponent > 0) {

            if (exponent & 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }

    int numberOfSets(int n, int k) {

        int N = n + k - 1;
        int R = 2 * k;

        vector<long long> fact(N + 1);
        vector<long long> invFact(N + 1);

        fact[0] = 1;

        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[N] = power(fact[N], MOD - 2);

        for (int i = N; i >= 1; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }

        long long answer = fact[N];

        answer = answer * invFact[R] % MOD;
        answer = answer * invFact[N - R] % MOD;

        return (int)answer;
    }
};
```

---

# 🧪 Combinatorial Dry Run

Take:

```text
n = 4
k = 2
```

Formula:

```text
C(n + k - 1, 2k)
=
C(5,4)
=
5
```

Therefore:

```text
answer = 5
```

The five valid sets correspond to the possible ways of distributing the required spacing among the two segments.

---

# 🧠 Correctness Proof — DP Approach

We process points from left to right.

At every point, the state captures all ways to construct the current number of segments.

For each state, exactly one of the following happens:

```text
1. The point does not complete a segment.
2. The point completes an active segment.
3. The point contributes to extending the current segment.
```

Every valid segment set can be represented by one sequence of these decisions.

Because the DP processes coordinates in increasing order, a later segment can never move backward and overlap an earlier segment.

Therefore every valid set is counted once, and no invalid overlapping set is counted.

---

# 🧠 Correctness Proof — Combinatorial Approach

Represent the `k` segments by their ordered endpoints:

```text
a1 < b1 <= a2 < b2 <= ... <= ak < bk
```

The only non-strict inequality is the boundary between consecutive segments because touching is allowed.

By applying a standard shift transformation to remove the `k-1` possible endpoint coincidences, the problem becomes equivalent to selecting `2k` positions from a total of:

```text
n + k - 1
```

positions.

Therefore the number of valid configurations is:

```text
C(n + k - 1, 2k)
```

Hence the combinatorial solution returns exactly the desired count.

---

# ⚠️ Common Mistakes

## 1. Treating touching as overlap

This is valid:

```text
[0,1]
[1,2]
```

The segments only meet at one endpoint.

---

## 2. Using `C(n, 2k)`

The answer is not simply:

```text
C(n, 2k)
```

because endpoints may be shared between adjacent segments.

The correct formula is:

```text
C(n + k - 1, 2k)
```

---

## 3. Forgetting modulo arithmetic

All combinations should be computed modulo:

```text
1,000,000,007
```

---

## 4. Overflow during multiplication

Even after taking modulo, two `long` / `long long` values may be large.

Always multiply using:

```text
(long long)
```

or Java `long`.

---

## 5. Confusing points with segment lengths

The segment:

```text
[2,5]
```

has geometric length:

```text
3
```

but it is determined by endpoints:

```text
2 and 5
```

The problem counts choices of segments, not integer lengths.

---

# 🎯 Interview Thought Process

A strong derivation can go like this:

```text
We have n ordered points.
        ↓
Choose exactly k segments.
        ↓
Segments can touch but not overlap.
        ↓
Order all segment endpoints from left to right.
        ↓
This suggests a DP over position + number of segments.
        ↓
Can we count it mathematically?
        ↓
Yes, the endpoint inequalities can be transformed
into a combinations problem.
        ↓
C(n + k - 1, 2k)
```

If the combinatorial transformation is not obvious in an interview, use the DP formulation first.

---

# 🧩 Pattern Recognition

When you see:

```text
ordered points
+
choose k non-overlapping intervals
+
count configurations
```

think about:

```text
DP on position and number of intervals
```

Then ask:

```text
Can endpoint ordering transform the problem
into a stars-and-bars / combinations problem?
```

This problem is an excellent example of moving between:

```text
Geometry
      ↓
Intervals
      ↓
Ordered endpoints
      ↓
Combinatorics
```

---

# 🔥 Deep Mental Model

Imagine every segment as:

```text
start → end
```

For `k` segments:

```text
start1 → end1
start2 → end2
...
startk → endk
```

Because they are ordered and cannot overlap:

```text
start1 < end1 <= start2 < end2 <= ...
```

So the geometry disappears.

What remains is an ordered inequality-counting problem.

That is the key transformation.

---

# 📊 Complexity Comparison

Let:

```text
n = number of points
k = number of segments
```

| Approach      |    Time |  Space |
| ------------- | ------: | -----: |
| DP            | `O(nk)` | `O(k)` |
| Combinatorics |  `O(n)` | `O(n)` |

The combinatorial solution is faster in asymptotic terms when `k` is much smaller than `n`.

---

# 🏆 Which Approach Should You Prefer?

### Approach 1 — DP

Prefer when:

- You want an intuitive algorithmic solution.
- You want to clearly model the construction process.
- You are still developing the combinatorial insight.

Core pattern:

```text
Position + Number of Segments
```

### Approach 2 — Combinatorics

Prefer when:

- You recognize ordered endpoints.
- You understand the transformation to combinations.
- You want the most compact mathematical solution.

Core formula:

```text
C(n + k - 1, 2k)
```

---

# 📌 Formula Sheet

### Main Formula

```text
Answer = C(n + k - 1, 2k)
```

### Combination

```text
C(N, R)
=
N! / (R! * (N-R)!)
```

Under modulo:

```text
C(N,R)
=
fact[N]
× invFact[R]
× invFact[N-R]
```

### Modular Inverse

Because `MOD` is prime:

```text
a^(-1) ≡ a^(MOD-2) (mod MOD)
```

using fast exponentiation.

---

# 🧪 Important Examples

### Example 1

```text
n = 3
k = 1
```

Choose any pair of points:

```text
C(3,2) = 3
```

Answer:

```text
3
```

---

### Example 2

```text
n = 3
k = 2
```

Only:

```text
[0,1]
[1,2]
```

is possible.

Answer:

```text
1
```

Formula:

```text
C(3 + 2 - 1, 4)
=
C(4,4)
=
1
```

---

### Example 3

```text
n = 4
k = 2
```

Formula:

```text
C(5,4)
=
5
```

Answer:

```text
5
```

---

# 🧠 Why the Formula Is So Beautiful

The original problem sounds geometric:

```text
draw line segments
```

but the final answer is simply:

```text
one binomial coefficient
```

That is a classic competitive-programming transformation:

```text
Geometric constraints
        ↓
Ordered inequalities
        ↓
Combinatorial counting
        ↓
Binomial coefficient
```

---

# 📝 Final Summary

The most important observation is that the `k` segments are naturally ordered from left to right:

```text
a1 < b1 <= a2 < b2 <= ... <= ak < bk
```

This lets us interpret the problem as counting ordered endpoint configurations.

A robust algorithmic solution uses:

```text
DP
```

over:

```text
position × number of segments
```

A deeper mathematical solution transforms the endpoint inequalities into:

```text
C(n + k - 1, 2k)
```

Thus the problem connects:

```text
Dynamic Programming
+
Geometry
+
Intervals
+
Combinatorics
+
Modular Arithmetic
```

---

# 💎 One-Line Insight

> **Because consecutive segments may touch, their ordered endpoints can be transformed into choosing `2k` positions from `n + k - 1` positions, giving `C(n + k - 1, 2k)` valid sets.**

---

# 🧠 Interview Cheat Sheet

```text
n ordered points
        ↓
exactly k segments
        ↓
segments may touch
but cannot overlap
        ↓
order all endpoints
        ↓
a1 < b1 <= a2 < b2 <= ...
        ↓
DP interpretation
        OR
combinatorial transformation
        ↓
C(n + k - 1, 2k)
```

---

## 🏷️ Tags

`Dynamic Programming, Combinatorics, Math, Counting, Geometry, Line Segments, Intervals, Non-overlapping Intervals, Modular Arithmetic, 1D DP, Binomial Coefficient, LeetCode, Medium`
