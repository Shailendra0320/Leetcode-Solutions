//Approach-1 (Math Formula)
//T.C : O(1)
//S.C : O(1)

class Solution {

    public double angleClock(int hour, int minutes) {

        double minuteAngle = 6.0 * minutes;

        double hourAngle =
            30.0 * hour + 0.5 * minutes;

        double angle =
            Math.abs(hourAngle - minuteAngle);

        return Math.min(
            angle,
            360.0 - angle
        );
    }
}