#Approach-1 (Greedy Sorting and Leveling)
#T.C : O(n log n)
#S.C : O(n)

class Solution:

    def minSumSquareDiff(self, nums1, nums2, k1: int, k2: int) -> int:
        diff = sorted(abs(a - b) for a, b in zip(nums1, nums2))
        operations = k1 + k2

        if operations >= sum(diff):
            return 0

        n = len(diff)

        for i in range(n - 1, -1, -1):
            next_level = diff[i - 1] if i > 0 else 0
            count = n - i
            cost = (diff[i] - next_level) * count

            if operations >= cost:
                operations -= cost
            else:
                q, r = divmod(operations, count)
                level = diff[i] - q

                answer = sum(x * x for x in diff[:i])
                answer += (count - r) * level * level
                answer += r * (level - 1) * (level - 1)

                return answer

        return 0


#Approach-2 (Binary Search on Final Difference Cap)
#T.C : O(n log M)
#S.C : O(n)

class Solution2:

    def minSumSquareDiff(self, nums1, nums2, k1: int, k2: int) -> int:
        diff = [abs(a - b) for a, b in zip(nums1, nums2)]
        operations = k1 + k2
        total = sum(diff)

        if operations >= total:
            return 0

        def required_operations(cap):
            return sum(max(d - cap, 0) for d in diff)

        low = 0
        high = max(diff)

        while low < high:
            mid = low + (high - low) // 2

            if required_operations(mid) <= operations:
                high = mid
            else:
                low = mid + 1

        cap = low
        remaining = operations

        for i in range(len(diff)):
            if diff[i] > cap:
                remaining -= diff[i] - cap
                diff[i] = cap

        for i in range(len(diff)):
            if remaining == 0:
                break

            if diff[i] == cap:
                diff[i] -= 1
                remaining -= 1

        return sum(d * d for d in diff)