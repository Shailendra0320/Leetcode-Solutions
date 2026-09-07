//Approach-1 (DP by Ending Character)
//T.C : O(26 * n) = O(n)
//S.C : O(26) = O(1)

class Solution {

    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007;

        long[] dp = new long[26];

        for (char ch : s.toCharArray()) {
            int index = ch - 'a';

            long total = 0;

            for (long count : dp) {
                total = (total + count) % MOD;
            }

            dp[index] = (total + 1) % MOD;
        }

        long answer = 0;

        for (long count : dp) {
            answer = (answer + count) % MOD;
        }

        return (int) answer;
    }
}


//Approach-2 (Total Count + Last Occurrence)
//T.C : O(n)
//S.C : O(1)

class Solution2 {

    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007;

        long total = 1;
        long[] last = new long[26];

        for (char ch : s.toCharArray()) {
            int index = ch - 'a';

            long previousTotal = total;

            total = (2 * total - last[index] + MOD) % MOD;

            last[index] = previousTotal;
        }

        return (int) ((total - 1 + MOD) % MOD);
    }
}