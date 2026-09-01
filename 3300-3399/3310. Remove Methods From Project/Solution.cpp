// Approach-1 (DFS + External Invocation Check)
// T.C : O(n + m)
// S.C : O(n + m)

class Solution
{
public:
  vector<vector<int>> graph;
  vector<bool> suspicious;

  vector<int> remainingMethods(
      int n,
      int k,
      vector<vector<int>> &invocations)
  {

    graph.resize(n);
    suspicious.assign(
        n,
        false);

    for (
        auto &edge :
        invocations)
    {

      int a =
          edge[0];

      int b =
          edge[1];

      graph[a].push_back(
          b);
    }

    dfs(
        k);

    for (
        auto &edge :
        invocations)
    {

      int a =
          edge[0];

      int b =
          edge[1];

      if (
          !suspicious[a] &&
          suspicious[b])
      {

        vector<int> answer;

        for (
            int i = 0;
            i < n;
            i++)
        {

          answer.push_back(
              i);
        }

        return answer;
      }
    }

    vector<int> answer;

    for (
        int i = 0;
        i < n;
        i++)
    {

      if (!suspicious[i])
      {

        answer.push_back(
            i);
      }
    }

    return answer;
  }

private:
  void dfs(
      int node)
  {

    suspicious[node] =
        true;

    for (
        int next :
        graph[node])
    {

      if (!suspicious[next])
      {

        dfs(
            next);
      }
    }
  }
};

// Approach-2 (DFS + Reverse Graph)
// T.C : O(n + m)
// S.C : O(n + m)

class Solution
{
public:
  vector<vector<int>> graph;
  vector<vector<int>> reverseGraph;
  vector<bool> suspicious;

  vector<int> remainingMethods(
      int n,
      int k,
      vector<vector<int>> &invocations)
  {

    graph.resize(n);
    reverseGraph.resize(n);

    suspicious.assign(
        n,
        false);

    for (
        auto &edge :
        invocations)
    {

      int a =
          edge[0];

      int b =
          edge[1];

      graph[a].push_back(
          b);

      reverseGraph[b].push_back(
          a);
    }

    dfs(
        k);

    for (
        int i = 0;
        i < n;
        i++)
    {

      if (!suspicious[i])
      {
        continue;
      }

      for (
          int caller :
          reverseGraph[i])
      {

        if (!suspicious[caller])
        {

          vector<int> answer;

          for (
              int j = 0;
              j < n;
              j++)
          {

            answer.push_back(
                j);
          }

          return answer;
        }
      }
    }

    vector<int> answer;

    for (
        int i = 0;
        i < n;
        i++)
    {

      if (!suspicious[i])
      {

        answer.push_back(
            i);
      }
    }

    return answer;
  }

private:
  void dfs(
      int node)
  {

    suspicious[node] =
        true;

    for (
        int next :
        graph[node])
    {

      if (!suspicious[next])
      {

        dfs(
            next);
      }
    }
  }
};