#include <bits/stdc++.h>
using namespace std;

// Approach-1 (Greedy Required-Closing Counter)
// T.C : O(n)
// S.C : O(1)

class Solution
{

public:
  int minInsertions(string s)
  {
    int answer = 0;
    int need = 0;

    for (char ch : s)
    {

      if (ch == '(')
      {
        need += 2;

        if (need % 2 == 1)
        {
          answer++;
          need--;
        }
      }
      else
      {
        need--;

        if (need < 0)
        {
          answer++;
          need = 1;
        }
      }
    }

    return answer + need;
  }
};

// Approach-2 (Stack of Remaining Closing Requirements)
// T.C : O(n)
// S.C : O(n)

class Solution2
{

public:
  int minInsertions(string s)
  {
    stack<int> st;
    int answer = 0;

    for (char ch : s)
    {

      if (ch == '(')
      {

        if (!st.empty() && st.top() == 1)
        {
          answer++;
          st.pop();
        }

        st.push(2);
      }
      else
      {

        if (st.empty())
        {
          answer++;
          st.push(1);
        }
        else
        {
          int remaining = st.top() - 1;
          st.pop();

          if (remaining > 0)
          {
            st.push(remaining);
          }
        }
      }
    }

    while (!st.empty())
    {
      answer += st.top();
      st.pop();
    }

    return answer;
  }
};