#C++ Solution

```cpp class Solution
{
public:
  int numberOfSubstrings(string s)
  {

    vector<int> count(3, 0);

    int left = 0;

    int answer = 0;

    int n = s.size();

    for (int right = 0; right < n; right++)
    {

      count[s[right] - 'a']++;

      while (count[0] > 0 &&
             count[1] > 0 &&
             count[2] > 0)
      {

        answer += n - right;

        count[s[left] - 'a']--;

        left++;
      }
    }

    return answer;
  }
};

/*

Alternative Approach (Last Seen Indices)

// T.C : O(n)
// S.C : O(1)

class Solution {
public:
    int numberOfSubstrings(string s) {

        vector<int> lastSeen(3, -1);

        int answer = 0;

        for (int i = 0; i < s.size(); i++) {

            lastSeen[s[i] - 'a'] = i;

            int minIndex = min(
                lastSeen[0],
                min(lastSeen[1], lastSeen[2])
            );

            if (minIndex != -1) {

                answer += minIndex + 1;
            }
        }

        return answer;
    }
};

*/
