# 1288. Remove Covered Intervals

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode (Main):** https://leetcode.com/u/Shailu03/

**LeetCode (Alternate):** https://leetcode.com/u/ShailendraLeetcode03/

---

## 🔗 Problem Link

[LeetCode - 1288. Remove Covered Intervals](https://leetcode.com/problems/remove-covered-intervals/)

---

# 📝 Problem Statement

You are given an array of intervals:

```text
intervals[i] = [lᵢ, rᵢ]
```

where the interval represents the half-open range:

```text
[lᵢ, rᵢ)
```

An interval:

```text
[a, b)
```

is covered by another interval:

```text
[c, d)
```

if and only if:

```text
c <= a
and
b <= d
```

In simple words, one interval covers another when it starts no later and ends no earlier.

Return the number of intervals remaining after removing every interval that is covered by another interval.

### Example

```text
Input:
intervals = [[1,4],[3,6],[2,8]]

Output:
2
```

Because:

```text
[3,6]
```

is completely inside:

```text
[2,8]
```

so `[3,6]` is removed.

The current constraints are:

```text
1 <= intervals.length <= 1000
intervals[i].length == 2
0 <= lᵢ < rᵢ <= 10^5
All intervals are unique
```

citeturn308710search0

---

# 💡 Understanding Coverage

Consider:

```text
A = [2,8)
B = [3,6)
```

Visual:

```text
A:  |--------------------------|
    2                          8

B:       |-------------|
         3             6
```

Since:

```text
2 <= 3
6 <= 8
```

`B` is completely inside `A`.

Therefore:

```text
[3,6) is covered by [2,8)
```

---

# 🚨 Coverage Is Not the Same as Overlap

Consider:

```text
A = [1,5)
B = [4,8)
```

Visual:

```text
A:  |---------|
    1         5

B:           |---------|
             4         8
```

These intervals overlap, but neither covers the other.

For `B` to be covered by `A`:

```text
1 <= 4    ✅
5 >= 8    ❌
```

So `B` is not covered.

Important:

```text
OVERLAP != COVERAGE
```

The smaller interval must be completely contained inside the larger interval.

---

# ⭐ Core Observation

For one interval to cover another, we need:

```text
coverStart <= currentStart
coverEnd   >= currentEnd
```

That means we need to compare both endpoints.

A very useful way to simplify this is:

```text
Sort intervals by start ascending.
```

Once sorted, every previously processed interval has:

```text
previousStart <= currentStart
```

So the only remaining question is:

> Does some previous interval extend far enough to cover the current interval?

That can be answered by tracking:

```text
maxEnd
```

where:

```text
maxEnd = maximum end value among previously processed intervals
```

---

# 🔥 Important Sorting Rule

Sort using:

```text
1. Start ascending
2. End descending when starts are equal
```

So:

```text
[start ↑, end ↓]
```

---

# 🧠 Why Equal Starts Must Use End Descending

Consider:

```text
[1,4]
[1,3]
```

Clearly:

```text
[1,3]
```

is covered by:

```text
[1,4]
```

because:

```text
1 <= 1
3 <= 4
```

Therefore `[1,4]` must appear first.

Correct order:

```text
[1,4]
[1,3]
```

This is achieved by sorting equal starts by:

```text
end descending
```

---

## ❌ Wrong Tie-Breaking

If we sort equal starts by end ascending:

```text
[1,3]
[1,4]
```

then we process the smaller interval first.

That can make us incorrectly count `[1,3]` before discovering `[1,4]`.

So the comparator must be:

```text
start ascending
end descending
```

This tie-breaker is the most important implementation detail in the solution. citeturn308710search1turn308710search2

---

# 📐 Sorting Example

Before sorting:

```text
[2,8]
[1,4]
[3,6]
[1,3]
[2,5]
```

After sorting:

```text
[1,4]
[1,3]
[2,8]
[2,5]
[3,6]
```

Notice:

```text
[1,4] comes before [1,3]
[2,8] comes before [2,5]
```

because when starts are equal, the interval with the larger end comes first.

---

# 🚀 Main Greedy Idea

After sorting, process intervals from left to right.

Maintain:

```text
maxEnd
```

For each current interval:

```text
[start, end]
```

there are two cases.

## Case 1: `end <= maxEnd`

Then the current interval is covered.

Why?

Because after sorting:

```text
some previous start <= current start
```

and because:

```text
maxEnd >= current end
```

some previous interval has an end large enough to cover it.

Therefore:

```text
current interval is covered
```

Do not count it.

---

## Case 2: `end > maxEnd`

No previous interval reaches as far right as the current interval.

So the current interval cannot be covered by any previous interval.

Therefore:

```text
keep it
```

and update:

```text
maxEnd = end
```

---

# 📊 Main Algorithm Diagram

```text
                  Original Intervals
                         |
                         v
              Sort [start ↑, end ↓]
                         |
                         v
                 Scan left -> right
                         |
                         v
                  Track maxEnd
                         |
                +--------+--------+
                |                 |
       currentEnd <= maxEnd   currentEnd > maxEnd
                |                 |
                v                 v
             COVERED          NOT COVERED
                |                 |
                |                 v
                |              answer++
                |              maxEnd=end
                |                 |
                +--------+--------+
                         |
                         v
                    Next Interval
                         |
                         v
                  Return answer
```

---

# 🚀 Approach 1: Sorting + `maxEnd`

This is the cleanest and recommended solution.

## Algorithm

```text
1. Sort intervals:
       start ascending
       end descending when starts are equal

2. Initialize:
       maxEnd = -1
       answer = 0

3. Traverse the sorted intervals.

4. For each interval:
       if end <= maxEnd:
           it is covered
           ignore it
       else:
           it is not covered
           answer++
           maxEnd = end

5. Return answer.
```

---

# 🧪 Detailed Dry Run — Example 1

```text
intervals = [[1,4],[3,6],[2,8]]
```

Expected:

```text
2
```

citeturn308710search0

### Step 1: Sort

```text
[1,4]
[2,8]
[3,6]
```

---

### Step 2: Process `[1,4]`

Initially:

```text
maxEnd = -1
answer = 0
```

Current end:

```text
4
```

Check:

```text
4 <= -1
```

False.

So `[1,4]` is not covered.

Update:

```text
answer = 1
maxEnd = 4
```

---

### Step 3: Process `[2,8]`

Current:

```text
end = 8
```

Check:

```text
8 <= 4
```

False.

Therefore `[2,8]` is not covered.

Update:

```text
answer = 2
maxEnd = 8
```

---

### Step 4: Process `[3,6]`

Current:

```text
end = 6
```

Check:

```text
6 <= 8
```

True.

Therefore `[3,6]` is covered.

Do not increment the answer.

Final:

```text
answer = 2
```

---

# 📐 Example 1 Visualization

```text
[1,4]
|---------|

[2,8]
|-------------------|

[3,6]
   |---------|
```

The third interval lies completely inside `[2,8]`.

Therefore:

```text
[3,6] -> removed
```

Remaining:

```text
[1,4]
[2,8]
```

Answer:

```text
2
```

---

# 🧪 Detailed Dry Run — Equal Starts

Consider:

```text
intervals = [[1,4],[1,3],[2,5]]
```

Sorted correctly:

```text
[1,4]
[1,3]
[2,5]
```

### `[1,4]`

```text
4 > -1
```

Keep:

```text
answer = 1
maxEnd = 4
```

### `[1,3]`

```text
3 <= 4
```

Covered.

Keep:

```text
answer = 1
```

### `[2,5]`

```text
5 > 4
```

Not covered.

Update:

```text
answer = 2
maxEnd = 5
```

Final:

```text
2
```

---

# 🚨 Why the Tie-Breaker Matters

Consider:

```text
[1,3]
[1,4]
```

If sorted incorrectly:

```text
[1,3]
[1,4]
```

then:

```text
maxEnd = 3
```

after the first interval.

The second interval has:

```text
4 > 3
```

so we count it too.

That would produce:

```text
answer = 2
```

even though:

```text
[1,3]
```

is covered by:

```text
[1,4]
```

Correct ordering:

```text
[1,4]
[1,3]
```

allows the coverage to be detected immediately.

---

# ⭐ Why `end <= maxEnd` Is Sufficient

This is the most important proof idea.

After sorting:

```text
previousStart <= currentStart
```

For the current interval to be covered, we only need a previous interval whose end is at least the current end.

Since:

```text
maxEnd
```

is the maximum end among all previous intervals:

```text
maxEnd >= currentEnd
```

means some previous interval reaches at least as far right.

Therefore:

```text
previousStart <= currentStart
previousEnd >= currentEnd
```

and the current interval is covered.

So:

```text
end <= maxEnd
```

is exactly the condition we need.

---

# 🧠 Why `maxEnd` Works Better Than Only Comparing With the Immediately Previous Interval

Consider:

```text
[1,10]
[2,5]
[3,8]
```

After sorting:

```text
[1,10]
[2,5]
[3,8]
```

If we only compare `[3,8]` with the immediately previous interval `[2,5]`, we might think:

```text
8 > 5
```

so it is not covered.

But it is actually covered by:

```text
[1,10]
```

because:

```text
1 <= 3
8 <= 10
```

The correct method remembers the farthest right boundary:

```text
maxEnd = 10
```

Then:

```text
8 <= 10
```

correctly identifies `[3,8]` as covered.

This is why tracking the **maximum end seen so far** is stronger than tracking only the last interval.

---

# 🧪 Nested Intervals Example

Consider:

```text
[1,10]
[2,9]
[3,8]
[4,7]
[5,6]
```

Visualization:

```text
[1,10]
|-------------------------------|

  [2,9]
  |-------------------------|

    [3,8]
    |-------------------|

      [4,7]
      |---------------|

        [5,6]
        |---------|
```

Processing:

```text
[1,10] -> keep
[2,9]  -> covered
[3,8]  -> covered
[4,7]  -> covered
[5,6]  -> covered
```

Final:

```text
answer = 1
```

---

# 🧪 No Covered Intervals

Consider:

```text
[[1,2],[3,4],[5,6]]
```

Sorted:

```text
[1,2]
[3,4]
[5,6]
```

Process:

```text
2 > -1  -> keep
4 > 2   -> keep
6 > 4   -> keep
```

Answer:

```text
3
```

Nothing is covered.

---

# 🧪 Partial Overlap

Consider:

```text
[[1,5],[4,8]]
```

Visual:

```text
[1,5]
|---------|

    [4,8]
    |---------|
```

These overlap, but neither contains the other.

Therefore:

```text
answer = 2
```

This reinforces:

```text
overlap != coverage
```

---

# 🧪 Same End Point

Consider:

```text
[[1,5],[3,5]]
```

The second interval is covered:

```text
1 <= 3
5 <= 5
```

So:

```text
[3,5]
```

is removed.

This shows that equality is allowed in the coverage condition.

---

# ✅ Java — Approach 1

```java
//Approach-1 (Sorting + Maximum End)
//T.C : O(n log n)
//S.C : O(1) extra space apart from sorting

class Solution {

    public int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(
            intervals,
            (a, b) -> {

                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }

                return Integer.compare(b[1], a[1]);
            }
        );

        int maxEnd = -1;
        int answer = 0;

        for (int[] interval : intervals) {

            if (interval[1] > maxEnd) {

                answer++;
                maxEnd = interval[1];
            }
        }

        return answer;
    }
}
```

---

# ✅ C++ — Approach 1

```cpp
//Approach-1 (Sorting + Maximum End)
//T.C : O(n log n)
//S.C : O(1) extra space apart from sorting

class Solution {
public:
    int removeCoveredIntervals(vector<vector<int>>& intervals) {

        sort(
            intervals.begin(),
            intervals.end(),
            [](const vector<int>& a, const vector<int>& b) {

                if (a[0] != b[0]) {
                    return a[0] < b[0];
                }

                return a[1] > b[1];
            }
        );

        int maxEnd = -1;
        int answer = 0;

        for (auto& interval : intervals) {

            if (interval[1] > maxEnd) {

                answer++;
                maxEnd = interval[1];
            }
        }

        return answer;
    }
};
```

---

# 🚀 Approach 2: Sort + Explicit Coverage Check

We can also keep the previous maximum-reaching interval explicitly.

The idea is still:

```text
Sort first.
Then maintain the interval with the largest end seen so far.
```

Instead of counting directly from `maxEnd`, we can think in terms of:

```text
current start
current end
maxStart
maxEnd
```

The important invariant remains:

```text
maxEnd = farthest right boundary among processed intervals
```

So the practical implementation is still best written using the `maxEnd` technique.

---

# 📐 Approach 2 Conceptual Diagram

```text
                 Sorted Interval
                       |
                       v
              +------------------+
              | previous coverage |
              | boundary = maxEnd |
              +------------------+
                       |
                       v
                 currentEnd
                       |
            +----------+----------+
            |                     |
       <= maxEnd              > maxEnd
            |                     |
            v                     v
       Current is             Current is
        covered              independent
            |                     |
            v                     v
          ignore             answer++
                                  |
                                  v
                              maxEnd=end
```

Approach 2 is therefore conceptually the same greedy invariant, expressed more explicitly.

---

# ✅ Correctness Proof

We prove that the sorting + `maxEnd` algorithm returns the correct number of remaining intervals.

## Lemma 1: Sorting establishes start order

After sorting:

```text
start ascending
```

every earlier interval has:

```text
previousStart <= currentStart
```

If starts are equal, the larger end comes first because of the descending tie-breaker.

---

## Lemma 2: If `currentEnd <= maxEnd`, current interval is covered

`maxEnd` is the maximum end among previously processed intervals.

So some previous interval has:

```text
previousEnd = maxEnd
```

Since sorting guarantees:

```text
previousStart <= currentStart
```

and:

```text
maxEnd >= currentEnd
```

we have:

```text
previousStart <= currentStart
previousEnd >= currentEnd
```

Therefore the current interval is covered.

---

## Lemma 3: If `currentEnd > maxEnd`, current interval cannot be covered

Assume some previous interval covered the current interval.

Then it would need:

```text
previousEnd >= currentEnd
```

But `maxEnd` is the largest previous end, so:

```text
maxEnd >= currentEnd
```

would have to be true.

This contradicts:

```text
currentEnd > maxEnd
```

Therefore the current interval is not covered by any previous interval.

So it must remain.

---

## Theorem

For every interval:

```text
currentEnd <= maxEnd
```

means it is safely removable.

And:

```text
currentEnd > maxEnd
```

means it must remain.

Thus every covered interval is removed and every non-covered interval is retained.

Therefore the returned count is correct.

---

# 📊 Complexity Analysis

Let:

```text
n = intervals.length
```

### Sorting

```text
O(n log n)
```

### Single scan

```text
O(n)
```

Therefore:

```text
Total Time Complexity = O(n log n)
```

Extra variables:

```text
maxEnd
answer
```

require:

```text
O(1)
```

extra space apart from sorting.

So:

```text
Time Complexity  : O(n log n)
Space Complexity : O(1) extra
```

---

# 🆚 Approach Comparison

| Approach           | Idea                                             | Time       | Extra Space | Recommendation |
| ------------------ | ------------------------------------------------ | ---------- | ----------- | -------------- |
| Brute Force        | Compare every interval with every other interval | O(n²)      | O(1)        | ❌             |
| Sorting + `maxEnd` | Greedy scan after sorting                        | O(n log n) | O(1)        | ⭐ Best        |
| Explicit reference | Same greedy invariant                            | O(n log n) | O(1)        | Good           |

---

# ⚠️ Common Mistakes

## 1. Sorting only by start

This is not enough.

For equal starts:

```text
[1,3]
[1,4]
```

the larger interval must come first.

Correct:

```text
start ascending
end descending
```

---

## 2. Sorting end ascending for equal starts

Wrong:

```text
[1,3]
[1,4]
```

Correct:

```text
[1,4]
[1,3]
```

---

## 3. Confusing overlap and coverage

```text
[1,5]
[4,8]
```

overlap, but no coverage.

---

## 4. Comparing only with the previous interval

A current interval can be covered by an older interval, not necessarily the immediately previous one.

Use:

```text
maxEnd
```

instead of only:

```text
previousEnd
```

---

## 5. Forgetting equality

Coverage uses:

```text
<=
```

not:

```text
<
```

For example:

```text
[1,5]
[3,5]
```

The second is covered.

---

# 🧩 Edge Cases

### One interval

```text
[[1,5]]
```

Answer:

```text
1
```

---

### Completely nested

```text
[[1,10],[2,9],[3,8]]
```

Answer:

```text
1
```

---

### Separate intervals

```text
[[1,2],[3,4],[5,6]]
```

Answer:

```text
3
```

---

### Partial overlap

```text
[[1,5],[4,8]]
```

Answer:

```text
2
```

---

### Same start

```text
[[1,4],[1,3]]
```

Sorted:

```text
[1,4]
[1,3]
```

Answer:

```text
1
```

---

### Same end

```text
[[1,5],[3,5]]
```

The second interval is covered.

Answer:

```text
1
```

---

# 🎤 Interview Explanation

A strong interview explanation is:

> I sort the intervals by starting point ascending, and for equal starts I sort by ending point descending. This ensures that if one interval can cover another with the same start, the covering interval appears first. Then I scan from left to right while maintaining the maximum end seen so far. If the current end is less than or equal to this maximum, the current interval is covered. Otherwise, it cannot be covered by any previous interval, so I count it and update the maximum end. Sorting takes `O(n log n)` and the scan takes `O(n)`.

---

# 🔄 Complete Problem-Solving Pattern

```text
                    Intervals
                        |
                        v
             Sort by start ascending
                        |
                        v
        Equal start -> end descending
                        |
                        v
                  Scan left -> right
                        |
                        v
                  Maintain maxEnd
                        |
              +---------+---------+
              |                   |
              v                   v
       end <= maxEnd        end > maxEnd
              |                   |
              v                   v
          Covered              Keep
              |               answer++
              |               maxEnd=end
              +---------+---------+
                        |
                        v
                  Next interval
                        |
                        v
                 Final answer
```

---

# 🧠 Reusable Interval Pattern

This problem teaches a classic:

```text
SORT + GREEDY + BOUNDARY
```

pattern.

When an interval problem asks about containment, start by asking:

```text
Can I sort by one endpoint
so that the other endpoint becomes easy to track?
```

For this problem:

```text
start -> ascending
end   -> descending for ties
```

Then:

```text
track maximum end
```

This converts a pairwise containment problem into a linear scan after sorting.

---

# 📌 Most Important Insight

Remember this one line:

```text
Sort by start ascending, and for equal starts by end descending.
```

Then:

```text
if currentEnd <= maxEnd:
    covered
else:
    keep
    maxEnd = currentEnd
```

That is the heart of LeetCode 1288.

---

# 🏁 Final Optimal Code

## Java

```java
//Approach-1 (Sorting + Maximum End)
//T.C : O(n log n)
//S.C : O(1) extra space apart from sorting

class Solution {

    public int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(
            intervals,
            (a, b) -> {

                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }

                return Integer.compare(b[1], a[1]);
            }
        );

        int maxEnd = -1;
        int answer = 0;

        for (int[] interval : intervals) {

            if (interval[1] > maxEnd) {

                answer++;
                maxEnd = interval[1];
            }
        }

        return answer;
    }
}
```

## C++

```cpp
//Approach-1 (Sorting + Maximum End)
//T.C : O(n log n)
//S.C : O(1) extra space apart from sorting

class Solution {
public:
    int removeCoveredIntervals(vector<vector<int>>& intervals) {

        sort(
            intervals.begin(),
            intervals.end(),
            [](const vector<int>& a, const vector<int>& b) {

                if (a[0] != b[0]) {
                    return a[0] < b[0];
                }

                return a[1] > b[1];
            }
        );

        int maxEnd = -1;
        int answer = 0;

        for (auto& interval : intervals) {

            if (interval[1] > maxEnd) {

                answer++;
                maxEnd = interval[1];
            }
        }

        return answer;
    }
};
```

---

# 🎯 Key Takeaways

```text
1. Coverage means complete containment.

2. Overlap is not enough.

3. Sort by:
       start ascending
       end descending for equal starts

4. Track:
       maxEnd

5. If:
       currentEnd <= maxEnd

   then current interval is covered.

6. Otherwise:
       keep it
       update maxEnd.

7. Equal starts require the descending end tie-breaker.

8. Comparing only with the immediately previous interval
   is not enough; use maxEnd.

9. Time Complexity:
       O(n log n)

10. Extra Space:
       O(1)
```

---

# 🏷️ Tags

`Array` `Intervals` `Sorting` `Greedy` `Interval Scheduling` `Range` `Medium`

---

## 📌 Recommended GitHub Title

```text
1288. Remove Covered Intervals | Greedy | Sorting | Java & C++
```
