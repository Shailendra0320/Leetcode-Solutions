# GitHub Repository

⭐ Full LeetCode Solutions Repository:  
:contentReference[oaicite:0]{index=0}

---

# 33. Search in Rotated Sorted Array

# Intuition

The array is originally sorted in increasing order but rotated at some pivot.

Example:

```text
Original:
[0,1,2,4,5,6,7]

Rotated:
[4,5,6,7,0,1,2]
```

We need to search for a target efficiently.

This problem can be solved using multiple approaches:

1. Optimized Binary Search
2. Pivot + Binary Search
3. Linear Search

---

# Approach 1 — Optimized Binary Search

## Idea

In a rotated sorted array:

- at least one half is always sorted

Using Binary Search:

- determine which half is sorted
- check if target lies inside that half
- discard the other half

This gives:

```text
O(log n)
```

time complexity.

---

# Optimized Binary Search Flow

```text
Find mid

        ↓

Left half sorted ?

        ↓

YES
│
├── Target inside left half ?
│       ├── YES → move left
│       └── NO  → move right
│
NO
│
└── Right half sorted
        ├── Target inside right ?
        │       ├── YES → move right
        │       └── NO  → move left
```

---

# Diagram (Optimized Binary Search)

```text
nums = [4,5,6,7,0,1,2]

low = 0
high = 6

mid = 3
nums[mid] = 7

Left half:
[4,5,6,7] → Sorted

Target = 0

0 not inside left half

Move right
```

↓

```text
low = 4
high = 6

mid = 5
nums[mid] = 1

Target lies left of mid

Move left
```

↓

```text
mid = 4

nums[mid] = 0

Found Answer
```

---

# Approach 2 — Pivot + Binary Search

## Idea

First:

- find pivot (minimum element)

Then:

- the array becomes divided into two sorted halves

Apply Binary Search separately on both halves.

---

# Pivot Search Flow

```text
Find Pivot

        ↓

Divide array into:

Left Sorted Half
Right Sorted Half

        ↓

Apply Binary Search
```

---

# Diagram (Pivot Approach)

```text
nums = [4,5,6,7,0,1,2]

Pivot Index = 4

nums[4] = 0

Left Half:
[4,5,6,7]

Right Half:
[0,1,2]
```

---

# Approach 3 — Linear Search

## Idea

Traverse the entire array:

- compare every element with target

If found:

- return index

Otherwise:

- return `-1`

Simple but inefficient.

---

# Linear Search Flow

```text
Traverse array

        ↓

nums[i] == target ?

        ↓

YES → return index

NO → continue traversal
```

---

# Diagram (Linear Search)

```text
nums = [4,5,6,7,0,1,2]

Check:

4 ❌
5 ❌
6 ❌
7 ❌
0 ✅

Return index 4
```

---

# Dry Run

Input:

```text
nums = [4,5,6,7,0,1,2]
target = 0
```

---

## Optimized Binary Search

### Step 1

```text
mid = 3
nums[mid] = 7
```

Left half sorted.

Target not inside left half.

Move right.

---

### Step 2

```text
mid = 5
nums[mid] = 1
```

Target lies left side.

Move left.

---

### Step 3

```text
mid = 4
nums[mid] = 0
```

Found answer.

---

# Complexity

## Approach 1 — Optimized Binary Search

### Time Complexity

```text
O(log n)
```

### Space Complexity

```text
O(1)
```

---

## Approach 2 — Pivot + Binary Search

### Time Complexity

```text
O(log n)
```

### Space Complexity

```text
O(1)
```

---

## Approach 3 — Linear Search

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

# Java Solution 1 — Optimized Binary Search

```java
class Solution {

    public int search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {

                return mid;
            }

            if (nums[low] <= nums[mid]) {

                if (nums[low] <= target && target < nums[mid]) {

                    high = mid - 1;
                }
                else {

                    low = mid + 1;
                }
            }
            else {

                if (nums[mid] < target && target <= nums[high]) {

                    low = mid + 1;
                }
                else {

                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}
```

---

# Java Solution 2 — Pivot + Binary Search

```java
class Solution {

    public int search(int[] nums, int target) {

        int n = nums.length;

        int pivot = findPivot(nums);

        if (nums[pivot] == target) {

            return pivot;
        }

        int idx = binarySearch(nums, pivot, n - 1, target);

        if (idx != -1) {

            return idx;
        }

        return binarySearch(nums, 0, pivot - 1, target);
    }

    private int findPivot(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {

                left = mid + 1;
            }
            else {

                right = mid;
            }
        }

        return left;
    }

    private int binarySearch(int[] nums, int left, int right, int target) {

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                return mid;
            }
            else if (nums[mid] < target) {

                left = mid + 1;
            }
            else {

                right = mid - 1;
            }
        }

        return -1;
    }
}
```

---

# Java Solution 3 — Linear Search

```java
class Solution {

    public int search(int[] nums, int target) {

        List<Integer> list = new ArrayList<>();

        for (int a : nums) {

            list.add(a);
        }

        return list.indexOf(target);
    }
}
```

---

# C++ Solution 1 — Optimized Binary Search

```cpp
class Solution {
public:

    int search(vector<int>& nums, int target) {

        int low = 0;
        int high = nums.size() - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {

                return mid;
            }

            if (nums[low] <= nums[mid]) {

                if (nums[low] <= target && target < nums[mid]) {

                    high = mid - 1;
                }
                else {

                    low = mid + 1;
                }
            }
            else {

                if (nums[mid] < target && target <= nums[high]) {

                    low = mid + 1;
                }
                else {

                    high = mid - 1;
                }
            }
        }

        return -1;
    }
};
```

---

# C++ Solution 2 — Pivot + Binary Search

```cpp
class Solution {
public:

    int search(vector<int>& nums, int target) {

        int n = nums.size();

        int pivot = findPivot(nums);

        if (nums[pivot] == target) {

            return pivot;
        }

        int idx = binarySearch(nums, pivot, n - 1, target);

        if (idx != -1) {

            return idx;
        }

        return binarySearch(nums, 0, pivot - 1, target);
    }

    int findPivot(vector<int>& nums) {

        int left = 0;
        int right = nums.size() - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {

                left = mid + 1;
            }
            else {

                right = mid;
            }
        }

        return left;
    }

    int binarySearch(vector<int>& nums, int left, int right, int target) {

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                return mid;
            }
            else if (nums[mid] < target) {

                left = mid + 1;
            }
            else {

                right = mid - 1;
            }
        }

        return -1;
    }
};
```

---

# C++ Solution 3 — Linear Search

```cpp
class Solution {
public:

    int search(vector<int>& nums, int target) {

        vector<int> list;

        for (int a : nums) {

            list.push_back(a);
        }

        for (int i = 0; i < list.size(); i++) {

            if (list[i] == target) {

                return i;
            }
        }

        return -1;
    }
};
```
