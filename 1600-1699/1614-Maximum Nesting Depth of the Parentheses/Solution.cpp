#include <bits/stdc++.h>
using namespace std;

//Approach-1 (Depth Counter / Balance)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:
    int maxDepth(string s) {
        int depth = 0;
        int maxDepth = 0;

        for (char ch : s) {
            if (ch == '(') {
                depth++;
                maxDepth = max(maxDepth, depth);
            }
            else if (ch == ')') {
                depth--;
            }
        }

        return maxDepth;
    }
};


//Approach-2 (Stack-Based Parentheses Tracking)
//T.C : O(n)
//S.C : O(n)

class Solution2 {
public:
    int maxDepth(string s) {
        stack<char> st;
        int maxDepth = 0;

        for (char ch : s) {
            if (ch == '(') {
                st.push(ch);
                maxDepth = max(maxDepth, (int)st.size());
            }
            else if (ch == ')') {
                st.pop();
            }
        }

        return maxDepth;
    }
};