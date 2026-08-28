// Approach-1 (Backtracking + Greedy + Frequency Counting)
// T.C : O(26 * n^2)
// S.C : O(n)

class Solution
{
public:
  string lexPalindromicPermutation(
      string s,
      string target)
  {

    int n =
        s.length();

    string calendrix =
        s;

    vector<int> freq(
        26,
        0);

    for (char c : s)
    {
      freq[c - 'a']++;
    }

    int oddCount = 0;
    char middle = 0;

    for (int i = 0; i < 26; i++)
    {

      if (freq[i] % 2 != 0)
      {

        oddCount++;

        middle =
            char('a' + i);
      }
    }

    if (oddCount > 1)
    {
      return "";
    }

    int halfLen =
        n / 2;

    vector<int> halfFreq(
        26,
        0);

    for (int i = 0; i < 26; i++)
    {

      halfFreq[i] =
          freq[i] / 2;
    }

    string left;

    for (int pos = 0; pos < halfLen; pos++)
    {

      bool found =
          false;

      for (int c = 0; c < 26; c++)
      {

        if (halfFreq[c] == 0)
        {
          continue;
        }

        halfFreq[c]--;

        left +=
            char('a' + c);

        if (
            canMakeGreater(
                left,
                halfFreq,
                middle,
                target))
        {

          found =
              true;

          break;
        }

        left.pop_back();

        halfFreq[c]++;
      }

      if (!found)
      {
        return "";
      }
    }

    return build(
        left,
        halfFreq,
        middle);
  }

private:
  bool canMakeGreater(
      string &left,
      vector<int> &freq,
      char middle,
      string &target)
  {

    string suffix;

    for (int c = 25; c >= 0; c--)
    {

      for (
          int count = 0;
          count < freq[c];
          count++)
      {

        suffix +=
            char('a' + c);
      }
    }

    string candidate =
        left +
        suffix;

    if (
        target.length() % 2 == 1)
    {
      candidate +=
          middle;
    }

    string rev =
        candidate;

    reverse(
        rev.begin(),
        rev.end());

    candidate +=
        rev;

    return candidate > target;
  }

  string build(
      string left,
      vector<int> &freq,
      char middle)
  {

    string firstHalf =
        left;

    for (int c = 0; c < 26; c++)
    {

      while (freq[c] > 0)
      {

        firstHalf +=
            char('a' + c);

        freq[c]--;
      }
    }

    string result =
        firstHalf;

    if (middle != 0)
    {
      result +=
          middle;
    }

    string rev =
        firstHalf;

    reverse(
        rev.begin(),
        rev.end());

    result +=
        rev;

    return result;
  }
};

// Approach-2 (Two Pointers + Greedy + Frequency Counting)
// T.C : O(26 * n^2)
// S.C : O(n)

class Solution
{
public:
  string lexPalindromicPermutation(
      string s,
      string target)
  {

    int n =
        s.length();

    string calendrix =
        s;

    vector<int> freq(
        26,
        0);

    for (char c : s)
    {
      freq[c - 'a']++;
    }

    int oddCount = 0;
    char middle = 0;

    for (int i = 0; i < 26; i++)
    {

      if (freq[i] % 2 != 0)
      {

        oddCount++;

        middle =
            char('a' + i);
      }
    }

    if (oddCount > 1)
    {
      return "";
    }

    int halfLen =
        n / 2;

    vector<int> halfFreq(
        26,
        0);

    for (int i = 0; i < 26; i++)
    {

      halfFreq[i] =
          freq[i] / 2;
    }

    string left;

    for (int i = 0; i < halfLen; i++)
    {

      int targetChar =
          target[i] - 'a';

      bool placed =
          false;

      if (halfFreq[targetChar] > 0)
      {

        halfFreq[targetChar]--;

        left +=
            target[i];

        placed =
            true;
      }

      if (!placed)
      {
        break;
      }
    }

    for (
        int pos = (int)left.length() - 1;
        pos >= 0;
        pos--)
    {

      int original =
          left[pos] - 'a';

      halfFreq[original]++;

      for (
          int c = original + 1;
          c < 26;
          c++)
      {

        if (halfFreq[c] == 0)
        {
          continue;
        }

        left[pos] =
            char('a' + c);

        halfFreq[c]--;

        string firstHalf =
            left;

        for (int x = 0; x < 26; x++)
        {

          while (
              halfFreq[x] > 0)
          {

            firstHalf +=
                char('a' + x);

            halfFreq[x]--;
          }
        }

        string result =
            firstHalf;

        if (middle != 0)
        {
          result +=
              middle;
        }

        string rev =
            firstHalf;

        reverse(
            rev.begin(),
            rev.end());

        result +=
            rev;

        if (
            result > target)
        {
          return result;
        }

        for (int x = 0; x < 26; x++)
        {
          halfFreq[x] = 0;
        }

        vector<int> temp(
            26,
            0);

        for (
            char ch : s)
        {
          temp[ch - 'a']++;
        }

        for (int x = 0; x < (int)left.length(); x++)
        {
          temp[left[x] - 'a']--;
        }

        halfFreq =
            temp;

        for (int x = 0; x < 26; x++)
        {
          halfFreq[x] /= 2;
        }
      }

      left[pos] =
          char('a' + original);
    }

    return "";
  }
};