//Approach-1 (Check Both Parities)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public boolean uniformArray(int[] nums1) {

        boolean isEvenPresent = false;
        boolean isOddPresent = false;

        for (int val : nums1) {

            if (val % 2 == 0) {
                isEvenPresent = true;
            } else {
                isOddPresent = true;
            }
        }

        if (!isEvenPresent || !isOddPresent) {
            return true;
        }

        return isOddPresent;
    }
}


//Approach-2 (Direct Mathematical Observation)
//T.C : O(1)
//S.C : O(1)

class Solution {

    public boolean uniformArray(int[] nums1) {

        return true;
    }
}