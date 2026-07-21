//Approach-1 (Greedy + Run Length Encoding)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    int maxActiveSectionsAfterTrade(
        string s
    ) {

        int totalOnes = 0;

        for (
            char ch : s
        ) {

            if (
                ch == '1'
            ) {

                totalOnes++;
            }
        }

        string augmented =
            "1" + s + "1";

        vector<pair<int, int>> blocks;

        int index = 0;

        while (
            index < augmented.size()
        ) {

            char current =
                augmented[index];

            int next =
                index;

            while (
                next < augmented.size() &&
                augmented[next] == current
            ) {

                next++;
            }

            blocks.push_back(
                {
                    current - '0',
                    next - index
                }
            );

            index = next;
        }

        int bestGain = 0;

        for (
            int i = 1;
            i + 1 < blocks.size();
            i++
        ) {

            auto previous =
                blocks[i - 1];

            auto current =
                blocks[i];

            auto next =
                blocks[i + 1];

            if (
                current.first == 1 &&
                previous.first == 0 &&
                next.first == 0
            ) {

                bestGain =
                    max(
                        bestGain,
                        previous.second + next.second
                    );
            }
        }

        return totalOnes + bestGain;
    }
};


/*
//Approach-2 (Simulation using Run Detection)
//T.C : O(n²)
//S.C : O(n)

class Solution {
public:

    int maxActiveSectionsAfterTrade(
        string s
    ) {

        int answer = 0;

        int n = s.size();

        for (
            int i = 0;
            i < n;
            i++
        ) {

            if (
                s[i] != '1'
            ) {

                continue;
            }

            int j = i;

            while (
                j < n &&
                s[j] == '1'
            ) {

                j++;
            }

            if (
                i == 0 ||
                j == n ||
                s[i - 1] != '0' ||
                s[j] != '0'
            ) {

                i = j - 1;

                continue;
            }

            int left = i - 1;

            while (
                left >= 0 &&
                s[left] == '0'
            ) {

                left--;
            }

            int right = j;

            while (
                right < n &&
                s[right] == '0'
            ) {

                right++;
            }

            int gain =
                (i - left - 1) +
                (right - j);

            int ones = 0;

            for (
                char ch : s
            ) {

                if (
                    ch == '1'
                ) {

                    ones++;
                }
            }

            answer =
                max(
                    answer,
                    ones + gain
                );

            i = j - 1;
        }

        return answer;
    }
};
*/