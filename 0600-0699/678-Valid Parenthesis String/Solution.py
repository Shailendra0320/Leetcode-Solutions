#Approach-1 (Greedy Balance Range)
#T.C : O(n)
#S.C : O(1)

class Solution:

    def checkValidString(self, s: str) -> bool:
        low = 0
        high = 0

        for ch in s:

            if ch == '(':
                low += 1
                high += 1
            elif ch == ')':
                low -= 1
                high -= 1
            else:
                low -= 1
                high += 1

            low = max(low, 0)

            if high < 0:
                return False

        return low == 0


#Approach-2 (Two Stacks with Index Matching)
#T.C : O(n)
#S.C : O(n)

class Solution2:

    def checkValidString(self, s: str) -> bool:
        open_stack = []
        star_stack = []

        for i, ch in enumerate(s):

            if ch == '(':
                open_stack.append(i)
            elif ch == '*':
                star_stack.append(i)
            else:

                if open_stack:
                    open_stack.pop()
                elif star_stack:
                    star_stack.pop()
                else:
                    return False

        while open_stack and star_stack:

            if open_stack[-1] > star_stack[-1]:
                return False

            open_stack.pop()
            star_stack.pop()

        return not open_stack