#Approach-1 (Greedy Required-Closing Counter)
#T.C : O(n)
#S.C : O(1)

class Solution:

    def minInsertions(self, s: str) -> int:
        answer = 0
        need = 0

        for ch in s:

            if ch == '(':
                need += 2

                if need % 2 == 1:
                    answer += 1
                    need -= 1

            else:
                need -= 1

                if need < 0:
                    answer += 1
                    need = 1

        return answer + need


#Approach-2 (Stack of Remaining Closing Requirements)
#T.C : O(n)
#S.C : O(n)

class Solution2:

    def minInsertions(self, s: str) -> int:
        stack = []
        answer = 0

        for ch in s:

            if ch == '(':

                if stack and stack[-1] == 1:
                    answer += 1
                    stack.pop()

                stack.append(2)

            else:

                if not stack:
                    answer += 1
                    stack.append(1)
                else:
                    remaining = stack.pop() - 1

                    if remaining > 0:
                        stack.append(remaining)

        answer += sum(stack)

        return answer