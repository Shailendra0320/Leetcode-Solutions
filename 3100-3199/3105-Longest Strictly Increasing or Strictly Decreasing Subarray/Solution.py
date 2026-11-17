
#Approach-1 (Single Pass with Two Counters)
#T.C: O(n)
#S.C: O(1)

from typing import List

class Solution:
    def longestMonotonicSubarray(self, nums: List[int]) -> int:
        inc = 1
        dec = 1
        answer = 1

        for i in range(1, len(nums)):
            if nums[i] > nums[i - 1]:
                inc += 1
                dec = 1
            elif nums[i] < nums[i - 1]:
                dec += 1
                inc = 1
            else:
                inc = 1
                dec = 1

            answer = max(answer, inc, dec)

        return answer


#Approach-2 (Two Independent Passes)
#T.C: O(n)
#S.C: O(1)

class Solution2:
    def longestMonotonicSubarray(self, nums: List[int]) -> int:
        n = len(nums)

        increasing = 1
        max_increasing = 1

        for i in range(1, n):
            if nums[i] > nums[i - 1]:
                increasing += 1
            else:
                increasing = 1

            max_increasing = max(max_increasing, increasing)

        decreasing = 1
        max_decreasing = 1

        for i in range(1, n):
            if nums[i] < nums[i - 1]:
                decreasing += 1
            else:
                decreasing = 1

            max_decreasing = max(max_decreasing, decreasing)

        return max(max_increasing, max_decreasing)
