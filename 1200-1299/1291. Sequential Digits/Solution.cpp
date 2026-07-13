//Approach-1 (String Enumeration)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:

    vector<int> sequentialDigits(
        int low,
        int high
    ) {

        vector<int> answer;

        string digits =
            "123456789";

        int n =
            digits.size();

        for (
            int length = 1;
            length <= n;
            length++
        ) {

            for (
                int start = 0;
                start + length <= n;
                start++
            ) {

                string current =
                    digits.substr(
                        start,
                        length
                    );

                int number =
                    stoi(current);

                if (
                    number >= low &&
                    number <= high
                ) {

                    answer.push_back(
                        number
                    );
                }
            }
        }

        sort(
            answer.begin(),
            answer.end()
        );

        return answer;
    }
};


/*
//Approach-2 (Breadth-First Search)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:

    vector<int> sequentialDigits(
        int low,
        int high
    ) {

        queue<int> q;

        vector<int> answer;

        for (
            int digit = 1;
            digit <= 9;
            digit++
        ) {

            q.push(digit);
        }

        while (!q.empty()) {

            int current =
                q.front();

            q.pop();

            if (
                current >= low &&
                current <= high
            ) {

                answer.push_back(
                    current
                );
            }

            int lastDigit =
                current % 10;

            if (lastDigit < 9) {

                int next =
                    current * 10
                    + lastDigit + 1;

                if (next <= high) {

                    q.push(next);
                }
            }
        }

        sort(
            answer.begin(),
            answer.end()
        );

        return answer;
    }
};
*/