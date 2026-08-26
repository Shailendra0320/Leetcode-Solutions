//Approach-1 (Positions of Ones)
//T.C : O(n^2)
//S.C : O(n)

class Solution {
public:

    string shortestBeautifulSubstring(
        string s,
        int k
    ) {

        int n =
            s.length();

        vector<int> ones;

        for (int i = 0; i < n; i++) {

            if (s[i] == '1') {
                ones.push_back(i);
            }
        }

        if (ones.size() < k) {
            return "";
        }

        string answer = "";

        for (
            int i = 0;
            i + k - 1 < ones.size();
            i++
        ) {

            int start =
                ones[i];

            int end =
                ones[i + k - 1];

            string candidate =
                s.substr(
                    start,
                    end - start + 1
                );

            if (
                answer.empty() ||
                candidate.length() < answer.length() ||
                (
                    candidate.length() == answer.length() &&
                    candidate < answer
                )
            ) {

                answer = candidate;
            }
        }

        return answer;
    }
};


//Approach-2 (Sliding Window)
//T.C : O(n^2)
//S.C : O(1)

class Solution {
public:

    string shortestBeautifulSubstring(
        string s,
        int k
    ) {

        int n =
            s.length();

        int left = 0;
        int ones = 0;

        string answer = "";

        for (
            int right = 0;
            right < n;
            right++
        ) {

            if (s[right] == '1') {
                ones++;
            }

            while (
                ones > k
            ) {

                if (s[left] == '1') {
                    ones--;
                }

                left++;
            }

            if (ones == k) {

                while (
                    left < right &&
                    s[left] == '0'
                ) {

                    left++;
                }

                string candidate =
                    s.substr(
                        left,
                        right - left + 1
                    );

                if (
                    answer.empty() ||
                    candidate.length() < answer.length() ||
                    (
                        candidate.length() == answer.length() &&
                        candidate < answer
                    )
                ) {

                    answer = candidate;
                }
            }
        }

        return answer;
    }
};