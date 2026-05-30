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

        prefixRow[row + 1][col + 1] = prefixRow[row + 1][col] + value;
        prefixCol[row + 1][col + 1] = prefixCol[row][col + 1] + value;
        mainDiagonal[row + 1][col + 1] = mainDiagonal[row][col] + value;
        secondaryDiagonal[row + 1][col] = secondaryDiagonal[row][col + 1] + value;
      }
    }

    for (int size = Math.min(rows, cols); size >= 2; size--) {
      if (checkMagicSquare(size, rows, cols, prefixRow, prefixCol,
          mainDiagonal, secondaryDiagonal)) {
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

        int diagonalSum = mainDiagonal[startRow + size][startCol + size]
            - mainDiagonal[startRow][startCol];

        int antiDiagonalSum = secondaryDiagonal[startRow + size][startCol]
            - secondaryDiagonal[startRow][startCol + size];

        if (diagonalSum != antiDiagonalSum) {
          continue;
        }

        boolean isValidSquare = true;

        for (int index = 0; index < size; index++) {

          int currentRowSum = prefixRow[startRow + index + 1][startCol + size]
              - prefixRow[startRow + index + 1][startCol];

          int currentColSum = prefixCol[startRow + size][startCol + index + 1]
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