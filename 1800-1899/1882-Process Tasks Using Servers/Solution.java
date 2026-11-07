// Approach-1 (Brute Force: Linear Search for Available Server)
// T.C: O(n * m)
// S.C: O(m)

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = tasks.length;
        int m = servers.length;

        long[] availableAt = new long[m];
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            long startTime = i;
            int chosen = -1;

            for (int s = 0; s < m; s++) {
                if (availableAt[s] <= startTime) {
                    if (chosen == -1 ||
                        servers[s] < servers[chosen] ||
                        (servers[s] == servers[chosen] && s < chosen)) {
                        chosen = s;
                    }
                }
            }

            if (chosen == -1) {
                startTime = Long.MAX_VALUE;

                for (long time : availableAt) {
                    startTime = Math.min(startTime, time);
                }

                for (int s = 0; s < m; s++) {
                    if (availableAt[s] <= startTime) {
                        if (chosen == -1 ||
                            servers[s] < servers[chosen] ||
                            (servers[s] == servers[chosen] && s < chosen)) {
                            chosen = s;
                        }
                    }
                }
            }

            answer[i] = chosen;
            availableAt[chosen] = startTime + tasks[i];
        }

        return answer;
    }
}

// Approach-2 (Two Min Heaps: Available and Busy Servers)
// T.C: O((n + m) * log m)
// S.C: O(m)

import java.util.*;

class Solution2 {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = tasks.length;
        int m = servers.length;

        PriorityQueue<int[]> available = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            int cmp = Long.compare(a[0], b[0]);

            if (cmp != 0) return cmp;

            cmp = Long.compare(a[1], b[1]);

            if (cmp != 0) return cmp;

            return Long.compare(a[2], b[2]);
        });

        for (int i = 0; i < m; i++) {
            available.offer(new int[]{servers[i], i});
        }

        int[] answer = new int[n];
        long currentTime = 0;

        for (int i = 0; i < n; i++) {
            currentTime = Math.max(currentTime, (long) i);

            while (!busy.isEmpty() && busy.peek()[0] <= currentTime) {
                long[] completed = busy.poll();

                available.offer(new int[]{
                    (int) completed[1],
                    (int) completed[2]
                });
            }

            if (available.isEmpty()) {
                currentTime = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= currentTime) {
                    long[] completed = busy.poll();

                    available.offer(new int[]{
                        (int) completed[1],
                        (int) completed[2]
                    });
                }
            }

            int[] server = available.poll();

            answer[i] = server[1];

            busy.offer(new long[]{
                currentTime + tasks[i],
                server[0],
                server[1]
            });
        }

        return answer;
    }
}