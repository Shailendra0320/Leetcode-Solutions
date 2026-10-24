//Approach-1 (Day-of-Year + Interval Intersection)
//T.C : O(1)
//S.C : O(1)

class Solution {
    private int dayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3));

        int[] prefix = {
            0, 31, 59, 90, 120, 151,
            181, 212, 243, 273, 304, 334
        };

        return prefix[month - 1] + day;
    }

    public int countDaysTogether(
        String arriveAlice,
        String leaveAlice,
        String arriveBob,
        String leaveBob
    ) {
        int startAlice = dayOfYear(arriveAlice);
        int endAlice = dayOfYear(leaveAlice);
        int startBob = dayOfYear(arriveBob);
        int endBob = dayOfYear(leaveBob);

        int start = Math.max(startAlice, startBob);
        int end = Math.min(endAlice, endBob);

        return Math.max(0, end - start + 1);
    }
}

//Approach-2 (Enumerate Every Calendar Day)
//T.C : O(365) = O(1)
//S.C : O(1)

class Solution2 {
    private int dayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3));

        int[] prefix = {
            0, 31, 59, 90, 120, 151,
            181, 212, 243, 273, 304, 334
        };

        return prefix[month - 1] + day;
    }

    public int countDaysTogether(
        String arriveAlice,
        String leaveAlice,
        String arriveBob,
        String leaveBob
    ) {
        int startAlice = dayOfYear(arriveAlice);
        int endAlice = dayOfYear(leaveAlice);
        int startBob = dayOfYear(arriveBob);
        int endBob = dayOfYear(leaveBob);

        int answer = 0;

        for (int day = 1; day <= 365; day++) {
            if (day >= startAlice && day <= endAlice
                    && day >= startBob && day <= endBob) {
                answer++;
            }
        }

        return answer;
    }
}