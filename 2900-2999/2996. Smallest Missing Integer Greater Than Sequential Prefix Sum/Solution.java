//Approach-1 (HashSet + Sequential Prefix)
//T.C : O(n) average
//S.C : O(n)

import java.util.HashSet;
import java.util.Set;

class Solution {

  public int missingInteger(
      int[] nums) {

    Set<Integer> seen = new HashSet<>();

    for (int val : nums) {

      seen.add(val);
    }

    int prefixSum = nums[0];

    for (int i = 1; i < nums.length; i++) {

      if (nums[i] == nums[i - 1] + 1) {

        prefixSum += nums[i];

      } else {

        break;
      }
    }

    while (seen.contains(
        prefixSum)) {

      prefixSum++;
    }

    return prefixSum;
  }
}