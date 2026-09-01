//Approach-1 (DFS + External Invocation Check)
//T.C : O(n + m)
//S.C : O(n + m)

class Solution {

    List<Integer>[] graph;
    boolean[] suspicious;

    public List<Integer> remainingMethods(
        int n,
        int k,
        int[][] invocations
    ) {

        graph =
            new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] =
                new ArrayList<>();
        }

        for (int[] edge : invocations) {

            int a =
                edge[0];

            int b =
                edge[1];

            graph[a].add(
                b
            );
        }

        suspicious =
            new boolean[n];

        dfs(k);

        for (int[] edge : invocations) {

            int a =
                edge[0];

            int b =
                edge[1];

            if (
                !suspicious[a] &&
                suspicious[b]
            ) {

                List<Integer> answer =
                    new ArrayList<>();

                for (
                    int i = 0;
                    i < n;
                    i++
                ) {

                    answer.add(i);
                }

                return answer;
            }
        }

        List<Integer> answer =
            new ArrayList<>();

        for (
            int i = 0;
            i < n;
            i++
        ) {

            if (!suspicious[i]) {
                answer.add(i);
            }
        }

        return answer;
    }

    private void dfs(
        int node
    ) {

        suspicious[node] =
            true;

        for (
            int next :
            graph[node]
        ) {

            if (!suspicious[next]) {

                dfs(
                    next
                );
            }
        }
    }
}


//Approach-2 (DFS + Reverse Graph)
//T.C : O(n + m)
//S.C : O(n + m)

class Solution {

    List<Integer>[] graph;
    List<Integer>[] reverseGraph;
    boolean[] suspicious;

    public List<Integer> remainingMethods(
        int n,
        int k,
        int[][] invocations
    ) {

        graph =
            new ArrayList[n];

        reverseGraph =
            new ArrayList[n];

        for (int i = 0; i < n; i++) {

            graph[i] =
                new ArrayList<>();

            reverseGraph[i] =
                new ArrayList<>();
        }

        for (int[] edge : invocations) {

            int a =
                edge[0];

            int b =
                edge[1];

            graph[a].add(
                b
            );

            reverseGraph[b].add(
                a
            );
        }

        suspicious =
            new boolean[n];

        dfs(
            k
        );

        for (
            int i = 0;
            i < n;
            i++
        ) {

            if (!suspicious[i]) {
                continue;
            }

            for (
                int caller :
                reverseGraph[i]
            ) {

                if (!suspicious[caller]) {

                    List<Integer> answer =
                        new ArrayList<>();

                    for (
                        int j = 0;
                        j < n;
                        j++
                    ) {

                        answer.add(j);
                    }

                    return answer;
                }
            }
        }

        List<Integer> answer =
            new ArrayList<>();

        for (
            int i = 0;
            i < n;
            i++
        ) {

            if (!suspicious[i]) {
                answer.add(i);
            }
        }

        return answer;
    }

    private void dfs(
        int node
    ) {

        suspicious[node] =
            true;

        for (
            int next :
            graph[node]
        ) {

            if (!suspicious[next]) {

                dfs(
                    next
                );
            }
        }
    }
}