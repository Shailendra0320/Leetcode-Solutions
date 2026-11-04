# Approach-1 (One Pass: Track Sum and Minimum)
# T.C: O(n)
# S.C: O(1)

class Solution:
    def minMoves(self, nums: List[int]) -> int:
        total_sum = 0
        minimum = nums[0]

        for num in nums:
            total_sum += num
            minimum = min(minimum, num)

        return total_sum - minimum * len(nums)


# Approach-2 (Two Passes: Add Every Difference)
# T.C: O(n)
# S.C: O(1)

class Solution2:
    def minMoves(self, nums: List[int]) -> int:
        minimum = min(nums)

        answer = 0

        for num in nums:
            answer += num - minimum

        return answer