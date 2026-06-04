class Solution
{
public:
  int calculateWaviness(int num)
  {

    if (num < 100)
    {
      return 0;
    }

    string s = to_string(num);

    int wavinessCount = 0;

    for (int i = 1; i < s.length() - 1; i++)
    {

      char current = s[i];
      char left = s[i - 1];
      char right = s[i + 1];

      if (current > left && current > right)
      {

        wavinessCount++;
      }
      else if (current < left && current < right)
      {

        wavinessCount++;
      }
    }

    return wavinessCount;
  }

  int totalWaviness(int num1, int num2)
  {

    int totalWavinessSum = 0;

    for (int i = num1; i <= num2; i++)
    {

      totalWavinessSum += calculateWaviness(i);
    }

    return totalWavinessSum;
  }
};