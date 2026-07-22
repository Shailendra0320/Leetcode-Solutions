//Approach-1 (Dynamic Programming + Bit Manipulation)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    vector<int> countBits(
        int n
    ) {

        vector<int> answer(
            n + 1,
            0
        );

        for (
            int i = 1;
            i <= n;
            i++
        ) {

            answer[i] =
                answer[i >> 1] +
                (i & 1);
        }

        return answer;
    }
};


/*
//Approach-2 (Brian Kernighan's Algorithm)
//T.C : O(n log n)
//S.C : O(n)

class Solution {
public:

    vector<int> countBits(
        int n
    ) {

        vector<int> answer(
            n + 1
        );

        for (
            int i = 0;
            i <= n;
            i++
        ) {

            int number =
                i;

            while (
                number != 0
            ) {

                answer[i]++;

                number =
                    number &
                    (number - 1);
            }
        }

        return answer;
    }
};
*/