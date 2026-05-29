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

# 3300. Minimum Element After Replacement With Digit Sum

# Intuition

For every number:

- Replace it with the sum of its digits.
- Find the minimum value among all digit sums.

Example:

```text
nums = [10, 12, 13, 14]

Digit Sums:

10 → 1
12 → 3
13 → 4
14 → 5

Minimum = 1
```

---

# Approaches

This problem can be solved using:

1. Digit Sum Array + Sorting
2. Optimal Single Pass

---

# Approach 1 — Digit Sum Array + Sorting

## Idea

Create a new array.

For every element:

```text
Find digit sum
Store in new array
```

After processing:

```text
Sort new array
Return first element
```

---

# Flow Diagram

```text
Traverse nums

        ↓

Calculate digit sum

        ↓

Store in new array

        ↓

Sort array

        ↓

Return first element
```

---

# Example

```text
nums = [25,43,18]
```

Digit sums:

```text
25 → 7
43 → 7
18 → 9
```

New Array:

```text
[7,7,9]
```

After Sorting:

```text
[7,7,9]
```

Answer:

```text
7
```

---

# Approach 2 — Optimal Single Pass

## Idea

No need to create another array.

For every number:

```text
Calculate digit sum
Update minimum answer
```

Keep track of the smallest digit sum.

---

# Flow Diagram

```text
Traverse nums

        ↓

Calculate digit sum

        ↓

Update minimum

        ↓

Return answer
```

---

# Example

```text
nums = [25,43,18]
```

---

### Step 1

```text
25 → 7

min = 7
```

---

### Step 2

```text
43 → 7

min = 7
```

---

### Step 3

```text
18 → 9

min = 7
```

---

Answer:

```text
7
```

---

# Detailed Dry Run

Input:

```text
nums = [999, 19, 101]
```

Digit sums:

```text
999 → 27
19  → 10
101 → 2
```

Minimum:

```text
2
```

Output:

```text
2
```

---

# Complexity Analysis

## Approach 1 — Digit Sum Array + Sorting

### Time Complexity

```text
O(n × d + n log n)
```

Where:

- n = size of array
- d = number of digits

### Space Complexity

```text
O(n)
```

---

## Approach 2 — Optimal Single Pass

### Time Complexity

```text
O(n × d)
```

Where:

- n = size of array
- d = number of digits

### Space Complexity

```text
O(1)
```

---

# Java Solution 1 — Digit Sum Array + Sorting

```java
class Solution {
    public int minElement(int[] nums) {
        int[] newArr = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {

            int sum = 0;
            int digit = nums[i];

            while (digit > 0) {
                sum += digit % 10;
                digit /= 10;
            }

            newArr[i] = sum;
        }

        Arrays.sort(newArr);

        return newArr[0];
    }
}
```

---

# Java Solution 2 — Optimal Single Pass

```java
class Solution {
    public int minElement(int[] nums) {

        int min = Integer.MAX_VALUE;

        for (int num : nums) {

            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            min = Math.min(min, sum);
        }

        return min;
    }
}
```

---

# C++ Solution 1 — Digit Sum Array + Sorting

```cpp
class Solution {
public:

    int minElement(vector<int>& nums) {

        vector<int> newArr(nums.size());

        for (int i = 0; i < nums.size(); i++) {

            int sum = 0;
            int digit = nums[i];

            while (digit > 0) {

                sum += digit % 10;
                digit /= 10;
            }

            newArr[i] = sum;
        }

        sort(newArr.begin(), newArr.end());

        return newArr[0];
    }
};
```

---

# C++ Solution 2 — Optimal Single Pass

```cpp
class Solution {
public:

    int minElement(vector<int>& nums) {

        int mn = INT_MAX;

        for (int num : nums) {

            int sum = 0;

            while (num > 0) {

                sum += num % 10;
                num /= 10;
            }

            mn = min(mn, sum);
        }

        return mn;
    }
};
```
