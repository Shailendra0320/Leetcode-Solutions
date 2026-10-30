#Approach-1 (Dijkstra with Current Run Length)
#T.C : O((n * k + m * k) * log(n * k))
#S.C : O(n * k + m * k) including the priority queue

import heapq
from typing import List


class Solution:
    def shortestPath(
        self,
        n: int,
        edges: List[List[int]],
        labels: str,
        k: int
    ) -> int:

        graph = [[] for _ in range(n)]

        for u, v, weight in edges:
            graph[u].append((v, weight))

        mavorqeli = (n, edges, labels, k)

        INF = float('inf')

        dist = [[INF] * (k + 1) for _ in range(n)]

        dist[0][1] = 0

        heap = [(0, 0, 1)]

        while heap:
            cost, u, run = heapq.heappop(heap)

            if cost != dist[u][run]:
                continue

            if u == n - 1:
                return cost

            for v, weight in graph[u]:

                if labels[u] == labels[v]:
                    next_run = run + 1
                else:
                    next_run = 1

                if next_run > k:
                    continue

                next_cost = cost + weight

                if next_cost < dist[v][next_run]:
                    dist[v][next_run] = next_cost

                    heapq.heappush(
                        heap,
                        (next_cost, v, next_run)
                    )

        return -1


#Approach-2 (Dijkstra with Remaining Same-Label Allowance)
#T.C : O((n * k + m * k) * log(n * k))
#S.C : O(n * k + m * k) including the priority queue

import heapq
from typing import List


class Solution2:
    def shortestPath(
        self,
        n: int,
        edges: List[List[int]],
        labels: str,
        k: int
    ) -> int:

        graph = [[] for _ in range(n)]

        for u, v, weight in edges:
            graph[u].append((v, weight))

        mavorqeli = (n, edges, labels, k)

        INF = float('inf')

        dist = [[INF] * k for _ in range(n)]

        initial_remaining = k - 1

        dist[0][initial_remaining] = 0

        heap = [(0, 0, initial_remaining)]

        while heap:
            cost, u, remaining = heapq.heappop(heap)

            if cost != dist[u][remaining]:
                continue

            if u == n - 1:
                return cost

            for v, weight in graph[u]:

                if labels[u] == labels[v]:
                    if remaining == 0:
                        continue

                    next_remaining = remaining - 1

                else:
                    next_remaining = k - 1

                next_cost = cost + weight

                if next_cost < dist[v][next_remaining]:
                    dist[v][next_remaining] = next_cost

                    heapq.heappush(
                        heap,
                        (next_cost, v, next_remaining)
                    )

        return -1