// Approach-1 (Greedy + Suffix Matching)
// T.C : O(n + m)
// S.C : O(m)

class Solution
{
public:
  vector<int> validSequence(
      string word1,
      string word2)
  {

    int n =
        word1.length();

    int m =
        word2.length();

    vector<int> last(
        m + 1);

    last[m] =
        n;

    int p =
        n - 1;

    for (
        int j = m - 1;
        j >= 0;
        j--)
    {

      while (
          p >= 0 &&
          word1[p] !=
              word2[j])
      {

        p--;
      }

      last[j] =
          p;

      if (
          p >= 0)
      {

        p--;
      }
    }

    vector<int> res(m);

    bool changed =
        false;

    int i = 0;

    for (
        int j = 0;
        j < m;
        j++)
    {

      bool found =
          false;

      while (
          i < n)
      {

        bool match =
            word1[i] ==
            word2[j];

        if (
            match)
        {

          if (
              changed)
          {

            if (
                last[j + 1] >=
                i + 1)
            {

              res[j] =
                  i;

              i++;

              found =
                  true;

              break;
            }
          }
          else
          {

            res[j] =
                i;

            i++;

            found =
                true;

            break;
          }
        }
        else
        {

          if (
              !changed &&
              last[j + 1] >=
                  i + 1)
          {

            res[j] =
                i;

            changed =
                true;

            i++;

            found =
                true;

            break;
          }
        }

        i++;
      }

      if (
          !found)
      {

        return {};
      }
    }

    return res;
  }
};