//Approach-1 (Greedy)
//T.C : O(n + m)
//S.C : O(1)

class Solution {

  public int earliestFinishTime(
      int[] landStartTime,
      int[] landDuration,
      int[] waterStartTime,
      int[] waterDuration) {

    int ans = Integer.MAX_VALUE;

    int bestLandEnd = Integer.MAX_VALUE;

    for (int i = 0; i < landStartTime.length; i++) {

      bestLandEnd = Math.min(
          bestLandEnd,
          landStartTime[i] + landDuration[i]);
    }

    for (int j = 0; j < waterStartTime.length; j++) {

      ans = Math.min(
          ans,
          Math.max(bestLandEnd, waterStartTime[j])
              + waterDuration[j]);
    }

    int bestWaterEnd = Integer.MAX_VALUE;

    for (int j = 0; j < waterStartTime.length; j++) {

      bestWaterEnd = Math.min(
          bestWaterEnd,
          waterStartTime[j] + waterDuration[j]);
    }

    for (int i = 0; i < landStartTime.length; i++) {

      ans = Math.min(
          ans,
          Math.max(bestWaterEnd, landStartTime[i])
              + landDuration[i]);
    }

    return ans;
  }
}

/*
 * //Approach-2 (Equivalent Greedy Form)
 * //T.C : O(n + m)
 * //S.C : O(1)
 * 
 * class Solution {
 * 
 * public int earliestFinishTime(
 * int[] landStartTime,
 * int[] landDuration,
 * int[] waterStartTime,
 * int[] waterDuration) {
 * 
 * int ans = Integer.MAX_VALUE;
 * 
 * int earliestLandFinishTime = Integer.MAX_VALUE;
 * int earliestWaterFinishTime = Integer.MAX_VALUE;
 * 
 * for (int i = 0; i < landStartTime.length; i++) {
 * 
 * earliestLandFinishTime = Math.min(
 * earliestLandFinishTime,
 * landStartTime[i] + landDuration[i]
 * );
 * }
 * 
 * for (int i = 0; i < waterStartTime.length; i++) {
 * 
 * if (waterStartTime[i] >= earliestLandFinishTime) {
 * 
 * ans = Math.min(
 * ans,
 * waterStartTime[i] + waterDuration[i]
 * );
 * }
 * else {
 * 
 * ans = Math.min(
 * ans,
 * earliestLandFinishTime + waterDuration[i]
 * );
 * }
 * }
 * 
 * for (int i = 0; i < waterStartTime.length; i++) {
 * 
 * earliestWaterFinishTime = Math.min(
 * earliestWaterFinishTime,
 * waterStartTime[i] + waterDuration[i]
 * );
 * }
 * 
 * for (int i = 0; i < landStartTime.length; i++) {
 * 
 * if (landStartTime[i] >= earliestWaterFinishTime) {
 * 
 * ans = Math.min(
 * ans,
 * landStartTime[i] + landDuration[i]
 * );
 * }
 * else {
 * 
 * ans = Math.min(
 * ans,
 * earliestWaterFinishTime + landDuration[i]
 * );
 * }
 * }
 * 
 * return ans;
 * }
 * }
 */