/*
Approach-1 (Depth Tracking)
T.C : O(n)
S.C : O(n)
*/

class Solution {
public:
    string removeOuterParentheses(string s) {
        string answer;
        int depth = 0;

        for (char ch : s) {
            if (ch == '(') {
                if (depth > 0) {
                    answer += ch;
                }
                depth++;
            } else {
                depth--;
                if (depth > 0) {
                    answer += ch;
                }
            }
        }

        return answer;
    }
};


/*
Approach-2 (Primitive Boundary Tracking)
T.C : O(n)
S.C : O(n)
*/

class Solution2 {
public:
    string removeOuterParentheses(string s) {
        string answer;
        int balance = 0;
        int start = 0;

        for (int i = 0; i < s.size(); i++) {
            if (s[i] == '(') {
                balance++;
            } else {
                balance--;
            }

            if (balance == 0) {
                answer += s.substr(start + 1, i - start - 1);
                start = i + 1;
            }
        }

        return answer;
    }
};