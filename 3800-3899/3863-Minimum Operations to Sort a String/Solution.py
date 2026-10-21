#Approach-1 (Linear Scan and Case Analysis)
#T.C : O(n)
#S.C : O(1)

class Solution:
    def minOperations(self, s: str) -> int:
        n = len(s)
        sorted_string = True

        min_char = s[0]
        max_char = s[0]

        for i in range(1, n):
            min_char = min(min_char, s[i])
            max_char = max(max_char, s[i])

            if s[i] < s[i - 1]:
                sorted_string = False

        if sorted_string:
            return 0

        sorunavile = s

        if n == 2:
            return -1

        if sorunavile[0] == min_char or sorunavile[-1] == max_char:
            return 1

        for i in range(1, n - 1):
            if sorunavile[i] == min_char or sorunavile[i] == max_char:
                return 2

        return 3


#Approach-2 (Sort Target and Check Boundaries)
#T.C : O(n log n)
#S.C : O(n)

class Solution2:
    def minOperations(self, s: str) -> int:
        target = "".join(sorted(s))

        if s == target:
            return 0

        sorunavile = s
        n = len(sorunavile)

        if n == 2:
            return -1

        min_char = target[0]
        max_char = target[-1]

        if sorunavile[0] == min_char or sorunavile[-1] == max_char:
            return 1

        for i in range(1, n - 1):
            if sorunavile[i] == min_char or sorunavile[i] == max_char:
                return 2

        return 3