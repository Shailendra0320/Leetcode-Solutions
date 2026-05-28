//Approach-1 (Using Built-in Functions)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:

    bool detectCapitalUse(string word) {

        string upper = word;

        string lower = word;

        transform(upper.begin(), upper.end(), upper.begin(), ::toupper);

        transform(lower.begin(), lower.end(), lower.begin(), ::tolower);

        if (word == upper) {

            return true;
        }

        if (word == lower) {

            return true;
        }

        if (isupper(word[0])) {

            bool valid = true;

            for (int i = 1; i < word.length(); i++) {

                if (!islower(word[i])) {

                    valid = false;

                    break;
                }
            }

            return valid;
        }

        return false;
    }
};


/*
//Approach-2 (Manual Counting)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:

    bool detectCapitalUse(string word) {

        int upper = 0;

        for (char ch : word) {

            if (isupper(ch)) {

                upper++;
            }
        }

        if (upper == word.length()) {

            return true;
        }

        if (upper == 0) {

            return true;
        }

        if (upper == 1 && isupper(word[0])) {

            return true;
        }

        return false;
    }
};
*/