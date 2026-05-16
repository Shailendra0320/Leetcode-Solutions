# 154. Find Minimum in Rotated Sorted Array II

# Intuition

The array is originally sorted but rotated at some pivot.

Unlike LeetCode 153, this problem may contain duplicates.

Example:

```text
[2,2,2,0,1]
```

Duplicates make Binary Search slightly harder because:

```text
nums[mid] == nums[high]
```

does not clearly indicate which side contains the minimum.

So we carefully shrink the search space while maintaining correctness.

---

# Approach

## Binary Search

We compare:

```text
nums[mid] and nums[high]
```

### Case 1

```text
nums[mid] < nums[high]
```

Minimum lies on the left side (including mid).

```text
high = mid
```

---

### Case 2

```text
nums[mid] > nums[high]
```

Minimum lies on the right side.

```text
low = mid + 1
```

---

### Case 3 (Duplicates)

```text
nums[mid] == nums[high]
```

Cannot determine the correct side.

Safely reduce search space:

```text
high--
```

---

# Dry Run

Input:

```text
nums = [2,2,2,0,1]
```

### Iteration 1

```text
mid = 2
nums[mid] = 2
nums[high] = 1
```

```text
2 > 1
```

Move right:

```text
low = mid + 1
```

---

### Iteration 2

```text
low = 3
high = 4
mid = 3
```

```text
nums[mid] = 0
nums[high] = 1
```

```text
0 < 1
```

Move left:

```text
high = mid
```

Answer:

```text
0
```

---

# Diagram

```text
Rotated Sorted Array with Duplicates

[2,2,2,0,1]

        ↓

Compare nums[mid] with nums[high]

        ↓

Remove duplicates carefully

        ↓

Apply Binary Search

        ↓

Find minimum element
```

---

# Complexity

## Optimal Binary Search

### Time Complexity

```text
O(log n)
```

Worst Case (all duplicates):

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

# Java Solution 1

```java
class Solution {

    public int findMin(int[] nums) {

        int l = 0;
        int r = nums.length - 1;

        int resultIdx = 0;

        while (l <= r) {

            while (l < r && nums[l] == nums[l + 1])
                l++;

            while (l < r && nums[r] == nums[r - 1])
                r--;

            int mid = l + (r - l) / 2;

            if (nums[mid] < nums[resultIdx])
                resultIdx = mid;

            if (nums[mid] > nums[r]) {

                l = mid + 1;
            }
            else {

                r = mid - 1;
            }
        }

        return nums[resultIdx];
    }
}
```

---

# Java Solution 2

```java
class Solution {

    public int findMin(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] < nums[high]) {

                high = mid;
            }
            else if (nums[mid] > nums[high]) {

                low = mid + 1;
            }
            else {

                high--;
            }
        }

        return nums[low];
    }
}
```

---

# C++ Solution 1

```cpp
class Solution {
public:

    int findMin(vector<int>& nums) {

        int l = 0;
        int r = nums.size() - 1;

        int resultIdx = 0;

        while (l <= r) {

            while (l < r && nums[l] == nums[l + 1])
                l++;

            while (l < r && nums[r] == nums[r - 1])
                r--;

            int mid = l + (r - l) / 2;

            if (nums[mid] < nums[resultIdx])
                resultIdx = mid;

            if (nums[mid] > nums[r]) {

                l = mid + 1;
            }
            else {

                r = mid - 1;
            }
        }

        return nums[resultIdx];
    }
};
```

---

# C++ Solution 2

```cpp
class Solution {
public:

    int findMin(vector<int>& nums) {

        int low = 0;
        int high = nums.size() - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] < nums[high]) {

                high = mid;
            }
            else if (nums[mid] > nums[high]) {

                low = mid + 1;
            }
            else {

                high--;
            }
        }

        return nums[low];
    }
};
```
