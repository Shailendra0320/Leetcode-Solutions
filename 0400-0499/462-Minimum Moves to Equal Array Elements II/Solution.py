#Approach-1 (Sorting + Median)
#T.C : O(n log n)
#S.C : O(n) for Python sorting

class Solution:
    def minMoves2(self, nums: List[int]) -> int:
        nums.sort()

        n = len(nums)
        median = nums[n // 2]

        moves = 0

        for num in nums:
            moves += abs(num - median)

        return moves


#Approach-2 (Randomized Quickselect + Median)
#T.C : O(n) expected, O(n^2) worst case
#S.C : O(1) auxiliary space

import random
from typing import List


class Solution2:
    def quickSelect(self, nums: List[int], target: int) -> int:
        left = 0
        right = len(nums) - 1

        while left <= right:
            pivot = nums[random.randrange(left, right + 1)]

            low = left
            mid = left
            high = right

            while mid <= high:
                if nums[mid] < pivot:
                    nums[low], nums[mid] = nums[mid], nums[low]
                    low += 1
                    mid += 1

                elif nums[mid] > pivot:
                    nums[mid], nums[high] = nums[high], nums[mid]
                    high -= 1

                else:
                    mid += 1

            if target < low:
                right = low - 1

            elif target > high:
                left = high + 1

            else:
                return nums[target]

        return nums[target]

    def minMoves2(self, nums: List[int]) -> int:
        median = self.quickSelect(nums, len(nums) // 2)

        moves = 0

        for num in nums:
            moves += abs(num - median)

        return moves