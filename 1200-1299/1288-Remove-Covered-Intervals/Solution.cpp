#include <bits/stdc++.h>
using namespace std;

// Approach-1 (Brute Force)
// T.C : O(n^2)
// S.C : O(1)

class Solution
{
public:
  int removeCoveredIntervals(vector<vector<int>> &intervals)
  {
    int n = intervals.size();

    vector<bool> covered(n, false);

    for (int i = 0; i < n; i++)
    {
      for (int j = 0; j < n; j++)
      {
        if (i == j)
          continue;

        // interval i is covered by interval j
        if (intervals[j][0] <= intervals[i][0] &&
            intervals[i][1] <= intervals[j][1])
        {
          covered[i] = true;
          break;
        }
      }
    }

    int answer = 0;

    for (bool isCovered : covered)
    {
      if (!isCovered)
      {
        answer++;
      }
    }

    return answer;
  }
};

// Approach-2 (Sort + Greedy)
// T.C : O(n log n)
// S.C : O(log n) to O(n) depending on sorting implementation

class Solution2
{
public:
  int removeCoveredIntervals(vector<vector<int>> &intervals)
  {

    // Sort by:
    // 1. start -> ascending
    // 2. end   -> descending when starts are equal
    sort(intervals.begin(), intervals.end(), [](const vector<int> &a, const vector<int> &b)
         {
            if (a[0] != b[0])
                return a[0] < b[0];

            return a[1] > b[1]; });

    int answer = 0;
    int maxRight = INT_MIN;

    for (const auto &interval : intervals)
    {

      // This interval is not covered by any previous interval
      if (interval[1] > maxRight)
      {
        answer++;
        maxRight = interval[1];
      }
    }

    return answer;
  }
};