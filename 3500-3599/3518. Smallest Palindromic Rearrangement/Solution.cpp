class Solution
{
public:
  string smallestPalindrome(
      string s,
      int k)
  {

    int n = s.length();

    vector<int> freq(26, 0);

    for (char c : s)
    {
      freq[c - 'a']++;
    }

    int midChar = -1;

    for (int i = 0; i < 26; i++)
    {
      if (freq[i] % 2 != 0)
      {
        midChar = i;
      }
    }

    vector<int> halfFreq(26, 0);

    int halfLen = 0;

    for (int i = 0; i < 26; i++)
    {
      halfFreq[i] = freq[i] / 2;
      halfLen += halfFreq[i];
    }

    long long CAP = 3000000000LL;

    long long total =
        countWays(
            halfFreq,
            halfLen,
            CAP);

    if (total < k)
    {
      return "";
    }

    string firstHalf;

    long long leftK = k;
    int leftLen = halfLen;

    for (int pos = 0; pos < halfLen; pos++)
    {

      for (int c = 0; c < 26; c++)
      {

        if (halfFreq[c] == 0)
        {
          continue;
        }

        halfFreq[c]--;

        long long ways =
            countWays(
                halfFreq,
                leftLen - 1,
                CAP);

        if (leftK <= ways)
        {

          firstHalf +=
              char('a' + c);

          break;
        }
        else
        {

          leftK -= ways;
          halfFreq[c]++;
        }
      }

      leftLen--;
    }

    string result =
        firstHalf;

    if (midChar != -1)
    {
      result +=
          char('a' + midChar);
    }

    reverse(
        firstHalf.begin(),
        firstHalf.end());

    result += firstHalf;

    return result;
  }

private:
  long long countWays(
      vector<int> &freq,
      int total,
      long long cap)
  {

    long long ways = 1;
    int rem = total;

    for (
        int i = 0;
        i < 26 && ways <= cap;
        i++)
    {

      int f = freq[i];
      long long comb = 1;

      for (int j = 1; j <= f; j++)
      {

        comb =
            comb * (rem - f + j) / j;

        if (comb > cap)
        {
          comb = cap + 1;
          break;
        }
      }

      ways *= comb;

      if (ways > cap)
      {
        ways = cap + 1;
        break;
      }

      rem -= f;
    }

    return ways;
  }
};