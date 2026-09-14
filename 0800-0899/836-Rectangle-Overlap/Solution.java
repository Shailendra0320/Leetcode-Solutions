//Approach-1 (Intersection Area)
//T.C : O(1)
//S.C : O(1)

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        int left = Math.max(rec1[0], rec2[0]);
        int bottom = Math.max(rec1[1], rec2[1]);

        int right = Math.min(rec1[2], rec2[2]);
        int top = Math.min(rec1[3], rec2[3]);

        int width = right - left;
        int height = top - bottom;

        return width > 0 && height > 0;
    }
}


//Approach-2 (Check Separating Conditions)
//T.C : O(1)
//S.C : O(1)

class Solution2 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        // Completely separated horizontally
        if (rec1[2] <= rec2[0] || rec2[2] <= rec1[0]) {
            return false;
        }

        // Completely separated vertically
        if (rec1[3] <= rec2[1] || rec2[3] <= rec1[1]) {
            return false;
        }

        return true;
    }
}