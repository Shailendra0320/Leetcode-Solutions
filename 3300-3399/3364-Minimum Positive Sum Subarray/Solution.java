//Approach-1 (Brute Force: Running Sum from Every Starting Index)
//T.C: O(n * r) - O(n^2) in the worst case
//S.C: O(1)

import java.util.*;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int answer = Integer.MAX_VALUE;

        for (int start = 0; start < n; start++) {
            int sum = 0;
            int limit = Math.min(n, start + r);

            for (int end = start; end < limit; end++) {
                sum += nums.get(end);
                int length = end - start + 1;

                if (length >= l && sum > 0) {
                    answer = Math.min(answer, sum);
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}


//Approach-2 (Fixed-Size Sliding Window)
//T.C: O(n * (r - l + 1)) - O(n^2) in the worst case
//S.C: O(1)

class Solution2 {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int answer = Integer.MAX_VALUE;

        for (int size = l; size <= r; size++) {
            int sum = 0;

            for (int i = 0; i < size; i++) {
                sum += nums.get(i);
            }

            if (sum > 0) {
                answer = Math.min(answer, sum);
            }

            for (int right = size; right < n; right++) {
                sum += nums.get(right) - nums.get(right - size);

                if (sum > 0) {
                    answer = Math.min(answer, sum);
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}