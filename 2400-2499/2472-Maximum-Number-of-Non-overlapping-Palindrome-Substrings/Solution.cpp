//Approach-1 (Palindrome DP + Prefix DP)
//T.C : O(n^2)
//S.C : O(n^2)

class Solution {
public:
    int maxPalindromes(string s, int k) {

        int n = s.size();

        vector<vector<bool>> pal(
            n,
            vector<bool>(n, false)
        );

        // Build palindrome table
        for (int len = 1; len <= n; len++) {
            for (int left = 0; left + len <= n; left++) {

                int right = left + len - 1;

                if (s[left] == s[right] &&
                    (len <= 2 || pal[left + 1][right - 1])) {

                    pal[left][right] = true;
                }
            }
        }

        // dp[i] = maximum number of valid palindromes
        // using the first i characters
        vector<int> dp(n + 1, 0);

        for (int right = 0; right < n; right++) {

            // Skip current character
            dp[right + 1] = max(
                dp[right + 1],
                dp[right]
            );

            // Select a palindrome ending at right
            for (int left = 0; left <= right; left++) {

                int length = right - left + 1;

                if (length >= k && pal[left][right]) {
                    dp[right + 1] = max(
                        dp[right + 1],
                        dp[left] + 1
                    );
                }
            }
        }

        return dp[n];
    }
};


//Approach-2 (Palindrome DP + Earliest-Finish Greedy)
//T.C : O(n^2)
//S.C : O(n^2)

class Solution2 {
public:
    int maxPalindromes(string s, int k) {

        int n = s.size();

        vector<vector<bool>> pal(
            n,
            vector<bool>(n, false)
        );

        // Build palindrome table
        for (int len = 1; len <= n; len++) {
            for (int left = 0; left + len <= n; left++) {

                int right = left + len - 1;

                if (s[left] == s[right] &&
                    (len <= 2 || pal[left + 1][right - 1])) {

                    pal[left][right] = true;
                }
            }
        }

        int answer = 0;
        int previousEnd = -1;

        // Process intervals by increasing end position
        for (int right = 0; right < n; right++) {

            for (int left = previousEnd + 1; left <= right; left++) {

                int length = right - left + 1;

                if (length >= k && pal[left][right]) {
                    answer++;
                    previousEnd = right;
                    break;
                }
            }
        }

        return answer;
    }
};