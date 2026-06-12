class Solution
{
public:
  bool areSimilar(vector<vector<int>> &mat, int k)
  {

    int rows = mat.size();
    int cols = mat[0].size();

    k %= cols;

    for (int row = 0; row < rows; row++)
    {

      for (int col = 0; col < cols; col++)
      {

        if (row % 2 == 0 &&
            mat[row][col] != mat[row][(col + k) % cols])
        {
          return false;
        }

        if (row % 2 == 1 &&
            mat[row][col] != mat[row][(col + cols - k) % cols])
        {
          return false;
        }
      }
    }

    return true;
  }
};