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

# 1895. Largest Magic Square

# Intuition

A Magic Square is a square matrix where:

- Every row sum is equal
- Every column sum is equal
- Main diagonal sum is equal
- Anti-diagonal sum is equal

We need to find the largest magic square present inside the matrix.

---

# Approach

Instead of calculating sums repeatedly:

We precompute:

- Row Prefix Sum
- Column Prefix Sum
- Main Diagonal Prefix Sum
- Anti-Diagonal Prefix Sum

Using prefix sums:

```text
Any row sum  -> O(1)
Any col sum  -> O(1)
Any diagonal -> O(1)
```

Then:

- Try square sizes from largest to smallest
- Check every possible square
- Return first valid size

---

# Prefix Sum Structure

## Row Prefix

```text
1 2 3

Row Prefix

1 3 6
```

---

## Column Prefix

```text
1
2
3

Column Prefix

1
3
6
```

---

## Diagonal Prefix

```text
1 2 3
4 5 6
7 8 9

Main Diagonal

1 + 5 + 9
```

---

# Magic Square Verification

For every square:

```text
Get Main Diagonal Sum

        ↓

Get Anti-Diagonal Sum

        ↓

Compare

        ↓

Check Every Row

        ↓

Check Every Column

        ↓

Valid ?
```

---

# Diagram

```text
8 1 6
3 5 7
4 9 2
```

Row sums:

```text
8+1+6 = 15
3+5+7 = 15
4+9+2 = 15
```

Column sums:

```text
8+3+4 = 15
1+5+9 = 15
6+7+2 = 15
```

Diagonals:

```text
8+5+2 = 15
6+5+4 = 15
```

Magic Square ✅

---

# Complexity

## Time Complexity

```text
O(min(m,n) × m × n × size)
```

Worst Case:

```text
O(m × n × min(m,n)^2)
```

---

## Space Complexity

```text
O(m × n)
```

Used for prefix arrays.

---

# Java Solution

```java
class Solution {

    public int largestMagicSquare(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] prefixRow = new int[rows + 1][cols + 1];
        int[][] prefixCol = new int[rows + 1][cols + 1];
        int[][] mainDiagonal = new int[rows + 1][cols + 1];
        int[][] secondaryDiagonal = new int[rows + 1][cols + 1];

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                int value = matrix[row][col];

                prefixRow[row + 1][col + 1] =
                    prefixRow[row + 1][col] + value;

                prefixCol[row + 1][col + 1] =
                    prefixCol[row][col + 1] + value;

                mainDiagonal[row + 1][col + 1] =
                    mainDiagonal[row][col] + value;

                secondaryDiagonal[row + 1][col] =
                    secondaryDiagonal[row][col + 1] + value;
            }
        }

        for (int size = Math.min(rows, cols); size >= 2; size--) {

            if (checkMagicSquare(
                    size,
                    rows,
                    cols,
                    prefixRow,
                    prefixCol,
                    mainDiagonal,
                    secondaryDiagonal)) {

                return size;
            }
        }

        return 1;
    }

    private boolean checkMagicSquare(
            int size,
            int totalRows,
            int totalCols,
            int[][] prefixRow,
            int[][] prefixCol,
            int[][] mainDiagonal,
            int[][] secondaryDiagonal) {

        for (int startRow = 0; startRow <= totalRows - size; startRow++) {

            for (int startCol = 0; startCol <= totalCols - size; startCol++) {

                int diagonalSum =
                    mainDiagonal[startRow + size][startCol + size]
                    - mainDiagonal[startRow][startCol];

                int antiDiagonalSum =
                    secondaryDiagonal[startRow + size][startCol]
                    - secondaryDiagonal[startRow][startCol + size];

                if (diagonalSum != antiDiagonalSum) {
                    continue;
                }

                boolean isValidSquare = true;

                for (int index = 0; index < size; index++) {

                    int currentRowSum =
                        prefixRow[startRow + index + 1][startCol + size]
                        - prefixRow[startRow + index + 1][startCol];

                    int currentColSum =
                        prefixCol[startRow + size][startCol + index + 1]
                        - prefixCol[startRow][startCol + index + 1];

                    if (currentRowSum != diagonalSum ||
                        currentColSum != diagonalSum) {

                        isValidSquare = false;
                        break;
                    }
                }

                if (isValidSquare) {
                    return true;
                }
            }
        }

        return false;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    bool checkMagicSquare(
        int size,
        int totalRows,
        int totalCols,
        vector<vector<int>>& prefixRow,
        vector<vector<int>>& prefixCol,
        vector<vector<int>>& mainDiagonal,
        vector<vector<int>>& secondaryDiagonal) {

        for (int startRow = 0; startRow <= totalRows - size; startRow++) {

            for (int startCol = 0; startCol <= totalCols - size; startCol++) {

                int diagonalSum =
                    mainDiagonal[startRow + size][startCol + size]
                    - mainDiagonal[startRow][startCol];

                int antiDiagonalSum =
                    secondaryDiagonal[startRow + size][startCol]
                    - secondaryDiagonal[startRow][startCol + size];

                if (diagonalSum != antiDiagonalSum) {
                    continue;
                }

                bool isValidSquare = true;

                for (int index = 0; index < size; index++) {

                    int currentRowSum =
                        prefixRow[startRow + index + 1][startCol + size]
                        - prefixRow[startRow + index + 1][startCol];

                    int currentColSum =
                        prefixCol[startRow + size][startCol + index + 1]
                        - prefixCol[startRow][startCol + index + 1];

                    if (currentRowSum != diagonalSum ||
                        currentColSum != diagonalSum) {

                        isValidSquare = false;
                        break;
                    }
                }

                if (isValidSquare) {
                    return true;
                }
            }
        }

        return false;
    }

    int largestMagicSquare(vector<vector<int>>& matrix) {

        int rows = matrix.size();
        int cols = matrix[0].size();

        vector<vector<int>> prefixRow(
            rows + 1,
            vector<int>(cols + 1, 0));

        vector<vector<int>> prefixCol(
            rows + 1,
            vector<int>(cols + 1, 0));

        vector<vector<int>> mainDiagonal(
            rows + 1,
            vector<int>(cols + 1, 0));

        vector<vector<int>> secondaryDiagonal(
            rows + 1,
            vector<int>(cols + 1, 0));

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                int value = matrix[row][col];

                prefixRow[row + 1][col + 1] =
                    prefixRow[row + 1][col] + value;

                prefixCol[row + 1][col + 1] =
                    prefixCol[row][col + 1] + value;

                mainDiagonal[row + 1][col + 1] =
                    mainDiagonal[row][col] + value;

                secondaryDiagonal[row + 1][col] =
                    secondaryDiagonal[row][col + 1] + value;
            }
        }

        for (int size = min(rows, cols); size >= 2; size--) {

            if (checkMagicSquare(
                    size,
                    rows,
                    cols,
                    prefixRow,
                    prefixCol,
                    mainDiagonal,
                    secondaryDiagonal)) {

                return size;
            }
        }

        return 1;
    }
};
```
