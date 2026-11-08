# Approach-1 (Brute Force: Scan the Current Row and Column)
# T.C: O(m * n * (m + n))
# S.C: O(1)

from typing import List

class Solution:
    def countServers(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])
        answer = 0

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0:
                    continue

                can_communicate = False

                for col in range(n):
                    if col != j and grid[i][col] == 1:
                        can_communicate = True
                        break

                if not can_communicate:
                    for row in range(m):
                        if row != i and grid[row][j] == 1:
                            can_communicate = True
                            break

                if can_communicate:
                    answer += 1

        return answer


# Approach-2 (Row and Column Frequency Counting)
# T.C: O(m * n)
# S.C: O(m + n)

class Solution2:
    def countServers(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])

        row_count = [0] * m
        col_count = [0] * n

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    row_count[i] += 1
                    col_count[j] += 1

        answer = 0

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1 and (
                    row_count[i] > 1 or col_count[j] > 1
                ):
                    answer += 1

        return answer


# Approach-3 (In-Place Marking: Rows First, Then Columns)
# T.C: O(m * n)
# S.C: O(1)

class Solution3:
    def countServers(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])
        answer = 0

        for i in range(m):
            count = 0

            for j in range(n):
                if grid[i][j] != 0:
                    count += 1

            if count > 1:
                answer += count

                for j in range(n):
                    if grid[i][j] == 1:
                        grid[i][j] = -1

        for j in range(n):
            total = 0
            unmarked = 0

            for i in range(m):
                if grid[i][j] != 0:
                    total += 1

                if grid[i][j] == 1:
                    unmarked += 1

            if total > 1:
                answer += unmarked

        for i in range(m):
            for j in range(n):
                if grid[i][j] == -1:
                    grid[i][j] = 1

        return answer