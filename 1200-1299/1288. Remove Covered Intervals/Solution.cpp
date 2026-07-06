//Approach-1 (Greedy + Sorting)
//T.C : O(n log n)
//S.C : O(1)

class Solution {
public:

    int removeCoveredIntervals(
        vector<vector<int>>& intervals
    ) {

        sort(
            intervals.begin(),
            intervals.end(),
            [](vector<int>& first,
               vector<int>& second) {

                if (first[0] == second[0]) {

                    return first[1] > second[1];
                }

                return first[0] < second[0];
            }
        );

        int remainingIntervals = 0;

        int maximumEnd = 0;

        for (auto &interval : intervals) {

            if (interval[1] > maximumEnd) {

                remainingIntervals++;

                maximumEnd = interval[1];
            }
        }

        return remainingIntervals;
    }
};


/*
//Approach-2 (Brute Force)
//T.C : O(n²)
//S.C : O(1)

class Solution {
public:

    int removeCoveredIntervals(
        vector<vector<int>>& intervals
    ) {

        int n = intervals.size();

        int coveredIntervals = 0;

        for (int i = 0; i < n; i++) {

            bool covered = false;

            for (int j = 0; j < n; j++) {

                if (i == j) {

                    continue;
                }

                if (
                    intervals[j][0] <= intervals[i][0] &&
                    intervals[i][1] <= intervals[j][1]
                ) {

                    covered = true;

                    break;
                }
            }

            if (covered) {

                coveredIntervals++;
            }
        }

        return n - coveredIntervals;
    }
};
*/