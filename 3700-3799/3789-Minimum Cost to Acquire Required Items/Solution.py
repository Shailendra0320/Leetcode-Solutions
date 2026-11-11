#Approach-1 (Brute Force: Try Every Number of Type-3 Items)
#T.C: O(max(need1, need2))
#S.C: O(1)

class Solution:
    def minimumCost(
        self, cost1: int, cost2: int, costBoth: int,
        need1: int, need2: int
    ) -> int:

        answer = float('inf')
        max_need = max(need1, need2)

        for both in range(max_need + 1):
            remaining1 = max(0, need1 - both)
            remaining2 = max(0, need2 - both)

            total_cost = (
                both * costBoth
                + remaining1 * cost1
                + remaining2 * cost2
            )

            answer = min(answer, total_cost)

        return answer


#Approach-2 (Three Optimal Purchase Plans)
#T.C: O(1)
#S.C: O(1)

class Solution2:
    def minimumCost(
        self, cost1: int, cost2: int, costBoth: int,
        need1: int, need2: int
    ) -> int:

        only_separate = need1 * cost1 + need2 * cost2

        only_both = max(need1, need2) * costBoth

        shared = min(need1, need2)

        shared_plan = (
            shared * costBoth
            + (need1 - shared) * cost1
            + (need2 - shared) * cost2
        )

        return min(only_separate, only_both, shared_plan)


#Approach-3 (Greedy: Effective Cost Per Unit)
#T.C: O(1)
#S.C: O(1)

class Solution3:
    def minimumCost(
        self, cost1: int, cost2: int, costBoth: int,
        need1: int, need2: int
    ) -> int:

        shared = min(need1, need2)

        shared_cost = min(costBoth, cost1 + cost2)

        extra_cost1 = min(cost1, costBoth)
        extra_cost2 = min(cost2, costBoth)

        answer = (
            shared * shared_cost
            + (need1 - shared) * extra_cost1
            + (need2 - shared) * extra_cost2
        )

        return answer