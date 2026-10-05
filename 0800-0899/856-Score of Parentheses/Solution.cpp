//Approach-1 (Stack Evaluation)
//T.C : O(n)
//S.C : O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution {

public:
    int scoreOfParentheses(string s) {
        stack<int> st;
        st.push(0);

        for (char ch : s) {

            if (ch == '(') {
                st.push(0);
            } else {
                int inner = st.top();
                st.pop();

                int score = (inner == 0) ? 1 : 2 * inner;

                int parent = st.top();
                st.pop();

                st.push(parent + score);
            }
        }

        return st.top();
    }
};

//Approach-2 (Depth-Based Scoring)
//T.C : O(n)
//S.C : O(1)

class Solution2 {

public:
    int scoreOfParentheses(string s) {
        int depth = 0;
        int answer = 0;

        for (int i = 0; i < (int)s.size(); i++) {

            if (s[i] == '(') {
                depth++;
            } else {
                if (s[i - 1] == '(') {
                    answer += 1 << (depth - 1);
                }

                depth--;
            }
        }

        return answer;
    }
};