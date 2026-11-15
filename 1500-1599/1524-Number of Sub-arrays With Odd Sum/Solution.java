//Approach-1 (Prefix Sum Parity + Frequency Counting)
//T.C: O(n)
//S.C: O(1)

class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;

        int even = 1;
        int odd = 0;
        int prefixParity = 0;

        long answer = 0;

        for (int num : arr) {
            prefixParity ^= (num & 1);

            if (prefixParity == 0) {
                answer = (answer + odd) % MOD;
                even++;
            } else {
                answer = (answer + even) % MOD;
                odd++;
            }
        }

        return (int) answer;
    }
}


//Approach-2 (Dynamic Programming: Odd and Even Subarrays)
//T.C: O(n)
//S.C: O(1)

class Solution2 {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;

        long odd = 0;
        long even = 0;
        long answer = 0;

        for (int num : arr) {
            if ((num & 1) == 0) {
                even = (even + 1) % MOD;
            } else {
                long temp = odd;

                odd = (even + 1) % MOD;
                even = temp;
            }

            answer = (answer + odd) % MOD;
        }

        return (int) answer;
    }
}