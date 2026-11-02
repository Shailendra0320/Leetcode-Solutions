//Approach-1 (Sorting + Median)
//T.C : O(n log n)
//S.C : O(log n) for sorting stack

import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int median = nums[n / 2];

        long moves = 0;

        for (int num : nums) {
            moves += Math.abs((long) num - median);
        }

        return (int) moves;
    }
}

//Approach-2 (Randomized Quickselect + Median)
//T.C : O(n) expected, O(n^2) worst case
//S.C : O(1) auxiliary space

import java.util.concurrent.ThreadLocalRandom;

class Solution2 {
    private int quickSelect(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int pivotIndex = ThreadLocalRandom.current()
                    .nextInt(left, right + 1);

            int pivot = nums[pivotIndex];

            int low = left;
            int mid = left;
            int high = right;

            while (mid <= high) {
                if (nums[mid] < pivot) {
                    swap(nums, low, mid);
                    low++;
                    mid++;
                } else if (nums[mid] > pivot) {
                    swap(nums, mid, high);
                    high--;
                } else {
                    mid++;
                }
            }

            if (target < low) {
                right = low - 1;
            } else if (target > high) {
                left = high + 1;
            } else {
                return nums[target];
            }
        }

        return nums[target];
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int minMoves2(int[] nums) {
        int median = quickSelect(nums, nums.length / 2);

        long moves = 0;

        for (int num : nums) {
            moves += Math.abs((long) num - median);
        }

        return (int) moves;
    }
}