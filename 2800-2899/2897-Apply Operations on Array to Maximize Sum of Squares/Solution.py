#Approach-1 (Greedy Bit Counting with Decrement)
#T.C : O(31 * (n + k))
#S.C : O(31)

class Solution:
    def maxSum(self, nums: List[int], k: int) -> int:
        MOD = 1_000_000_007
        count = [0] * 31

        for num in nums:
            for bit in range(31):
                if num & (1 << bit):
                    count[bit] += 1

        answer = 0

        for _ in range(k):
            x = 0

            for bit in range(31):
                if count[bit] > 0:
                    x |= 1 << bit
                    count[bit] -= 1

            answer = (answer + x * x) % MOD

        return answer


#Approach-2 (Greedy Bit-Frequency Ranking)
#T.C : O(31 * (n + k))
#S.C : O(31)

class Solution2:
    def maxSum(self, nums: List[int], k: int) -> int:
        MOD = 1_000_000_007
        count = [0] * 31

        for num in nums:
            for bit in range(31):
                if num & (1 << bit):
                    count[bit] += 1

        answer = 0

        for i in range(k):
            x = 0

            for bit in range(31):
                if count[bit] > i:
                    x |= 1 << bit

            answer = (answer + x * x) % MOD

        return answer