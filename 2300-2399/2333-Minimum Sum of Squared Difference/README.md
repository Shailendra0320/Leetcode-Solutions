# 2333. Minimum Sum of Squared Difference — Greedy Leveling and Binary Search

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

## 🏷️ Tags

`Array, Greedy, Binary Search, Sorting, Math, Optimization, Difference Array, Leveling, Water Filling, Squared Difference, Two Pointers, LeetCode, Medium`

---

# 📌 Problem

You are given two integer arrays `nums1` and `nums2` of equal length, along with two operation limits, `k1` and `k2`.

One operation allows you to increase or decrease an element of `nums1` by `1`, up to `k1` times. You can similarly modify elements of `nums2` up to `k2` times.

Return the minimum possible value of:

\[
\sum\_{i=0}^{n-1}(nums1[i]-nums2[i])^2
\]

Array elements are allowed to become negative. The important part is the absolute difference at each index, not the actual value of either array.

## 🧪 Examples

### Example 1

```text
Input:
nums1 = [1,2,3,4]
nums2 = [2,10,20,19]
k1 = 0
k2 = 0

Output: 579
```

Absolute differences:

```text
[1, 8, 17, 15]
```

No operations are available, so:

```text
1² + 8² + 17² + 15²
= 1 + 64 + 289 + 225
= 579
```

### Example 2

```text
Input:
nums1 = [1,4,10,12]
nums2 = [5,8,6,9]
k1 = 1
k2 = 1

Output: 43
```

Absolute differences:

```text
[4, 4, 4, 3]
```

There are two operations available. Reduce two of the differences from `4` to `3`:

```text
[3, 3, 4, 3]
```

Then:

```text
3² + 3² + 4² + 3²
= 9 + 9 + 16 + 9
= 43
```

---

# 🔍 What Is the Problem Really Asking?

The original arrays can be transformed into one difference array:

```text
diff[i] = abs(nums1[i] - nums2[i])
K = k1 + k2
```

Increasing an element of `nums1` or decreasing the corresponding element of `nums2` has the same effect on its absolute difference. Therefore, the two operation budgets can be combined into one total budget `K`.

Every useful operation decreases one non-zero difference by exactly `1`. We need to minimize:

```text
sum(diff[i] * diff[i])
```

The operation budget can be extremely large, so simulating individual operations is too slow. Instead, we need to reduce groups of differences in batches.

---

# 💡 Core Insight — Reduce the Largest Differences First

Reducing a larger difference by one saves more squared cost than reducing a smaller difference.

\[
x^2-(x-1)^2=2x-1
\]

For example:

```text
Reducing 8 to 7 saves: 8² - 7² = 15
Reducing 3 to 2 saves: 3² - 2² = 5
```

So the optimal strategy always spends an available operation on a currently largest difference.

But `K` can be as large as two billion. We must not run a loop once per operation.

Two efficient approaches are:

1. **Greedy Sorting and Leveling:** lower a group of largest differences to the next level in one batch.
2. **Binary Search on the Final Cap:** find the smallest achievable maximum difference, then spend the remaining operations on values at that cap.

## Preliminary Check

If all differences can be eliminated:

```text
if sum(diff) <= K:
    return 0
```

This is optimal because a sum of squares cannot be negative.

Use a 64-bit integer for the final answer in Java and C++, because the sum of squares may exceed the 32-bit integer range.

---

# 🌳 Approach 1 — Greedy Sorting and Leveling

## Idea

Sort the differences in non-decreasing order:

```text
diff[0] <= diff[1] <= ... <= diff[n-1]
```

Focus on the largest difference and reduce it to the next-largest level. If several values already share that level, lower all of them together.

For example:

```text
diff = [2, 4, 4, 7]
```

Lower the largest value from `7` to `4`. The cost is:

```text
7 - 4 = 3 operations
```

The effective levels become:

```text
[2, 4, 4, 4]
```

Now three values share the highest level `4`. Lowering those three values to `2` would cost:

```text
(4 - 2) * 3 = 6 operations
```

If the budget can pay for a complete level, do so and add one more value to the active group. Otherwise, distribute the remaining operations evenly across this active group.

## State and Formula

At sorted index `i`, suppose the largest `count` values have been leveled to `diff[i]`:

```text
count = n - i
nextLevel = diff[i - 1]    (or 0 when i == 0)
cost = (diff[i] - nextLevel) * count
```

### Case 1: The whole level is affordable

If `K >= cost`, lower the active group to `nextLevel` and update:

```text
K -= cost
```

The next iteration considers a group containing one more value.

### Case 2: The whole level is not affordable

Let:

```text
q = K / count
r = K % count
level = diff[i] - q
```

First lower every active value by `q`. Then spend the remaining `r` operations to lower `r` of those values one additional step.

The final active group contains:

```text
count - r values at level
r values at level - 1
```

So its contribution is:

```text
(count - r) * level² + r * (level - 1)²
```

Add the squares of the untouched lower values to obtain the answer.

---

## 🏗️ Architecture / Flow Diagram — Approach 1

```text
                      Start
                        |
                        v
             diff[i] = abs(nums1[i] - nums2[i])
                        |
                        v
                  K = k1 + k2
                        |
                        v
                  sum(diff) <= K?
                    /         \
                  Yes          No
                   |            |
                   v            v
                return 0     sort diff
                                |
                                v
                       start at largest value
                                |
                                v
                  cost = (level gap) * active count
                                |
                       +--------+--------+
                       |                 |
                   K >= cost          K < cost
                       |                 |
                       v                 v
                 complete level     divide K by count
                       |                 |
                       v                 v
                enlarge active     level top group evenly
                    group          and apply remainder
                       |                 |
                       +--------+--------+
                                |
                                v
                         sum of squares
                                |
                                v
                           final answer
```

## 🔄 Data Flow — Approach 1

```text
Two arrays
   |
   v
Absolute differences
   |
   v
Sort differences
   |
   v
Lower the largest group level by level
   |
   +--> Enough operations for next level
   |        |
   |        v
   |   Consume the batch cost and expand group
   |
   +--> Not enough operations
            |
            v
       q = K / count
       r = K % count
            |
            v
       count-r values -> level
       r values       -> level-1
            |
            v
       Sum squared differences
```

## 🧪 Dry Run — Approach 1

Use Example 2:

```text
nums1 = [1,4,10,12]
nums2 = [5,8,6,9]
K = 2
```

Absolute differences:

```text
[4, 4, 4, 3]
```

After sorting:

```text
[3, 4, 4, 4]
```

The three largest values are at level `4`; the next level is `3`.

```text
active count = 3
level gap = 4 - 3 = 1
cost = 1 * 3 = 3
```

But only `2` operations remain, so the full level cannot be completed.

Distribute the operations:

```text
q = 2 / 3 = 0
r = 2 % 3 = 2
```

Two values decrease from `4` to `3`; one stays at `4`:

```text
[3, 3, 4, 3]
```

The squared sum is:

```text
9 + 9 + 16 + 9 = 43
```

## ✅ Correctness — Approach 1

For a difference `x`, lowering it by one decreases the squared sum by `2x - 1`. This saving is greater for larger values, so an optimal solution must always reduce a currently largest difference before reducing a smaller one.

Sorting orders these values and identifies when the active largest group changes. The algorithm computes the exact operation cost to lower that group to the next level. If the budget covers that cost, lowering the complete group follows the greedy optimum. If it does not, equalizing the remaining reductions across the active group minimizes the squared sum for those remaining operations.

If the budget can reduce every difference to zero, the early check returns the globally minimal answer `0`. Otherwise, the leveling process stops at the first partially affordable level and distributes every remaining operation optimally. Therefore the algorithm returns the minimum squared sum.

## ☕ Java — Approach 1

```text
//Approach-1 (Greedy Sorting and Leveling)
//T.C : O(n log n)
//S.C : O(n)
```

```java
import java.util.*;

class Solution {

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long operations = (long) k1 + k2;
        long total = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
        }

        if (operations >= total) {
            return 0L;
        }

        Arrays.sort(diff);

        for (int i = n - 1; i >= 0; i--) {
            int nextLevel = (i == 0) ? 0 : diff[i - 1];
            long count = n - i;
            long cost = (long) (diff[i] - nextLevel) * count;

            if (operations >= cost) {
                operations -= cost;
            } else {
                long q = operations / count;
                long r = operations % count;
                long level = diff[i] - q;
                long answer = 0L;

                for (int j = 0; j < i; j++) {
                    answer += (long) diff[j] * diff[j];
                }

                answer += (count - r) * level * level;
                answer += r * (level - 1) * (level - 1);

                return answer;
            }
        }

        return 0L;
    }
}
```

## 💻 C++ — Approach 1

```text
//Approach-1 (Greedy Sorting and Leveling)
//T.C : O(n log n)
//S.C : O(n)
```

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {

public:
    long long minSumSquareDiff(vector<int>& nums1, vector<int>& nums2,
                               int k1, int k2) {
        int n = nums1.size();
        vector<int> diff(n);
        long long operations = (long long) k1 + k2;
        long long total = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = abs(nums1[i] - nums2[i]);
            total += diff[i];
        }

        if (operations >= total) {
            return 0LL;
        }

        sort(diff.begin(), diff.end());

        for (int i = n - 1; i >= 0; i--) {
            int nextLevel = (i == 0) ? 0 : diff[i - 1];
            long long count = n - i;
            long long cost = (long long) (diff[i] - nextLevel) * count;

            if (operations >= cost) {
                operations -= cost;
            } else {
                long long q = operations / count;
                long long r = operations % count;
                long long level = diff[i] - q;
                long long answer = 0;

                for (int j = 0; j < i; j++) {
                    answer += 1LL * diff[j] * diff[j];
                }

                answer += (count - r) * level * level;
                answer += r * (level - 1) * (level - 1);

                return answer;
            }
        }

        return 0LL;
    }
};
```

## 🐍 Python — Approach 1

```text
#Approach-1 (Greedy Sorting and Leveling)
#T.C : O(n log n)
#S.C : O(n)
```

```python
class Solution:

    def minSumSquareDiff(self, nums1, nums2, k1: int, k2: int) -> int:
        diff = sorted(abs(a - b) for a, b in zip(nums1, nums2))
        operations = k1 + k2

        if operations >= sum(diff):
            return 0

        n = len(diff)

        for i in range(n - 1, -1, -1):
            next_level = diff[i - 1] if i > 0 else 0
            count = n - i
            cost = (diff[i] - next_level) * count

            if operations >= cost:
                operations -= cost
            else:
                q, r = divmod(operations, count)
                level = diff[i] - q

                answer = sum(x * x for x in diff[:i])
                answer += (count - r) * level * level
                answer += r * (level - 1) * (level - 1)

                return answer

        return 0
```

---

# ⏱️ Complexity — Approach 1

```text
Time:  O(n log n)
Space: O(n)
```

Computing differences is `O(n)`, sorting is `O(n log n)`, and the leveling loop processes the sorted levels without iterating once per operation. The auxiliary difference array takes `O(n)` space.

---

# 🌳 Approach 2 — Binary Search on the Final Difference Cap

## Idea

Instead of sorting, binary-search the largest difference allowed to remain after optimization.

Suppose we choose a cap `cap`. Every difference `d` greater than the cap must be reduced by:

```text
max(d - cap, 0)
```

Therefore, the total cost of enforcing that cap is:

```text
required(cap) = sum(max(d - cap, 0))
```

If:

```text
required(cap) <= K
```

the cap is achievable. Otherwise, it is too small for the available budget.

As `cap` increases, `required(cap)` never increases. This monotonic property lets us binary-search the **smallest feasible cap**.

## After Finding the Cap

Reduce every value greater than `cap` down to `cap`. Let:

```text
remaining = K - operationsUsed
```

There may be a few operations left. Because `cap` is the smallest feasible cap, `remaining` is smaller than the number of differences at `cap`. So we can reduce that many cap-level values one additional step:

```text
cap -> cap - 1
```

One such step reduces the squared sum by:

\[
cap^2-(cap-1)^2=2\cdot cap-1
\]

The implementation performs these final one-step reductions explicitly, then sums the squares.

---

## 🏗️ Architecture / Flow Diagram — Approach 2

```text
                       Start
                         |
                         v
                 Calculate differences
                         |
                         v
                  K = k1 + k2
                         |
                         v
                  sum(diff) <= K?
                    /         \\
                  Yes          No
                   |            |
                   v            v
                return 0     Binary search cap
                                  |
                                  v
                  required(cap) <= K?
                        /            \\
                      Yes             No
                       |               |
                       v               v
                search smaller     search larger
                    cap                 cap
                       \\             /
                        \\           /
                         v           v
                     smallest feasible cap
                                  |
                                  v
                      reduce values to cap
                                  |
                                  v
                   spend leftovers on cap -> cap-1
                                  |
                                  v
                          sum of squares
```

## 🔄 Data Flow — Approach 2

```text
nums1, nums2
     |
     v
absolute difference array
     |
     v
Binary search cap using required(cap)
     |
     v
smallest feasible cap
     |
     v
reduce every diff greater than cap
     |
     v
remaining = K - reductions used
     |
     v
reduce remaining values equal to cap by one
     |
     v
sum of squares
```

## 🧪 Dry Run — Approach 2

Use Example 2:

```text
diff = [4, 4, 4, 3]
K = 2
```

Find the smallest `cap` such that:

```text
sum(max(d - cap, 0)) <= 2
```

### Try `cap = 2`

```text
required(2) = 2 + 2 + 2 + 1 = 7
```

Seven operations are required, so this cap is too small.

### Try `cap = 3`

```text
required(3) = 1 + 1 + 1 + 0 = 3
```

Three operations are required, so this cap is still too small.

### Try `cap = 4`

```text
required(4) = 0
```

The cap is feasible, so the smallest feasible cap is `4`.

We still have two operations. Reduce two of the values at `4` by one:

```text
[4, 4, 4, 3]
     ↓  ↓
[3, 3, 4, 3]
```

The sum is:

```text
9 + 9 + 16 + 9 = 43
```

## ✅ Correctness — Approach 2

For a fixed cap, each difference above the cap must be reduced exactly by `d - cap`; values already below the cap need no operations. Therefore `required(cap)` is the exact minimum number of operations needed to make every difference at most `cap`.

This function is monotonic, so binary search can find the smallest integer cap achievable within `K` operations.

After reaching the cap, if there were enough remaining operations to reduce every value currently at the cap to `cap - 1`, then `cap - 1` would also be feasible. That contradicts the choice of the smallest feasible cap. Consequently, the remaining operations can each reduce one distinct cap-level value by one. Applying them this way preserves the greedy optimality of lowering the largest differences first.

The resulting array is the optimally leveled difference array, so its sum of squares is minimal.

## ☕ Java — Approach 2

```text
//Approach-2 (Binary Search on Final Difference Cap)
//T.C : O(n log M)
//S.C : O(n)
```

```java
class Solution2 {

    private long requiredOperations(int[] diff, int cap) {
        long required = 0;

        for (int d : diff) {
            if (d > cap) {
                required += d - cap;
            }
        }

        return required;
    }

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long operations = (long) k1 + k2;
        long total = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        if (operations >= total) {
            return 0L;
        }

        int low = 0;
        int high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (requiredOperations(diff, mid) <= operations) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int cap = low;
        long remaining = operations;

        for (int i = 0; i < n; i++) {
            if (diff[i] > cap) {
                remaining -= diff[i] - cap;
                diff[i] = cap;
            }
        }

        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] == cap) {
                diff[i]--;
                remaining--;
            }
        }

        long answer = 0L;

        for (int d : diff) {
            answer += (long) d * d;
        }

        return answer;
    }
}
```

## 💻 C++ — Approach 2

```text
//Approach-2 (Binary Search on Final Difference Cap)
//T.C : O(n log M)
//S.C : O(n)
```

```cpp
class Solution2 {

    long long requiredOperations(const vector<int>& diff, int cap) {
        long long required = 0;

        for (int d : diff) {
            if (d > cap) {
                required += d - cap;
            }
        }

        return required;
    }

public:
    long long minSumSquareDiff(vector<int>& nums1, vector<int>& nums2,
                               int k1, int k2) {
        int n = nums1.size();
        vector<int> diff(n);
        long long operations = (long long) k1 + k2;
        long long total = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = abs(nums1[i] - nums2[i]);
            total += diff[i];
            maxDiff = max(maxDiff, diff[i]);
        }

        if (operations >= total) {
            return 0LL;
        }

        int low = 0;
        int high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (requiredOperations(diff, mid) <= operations) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int cap = low;
        long long remaining = operations;

        for (int i = 0; i < n; i++) {
            if (diff[i] > cap) {
                remaining -= diff[i] - cap;
                diff[i] = cap;
            }
        }

        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] == cap) {
                diff[i]--;
                remaining--;
            }
        }

        long long answer = 0;

        for (int d : diff) {
            answer += 1LL * d * d;
        }

        return answer;
    }
};
```

## 🐍 Python — Approach 2

```text
#Approach-2 (Binary Search on Final Difference Cap)
#T.C : O(n log M)
#S.C : O(n)
```

```python
class Solution2:

    def minSumSquareDiff(self, nums1, nums2, k1: int, k2: int) -> int:
        diff = [abs(a - b) for a, b in zip(nums1, nums2)]
        operations = k1 + k2
        total = sum(diff)

        if operations >= total:
            return 0

        def required_operations(cap):
            return sum(max(d - cap, 0) for d in diff)

        low = 0
        high = max(diff)

        while low < high:
            mid = low + (high - low) // 2

            if required_operations(mid) <= operations:
                high = mid
            else:
                low = mid + 1

        cap = low
        remaining = operations

        for i in range(len(diff)):
            if diff[i] > cap:
                remaining -= diff[i] - cap
                diff[i] = cap

        for i in range(len(diff)):
            if remaining == 0:
                break

            if diff[i] == cap:
                diff[i] -= 1
                remaining -= 1

        return sum(d * d for d in diff)
```

---

# ⏱️ Complexity — Approach 2

Let `M` be the maximum initial absolute difference.

```text
Time:  O(n log M)
Space: O(n)
```

Each binary-search iteration evaluates the operation cost in `O(n)`. Binary search uses `O(log M)` iterations, followed by linear leveling and summation. With the given constraints, `M <= 10^5`, so this is efficient.

---

# ⚖️ Approach Comparison

| Feature                      | Greedy Sorting and Leveling     | Binary Search on Final Cap                    |
| ---------------------------- | ------------------------------- | --------------------------------------------- |
| Main idea                    | Lower largest groups in batches | Find the smallest feasible maximum difference |
| Time                         | `O(n log n)`                    | `O(n log M)`                                  |
| Auxiliary space              | `O(n)`                          | `O(n)`                                        |
| Requires sorting             | Yes                             | No                                            |
| Key concept                  | Water-filling / equalization    | Monotonic operation cost                      |
| Simulates each operation     | No                              | No                                            |
| Handles huge `K` efficiently | Yes                             | Yes                                           |
| Best explanation of leveling | ✅                              | Good                                          |

---

# 🧠 Pattern Recognition

When a problem asks you to minimize a sum of squared differences and each operation changes one difference by one unit, think about:

```text
1. Absolute differences
2. Reduce the largest difference first
3. Batch equal levels instead of processing operations one-by-one
4. Binary search a final maximum cap
```

The key identity is:

```text
x² - (x - 1)² = 2x - 1
```

It explains why reducing a larger value gives a greater benefit.

---

# 🔥 Why `k1` and `k2` Can Be Combined

For a pair of values such as:

```text
nums1[i] = 5
nums2[i] = 9
```

the absolute difference is `4`.

Either of these operations reduces the difference by one:

```text
increase nums1[i]
decrease nums2[i]
```

Thus their individual operation budgets have the same role in this optimization:

```text
K = k1 + k2
```

---

# ❌ Common Mistakes

## 1. Running one iteration per operation

`K` may be enormous. A simulation loop that runs `K` times can time out. Always level values in batches or use binary search.

## 2. Reducing the smallest difference first

That produces less improvement in the squared sum. Since:

```text
x² - (x - 1)² = 2x - 1
```

reducing larger values first is optimal.

## 3. Forgetting the all-zero case

If:

```text
sum(diff) <= K
```

all differences can be reduced to zero and the answer is `0`.

## 4. Forgetting leftover operations after finding a cap

After reaching the smallest feasible cap, use the leftover operations to reduce distinct values equal to that cap by one.

## 5. Using 32-bit arithmetic for the final sum

The squared sum can be much larger than an `int` can hold. Use `long` in Java and `long long` in C++.

## 6. Incorrect remainder distribution

After dividing operations across `count` active values:

```text
q = K / count
r = K % count
```

`q` lowers each active value, and `r` lowers `r` of them one additional time. The final group is split between `level` and `level - 1`.

---

# 🎯 Interview Explanation

> First, I compute the absolute difference at every index and combine the two operation budgets into `K = k1 + k2`. Each operation can reduce one difference by one. Since reducing a larger difference gives a larger reduction in squared cost, we should level down the largest differences.
>
> One approach sorts the differences and lowers the largest group to the next level in batches. If the remaining budget cannot complete a level, I distribute it evenly across the current largest group.
>
> Another approach binary-searches the smallest final cap whose total required reductions are at most `K`. I cap each difference at that level, use any remaining operations to lower values equal to the cap by one, and calculate the final sum of squares. Both approaches avoid simulating operations one at a time.

---

# 📝 Quick Revision

## Greedy Sorting and Leveling

```text
diff[i] = abs(nums1[i] - nums2[i])
K = k1 + k2

if sum(diff) <= K:
    return 0

sort(diff)

lower the largest group toward the next level
if a full level cannot be reached:
    q = K / activeCount
    r = K % activeCount

    activeCount-r values -> level
    r values             -> level-1
```

## Binary Search

```text
required(cap) = sum(max(diff[i] - cap, 0))

find the smallest cap such that:
required(cap) <= K

reduce every diff to at most cap
spend remaining operations on cap -> cap-1
```

## Final Answer

```text
sum(diff[i] * diff[i])
```

---

# 🚀 One-Line Insight

> Because reducing larger differences saves more squared cost, optimally level the largest values in batches instead of spending operations one at a time.

---

# ✅ Final Takeaway

Transform the two arrays into one absolute-difference array, combine the operation budgets, then equalize the largest differences as much as possible.

```text
Approach 1 — Greedy Sorting and Leveling
Time  : O(n log n)
Space : O(n)

Approach 2 — Binary Search on Final Cap
Time  : O(n log M)
Space : O(n)
```

Both methods handle very large operation budgets efficiently. Sorting and leveling makes the balancing process intuitive; binary search uses the monotonic number of operations needed to enforce a maximum difference.

---

## References

- [LeetCode 2333 — Minimum Sum of Squared Difference](https://leetcode.com/problems/minimum-sum-of-squared-difference/)
- [LeetCode problem hints](https://leetcode.com/problems/minimum-sum-of-squared-difference/)

The implementation sections above are original explanations and code adaptations for study and review.
