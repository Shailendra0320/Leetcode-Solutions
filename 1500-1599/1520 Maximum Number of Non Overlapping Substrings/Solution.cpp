#include <bits/stdc++.h>
using namespace std;

//Approach-1 (Greedy Interval Expansion + Replacement)
//T.C : O(26 * n) = O(n)
//S.C : O(26)

class Solution {
public:
    vector<string> maxNumOfSubstrings(string s) {

        int n = s.size();

        vector<int> first(26, n);
        vector<int> last(26, -1);

        for (int i = 0; i < n; i++) {
            int c = s[i] - 'a';

            first[c] = min(first[c], i);
            last[c] = i;
        }

        vector<string> answer;

        int previousEnd = -1;

        for (int i = 0; i < n; i++) {

            int c = s[i] - 'a';

            if (first[c] != i) {
                continue;
            }

            int left = i;
            int right = last[c];

            bool valid = true;

            for (int j = left; j <= right; j++) {

                int current = s[j] - 'a';

                if (first[current] < left) {
                    valid = false;
                    break;
                }

                right = max(right, last[current]);
            }

            if (!valid) {
                continue;
            }

            string current = s.substr(left, right - left + 1);

            if (left > previousEnd) {
                answer.push_back(current);
            } else {
                answer.back() = current;
            }

            previousEnd = right;
        }

        return answer;
    }
};


//Approach-2 (Generate Valid Intervals + Earliest Finish Greedy)
//T.C : O(26 * n + 26 log 26) = O(n)
//S.C : O(26)

class Solution2 {
public:
    vector<string> maxNumOfSubstrings(string s) {

        int n = s.size();

        vector<int> first(26, n);
        vector<int> last(26, -1);

        for (int i = 0; i < n; i++) {
            int c = s[i] - 'a';

            first[c] = min(first[c], i);
            last[c] = i;
        }

        vector<pair<int, int>> intervals;

        for (int c = 0; c < 26; c++) {

            if (last[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];

            bool valid = true;

            for (int i = left; i <= right; i++) {

                int current = s[i] - 'a';

                if (first[current] < left) {
                    valid = false;
                    break;
                }

                right = max(right, last[current]);
            }

            if (valid) {
                intervals.push_back({left, right});
            }
        }

        sort(
            intervals.begin(),
            intervals.end(),
            [](const pair<int, int>& a, const pair<int, int>& b) {
                return a.second < b.second;
            }
        );

        vector<string> answer;

        int previousEnd = -1;

        for (auto& [left, right] : intervals) {

            if (left > previousEnd) {
                answer.push_back(
                    s.substr(left, right - left + 1)
                );

                previousEnd = right;
            }
        }

        return answer;
    }
};