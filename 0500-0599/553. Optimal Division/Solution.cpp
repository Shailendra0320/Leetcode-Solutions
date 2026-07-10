//Approach-1 (Greedy + String Construction)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    string optimalDivision(
        vector<int>& nums
    ) {

        int n = nums.size();

        if (n == 1) {

            return to_string(
                nums[0]
            );
        }

        if (n == 2) {

            return
                to_string(nums[0])
                + "/"
                + to_string(nums[1]);
        }

        string answer =
            to_string(nums[0])
            + "/("
            + to_string(nums[1]);

        for (
            int i = 2;
            i < n;
            i++
        ) {

            answer += "/";

            answer +=
                to_string(nums[i]);
        }

        answer += ")";

        return answer;
    }
};


/*
//Approach-2 (Recursive DFS / Brute Force)
//T.C : Exponential
//S.C : O(n)

class Solution {
public:

    pair<double, string> getMaximum(
        vector<int>& nums,
        int left,
        int right
    ) {

        if (left == right) {

            return {
                nums[left],
                to_string(nums[left])
            };
        }

        pair<double, string> best = {
            -1.0,
            ""
        };

        for (
            int k = left;
            k < right;
            k++
        ) {

            auto leftPart =
                getMaximum(
                    nums,
                    left,
                    k
                );

            auto rightPart =
                getMinimum(
                    nums,
                    k + 1,
                    right
                );

            double value =
                leftPart.first /
                rightPart.first;

            if (value > best.first) {

                string expression =
                    leftPart.second
                    + "/";

                if (k + 1 < right) {

                    expression +=
                        "("
                        + rightPart.second
                        + ")";
                } else {

                    expression +=
                        rightPart.second;
                }

                best = {
                    value,
                    expression
                };
            }
        }

        return best;
    }

    pair<double, string> getMinimum(
        vector<int>& nums,
        int left,
        int right
    ) {

        if (left == right) {

            return {
                nums[left],
                to_string(nums[left])
            };
        }

        pair<double, string> best = {
            1e18,
            ""
        };

        for (
            int k = left;
            k < right;
            k++
        ) {

            auto leftPart =
                getMinimum(
                    nums,
                    left,
                    k
                );

            auto rightPart =
                getMaximum(
                    nums,
                    k + 1,
                    right
                );

            double value =
                leftPart.first /
                rightPart.first;

            if (value < best.first) {

                string expression =
                    leftPart.second
                    + "/";

                if (k + 1 < right) {

                    expression +=
                        "("
                        + rightPart.second
                        + ")";
                } else {

                    expression +=
                        rightPart.second;
                }

                best = {
                    value,
                    expression
                };
            }
        }

        return best;
    }

    string optimalDivision(
        vector<int>& nums
    ) {

        return getMaximum(
            nums,
            0,
            nums.size() - 1
        ).second;
    }
};
*/