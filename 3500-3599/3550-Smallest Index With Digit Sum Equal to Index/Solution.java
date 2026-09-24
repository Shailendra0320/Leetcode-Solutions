//Approach-1 (Arithmetic Digit Sum)
//T.C : O(n * D)
//S.C : O(1)

class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            if (sum == i) {
                return i;
            }
        }

        return -1;
    }
}


//Approach-2 (String Digit Sum)
//T.C : O(n * D)
//S.C : O(D)

class Solution2 {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            String s = String.valueOf(nums[i]);
            int sum = 0;

            for (char ch : s.toCharArray()) {
                sum += ch - '0';
            }

            if (sum == i) {
                return i;
            }
        }

        return -1;
    }
}