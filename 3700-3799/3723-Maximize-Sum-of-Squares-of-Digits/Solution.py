#Approach-1 (Greedy Using Quotient and Remainder)
#T.C : O(num)
#S.C : O(num)

class Solution:
    def maxSumOfSquares(self, num: int, sum: int) -> str:
        if sum > 9 * num:
            return ""

        nines = sum // 9
        remainder = sum % 9

        ans = "9" * nines

        if remainder > 0:
            ans += str(remainder)

        ans += "0" * (num - len(ans))

        return ans


#Approach-2 (Greedy Digit-by-Digit Construction)
#T.C : O(num)
#S.C : O(num)

class Solution2:
    def maxSumOfSquares(self, num: int, sum: int) -> str:
        if sum > 9 * num:
            return ""

        remaining_sum = sum
        digits = []

        for _ in range(num):
            digit = min(9, remaining_sum)

            digits.append(str(digit))
            remaining_sum -= digit

        return "".join(digits)