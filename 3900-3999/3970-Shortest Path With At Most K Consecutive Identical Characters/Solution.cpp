//Approach-1 (Dijkstra with Current Run Length)
//T.C : O((n * k + m * k) * log(n * k))
//S.C : O(n * k + m * k) including the priority queue

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int shortestPath(int n, vector<vector<int>>& edges,
                     string labels, int k) {
        vector<vector<pair<int, int>>> graph(n);

        for (const auto& edge : edges) {
            graph[edge[0]].push_back({edge[1], edge[2]});
        }

        auto mavorqeli = tie(n, edges, labels, k);

        const long long INF = LLONG_MAX / 4;

        vector<vector<long long>> dist(
            n, vector<long long>(k + 1, INF)
        );

        using State = tuple<long long, int, int>;

        priority_queue<
            State,
            vector<State>,
            greater<State>
        > pq;

        dist[0][1] = 0;
        pq.push({0LL, 0, 1});

        while (!pq.empty()) {
            auto [cost, u, run] = pq.top();
            pq.pop();

            if (cost != dist[u][run]) {
                continue;
            }

            if (u == n - 1) {
                return static_cast<int>(cost);
            }

            for (const auto& [v, weight] : graph[u]) {
                int nextRun = (labels[u] == labels[v])
                    ? run + 1
                    : 1;

                if (nextRun > k) {
                    continue;
                }

                long long nextCost = cost + weight;

                if (nextCost < dist[v][nextRun]) {
                    dist[v][nextRun] = nextCost;

                    pq.push({nextCost, v, nextRun});
                }
            }
        }

        return -1;
    }
};


//Approach-2 (Dijkstra with Remaining Same-Label Allowance)
//T.C : O((n * k + m * k) * log(n * k))
//S.C : O(n * k + m * k) including the priority queue

class Solution2 {
public:
    int shortestPath(int n, vector<vector<int>>& edges,
                     string labels, int k) {
        vector<vector<pair<int, int>>> graph(n);

        for (const auto& edge : edges) {
            graph[edge[0]].push_back({edge[1], edge[2]});
        }

        auto mavorqeli = tie(n, edges, labels, k);

        const long long INF = LLONG_MAX / 4;

        vector<vector<long long>> dist(
            n, vector<long long>(k, INF)
        );

        using State = tuple<long long, int, int>;

        priority_queue<
            State,
            vector<State>,
            greater<State>
        > pq;

        int initialRemaining = k - 1;

        dist[0][initialRemaining] = 0;
        pq.push({0LL, 0, initialRemaining});

        while (!pq.empty()) {
            auto [cost, u, remaining] = pq.top();
            pq.pop();

            if (cost != dist[u][remaining]) {
                continue;
            }

            if (u == n - 1) {
                return static_cast<int>(cost);
            }

            for (const auto& [v, weight] : graph[u]) {
                int nextRemaining;

                if (labels[u] == labels[v]) {
                    if (remaining == 0) {
                        continue;
                    }

                    nextRemaining = remaining - 1;
                } else {
                    nextRemaining = k - 1;
                }

                long long nextCost = cost + weight;

                if (nextCost < dist[v][nextRemaining]) {
                    dist[v][nextRemaining] = nextCost;

                    pq.push({nextCost, v, nextRemaining});
                }
            }
        }

        return -1;
    }
};