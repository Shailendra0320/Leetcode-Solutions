class Solution {
public:

    string mapWordWeights(vector<string>& words, vector<int>& weights) {

        string answer;

        for (string& word : words) {

            int sum = 0;

            for (char ch : word) {

                sum = (sum + weights[ch - 'a']) % 26;
            }

            answer.push_back('a' + (25 - sum));
        }

        return answer;
    }
};