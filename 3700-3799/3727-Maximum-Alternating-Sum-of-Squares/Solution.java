
import java.util.Arrays;

class Solution {

    // Approach 1: Use a separate array
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        long ans = 0;
        int mid = n / 2;

        for (int i = 0; i < mid; i++) {
            ans -= squares[i];
        }

        for (int i = mid; i < n; i++) {
            ans += squares[i];
        }

        return ans;
    }

    // Approach 2: Square and sort the input array
    // Time Complexity: O(n log n)
    // Auxiliary Space Complexity: O(log n) for sorting stack
    // Note: This approach modifies the input array.

    public long maxAlternatingSumApproach2(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        long ans = 0;
        int mid = n / 2;

        for (int i = 0; i < mid; i++) {
            ans -= squares[i];
        }

        for (int i = mid; i < n; i++) {
            ans += squares[i];
        }

        return ans;
    }
}
