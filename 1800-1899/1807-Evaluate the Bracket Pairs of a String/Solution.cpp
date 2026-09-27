#include <bits/stdc++.h>
using namespace std;

// Approach-1 (Single-Pass Parsing with HashMap)
// T.C : O(n + total knowledge size)
// S.C : O(k)

class Solution
{
public:
  string evaluate(
      string s,
      vector<vector<string>> &knowledge)
  {
    unordered_map<string, string> mp;

    for (auto &pair : knowledge)
    {
      mp[pair[0]] = pair[1];
    }

    string ans;

    for (int i = 0; i < s.size();)
    {
      if (s[i] != '(')
      {
        ans += s[i];
        i++;
        continue;
      }

      int j = i + 1;

      while (s[j] != ')')
      {
        j++;
      }

      string key = s.substr(i + 1, j - i - 1);

      if (mp.count(key))
      {
        ans += mp[key];
      }
      else
      {
        ans += '?';
      }

      i = j + 1;
    }

    return ans;
  }
};

// Approach-2 (Two-Pointer Parsing with Helper)
// T.C : O(n + total knowledge size)
// S.C : O(k)

class Solution2
{
public:
  string evaluate(
      string s,
      vector<vector<string>> &knowledge)
  {
    unordered_map<string, string> mp;

    for (auto &pair : knowledge)
    {
      mp[pair[0]] = pair[1];
    }

    string ans;
    int i = 0;

    while (i < s.size())
    {
      if (s[i] != '(')
      {
        ans += s[i];
        i++;
        continue;
      }

      int end = i + 1;

      while (s[end] != ')')
      {
        end++;
      }

      string key = getKey(s, i + 1, end);

      if (mp.count(key))
      {
        ans += mp[key];
      }
      else
      {
        ans += '?';
      }

      i = end + 1;
    }

    return ans;
  }

private:
  string getKey(
      const string &s,
      int left,
      int right)
  {
    string key;

    while (left < right)
    {
      key += s[left];
      left++;
    }

    return key;
  }
};