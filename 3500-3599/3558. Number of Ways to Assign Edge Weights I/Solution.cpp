class Solution
{

public:
  static constexpr int MOD = 1000000007;

  vector<int> depth;

  void dfs(

      int node,

      vector<vector<int>> &graph,

      int parent,

      int currentDepth

  )
  {

    depth[node] = currentDepth;

    for (int child : graph[node])
    {

      if (child != parent)
      {

        dfs(

            child,

            graph,

            node,

            currentDepth + 1

        );
      }
    }
  }

  long long modPow(

      long long base,

      int exponent

  )
  {

    long long result = 1;

    while (exponent > 0)
    {

      if (exponent & 1)
      {

        result = (result * base) % MOD;
      }

      base = (base * base) % MOD;

      exponent >>= 1;
    }

    return result;
  }

  int assignEdgeWeights(vector<vector<int>> &edges)
  {

    int n = edges.size() + 1;

    vector<vector<int>> graph(n + 1);

    depth.assign(n + 1, 0);

    for (auto &edge : edges)
    {

      int u = edge[0];

      int v = edge[1];

      graph[u].push_back(v);

      graph[v].push_back(u);
    }

    dfs(1, graph, -1, 0);

    int maxDepth = 0;

    for (int node = 1; node <= n; node++)
    {

      maxDepth = max(maxDepth, depth[node]);
    }

    return (int)modPow(2, maxDepth - 1);
  }
};
