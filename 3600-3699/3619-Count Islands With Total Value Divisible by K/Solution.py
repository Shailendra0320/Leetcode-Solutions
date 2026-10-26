#Approach-1 (Iterative DFS with In-Place Marking)
#T.C : O(m * n)
#S.C : O(m * n)

from typing import List


class Solution:
    def countIslands(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        directions = ((-1, 0), (1, 0), (0, -1), (0, 1))

        def explore_island(sr: int, sc: int) -> int:
            total = grid[sr][sc]
            stack = [sr * n + sc]
            grid[sr][sc] = 0

            while stack:
                cell = stack.pop()
                r, c = divmod(cell, n)

                for dr, dc in directions:
                    nr = r + dr
                    nc = c + dc

                    if (
                        0 <= nr < m
                        and 0 <= nc < n
                        and grid[nr][nc] > 0
                    ):
                        total += grid[nr][nc]
                        grid[nr][nc] = 0
                        stack.append(nr * n + nc)

            return total

        answer = 0

        for r in range(m):
            for c in range(n):
                if grid[r][c] > 0:
                    total = explore_island(r, c)

                    if total % k == 0:
                        answer += 1

        return answer


#Approach-2 (BFS with Separate Visited Matrix)
#T.C : O(m * n)
#S.C : O(m * n)

from collections import deque
from typing import List


class Solution2:
    def countIslands(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        directions = ((-1, 0), (1, 0), (0, -1), (0, 1))

        visited = [[False] * n for _ in range(m)]
        answer = 0

        for r in range(m):
            for c in range(n):
                if grid[r][c] == 0 or visited[r][c]:
                    continue

                total = 0
                queue = deque([(r, c)])
                visited[r][c] = True

                while queue:
                    cr, cc = queue.popleft()
                    total += grid[cr][cc]

                    for dr, dc in directions:
                        nr = cr + dr
                        nc = cc + dc

                        if (
                            0 <= nr < m
                            and 0 <= nc < n
                            and grid[nr][nc] > 0
                            and not visited[nr][nc]
                        ):
                            visited[nr][nc] = True
                            queue.append((nr, nc))

                if total % k == 0:
                    answer += 1

        return answer