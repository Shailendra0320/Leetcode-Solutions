#Approach-1 (Set Enumeration)
#T.C : O(n^2) expected
#S.C : O(n)

from typing import List

class Solution:
    def sumCounts(self, nums: List[int]) -> int:
        n = len(nums)
        answer = 0

        for i in range(n):
            seen = set()

            for j in range(i, n):
                seen.add(nums[j])

                distinct = len(seen)
                answer += distinct * distinct

        return answer


#Approach-2 (Fixed Boolean Array)
#T.C : O(n^2)
#S.C : O(1)

class Solution2:
    def sumCounts(self, nums: List[int]) -> int:
        n = len(nums)
        answer = 0

        for i in range(n):
            seen = [False] * 101
            distinct = 0

            for j in range(i, n):
                value = nums[j]

                if not seen[value]:
                    seen[value] = True
                    distinct += 1

                answer += distinct * distinct

        return answer