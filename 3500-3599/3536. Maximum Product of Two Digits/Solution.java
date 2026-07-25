//Approach-1 (Greedy + Digit Traversal)
//T.C : O(d)
//S.C : O(1)

class Solution {

    public int maxProduct(
        int n
    ) {

        int max1 = 0;

        int max2 = 0;

        while (
            n > 0
        ) {

            int digit =
                n % 10;

            if (
                digit > max1
            ) {

                max2 =
                    max1;

                max1 =
                    digit;

            } else if (
                digit > max2
            ) {

                max2 =
                    digit;
            }

            n /= 10;
        }

        return
            max1 * max2;
    }
}


/*
//Approach-2 (Sorting Digits)
//T.C : O(d log d)
//S.C : O(d)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    public int maxProduct(
        int n
    ) {

        List<Integer> digits =
            new ArrayList<>();

        while (
            n > 0
        ) {

            digits.add(
                n % 10
            );

            n /= 10;
        }

        Collections.sort(
            digits
        );

        int size =
            digits.size();

        return
            digits.get(size - 1) *
            digits.get(size - 2);
    }
}
*/