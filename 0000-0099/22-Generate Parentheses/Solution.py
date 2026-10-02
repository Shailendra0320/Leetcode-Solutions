#Approach-1 (Backtracking / DFS)
#T.C : O(Cn * n)
#S.C : O(n) auxiliary, excluding output

class Solution:

    def generateParenthesis(self, n: int):
        ans = []
        current = []

        def backtrack(open_count, close_count):
            if open_count == n and close_count == n:
                ans.append("".join(current))
                return

            if open_count < n:
                current.append('(')
                backtrack(open_count + 1, close_count)
                current.pop()

            if close_count < open_count:
                current.append(')')
                backtrack(open_count, close_count + 1)
                current.pop()

        backtrack(0, 0)

        return ans


#Approach-2 (Dynamic Programming / Catalan Decomposition)
#T.C : O(Cn * n)
#S.C : O(Cn * n)

class Solution2:

    def generateParenthesis(self, n: int):
        dp = [[] for _ in range(n + 1)]
        dp[0].append("")

        for pairs in range(1, n + 1):
            for left_pairs in range(pairs):
                right_pairs = pairs - 1 - left_pairs

                for left in dp[left_pairs]:
                    for right in dp[right_pairs]:
                        dp[pairs].append("(" + left + ")" + right)

        return dp[n]