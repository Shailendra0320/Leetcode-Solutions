#Approach-1 (Direct Block Matching with KMP)
#T.C : O(n log n + n * D), where D is the number of divisors of n
#S.C : O(n)

from math import isqrt
from typing import List


class Solution:
    def sortableIntegers(self, nums: List[int]) -> int:
        n = len(nums)
        sorted_nums = sorted(nums)
        answer = 0

        for d in range(1, isqrt(n) + 1):
            if n % d != 0:
                continue

            if self._is_sortable_for_k(nums, sorted_nums, d):
                answer += d

            other = n // d

            if other != d and self._is_sortable_for_k(nums, sorted_nums, other):
                answer += other

        return answer

    def _is_sortable_for_k(
        self, nums: List[int], sorted_nums: List[int], k: int
    ) -> bool:
        for start in range(0, len(nums), k):
            if not self._is_rotation(nums, sorted_nums, start, k):
                return False

        return True

    def _is_rotation(
        self, nums: List[int], sorted_nums: List[int], start: int, k: int
    ) -> bool:
        lps = [0] * k
        length = 0
        i = 1

        while i < k:
            if sorted_nums[start + i] == sorted_nums[start + length]:
                length += 1
                lps[i] = length
                i += 1
            elif length > 0:
                length = lps[length - 1]
            else:
                lps[i] = 0
                i += 1

        text_index = 0
        pattern_index = 0
        text_length = 2 * k - 1

        while text_index < text_length:
            value = nums[start + text_index % k]

            if value == sorted_nums[start + pattern_index]:
                text_index += 1
                pattern_index += 1

                if pattern_index == k:
                    return True
            elif pattern_index > 0:
                pattern_index = lps[pattern_index - 1]
            else:
                text_index += 1

        return False


#Approach-2 (Circular Drops and Boundary Arrays)
#T.C : O(n + sum(n / k) for all divisors k of n)
#S.C : O(n)

class Solution2:
    def sortableIntegers(self, nums: List[int]) -> int:
        n = len(nums)

        pref_max = [0] * (n + 1)

        for i, value in enumerate(nums):
            pref_max[i + 1] = max(pref_max[i], value)

        inf = 10**18
        suff_min = [inf] * (n + 1)

        for i in range(n - 1, -1, -1):
            suff_min[i] = min(nums[i], suff_min[i + 1])

        pref_drop = [0] * n

        for i in range(1, n):
            pref_drop[i] = pref_drop[i - 1] + (
                1 if nums[i - 1] > nums[i] else 0
            )

        def is_sortable_for_k(k: int) -> bool:
            for start in range(0, n, k):
                end = start + k - 1
                drops = pref_drop[end] - pref_drop[start]

                if nums[end] > nums[start]:
                    drops += 1

                if drops > 1 or pref_max[start] > suff_min[start]:
                    return False

            return True

        answer = 0

        for d in range(1, isqrt(n) + 1):
            if n % d != 0:
                continue

            if is_sortable_for_k(d):
                answer += d

            other = n // d

            if other != d and is_sortable_for_k(other):
                answer += other

        return answer