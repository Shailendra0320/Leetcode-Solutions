#Approach-1 (Lazy Segment Tree: Sum and Sum of Squares)
#T.C : O(n log n)
#S.C : O(n)

from typing import List

MOD = 1_000_000_007


class Solution:
    class SegmentTree:
        def __init__(self, n: int):
            self.n = n
            size = 4 * n

            self.sum = [0] * size
            self.square_sum = [0] * size
            self.lazy = [0] * size

        def _apply(self, node: int, left: int,
                   right: int, delta: int) -> None:
            length = right - left + 1
            old_sum = self.sum[node]

            self.square_sum[node] = (
                self.square_sum[node]
                + 2 * delta * old_sum
                + length * delta * delta
            ) % MOD

            self.sum[node] = (
                old_sum + length * delta
            ) % MOD

            self.lazy[node] += delta

        def _push(self, node: int, left: int,
                  right: int) -> None:
            if self.lazy[node] == 0 or left == right:
                return

            mid = (left + right) // 2
            pending = self.lazy[node]

            self._apply(node * 2, left, mid, pending)
            self._apply(node * 2 + 1, mid + 1, right, pending)

            self.lazy[node] = 0

        def _update(self, node: int, left: int, right: int,
                    ql: int, qr: int) -> None:
            if ql <= left and right <= qr:
                self._apply(node, left, right, 1)
                return

            self._push(node, left, right)

            mid = (left + right) // 2

            if ql <= mid:
                self._update(node * 2, left, mid, ql, qr)

            if qr > mid:
                self._update(node * 2 + 1, mid + 1, right, ql, qr)

            self.sum[node] = (
                self.sum[node * 2] + self.sum[node * 2 + 1]
            ) % MOD

            self.square_sum[node] = (
                self.square_sum[node * 2]
                + self.square_sum[node * 2 + 1]
            ) % MOD

        def add_one(self, left: int, right: int) -> None:
            if left <= right:
                self._update(1, 0, self.n - 1, left, right)

        def get_square_sum(self) -> int:
            return self.square_sum[1]

    def sumCounts(self, nums: List[int]) -> int:
        n = len(nums)
        last_seen = {}
        tree = self.SegmentTree(n)
        answer = 0

        for i, value in enumerate(nums):
            previous = last_seen.get(value, -1)

            tree.add_one(previous + 1, i)

            answer = (answer + tree.get_square_sum()) % MOD

            last_seen[value] = i

        return answer


#Approach-2 (Range Sum Tree + Running Square Total)
#T.C : O(n log n)
#S.C : O(n)

class Solution2:
    class SegmentTree:
        def __init__(self, n: int):
            self.n = n
            self.sum = [0] * (4 * n)
            self.lazy = [0] * (4 * n)

        def _apply(self, node: int, left: int,
                   right: int, delta: int) -> None:
            length = right - left + 1

            self.sum[node] = (
                self.sum[node] + length * delta
            ) % MOD

            self.lazy[node] += delta

        def _push(self, node: int, left: int,
                  right: int) -> None:
            if self.lazy[node] == 0 or left == right:
                return

            mid = (left + right) // 2
            pending = self.lazy[node]

            self._apply(node * 2, left, mid, pending)
            self._apply(node * 2 + 1, mid + 1, right, pending)

            self.lazy[node] = 0

        def _update(self, node: int, left: int, right: int,
                    ql: int, qr: int) -> None:
            if ql <= left and right <= qr:
                self._apply(node, left, right, 1)
                return

            self._push(node, left, right)

            mid = (left + right) // 2

            if ql <= mid:
                self._update(node * 2, left, mid, ql, qr)

            if qr > mid:
                self._update(node * 2 + 1, mid + 1, right, ql, qr)

            self.sum[node] = (
                self.sum[node * 2] + self.sum[node * 2 + 1]
            ) % MOD

        def _query(self, node: int, left: int, right: int,
                   ql: int, qr: int) -> int:
            if ql <= left and right <= qr:
                return self.sum[node]

            self._push(node, left, right)

            mid = (left + right) // 2
            result = 0

            if ql <= mid:
                result += self._query(
                    node * 2, left, mid, ql, qr
                )

            if qr > mid:
                result += self._query(
                    node * 2 + 1, mid + 1, right, ql, qr
                )

            return result % MOD

        def add_one(self, left: int, right: int) -> None:
            self._update(1, 0, self.n - 1, left, right)

        def range_sum(self, left: int, right: int) -> int:
            return self._query(1, 0, self.n - 1, left, right)

    def sumCounts(self, nums: List[int]) -> int:
        n = len(nums)
        last_seen = {}
        tree = self.SegmentTree(n)

        square_ending = 0
        answer = 0

        for i, value in enumerate(nums):
            previous = last_seen.get(value, -1)
            left = previous + 1

            old_range_sum = tree.range_sum(left, i)
            length = i - left + 1

            square_ending = (
                square_ending + 2 * old_range_sum + length
            ) % MOD

            tree.add_one(left, i)

            answer = (answer + square_ending) % MOD

            last_seen[value] = i

        return answer