class Solution:

    #Approach-1 (Stack with Direct Matching)
    #T.C : O(n)
    #S.C : O(n)

    def isValid(self, s: str) -> bool:
        stack = []

        for ch in s:
            if ch in "([{":
                stack.append(ch)
            else:
                if not stack:
                    return False

                top = stack.pop()

                if ((ch == ')' and top != '(') or
                    (ch == ']' and top != '[') or
                    (ch == '}' and top != '{')):
                    return False

        return not stack


class Solution2:

    #Approach-2 (Stack of Expected Closing Brackets)
    #T.C : O(n)
    #S.C : O(n)

    def isValid(self, s: str) -> bool:
        stack = []

        for ch in s:
            if ch == '(':
                stack.append(')')
            elif ch == '[':
                stack.append(']')
            elif ch == '{':
                stack.append('}')
            else:
                if not stack or stack.pop() != ch:
                    return False

        return not stack