//Approach-1 (Direct Character Arithmetic)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:
    int reverseDegree(string s) {

        int answer = 0;

        for (int i = 0; i < s.size(); i++) {

            int reverseValue = 26 - (s[i] - 'a');

            answer += reverseValue * (i + 1);
        }

        return answer;
    }
};


//Approach-2 (Reverse Alphabet Value)
//T.C : O(n)
//S.C : O(1)

class Solution2 {
public:
    int reverseDegree(string s) {

        int answer = 0;

        for (int i = 0; i < s.size(); i++) {

            int reverseValue = 'z' - s[i] + 1;

            answer += reverseValue * (i + 1);
        }

        return answer;
    }
};