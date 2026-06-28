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

# 1846. Maximum Element After Decreasing and Rearranging

## Tags

```text
Greedy
Sorting
Counting Sort
Array
Math
Java
C++
```

---

# Intuition

We may:

- Rearrange the array in any order.
- Decrease any element any number of times.
- The first element must become **1**.
- The difference between adjacent elements cannot exceed **1**.

Our goal is to maximize the last element.

---

# Approaches

1. Sorting + Greedy
2. Counting Sort
3. Sorting + Construct New Array

---

# Approach 1 — Sorting + Greedy (Optimal)

## Idea

Sort the array.

Make

```text
arr[0] = 1
```

For every next element,

```text
arr[i] = min(arr[i], arr[i-1] + 1)
```

This guarantees

- first element is 1
- adjacent difference ≤ 1
- maximum possible last element

---

# Flowchart

```text
Input Array

      │

      ▼

Sort Array

      │

      ▼

Make First Element = 1

      │

      ▼

Traverse Remaining Elements

      │

      ▼

Current Value >

Previous + 1 ?

      │

 YES ─────► Set = Previous + 1

 NO

      │

      ▼

Continue

      │

      ▼

Return Last Element
```

---

# Example

Input

```text
[2,2,1,2,1]
```

After Sorting

```text
[1,1,2,2,2]
```

Process

```text
1

1

2

2

2
```

Answer

```text
2
```

---

# Dry Run

```text
Input

[100,1,1000]
```

Sort

```text
[1,100,1000]
```

Step 1

```text
[1,100,1000]
```

Step 2

```text
1

min(100,2)=2

min(1000,3)=3
```

Final

```text
[1,2,3]
```

Answer

```text
3
```

---

# Approach 2 — Counting Sort

Instead of sorting,

store frequencies.

Traverse values from

```text
1 → n
```

and greedily assign the largest possible valid value.

---

# Approach 3 — Sorting + New Array

Sort the array.

Create another array.

Build the answer greedily instead of modifying the original array.

---

# Complexity Analysis

## Approach 1

**Time**

```text
O(n log n)
```

**Space**

```text
O(1)
```

---

## Approach 2

**Time**

```text
O(n)
```

**Space**

```text
O(n)
```

---

## Approach 3

**Time**

```text
O(n log n)
```

**Space**

```text
O(n)
```

---

# Complexity Comparison

| Approach            | Time       | Space |
| ------------------- | ---------- | ----- |
| Sorting + Greedy    | O(n log n) | O(1)  |
| Counting Sort       | O(n)       | O(n)  |
| Sorting + New Array | O(n log n) | O(n)  |

---

# Java Solution

## Approach 1 — Sorting + Greedy

```java
//Approach-1 (Sorting + Greedy)
//T.C : O(n log n)
//S.C : O(1)

import java.util.Arrays;

class Solution {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {

        Arrays.sort(arr);

        arr[0] = 1;

        for (int i = 1; i < arr.length; i++) {

            arr[i] = Math.min(
                arr[i],
                arr[i - 1] + 1
            );
        }

        return arr[arr.length - 1];
    }
}
```

---

## Approach 2 — Counting Sort / Frequency Array

```java
//Approach-2 (Counting Sort / Frequency Array)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {

        int n = arr.length;

        int[] frequency = new int[n + 1];

        for (int value : arr) {

            frequency[Math.min(value, n)]++;
        }

        int current = 0;

        for (int value = 1; value <= n; value++) {

            if (frequency[value] == 0) {

                current = Math.min(current, value - 1);

            } else {

                current = Math.min(current + frequency[value], value);
            }
        }

        return current;
    }
}
```

---

## Approach 3 — Sorting + Construct New Array

```java
//Approach-3 (Sorting + Construct New Array)
//T.C : O(n log n)
//S.C : O(n)

import java.util.Arrays;

class Solution {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {

        Arrays.sort(arr);

        int n = arr.length;

        int[] result = new int[n];

        result[0] = 1;

        for (int i = 1; i < n; i++) {

            if (arr[i] >= result[i - 1] + 1) {

                result[i] = result[i - 1] + 1;

            } else {

                result[i] = arr[i];
            }
        }

        return result[n - 1];
    }
}
```

---

# C++ Solution

## Approach 1 — Sorting + Greedy

```cpp
//Approach-1 (Sorting + Greedy)
//T.C : O(n log n)
//S.C : O(1)

class Solution {
public:

    int maximumElementAfterDecrementingAndRearranging(vector<int>& arr) {

        sort(arr.begin(), arr.end());

        arr[0] = 1;

        for (int i = 1; i < arr.size(); i++) {

            arr[i] = min(
                arr[i],
                arr[i - 1] + 1
            );
        }

        return arr.back();
    }
};
```

---

## Approach 2 — Counting Sort / Frequency Array

```cpp
//Approach-2 (Counting Sort / Frequency Array)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    int maximumElementAfterDecrementingAndRearranging(vector<int>& arr) {

        int n = arr.size();

        vector<int> frequency(n + 1, 0);

        for (int value : arr) {

            frequency[min(value, n)]++;
        }

        int current = 0;

        for (int value = 1; value <= n; value++) {

            if (frequency[value] == 0) {

                current = min(current, value - 1);

            } else {

                current = min(current + frequency[value], value);
            }
        }

        return current;
    }
};
```

---

## Approach 3 — Sorting + Construct New Array

```cpp
//Approach-3 (Sorting + Construct New Array)
//T.C : O(n log n)
//S.C : O(n)

class Solution {
public:

    int maximumElementAfterDecrementingAndRearranging(vector<int>& arr) {

        sort(arr.begin(), arr.end());

        int n = arr.size();

        vector<int> result(n);

        result[0] = 1;

        for (int i = 1; i < n; i++) {

            if (arr[i] >= result[i - 1] + 1) {

                result[i] = result[i - 1] + 1;

            } else {

                result[i] = arr[i];
            }
        }

        return result[n - 1];
    }
};
```
