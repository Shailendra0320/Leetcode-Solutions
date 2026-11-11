//Approach-1 (Brute Force: Try Every Number of Type-3 Items)
//T.C: O(max(need1, need2))
//S.C: O(1)

class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {
        long answer = Long.MAX_VALUE;
        long maxNeed = Math.max(need1, need2);

        for (long both = 0; both <= maxNeed; both++) {
            long remaining1 = Math.max(0L, need1 - both);
            long remaining2 = Math.max(0L, need2 - both);

            long totalCost = both * costBoth
                    + remaining1 * cost1
                    + remaining2 * cost2;

            answer = Math.min(answer, totalCost);
        }

        return answer;
    }
}

//Approach-2 (Three Optimal Purchase Plans)
//T.C: O(1)
//S.C: O(1)

class Solution2 {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {

        long onlySeparate = (long) need1 * cost1
                          + (long) need2 * cost2;

        long onlyBoth = (long) Math.max(need1, need2) * costBoth;

        int shared = Math.min(need1, need2);

        long sharedPlan = (long) shared * costBoth
                        + (long) (need1 - shared) * cost1
                        + (long) (need2 - shared) * cost2;

        return Math.min(onlySeparate,
               Math.min(onlyBoth, sharedPlan));
    }
}

//Approach-3 (Greedy: Effective Cost Per Unit)
//T.C: O(1)
//S.C: O(1)

class Solution3 {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {

        long shared = Math.min(need1, need2);

        long sharedCost = Math.min((long) costBoth,
                                   (long) cost1 + cost2);

        long extraCost1 = Math.min(cost1, costBoth);
        long extraCost2 = Math.min(cost2, costBoth);

        long answer = shared * sharedCost
                    + (need1 - shared) * extraCost1
                    + (need2 - shared) * extraCost2;

        return answer;
    }
}