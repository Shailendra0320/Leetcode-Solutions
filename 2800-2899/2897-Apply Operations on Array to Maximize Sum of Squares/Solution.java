//Approach-1 (Greedy Bit Counting with Decrement)
//T.C : O(31 * (n + k))
//S.C : O(31)

class Solution {
    public int maxSum(int[] nums, int k) {
        final int MOD = 1_000_000_007;
        int[] count = new int[31];

        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    count[bit]++;
                }
            }
        }

        long answer = 0;

        for (int i = 0; i < k; i++) {
            long x = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (count[bit] > 0) {
                    x |= (1L << bit);
                    count[bit]--;
                }
            }

            answer = (answer + x * x) % MOD;
        }

        return (int) answer;
    }
}

//Approach-2 (Greedy Bit-Frequency Ranking)
//T.C : O(31 * (n + k))
//S.C : O(31)

class Solution2 {
    public int maxSum(int[] nums, int k) {
        final int MOD = 1_000_000_007;
        int[] count = new int[31];

        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    count[bit]++;
                }
            }
        }

        long answer = 0;

        for (int i = 0; i < k; i++) {
            long x = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (count[bit] > i) {
                    x |= (1L << bit);
                }
            }

            answer = (answer + x * x) % MOD;
        }

        return (int) answer;
    }
}