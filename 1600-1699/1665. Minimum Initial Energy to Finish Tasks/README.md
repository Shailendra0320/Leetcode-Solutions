# GitHub Repository

⭐ Full LeetCode Solutions Repository:  
https://github.com/Shailendra0320/Leetcode-Solutions

---

# Intuition

Each task contains:

- `actual[i]` → energy consumed after completing task
- `minimum[i]` → minimum energy required before starting task

We need to find the minimum initial energy required to finish all tasks.

To minimize starting energy:

- Perform tasks with larger `(minimum - actual)` first
- This greedy ordering reduces extra energy requirements later

Then we use Binary Search on the answer:

- Check whether a given starting energy can complete all tasks
- If possible → try smaller energy
- Otherwise → increase energy

---

# Approach

## Step 1: Sort Tasks

Sort tasks in decreasing order of:

```text
(minimum - actual)
```

This ensures difficult tasks are handled earlier.

---

## Step 2: Binary Search

Search for minimum valid initial energy.

Range:

```text
0 → 1e9
```

---

## Step 3: Validation Function

For each task:

- If current energy < minimum required:
  - impossible
- Otherwise:
  - complete task
  - reduce energy by actual cost

---

# Dry Run

Input:

```text
tasks = [[1,2],[2,4],[4,8]]
```

After sorting:

```text
[[4,8],[2,4],[1,2]]
```

Binary Search checks minimum energy.

Suppose:

```text
energy = 8
```

Process:

```text
Task [4,8]
8 >= 8 → possible
energy = 8 - 4 = 4

Task [2,4]
4 >= 4 → possible
energy = 4 - 2 = 2

Task [1,2]
2 >= 2 → possible
energy = 2 - 1 = 1
```

All tasks completed.

Answer = 8

---

# Diagram

```text
Tasks:

[actual, minimum]

[1,2]
[2,4]
[4,8]

        ↓

Sort by:

(minimum - actual)

        ↓

[4,8]
[2,4]
[1,2]

        ↓

Binary Search Initial Energy

        ↓

Check feasibility

        ↓

Minimum Valid Energy Found
```

---

# Complexity

### Time Complexity

- O(n log n + n log(1e9))

### Space Complexity

- O(1)

---

# Java Solution

```java
class Solution {

    public boolean isPossible(int[][] tasks, int energy) {

        for (int[] task : tasks) {

            int actual = task[0];
            int minimum = task[1];

            if (energy < minimum) {
                return false;
            }

            energy -= actual;
        }

        return true;
    }

    public int minimumEffort(int[][] tasks) {

        Arrays.sort(tasks, (a, b) -> {
            int diff1 = a[1] - a[0];
            int diff2 = b[1] - b[0];

            return diff2 - diff1;
        });

        int l = 0;
        int r = (int)1e9;

        int ans = Integer.MAX_VALUE;

        while (l <= r) {

            int mid = l + (r - l) / 2;

            if (isPossible(tasks, mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    bool isPossible(vector<vector<int>>& tasks, int energy) {

        for(auto& task : tasks) {

            int actual = task[0];
            int minimum = task[1];

            if(energy < minimum) {
                return false;
            }

            energy -= actual;
        }

        return true;
    }

    int minimumEffort(vector<vector<int>>& tasks) {

        sort(tasks.begin(), tasks.end(),
            [](vector<int>& a, vector<int>& b) {

                int diff1 = a[1] - a[0];
                int diff2 = b[1] - b[0];

                return diff2 < diff1;
            });

        int l = 0;
        int r = 1e9;

        int ans = INT_MAX;

        while(l <= r) {

            int mid = l + (r - l) / 2;

            if(isPossible(tasks, mid)) {
                ans = mid;
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }

        return ans;
    }
};
```
