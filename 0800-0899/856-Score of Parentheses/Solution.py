#Approach-1 (Stack Evaluation)
#T.C : O(n)
#S.C : O(n)

class Solution:

    def scoreOfParentheses(self, s: str) -> int:
        stack = [0]

        for ch in s:

            if ch == '(':
                stack.append(0)
            else:
                inner = stack.pop()
                score = 1 if inner == 0 else 2 * inner
                stack[-1] += score

        return stack[-1]


#Approach-2 (Depth-Based Scoring)
#T.C : O(n)
#S.C : O(1)

class Solution2:

    def scoreOfParentheses(self, s: str) -> int:
        depth = 0
        answer = 0

        for i, ch in enumerate(s):

            if ch == '(':
                depth += 1
            else:
                if s[i - 1] == '(':
                    answer += 1 << (depth - 1)

                depth -= 1

        return answer