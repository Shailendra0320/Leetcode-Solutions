# Approach-1 (Depth Tracking)
# T.C : O(n)
# S.C : O(n)

class Solution:

    def removeOuterParentheses(self, s: str) -> str:
        answer = []
        depth = 0

        for ch in s:
            if ch == '(':
                if depth > 0:
                    answer.append(ch)
                depth += 1
            else:
                depth -= 1
                if depth > 0:
                    answer.append(ch)

        return ''.join(answer)


# Approach-2 (Primitive Boundary Tracking)
# T.C : O(n)
# S.C : O(n)

class Solution2:

    def removeOuterParentheses(self, s: str) -> str:
        answer = []
        balance = 0
        start = 0

        for i in range(len(s)):
            if s[i] == '(':
                balance += 1
            else:
                balance -= 1

            if balance == 0:
                answer.append(s[start + 1:i])
                start = i + 1

        return ''.join(answer)