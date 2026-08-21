// Approach-1 (Binary Search + On-Demand Inclusion-Exclusion)
// T.C : O(n * 2^n * log(k * minCoin))
// S.C : O(1)

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int numSubsets = 1 << n;

        long minCoin = coins[0];

        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long low = 1;
        long high = minCoin * (long) k;

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (countMultiples(mid, coins, n, numSubsets) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long countMultiples(
        long target,
        int[] coins,
        int n,
        int numSubsets
    ) {
        long totalCount = 0;

        for (int mask = 1; mask < numSubsets; mask++) {
            long currentLCM = 1;
            int selectedCoins = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentLCM = lcm(currentLCM, coins[i]);
                    selectedCoins++;
                }
            }

            long contribution = target / currentLCM;

            if ((selectedCoins & 1) == 1) {
                totalCount += contribution;
            } else {
                totalCount -= contribution;
            }
        }

        return totalCount;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}


// Approach-2 (Binary Search + Precomputed Subset LCM)
// T.C : O(2^n * log(k * minCoin))
// S.C : O(2^n)

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int numSubsets = 1 << n;

        long[] lcms = new long[numSubsets];
        int[] signs = new int[numSubsets];

        lcms[0] = 1;
        signs[0] = -1;

        for (int mask = 1; mask < numSubsets; mask++) {
            int leastSetBit =
                Integer.numberOfTrailingZeros(mask);

            int prevMask =
                mask ^ (1 << leastSetBit);

            if (prevMask == 0) {
                lcms[mask] =
                    coins[leastSetBit];

                signs[mask] = 1;
            } else {
                lcms[mask] =
                    lcm(
                        lcms[prevMask],
                        coins[leastSetBit]
                    );

                signs[mask] =
                    -signs[prevMask];
            }
        }

        long minCoin = coins[0];

        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long low = 1;
        long high = minCoin * (long) k;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (countMultiples(
                    mid,
                    numSubsets,
                    lcms,
                    signs
                ) >= k) {

                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long countMultiples(
        long target,
        int numSubsets,
        long[] lcms,
        int[] signs
    ) {
        long totalCount = 0;

        for (int mask = 1; mask < numSubsets; mask++) {
            totalCount +=
                signs[mask] *
                (target / lcms[mask]);
        }

        return totalCount;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}