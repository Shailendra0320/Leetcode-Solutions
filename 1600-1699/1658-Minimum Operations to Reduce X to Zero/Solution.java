import java.util.*;

//Approach-1 (Sliding Window)
//T.C : O(n)
//S.C : O(1)

class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;

        if (target < 0) {
            return -1;
        }

        if (target == 0) {
            return n;
        }

        int left = 0;
        int windowSum = 0;
        int maxLength = -1;

        for (int right = 0; right < n; right++) {
            windowSum += nums[right];

            while (windowSum > target) {
                windowSum -= nums[left++];
            }

            if (windowSum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength == -1 ? -1 : n - maxLength;
    }
}


//Approach-2 (Prefix Sum + HashMap)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;

        if (target < 0) {
            return -1;
        }

        if (target == 0) {
            return n;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int prefixSum = 0;
        int maxLength = -1;

        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];

            if (map.containsKey(prefixSum - target)) {
                maxLength = Math.max(
                    maxLength,
                    i - map.get(prefixSum - target)
                );
            }

            map.putIfAbsent(prefixSum, i);
        }

        return maxLength == -1 ? -1 : n - maxLength;
    }
}