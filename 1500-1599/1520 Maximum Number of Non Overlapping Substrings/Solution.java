import java.util.*;

//Approach-1 (Greedy Interval Expansion + Replacement)
//T.C : O(26 * n) = O(n)
//S.C : O(26)

class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<String> answer = new ArrayList<>();

        int previousEnd = -1;

        for (int i = 0; i < n; i++) {

            int c = s.charAt(i) - 'a';

            if (first[c] != i) {
                continue;
            }

            int left = i;
            int right = last[c];

            boolean valid = true;

            for (int j = left; j <= right; j++) {

                int current = s.charAt(j) - 'a';

                if (first[current] < left) {
                    valid = false;
                    break;
                }

                right = Math.max(right, last[current]);
            }

            if (!valid) {
                continue;
            }

            String current = s.substring(left, right + 1);

            if (left > previousEnd) {
                answer.add(current);
            } else {
                answer.set(answer.size() - 1, current);
            }

            previousEnd = right;
        }

        return answer;
    }
}


//Approach-2 (Generate Valid Intervals + Earliest Finish Greedy)
//T.C : O(26 * n + 26 log 26) = O(n)
//S.C : O(26)

class Solution2 {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {

            if (last[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];

            boolean valid = true;

            for (int i = left; i <= right; i++) {

                int current = s.charAt(i) - 'a';

                if (first[current] < left) {
                    valid = false;
                    break;
                }

                right = Math.max(right, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> answer = new ArrayList<>();

        int previousEnd = -1;

        for (int[] interval : intervals) {

            int left = interval[0];
            int right = interval[1];

            if (left > previousEnd) {
                answer.add(s.substring(left, right + 1));
                previousEnd = right;
            }
        }

        return answer;
    }
}