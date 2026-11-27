
import java.util.*;


// Approach-1 (Brute Force Divisibility Counting + 0/1 Knapsack)
// T.C: O(n^2 + n * budget)
// S.C: O(budget)

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];

            int gain = 0;

            for (int j = 0; j < n; j++) {
                if (items[j][0] % factor == 0) {
                    gain++;
                }
            }

            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(
                    dp[b],
                    dp[b - price] + gain
                );
            }
        }

        int ans = 0;

        for (int spent = 0; spent <= budget; spent++) {
            int extra = (budget - spent) / minPrice;

            ans = Math.max(ans, dp[spent] + extra);
        }

        return ans;
    }
}


// Approach-2 (Divisor Frequency Precomputation + 0/1 Knapsack)
// T.C: O(V log V + n * budget)
// S.C: O(V + budget)

class Solution2 {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        int[] gain = new int[maxFactor + 1];

        for (int factor = 1; factor <= maxFactor; factor++) {
            for (int multiple = factor;
                 multiple <= maxFactor;
                 multiple += factor) {

                gain[factor] += freq[multiple];
            }
        }

        int[] dp = new int[budget + 1];

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            int reward = gain[factor];

            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(
                    dp[b],
                    dp[b - price] + reward
                );
            }
        }

        int ans = 0;

        for (int spent = 0; spent <= budget; spent++) {
            int extra = (budget - spent) / minPrice;

            ans = Math.max(ans, dp[spent] + extra);
        }

        return ans;
    }
}
