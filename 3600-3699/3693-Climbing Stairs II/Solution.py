#Approach-1 (Recursion)
#T.C: O(3^n)
#S.C: O(n)

from typing import List

class Solution:
    def climbStairs(self, n: int, costs: List[int]) -> int:
        def solve(curr):
            if curr == n:
                return 0

            answer = float('inf')

            for jump in range(1, 4):
                next_step = curr + jump

                if next_step <= n:
                    cost = costs[next_step - 1] + jump * jump

                    answer = min(
                        answer,
                        cost + solve(next_step)
                    )

            return answer

        return solve(0)


#Approach-2 (Recursion + Memoization)
#T.C: O(n)
#S.C: O(n)

class Solution2:
    def climbStairs(self, n: int, costs: List[int]) -> int:
        memo = [-1] * (n + 1)

        def solve(curr):
            if curr == n:
                return 0

            if memo[curr] != -1:
                return memo[curr]

            answer = float('inf')

            for jump in range(1, 4):
                next_step = curr + jump

                if next_step <= n:
                    cost = costs[next_step - 1] + jump * jump

                    answer = min(
                        answer,
                        cost + solve(next_step)
                    )

            memo[curr] = answer
            return answer

        return solve(0)


#Approach-3 (Bottom-Up Dynamic Programming)
#T.C: O(n)
#S.C: O(n)

class Solution3:
    def climbStairs(self, n: int, costs: List[int]) -> int:
        dp = [float('inf')] * (n + 1)
        dp[0] = 0

        for i in range(1, n + 1):
            for jump in range(1, 4):
                prev = i - jump

                if prev >= 0:
                    cost = costs[i - 1] + jump * jump

                    dp[i] = min(
                        dp[i],
                        dp[prev] + cost
                    )

        return dp[n]


#Approach-4 (Space Optimized DP: Rolling Array)
#T.C: O(n)
#S.C: O(1)

class Solution4:
    def climbStairs(self, n: int, costs: List[int]) -> int:
        dp = [float('inf')] * 4
        dp[0] = 0

        for i in range(1, n + 1):
            current = float('inf')

            for jump in range(1, 4):
                prev = i - jump

                if prev >= 0:
                    cost = costs[i - 1] + jump * jump

                    current = min(
                        current,
                        dp[prev % 4] + cost
                    )

            dp[i % 4] = current

        return dp[n % 4]