#Approach-1 (Maximum Value + Total Sum)
#T.C : O(n)
#S.C : O(1)

from typing import List

class Solution:
    def minMoves(self, nums: List[int]) -> int:
        max_value = 0
        total_sum = 0

        for num in nums:
            max_value = max(max_value, num)
            total_sum += num

        return max_value * len(nums) - total_sum


#Approach-2 (Maximum Value + Element-Wise Differences)
#T.C : O(n)
#S.C : O(1)

class Solution2:
    def minMoves(self, nums: List[int]) -> int:
        max_value = max(nums)
        moves = 0

        for num in nums:
            moves += max_value - num

        return moves