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

# 2946. Matrix Similarity After Cyclic Shifts

# Intuition

For each row:

```text
Even Row Index
→ Shift Left by k

Odd Row Index
→ Shift Right by k
```

After performing these cyclic shifts:

```text
The matrix should remain unchanged.
```

Instead of actually shifting rows:

```text
O(m × n × k)
```

we directly check where each element would move after the shift.

---

# Key Observation

For an even row:

```text
newPosition = (j + k) % n
```

For an odd row:

```text
newPosition = (j + n - k) % n
```

If the element at the current position differs from the element at the shifted position:

```text
Matrix is not similar.
```

---

# Approach

## Step 1

Find:

```text
rows = m
cols = n
```

---

## Step 2

Reduce:

```text
k %= n
```

because shifting n times returns the row to its original state.

---

## Step 3

Traverse every cell.

For even rows:

```text
mat[i][j]
==
mat[i][(j+k)%n]
```

For odd rows:

```text
mat[i][j]
==
mat[i][(j+n-k)%n]
```

---

## Step 4

If any mismatch occurs:

```text
return false
```

Otherwise:

```text
return true
```

---

# Flowchart

```text
Start

   │
   ▼

Read Matrix

   │
   ▼

k = k % n

   │
   ▼

Traverse Every Cell

   │
   ▼

Even Row ?

 ┌───────┴────────┐
 │                │
Yes             No
 │                │
 ▼                ▼

Check         Check
(j+k)%n      (j+n-k)%n

 │                │
 └───────┬────────┘
         ▼

Mismatch ?

 ┌───────┴────────┐
 │                │
Yes             No
 │                │
 ▼                ▼

Return False   Continue

         │
         ▼

All Cells Checked

         │
         ▼

Return True
```

---

# Visualization

Input

```text
mat =

[
 [1,2,1,2],
 [5,5,5,5],
 [6,3,6,3]
]

k = 2
```

---

Even Row 0

```text
[1,2,1,2]

Shift Left 2

[1,2,1,2]
```

Same ✅

---

Odd Row 1

```text
[5,5,5,5]

Shift Right 2

[5,5,5,5]
```

Same ✅

---

Even Row 2

```text
[6,3,6,3]

Shift Left 2

[6,3,6,3]
```

Same ✅

---

Result

```text
true
```

---

# Detailed Dry Run

Input

```text
mat =

[
 [1,2,1,2],
 [5,5,5,5],
 [6,3,6,3]
]

k = 2
```

---

Row 0

```text
1 == 1 ✓
2 == 2 ✓
1 == 1 ✓
2 == 2 ✓
```

---

Row 1

```text
5 == 5 ✓
5 == 5 ✓
5 == 5 ✓
5 == 5 ✓
```

---

Row 2

```text
6 == 6 ✓
3 == 3 ✓
6 == 6 ✓
3 == 3 ✓
```

---

Final

```text
No mismatch found
```

Answer:

```text
true
```

---

# Memory Visualization

```text
Matrix

[1,2,1,2]
[5,5,5,5]
[6,3,6,3]

       │
       ▼

Even Rows

Shift Left

       │
       ▼

Odd Rows

Shift Right

       │
       ▼

Compare Elements

       │
       ▼

No Mismatch

       │
       ▼

Return True
```

---

# Complexity Analysis

## Time Complexity

```text
O(m × n)
```

Every cell is checked once.

---

## Space Complexity

```text
O(1)
```

No extra matrix is created.

---

# Java Solution

```java
class Solution {

    public boolean areSimilar(int[][] mat, int k) {

        int m = mat.length;
        int n = mat[0].length;

        k %= n;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i % 2 == 0 &&
                    mat[i][j] != mat[i][(j + k) % n]) {
                    return false;
                }

                if (i % 2 == 1 &&
                    mat[i][j] != mat[i][(j + n - k) % n]) {
                    return false;
                }
            }
        }

        return true;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    bool areSimilar(vector<vector<int>>& mat, int k) {

        int rows = mat.size();
        int cols = mat[0].size();

        k %= cols;

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                if (row % 2 == 0 &&
                    mat[row][col] != mat[row][(col + k) % cols]) {
                    return false;
                }

                if (row % 2 == 1 &&
                    mat[row][col] != mat[row][(col + cols - k) % cols]) {
                    return false;
                }
            }
        }

        return true;
    }
};
```
