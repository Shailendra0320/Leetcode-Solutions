
//Approach-1 (Stack of Indices)
//T.C : O(n)
//S.C : O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution {

public:
    int longestValidParentheses(string s) {
        stack<int> st;
        st.push(-1);

        int maxLen = 0;

        for (int i = 0; i < (int)s.size(); i++) {

            if (s[i] == '(') {
                st.push(i);
            } else {
                st.pop();

                if (st.empty()) {
                    st.push(i);
                } else {
                    maxLen = max(maxLen, i - st.top());
                }
            }
        }

        return maxLen;
    }
};

//Approach-2 (Two-Pass Counter / Constant Space)
//T.C : O(n)
//S.C : O(1)

class Solution2 {

public:
    int longestValidParentheses(string s) {
        int maxLen = 0;
        int left = 0;
        int right = 0;

        for (char ch : s) {

            if (ch == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLen = max(maxLen, 2 * right);
            } else if (right > left) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;

        for (int i = (int)s.size() - 1; i >= 0; i--) {

            if (s[i] == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLen = max(maxLen, 2 * left);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return maxLen;
    }
};