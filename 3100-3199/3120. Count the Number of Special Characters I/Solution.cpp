// Approach-1 (Using Frequency Arrays)
// T.C : O(n)
// S.C : O(1)

class Solution
{
public:
  int numberOfSpecialChars(string word)
  {

    vector<bool> lowercase(26, false);

    vector<bool> uppercase(26, false);

    for (char c : word)
    {

      if (c >= 'a' && c <= 'z')
      {

        lowercase[c - 'a'] = true;
      }
      else if (c >= 'A' && c <= 'Z')
      {

        uppercase[c - 'A'] = true;
      }
    }

    int count = 0;

    for (int i = 0; i < 26; i++)
    {

      if (lowercase[i] && uppercase[i])
      {

        count++;
      }
    }

    return count;
  }
};

/*
//Approach-2 (Using HashSet)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    int numberOfSpecialChars(string word) {

        unordered_set<char> lower;

        unordered_set<char> upper;

        for (char ch : word) {

            if (islower(ch)) {

                lower.insert(ch);
            }
            else if (isupper(ch)) {

                upper.insert(tolower(ch));
            }
        }

        int count = 0;

        for (char ch : lower) {

            if (upper.count(ch)) {

                count++;
            }
        }

        return count;
    }
};
*/

/*
//Approach-3 (Brute Force)
//T.C : O(n^2)
//S.C : O(1)

class Solution {
public:

    int numberOfSpecialChars(string word) {

        int count = 0;

        vector<bool> arr(26, false);

        for (int i = 0; i < word.length(); i++) {

            char ch = word[i];

            if (isupper(ch)) {

                continue;
            }
            else {

                char upper = toupper(ch);

                for (int j = 0; j < word.length(); j++) {

                    if (word[j] == upper && !arr[ch - 'a']) {

                        count++;

                        arr[ch - 'a'] = true;
                    }
                }
            }
        }

        return count;
    }
};
*/