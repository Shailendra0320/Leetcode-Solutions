//Approach-1 (Greedy Using Quotient and Remainder)
//T.C : O(num)
//S.C : O(num)

class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }

        int nines = sum / 9;
        int remainder = sum % 9;

        StringBuilder ans = new StringBuilder(num);

        for (int i = 0; i < nines; i++) {
            ans.append('9');
        }

        if (remainder > 0) {
            ans.append((char) ('0' + remainder));
        }

        while (ans.length() < num) {
            ans.append('0');
        }

        return ans.toString();
    }
}

//Approach-2 (Greedy Digit-by-Digit Construction)
//T.C : O(num)
//S.C : O(num)

class Solution2 {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder ans = new StringBuilder(num);
        int remainingSum = sum;

        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, remainingSum);

            ans.append((char) ('0' + digit));
            remainingSum -= digit;
        }

        return ans.toString();
    }
}