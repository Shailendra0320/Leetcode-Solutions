#include <bits/stdc++.h>
using namespace std;

// Approach-1 (Recursive Descent Parser)
// T.C : Output-sensitive, worst-case exponential
// S.C : O(n) recursion + output space

class Solution
{
private:
  string expression;
  int index;

  set<string> parseExpression()
  {
    set<string> result = parseTerm();

    while (index < expression.size() && expression[index] == ',')
    {
      index++;

      set<string> next = parseTerm();
      result.insert(next.begin(), next.end());
    }

    return result;
  }

  set<string> parseTerm()
  {
    set<string> result = {""};

    while (index < expression.size())
    {
      char ch = expression[index];

      if (ch == '}' || ch == ',')
      {
        break;
      }

      set<string> factor = parseFactor();
      result = concatenate(result, factor);
    }

    return result;
  }

  set<string> parseFactor()
  {
    char ch = expression[index];

    if (ch == '{')
    {
      index++;

      set<string> result = parseExpression();

      index++;
      return result;
    }

    index++;

    return {string(1, ch)};
  }

  set<string> concatenate(
      const set<string> &first,
      const set<string> &second)
  {
    set<string> result;

    for (const string &a : first)
    {
      for (const string &b : second)
      {
        result.insert(a + b);
      }
    }

    return result;
  }

public:
  vector<string> braceExpansionII(string expression)
  {
    this->expression = expression;
    this->index = 0;

    set<string> result = parseExpression();

    return vector<string>(result.begin(), result.end());
  }
};

// Approach-2 (Stack-Based Set Evaluation)
// T.C : Output-sensitive, worst-case exponential
// S.C : O(n) stack + output space

class Solution2
{
private:
  struct Frame
  {
    set<string> unionSet;
    set<string> product = {""};
  };

  set<string> concatenate(
      const set<string> &first,
      const set<string> &second)
  {
    set<string> result;

    for (const string &a : first)
    {
      for (const string &b : second)
      {
        result.insert(a + b);
      }
    }

    return result;
  }

public:
  vector<string> braceExpansionII(string expression)
  {
    stack<Frame> st;
    st.push(Frame());

    for (char ch : expression)
    {

      if (ch == '{')
      {
        st.push(Frame());
      }

      else if (ch == ',')
      {
        Frame &frame = st.top();

        frame.unionSet.insert(
            frame.product.begin(),
            frame.product.end());

        frame.product.clear();
        frame.product.insert("");
      }

      else if (ch == '}')
      {
        Frame frame = st.top();
        st.pop();

        frame.unionSet.insert(
            frame.product.begin(),
            frame.product.end());

        st.top().product = concatenate(
            st.top().product,
            frame.unionSet);
      }

      else
      {
        set<string> letter = {string(1, ch)};

        st.top().product = concatenate(
            st.top().product,
            letter);
      }
    }

    Frame root = st.top();

    root.unionSet.insert(
        root.product.begin(),
        root.product.end());

    return vector<string>(
        root.unionSet.begin(),
        root.unionSet.end());
  }
};