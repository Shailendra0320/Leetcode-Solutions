#Approach-1 (Open Top Row and Last Column)
#T.C : O(m * n)
#S.C : O(m * n)

from typing import List

class Solution:
    def createGrid(self, m: int, n: int) -> List[str]:
        grid = [['#'] * n for _ in range(m)]

        grid[0] = ['.'] * n

        for i in range(m):
            grid[i][n - 1] = '.'

        return [''.join(row) for row in grid]


#Approach-2 (Open First Column and Last Row)
#T.C : O(m * n)
#S.C : O(m * n)

class Solution2:
    def createGrid(self, m: int, n: int) -> List[str]:
        grid = [['#'] * n for _ in range(m)]

        for i in range(m):
            grid[i][0] = '.'

        grid[m - 1] = ['.'] * n

        return [''.join(row) for row in grid]