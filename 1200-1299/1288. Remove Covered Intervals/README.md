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

# 1288. Remove Covered Intervals

## Tags

```text
Greedy
Sorting
Intervals
Array
Comparator
Java
C++
```

---

# Intuition

We need to remove every interval that is completely covered by another interval.

An interval

```text
[a, b]
```

is covered by

```text
[c, d]
```

if

```text
c ≤ a

and

b ≤ d
```

Instead of comparing every pair of intervals,

we first

```text
Sort
```

the intervals in a clever way.

---

# Key Observation

Sort intervals by

```text
Start Increasing

↓

End Decreasing
```

Why?

If two intervals have the same starting point,

placing the larger interval first guarantees that

the smaller interval becomes covered.

Example

```text
[1,8]

[1,5]

[2,4]

[3,6]
```

After sorting

```text
[1,8]

[1,5]

[2,4]

[3,6]
```

Now we only need to check

```text
Current End

≤

Maximum End Seen
```

---

# Approaches

1. Greedy + Sorting (Optimal)

---

# Approach 1 — Greedy + Sorting

## Idea

Sort all intervals.

Maintain

```text
Maximum Ending Point
```

seen so far.

If

```text
Current End

≤

Maximum End
```

then the current interval is completely covered.

Otherwise,

it contributes to the answer.

---

# Algorithm

### Step 1

Sort intervals using

```text
Start Ascending

End Descending
```

---

### Step 2

Initialize

```text
Maximum End

=

0
```

---

### Step 3

Traverse every interval.

---

### Step 4

If

```text
Current End

≤

Maximum End
```

then

```text
Covered Interval
```

Ignore it.

---

### Step 5

Otherwise

```text
Answer++

Update Maximum End
```

---

### Step 6

Return

```text
Answer
```

---

# Flowchart

```text
            Start

              │

              ▼

      Sort Intervals

              │

              ▼

 Traverse Every Interval

              │

              ▼

 Current End ≤ Max End ?

        ┌────────┴────────┐

       Yes               No

        │                 │

        ▼                 ▼

 Ignore Interval     Count Interval

                         │

                         ▼

                Update Maximum End

                         │

                         ▼

                   Continue

                         │

                         ▼

                  Return Answer
```

---

# Example

Input

```text
[[1,4],
 [3,6],
 [2,8]]
```

Sorted

```text
[1,4]

[2,8]

[3,6]
```

Visualization

```text
1         4
|---------|

    2               8
    |---------------|

      3       6
      |-------|
```

Interval

```text
[3,6]
```

is completely inside

```text
[2,8]
```

Therefore,

```text
Remove

[3,6]
```

Remaining Intervals

```text
2
```

---

# Dry Run

Sorted

```text
[1,4]

[2,8]

[3,6]
```

Maximum End

```text
4
```

↓

Visit

```text
[2,8]
```

Maximum End

```text
8
```

↓

Visit

```text
[3,6]
```

Since

```text
6 ≤ 8
```

Covered

↓

Ignore

Final Answer

```text
2
```

---

# Memory Visualization

```text
Intervals

      │

      ▼

Sorting

      │

      ▼

Maximum End

      │

      ▼

Covered ?

      │

      ▼

Answer
```

---

# Why Greedy Works?

After sorting,

every interval that could cover another interval

appears before it.

Therefore,

maintaining

```text
Maximum End
```

is sufficient to determine whether the current interval

is covered.

Each interval is processed only once.

---

# Complexity Analysis

## Approach 1 — Greedy + Sorting

### Time Complexity

```text
O(n log n)
```

Sorting dominates the running time.

---

### Space Complexity

```text
O(1)
```

Ignoring the sorting algorithm's internal space.

---

# Java Solution

## Approach 1 — Greedy + Sorting (Optimal)

```java
//Approach-1 (Greedy + Sorting)
//T.C : O(n log n)
//S.C : O(1)

import java.util.Arrays;

class Solution {

    public int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(
            intervals,
            (a, b) -> {

                if (a[0] == b[0]) {

                    return b[1] - a[1];
                }

                return a[0] - b[0];
            }
        );

        int remainingIntervals = 0;

        int maximumEnd = 0;

        for (int[] interval : intervals) {

            if (interval[1] > maximumEnd) {

                remainingIntervals++;

                maximumEnd = interval[1];
            }
        }

        return remainingIntervals;
    }
}
```

---

## Approach 2 — Brute Force

```java
/*
//Approach-2 (Brute Force)
//T.C : O(n²)
//S.C : O(1)

class Solution {

    public int removeCoveredIntervals(int[][] intervals) {

        int covered = 0;

        int n = intervals.length;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i == j) {

                    continue;
                }

                if (
                    intervals[j][0] <= intervals[i][0] &&
                    intervals[i][1] <= intervals[j][1]
                ) {

                    covered++;

                    break;
                }
            }
        }

        return n - covered;
    }
}
*/
```

---

# C++ Solution

## Approach 1 — Greedy + Sorting (Optimal)

```cpp
//Approach-1 (Greedy + Sorting)
//T.C : O(n log n)
//S.C : O(1)

class Solution {
public:

    int removeCoveredIntervals(
        vector<vector<int>>& intervals
    ) {

        sort(
            intervals.begin(),
            intervals.end(),
            [](vector<int>& first,
               vector<int>& second) {

                if (first[0] == second[0]) {

                    return first[1] > second[1];
                }

                return first[0] < second[0];
            }
        );

        int remainingIntervals = 0;

        int maximumEnd = 0;

        for (auto &interval : intervals) {

            if (interval[1] > maximumEnd) {

                remainingIntervals++;

                maximumEnd = interval[1];
            }
        }

        return remainingIntervals;
    }
};
```

---

## Approach 2 — Brute Force

```cpp
/*
//Approach-2 (Brute Force)
//T.C : O(n²)
//S.C : O(1)

class Solution {
public:

    int removeCoveredIntervals(
        vector<vector<int>>& intervals
    ) {

        int covered = 0;

        int n = intervals.size();

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i == j) {

                    continue;
                }

                if (
                    intervals[j][0] <= intervals[i][0] &&
                    intervals[i][1] <= intervals[j][1]
                ) {

                    covered++;

                    break;
                }
            }
        }

        return n - covered;
    }
};
*/
```

---

# Complexity Comparison

| Approach | Algorithm | Time | Space |
|:---------|:----------|:----:|:-----:|
| Greedy + Sorting | Sorting + Greedy | **O(n log n)** | **O(1)** |
| Brute Force | Nested Loops | **O(n²)** | **O(1)** |

---

# Final Complexity

```text
Approach 1 (Greedy + Sorting)

Time Complexity  : O(n log n)

Space Complexity : O(1)

----------------------------------------

Approach 2 (Brute Force)

Time Complexity  : O(n²)

Space Complexity : O(1)
```

---

# Conclusion

- ✅ Sort intervals by **starting point in ascending order**.
- ✅ If two intervals have the same starting point, sort by **ending point in descending order** so that larger intervals appear first.
- ✅ Maintain the **maximum ending point** encountered during traversal.
- ✅ If the current interval's end is less than or equal to the maximum end, it is completely covered and can be removed.
- ✅ Otherwise, keep the interval and update the maximum end.
- ✅ The greedy approach solves the problem efficiently in **O(n log n)** time, making it the optimal solution.