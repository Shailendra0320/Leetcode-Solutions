//Approach-1 (Brute Force + Digit Simulation)
//T.C : O(k × d)
//S.C : O(1)

class Solution {
public:

    int smallestNumber(
        int n,
        int t
    ) {

        for (
            int num = n;
            num <= n + 100;
            num++
        ) {

            int product = 1;

            int temp =
                num;

            while (
                temp > 0
            ) {

                product *=
                    temp % 10;

                temp /= 10;
            }

            if (
                product % t == 0
            ) {

                return num;
            }
        }

        return -1;
    }
};


/*
//Approach-2 (Brute Force Using String Conversion)
//T.C : O(k × d)
//S.C : O(d)

class Solution {
public:

    int smallestNumber(
        int n,
        int t
    ) {

        for (
            int num = n;
            num <= n + 100;
            num++
        ) {

            int product = 1;

            string str =
                to_string(num);

            for (
                char ch : str
            ) {

                product *=
                    ch - '0';
            }

            if (
                product % t == 0
            ) {

                return num;
            }
        }

        return -1;
    }
};
*/