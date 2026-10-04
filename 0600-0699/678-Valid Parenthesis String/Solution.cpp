//Approach-1 (Greedy Balance Range)
//T.C : O(n)
//S.C : O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {

public:
    bool checkValidString(string s) {
        int low = 0;
        int high = 0;

        for (char ch : s) {

            if (ch == '(') {
                low++;
                high++;
            } else if (ch == ')') {
                low--;
                high--;
            } else {
                low--;
                high++;
            }

            low = max(low, 0);

            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
};

//Approach-2 (Two Stacks with Index Matching)
//T.C : O(n)
//S.C : O(n)

class Solution2 {

public:
    bool checkValidString(string s) {
        stack<int> openStack;
        stack<int> starStack;

        for (int i = 0; i < (int)s.size(); i++) {

            if (s[i] == '(') {
                openStack.push(i);
            } else if (s[i] == '*') {
                starStack.push(i);
            } else {

                if (!openStack.empty()) {
                    openStack.pop();
                } else if (!starStack.empty()) {
                    starStack.pop();
                } else {
                    return false;
                }
            }
        }

        while (!openStack.empty() && !starStack.empty()) {

            if (openStack.top() > starStack.top()) {
                return false;
            }

            openStack.pop();
            starStack.pop();
        }

        return openStack.empty();
    }
};