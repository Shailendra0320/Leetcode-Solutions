#Approach-1 (Sliding Window + Monotonic Deque: Value-Count Pairs)
#T.C: O(n)
#S.C: O(n)

from typing import List
from collections import deque



class Solution:
    def countNonDecreasingSubarrays(
        self, nums: List[int], k: int
    ) -> int:

        n = len(nums)
        dq = deque()

        answer = 0
        cost = 0
        right = n - 1

        for left in range(n - 1, -1, -1):
            num = nums[left]
            count = 1

            while dq and dq[-1][0] < num:
                next_num, next_count = dq.pop()

                count += next_count
                cost += (num - next_num) * next_count

            dq.append((num, count))

            while cost > k:
                rightmost_num, rightmost_count = dq.popleft()

                cost -= rightmost_num - nums[right]

                right -= 1

                if rightmost_count > 1:
                    dq.appendleft(
                        (rightmost_num, rightmost_count - 1)
                    )

            answer += right - left + 1

        return answer


#Approach-2 (Sliding Window + Monotonic Deque: Indices)
#T.C: O(n)
#S.C: O(n)

class Solution2:
    def countNonDecreasingSubarrays(
        self, nums: List[int], k: int
    ) -> int:

        n = len(nums)
        dq = deque()

        answer = 0
        cost = 0
        right = n - 1

        for left in range(n - 1, -1, -1):
            num = nums[left]

            while dq and nums[dq[-1]] < num:
                l = dq.pop()

                r = dq[-1] if dq else right + 1

                cost += (r - l) * (num - nums[l])

            dq.append(left)

            while cost > k:
                cost -= nums[dq[0]] - nums[right]

                if dq[0] == right:
                    dq.popleft()

                right -= 1

            answer += right - left + 1

        return answer