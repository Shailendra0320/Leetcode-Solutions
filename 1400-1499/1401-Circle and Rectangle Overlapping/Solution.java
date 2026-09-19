//Approach-1 (Closest Point on Rectangle)
//T.C : O(1)
//S.C : O(1)

class Solution {
    public boolean checkOverlap(
        int radius,
        int xCenter,
        int yCenter,
        int x1,
        int y1,
        int x2,
        int y2
    ) {

        int closestX = Math.max(
            x1,
            Math.min(xCenter, x2)
        );

        int closestY = Math.max(
            y1,
            Math.min(yCenter, y2)
        );

        long dx = (long) xCenter - closestX;
        long dy = (long) yCenter - closestY;

        return dx * dx + dy * dy
                <= (long) radius * radius;
    }
}


//Approach-2 (Direct Distance Components)
//T.C : O(1)
//S.C : O(1)

class Solution2 {
    public boolean checkOverlap(
        int radius,
        int xCenter,
        int yCenter,
        int x1,
        int y1,
        int x2,
        int y2
    ) {

        int dx = 0;
        int dy = 0;

        if (xCenter < x1) {
            dx = x1 - xCenter;
        } else if (xCenter > x2) {
            dx = xCenter - x2;
        }

        if (yCenter < y1) {
            dy = y1 - yCenter;
        } else if (yCenter > y2) {
            dy = yCenter - y2;
        }

        return (long) dx * dx + (long) dy * dy
                <= (long) radius * radius;
    }
}