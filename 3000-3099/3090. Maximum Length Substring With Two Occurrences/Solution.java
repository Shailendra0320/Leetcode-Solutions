// Approach 1 — Brute Force

class Solution {
    public int maximumLengthSubstring(String s) {
        int maxLen = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];

            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;

                boolean valid = true;

                for (int f : freq) {
                    if (f > 2) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    maxLen = Math.max(maxLen, j - i + 1);
                } else {
                    break;
                }
            }
        }

        return maxLen;
    }

    // Time Complexity: O(n²)
    // Space Complexity: O(1)
}


// Approach 2 — Sliding Window (Optimal)

class Solution {
    public int maximumLengthSubstring(String s) {
        int[] freq = new int[26];
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'a']++;

            while (freq[s.charAt(right) - 'a'] > 2) {
                freq[s.charAt(left) - 'a']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}