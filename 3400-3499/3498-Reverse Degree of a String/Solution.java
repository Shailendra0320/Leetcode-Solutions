//Approach-1 (Direct Character Arithmetic)
//T.C : O(n)
//S.C : O(1)

class Solution {
    public int reverseDegree(String s) {

        int answer = 0;

        for (int i = 0; i < s.length(); i++) {

            int reverseValue = 26 - (s.charAt(i) - 'a');

            answer += reverseValue * (i + 1);
        }

        return answer;
    }
}


//Approach-2 (Reverse Alphabet Value)
//T.C : O(n)
//S.C : O(1)

class Solution2 {
    public int reverseDegree(String s) {

        int answer = 0;

        for (int i = 0; i < s.length(); i++) {

            int reverseValue = 'z' - s.charAt(i) + 1;

            answer += reverseValue * (i + 1);
        }

        return answer;
    }
}