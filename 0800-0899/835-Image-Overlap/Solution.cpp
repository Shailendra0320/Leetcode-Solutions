#include <bits/stdc++.h>
using namespace std;

// Approach-1 (Brute Force Translation / Simulation)
// T.C : O(n^4)
// S.C : O(1)

class Solution
{
public:
  int largestOverlap(vector<vector<int>> &img1,
                     vector<vector<int>> &img2)
  {

    int n = img1.size();
    int answer = 0;

    for (int dr = -(n - 1); dr <= n - 1; dr++)
    {
      for (int dc = -(n - 1); dc <= n - 1; dc++)
      {

        int overlap = 0;

        for (int r = 0; r < n; r++)
        {
          for (int c = 0; c < n; c++)
          {

            int nr = r + dr;
            int nc = c + dc;

            if (nr < 0 || nr >= n ||
                nc < 0 || nc >= n)
            {
              continue;
            }

            if (img1[r][c] == 1 &&
                img2[nr][nc] == 1)
            {
              overlap++;
            }
          }
        }

        answer = max(answer, overlap);
      }
    }

    return answer;
  }
};

// Approach-2 (Coordinates + Frequency Map)
// T.C : O(n^2 + k1 * k2)
// S.C : O(k1 * k2)

class Solution2
{
public:
  int largestOverlap(vector<vector<int>> &img1,
                     vector<vector<int>> &img2)
  {

    int n = img1.size();

    vector<pair<int, int>> ones1;
    vector<pair<int, int>> ones2;

    for (int r = 0; r < n; r++)
    {
      for (int c = 0; c < n; c++)
      {

        if (img1[r][c] == 1)
        {
          ones1.push_back({r, c});
        }

        if (img2[r][c] == 1)
        {
          ones2.push_back({r, c});
        }
      }
    }

    map<pair<int, int>, int> frequency;

    int answer = 0;

    for (auto &p1 : ones1)
    {
      for (auto &p2 : ones2)
      {

        int dr = p2.first - p1.first;
        int dc = p2.second - p1.second;

        int count = ++frequency[{dr, dc}];

        answer = max(answer, count);
      }
    }

    return answer;
  }
};