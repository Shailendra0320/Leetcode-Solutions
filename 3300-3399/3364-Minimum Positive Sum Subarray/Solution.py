#Approach-1 (Brute Force: Running Sum from Every Starting Index)
#T.C: O(n * r) - O(n^2) in the worst case
#S.C: O(1)

from typing import List

class Solution:
    def minimumSumSubarray(self, nums: List[int], l: int, r: int) -> int:
        n = len(nums)
        answer = float('inf')

        for start in range(n):
            current_sum = 0
            limit = min(n, start + r)

            for end in range(start, limit):
                current_sum += nums[end]
                length = end - start + 1

                if length >= l and current_sum > 0:
                    answer = min(answer, current_sum)

        return -1 if answer == float('inf') else answer


#Approach-2 (Fixed-Size Sliding Window)
#T.C: O(n * (r - l + 1)) - O(n^2) in the worst case
#S.C: O(1)

class Solution2:
    def minimumSumSubarray(self, nums: List[int], l: int, r: int) -> int:
        n = len(nums)
        answer = float('inf')

        for size in range(l, r + 1):
            current_sum = sum(nums[:size])

            if current_sum > 0:
                answer = min(answer, current_sum)

            for right in range(size, n):
                current_sum += nums[right] - nums[right - size]

                if current_sum > 0:
                    answer = min(answer, current_sum)

        return -1 if answer == float('inf') else answer