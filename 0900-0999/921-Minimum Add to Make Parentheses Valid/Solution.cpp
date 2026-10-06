//Approach-1 (Greedy Balance Counting)
//T.C : O(n)
//S.C : O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {

public:
    int minAddToMakeValid(string s) {
        int open = 0;
        int answer = 0;

        for (char ch : s) {

            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    answer++;
                }
            }
        }

        return answer + open;
    }
};

//Approach-2 (Stack-Based Matching)
//T.C : O(n)
//S.C : O(n)

class Solution2 {

public:
    int minAddToMakeValid(string s) {
        stack<char> st;

        for (char ch : s) {

            if (ch == '(') {
                st.push(ch);
            } else {
                if (!st.empty() && st.top() == '(') {
                    st.pop();
                } else {
                    st.push(ch);
                }
            }
        }

        return st.size();
    }
};