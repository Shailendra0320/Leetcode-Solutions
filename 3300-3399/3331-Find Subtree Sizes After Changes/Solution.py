from typing import List

class Solution:

    #Approach-1 (Build Modified Tree + Reverse DFS)
    #T.C: O(n)
    #S.C: O(n)

    def findSubtreeSizes(self, parent: List[int], s: str) -> List[int]:
        n = len(parent)

        children = [[] for _ in range(n)]

        for i in range(1, n):
            children[parent[i]].append(i)

        new_parent = [-1] * n
        last = [-1] * 26
        previous_same = [-1] * n

        stack = [0]

        while stack:
            event = stack.pop()

            if event >= 0:
                u = event
                c = ord(s[u]) - ord('a')

                previous_same[u] = last[c]

                if u != 0:
                    new_parent[u] = last[c] if last[c] != -1 else parent[u]

                last[c] = u
                stack.append(~u)

                for v in children[u]:
                    stack.append(v)

            else:
                u = ~event
                last[ord(s[u]) - ord('a')] = previous_same[u]

        modified = [[] for _ in range(n)]

        for i in range(1, n):
            modified[new_parent[i]].append(i)

        order = []
        stack = [0]

        while stack:
            u = stack.pop()
            order.append(u)

            for v in modified[u]:
                stack.append(v)

        ans = [1] * n

        for u in reversed(order[1:]):
            ans[new_parent[u]] += ans[u]

        return ans


class Solution2:

    #Approach-2 (Single DFS + Direct Subtree Accumulation)
    #T.C: O(n)
    #S.C: O(n)

    def findSubtreeSizes(self, parent: List[int], s: str) -> List[int]:
        n = len(parent)

        children = [[] for _ in range(n)]

        for i in range(1, n):
            children[parent[i]].append(i)

        last = [-1] * 26
        previous_same = [-1] * n
        ans = [1] * n

        stack = [0]

        while stack:
            event = stack.pop()

            if event >= 0:
                u = event
                c = ord(s[u]) - ord('a')

                previous_same[u] = last[c]
                last[c] = u

                stack.append(~u)

                for v in children[u]:
                    stack.append(v)

            else:
                u = ~event

                target = previous_same[u] if previous_same[u] != -1 else parent[u]

                if target != -1:
                    ans[target] += ans[u]

                last[ord(s[u]) - ord('a')] = previous_same[u]

        return ans