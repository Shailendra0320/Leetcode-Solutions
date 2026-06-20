// Approach-1 (Peak Formula)
// T.C : O(m log m)
// S.C : O(m)

class Solution
{
public:
  int maxBuilding(
      int n,
      vector<vector<int>> &restrictions)
  {

    vector<vector<int>> list;

    for (auto &r : restrictions)
    {
      list.push_back(r);
    }

    list.push_back({1, 0});
    list.push_back({n, n - 1});

    sort(
        list.begin(),
        list.end());

    int size = list.size();

    for (int i = 1; i < size; i++)
    {

      list[i][1] =
          min(
              list[i][1],
              list[i - 1][1] +
                  list[i][0] -
                  list[i - 1][0]);
    }

    for (int i = size - 2; i >= 0; i--)
    {

      list[i][1] =
          min(
              list[i][1],
              list[i + 1][1] +
                  list[i + 1][0] -
                  list[i][0]);
    }

    int answer = 0;

    for (int i = 1; i < size; i++)
    {

      int distance =
          list[i][0] -
          list[i - 1][0];

      int peak =
          (distance +
           list[i][1] +
           list[i - 1][1]) /
          2;

      answer =
          max(
              answer,
              peak);
    }

    return answer;
  }
};

/*
//Approach-2 (Alternative Peak Formula)
//T.C : O(m log m)
//S.C : O(m)

class Solution {
public:

    int maxBuilding(
        int n,
        vector<vector<int>>& restrictions
    ) {

        vector<vector<int>> list;

        list.push_back({1,0});

        for(auto &restriction : restrictions){
            list.push_back(restriction);
        }

        sort(
            list.begin(),
            list.end()
        );

        if(list.back()[0] != n){
            list.push_back({n,n-1});
        }

        for(int i=1;i<list.size();i++){

            list[i][1] =
                min(
                    list[i][1],
                    list[i-1][1]
                    +
                    list[i][0]
                    -
                    list[i-1][0]
                );
        }

        for(int i=list.size()-2;i>=0;i--){

            list[i][1] =
                min(
                    list[i][1],
                    list[i+1][1]
                    +
                    list[i+1][0]
                    -
                    list[i][0]
                );
        }

        int answer = 0;

        for(int i=1;i<list.size();i++){

            int h1 = list[i-1][1];
            int h2 = list[i][1];

            int x = list[i-1][0];
            int y = list[i][0];

            answer =
                max(
                    answer,
                    max(h1,h2)
                    +
                    (
                        y-x-
                        abs(h1-h2)
                    )/2
                );
        }

        return answer;
    }
};
*/