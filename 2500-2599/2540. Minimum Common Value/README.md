# GitHub Repository

⭐ Full LeetCode Solutions Repository:  
https://github.com/Shailendra0320/Leetcode-Solutions

---

# 2540. Minimum Common Value

# Intuition

We are given two sorted arrays.

We need to find the smallest integer that exists in both arrays.

Since arrays are already sorted:

- we can efficiently solve the problem using:
  1. Two Pointers
  2. Binary Search

The goal is to minimize unnecessary comparisons.

---

# Approach 1 — Two Pointers

## Idea

Use:

- pointer `i` for `nums1`
- pointer `j` for `nums2`

Since arrays are sorted:

- if values are equal
  → we found the smallest common value

- if `nums1[i] < nums2[j]`
  → move `i`

- otherwise
  → move `j`

This works because smaller values can never become equal later.

---

# Two Pointer Flow

```text
nums1[i] == nums2[j]
        ↓
Return answer

nums1[i] < nums2[j]
        ↓
Move i

nums1[i] > nums2[j]
        ↓
Move j
```

---

# Diagram (Two Pointers)

```text
nums1 = [1,2,3,6]
          ↑

nums2 = [2,4,6]
          ↑

1 < 2

Move pointer i
```

↓

```text
nums1 = [1,2,3,6]
            ↑

nums2 = [2,4,6]
          ↑

2 == 2

Return 2
```

---

# Approach 2 — Binary Search

## Idea

Traverse one array and search each element inside the second array using Binary Search.

For every element in `nums2`:

- perform binary search in `nums1`

If found:

- return the element

Otherwise:

- continue searching

Because arrays are sorted:

- binary search works efficiently.

---

# Binary Search Flow

```text
Pick element from nums2

        ↓

Binary Search in nums1

        ↓

Found ?
   YES → Return Answer
   NO  → Continue
```

---

# Diagram (Binary Search)

```text
nums1 = [1,2,3,4,5,6]
nums2 = [2,7]

Search 2 inside nums1
```

↓

```text
low = 0
high = 5

mid = 2

nums1[mid] = 3

3 > 2

Move Left
```

↓

```text
low = 0
high = 1

mid = 0

nums1[mid] = 1

1 < 2

Move Right
```

↓

```text
low = 1
high = 1

mid = 1

nums1[mid] = 2

Found Answer
```

---

# Dry Run

Input:

```text
nums1 = [1,2,3]
nums2 = [2,4]
```

### Step 1

```text
1 < 2
```

Move pointer `i`

---

### Step 2

```text
2 == 2
```

Answer:

```text
2
```

---

# Complexity

## Two Pointer Approach

### Time Complexity

```text
O(n + m)
```

### Space Complexity

```text
O(1)
```

---

## Binary Search Approach

### Time Complexity

```text
O(m log n)
```

### Space Complexity

```text
O(1)
```

---

# Java Solution (Two Pointers)

```java
class Solution {

    public int getCommon(int[] nums1, int[] nums2) {

        int i = 0;
        int j = 0;

        while (i < nums1.length && j < nums2.length) {

            if (nums1[i] == nums2[j]) {

                return nums1[i];
            }
            else if (nums1[i] < nums2[j]) {

                i++;
            }
            else {

                j++;
            }
        }

        return -1;
    }
}
```

---

# Java Solution (Binary Search)

```java
class Solution {

    public int getCommon(int[] nums1, int[] nums2) {

        int n = nums1.length;

        int m = nums2.length;

        for (int i = 0; i < m; i++) {

            int low = 0;
            int high = n - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (nums1[mid] == nums2[i]) {

                    return nums2[i];
                }
                else if (nums1[mid] < nums2[i]) {

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

# C++ Solution (Two Pointers)

```cpp
class Solution {
public:

    int getCommon(vector<int>& nums1, vector<int>& nums2) {

        int i = 0;
        int j = 0;

        while (i < nums1.size() && j < nums2.size()) {

            if (nums1[i] == nums2[j]) {

                return nums1[i];
            }
            else if (nums1[i] < nums2[j]) {

                i++;
            }
            else {

                j++;
            }
        }

        return -1;
    }
};
```

---

# C++ Solution (Binary Search)

```cpp
class Solution {
public:

    int getCommon(vector<int>& nums1, vector<int>& nums2) {

        int n = nums1.size();

        int m = nums2.size();

        for (int i = 0; i < m; i++) {

            int low = 0;
            int high = n - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (nums1[mid] == nums2[i]) {

                    return nums2[i];
                }
                else if (nums1[mid] < nums2[i]) {

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
