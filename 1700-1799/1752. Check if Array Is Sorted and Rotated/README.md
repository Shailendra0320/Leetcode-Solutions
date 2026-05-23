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

# 1752. Check if Array Is Sorted and Rotated

# Intuition

A sorted array can be rotated some number of times.

Example:

```text
Sorted Array:
[1,2,3,4,5]

Rotated:
[3,4,5,1,2]
```

We need to determine whether the given array:

- was originally sorted
- then rotated

This problem can be solved using:

1. Brute Force Rotation Simulation
2. Better Brute Force using Sorting
3. Optimal Peak Counting

---

# Approach 1 — Brute Force Rotation Simulation

## Idea

Try every possible rotation:

- rotate the array
- check whether the rotated array becomes sorted

If any rotation is sorted:

- return true

Otherwise:

- return false

---

# Rotation Simulation Flow

```text
Try all rotations

        ↓

Create rotated array

        ↓

Check if sorted

        ↓

YES → return true

NO → continue
```

---

# Diagram

```text
nums = [3,4,5,1,2]

Rotation 0:
[3,4,5,1,2] ❌

Rotation 1:
[4,5,1,2,3] ❌

Rotation 2:
[5,1,2,3,4] ❌

Rotation 3:
[1,2,3,4,5] ✅
```

---

# Approach 2 — Better Brute Force

## Idea

First:

- sort the array

Then:

- compare every rotation of original array
- with the sorted array

If any rotation matches:

- return true

---

# Better Brute Force Flow

```text
Sort array

        ↓

Try every rotation

        ↓

Compare with sorted array

        ↓

Match found ?

YES → return true
NO  → continue
```

---

# Diagram

```text
nums = [3,4,5,1,2]

sorted = [1,2,3,4,5]

Rotate nums:

[1,2,3,4,5]

Matches sorted array ✅
```

---

# Approach 3 — Optimal Peak Counting

## Idea

In a sorted rotated array:

- there can be at most one position where:

```text
nums[i] > nums[i+1]
```

Example:

```text
[3,4,5,1,2]

5 > 1  ← only one peak
```

If peaks are more than 1:

- array is not sorted and rotated

---

# Optimal Flow

```text
Traverse array

        ↓

Count peaks:
nums[i] > nums[i+1]

        ↓

Peak count <= 1 ?

YES → valid
NO  → invalid
```

---

# Diagram

```text
nums = [3,4,5,1,2]

3 < 4 ✅
4 < 5 ✅
5 > 1 ❌ peak = 1
1 < 2 ✅
2 < 3 ✅

Only one peak → valid
```

---

# Dry Run

Input:

```text
nums = [3,4,5,1,2]
```

---

## Optimal Approach

### Step 1

```text
3 < 4
```

Peak = 0

---

### Step 2

```text
4 < 5
```

Peak = 0

---

### Step 3

```text
5 > 1
```

Peak = 1

---

### Step 4

```text
1 < 2
```

Peak = 1

---

### Step 5

```text
2 < 3
```

Peak = 1

---

Final:

```text
peak <= 1
```

Return:

```text
true
```

---

# Complexity

## Approach 1 — Brute Force Rotation

### Time Complexity

```text
O(n^2)
```

### Space Complexity

```text
O(n)
```

---

## Approach 2 — Better Brute Force

### Time Complexity

```text
O(n^2)
```

### Space Complexity

```text
O(n)
```

---

## Approach 3 — Optimal

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

# Java Solution 1 — Brute Force Rotation

```java
class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;

        int[] sorted = new int[n];

        for (int r = 0; r < n; r++) {

            int idx = 0;
            for (int i = r; i < n; i++) {
                sorted[idx] = nums[i];
                idx++;
            }

            for (int i = 0; i < r; i++) {
                sorted[idx] = nums[i];
                idx++;
            }

            boolean isSorted = true;

            for (int i = 0; i < n - 1; i++) {
                if (sorted[i] > sorted[i + 1]) {
                    isSorted = false;
                    break;
                }
            }

            if (isSorted) {
                return true;
            }
        }

        return false;
    }
}
```

---

# Java Solution 2 — Better Brute Force

```java
class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        for (int r = 0; r < n; r++) {

            boolean isSorted = true;

            for (int i = 0; i < n; i++) {

                if (sorted[i] != nums[(i + r) % n]) {

                    isSorted = false;

                    break;
                }
            }

            if (isSorted) {

                return true;
            }
        }

        return false;
    }
}
```

---

# Java Solution 3 — Optimal

```java
class Solution {
    public boolean check(int[] nums) {

        int n = nums.length;

        int peak = 0;

        for (int i = 0; i < n; i++) {

            if (nums[i] > nums[(i + 1) % n]) {

                peak++;
            }
        }

        return peak <= 1;
    }
}
```

---

# C++ Solution 1 — Brute Force Rotation

```cpp
class Solution {
public:

    bool check(vector<int>& nums) {

        int n = nums.size();

        vector<int> sorted(n);

        for (int r = 0; r < n; r++) {

            int idx = 0;

            for (int i = r; i < n; i++) {

                sorted[idx] = nums[i];

                idx++;
            }

            for (int i = 0; i < r; i++) {

                sorted[idx] = nums[i];

                idx++;
            }

            bool isSorted = true;

            for (int i = 0; i < n - 1; i++) {

                if (sorted[i] > sorted[i + 1]) {

                    isSorted = false;

                    break;
                }
            }

            if (isSorted) {

                return true;
            }
        }

        return false;
    }
};
```

---

# C++ Solution 2 — Better Brute Force

```cpp
class Solution {
public:

    bool check(vector<int>& nums) {

        int n = nums.size();

        vector<int> sorted = nums;

        sort(sorted.begin(), sorted.end());

        for (int r = 0; r < n; r++) {

            bool isSorted = true;

            for (int i = 0; i < n; i++) {

                if (sorted[i] != nums[(i + r) % n]) {

                    isSorted = false;

                    break;
                }
            }

            if (isSorted) {

                return true;
            }
        }

        return false;
    }
};
```

---

# C++ Solution 3 — Optimal

```cpp
class Solution {
public:

    bool check(vector<int>& nums) {

        int n = nums.size();

        int peak = 0;

        for (int i = 0; i < n; i++) {

            if (nums[i] > nums[(i + 1) % n]) {

                peak++;
            }
        }

        return peak <= 1;
    }
};
```
