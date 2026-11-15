#Approach-1 (Prefix Sum Parity + Frequency Counting)
#T.C: O(n)
#S.C: O(1)

from typing import List

class Solution:
    def numOfSubarrays(self, arr: List[int]) -> int:
        MOD = 10**9 + 7

        even = 1
        odd = 0
        prefix_parity = 0

        answer = 0

        for num in arr:
            prefix_parity ^= (num & 1)

            if prefix_parity == 0:
                answer = (answer + odd) % MOD
                even += 1
            else:
                answer = (answer + even) % MOD
                odd += 1

        return answer


#Approach-2 (Dynamic Programming: Odd and Even Subarrays)
#T.C: O(n)
#S.C: O(1)

class Solution2:
    def numOfSubarrays(self, arr: List[int]) -> int:
        MOD = 10**9 + 7

        odd = 0
        even = 0
        answer = 0

        for num in arr:
            if num % 2 == 0:
                even = (even + 1) % MOD
            else:
                temp = odd

                odd = (even + 1) % MOD
                even = temp

            answer = (answer + odd) % MOD

        return answer