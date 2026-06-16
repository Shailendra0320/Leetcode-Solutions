// Approach-1 (String Simulation)
// T.C : O(n²)
// S.C : O(n)

class Solution
{
public:
  string processStr(string s)
  {

    string result;

    for (char ch : s)
    {

      if (ch == '*')
      {

        if (!result.empty())
        {

          result.pop_back();
        }
      }
      else if (ch == '#')
      {

        result += result;
      }
      else if (ch == '%')
      {

        reverse(result.begin(), result.end());
      }
      else
      {

        result.push_back(ch);
      }
    }

    return result;
  }
};

/*
//Approach-2 (Vector Simulation)
//T.C : O(n²)
//S.C : O(n)

class Solution {
public:

    string processStr(string input) {

        vector<char> characters;

        for (char current : input) {

            if (current >= 'a' && current <= 'z') {

                characters.push_back(current);
            }
            else if (current == '*') {

                if (!characters.empty()) {

                    characters.pop_back();
                }
            }
            else if (current == '#') {

                int size = characters.size();

                for (int i = 0; i < size; i++) {

                    characters.push_back(characters[i]);
                }
            }
            else {

                reverse(
                    characters.begin(),
                    characters.end()
                );
            }
        }

        string answer;

        for (char ch : characters) {

            answer += ch;
        }

        return answer;
    }
};
*/

/*
//Approach-3 (StringBuilder Equivalent)
//T.C : O(n²)
//S.C : O(n)

class Solution {
public:

    string processStr(string input) {

        string output;

        for (char current : input) {

            if (islower(current)) {

                output.push_back(current);
            }
            else if (current == '*') {

                if (!output.empty()) {

                    output.pop_back();
                }
            }
            else if (current == '#') {

                output += output;
            }
            else {

                reverse(
                    output.begin(),
                    output.end()
                );
            }
        }

        return output;
    }
};
*/