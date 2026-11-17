//Approach-1 (Recursive Partitioning + Pascal's Triangle)
//T.C : O(n^2)
//S.C : O(n^2)

#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    static constexpr long long MOD = 1'000'000'007LL;
    vector<vector<long long>> combinations;

    long long dfs(const vector<int>& nums) {
        int n = static_cast<int>(nums.size());

        if (n <= 2) {
            return 1;
        }

        int root = nums[0];
        vector<int> left, right;

        for (int i = 1; i < n; i++) {
            if (nums[i] < root) {
                left.push_back(nums[i]);
            } else {
                right.push_back(nums[i]);
            }
        }

        long long ways = combinations[n - 1][left.size()];
        ways = ways * dfs(left) % MOD;
        ways = ways * dfs(right) % MOD;

        return ways;
    }

public:
    int numOfWays(vector<int>& nums) {
        int n = static_cast<int>(nums.size());

        combinations.assign(
            n, vector<long long>(n, 0)
        );

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

        return static_cast<int>(
            (dfs(nums) - 1 + MOD) % MOD
        );
    }
};


//Approach-2 (BST Construction + Subtree DP)
//T.C : O(n^2) worst case
//S.C : O(n)

class Solution2 {
private:
    static constexpr long long MOD = 1'000'000'007LL;

    struct Node {
        int value;
        Node* left;
        Node* right;

        explicit Node(int value)
            : value(value), left(nullptr), right(nullptr) {}
    };

    Node* insert(Node* root, int value) {
        if (root == nullptr) {
            return new Node(value);
        }

        Node* current = root;

        while (true) {
            if (value < current->value) {
                if (current->left == nullptr) {
                    current->left = new Node(value);
                    break;
                }

                current = current->left;
            } else {
                if (current->right == nullptr) {
                    current->right = new Node(value);
                    break;
                }

                current = current->right;
            }
        }

        return root;
    }

    long long modPow(long long base, long long exponent) {
        long long result = 1;

        while (exponent > 0) {
            if (exponent & 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }

    pair<int, long long> countWays(
        Node* node,
        const vector<long long>& factorial,
        const vector<long long>& inverseFactorial
    ) {
        if (node == nullptr) {
            return {0, 1};
        }

        auto left = countWays(
            node->left, factorial, inverseFactorial
        );

        auto right = countWays(
            node->right, factorial, inverseFactorial
        );

        int leftSize = left.first;
        int rightSize = right.first;
        int total = leftSize + rightSize;

        long long choose = factorial[total]
            * inverseFactorial[leftSize] % MOD
            * inverseFactorial[rightSize] % MOD;

        long long ways = choose * left.second % MOD;
        ways = ways * right.second % MOD;

        return {total + 1, ways};
    }

public:
    int numOfWays(vector<int>& nums) {
        int n = static_cast<int>(nums.size());
        Node* root = nullptr;

        for (int num : nums) {
            root = insert(root, num);
        }

        vector<long long> factorial(n + 1, 1);
        vector<long long> inverseFactorial(n + 1, 1);

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

        auto result = countWays(
            root, factorial, inverseFactorial
        );

        return static_cast<int>(
            (result.second - 1 + MOD) % MOD
        );
    }
};