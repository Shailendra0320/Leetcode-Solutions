// Approach-1 (Greedy + Frequency Counting + Backtracking)
// T.C : O(26 * n)
// S.C : O(n)

class Solution
{
public:
  string lexGreaterPermutation(
      string s,
      string target)
  {

    int n =
        s.length();

    string quinorath =
        s;

    vector<int> freq(
        26,
        0);

    for (char c : s)
    {
      freq[c - 'a']++;
    }

    int i = 0;

    while (
        i < n &&
        freq[target[i] - 'a'] > 0)
    {

      freq[target[i] - 'a']--;
      i++;
    }

    for (
        int pos = i - 1;
        pos >= 0;
        pos--)
    {

      freq[target[pos] - 'a']++;

      int current =
          target[pos] - 'a';

      for (
          int c = current + 1;
          c < 26;
          c++)
      {

        if (freq[c] == 0)
        {
          continue;
        }

        string ans;

        for (
            int j = 0;
            j < pos;
            j++)
        {

          ans +=
              target[j];
        }

        ans +=
            char('a' + c);

        freq[c]--;

        for (
            int ch = 0;
            ch < 26;
            ch++)
        {

          while (freq[ch] > 0)
          {

            ans +=
                char('a' + ch);

            freq[ch]--;
          }
        }

        return ans;
      }
    }

    return "";
  }
};

// Approach-2 (Greedy + Next Permutation Style)
// T.C : O(26 * n)
// S.C : O(n)

class Solution
{
public:
  string lexGreaterPermutation(
      string s,
      string target)
  {

    int n =
        s.length();

    vector<int> freq(
        26,
        0);

    for (char c : s)
    {
      freq[c - 'a']++;
    }

    string prefix;

    for (
        int i = 0;
        i < n;
        i++)
    {

      int targetChar =
          target[i] - 'a';

      if (freq[targetChar] > 0)
      {

        freq[targetChar]--;

        prefix +=
            target[i];
      }
      else
      {

        for (
            int c = targetChar + 1;
            c < 26;
            c++)
        {

          if (freq[c] == 0)
          {
            continue;
          }

          string ans =
              prefix;

          ans +=
              char('a' + c);

          freq[c]--;

          for (
              int ch = 0;
              ch < 26;
              ch++)
          {

            while (freq[ch] > 0)
            {

              ans +=
                  char('a' + ch);

              freq[ch]--;
            }
          }

          return ans;
        }

        break;
      }
    }

    for (
        int pos = n - 1;
        pos >= 0;
        pos--)
    {

      int current =
          prefix[pos] - 'a';

      freq[current]++;

      for (
          int c = current + 1;
          c < 26;
          c++)
      {

        if (freq[c] == 0)
        {
          continue;
        }

        string ans;

        for (
            int j = 0;
            j < pos;
            j++)
        {

          ans +=
              prefix[j];
        }

        ans +=
            char('a' + c);

        freq[c]--;

        for (
            int ch = 0;
            ch < 26;
            ch++)
        {

          while (freq[ch] > 0)
          {

            ans +=
                char('a' + ch);

            freq[ch]--;
          }
        }

        return ans;
      }
    }

    return "";
  }
};