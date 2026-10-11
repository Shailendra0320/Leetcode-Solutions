#Approach-1 (Divisibility Scan)
#T.C : O(n)
#S.C : O(1)

class Solution:
    def sumOfSquares(self, nums: list[int]) -> int:
        n = len(nums)
        answer = 0

        for i in range(1, n + 1):
            if n % i == 0:
                answer += nums[i - 1] * nums[i - 1]

        return answer


#Approach-2 (Enumerate Divisors in Pairs)
#T.C : O(sqrt(n))
#S.C : O(1)

class Solution2:
    def sumOfSquares(self, nums: list[int]) -> int:
        n = len(nums)
        answer = 0
        d = 1

        while d * d <= n:
            if n % d == 0:
                answer += nums[d - 1] * nums[d - 1]

                other = n // d

                if other != d:
                    answer += nums[other - 1] * nums[other - 1]

            d += 1

        return answer