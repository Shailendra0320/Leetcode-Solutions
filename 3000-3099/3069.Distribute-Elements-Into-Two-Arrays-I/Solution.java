// Approach-1 (ArrayList Simulation)
// T.C : O(n)
// S.C : O(n)

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] resultArray(int[] nums) {
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        arr1.add(nums[0]);
        arr2.add(nums[1]);

        for (int i = 2; i < nums.length; i++) {
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }

        int[] result = new int[nums.length];
        int idx = 0;

        for (int num : arr1) {
            result[idx++] = num;
        }

        for (int num : arr2) {
            result[idx++] = num;
        }

        return result;
    }
}


// Approach-2 (Array Simulation)
// T.C : O(n)
// S.C : O(n)

class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;

        int[] arr1 = new int[n];
        int[] arr2 = new int[n];

        int size1 = 0;
        int size2 = 0;

        arr1[size1++] = nums[0];
        arr2[size2++] = nums[1];

        for (int i = 2; i < n; i++) {
            if (arr1[size1 - 1] > arr2[size2 - 1]) {
                arr1[size1++] = nums[i];
            } else {
                arr2[size2++] = nums[i];
            }
        }

        int[] result = new int[n];
        int idx = 0;

        for (int i = 0; i < size1; i++) {
            result[idx++] = arr1[i];
        }

        for (int i = 0; i < size2; i++) {
            result[idx++] = arr2[i];
        }

        return result;
    }
}