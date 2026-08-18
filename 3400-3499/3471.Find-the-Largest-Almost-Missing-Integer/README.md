# Profiles

## GitHub

⭐ GitHub Repository:
[https://github.com/Shailendra0320](https://github.com/Shailendra0320?utm_source=chatgpt.com)

---

## LeetCode Profiles

🔥 Main Profile:
[https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/?utm_source=chatgpt.com)

🚀 Alternate Profile:
[https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/?utm_source=chatgpt.com)

---

# 3471. Find the Largest Almost Missing Integer

## Intuition

We need to find the **largest integer that appears in exactly one subarray of size `k`**.

The important point is that we are **not simply counting how many times a number occurs in the original array**.

For example:

```text
nums = [5,5]
k = 2
```

There is only one subarray:

```text
[5,5]
```

So even though `5` occurs twice in the array, it appears in only **one subarray**.

There are two ways to solve this problem:

1. **Brute Force — Check Every Candidate Against Every Subarray**
2. **Optimal — Frequency + Boundary Observation**

---

# Approaches

1. Brute Force / Direct Simulation
2. Frequency + Boundary Observation

---

# Approach 1 — Brute Force

## Idea

First, store all unique values from the array.

For every candidate:

```text
candidate
```

check every possible subarray of size `k`.

For each subarray:

```text
Check whether candidate exists
```

If it exists:

```text
subarrayCount++
```

Finally:

```text
if subarrayCount == 1
    candidate is valid
```

Among all valid candidates, return the largest one.

---

# Approach 1 Visualization

```text
                         Input Array

                  [3,9,2,1,7]
                         |
                         |
                       k = 3
                         |
                         ▼

             Generate all size-k subarrays

             ┌───────────┬───────────┬───────────┐
             │           │           │           │
             ▼           ▼           ▼           │

          [3,9,2]     [9,2,1]     [2,1,7]
             │           │           │
             └───────────┼───────────┘
                         │
                         ▼
                  Check candidate
                         │
          ┌──────────────┼──────────────┐
          │              │              │
          ▼              ▼              ▼
          3              9              7
          │              │              │
          ▼              ▼              ▼
       count = 1      count = 2      count = 1
          │              │              │
          ▼              ▼              ▼
        Valid          Invalid        Valid
          │                             │
          └──────────────┬──────────────┘
                         ▼
                   max(3,7) = 7
```

---

# Detailed Dry Run

### Input

```text
nums  = [3,9,2,1,7]
k     = 3
```

Possible subarrays:

```text
[3,9,2]
[9,2,1]
[2,1,7]
```

---

## Candidate = 3

Check:

```text
[3,9,2] → contains 3
[9,2,1] → does not contain 3
[2,1,7] → does not contain 3
```

Therefore:

```text
subarrayCount = 1
```

So:

```text
3 is valid
```

Current answer:

```text
answer = 3
```

---

## Candidate = 9

Check:

```text
[3,9,2] → contains 9
[9,2,1] → contains 9
[2,1,7] → does not contain 9
```

Therefore:

```text
subarrayCount = 2
```

So:

```text
9 is invalid
```

---

## Candidate = 2

Check:

```text
[3,9,2] → contains 2
[9,2,1] → contains 2
[2,1,7] → contains 2
```

Therefore:

```text
subarrayCount = 3
```

So:

```text
2 is invalid
```

---

## Candidate = 1

Check:

```text
[3,9,2] → does not contain 1
[9,2,1] → contains 1
[2,1,7] → contains 1
```

Therefore:

```text
subarrayCount = 2
```

So:

```text
1 is invalid
```

---

## Candidate = 7

Check:

```text
[3,9,2] → does not contain 7
[9,2,1] → does not contain 7
[2,1,7] → contains 7
```

Therefore:

```text
subarrayCount = 1
```

So:

```text
7 is valid
```

Update:

```text
answer = max(3,7)

answer = 7
```

---

# Final Result

```text
7
```

---

# Approach 1 Flowchart

```text
                  Start
                    │
                    ▼
          Store unique values
                    │
                    ▼
          Pick a candidate
                    │
                    ▼
       Generate every k-size window
                    │
                    ▼
       Does window contain candidate?
              /             \
            Yes              No
             │                │
             ▼                │
      count = count + 1       │
             │                │
             └───────┬────────┘
                     ▼
              More windows?
                /       \
              Yes        No
               │          │
               │          ▼
               │     count == 1?
               │       /      \
               │     Yes       No
               │      │         │
               │      ▼         │
               │   Update       │
               │   answer       │
               │      │         │
               └──────┴─────────┘
                      │
                      ▼
                  More values?
                  /        \
                Yes         No
                 │           │
                 └───────────┤
                             ▼
                         Return answer
```

---

# Approach 1 — Why This Works

For every possible candidate, we inspect every possible subarray of size `k`.

For every window, we explicitly determine whether the candidate is present.

Therefore:

```text
subarrayCount
```

is exactly the number of size-`k` subarrays containing the candidate.

If:

```text
subarrayCount == 1
```

the candidate satisfies the definition.

Finally:

```text
answer = max(answer, candidate)
```

guarantees that the largest valid candidate is returned.

---

# Approach 1 — Complexity

Let:

```text
n = nums.length
d = number of distinct values
```

Number of size-`k` subarrays:

```text
n - k + 1
```

For every candidate, we may inspect `k` elements in every window.

Therefore:

```text
Time Complexity:
O(d × (n-k+1) × k)
```

Since:

```text
d <= n
```

the worst-case complexity is:

```text
O(n³)
```

### Space Complexity

The `HashSet` stores distinct values:

```text
O(d)
```

Therefore:

```text
O(n)
```

---

# Java Solution 1

```java
//Approach-1 (Brute Force)
//T.C : O(d * (n-k+1) * k), worst case O(n^3)
//S.C : O(n)

class Solution {
    public int largestInteger(int[] nums, int k) {
        int arrayLength = nums.length;
        int answer = -1;

        Set<Integer> uniqueValues = new HashSet<>();
        for (int value : nums) uniqueValues.add(value);

        for (int candidate : uniqueValues) {
            int subarrayCount = 0;

            for (int startIndex = 0; startIndex <= arrayLength - k; startIndex++) {
                boolean foundInSubarray = false;

                for (int position = startIndex; position < startIndex + k; position++) {
                    if (nums[position] == candidate) {
                        foundInSubarray = true;
                        break;
                    }
                }

                if (foundInSubarray) subarrayCount++;
            }

            if (subarrayCount == 1) {
                answer = Math.max(answer, candidate);
            }
        }

        return answer;
    }
}
```

---

# C++ Solution 1

```cpp
//Approach-1 (Brute Force)
//T.C : O(d * (n-k+1) * k), worst case O(n^3)
//S.C : O(n)

class Solution {
public:
    int largestInteger(vector<int>& nums, int k) {
        int arrayLength = nums.size();
        int answer = -1;

        unordered_set<int> uniqueValues;

        for (int value : nums) {
            uniqueValues.insert(value);
        }

        for (int candidate : uniqueValues) {
            int subarrayCount = 0;

            for (int startIndex = 0;
                 startIndex <= arrayLength - k;
                 startIndex++) {

                bool foundInSubarray = false;

                for (int position = startIndex;
                     position < startIndex + k;
                     position++) {

                    if (nums[position] == candidate) {
                        foundInSubarray = true;
                        break;
                    }
                }

                if (foundInSubarray) {
                    subarrayCount++;
                }
            }

            if (subarrayCount == 1) {
                answer = max(answer, candidate);
            }
        }

        return answer;
    }
};
```

---

# Approach 2 — Frequency + Boundary Observation

## Idea

The brute-force solution checks every candidate against every window.

We can do much better by understanding **which positions can belong to exactly one subarray of size `k`**.

There are three cases:

```text
1. k = 1
2. k = n
3. 1 < k < n
```

Each case can be solved efficiently.

---

# Case 1 — `k = 1`

When:

```text
k = 1
```

every subarray contains exactly one element.

Example:

```text
nums = [4,2,7,2,9]
k = 1
```

Subarrays:

```text
[4]
[2]
[7]
[2]
[9]
```

Therefore, an integer appears in exactly one subarray if and only if:

```text
frequency[value] == 1
```

So we find the largest value whose frequency is `1`.

---

# Case 2 — `k = n`

When:

```text
k = n
```

there is exactly one subarray:

```text
nums[0 ... n-1]
```

Every value in the array appears in that one subarray.

Therefore:

```text
answer = maximum value in nums
```

---

# Case 3 — `1 < k < n`

This is the important observation.

Consider:

```text
nums = [3,9,2,1,7]
k = 3
```

The windows are:

```text
[3,9,2]
[9,2,1]
[2,1,7]
```

Notice:

```text
3
```

appears only in the first window.

And:

```text
7
```

appears only in the last window.

But:

```text
9
```

appears in:

```text
[3,9,2]
[9,2,1]
```

So it appears in two windows.

Similarly, an interior position can be covered by multiple overlapping windows.

Therefore, for:

```text
1 < k < n
```

only the first and last positions can potentially contain a value that appears in exactly one window.

Those values are:

```text
nums[0]
nums[n-1]
```

But we also need to make sure that the value occurs only once in the entire array.

Therefore:

```text
frequency[nums[0]] == 1
```

or:

```text
frequency[nums[n-1]] == 1
```

must be true.

---

# Approach 2 Visualization

```text
                         Input
                           │
                           ▼
                    [3,9,2,1,7]
                           │
                           ▼
                         k = 3
                           │
                           ▼
              ┌─────────────────────┐
              │   Check boundaries  │
              └─────────────────────┘
                    │           │
                    ▼           ▼
                  nums[0]    nums[n-1]
                    │           │
                    ▼           ▼
                    3           7
                    │           │
                    ▼           ▼
              frequency = 1  frequency = 1
                    │           │
                    └─────┬─────┘
                          ▼
                     max(3,7)
                          │
                          ▼
                           7
```

---

# Approach 2 Flowchart

```text
                         Start
                           │
                           ▼
                    Build Frequency Map
                           │
                           ▼
                       Is k == 1?
                      /          \
                    Yes           No
                     │             │
                     ▼             ▼
              Find maximum      Is k == n?
              freq == 1         /          \
                               Yes          No
                                │            │
                                ▼            ▼
                          Return max      Check nums[0]
                          element         and nums[n-1]
                                             │
                                             ▼
                                     frequency == 1?
                                             │
                                             ▼
                                      Update answer
                                             │
                                             ▼
                                       Return answer
```

---

# Detailed Example

### Input

```text
nums = [3,9,2,1,7]
k = 3
```

Since:

```text
1 < k < n
```

we only need to check:

```text
nums[0]   = 3
nums[n-1] = 7
```

Frequency map:

```text
3 → 1
9 → 1
2 → 1
1 → 1
7 → 1
```

Check `3`:

```text
frequency[3] == 1
```

Therefore:

```text
3 is valid
```

Check `7`:

```text
frequency[7] == 1
```

Therefore:

```text
7 is valid
```

Take the maximum:

```text
max(3,7) = 7
```

Final answer:

```text
7
```

---

# Approach 2 — Memory Visualization

```text
                     nums

              [3, 9, 2, 1, 7]
               ▲           ▲
               │           │
               │           │
          first element  last element
               │           │
               ▼           ▼
              3             7
               │           │
               ▼           ▼
           frequency     frequency
               │           │
               ▼           ▼
                1           1
                 \          /
                  \        /
                   \      /
                    ▼    ▼
                    Maximum
                       │
                       ▼
                       7
```

---

# Approach 2 — Why This Works

For:

```text
1 < k < n
```

the size-`k` windows overlap.

An interior position belongs to multiple possible windows.

Therefore, a value located only at an interior position cannot be present in exactly one size-`k` window.

The first position can belong only to the first window.

The last position can belong only to the last window.

Thus, only:

```text
nums[0]
nums[n-1]
```

can qualify.

However, if the same value occurs elsewhere, it can also appear in another window.

Therefore, the value must have:

```text
frequency[value] == 1
```

This gives us an `O(n)` solution.

---

# Approach 2 — Complexity

### Time Complexity

Building the frequency map requires:

```text
O(n)
```

The remaining operations only inspect a constant number of elements.

Therefore:

```text
O(n)
```

### Space Complexity

The frequency map can contain at most `n` different values:

```text
O(n)
```

---

# Comparison of Approaches

| Approach             | Idea                                           | Time               | Space |
| -------------------- | ---------------------------------------------- | ------------------ | ----- |
| Brute Force          | Check every candidate against every `k`-window | O(d × (n-k+1) × k) | O(n)  |
| Frequency + Boundary | Frequency map + boundary observation           | O(n)               | O(n)  |

---

# Complexity Analysis

## Approach 1

### Time Complexity

```text
O(d × (n-k+1) × k)

Worst Case:
O(n³)
```

### Space Complexity

```text
O(n)
```

---

## Approach 2

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

# Java Solution 2

```java
//Approach-2 (Frequency + Boundary Observation)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int largestInteger(int[] nums, int k) {

        int n = nums.length;

        Map<Integer, Integer> frequency = new HashMap<>();

        for (int num : nums) {
            frequency.put(
                num,
                frequency.getOrDefault(num, 0) + 1
            );
        }

        int answer = -1;

        if (k == 1) {

            for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {

                if (entry.getValue() == 1) {

                    answer = Math.max(
                        answer,
                        entry.getKey()
                    );
                }
            }

            return answer;
        }

        if (k == n) {

            for (int num : nums) {

                answer = Math.max(answer, num);
            }

            return answer;
        }

        if (frequency.get(nums[0]) == 1) {

            answer = Math.max(
                answer,
                nums[0]
            );
        }

        if (frequency.get(nums[n - 1]) == 1) {

            answer = Math.max(
                answer,
                nums[n - 1]
            );
        }

        return answer;
    }
}
```

---

# C++ Solution 2

```cpp
//Approach-2 (Frequency + Boundary Observation)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    int largestInteger(vector<int>& nums, int k) {

        int n = nums.size();

        unordered_map<int, int> frequency;

        for (int num : nums) {
            frequency[num]++;
        }

        int answer = -1;

        if (k == 1) {

            for (auto& [value, count] : frequency) {

                if (count == 1) {

                    answer = max(
                        answer,
                        value
                    );
                }
            }

            return answer;
        }

        if (k == n) {

            return *max_element(
                nums.begin(),
                nums.end()
            );
        }

        if (frequency[nums[0]] == 1) {

            answer = max(
                answer,
                nums[0]
            );
        }

        if (frequency[nums[n - 1]] == 1) {

            answer = max(
                answer,
                nums[n - 1]
            );
        }

        return answer;
    }
};
```

---

# Important Edge Cases

## Case 1 — `k = 1`

```text
nums = [4,2,7,2,9]
k = 1
```

Unique values:

```text
4
7
9
```

Largest:

```text
9
```

---

## Case 2 — No Valid Answer

```text
nums = [2,2,2]
k = 1
```

Frequency:

```text
2 → 3
```

No value occurs exactly once.

Output:

```text
-1
```

---

## Case 3 — `k = n`

```text
nums = [4,1,9,3]
k = 4
```

Only one subarray exists:

```text
[4,1,9,3]
```

Output:

```text
9
```

---

## Case 4 — Duplicate Boundary Value

```text
nums = [7,2,3,7]
k = 2
```

Although `7` occurs at both boundaries:

```text
frequency[7] = 2
```

Therefore, it is not valid.

---

# Key Takeaways

The brute-force solution directly follows the definition:

```text
Candidate
    ↓
Every k-size subarray
    ↓
Check candidate
    ↓
Count containing windows
    ↓
Count == 1
```

The optimized solution uses structural analysis:

```text
k = 1
    ↓
frequency == 1

k = n
    ↓
maximum element

1 < k < n
    ↓
only first/last position can qualify
    ↓
frequency == 1
```

The most important optimization is recognizing that we do **not** need to inspect every subarray.

---

# Final Complexity Comparison

```text
┌──────────────────────────┬──────────────────┬──────────┐
│ Approach                 │ Time             │ Space    │
├──────────────────────────┼──────────────────┼──────────┤
│ Brute Force              │ O(n³) worst case │ O(n)     │
│ Frequency + Boundary     │ O(n)             │ O(n)     │
└──────────────────────────┴──────────────────┴──────────┘
```

The **Brute Force approach** is useful for understanding and validating the problem.

The **Frequency + Boundary approach** is the preferred optimized solution.

---

# Tags

`Array` `Hash Table` `Sliding Window` `Frequency Count` `Subarray` `Case Analysis` `LeetCode`

---
