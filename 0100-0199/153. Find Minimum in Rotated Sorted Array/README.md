# GitHub Repository

⭐ Full LeetCode Solutions Repository:  
https://github.com/Shailendra0320/Leetcode-Solutions

---

# 153. Find Minimum in Rotated Sorted Array

# Intuition

A rotated sorted array is originally sorted in ascending order, but rotated at some pivot.

Example:

```text
[0,1,2,4,5,6,7]
```

After rotation:

```text
[4,5,6,7,0,1,2]
```

The goal is to find the minimum element efficiently.

We can solve this problem using:

1. Priority Queue (Brute Force)
2. Binary Search (Optimal)

---

# Approach 1 — Priority Queue

## Idea

- Insert all elements into a min-heap
- The smallest element will be at the top

Then:

- return the top element

---

# Approach 2 — Binary Search

## Key Observation

In a rotated sorted array:

- one half is always sorted
- minimum element lies in the unsorted half

We compare:

```text
nums[mid] and nums[high]
```

### Case 1

```text
nums[mid] > nums[high]
```

Minimum lies on the right side.

```text
low = mid + 1
```

### Case 2

```text
nums[mid] <= nums[high]
```

Minimum may be at mid or left side.

```text
high = mid
```

Finally:

```text
nums[low]
```

will be the minimum element.

---

# Dry Run

Input:

```text
nums = [4,5,6,7,0,1,2]
```

### Iteration 1

```text
mid = 3
nums[mid] = 7
nums[high] = 2
```

Since:

```text
7 > 2
```

Move right:

```text
low = mid + 1
```

---

### Iteration 2

```text
low = 4
high = 6
mid = 5
```

```text
nums[mid] = 1
nums[high] = 2
```

Move left:

```text
high = mid
```

Eventually:

```text
low = high = 4
```

Answer:

```text
0
```

---

# Diagram

```text
Rotated Array

[4,5,6,7,0,1,2]

        ↓

Compare mid with high

        ↓

Minimum lies in unsorted half

        ↓

Binary Search
```

---

# Complexity

## Priority Queue Approach

### Time Complexity

```text
O(n log n)
```

### Space Complexity

```text
O(n)
```

---

## Binary Search Approach

### Time Complexity

```text
O(log n)
```

### Space Complexity

```text
O(1)
```

---

# Java Solution (Priority Queue)

```java
class Solution {

    public int findMin(int[] nums) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i : nums) {
            pq.add(i);
        }

        return pq.poll();
    }
}
```

---

# Java Solution (Binary Search)

```java
class Solution {

    public int findMin(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {

                low = mid + 1;
            }
            else {

                high = mid;
            }
        }

        return nums[high];
    }
}
```

---

# C++ Solution (Priority Queue)

```cpp
class Solution {
public:

    int findMin(vector<int>& nums) {

        priority_queue<int, vector<int>, greater<int>> pq;

        for (int num : nums) {
            pq.push(num);
        }

        return pq.top();
    }
};
```

---

# C++ Solution (Binary Search)

```cpp
class Solution {
public:

    int findMin(vector<int>& nums) {

        int low = 0;
        int high = nums.size() - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {

                low = mid + 1;
            }
            else {

                high = mid;
            }
        }

        return nums[high];
    }
};
```
