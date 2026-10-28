#Approach-1 (Full Grid DP with XOR States)
#T.C : O(m * n * 1024)
#S.C : O(m * n * 1024)

from typing import List


class Solution:
    def minCost(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        MAX_XOR = 1024

        dp = [
            [[False] * MAX_XOR for _ in range(n)]
            for _ in range(m)
        ]

        dp[0][0][grid[0][0]] = True

        for i in range(m):
            for j in range(n):
                if i == 0 and j == 0:
                    continue

                value = grid[i][j]

                if i > 0:
                    for x in range(MAX_XOR):
                        if dp[i - 1][j][x]:
                            dp[i][j][x ^ value] = True

                if j > 0:
                    for x in range(MAX_XOR):
                        if dp[i][j - 1][x]:
                            dp[i][j][x ^ value] = True

        for x in range(MAX_XOR):
            if dp[m - 1][n - 1][x]:
                return x

        return -1


#Approach-2 (Rolling Row DP with XOR States)
#T.C : O(m * n * 1024)
#S.C : O(n * 1024)

class Solution2:
    def minCost(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        MAX_XOR = 1024

        prev = [[False] * MAX_XOR for _ in range(n)]

        for i in range(m):
            curr = [[False] * MAX_XOR for _ in range(n)]

            for j in range(n):
                value = grid[i][j]

                if i == 0 and j == 0:
                    curr[j][value] = True
                    continue

                if i > 0:
                    for x in range(MAX_XOR):
                        if prev[j][x]:
                            curr[j][x ^ value] = True

                if j > 0:
                    for x in range(MAX_XOR):
                        if curr[j - 1][x]:
                            curr[j][x ^ value] = True

            prev = curr

        for x in range(MAX_XOR):
            if prev[n - 1][x]:
                return x

        return -1