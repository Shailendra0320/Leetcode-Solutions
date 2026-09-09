//Approach-1 (Threshold-Based Mathematical Counting)
//T.C : O(log(n))
//S.C : O(1)

class Solution {
public:

    long long countCommas(long long n) {
        long long answer = 0;

        for (long long x = 1000; x <= n; x *= 1000) {
            answer += n - x + 1;
        }

        return answer;
    }
};


//Approach-2 (Digit-Range Grouping)
//T.C : O(log(n))
//S.C : O(1)

class Solution2 {
public:

    long long countCommas(long long n) {
        long long answer = 0;
        long long start = 1000;
        long long commas = 1;

        while (start <= n) {
            long long end = min(n, start * 1000 - 1);

            long long count = end - start + 1;

            answer += count * commas;

            start *= 1000;
            commas++;
        }

        return answer;
    }
};