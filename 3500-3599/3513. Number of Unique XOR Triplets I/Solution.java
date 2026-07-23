//Approach-1 (Bit Manipulation + Mathematical Observation)
//T.C : O(1)
//S.C : O(1)

class Solution {

    public int uniqueXorTriplets(
        int[] nums
    ) {

        int n =
            nums.length;

        if (
            n < 3
        ) {

            return n;
        }

        int bits =
            32 -
            Integer.numberOfLeadingZeros(
                n
            );

        return
            1 << bits;
    }
}


/*
//Approach-2 (Brute Force)
//T.C : O(n³)
//S.C : O(n³)

import java.util.HashSet;
import java.util.Set;

class Solution {

    public int uniqueXorTriplets(
        int[] nums
    ) {

        Set<Integer> values =
            new HashSet<>();

        int n =
            nums.length;

        for (
            int i = 0;
            i < n;
            i++
        ) {

            for (
                int j = i;
                j < n;
                j++
            ) {

                for (
                    int k = j;
                    k < n;
                    k++
                ) {

                    values.add(
                        nums[i] ^
                        nums[j] ^
                        nums[k]
                    );
                }
            }
        }

        return values.size();
    }
}
*/