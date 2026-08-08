//Approach-1 (Greedy + Suffix Matching)
//T.C : O(n + m)
//S.C : O(m)

class Solution {

    public int[] validSequence(
        String word1,
        String word2
    ) {

        int n =
            word1.length();

        int m =
            word2.length();

        int[] last =
            new int[m + 1];

        last[m] =
            n;

        int p =
            n - 1;

        for (
            int j = m - 1;
            j >= 0;
            j--
        ) {

            while (
                p >= 0 &&
                word1.charAt(p) !=
                word2.charAt(j)
            ) {

                p--;
            }

            last[j] =
                p;

            if (
                p >= 0
            ) {

                p--;
            }
        }

        int[] res =
            new int[m];

        boolean changed =
            false;

        int i = 0;

        for (
            int j = 0;
            j < m;
            j++
        ) {

            boolean found =
                false;

            while (
                i < n
            ) {

                boolean match =
                    word1.charAt(i) ==
                    word2.charAt(j);

                if (
                    match
                ) {

                    if (
                        changed
                    ) {

                        if (
                            last[j + 1] >=
                            i + 1
                        ) {

                            res[j] =
                                i;

                            i++;

                            found =
                                true;

                            break;
                        }

                    } else {

                        res[j] =
                            i;

                        i++;

                        found =
                            true;

                        break;
                    }

                } else {

                    if (
                        !changed &&
                        last[j + 1] >=
                        i + 1
                    ) {

                        res[j] =
                            i;

                        changed =
                            true;

                        i++;

                        found =
                            true;

                        break;
                    }
                }

                i++;
            }

            if (
                !found
            ) {

                return new int[0];
            }
        }

        return res;
    }
}