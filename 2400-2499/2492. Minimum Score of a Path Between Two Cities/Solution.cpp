// Approach-1 (Breadth-First Search)
// T.C : O(V + E)
// S.C : O(V + E)

class Solution
{
public:
  int minScore(int n, vector<vector<int>> &roads)
  {

    vector<vector<pair<int, int>>> adjacencyList(n + 1);

    for (auto &road : roads)
    {

      int source = road[0];

      int destination = road[1];

      int weight = road[2];

      adjacencyList[source].push_back(
          {destination, weight});

      adjacencyList[destination].push_back(
          {source, weight});
    }

    vector<bool> visited(
        n + 1,
        false);

    queue<int> bfsQueue;

    bfsQueue.push(1);

    visited[1] = true;

    int answer = INT_MAX;

    while (!bfsQueue.empty())
    {

      int currentCity = bfsQueue.front();

      bfsQueue.pop();

      for (auto &neighbour : adjacencyList[currentCity])
      {

        int nextCity = neighbour.first;

        int weight = neighbour.second;

        answer = min(
            answer,
            weight);

        if (!visited[nextCity])
        {

          visited[nextCity] = true;

          bfsQueue.push(nextCity);
        }
      }
    }

    return answer;
  }
};

/*
//Approach-2 (Depth-First Search)
//T.C : O(V + E)
//S.C : O(V + E)

class Solution {
public:

    int answer = INT_MAX;

    void dfs(
        int city,
        vector<vector<pair<int,int>>>& adjacencyList,
        vector<bool>& visited
    ) {

        visited[city] = true;

        for (auto &neighbour : adjacencyList[city]) {

            answer = min(
                answer,
                neighbour.second
            );

            if (!visited[neighbour.first]) {

                dfs(
                    neighbour.first,
                    adjacencyList,
                    visited
                );
            }
        }
    }

    int minScore(int n, vector<vector<int>>& roads) {

        vector<vector<pair<int,int>>> adjacencyList(n + 1);

        for (auto &road : roads) {

            adjacencyList[road[0]].push_back(
                {road[1], road[2]}
            );

            adjacencyList[road[1]].push_back(
                {road[0], road[2]}
            );
        }

        vector<bool> visited(
            n + 1,
            false
        );

        dfs(
            1,
            adjacencyList,
            visited
        );

        return answer;
    }
};
*/

/*
//Approach-3 (Disjoint Set Union / Union Find)
//T.C : O((V + E) α(V))
//S.C : O(V)

class Solution {
public:

    vector<int> parent;

    int find(int node) {

        if (parent[node] != node) {

            parent[node] =
                find(parent[node]);
        }

        return parent[node];
    }

    void unite(
        int u,
        int v
    ) {

        int parentU = find(u);

        int parentV = find(v);

        if (parentU != parentV) {

            parent[parentV] = parentU;
        }
    }

    int minScore(int n, vector<vector<int>>& roads) {

        parent.resize(n + 1);

        for (int i = 1; i <= n; i++) {

            parent[i] = i;
        }

        for (auto &road : roads) {

            unite(
                road[0],
                road[1]
            );
        }

        int root = find(1);

        int answer = INT_MAX;

        for (auto &road : roads) {

            if (find(road[0]) == root) {

                answer = min(
                    answer,
                    road[2]
                );
            }
        }

        return answer;
    }
};
*/