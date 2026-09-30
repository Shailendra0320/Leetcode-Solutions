#include <bits/stdc++.h>
using namespace std;

// Approach-1 (Nesting Depth Parity)
// T.C : O(n)
// S.C : O(1) auxiliary

class Solution
{
public:
  vector<int> maxDepthAfterSplit(string seq)
  {
    int n = seq.size();
    vector<int> answer(n);

    int depth = 0;

    for (int i = 0; i < n; i++)
    {
      if (seq[i] == '(')
      {
        depth++;
        answer[i] = depth % 2;
      }
      else
      {
        answer[i] = depth % 2;
        depth--;
      }
    }

    return answer;
  }
};

// Approach-2 (Stack-Based Matching Pair Assignment)
// T.C : O(n)
// S.C : O(n)

class Solution2
{
public:
  vector<int> maxDepthAfterSplit(string seq)
  {
    int n = seq.size();
    vector<int> answer(n);

    stack<int> st;

    for (int i = 0; i < n; i++)
    {
      if (seq[i] == '(')
      {
        int group = (st.size() + 1) % 2;

        answer[i] = group;
        st.push(group);
      }
      else
      {
        answer[i] = st.top();
        st.pop();
      }
    }

    return answer;
  }
};