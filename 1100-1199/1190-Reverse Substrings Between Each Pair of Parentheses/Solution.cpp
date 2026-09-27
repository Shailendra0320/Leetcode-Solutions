#include <bits/stdc++.h>
using namespace std;

// Approach-1 (Matching Parentheses + Direction Reversal)
// T.C : O(n)
// S.C : O(n)

class Solution
{
public:
  string reverseParentheses(string s)
  {
    int n = s.size();

    vector<int> pair(n);
    stack<int> st;

    for (int i = 0; i < n; i++)
    {
      if (s[i] == '(')
      {
        st.push(i);
      }
      else if (s[i] == ')')
      {
        int open = st.top();
        st.pop();

        pair[open] = i;
        pair[i] = open;
      }
    }

    string ans;

    int i = 0;
    int direction = 1;

    while (i >= 0 && i < n)
    {
      if (s[i] == '(' || s[i] == ')')
      {
        i = pair[i];
        direction = -direction;
      }
      else
      {
        ans += s[i];
      }

      i += direction;
    }

    return ans;
  }
};

// Approach-2 (Stack of Partial Strings)
// T.C : O(n^2) worst-case
// S.C : O(n)

class Solution2
{
public:
  string reverseParentheses(string s)
  {
    stack<string> st;
    string current;

    for (char ch : s)
    {
      if (ch == '(')
      {
        st.push(current);
        current.clear();
      }
      else if (ch == ')')
      {
        reverse(current.begin(), current.end());

        string outer = st.top();
        st.pop();

        outer += current;
        current = outer;
      }
      else
      {
        current += ch;
      }
    }

    return current;
  }
};