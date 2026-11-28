
from typing import List
import heapq


#Approach-1 (Divisibility Sieve + Sorting + Greedy)
#T.C: O(n log n)
#S.C: O(n)

class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        max_factor = max(factor for factor, price in items)
        min_price = min(price for factor, price in items)

        # Count frequencies of factors.
        freq = [0] * (max_factor + 1)

        for factor, price in items:
            freq[factor] += 1

        # Count item types whose factors are multiples of f.
        total = [0] * (max_factor + 1)

        for f in range(1, max_factor + 1):
            for multiple in range(f, max_factor + 1, f):
                total[f] += freq[multiple]

        # Store useful bonus batches: (price, gain).
        batches = []
        limit = 2 * min_price

        for factor, price in items:
            gain = total[factor] - 1

            if gain > 0 and price < limit:
                batches.append((price, gain))

        # Sort by ascending price.
        batches.sort()

        answer = 0
        remaining_budget = budget

        # Buy the cheapest bonus copies first.
        for price, gain in batches:
            take = min(gain, remaining_budget // price)

            answer += 2 * take
            remaining_budget -= take * price

        # Buy regular copies using the cheapest item.
        answer += remaining_budget // min_price

        return answer


#Approach-2 (Divisibility Sieve + Min-Heap Greedy)
#T.C: O(n log n)
#S.C: O(n)

class Solution2:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        max_factor = max(factor for factor, price in items)
        min_price = min(price for factor, price in items)

        # Count frequencies of factors.
        freq = [0] * (max_factor + 1)

        for factor, price in items:
            freq[factor] += 1

        # Count item types whose factors are multiples of f.
        total = [0] * (max_factor + 1)

        for f in range(1, max_factor + 1):
            for multiple in range(f, max_factor + 1, f):
                total[f] += freq[multiple]

        # Min-heap ordered by price.
        min_heap = []
        limit = 2 * min_price

        for factor, price in items:
            gain = total[factor] - 1

            if gain > 0 and price < limit:
                heapq.heappush(min_heap, (price, gain))

        answer = 0
        remaining_budget = budget

        # Process cheapest bonus copies first.
        while min_heap and remaining_budget > 0:
            price, gain = heapq.heappop(min_heap)

            take = min(gain, remaining_budget // price)

            answer += 2 * take
            remaining_budget -= take * price

        # Buy regular copies using the cheapest item.
        answer += remaining_budget // min_price

        return answer
