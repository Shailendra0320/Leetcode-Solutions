//Approach-1 (Greedy + Sorting)
//T.C : O(n log n)
//S.C : O(1)

import java.util.Arrays;

class Solution {

  public int removeCoveredIntervals(int[][] intervals) {

    Arrays.sort(
        intervals,
        (first, second) -> {

          if (first[0] == second[0]) {

            return second[1] - first[1];
          }

          return first[0] - second[0];
        });

    int remainingIntervals = 0;

    int maximumEnd = 0;

    for (int[] interval : intervals) {

      if (interval[1] > maximumEnd) {

        remainingIntervals++;

        maximumEnd = interval[1];
      }
    }

    return remainingIntervals;
  }
}

/*
 * //Approach-2 (Brute Force)
 * //T.C : O(n²)
 * //S.C : O(1)
 * 
 * class Solution {
 * 
 * public int removeCoveredIntervals(int[][] intervals) {
 * 
 * int n = intervals.length;
 * 
 * int coveredIntervals = 0;
 * 
 * for (int i = 0; i < n; i++) {
 * 
 * boolean covered = false;
 * 
 * for (int j = 0; j < n; j++) {
 * 
 * if (i == j) {
 * 
 * continue;
 * }
 * 
 * if (
 * intervals[j][0] <= intervals[i][0] &&
 * intervals[i][1] <= intervals[j][1]
 * ) {
 * 
 * covered = true;
 * 
 * break;
 * }
 * }
 * 
 * if (covered) {
 * 
 * coveredIntervals++;
 * }
 * }
 * 
 * return n - coveredIntervals;
 * }
 * }
 */