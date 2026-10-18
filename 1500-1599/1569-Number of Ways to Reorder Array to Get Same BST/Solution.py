#Approach-1 (Recursive Partitioning + Pascal's Triangle)
#T.C : O(n^2)
#S.C : O(n^2)

from typing import List
import sys

MOD = 1_000_000_007


class Solution:
    def numOfWays(self, nums: List[int]) -> int:
        sys.setrecursionlimit(3000)
        n = len(nums)

        combinations = [[0] * n for _ in range(n)]

        for i in range(n):
            combinations[i][0] = 1
            combinations[i][i] = 1

            for j in range(1, i):
                combinations[i][j] = (
                    combinations[i - 1][j - 1]
                    + combinations[i - 1][j]
                ) % MOD

        def dfs(values: List[int]) -> int:
            size = len(values)

            if size <= 2:
                return 1

            root = values[0]
            left = []
            right = []

            for value in values[1:]:
                if value < root:
                    left.append(value)
                else:
                    right.append(value)

            ways = combinations[size - 1][len(left)]
            ways = ways * dfs(left) % MOD
            ways = ways * dfs(right) % MOD

            return ways

        return (dfs(nums) - 1) % MOD


#Approach-2 (BST Construction + Subtree DP)
#T.C : O(n^2) worst case
#S.C : O(n)

class Solution2:
    class Node:
        def __init__(self, value: int):
            self.value = value
            self.left = None
            self.right = None

    def _insert(self, root, value: int):
        if root is None:
            return self.Node(value)

        current = root

        while True:
            if value < current.value:
                if current.left is None:
                    current.left = self.Node(value)
                    break

                current = current.left
            else:
                if current.right is None:
                    current.right = self.Node(value)
                    break

                current = current.right

        return root

    def _count_ways(self, node, factorial, inverse_factorial):
        if node is None:
            return 0, 1

        left_size, left_ways = self._count_ways(
            node.left, factorial, inverse_factorial
        )

        right_size, right_ways = self._count_ways(
            node.right, factorial, inverse_factorial
        )

        total = left_size + right_size

        choose = (
            factorial[total]
            * inverse_factorial[left_size]
            % MOD
            * inverse_factorial[right_size]
            % MOD
        )

        ways = choose * left_ways % MOD
        ways = ways * right_ways % MOD

        return total + 1, ways

    def numOfWays(self, nums: List[int]) -> int:
        sys.setrecursionlimit(3000)
        n = len(nums)
        root = None

        for num in nums:
            root = self._insert(root, num)

        factorial = [1] * (n + 1)
        inverse_factorial = [1] * (n + 1)

        for i in range(1, n + 1):
            factorial[i] = factorial[i - 1] * i % MOD

        inverse_factorial[n] = pow(
            factorial[n], MOD - 2, MOD
        )

        for i in range(n, 0, -1):
            inverse_factorial[i - 1] = (
                inverse_factorial[i] * i % MOD
            )

        _, ways = self._count_ways(
            root, factorial, inverse_factorial
        )

        return (ways - 1) % MOD