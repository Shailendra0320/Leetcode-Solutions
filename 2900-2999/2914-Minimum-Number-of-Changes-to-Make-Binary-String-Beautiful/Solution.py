# Approach-1 (Greedy Pair-by-Pair Counting)
# T.C: O(n)
# S.C: O(1)

class Solution:
    def minChanges(self, s: str) -> int:
        changes = 0

        for i in range(0, len(s), 2):
            if s[i] != s[i + 1]:
                changes += 1

        return changes


# Approach-2 (Count Zeroes and Ones in Each Pair)
# T.C: O(n)
# S.C: O(1)

class Solution2:
    def minChanges(self, s: str) -> int:
        changes = 0

        for i in range(0, len(s), 2):
            countZero = 0
            countOne = 0

            if s[i] == '0':
                countZero += 1
            else:
                countOne += 1

            if s[i + 1] == '0':
                countZero += 1
            else:
                countOne += 1

            changes += min(countZero, countOne)

        return changes