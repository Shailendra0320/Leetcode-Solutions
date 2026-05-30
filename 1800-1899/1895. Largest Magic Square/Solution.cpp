class Solution
{
public:
  bool checkMagicSquare(
      int size,
      int totalRows,
      int totalCols,
      vector<vector<int>> &prefixRow,
      vector<vector<int>> &prefixCol,
      vector<vector<int>> &mainDiagonal,
      vector<vector<int>> &secondaryDiagonal)
  {

    for (int startRow = 0; startRow <= totalRows - size; startRow++)
    {

      for (int startCol = 0; startCol <= totalCols - size; startCol++)
      {

        int diagonalSum =
            mainDiagonal[startRow + size][startCol + size] - mainDiagonal[startRow][startCol];

        int antiDiagonalSum =
            secondaryDiagonal[startRow + size][startCol] - secondaryDiagonal[startRow][startCol + size];

        if (diagonalSum != antiDiagonalSum)
        {
          continue;
        }

        bool isValidSquare = true;

        for (int index = 0; index < size; index++)
        {

          int currentRowSum =
              prefixRow[startRow + index + 1][startCol + size] - prefixRow[startRow + index + 1][startCol];

          int currentColSum =
              prefixCol[startRow + size][startCol + index + 1] - prefixCol[startRow][startCol + index + 1];

          if (currentRowSum != diagonalSum ||
              currentColSum != diagonalSum)
          {

            isValidSquare = false;
            break;
          }
        }

        if (isValidSquare)
        {
          return true;
        }
      }
    }

    return false;
  }

  int largestMagicSquare(vector<vector<int>> &matrix)
  {

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

    for (int row = 0; row < rows; row++)
    {

      for (int col = 0; col < cols; col++)
      {

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

    for (int size = min(rows, cols); size >= 2; size--)
    {

      if (checkMagicSquare(
              size,
              rows,
              cols,
              prefixRow,
              prefixCol,
              mainDiagonal,
              secondaryDiagonal))
      {

        return size;
      }
    }

    return 1;
  }
};