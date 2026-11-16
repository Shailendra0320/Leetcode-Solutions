#Approach-1 (Iterative Simulation)
#T.C: O(n)
#S.C: O(n)

class Solution:
    def digitSum(self, s: str, k: int) -> str:
        while len(s) > k:
            next_string = []

            for i in range(0, len(s), k):
                group = s[i:i + k]
                digit_sum = sum(int(ch) for ch in group)

                next_string.append(str(digit_sum))

            s = "".join(next_string)

        return s


#Approach-2 (Recursive Simulation)
#T.C: O(n)
#S.C: O(n)

class Solution2:
    def digitSum(self, s: str, k: int) -> str:
        if len(s) <= k:
            return s

        next_string = []

        for i in range(0, len(s), k):
            group = s[i:i + k]
            digit_sum = sum(int(ch) for ch in group)

            next_string.append(str(digit_sum))

        return self.digitSum("".join(next_string), k)