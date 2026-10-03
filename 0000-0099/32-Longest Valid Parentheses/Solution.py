#Approach-1 (Stack of Indices)
#T.C : O(n)
#S.C : O(n)

class Solution:

    def longestValidParentheses(self, s: str) -> int:
        stack = [-1]
        max_len = 0

        for i, ch in enumerate(s):

            if ch == '(':
                stack.append(i)
            else:
                stack.pop()

                if not stack:
                    stack.append(i)
                else:
                    max_len = max(max_len, i - stack[-1])

        return max_len


#Approach-2 (Two-Pass Counter / Constant Space)
#T.C : O(n)
#S.C : O(1)

class Solution2:

    def longestValidParentheses(self, s: str) -> int:
        max_len = 0
        left = 0
        right = 0

        for ch in s:

            if ch == '(':
                left += 1
            else:
                right += 1

            if left == right:
                max_len = max(max_len, 2 * right)
            elif right > left:
                left = 0
                right = 0

        left = 0
        right = 0

        for ch in reversed(s):

            if ch == '(':
                left += 1
            else:
                right += 1

            if left == right:
                max_len = max(max_len, 2 * left)
            elif left > right:
                left = 0
                right = 0

        return max_len