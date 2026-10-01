#include <bits/stdc++.h>
using namespace std;

class Solution
{

  // Approach-1 (Stack with Direct Matching)
  // T.C : O(n)
  // S.C : O(n)

public:
  bool isValid(string s)
  {
    stack<char> st;

    for (char ch : s)
    {
      if (ch == '(' || ch == '[' || ch == '{')
      {
        st.push(ch);
      }
      else
      {
        if (st.empty())
        {
          return false;
        }

        char top = st.top();
        st.pop();

        if ((ch == ')' && top != '(') ||
            (ch == ']' && top != '[') ||
            (ch == '}' && top != '{'))
        {
          return false;
        }
      }
    }

    return st.empty();
  }
};

class Solution2
{

  // Approach-2 (Stack of Expected Closing Brackets)
  // T.C : O(n)
  // S.C : O(n)

public:
  bool isValid(string s)
  {
    stack<char> st;

    for (char ch : s)
    {
      if (ch == '(')
      {
        st.push(')');
      }
      else if (ch == '[')
      {
        st.push(']');
      }
      else if (ch == '{')
      {
        st.push('}');
      }
      else
      {
        if (st.empty() || st.top() != ch)
        {
          return false;
        }

        st.pop();
      }
    }

    return st.empty();
  }
};