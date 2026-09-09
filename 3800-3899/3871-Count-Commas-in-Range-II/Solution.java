//Approach-1 (Threshold-Based Mathematical Counting)
//T.C : O(log(n))
//S.C : O(1)

class Solution {

    public long countCommas(long n) {
        long answer = 0;

        for (long x = 1000; x <= n; x *= 1000) {
            answer += n - x + 1;
        }

        return answer;
    }
}


//Approach-2 (Digit-Range Grouping)
//T.C : O(log(n))
//S.C : O(1)

class Solution2 {

    public long countCommas(long n) {
        long answer = 0;
        long start = 1000;
        long commas = 1;

        while (start <= n) {
            long end = Math.min(n, start * 1000 - 1);

            long count = end - start + 1;

            answer += count * commas;

            start *= 1000;
            commas++;
        }

        return answer;
    }
}