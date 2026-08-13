class Solution {

    int[] prefLen, sufLen, maxLen, rangeLen;
    char[] leftChar, rightChar;
    char[] str;

    void build(int node, int start, int end) {
        if (start == end) {
            prefLen[node] = 1;
            sufLen[node] = 1;
            maxLen[node] = 1;
            rangeLen[node] = 1;

            leftChar[node] = str[start];
            rightChar[node] = str[start];

            return;
        }

        int mid = (start + end) / 2;

        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);

        merge(node);
    }

    void merge(int node) {
        int left = 2 * node;
        int right = 2 * node + 1;

        leftChar[node] = leftChar[left];
        rightChar[node] = rightChar[right];

        rangeLen[node] =
                rangeLen[left] + rangeLen[right];

        prefLen[node] = prefLen[left];

        if (rightChar[left] == leftChar[right]
                && prefLen[left] == rangeLen[left]) {

            prefLen[node] =
                    rangeLen[left] + prefLen[right];
        }

        sufLen[node] = sufLen[right];

        if (rightChar[left] == leftChar[right]
                && sufLen[right] == rangeLen[right]) {

            sufLen[node] =
                    rangeLen[right] + sufLen[left];
        }

        maxLen[node] =
                Math.max(maxLen[left], maxLen[right]);

        if (rightChar[left] == leftChar[right]) {
            maxLen[node] =
                    Math.max(
                            maxLen[node],
                            sufLen[left] + prefLen[right]
                    );
        }
    }

    void update(
            int node,
            int start,
            int end,
            int idx,
            char ch) {

        if (start == end) {
            str[idx] = ch;

            leftChar[node] = ch;
            rightChar[node] = ch;

            prefLen[node] = 1;
            sufLen[node] = 1;
            maxLen[node] = 1;

            return;
        }

        int mid = (start + end) / 2;

        if (idx <= mid) {
            update(
                    2 * node,
                    start,
                    mid,
                    idx,
                    ch
            );
        } else {
            update(
                    2 * node + 1,
                    mid + 1,
                    end,
                    idx,
                    ch
            );
        }

        merge(node);
    }

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int n = s.length();
        int q = queryIndices.length;

        str = s.toCharArray();

        prefLen = new int[4 * n];
        sufLen = new int[4 * n];
        maxLen = new int[4 * n];
        rangeLen = new int[4 * n];

        leftChar = new char[4 * n];
        rightChar = new char[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            update(
                    1,
                    0,
                    n - 1,
                    queryIndices[i],
                    queryCharacters.charAt(i)
            );

            ans[i] = maxLen[1];
        }

        return ans;
    }
}