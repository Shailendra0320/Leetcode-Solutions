// Approach-1 (Greedy)
// T.C : O(n + m)
// S.C : O(1)

class Solution
{
public:
  int earliestFinishTime(
      vector<int> &landStartTime,
      vector<int> &landDuration,
      vector<int> &waterStartTime,
      vector<int> &waterDuration)
  {

    int ans = INT_MAX;

    int bestLandEnd = INT_MAX;

    for (int i = 0; i < landStartTime.size(); i++)
    {

      bestLandEnd = min(
          bestLandEnd,
          landStartTime[i] + landDuration[i]);
    }

    for (int j = 0; j < waterStartTime.size(); j++)
    {

      ans = min(
          ans,
          max(bestLandEnd, waterStartTime[j]) + waterDuration[j]);
    }

    int bestWaterEnd = INT_MAX;

    for (int j = 0; j < waterStartTime.size(); j++)
    {

      bestWaterEnd = min(
          bestWaterEnd,
          waterStartTime[j] + waterDuration[j]);
    }

    for (int i = 0; i < landStartTime.size(); i++)
    {

      ans = min(
          ans,
          max(bestWaterEnd, landStartTime[i]) + landDuration[i]);
    }

    return ans;
  }
};

/*
//Approach-2 (Equivalent Greedy Form)
//T.C : O(n + m)
//S.C : O(1)

class Solution {
public:

    int earliestFinishTime(
        vector<int>& landStartTime,
        vector<int>& landDuration,
        vector<int>& waterStartTime,
        vector<int>& waterDuration) {

        int ans = INT_MAX;

        int earliestLandFinishTime = INT_MAX;
        int earliestWaterFinishTime = INT_MAX;

        for (int i = 0; i < landStartTime.size(); i++) {

            earliestLandFinishTime = min(
                earliestLandFinishTime,
                landStartTime[i] + landDuration[i]
            );
        }

        for (int i = 0; i < waterStartTime.size(); i++) {

            if (waterStartTime[i] >= earliestLandFinishTime) {

                ans = min(
                    ans,
                    waterStartTime[i] + waterDuration[i]
                );
            }
            else {

                ans = min(
                    ans,
                    earliestLandFinishTime + waterDuration[i]
                );
            }
        }

        for (int i = 0; i < waterStartTime.size(); i++) {

            earliestWaterFinishTime = min(
                earliestWaterFinishTime,
                waterStartTime[i] + waterDuration[i]
            );
        }

        for (int i = 0; i < landStartTime.size(); i++) {

            if (landStartTime[i] >= earliestWaterFinishTime) {

                ans = min(
                    ans,
                    landStartTime[i] + landDuration[i]
                );
            }
            else {

                ans = min(
                    ans,
                    earliestWaterFinishTime + landDuration[i]
                );
            }
        }

        return ans;
    }
};
*/