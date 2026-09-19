//Approach-1 (Closest Point on Rectangle)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:
    bool checkOverlap(
        int radius,
        int xCenter,
        int yCenter,
        int x1,
        int y1,
        int x2,
        int y2
    ) {

        int closestX = max(
            x1,
            min(xCenter, x2)
        );

        int closestY = max(
            y1,
            min(yCenter, y2)
        );

        long long dx = 1LL * xCenter - closestX;
        long long dy = 1LL * yCenter - closestY;

        return dx * dx + dy * dy
                <= 1LL * radius * radius;
    }
};


//Approach-2 (Direct Distance Components)
//T.C : O(1)
//S.C : O(1)

class Solution2 {
public:
    bool checkOverlap(
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

        return 1LL * dx * dx + 1LL * dy * dy
                <= 1LL * radius * radius;
    }
};