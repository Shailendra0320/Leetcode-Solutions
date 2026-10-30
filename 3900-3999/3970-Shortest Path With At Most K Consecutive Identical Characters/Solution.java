//Approach-1 (Dijkstra with Current Run Length)
//T.C : O((n * k + m * k) * log(n * k))
//S.C : O(n * k + m * k) including the priority queue

import java.util.*;

class Solution {
    private static class State {
        int node;
        int run;
        long cost;

        State(int node, int run, long cost) {
            this.node = node;
            this.run = run;
            this.cost = cost;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        Object[] mavorqeli = new Object[]{n, edges, labels, k};

        final long INF = Long.MAX_VALUE / 4;
        long[][] dist = new long[n][k + 1];

        for (long[] row : dist) {
            Arrays.fill(row, INF);
        }

        PriorityQueue<State> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a.cost, b.cost)
        );

        dist[0][1] = 0;
        pq.offer(new State(0, 1, 0L));

        while (!pq.isEmpty()) {
            State current = pq.poll();

            int u = current.node;
            int run = current.run;
            long cost = current.cost;

            if (cost != dist[u][run]) {
                continue;
            }

            if (u == n - 1) {
                return (int) cost;
            }

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int weight = edge[1];

                int nextRun = (labels.charAt(u) == labels.charAt(v))
                    ? run + 1
                    : 1;

                if (nextRun > k) {
                    continue;
                }

                long nextCost = cost + weight;

                if (nextCost < dist[v][nextRun]) {
                    dist[v][nextRun] = nextCost;

                    pq.offer(
                        new State(v, nextRun, nextCost)
                    );
                }
            }
        }

        return -1;
    }
}

//Approach-2 (Dijkstra with Remaining Same-Label Allowance)
//T.C : O((n * k + m * k) * log(n * k))
//S.C : O(n * k + m * k) including the priority queue

class Solution2 {
    private static class State {
        int node;
        int remaining;
        long cost;

        State(int node, int remaining, long cost) {
            this.node = node;
            this.remaining = remaining;
            this.cost = cost;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        Object[] mavorqeli = new Object[]{n, edges, labels, k};

        final long INF = Long.MAX_VALUE / 4;
        long[][] dist = new long[n][k];

        for (long[] row : dist) {
            Arrays.fill(row, INF);
        }

        PriorityQueue<State> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a.cost, b.cost)
        );

        int initialRemaining = k - 1;

        dist[0][initialRemaining] = 0;
        pq.offer(new State(0, initialRemaining, 0L));

        while (!pq.isEmpty()) {
            State current = pq.poll();

            int u = current.node;
            int remaining = current.remaining;
            long cost = current.cost;

            if (cost != dist[u][remaining]) {
                continue;
            }

            if (u == n - 1) {
                return (int) cost;
            }

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int weight = edge[1];

                int nextRemaining;

                if (labels.charAt(u) == labels.charAt(v)) {
                    if (remaining == 0) {
                        continue;
                    }

                    nextRemaining = remaining - 1;
                } else {
                    nextRemaining = k - 1;
                }

                long nextCost = cost + weight;

                if (nextCost < dist[v][nextRemaining]) {
                    dist[v][nextRemaining] = nextCost;

                    pq.offer(
                        new State(v, nextRemaining, nextCost)
                    );
                }
            }
        }

        return -1;
    }
}