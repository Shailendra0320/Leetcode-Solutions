
// Approach-1 (Brute Force + BigInteger)
// T.C: O(n^2) subarray extensions, excluding BigInteger arithmetic cost
// S.C: O(1) auxiliary space, excluding BigInteger storage

import java.math.BigInteger;

class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            BigInteger product = BigInteger.ONE;
            int gcd = 0;
            int lcm = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];

                product = product.multiply(BigInteger.valueOf(x));

                gcd = gcd(gcd, x);
                lcm = (lcm / gcd(lcm, x)) * x;

                BigInteger required = BigInteger.valueOf((long) gcd * lcm);

                if (product.equals(required)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

// Approach-2 (Optimized Brute Force + Product Bound)
// T.C: O(n^2)
// S.C: O(1)

class Solution2 {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        int globalLcm = 1;
        int maxValue = 0;

        for (int x : nums) {
            globalLcm = lcm(globalLcm, x);
            maxValue = Math.max(maxValue, x);
        }

        long limit = (long) globalLcm * maxValue;

        for (int i = 0; i < n; i++) {
            long product = 1;
            int gcd = 0;
            int lcm = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];

                product *= x;

                // Product cannot decrease because all elements are positive.
                if (product > limit) {
                    break;
                }

                gcd = gcd(gcd, x);
                lcm = lcm(lcm, x);

                if (product == (long) gcd * lcm) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}