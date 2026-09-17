import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Approach-1 (Sliding Window + DP)
//T.C : O(n)
//S.C : O(n)

class Solution {
  public int minSumOfLengths(int[] arr, int target) {

    int n = arr.length;
    int INF = n + 1;

    // best[i] = minimum length of a target-sum
    // subarray completely inside the first i elements.
    int[] best = new int[n + 1];
    Arrays.fill(best, INF);

    int left = 0;
    int sum = 0;
    int answer = INF;

    for (int right = 0; right < n; right++) {

      sum += arr[right];

      while (sum > target && left <= right) {
        sum -= arr[left];
        left++;
      }

      if (sum == target) {

        int length = right - left + 1;

        // Combine current subarray with the best
        // target-sum subarray completely before it.
        if (best[left] != INF) {
          answer = Math.min(
              answer,
              best[left] + length);
        }

        // Current subarray becomes a candidate
        // for future subarrays.
        best[right + 1] = Math.min(
            best[right],
            length);

      } else {
        best[right + 1] = best[right];
      }
    }

    return answer == INF ? -1 : answer;
  }
}

// Approach-2 (Prefix Sum + HashMap + DP)
// T.C : O(n)
// S.C : O(n)

class Solution2 {
  public int minSumOfLengths(int[] arr, int target) {

    int n = arr.length;
    int INF = n + 1;

    // best[i] = minimum length of a target-sum
    // subarray completely inside the first i elements.
    int[] best = new int[n + 1];
    Arrays.fill(best, INF);

    Map<Integer, Integer> first = new HashMap<>();

    // Prefix sum 0 occurs before the array starts.
    first.put(0, 0);

    int prefix = 0;
    int answer = INF;

    for (int i = 1; i <= n; i++) {

      prefix += arr[i - 1];

      int required = prefix - target;

      if (first.containsKey(required)) {

        int left = first.get(required);
        int length = i - left;

        // best[left] is completely before [left, i-1].
        if (best[left] != INF) {
          answer = Math.min(
              answer,
              best[left] + length);
        }

        best[i] = Math.min(
            best[i - 1],
            length);

      } else {
        best[i] = best[i - 1];
      }

      first.put(prefix, i);
    }

    return answer == INF ? -1 : answer;
  }
}