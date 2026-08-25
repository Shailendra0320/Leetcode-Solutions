//Approach-1 (Boolean Array)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public int missingMultiple(
        int[] nums,
        int k
    ) {

        boolean[] present =
            new boolean[101];

        for (int num : nums) {
            present[num] = true;
        }

        for (int i = 1; ; i++) {

            int multiple =
                i * k;

            if (
                multiple >= present.length ||
                !present[multiple]
            ) {
                return multiple;
            }
        }
    }
}

// Approach-2 (HashSet)
// T.C : O(n)
// S.C : O(n)

import java.util.HashSet;

class Solution {

  public int missingMultiple(
      int[] nums,
      int k) {

    HashSet<Integer> set = new HashSet<>();

    for (int num : nums) {
      set.add(num);
    }

    for (int i = 1;; i++) {

      int multiple = i * k;

      if (!set.contains(multiple)) {
        return multiple;
      }
    }
  }
}