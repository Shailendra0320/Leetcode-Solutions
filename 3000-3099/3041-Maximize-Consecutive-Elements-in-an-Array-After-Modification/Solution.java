
import java.util.*;

class Solution {

    //Approach-1 (Sorting + HashMap DP)
    //T.C: O(n log n)
    //S.C: O(n)

    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            dp.put(x + 1, dp.getOrDefault(x, 0) + 1);
            dp.put(x, dp.getOrDefault(x - 1, 0) + 1);

            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }

        return ans;
    }
}


//Approach-2 (Sorting + TreeMap DP)
//T.C: O(n log n)
//S.C: O(n)

class Solution2 {

    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        TreeMap<Integer, Integer> dp = new TreeMap<>();
        int ans = 0;

        for (int x : nums) {
            int extend = dp.getOrDefault(x, 0) + 1;
            int start = dp.getOrDefault(x - 1, 0) + 1;

            dp.put(x + 1, extend);
            dp.put(x, start);

            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }

        return ans;
    }
}
