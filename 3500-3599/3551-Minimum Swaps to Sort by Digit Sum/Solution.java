//Approach-1 (Permutation Cycle Decomposition)
//T.C : O(n log n)
//S.C : O(n)

import java.util.*;

class Solution {
    private int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] target = new Integer[n];

        for (int i = 0; i < n; i++) {
            target[i] = nums[i];
        }

        Arrays.sort(target, (a, b) -> {
            int sumA = digitSum(a);
            int sumB = digitSum(b);

            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }

            return Integer.compare(a, b);
        });

        Map<Integer, Integer> targetIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            targetIndex.put(target[i], i);
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int j = i;
            int cycleSize = 0;

            while (!visited[j]) {
                visited[j] = true;
                cycleSize++;
                j = targetIndex.get(nums[j]);
            }

            swaps += cycleSize - 1;
        }

        return swaps;
    }
}

//Approach-2 (Greedy Correct-Position Swapping)
//T.C : O(n log n)
//S.C : O(n)

class Solution2 {
    private int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] target = new Integer[n];

        for (int i = 0; i < n; i++) {
            target[i] = nums[i];
        }

        Arrays.sort(target, (a, b) -> {
            int sumA = digitSum(a);
            int sumB = digitSum(b);

            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }

            return Integer.compare(a, b);
        });

        int[] current = nums.clone();
        Map<Integer, Integer> position = new HashMap<>();

        for (int i = 0; i < n; i++) {
            position.put(current[i], i);
        }

        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (current[i] == target[i]) {
                continue;
            }

            int correctValue = target[i];
            int j = position.get(correctValue);
            int displacedValue = current[i];

            current[i] = correctValue;
            current[j] = displacedValue;

            position.put(correctValue, i);
            position.put(displacedValue, j);

            swaps++;
        }

        return swaps;
    }
}