//Approach-1 (Recursive Partitioning + Pascal's Triangle)
//T.C : O(n^2)
//S.C : O(n^2)

import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] combinations;

    private long dfs(List<Integer> nums) {
        int n = nums.size();

        if (n <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long ways = combinations[n - 1][left.size()];
        ways = ways * dfs(left) % MOD;
        ways = ways * dfs(right) % MOD;

        return ways;
    }

    public int numOfWays(int[] nums) {
        int n = nums.length;

        combinations = new long[n][n];

        for (int i = 0; i < n; i++) {
            combinations[i][0] = 1;
            combinations[i][i] = 1;

            for (int j = 1; j < i; j++) {
                combinations[i][j] = (
                    combinations[i - 1][j - 1]
                    + combinations[i - 1][j]
                ) % MOD;
            }
        }

        List<Integer> values = new ArrayList<>();

        for (int num : nums) {
            values.add(num);
        }

        return (int) ((dfs(values) - 1 + MOD) % MOD);
    }
}

//Approach-2 (BST Construction + Subtree DP)
//T.C : O(n^2) worst case
//S.C : O(n)

class Solution2 {
    private static final long MOD = 1_000_000_007L;

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        Node current = root;

        while (true) {
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value);
                    break;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    break;
                }

                current = current.right;
            }
        }

        return root;
    }

    private long modPow(long base, long exponent) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }

    private long[] countWays(
        Node node,
        long[] factorial,
        long[] inverseFactorial
    ) {
        if (node == null) {
            return new long[]{0, 1};
        }

        long[] left = countWays(
            node.left, factorial, inverseFactorial
        );

        long[] right = countWays(
            node.right, factorial, inverseFactorial
        );

        int leftSize = (int) left[0];
        int rightSize = (int) right[0];
        int total = leftSize + rightSize;

        long choose = factorial[total]
            * inverseFactorial[leftSize] % MOD
            * inverseFactorial[rightSize] % MOD;

        long ways = choose * left[1] % MOD;
        ways = ways * right[1] % MOD;

        return new long[]{total + 1, ways};
    }

    public int numOfWays(int[] nums) {
        int n = nums.length;
        Node root = null;

        for (int num : nums) {
            root = insert(root, num);
        }

        long[] factorial = new long[n + 1];
        long[] inverseFactorial = new long[n + 1];

        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }

        inverseFactorial[n] = modPow(
            factorial[n], MOD - 2
        );

        for (int i = n; i >= 1; i--) {
            inverseFactorial[i - 1] =
                inverseFactorial[i] * i % MOD;
        }

        long[] result = countWays(
            root, factorial, inverseFactorial
        );

        return (int) ((result[1] - 1 + MOD) % MOD);
    }
}