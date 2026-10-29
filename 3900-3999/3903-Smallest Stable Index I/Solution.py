#Approach-1 (Check Every Index from Scratch)
#T.C : O(n^2)
#S.C : O(1)

from typing import List

class Solution:
    def firstStableIndex(self, nums: List[int], k: int) -> int:
        n = len(nums)

        for i in range(n):
            prefix_max = nums[0]

            for j in range(1, i + 1):
                prefix_max = max(prefix_max, nums[j])

            suffix_min = nums[i]

            for j in range(i + 1, n):
                suffix_min = min(suffix_min, nums[j])

            if prefix_max - suffix_min <= k:
                return i

        return -1


#Approach-2 (Suffix Minimum + Running Prefix Maximum)
#T.C : O(n)
#S.C : O(n)

class Solution2:
    def firstStableIndex(self, nums: List[int], k: int) -> int:
        n = len(nums)
        right_min = [0] * n

        right_min[n - 1] = nums[n - 1]

        for i in range(n - 2, -1, -1):
            right_min[i] = min(nums[i], right_min[i + 1])

        left_max = nums[0]

        for i in range(n):
            left_max = max(left_max, nums[i])

            if left_max - right_min[i] <= k:
                return i

        return -1