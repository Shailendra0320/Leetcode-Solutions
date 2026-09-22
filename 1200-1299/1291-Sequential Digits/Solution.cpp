#include <bits/stdc++.h>
using namespace std;

// Approach-1 (Direct Generation)
// T.C : O(1)
// S.C : O(1)

class Solution
{
public:
  vector<int> sequentialDigits(int low, int high)
  {
    vector<int> ans;
    string digits = "123456789";

    for (int len = 2; len <= 9; len++)
    {
      for (int i = 0; i + len <= 9; i++)
      {
        int num = stoi(digits.substr(i, len));

        if (num >= low && num <= high)
        {
          ans.push_back(num);
        }
      }
    }

    return ans;
  }
};

// Approach-2 (Recursive Construction)
// T.C : O(1)
// S.C : O(1)

class Solution2
{
public:
  vector<int> sequentialDigits(int low, int high)
  {
    vector<int> ans;

    for (int start = 1; start <= 8; start++)
    {
      generate(start, start, low, high, ans);
    }

    sort(ans.begin(), ans.end());
    return ans;
  }

private:
  void generate(int lastDigit, int num, int low, int high, vector<int> &ans)
  {
    if (lastDigit == 9)
    {
      return;
    }

    int next = num * 10 + (lastDigit + 1);

    if (next > high)
    {
      return;
    }

    if (next >= low)
    {
      ans.push_back(next);
    }

    generate(lastDigit + 1, next, low, high, ans);
  }
};