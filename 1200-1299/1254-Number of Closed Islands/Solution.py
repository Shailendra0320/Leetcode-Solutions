#Approach-1 (DFS with Boundary Check)
#T.C : O(m * n)
#S.C : O(m * n)

from typing import List


class Solution:
    def closedIsland(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

        def explore_island(sr: int, sc: int) -> bool:
            stack = [(sr, sc)]
            grid[sr][sc] = 1
            closed = True

            while stack:
                r, c = stack.pop()

                if r == 0 or r == m - 1 or c == 0 or c == n - 1:
                    closed = False

                for dr, dc in directions:
                    nr = r + dr
                    nc = c + dc

                    if (
                        0 <= nr < m
                        and 0 <= nc < n
                        and grid[nr][nc] == 0
                    ):
                        grid[nr][nc] = 1
                        stack.append((nr, nc))

            return closed

        answer = 0

        for r in range(m):
            for c in range(n):
                if grid[r][c] == 0:
                    if explore_island(r, c):
                        answer += 1

        return answer


#Approach-2 (Flood Boundary Land First)
#T.C : O(m * n)
#S.C : O(m * n)

class Solution2:
    def closedIsland(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

        def flood(sr: int, sc: int) -> None:
            if grid[sr][sc] != 0:
                return

            stack = [(sr, sc)]
            grid[sr][sc] = 1

            while stack:
                r, c = stack.pop()

                for dr, dc in directions:
                    nr = r + dr
                    nc = c + dc

                    if (
                        0 <= nr < m
                        and 0 <= nc < n
                        and grid[nr][nc] == 0
                    ):
                        grid[nr][nc] = 1
                        stack.append((nr, nc))

        for r in range(m):
            flood(r, 0)
            flood(r, n - 1)

        for c in range(n):
            flood(0, c)
            flood(m - 1, c)

        answer = 0

        for r in range(m):
            for c in range(n):
                if grid[r][c] == 0:
                    answer += 1
                    flood(r, c)

        return answer