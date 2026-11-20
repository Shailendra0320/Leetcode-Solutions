
class Solution:

    #Approach-1 (Sorting + HashMap DP)
    #T.C: O(n log n)
    #S.C: O(n)

    def maxSelectedElements(self, nums: list[int]) -> int:
        nums.sort()

        dp = {}
        ans = 0

        for x in nums:
            dp[x + 1] = dp.get(x, 0) + 1
            dp[x] = dp.get(x - 1, 0) + 1

            ans = max(ans, dp[x], dp[x + 1])

        return ans


class Solution2:

    #Approach-2 (Sorting + Ordered Dictionary DP)
    #T.C: O(n log n)
    #S.C: O(n)

    def maxSelectedElements(self, nums: list[int]) -> int:
        nums.sort()

        dp = {}
        ans = 0

        for x in nums:
            extend = dp.get(x, 0) + 1
            start = dp.get(x - 1, 0) + 1

            dp[x + 1] = extend
            dp[x] = start

            ans = max(ans, extend, start)

        return ans
