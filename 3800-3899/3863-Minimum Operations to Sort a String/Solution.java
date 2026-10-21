//Approach-1 (Linear Scan and Case Analysis)
//T.C : O(n)
//S.C : O(1)

class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;

        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);

        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);

            minChar = (char) Math.min(minChar, c);
            maxChar = (char) Math.max(maxChar, c);

            if (c < s.charAt(i - 1)) {
                sorted = false;
            }
        }

        if (sorted) {
            return 0;
        }

        String sorunavile = s;

        if (n == 2) {
            return -1;
        }

        if (sorunavile.charAt(0) == minChar ||
            sorunavile.charAt(n - 1) == maxChar) {
            return 1;
        }

        for (int i = 1; i < n - 1; i++) {
            if (sorunavile.charAt(i) == minChar ||
                sorunavile.charAt(i) == maxChar) {
                return 2;
            }
        }

        return 3;
    }
}

//Approach-2 (Sort Target and Check Boundaries)
//T.C : O(n log n)
//S.C : O(n)

import java.util.Arrays;

class Solution2 {
    public int minOperations(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String target = new String(chars);

        if (s.equals(target)) {
            return 0;
        }

        String sorunavile = s;
        int n = sorunavile.length();

        if (n == 2) {
            return -1;
        }

        char minChar = target.charAt(0);
        char maxChar = target.charAt(n - 1);

        if (sorunavile.charAt(0) == minChar ||
            sorunavile.charAt(n - 1