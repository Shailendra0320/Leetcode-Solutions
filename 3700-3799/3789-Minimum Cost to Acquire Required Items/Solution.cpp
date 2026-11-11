//Approach-1 (Brute Force: Try Every Number of Type-3 Items)
//T.C: O(max(need1, need2))
//S.C: O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long minimumCost(int cost1, int cost2, int costBoth,
                          int need1, int need2) {

        long long answer = LLONG_MAX;
        long long maxNeed = max(need1, need2);

        for (long long both = 0; both <= maxNeed; both++) {
            long long remaining1 = max(0LL, (long long)need1 - both);
            long long remaining2 = max(0LL, (long long)need2 - both);

            long long totalCost = both * costBoth
                                + remaining1 * cost1
                                + remaining2 * cost2;

            answer = min(answer, totalCost);
        }

        return answer;
    }
};

//Approach-2 (Three Optimal Purchase Plans)
//T.C: O(1)
//S.C: O(1)

class Solution2 {
public:
    long long minimumCost(int cost1, int cost2, int costBoth,
                          int need1, int need2) {

        long long onlySeparate = 1LL * need1 * cost1
                               + 1LL * need2 * cost2;

        long long onlyBoth = 1LL * max(need1, need2) * costBoth;

        long long shared = min(need1, need2);

        long long sharedPlan = shared * costBoth
                             + 1LL * (need1 - shared) * cost1
                             + 1LL * (need2 - shared) * cost2;

        return min({onlySeparate, onlyBoth, sharedPlan});
    }
};

//Approach-3 (Greedy: Effective Cost Per Unit)
//T.C: O(1)
//S.C: O(1)

class Solution3 {
public:
    long long minimumCost(int cost1, int cost2, int costBoth,
                          int need1, int need2) {

        long long shared = min(need1, need2);

        long long sharedCost = min(
            (long long)costBoth,
            (long long)cost1 + cost2
        );

        long long extraCost1 = min(cost1, costBoth);
        long long extraCost2 = min(cost2, costBoth);

        long long answer = shared * sharedCost
                         + (need1 - shared) * extraCost1
                         + (need2 - shared) * extraCost2;

        return answer;
    }
};