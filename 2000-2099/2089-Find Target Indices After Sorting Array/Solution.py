#Approach-1 (Sort and Scan)
#T.C : O(n log n)
#S.C : O(n)

from typing import List

class Solution:
    def targetIndices(self, nums: List[int], target: int) -> List[int]:
        nums.sort()
        ans = []

        for i, num in enumerate(nums):
            if num == target:
                ans.append(i)

        return ans


#Approach-2 (Frequency Counting Without Sorting)
#T.C : O(n)
#S.C : O(1) auxiliary space, excluding output

class Solution2:
    def targetIndices(self, nums: List[int], target: int) -> List[int]:
        smaller = 0
        equal = 0

        for num in nums:
            if num < target:
                smaller += 1
            elif num == target:
                equal += 1

        return list(range(smaller, smaller + equal))