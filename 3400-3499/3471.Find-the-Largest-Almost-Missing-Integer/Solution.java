import java.util.*;

class Solution {

  // Approach-1 (Frequency + Boundary Observation)
  // T.C : O(n)
  // S.C : O(n)

  public int largestInteger(int[] nums, int k) {

    int n = nums.length;

    Map<Integer, Integer> frequency = new HashMap<>();

    for (int num : nums) {
      frequency.put(
          num,
          frequency.getOrDefault(num, 0) + 1);
    }

    int answer = -1;

    if (k == 1) {

      for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {

        if (entry.getValue() == 1) {
          answer = Math.max(answer, entry.getKey());
        }
      }

      return answer;
    }

    if (k == n) {

      for (int num : nums) {
        answer = Math.max(answer, num);
      }

      return answer;
    }

    if (frequency.get(nums[0]) == 1) {
      answer = Math.max(answer, nums[0]);
    }

    if (frequency.get(nums[n - 1]) == 1) {
      answer = Math.max(answer, nums[n - 1]);
    }

    return answer;
  }

  // Approach-2 (Brute Force)
  // T.C : O(d * (n-k+1) * k), worst case O(n^3)
  // S.C : O(n)

  public int largestIntegerBruteForce(int[] nums, int k) {

    int arrayLength = nums.length;
    int answer = -1;

    Set<Integer> uniqueValues = new HashSet<>();

    for (int value : nums) {
      uniqueValues.add(value);
    }

    for (int candidate : uniqueValues) {

      int subarrayCount = 0;

      for (int startIndex = 0; startIndex <= arrayLength - k; startIndex++) {

        boolean foundInSubarray = false;

        for (int position = startIndex; position < startIndex + k; position++) {

          if (nums[position] == candidate) {
            foundInSubarray = true;
            break;
          }
        }

        if (foundInSubarray) {
          subarrayCount++;
        }
      }

      if (subarrayCount == 1) {
        answer = Math.max(answer, candidate);
      }
    }

    return answer;
  }
}