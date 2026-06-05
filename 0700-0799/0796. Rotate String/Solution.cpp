//Approach-1 (String Concatenation)
//T.C : O(n²) in worst case
//S.C : O(n)

class Solution {
public:

    bool rotateString(string source, string target) {

        if (source.length() != target.length()) {
            return false;
        }

        string doubledString = source + source;

        return doubledString.find(target) != string::npos;
    }
};