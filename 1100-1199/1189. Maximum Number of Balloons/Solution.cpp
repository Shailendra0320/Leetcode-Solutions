// Approach-1 (Frequency Counting)
// T.C : O(n)
// S.C : O(1)

class Solution
{
public:
  int maxNumberOfBalloons(string text)
  {

    vector<int> frequency(26, 0);

    for (char ch : text)
    {
      frequency[ch - 'a']++;
    }

    return min(
        min(
            frequency['b' - 'a'],
            frequency['a' - 'a']),
        min(
            frequency['l' - 'a'] / 2,
            min(
                frequency['o' - 'a'] / 2,
                frequency['n' - 'a'])));
  }
};

/*
//Approach-2 (unordered_map)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:

    int maxNumberOfBalloons(string text) {

        unordered_map<char,int> frequency;

        for(char ch : text) {

            frequency[ch]++;
        }

        int b = frequency['b'];

        int a = frequency['a'];

        int l = frequency['l'] / 2;

        int o = frequency['o'] / 2;

        int n = frequency['n'];

        return min(
            min(b,a),
            min(
                l,
                min(o,n)
            )
        );
    }
};
*/