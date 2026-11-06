// Approach-1 (Brute Force: Check Every Log for Each Query)
// T.C: O(q * m)
// S.C: O(min(n, m))

import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int q = queries.length;
        int[] answer = new int[q];

        for (int i = 0; i < q; i++) {
            int start = queries[i] - x;
            int end = queries[i];

            Set<Integer> activeServers = new HashSet<>();

            for (int[] log : logs) {
                int server = log[0];
                int time = log[1];

                if (time >= start && time <= end) {
                    activeServers.add(server);
                }
            }

            answer[i] = n - activeServers.size();
        }

        return answer;
    }
}

// Approach-2 (Offline Queries + Sorting + Sliding Window)
// T.C: O(m log m + q log q)
// S.C: O(n + m + q)

class Solution2 {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length;
        int q = queries.length;

        int[][] sortedLogs = logs.clone();
        Arrays.sort(sortedLogs, (a, b) -> Integer.compare(a[1], b[1]));

        int[][] sortedQueries = new int[q][2];

        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] frequency = new int[n + 1];
        int[] answer = new int[q];

        int left = 0;
        int right = 0;
        int activeServers = 0;

        for (int[] query : sortedQueries) {
            int time = query[0];
            int index = query[1];

            int start = time - x;

            while (right < m && sortedLogs[right][1] <= time) {
                int server = sortedLogs[right][0];

                if (frequency[server] == 0) {
                    activeServers++;
                }

                frequency[server]++;
                right++;
            }

            while (left < right && sortedLogs[left][1] < start) {
                int server = sortedLogs[left][0];

                frequency[server]--;

                if (frequency[server] == 0) {
                    activeServers--;
                }

                left++;
            }

            answer[index] = n - activeServers;
        }

        return answer;
    }
}