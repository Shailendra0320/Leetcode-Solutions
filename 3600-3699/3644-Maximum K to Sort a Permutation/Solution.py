#Approach-1 (Bitwise AND of Misplaced Values)
#T.C : O(n)
#S.C : O(1)

from typing import List

class Solution:
    def sortPermutation(self, nums: List[int]) -> int:
        ans = -1

        for i, num in enumerate(nums):
            if num != i:
                ans &= num

        return max(ans, 0)


#Approach-2 (Build the Answer Bit by Bit)
#T.C : O(31 * n) = O(n)
#S.C : O(1)

class Solution2:
    def sortPermutation(self, nums: List[int]) -> int:
        misplaced = any(num != i for i, num in enumerate(nums))

        if not misplaced:
            return 0

        ans = 0

        for bit in range(30, -1, -1):
            mask = 1 << bit
            common = True

            for i, num in enumerate(nums):
                if num != i and (num & mask) == 0:
                    common = False
                    break

            if common:
                ans |= mask

        return ans