// Approach-1 (Array Tracking)
// T.C : O(n)
// S.C : O(1)

class Solution
{
public:
  int numberOfSpecialChars(string word)
  {

    vector<int> lowerLast(26, -1);

    vector<int> upperFirst(26, -1);

    for (int i = 0; i < word.length(); i++)
    {

      char ch = word[i];

      if (islower(ch))
      {

        lowerLast[ch - 'a'] = i;
      }
      else
      {

        if (upperFirst[ch - 'A'] == -1)
        {

          upperFirst[ch - 'A'] = i;
        }
      }
    }

    int count = 0;

    for (int i = 0; i < 26; i++)
    {

      if (lowerLast[i] != -1 &&
          upperFirst[i] != -1 &&
          lowerLast[i] < upperFirst[i])
      {

        count++;
      }
    }

    return count;
  }
};

/*
//Approach-2 (HashMap Tracking)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    int numberOfSpecialChars(string word) {

        unordered_map<char, int> firstUpper;

        unordered_map<char, int> lastLower;

        int c = 0;

        for (int i = 0; i < word.length(); i++) {

            char ch = word[i];

            if (islower(ch)) {

                lastLower[ch] = i;
            }
            else {

                if (!firstUpper.count(ch)) {

                    firstUpper[ch] = i;
                }
            }
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {

            char upper = toupper(ch);

            if (lastLower.count(ch) &&
                firstUpper.count(upper)) {

                if (lastLower[ch] < firstUpper[upper]) {

                    c++;
                }
            }
        }

        return c;
    }
};
*/