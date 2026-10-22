//Approach-1 (Linear Case Analysis)
//T.C : O(n)
//S.C : O(1)

class Solution {
    private boolean check(int[] nums, int zero, int step) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int prev = (zero + (i - 1) * step + n) % n;
            int curr = (zero + i * step + n) % n;

            if (nums[prev] > nums[curr]) {
                return false;
            }
        }

        return true;
    }

    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        int[] dranofelik = nums;

        boolean forward = check(dranofelik, zero, 1);
        boolean backward = check(dranofelik, zero, -1);

        int answer = Integer.MAX_VALUE;

        if (forward) {
            answer = Math.min(answer, zero);
            answer = Math.min(answer, n - zero + 2);
        }

        if (backward) {
            answer = Math.min(answer, zero + 2);
            answer = Math.min(answer, n - zero);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}

//Approach-2 (BFS on Compressed States)
//T.C : O(n)
//S.C : O(n)

import java.util.*;

class Solution2 {
    private boolean check(int[] nums, int zero, int step) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int prev = (zero + (i - 1) * step + n) % n;
            int curr = (zero + i * step + n) % n;

            if (nums[prev] > nums[curr]) {
                return false;
            }
        }

        return true;
    }

    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        boolean forward = check(nums, zero, 1);
        boolean backward = check(nums, zero, -1);

        if (!forward && !backward) {
            return -1;
        }

        int[][] dist = new int[2][n];

        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int type = state[0];
            int shift = state[1];
            int nextDist = dist[type][shift] + 1;

            int rotateShift = (shift + 1) % n;

            if (dist[type][rotateShift] == -1) {
                dist[type][rotateShift] = nextDist;
                queue.offer(new int[]{type, rotateShift});
            }

            int reverseType = 1 - type;
            int reverseShift = (n - shift) % n;

            if (dist[reverseType][reverseShift] == -1) {
                dist[reverseType][reverseShift] = nextDist;
                queue.offer(new int[]{reverseType, reverseShift});
            }
        }

        int answer = Integer.MAX_VALUE;

        if (forward) {
            answer = Math.min(answer, dist[0][zero]);
        }

        if (backward) {
            int reverseBaseZero = n - 1 - zero;
            answer = Math.min(answer, dist[1][reverseBaseZero]);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}