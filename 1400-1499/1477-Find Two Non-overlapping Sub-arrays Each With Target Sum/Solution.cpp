#include <bits/stdc++.h>
using namespace std;

// Approach-1 (Sliding Window + DP)
// T.C : O(n)
// S.C : O(n)

class Solution
{
public:
  int minSumOfLengths(vector<int> &arr, int target)
  {

    int n = arr.size();
    int INF = n + 1;

    // best[i] = minimum length of a target-sum
    // subarray completely inside the first i elements.
    vector<int> best(n + 1, INF);

    int left = 0;
    int sum = 0;
    int answer = INF;

    for (int right = 0; right < n; right++)
    {

      sum += arr[right];

      while (sum > target && left <= right)
      {
        sum -= arr[left];
        left++;
      }

      if (sum == target)
      {

        int length = right - left + 1;

        // Combine current subarray with the best
        // target-sum subarray completely before it.
        if (best[left] != INF)
        {
          answer = min(
              answer,
              best[left] + length);
        }

        // Current subarray becomes a candidate
        // for future subarrays.
        best[right + 1] = min(
            best[right],
            length);
      }
      else
      {
        best[right + 1] = best[right];
      }
    }

    return answer == INF ? -1 : answer;
  }
};

// Approach-2 (Prefix Sum + HashMap + DP)
// T.C : O(n)
// S.C : O(n)

class Solution2
{
public:
  int minSumOfLengths(vector<int> &arr, int target)
  {

    int n = arr.size();
    int INF = n + 1;

    // best[i] = minimum length of a target-sum
    // subarray completely inside the first i elements.
    vector<int> best(n + 1, INF);

    unordered_map<int, int> first;

    // Prefix sum 0 occurs before the array starts.
    first[0] = 0;

    int prefix = 0;
    int answer = INF;

    for (int i = 1; i <= n; i++)
    {

      prefix += arr[i - 1];

      int required = prefix - target;

      if (first.count(required))
      {

        int left = first[required];
        int length = i - left;

        // best[left] is completely before [left, i-1].
        if (best[left] != INF)
        {
          answer = min(
              answer,
              best[left] + length);
        }

        best[i] = min(
            best[i - 1],
            length);
      }
      else
      {
        best[i] = best[i - 1];
      }

      first[prefix] = i;
    }

    return answer == INF ? -1 : answer;
  }
};