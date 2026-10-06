#Approach-1 (Greedy Balance Counting)
#T.C : O(n)
#S.C : O(1)

class Solution:

    def minAddToMakeValid(self, s: str) -> int:
        open_count = 0
        answer = 0

        for ch in s:

            if ch == '(':
                open_count += 1
            else:
                if open_count > 0:
                    open_count -= 1
                else:
                    answer += 1

        return answer + open_count


#Approach-2 (Stack-Based Matching)
#T.C : O(n)
#S.C : O(n)

class Solution2:

    def minAddToMakeValid(self, s: str) -> int:
        stack = []

        for ch in s:

            if ch == '(':
                stack.append(ch)
            else:
                if stack and stack[-1] == '(':
                    stack.pop()
                else:
                    stack.append(ch)

        return len(stack)