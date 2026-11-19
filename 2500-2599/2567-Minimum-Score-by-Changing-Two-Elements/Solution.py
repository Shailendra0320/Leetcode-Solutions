# Approach-1 (Sorting + Three Possible Cases)
# T.C: O(n log n)
# S.C: O(1) auxiliary, excluding sorting internals

class Solution:
    def minDifference(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)

        case1 = nums[n - 1] - nums[2]
        case2 = nums[n - 2] - nums[1]
        case3 = nums[n - 3] - nums[0]

        return min(case1, case2, case3)


# Approach-2 (Sorting + Sliding Window of Size n - 2)
# T.C: O(n log n)
# S.C: O(1) auxiliary, excluding sorting internals

class Solution2:
    def minDifference(self, nums: List[int]) -> int:
        nums.sort()

        n = len(nums)
        windowSize = n - 2
        answer = float('inf')

        for start in range(3):
            end = start + windowSize - 1
            currentScore = nums[end] - nums[start]

            answer = min(answer, currentScore)

        return answer