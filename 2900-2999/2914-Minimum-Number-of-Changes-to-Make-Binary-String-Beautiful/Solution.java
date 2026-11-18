//Approach-1 (Greedy Pair-by-Pair Counting)
//T.C: O(n)
//S.C: O(1)

class Solution {
    public int minChanges(String s) {
        int changes = 0;

        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }

        return changes;
    }
}


//Approach-2 (Count Zeroes and Ones in Each Pair)
//T.C: O(n)
//S.C: O(1)

class Solution2 {
    public int minChanges(String s) {
        int changes = 0;

        for (int i = 0; i < s.length(); i += 2) {
            int countZero = 0;
            int countOne = 0;

            if (s.charAt(i) == '0') {
                countZero++;
            } else {
                countOne++;
            }

            if (s.charAt(i + 1) == '0') {
                countZero++;
            } else {
                countOne++;
            }

            changes += Math.min(countZero, countOne);
        }

        return changes;
    }
}