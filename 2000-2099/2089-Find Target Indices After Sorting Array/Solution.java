//Approach-1 (Sort and Scan)
//T.C : O(n log n)
//S.C : O(n)

import java.util.*;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                ans.add(i);
            }
        }

        return ans;
    }
}

//Approach-2 (Frequency Counting Without Sorting)
//T.C : O(n)
//S.C : O(1) auxiliary space, excluding output

class Solution2 {
    public List<Integer> targetIndices(int[] nums, int target) {
        int smaller = 0;
        int equal = 0;

        for (int num : nums) {
            if (num < target) {
                smaller++;
            } else if (num == target) {
                equal++;
            }
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = smaller; i < smaller + equal; i++) {
            ans.add(i);
        }

        return ans;
    }
}