# Approach-1 (Brute Force: Check Every Log for Each Query)
# T.C: O(q * m)
# S.C: O(min(n, m))

from typing import List

class Solution:
    def countServers(
        self, n: int, logs: List[List[int]], x: int, queries: List[int]
    ) -> List[int]:

        answer = []

        for time in queries:
            start = time - x
            end = time

            active_servers = set()

            for server, log_time in logs:
                if start <= log_time <= end:
                    active_servers.add(server)

            answer.append(n - len(active_servers))

        return answer


# Approach-2 (Offline Queries + Sorting + Sliding Window)
# T.C: O(m log m + q log q)
# S.C: O(n + m + q)

class Solution2:
    def countServers(
        self, n: int, logs: List[List[int]], x: int, queries: List[int]
    ) -> List[int]:

        m = len(logs)
        q = len(queries)

        sorted_logs = sorted(logs, key=lambda log: log[1])
        sorted_queries = sorted(
            (time, index) for index, time in enumerate(queries)
        )

        frequency = [0] * (n + 1)
        answer = [0] * q

        left = 0
        right = 0
        active_servers = 0

        for time, index in sorted_queries:
            start = time - x

            while right < m and sorted_logs[right][1] <= time:
                server = sorted_logs[right][0]

                if frequency[server] == 0:
                    active_servers += 1

                frequency[server] += 1
                right += 1

            while left < right and sorted_logs[left][1] < start:
                server = sorted_logs[left][0]

                frequency[server] -= 1

                if frequency[server] == 0:
                    active_servers -= 1

                left += 1

            answer[index] = n - active_servers

        return answer