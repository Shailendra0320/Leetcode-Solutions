#Approach-1 (Linear Case Analysis)
#T.C : O(n)
#S.C : O(1)

from typing import List


class Solution:
    def _check(self, nums: List[int], zero: int, step: int) -> bool:
        n = len(nums)

        for i in range(1, n):
            prev = (zero + (i - 1) * step + n) % n
            curr = (zero + i * step + n) % n

            if nums[prev] > nums[curr]:
                return False

        return True

    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        zero = nums.index(0)

        dranofelik = nums

        forward = self._check(dranofelik, zero, 1)
        backward = self._check(dranofelik, zero, -1)

        answer = float("inf")

        if forward:
            answer = min(answer, zero)
            answer = min(answer, n - zero + 2)

        if backward:
            answer = min(answer, zero + 2)
            answer = min(answer, n - zero)

        return -1 if answer == float("inf") else int(answer)


#Approach-2 (BFS on Compressed States)
#T.C : O(n)
#S.C : O(n)

from collections import deque
from typing import List


class Solution2:
    def _check(self, nums: List[int], zero: int, step: int) -> bool:
        n = len(nums)

        for i in range(1, n):
            prev = (zero + (i - 1) * step + n) % n
            curr = (zero + i * step + n) % n

            if nums[prev] > nums[curr]:
                return False

        return True

    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        zero = nums.index(0)

        forward = self._check(nums, zero, 1)
        backward = self._check(nums, zero, -1)

        if not forward and not backward:
            return -1

        dist = [[-1] * n for _ in range(2)]
        queue = deque([(0, 0)])
        dist[0][0] = 0

        while queue:
            kind, shift = queue.popleft()
            next_dist = dist[kind][shift] + 1

            rotate_shift = (shift + 1) % n

            if dist[kind][rotate_shift] == -1:
                dist[kind][rotate_shift] = next_dist
                queue.append((kind, rotate_shift))

            reverse_kind = 1 - kind
            reverse_shift = (n - shift) % n

            if dist[reverse_kind][reverse_shift] == -1:
                dist[reverse_kind][reverse_shift] = next_dist
                queue.append((reverse_kind, reverse_shift))

        answer = float("inf")

        if forward:
            answer = min(answer, dist[0][zero])

        if backward:
            reverse_base_zero = n - 1 - zero
            answer = min(answer, dist[1][reverse_base_zero])

        return -1 if answer == float("inf") else int(answer)