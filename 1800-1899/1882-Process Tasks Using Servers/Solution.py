# Approach-1 (Brute Force: Linear Search for Available Server)
# T.C: O(n * m)
# S.C: O(m)

from typing import List

class Solution:
    def assignTasks(self, servers: List[int], tasks: List[int]) -> List[int]:
        n = len(tasks)
        m = len(servers)

        available_at = [0] * m
        answer = [0] * n

        for i in range(n):
            start_time = i
            chosen = -1

            for s in range(m):
                if available_at[s] <= start_time:
                    if chosen == -1 or (servers[s], s) < (servers[chosen], chosen):
                        chosen = s

            if chosen == -1:
                start_time = min(available_at)

                for s in range(m):
                    if available_at[s] <= start_time:
                        if chosen == -1 or (servers[s], s) < (servers[chosen], chosen):
                            chosen = s

            answer[i] = chosen
            available_at[chosen] = start_time + tasks[i]

        return answer


# Approach-2 (Two Min Heaps: Available and Busy Servers)
# T.C: O((n + m) * log m)
# S.C: O(m)

import heapq

class Solution2:
    def assignTasks(self, servers: List[int], tasks: List[int]) -> List[int]:
        n = len(tasks)
        m = len(servers)

        available = [(servers[i], i) for i in range(m)]
        heapq.heapify(available)

        busy = []
        answer = [0] * n
        current_time = 0

        for i in range(n):
            current_time = max(current_time, i)

            while busy and busy[0][0] <= current_time:
                finish_time, weight, server_index = heapq.heappop(busy)

                heapq.heappush(available, (weight, server_index))

            if not available:
                current_time = busy[0][0]

                while busy and busy[0][0] <= current_time:
                    finish_time, weight, server_index = heapq.heappop(busy)

                    heapq.heappush(available, (weight, server_index))

            weight, server_index = heapq.heappop(available)

            answer[i] = server_index

            heapq.heappush(
                busy,
                (current_time + tasks[i], weight, server_index)
            )

        return answer