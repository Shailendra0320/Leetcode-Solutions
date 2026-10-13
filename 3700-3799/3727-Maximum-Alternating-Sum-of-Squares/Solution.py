
class Solution:

    # Approach 1: Use a separate squared list
    # Time Complexity: O(n log n)
    # Space Complexity: O(n)

    def maxAlternatingSum(self, nums: list[int]) -> int:
        squares = sorted(x * x for x in nums)
        mid = len(nums) // 2

        return sum(squares[mid:]) - sum(squares[:mid])

    # Approach 2: Square and sort the input list
    # Time Complexity: O(n log n)
    # Auxiliary Space Complexity: O(n) for sorting and slices
    # Note: This approach modifies nums.

    def maxAlternatingSumApproach2(self, nums: list[int]) -> int:
        for i in range(len(nums)):
            nums[i] *= nums[i]

        nums.sort()

        mid = len(nums) // 2
        return sum(nums[mid:]) - sum(nums[:mid])
