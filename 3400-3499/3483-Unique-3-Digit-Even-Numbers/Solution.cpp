// Approach-1 (Three-Level Enumeration + HashSet)
// T.C : O(n^3)
// S.C : O(n^3) worst case

class Solution
{
public:
  int totalNumbers(vector<int> &digits)
  {
    unordered_set<int> set;

    int n = digits.size();

    for (int i = 0; i < n; i++)
    {

      // Hundreds digit cannot be zero.
      if (digits[i] == 0)
      {
        continue;
      }

      for (int j = 0; j < n; j++)
      {

        if (j == i)
        {
          continue;
        }

        for (int k = 0; k < n; k++)
        {

          if (k == i || k == j)
          {
            continue;
          }

          // Units digit must be even.
          if (digits[k] % 2 != 0)
          {
            continue;
          }

          int number =
              digits[i] * 100 +
              digits[j] * 10 +
              digits[k];

          set.insert(number);
        }
      }
    }

    return set.size();
  }
};

// Approach-2 (Backtracking + Frequency Array)
// T.C : O(10^3)
// S.C : O(10)

class Solution2
{
public:
  int freq[10] = {};
  unordered_set<int> set;

  int totalNumbers(vector<int> &digits)
  {

    for (int digit : digits)
    {
      freq[digit]++;
    }

    backtrack(0, 0);

    return set.size();
  }

  void backtrack(int position, int number)
  {

    if (position == 3)
    {
      set.insert(number);
      return;
    }

    for (int digit = 0; digit <= 9; digit++)
    {

      if (freq[digit] == 0)
      {
        continue;
      }

      // Hundreds digit cannot be zero.
      if (position == 0 && digit == 0)
      {
        continue;
      }

      // Units digit must be even.
      if (position == 2 && digit % 2 != 0)
      {
        continue;
      }

      freq[digit]--;

      backtrack(
          position + 1,
          number * 10 + digit);

      freq[digit]++;
    }
  }
};