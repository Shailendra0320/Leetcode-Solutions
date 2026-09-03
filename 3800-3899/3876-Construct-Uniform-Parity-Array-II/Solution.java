//Approach-1 (Minimum Element + Parity Check)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public boolean uniformArray(int[] nums1) {

        int min = Integer.MAX_VALUE;

        for (int x : nums1) {

            min = Math.min(min, x);
        }

        if (min % 2 == 1) {
            return true;
        }

        for (int x : nums1) {

            if (x % 2 == 1) {
                return false;
            }
        }

        return true;
    }
}


//Approach-2 (Single Pass: Minimum + Odd Flag)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public boolean uniformArray(int[] nums1) {

        int minValue = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for (int x : nums1) {

            minValue = Math.min(minValue, x);

            if (x % 2 == 1) {
                hasOdd = true;
            }
        }

        if (minValue % 2 == 1) {
            return true;
        }

        return !hasOdd;
    }
}