//Approach-1 (Sliding Window + Monotonic Deque: Value-Count Pairs)
//T.C: O(n)
//S.C: O(n)

import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<int[]> dq = new ArrayDeque<>();

        long answer = 0;
        long cost = 0;
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            int num = nums[left];
            int count = 1;

            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                int[] last = dq.pollLast();

                count += last[1];
                cost += (long) (num - last[0]) * last[1];
            }

            dq.offerLast(new int[]{num, count});

            while (cost > k) {
                int[] first = dq.pollFirst();

                cost -= (long) first[0] - nums[right];
                right--;

                if (first[1] > 1) {
                    dq.offerFirst(
                        new int[]{first[0], first[1] - 1}
                    );
                }
            }

            answer += right - left + 1;
        }

        return answer;
    }
}


//Approach-2 (Sliding Window + Monotonic Deque: Indices)
//T.C: O(n)
//S.C: O(n)

class Solution2 {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();

        long answer = 0;
        long cost = 0;
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            int num = nums[left];

            while (!dq.isEmpty() && nums[dq.peekLast()] < num) {
                int l = dq.pollLast();

                int r = dq.isEmpty() ? right + 1 : dq.peekLast();

                cost += (long) (r - l) * (num - nums[l]);
            }

            dq.offerLast(left);

            while (cost > k) {
                cost -= (long) nums[dq.peekFirst()] - nums[right];

                if (dq.peekFirst() == right) {
                    dq.pollFirst();
                }

                right--;
            }

            answer += right - left + 1;
        }

        return answer;
    }
}