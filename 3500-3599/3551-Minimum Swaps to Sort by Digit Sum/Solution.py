#Approach-1 (Permutation Cycle Decomposition)
#T.C : O(n log n)
#S.C : O(n)

from typing import List


class Solution:
    def digit_sum(self, num: int) -> int:
        total = 0

        while num > 0:
            total += num % 10
            num //= 10

        return total

    def minSwaps(self, nums: List[int]) -> int:
        n = len(nums)

        target = sorted(
            nums,
            key=lambda num: (self.digit_sum(num), num)
        )

        target_index = {
            value: i for i, value in enumerate(target)
        }

        visited = [False] * n
        swaps = 0

        for i in range(n):
            if visited[i]:
                continue

            j = i
            cycle_size = 0

            while not visited[j]:
                visited[j] = True
                cycle_size += 1
                j = target_index[nums[j]]

            swaps += cycle_size - 1

        return swaps


#Approach-2 (Greedy Correct-Position Swapping)
#T.C : O(n log n)
#S.C : O(n)

class Solution2:
    def digit_sum(self, num: int) -> int:
        total = 0

        while num > 0:
            total += num % 10
            num //= 10

        return total

    def minSwaps(self, nums: List[int]) -> int:
        n = len(nums)

        target = sorted(
            nums,
            key=lambda num: (self.digit_sum(num), num)
        )

        current = nums[:]
        position = {
            value: i for i, value in enumerate(current)
        }

        swaps = 0

        for i in range(n):
            if current[i] == target[i]:
                continue

            correct_value = target[i]
            j = position[correct_value]
            displaced_value = current[i]

            current[i] = correct_value
            current[j] = displaced_value

            position[correct_value] = i
            position[displaced_value] = j

            swaps += 1

        return swaps