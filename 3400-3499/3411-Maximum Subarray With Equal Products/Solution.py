
# Approach-1 (Brute Force + Arbitrary Precision Integer)
# T.C: O(n^2) subarray extensions, excluding integer arithmetic cost
# S.C: O(1) auxiliary space, excluding integer storage

class Solution:
    def maxLength(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0

        for i in range(n):
            product = 1
            gcd_val = 0
            lcm_val = 1

            for j in range(i, n):
                x = nums[j]

                product *= x

                gcd_val = gcd(gcd_val, x)
                lcm_val = lcm(lcm_val, x)

                if product == gcd_val * lcm_val:
                    ans = max(ans, j - i + 1)

        return ans

# Approach-2 (Optimized Brute Force + Product Bound)
# T.C: O(n^2)
# S.C: O(1)

class Solution2:
    def maxLength(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0

        global_lcm = 1
        max_value = max(nums)

        for x in nums:
            global_lcm = lcm(global_lcm, x)

        limit = global_lcm * max_value

        for i in range(n):
            product = 1
            gcd_val = 0
            lcm_val = 1

            for j in range(i, n):
                x = nums[j]

                product *= x

                # Product only increases, so stop when it exceeds the limit.
                if product > limit:
                    break

                gcd_val = gcd(gcd_val, x)
                lcm_val = lcm(lcm_val, x)

                if product == gcd_val * lcm_val:
                    ans = max(ans, j - i + 1)

        return ans