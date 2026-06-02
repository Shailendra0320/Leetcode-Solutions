class Solution {
    public int earliestFinishTime(int[] landStart, int[] landDuration,
                                  int[] waterStart, int[] waterDuration) {

        int answer = Integer.MAX_VALUE;

        int firstRideDone = Integer.MAX_VALUE;
        for (int i = 0; i < landStart.length; i++) {
            firstRideDone = Math.min(firstRideDone,
                                     landStart[i] + landDuration[i]);
        }

        for (int j = 0; j < waterStart.length; j++) {
            int completion =
                    (firstRideDone < waterStart[j]
                            ? waterStart[j]
                            : firstRideDone)
                    + waterDuration[j];

            answer = Math.min(answer, completion);
        }

        int secondRideDone = Integer.MAX_VALUE;
        for (int i = 0; i < waterStart.length; i++) {
            secondRideDone = Math.min(secondRideDone,
                                      waterStart[i] + waterDuration[i]);
        }

        for (int j = 0; j < landStart.length; j++) {
            int completion =
                    (secondRideDone < landStart[j]
                            ? landStart[j]
                            : secondRideDone)
                    + landDuration[j];

            answer = Math.min(answer, completion);
        }

        return answer;
    }
}