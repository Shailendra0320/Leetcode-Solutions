class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n];
        long cur = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                cur = Math.max(0, cur - 1);
            } else if (c == '#') {
                cur = Math.min(cur * 2, (long) 2e15);
            } else if (c != '%') {
                cur++;
            }
            len[i] = cur;
        }

        if (k >= len[n - 1]) return '.';

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == '#') {
                long prevLen = (i > 0) ? len[i - 1] : 0;
                if (k >= prevLen) {
                    k -= prevLen;
                }
            } else if (c == '%') {
                long prevLen = (i > 0) ? len[i - 1] : 0;
                k = prevLen - 1 - k;
            } else if (c != '*') {
                if (len[i] - 1 == k) {
                    return c;
                }
            }
        }

        return '.';
    }
}