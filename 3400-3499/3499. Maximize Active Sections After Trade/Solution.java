//Approach-1 (Greedy + Run Length Encoding)
//T.C : O(n)
//S.C : O(n)

import java.util.ArrayList;
import java.util.List;

class Solution {

    public int maxActiveSectionsAfterTrade(
        String s
    ) {

        int totalOnes = 0;

        for (
            char ch : s.toCharArray()
        ) {

            if (
                ch == '1'
            ) {

                totalOnes++;
            }
        }

        String augmented =
            "1" + s + "1";

        List<int[]> blocks =
            new ArrayList<>();

        int index = 0;

        while (
            index < augmented.length()
        ) {

            char current =
                augmented.charAt(index);

            int next =
                index;

            while (
                next < augmented.length() &&
                augmented.charAt(next) == current
            ) {

                next++;
            }

            blocks.add(
                new int[] {
                    current - '0',
                    next - index
                }
            );

            index = next;
        }

        int bestGain = 0;

        for (
            int i = 1;
            i + 1 < blocks.size();
            i++
        ) {

            int[] previous =
                blocks.get(i - 1);

            int[] current =
                blocks.get(i);

            int[] next =
                blocks.get(i + 1);

            if (
                current[0] == 1 &&
                previous[0] == 0 &&
                next[0] == 0
            ) {

                bestGain =
                    Math.max(
                        bestGain,
                        previous[1] + next[1]
                    );
            }
        }

        return totalOnes + bestGain;
    }
}


/*
//Approach-2 (Simulation using Run Detection)
//T.C : O(n²)
//S.C : O(n)

class Solution {

    public int maxActiveSectionsAfterTrade(
        String s
    ) {

        int answer = 0;

        int n =
            s.length();

        for (
            int i = 0;
            i < n;
            i++
        ) {

            if (
                s.charAt(i) != '1'
            ) {

                continue;
            }

            int j = i;

            while (
                j < n &&
                s.charAt(j) == '1'
            ) {

                j++;
            }

            if (
                i == 0 ||
                j == n ||
                s.charAt(i - 1) != '0' ||
                s.charAt(j) != '0'
            ) {

                i = j - 1;

                continue;
            }

            int left =
                i - 1;

            while (
                left >= 0 &&
                s.charAt(left) == '0'
            ) {

                left--;
            }

            int right =
                j;

            while (
                right < n &&
                s.charAt(right) == '0'
            ) {

                right++;
            }

            int gain =
                (i - left - 1) +
                (right - j);

            int ones = 0;

            for (
                char ch : s.toCharArray()
            ) {

                if (
                    ch == '1'
                ) {

                    ones++;
                }
            }

            answer =
                Math.max(
                    answer,
                    ones + gain
                );

            i = j - 1;
        }

        return answer;
    }
}
*/