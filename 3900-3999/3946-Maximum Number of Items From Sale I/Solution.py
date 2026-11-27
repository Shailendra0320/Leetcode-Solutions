
from typing import List


# Approach-1 (Brute Force Divisibility Counting + 0/1 Knapsack)
# T.C: O(n^2 + n * budget)
# S.C: O(budget)

class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        n = len(items)
        min_price = min(price for factor, price in items)

        dp = [0] * (budget + 1)

        for i in range(n):
            factor, price = items[i]

            gain = 0

            for j in range(n):
                if items[j][0] % factor == 0:
                    gain += 1

            for b in range(budget, price - 1, -1):
                dp[b] = max(
                    dp[b],
                    dp[b - price] + gain
                )

        ans = 0

        for spent in range(budget + 1):
            extra = (budget - spent) // min_price

            ans = max(ans, dp[spent] + extra)

        return ans


# Approach-2 (Divisor Frequency Precomputation + 0/1 Knapsack)
# T.C: O(V log V + n * budget)
# S.C: O(V + budget)

class Solution2:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        n = len(items)

        max_factor = max(factor for factor, price in items)
        min_price = min(price for factor, price in items)

        freq = [0] * (max_factor + 1)

        for factor, price in items:
            freq[factor] += 1

        gain = [0] * (max_factor + 1)

        for factor in range(1, max_factor + 1):
            for multiple in range(factor, max_factor + 1, factor):
                gain[factor] += freq[multiple]

        dp = [0] * (budget + 1)

        for factor, price in items:
            reward = gain[factor]

            for b in range(budget, price - 1, -1):
                dp[b] = max(
                    dp[b],
                    dp[b - price] + reward
                )

        ans = 0

        for spent in range(budget + 1):
            extra = (budget - spent) // min_price

            ans = max(ans, dp[spent] + extra)

        return ans
